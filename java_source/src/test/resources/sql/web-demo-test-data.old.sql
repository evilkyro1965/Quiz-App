SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE multiple_choice_quiz_answer;
TRUNCATE TABLE quiz_session;
TRUNCATE TABLE question_image;
TRUNCATE TABLE quiz_question;
TRUNCATE TABLE quiz;
TRUNCATE TABLE quiz_category;
TRUNCATE TABLE user;
insert  into `quiz`(`id`,`userOwnedId`,`name`,`categoryId`) values (6,1,'Computer Basic 1',3);
insert  into `quiz_category`(`id`,`name`) values (1,'Math'),(2,'Physic'),(3,'Computer Science'),(4,'History'),(5,'Chemistry');
insert  into `quiz_question`(`id`,`userOwnedId`,`choiceA`,`choiceB`,`choiceC`,`choiceD`,`correctAnswer`,`no`,`question`,`quizId`) values (4,1,'<p>Binary Digit</p>','<p>Binary Data</p>','<p>Binary Deci</p>','<p>Binary Deci</p>','A',1,'<p>A Bit stands for</p>',6),(5,1,'<p>Hard Disk</p>','<p>USB Disk</p>','<p>Floppy Disk</p>','<p>All of the above</p>','D',2,'<p>Which of the following is a storage device?</p>',6),(6,1,'<p>Hybrid</p>','<p>Digital</p>','<p>Analog</p>','<p>None of the above</p>','C',3,'<p>Speedometer&nbsp; is an example of ____________ computers</p>',6),(7,1,'<p>MS-Word</p>','<p>MS-Excel</p>','<p>MS-Power Point</p>','<p>MS-Access</p>','C',4,'<p>Which of the following is a presentation program?</p>',6),(8,1,'<p>First</p>','<p>Third</p>','<p>Fourth</p>','<p>Fourth</p>','A',5,'<p>Vacuum tubes was used in ___________________generation.</p>',6);
insert  into `user`(`id`,`email`,`firstName`,`isActive`,`lastName`,`password`,`school`,`userType`,`username`,`workCompany`) values (1,'test','john',1,'doe','teacher','test','LECTURER','teacher','test'),(2,'test','john',1,'cage','student','test','STUDENT','student','test');
SET FOREIGN_KEY_CHECKS = 1;
