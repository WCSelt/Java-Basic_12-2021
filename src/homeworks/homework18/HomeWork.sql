CREATE DATABASE HomeWork;


// Таблица с вопросами


CREATE TABLE questions
(
	question_id 	SMALLSERIAL 	PRIMARY KEY,
	question 		VARCHAR(200) 	NOT NULL UNIQUE
);

INSERT INTO questions (question)

VALUES
	('Какой из типов чисел с плавающей точкой занимает 4 байта?'),
	('Какое количество значений может принимать тип int?'),
	('Какое объявление переменной является неправильным?'),
	('Сколько примитивных типов данных существует в в Java?'),
	('Какой из предложенных целочисленных типов данных занимает наименьший объём памяти?');

SELECT question_id, question
FROM questions
ORDER BY question_id;


// Таблица с вариантами ответов и правильными ответами


CREATE TABLE answers_options
(
	question_id 	SMALLINT  		REFERENCES questions (question_id),
	option_id 		SMALLINT 		NOT NULL,	
	answer_option	varchar(100)	NOT NULL,
	true_answer     boolean 
);

INSERT INTO answers_options (question_id,option_id,answer_option,true_answer)

VALUES
	(1,1,'double',false),
	(1,2,'int',false),
	(1,3,'float',true),
	(1,4,'short',false),
	(2,1,'2^16',false),
	(2,2,'2^32',true),
	(2,3,'от 0 до 65535',false),
	(2,4,'от -214748368 до 214748367',false),
	(3,1,'int i = 1;',false),
	(3,2,'Integer i = 1_000_000_000;',false),
	(3,3,'String s,a;',false),
	(3,4,'d = 4.35',true),
	(4,1,'8',true),
	(4,2,'11',false),
	(4,3,'5',false),
	(4,4,'3',false),
	(5,1,'bite',false),
	(5,2,'char',false),
	(5,3,'float',false),
	(5,4,'long',true);
	
SELECT question_id, option_id, answer_option, true_answer
FROM answers_options
ORDER BY question_id, option_id;








