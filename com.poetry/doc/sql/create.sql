SET GLOBAL max_allowed_packet = 1024 * 1024 * 32;
SET SESSION max_allowed_packet = 1024 * 1024 * 32;

create table Poet
(
	username varchar( 32 ),
	pen_name varchar( 32 ),
	password varchar( 64 ),
	email varchar( 128 ),
	authority varchar( 128 )
	
);

create table File
(
	id varchar( 32 ),
	name varchar( 256 ),
	owner varchar( 32 ),
	mime varchar( 64 ),
	contents mediumblob
);

create table Poetry
(
	id varchar( 32 ),
	title varchar( 256 ),
	author varchar( 32 ),
	image varchar( 64 ),
	createdDate date,
	contents TEXT
);

create table Reply
(
	id varchar( 32 ),
	targetId varchar( 32 ),
	writer varchar( 128 ),
	contents varchar( 256 ),
	createdDate date
);

create table Following
(
	following varchar( 32 ),
	follower varchar( 32 )
);

create table Star
(
	poetryId varchar( 32 ),
	poetId varchar( 32 )
);

create table Bookmark
(
	poetryId varchar( 32 ),
	poetId varchar( 32 )
);

create table Mission
(
	id varchar( 32 ),
	date date,
	imageId varchar( 32 ),
	description TEXT
);
create table Image
(
	id varchar( 32 ),
	red numeric,
	green numeric,
	blue numeric,
	intensity numeric
);

create table Today
(
	date date,
	target varchar( 32 ),
	poet varchar( 64 )
);



