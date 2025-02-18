drop database itsupport;
create database itsupport;
use itsupport;


CREATE TABLE role (
	id bigint(20) NOT NULL auto_increment primary key,
	name varchar(255) NOT NULL
);


CREATE TABLE user (
	id bigint(20) NOT NULL auto_increment primary key,
	email varchar(255) NOT NULL unique,
	employeeNumber int(10) NOT NULL unique,
	name varchar(255) NOT NULL,
	userName varchar(255) NOT NULL unique,
	password varchar(255) NOT NULL,
	state bit NOT NULL
);

CREATE TABLE user_role (
	id bigint(20) NOT NULL auto_increment primary key,
	user_id bigint(20) NOT NULL references user(id),
	role_id bigint(20) NOT NULL references role(id)
);

-- one to many
CREATE TABLE issue (
	id bigint NOT NULL AUTO_INCREMENT primary key,
	issueTittle varchar(45),
	requestor varchar(60) NOT NULL,
	requestDate datetime NOT NULL,
	description text NOT NULL,
	status varchar(45) NOT NULL,
	assignedTo varchar(45) NOT NULL,
	resolvedBy varchar(45) ,
	resolution text ,
	resolvedDate datetime ,
	application varchar(45)NOT NULL,
	environment varchar(45)NOT NULL,
	client varchar(45)NOT NULL
);
-- one to many  
CREATE TABLE category (
    	id bigint NOT NULL AUTO_INCREMENT primary key,
   	categoryName varchar(60) NOT NULL,
	description varchar(45) NOT NULL
);
CREATE TABLE issue_category(
	id bigint(20) NOT NULL auto_increment primary key,
	issue_id  bigint NOT NULL ,foreign key(issue_id) references issue(id)  ,
	category_id bigint NOT NULL ,foreign key (category_id) references category(id)
	);
	
CREATE TABLE `issue_audit_history` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`issue_id` BIGINT(19) NOT NULL,
	`status` VARCHAR(45) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`name` VARCHAR(500) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`updatedTime` DATETIME NOT NULL,
	`updatedBy` VARCHAR(50) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`resolution` VARCHAR(500) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `FKmbx99nwvd6yyst6xh51cw5ciq` (`issue_id`) USING BTREE,
	CONSTRAINT `FKmbx99nwvd6yyst6xh51cw5ciq` FOREIGN KEY (`issue_id`) REFERENCES `itsupport`.`issue` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
	CONSTRAINT `issue_audit_history_ibfk_1` FOREIGN KEY (`issue_id`) REFERENCES `itsupport`.`issue` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=40
;

CREATE TABLE issue_history (
   	id bigint(20) NOT NULL auto_increment primary key,
	issue_id bigint not null ,foreign key (issue_id) REFERENCES issue(id),
	user_id bigint not null ,foreign key (user_id) REFERENCES user(id) ,
   	commentDate date ,
	comments text not null
	
);

CREATE TABLE emailAddresses(
	id bigint(20) NOT NULL auto_increment primary key,
	title VARCHAR(245) NOT NULL unique,
	subject VARCHAR(245),
	content TEXT,
	emails VARCHAR(245),
	pathLocation  VARCHAR(245)
);

CREATE TABLE dailytask(
	id bigint(20) NOT NULL auto_increment primary key,
	taskDate datetime NOT NULL,
	taskHours varchar(10) NOT NULL,
	task varchar(500) NOT NULL,
	user_id bigint not null ,foreign key (user_id) REFERENCES user(id)

);

insert into role (name) values ('ROLE_USER');
insert into role (name) values ('ROLE_ADMIN');



insert into user (name,userName,password,state,employeeNumber,email) values('admin','admin','$2a$10$2229czp7VOE2mtRwRi7W8O.PgsyWN8N3Lw90nn5kYBSRpOQxZBuky',1,2222,'admin@gmail.com');
insert into user_role(user_id,role_id) values(1,2);

--This query for production Start 

INSERT INTO emailaddresses (id,title,subject,content,emails,pathLocation) VALUES (1,'Daily Task List',' [BCS] - Daily Task List - ','<h4>Hi All, </h4><br> Please find attached the BAU Daily Tasklist for today.<br><br>Thanks and Regards,<br><br>BIZZ Customer Support.<br><br>IT Support<br><br>This is an auto-generated email form the Bizz Customer Support Application, sent from an un-monitored mailbox. Please do not reply to this email. Thank you.<br>','it.support@tnets.com.sg;',"E:\\BizzCustomerSupport\\daily_report\\");

