SET GLOBAL max_allowed_packet = 1024 * 1024 * 32;
SET SESSION max_allowed_packet = 1024 * 1024 * 32;

create database poetry default character set utf8 collate utf8_general_ci;

create table Poet
(
	username varchar( 32 ) primary key,
	pen_name varchar( 32 ),
	icon varchar( 40 ),
	password varchar( 64 ),
	email varchar( 128 ),
	authority varchar( 128 )
	
);

create table File
(
	id varchar( 40 ) primary key,
	name varchar( 256 ),
	owner varchar( 32 ),
	mime varchar( 64 ),
	contents mediumblob
);

create table Poetry
(
	id varchar( 40 ) primary key,
	title varchar( 256 ),
	titleFont varchar( 16 ),
	titleSize varchar( 4 ),
	titleColor varchar( 16 ),
	author varchar( 40 ),
	image varchar( 64 ),
	createdDate date,
	contents TEXT,
	contentsFont varchar( 16 ),
	contentsSize varchar( 4 ),
	contentsColor varchar( 16 )
);

create table Reply
(
	id varchar( 40 ) primary key,
	targetId varchar( 40 ),
	writer varchar( 40 ),
	contents varchar( 256 ),
	createdDate date
);

create table Following
(
	following varchar( 40 ),
	follower varchar( 40 ),
	constraint following_unique primary key ( following, follower )
);

create table Star
(
	poetryId varchar( 40 ),
	poetId varchar( 40 ),
	constraint star_unique primary key( poetryId, poetId )
);

create table Bookmark
(
	poetryId varchar( 40 ),
	poetId varchar( 40 ),
	constraint bookmark_unique primary key( poetryId, poetId )
);

create table Mission
(
	id varchar( 40 ) primary key,
	date date,
	imageId varchar( 40 ),
	description TEXT
);

create table Today
(
	date date primary key,
	target integer,
	type varchar( 64 ),
	poetry varchar( 64 )
	
);

create table MissionPoetry
(
	id varchar( 40 ) primary key,
	date date,
	poetryId varchar( 40 )
);

create table Block
(
	following varchar( 40 ),
	follower varchar( 40 ),
	constraint block_unique primary key ( following, follower )
);