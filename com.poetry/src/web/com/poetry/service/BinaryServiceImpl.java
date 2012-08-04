package com.poetry.service;

import java.awt.image.BufferedImage;
import java.awt.image.Raster;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.ImageDao;
import com.poetry.dao.MissionDao;
import com.poetry.dao.MissionPoetDao;
import com.poetry.model.Binary;
import com.poetry.model.ImageAnalysis;
import com.poetry.model.Mission;
import com.poetry.model.MissionPoetry;

public class
BinaryServiceImpl
implements BinaryService
{
	protected final Logger logger = LoggerFactory.getLogger( getClass() );
	
	@Autowired
	protected BinaryDao binaryDao;
	
	@Autowired
	protected MissionDao missionDao;
	
	@Autowired
	protected MissionPoetDao missionPoetDao;
	
	@Autowired
	protected ImageDao imageDao;
	
	@Override
	public Mission getMission() {
		return getMission( new Date() );
	}
	
	@Override
	public Mission getMission( Date date )
	{
		return missionDao.getMission( date );
	}
	
	@Override
	public
	Binary
	getBinary(
		final String id
	)
	{
		return binaryDao.get( id );
	}

	@Override
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
	
	@Override
	public String upload( Mission mission )
	{
		missionDao.addNewMission( mission );
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

	@Override
	public void upload(
		final MissionPoetry missionPoet
	)
	{
		missionPoetDao.addMissionPoet( missionPoet );
	}

	
}
