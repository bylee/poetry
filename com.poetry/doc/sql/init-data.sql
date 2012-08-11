INSERT INTO Poet ( username, password, pen_name ) VALUES
( 'bylee', 'bylee', 'bylee1');

INSERT INTO Poet ( username, password, pen_name ) VALUES
( 'anjong', 'anjong', 'anjong1');

INSERT INTO Poet ( username, password, pen_name ) VALUES
( 'csoonoosc', 'csoonoosc', 'choongsun');

INSERT INTO Poet ( username, password, pen_name ) VALUES
( 'hellojintae', 'hellojintae', 'jintae');

INSERT INTO Poetry( id, title, author, image, contents ) VALUES
( 'first', 'My First Poetry', 'bylee', 'aaaa', 'Hello, world' );



select * from Poet;

select * from Following;

select * from Bookmark;


select * from File;

select * from Reply order by id desc;

select * from poetry where author = 'bylee' order by id desc;

select * from Mission;

select * from missionpoetry;

select * from Star;

select * from today

select * from missionpoetry, poetry where missionpoetry.poetryid = poetry.id and missionpoetry.date = ? order by poetry.id

select poetry.id, count( * ) rank
from poetry, star
where poetry.id = star.poetryId
group by poetry.id order by rank desc

select *
from poetry, following 
where poetry.author = following.following 
and following.follower = 'bylee'
order by poetry.id;

select poetry.id, count( poetry.id ) as rank
from poetry, star, missionpoetry
where missionpoetry.poetryid = poetry.id and poetry.id = star.poetryId 
group by poetry.id 
order by rank, id desc

select * from following

select * 
from following f
where f.following = 'anjong' and ( f.following, f.follower ) not in ( select b.following, b.follower from block b )

select *
from Poetry p, Today t
where t.poetry = p.id and t.date = ?