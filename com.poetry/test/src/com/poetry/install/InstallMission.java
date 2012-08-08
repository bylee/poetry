package com.poetry.install;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.MissionDao;
import com.poetry.model.Binary;
import com.poetry.model.Mission;

@Component
public class
InstallMission
extends AbstractInstall
implements Install<Mission>
{
	@Autowired
	protected BinaryDao binaryDao;
	
	@Autowired
	protected MissionDao missionDao;

	@Override
	public
	Map<String, Mission>
	install()
	throws Exception
	{
		final Date now = new Date();
		final Map<String, Mission> ret = new TreeMap<String, Mission>();
		
		final Object[][] data = new Object[][] {
			new Object[] {
				new Binary( "Hydrangeas.jpg", "bylee", "image/jpg", load( "Hydrangeas.jpg" ) ),
				new Mission( now, null, "황량한 사막입니다." )	
			},
			new Object[] {
				new Binary( "fallroad.jpeg", "bylee", "image/jpeg", load( "fallroad.jpeg" ) ),
				new Mission(
					new Date( now.getTime() + 24 * 60 * 60 * 1000 ),
					null,
					"이 사진은 강원도 지역의 시골길을 찍은 것이며, 멀리 보이는 길을 가르쳐주는 가을 나무들이 있는 풍경입니다. 날짜는 2002년 10월경입니다."
				)	
			}
		};
		
		for ( final Object[] datum : data )
		{
			final Binary binary = (Binary) datum[0];
			binaryDao.addNewBinary( binary );
			
			final Mission mission = (Mission) datum[1];
			mission.setImageId( binary.getId() );
			missionDao.addNewMission( mission );
			ret.put( mission.getId(), mission );
		}
		
		return ret;
	}

}
