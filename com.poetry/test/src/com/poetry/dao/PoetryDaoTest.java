package com.poetry.dao;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


@RunWith( SpringJUnit4ClassRunner.class )
@ContextConfiguration(
	locations= { "classpath:com/poetry/dao/PoetryDaoTest-context.xml" }
)
public class PoetryDaoTest
{
	@Autowired
	protected PoetryDao poetryDao;
	
	@Autowired
	protected SessionFactory sessionFactory;
	@Autowired
	protected Session session;
	
	@Test
	public
	void
	test_getTodayPoetry()
	throws Exception
	{
		when( sessionFactory.getCurrentSession() ).thenReturn( session );
		Query queryMock = mock( Query.class );
		when( session.createQuery( Matchers.anyString() ) ).thenReturn( queryMock );
		when( queryMock.list() ).thenReturn( Arrays.asList( new Object[][] {
			new Object[] { "e6e4aa28ac156ff528294f626e4d4ef1", 3L },
			new Object[] { "e6e4aa26ac156ff551af4309b958f5d7", 2L }
		} ) );
		
		final List<String> poetries = poetryDao.getTodayPoetryCandidates();
		assertEquals( 1, poetries.size() );
		assertEquals( "e6e4aa28ac156ff528294f626e4d4ef1", poetries.get( 0 ) );
	}
	

}
