create table file
(
	id varchar( 64 ),
	name varchar( 256 ),
	mime varchar( 64 ),
	contents blob
);

create table poetry
(
	id varchar( 64 ),
	createdDate date,
	title varchar( 256 ),
	contents TEXT
);

create table reply
(
	id varchar( 64 ),
	targetId varchar( 64 ),
	user varchar( 128 ),
	contents varchar( 256 ),
	createdDate date
);

select * from poetry

select * from binary





