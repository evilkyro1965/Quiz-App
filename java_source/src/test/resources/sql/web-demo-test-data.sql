TRUNCATE multiple_choice_quiz_answer,
choice_image,
question_image,
quiz_session,
quiz_question,
quiz,
quiz_category,
user_table
RESTART IDENTITY;

INSERT INTO user_table(
            id, email, firstname, isactive, lastname, password, school, usertype,
            username, workcompany)
    VALUES (1, 'test', 'test', true, 'test', 'teacher', 'test', 'LECTURER',
            'teacher', 'test'),
            (2, 'test', 'test', true, 'test', 'student', 'test', 'STUDENT',
            'student', 'test');

INSERT INTO quiz_category(
            id, name)
    VALUES (1, 'Math'),(2, 'Physic'),(3, 'Computer Science'),(4, 'Chemistry'),(5, 'History');

INSERT INTO quiz(
            id, userownedid, name, categoryid)
    VALUES (1, 1, 'Computer Basic 1', 3);

INSERT INTO quiz_question(
            id, userownedid, choicea, choiceb, choicec, choiced, correctanswer,
            no, question, quizid)
    VALUES (1,1,'<p>Binary Digit</p>','<p>Binary Data</p>','<p>Binary Deci</p>','<p>Binary Deci</p>','A',1,'<p>A Bit stands for</p>',1),
(2,1,'<p>Hard Disk</p>','<p>USB Disk</p>','<p>Floppy Disk</p>','<p>All of the above</p>','D',2,'<p>Which of the following is a storage device?</p>',1),
(3,1,'<p>Hybrid</p>','<p>Digital</p>','<p>Analog</p>','<p>None of the above</p>','C',3,'<p>Speedometer&nbsp; is an example of ____________ computers</p>',1),
(4,1,'<p>MS-Word</p>','<p>MS-Excel</p>','<p>MS-Power Point</p>','<p>MS-Access</p>','C',4,'<p>Which of the following is a presentation program?</p>',1),
(5,1,'<p>First</p>','<p>Third</p>','<p>Fourth</p>','<p>Fourth</p>','A',5,'<p>Vacuum tubes was used in ___________________generation.</p>',1);