INSERT INTO emailaddresses (id,title,subject,content,emails,pathLocation) VALUES (2,'BAU Task List',' [BCS] - Bau Task List - ','<h4>Hi All, </h4><br> Please find attached the BAU Daily Tasklist for today.<br><br>Thanks and Regards,<br><br>BIZZ Customer Support.<br><br>IT Support<br><br>This is an auto-generated email form the Bizz Customer Support Application, sent from an un-monitored mailbox. Please do not reply to this email. Thank you.<br>','it.support@tnets.com.sg',"E:\\BizzCustomerSupport\\daily_report\\");

INSERT INTO emailaddresses (id,title,subject,content,emails,pathLocation) VALUES (3,'BCS startUpIn',' Bizz Customer Support ','<h3>Hi All, </h3><br> Bizz Customer Support Successfully StartUpIn.<br><br>Thanks and Regards,<br><br>BIZZ Customer Support.<br><br>SIT<br> ','it.support@tnets.com.sg',"E:\\BizzCustomerSupport\\daily_report\\");

INSERT INTO emailaddresses (id,title,subject,content,emails,pathLocation) VALUES (4,'Exception',' [BCS] - Error: ','<br><br>Thanks and Regards,<br><br>BIZZ Customer Support.<br><br>IT Support<br><br>This is an auto-generated email form the Bizz Customer Support Application, sent from an un-monitored mailbox. Please do not reply to this email. <br><br>Thank you.<br>','it.support@tnets.com.sg',"E:\\BizzCustomerSupport\\daily_report\\");

--End


--This query for development Start
INSERT INTO `emailaddresses` (`id`, `title`, `subject`, `content`, `emails`, `pathLocation`) VALUES (1, 'Daily Task List', ' [BCS] - Daily Task List - ', '<h3>Hi All, </h3><br> Please find attached the  Daily Task List for today.<br><br>', 'mohamed.fahad@tnets.com.sg', 'E:\\BizzCustomerSupport\\daily_report\\');

INSERT INTO emailaddresses (id,title,subject,content,emails,pathLocation) VALUES (1,'Daily Task List',' [BCS] - Daily Task List - ','<h4>Hi All, </h4><br> Please find attached the BAU Daily Tasklist for today.<br><br>Thanks and Regards,<br><br>BIZZ Customer Support.<br><br>IT Support<br><br>This is an auto-generated email form the Bizz Customer Support Application, sent from an un-monitored mailbox. Please do not reply to this email. Thank you.<br>','it.support@tnets.com.sg',"E:\\BizzCustomerSupport\\daily_report\\");

INSERT INTO emailaddresses (id,title,subject,content,emails,pathLocation) VALUES (2,'BAU Task List',' [BCS] - Bau Task List - ','<h4>Hi All, </h4><br> Please find attached the BAU Daily Tasklist for today.<br><br>Thanks and Regards,<br><br>BIZZ Customer Support.<br><br>IT Support<br><br>This is an auto-generated email form the Bizz Customer Support Application, sent from an un-monitored mailbox. Please do not reply to this email. Thank you.<br>','it.support@tnets.com.sg',"E:\\BizzCustomerSupport\\daily_report\\");

INSERT INTO emailaddresses (id,title,subject,content,emails,pathLocation) VALUES (3,'BCS startUpIn',' Bizz Customer Support ','<h3>Hi All, </h3><br> Bizz Customer Support Successfully StartUpIn.<br><br>Thanks and Regards,<br><br>BIZZ Customer Support.<br><br>Dev<br> ','it.support@tnets.com.sg',"E:\\BizzCustomerSupport\\daily_report\\");

INSERT INTO emailaddresses (id,title,subject,content,emails,pathLocation) VALUES (4,'Exception',' [BCS] - Error: ','<br><br>Thanks and Regards,<br><br>BIZZ Customer Support.<br><br>IT Support<br><br>This is an auto-generated email form the Bizz Customer Support Application, sent from an un-monitored mailbox. Please do not reply to this email. <br><br>Thank you.<br>','it.support@tnets.com.sg',"E:\\BizzCustomerSupport\\daily_report\\");

--End


-alter table issue  add createDate DATETIME

-CREATE table temp AS (SELECT * FROM issue)



-update issue i,temp t  set i.createDate = t.requestDate where i.id=t.id
GRANT ALL ON itsupport.* TO 'itsupport'@'localhost' IDENTIFIED BY 'itsupport';


