package com.poetry.install;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.poetry.dao.BinaryDao;
import com.poetry.dao.BookmarkDao;
import com.poetry.dao.MissionPoetryDao;
import com.poetry.dao.PoetryDao;
import com.poetry.dao.ReplyDao;
import com.poetry.dao.StarDao;
import com.poetry.model.Binary;
import com.poetry.model.Bookmark;
import com.poetry.model.Mission;
import com.poetry.model.MissionPoetry;
import com.poetry.model.Poet;
import com.poetry.model.Poetry;
import com.poetry.model.Reply;
import com.poetry.model.Star;

import escode.util.CollectionUtils;

@Component
public class
InstallPoetry
extends AbstractInstall
implements Install<Poetry>
{
	protected Map<String, Poet> poets;
	
	protected Map<String, Mission> missions;
	
	@Autowired
	protected BinaryDao binaryDao;
	
	@Autowired
	protected PoetryDao poetryDao;
	
	@Autowired
	protected MissionPoetryDao missionPoetryDao;
	
	@Autowired
	protected ReplyDao replyDao;
	
	@Autowired
	protected StarDao starDao;
	
	@Autowired
	protected BookmarkDao bookmarkDao;
	

	public void setPoets( Map<String, Poet> poets )
	{
		this.poets = poets;
	}
	public void setMissions( final Map<String, Mission> missions )
	{
		this.missions = missions;
	}

	@Override
	public
	Map<String, Poetry>
	install()
	throws Exception
	{
		final String missionId = CollectionUtils.pickupFirst( missions.keySet() );
		final Mission mission = missions.get( missionId );
		
		final Object[] poetries = new Object[] {
			// 오늘의 미션 시들
			new Poetry( "님의 침묵", poets.get( "bylee" ), null, mission.getImageId() ),
			new MissionPoetry(),
			new Reply( null, poets.get( "anjong" ), "굿~" ),
			new Bookmark( null, "anjong" ),
			
			new Poetry( "두루미천남성", poets.get( "anjong" ), null, mission.getImageId() ),
			new MissionPoetry(),
			new Bookmark( null, "bylee" ),
			new Star( null, "bylee" ),
			
			
			new Poetry( "사랑의 무게", poets.get( "csoonoosc" ), null, mission.getImageId() ),
			new MissionPoetry(),
			new Reply( null, poets.get( "anjong" ), "굿~" ),
			new Reply( null, poets.get( "hellojintae" ), "굿~2" ),
			new Bookmark( null, "bylee" ),
			new Star( null, "bylee" ),
			
			new Poetry( "그래도 좋은 인연", poets.get( "hellojintae" ), null, mission.getImageId() ),
			new MissionPoetry(),
			new Reply( null, poets.get( "anjong" ), "굿~" ),
			new Bookmark( null, "anjong" ),
			new Bookmark( null, "bylee" ),
			new Star( null, "bylee" ),
			
			new Poetry( "내 생애 가장 아름다운 편지", poets.get( "hanseoung82" ), null, mission.getImageId() ),
			new MissionPoetry(),
			new Reply( null, poets.get( "anjong" ), "굿~" ),
			new Reply( null, poets.get( "hellojintae" ), "굿~2" ),
			new Bookmark( null, "bylee" ),
			new Star( null, "bylee" ),
			
			// 개인 시들
			new Binary( "154FD3014BB3276C630E3C_LP9csaIpvi.jpg", "bylee", "image/jpg", null ),
			new Poetry( "목화", poets.get( "bylee" ), null, null ),
			new Reply( null, poets.get( "anjong" ), "굿~" ),
			new Reply( null, poets.get( "hanseoung82" ), "퍼가요~" ),
			new Reply( null, poets.get( "hellojintae" ), "굿~2" ),
			new Reply( null, poets.get( "anjong" ), "test5" ),
			new Reply( null, poets.get( "anjong" ), "test4" ),
			new Reply( null, poets.get( "anjong" ), "test3" ),
			new Reply( null, poets.get( "anjong" ), "test2" ),
			new Reply( null, poets.get( "anjong" ), "test1" ),
			new Reply( null, poets.get( "anjong" ), "test는 1,2,3,4순으로 보여야 한다." ),
			new Star( null, "anjong" ),
			new Star( null, "hanseoung82" ),
			new Star( null, "csoonoosc" ),
			
			new Binary( "cheonnamseong9.jpg", "bylee", "image/jpg", null ),
			new Poetry( "天南星", poets.get( "bylee" ), null, null ),
			
			new Binary( "kkwongeuivaram14.jpg", "bylee", "image/jpg", null ),
			new Poetry( "꿩의바람꽃을 아시나요", poets.get( "bylee" ), null, null ),
			
			new Poetry( "푸른 하늘", poets.get( "bylee" ), null, null ),
			
			new Binary( "2040624417_x7eFAsUk_BFECBBEA.jpg", "bylee", "image/jpg", null ),
			new Poetry( "비를 좋아하는 사람은", poets.get( "bylee" ), null, null ),
			
			new Binary( "1257C04D4D406E940C4ACD_f7pQBjVykAx.jpg", "hanseoung82", "image/jpg", null ),
			new Poetry( "괭이밥", poets.get( "hanseoung82" ), null, null ),
			new Reply( null, poets.get( "csoonoosc" ), "굿~" ),
			new Reply( null, poets.get( "hanseoung82" ), "퍼가요~" ),
			new Reply( null, poets.get( "hellojintae" ), "굿~" ),
			new Bookmark( null, "bylee" ),
			new Bookmark( null, "anjong" ),
			new Star( null, "anjong" ),
			new Star( null, "bylee" ),
			
			new Binary( "166988494E6B7DF203CD14_ruthtwIn.jpg", "hellojintae", "image/jpg", null ),
			new Poetry( "도둑놈의지팡이", poets.get( "hellojintae" ), null, null ),
			new Reply( null, poets.get( "hanseoung82" ), "퍼가요~" ),
			new Star( null, "csoonoosc" ),
			new Bookmark( null, "bylee" ),
			
		};
		final HashMap<String, Poetry> ret = new HashMap<String, Poetry>();
		
		Binary binary = null;
		Poetry poetry = null;
		for ( int i = 0, n = poetries.length ; i < n ; ++i )
		{
			if ( poetries[i] instanceof Binary )
			{
				binary = (Binary) poetries[i];
				binary.setContents( load( binary.getName() ) );
				binaryDao.addNewBinary( binary );
				poetry = null;
			}
			else if ( poetries[i] instanceof MissionPoetry )
			{
				final MissionPoetry missionPoetry = (MissionPoetry) poetries[i];
				missionPoetry.setPoetryId( poetry.getId() );
				missionPoetry.setMissionId( missionId );
				missionPoetryDao.addMissionPoetry( missionPoetry );
			}
			else if ( poetries[i] instanceof Poetry )
			{
				poetry = (Poetry) poetries[i];
				poetry.setContents( read( poetry.getTitle() ) );
				if ( null != binary )
				{
					poetry.setImage( binary.getId() );
				}
				poetryDao.addNewPoetry( poetry );
				binary = null;
			}
			else if ( poetries[i] instanceof Reply )
			{
				final Reply reply = (Reply) poetries[i];
				reply.setTargetId( poetry.getId() );
				reply.setCreateDate( new Date() );
				replyDao.addReply( reply );
			}
			else if ( poetries[i] instanceof Star )
			{
				final Star star = (Star) poetries[i];
				star.setPoetryId( poetry.getId() );
				starDao.addStar( star );
			}
			else if ( poetries[i] instanceof Bookmark ) 
			{
				final Bookmark bookmark = (Bookmark) poetries[i];
				bookmark.setPoetryId( poetry.getId() );
				bookmarkDao.addBookmark( bookmark );
			}
		}
		
		return ret;
	}

}
