create table Poet
(
	username varchar( 32 ),
	pen_name varchar( 32 ),
	password varchar( 64 ),
	authority varchar( 128 )
	
);

create table File
(
	id varchar( 64 ),
	name varchar( 256 ),
	mime varchar( 64 ),
	contents mediumblob
);

create table Poetry
(
	id varchar( 64 ),
	title varchar( 256 ),
	author varchar( 32 ),
	image varchar( 64 ),
	createdDate date,
	contents TEXT,
	star numeric
);

create table Reply
(
	id varchar( 64 ),
	targetId varchar( 64 ),
	writer varchar( 128 ),
	contents varchar( 256 ),
	createdDate date
);

