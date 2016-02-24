SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE quiz_category;
TRUNCATE TABLE user;
insert  into `user`(`id`,`email`,`firstName`,`isActive`,`lastName`,`password`,`school`,`userType`,`username`,`workCompany`) values (1,'test','test',1,'test','test','test','LECTURER','john.doe','test'),
(2,'test','test',1,'test','test','test','LECTURER','john.doe2','test');
insert  into `quiz_category`(`id`,`name`) values (1,'test'),(2,'test2');
SET FOREIGN_KEY_CHECKS = 1;