--Server request
CREATE TABLE serverRequestLog(
	id bigint(20) NOT NULL auto_increment primary key,
	checkIn datetime NOT NULL ,
	checkOut datetime NOT NULL,
	serverName varchar(150) NOT NULL,
	reason text NOT NULL,
    userName varchar(45) not null,
	requestMode VARCHAR(30) NOT NULL,
	comment text);
	
--Deployment Logger
CREATE TABLE deploymentPlanner(
    id bigint(20) NOT NULL auto_increment primary key,
	auther varchar(45) NOT NULL,
	performer varchar(45) NOT NULL,
	serverName varchar(45) NOT NULL,
	application varchar(45) NOT NULL,
	deployment varchar(150) NOT NULL,
	fileType varchar(150) NOT NULL,
	enhancedFor varchar(150) NOT NULL,
	deploymentDate datetime NOT NULL ,
	comment text);



ALTER TABLE issue MODIFY issueTittle VARCHAR(250);

ALTER TABLE dailytask MODIFY taskHours VARCHAR(25);
ALTER TABLE dailytask MODIFY task VARCHAR(1000);

ALTER TABLE `user`	ADD COLUMN `designation` VARCHAR(100) NOT NULL AFTER `state`;


CREATE TABLE `kt_details` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`createDate` DATETIME NULL DEFAULT NULL,
	`createdBy` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`issueid` BIGINT(19) NOT NULL,
	`ktDate` DATETIME NULL DEFAULT NULL,
	`ktTime` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`ktTimeTaken` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`ktTo` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`ktTopic` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`resolution` VARCHAR(500) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`status` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=MyISAM
AUTO_INCREMENT=19
;

ALTER TABLE dailytask
ADD taskname varchar(100);


CREATE TABLE taskcreation (
	id BIGINT(19) NOT NULL AUTO_INCREMENT,
	description VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	taskName VARCHAR(100) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	taskstatus VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	tasktype VARCHAR(50) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (id) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=MyISAM
AUTO_INCREMENT=6
;


CREATE TABLE tasktype(
	id bigint(20) NOT NULL auto_increment primary key,
	title VARCHAR(245) NOT NULL unique
);

--This query for development Start
INSERT INTO tasktype (id,title) VALUES (1,'Bug');
INSERT INTO tasktype (id,title) VALUES (2,'Change Request');
INSERT INTO tasktype (id,title) VALUES (3,'Enhancement');



CREATE TABLE `leavedetails` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`approveDate` DATETIME NULL DEFAULT NULL,
	`approvedBy` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`leaveCount` INT(10) NOT NULL,
	`leaveFrom` DATETIME NULL DEFAULT NULL,
	`leaveReason` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`leaveTo` DATETIME NULL DEFAULT NULL,
	`leaveType` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`leavestatus` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	`requestDate` DATETIME NULL DEFAULT NULL,
	`user_id` BIGINT(19) NULL DEFAULT NULL,
	PRIMARY KEY (`id`) USING BTREE,
	INDEX `FKgdtv8pl1n56dyu1i3wx7vdpgq` (`user_id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=MyISAM
;

ALTER TABLE issue ADD column issuetype VARCHAR(50) NOT NULL,ADD column timetaken VARCHAR(50),ADD column severity VARCHAR(50) NOT NULL,ADD column supporttype VARCHAR(10);


CREATE TABLE `issuetype` (
	`id` BIGINT(19) NOT NULL AUTO_INCREMENT,
	`title` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
	PRIMARY KEY (`id`) USING BTREE
)
COLLATE='utf8mb4_0900_ai_ci'
ENGINE=MyISAM
;


INSERT INTO issuetype (id,title) VALUES (1,'Production');
INSERT INTO issuetype (id,title) VALUES (2,'Database');
INSERT INTO issuetype (id,title) VALUES (3,'Development');
INSERT INTO issuetype (id,title) VALUES (4,'Others');

CREATE TABLE BugTracker (
	id bigint NOT NULL AUTO_INCREMENT primary key,
	author int not null REFERENCES user(id) ,
	createdDate datetime NOT NULL,
	applicationName varchar(45)NOT NULL,
	serverName varchar(45)NOT NULL,
	bugName varchar(250) NOT NULL,
	description text NOT NULL,
	bugNo varchar(15)NOT NULL UNIQUE,
	bugStatus varchar(10) NOT NULL,
	priority varchar(10) NOT NULL,
	assignee int not null REFERENCES user(id) ,
	estimatedFixDate datetime NOT NULL
);


