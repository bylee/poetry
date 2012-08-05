package com.poetry.service;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.ImageDao;
import com.poetry.dao.MissionDao;
import com.poetry.dao.MissionPoetryDao;
import com.poetry.model.Binary;
import com.poetry.model.ImageAnalysis;
import com.poetry.model.Mission;
import com.poetry.model.MissionPoetry;
import com.poetry.model.Poetry;

@Service
public class
MissionService
extends AbstractService
{
	@Autowired( required = false )
	protected BinaryDao binaryDao;
	
	@Autowired( required = false )
	protected MissionDao missionDao;
	
	@Autowired( required = false )
	protected MissionPoetryDao missionPoetDao;
	
	@Autowired( required = false )
	protected ImageDao imageDao;
	
	public Mission getMission() {
		return getMission( new Date() );
	}
	
	public Mission getMission( Date date )
	{
		return missionDao.getMission( date );
	}
	
	public
	Binary
	getBinary(
		final String id
	)
	{
		return binaryDao.get( id );
	}

	public
	String
	upload(
		final Binary binary
	)
	{
		try {
			analyze( binary );
		} catch (
			final IOException e
		)
		{
		}
		return binaryDao.addBinary( binary );
	}
	
	public
	String
	upload(
		final Mission mission
	)
	{
		missionDao.addNewMission( mission );
		logger.info( "Mission registered :{}", mission );
		return mission.getId();
	}

	public
	ImageAnalysis
	analyze(
		final Binary binary
	) throws IOException
	{
		if ( null == binary )
		{
			return null;
		}
		final String mime = binary.getMime();

		if ( null == mime || !mime.startsWith( "image/" ) )
		{
			return null;
		}

		
		final byte[] contents = binary.getContents();
		
		if ( null == contents )
		{
			return null;
		}

		final ImageAnalysis analysis = new ImageAnalysis();
		final ImageInputStream is = ImageIO.createImageInputStream( new ByteArrayInputStream( contents ) );
		final Iterator<ImageReader> iter = ImageIO.getImageReaders( is );

		if (!iter.hasNext())
		{
			return null;
		}
		final ImageReader imageReader = (ImageReader) iter.next();
		imageReader.setInput( is );
		final BufferedImage image = imageReader.read( 0 );
		
		int height = image.getHeight();
        int width = image.getWidth();
        
        final Raster raster = image.getRaster();
        
        final int nGrid = 6;
        double offset = ( nGrid / 2.0 );
        for ( int x = 0 ; x < nGrid ; ++x )
        {
        	double coefficientX = Math.abs( x - offset ) + 1;
        	for ( int y = 0 ; y < nGrid ; ++y )
        	{
        		double coefficientY = Math.abs( y - offset ) + 1;
        		double[] value = sum(
        			raster,
        			(int) (x * width / nGrid),
        			(int) (y * height / nGrid),
        			(int) (( x + 1 ) * width / nGrid ),
        			(int) (( y + 1 ) * height / nGrid )
        		);
        		
        		final double coefficient = Math.max( coefficientX, coefficientY );
        		final double r = value[0] / coefficient;
        		final double g = value[1] / coefficient;
        		final double b = value[2] / coefficient;
        		
        		analysis.setRed( analysis.getRed() + r );
        		analysis.setGreen( analysis.getGreen() + g );
        		analysis.setBlue( analysis.getBlue() + b );
        		analysis.setIntensity( analysis.getIntensity() + ( r + g + b )/3 );
        	}
        }
        
		return analysis;
	}
	
	protected
	double[] sum(
		final Raster raster,
		int xStart,
		int yStart,
		int xEnd,
		int yEnd
	)
	{
		long red = 0;
		long green = 0;
		long blue = 0;
		
		for ( int x = xStart ; x<xEnd ; ++x )
		{
			for ( int y = yStart ; y<yEnd ; ++y )
			{
				red = raster.getSample( x, y, 0 );
				green = raster.getSample( x, y, 1 );
				blue = raster.getSample( x, y, 2 );
			}
		}
		
		int total = ( xEnd - xStart ) * ( yEnd - yStart );
		
		return new double[] { red / (double) total, green / (double) total, blue / (double) total };
		
	}

	public void upload(
		final MissionPoetry missionPoet
	)
	{
		missionPoetDao.addMissionPoetry( missionPoet );
	}
	
	public List<Poetry>
	getMissionPoetry(
		final Date date,
		final String start
	)
	{
		if ( null == start )
		{
			return missionPoetDao.getMissionPoetry( date );
		}
		else
		{
			return missionPoetDao.getMissionPoetry( date, start );
		}
		
	}

	
}
