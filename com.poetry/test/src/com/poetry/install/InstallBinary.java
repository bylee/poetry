package com.poetry.install;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poetry.dao.BinaryDao;
import com.poetry.model.Binary;

@Component
public class
InstallBinary
extends AbstractInstall
implements Install<Binary>
{

	@Autowired
	protected BinaryDao binaryDao;
	
	public
	Map<String, Binary>
	install()
	throws Exception
	{
		final Binary[] binaries = new Binary[] {
			new Binary( null, "mainimage.jpeg", "bylee", "image/jpg", load( "mainimage.jpeg" ) ),
			new Binary( null, "image2-1.jpeg", "bylee", "image/jpg", load( "image2-1.jpeg" ) ),
			new Binary( null, "image2-2.jpeg", "bylee", "image/jpg", load( "image2-2.jpeg" ) ),
			new Binary( null, "image3-1.jpeg", "bylee", "image/jpg", load( "image3-1.jpeg" ) ),
			new Binary( null, "image3-2.jpeg", "bylee", "image/jpg", load( "image3-2.jpeg" ) ),
			new Binary( null, "fallroad.jpeg", "bylee", "image/jpg", load( "fallroad.jpeg" ) ),
		};
		
		final HashMap<String, Binary> ret = new HashMap<String, Binary>();
		for ( final Binary binary : binaries )
		{
			binaryDao.addBinary( binary );
			ret.put( binary.getName(), binary );
		}
		return ret;
	}

}
