SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE question_image;
TRUNCATE TABLE quiz_question;
TRUNCATE TABLE quiz;
TRUNCATE TABLE quiz_category;
TRUNCATE TABLE user;
insert  into `user`(`id`,`email`,`firstName`,`isActive`,`lastName`,`password`,`school`,`userType`,`username`,`workCompany`) values (1,'test','test',1,'test','test','test','LECTURER','john.doe','test'),
(2,'test','test',1,'test','test','test','LECTURER','john.doe2','test');
insert  into `quiz_category`(`id`,`name`) values (1,'test'),(2,'test2');
insert  into `quiz`(`id`,`userOwnedId`,`name`,`categoryId`) values (1,1,'test',1),(2,1,'test2',1),(3,1,'test3',1),(4,1,'test4',1),(5,1,'test5',1);
insert  into `quiz_question`(`id`,`userOwnedId`,`choiceA`,`choiceB`,`choiceC`,`choiceD`,`correctAnswer`,`question`) values (1,1,'1','2','3','4','A','test');
SET FOREIGN_KEY_CHECKS = 1;