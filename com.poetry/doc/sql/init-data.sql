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

select * from File where id='ef8f1673c0a81f38517ceb9b210bf604';

select * from Reply;

select * from poetry;
select * from image;
select * from mission;

select * from Star;

select poetry.id, count( * ) rank
from poetry, star
where poetry.id = star.poetryId
group by poetry.id order by rank desc

