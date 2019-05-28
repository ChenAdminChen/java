CREATE TABLE `student` (`id` int(11) NOT NULL AUTO_INCREMENT,`name` varchar(255) DEFAULT NULL,PRIMARY KEY (`id`));


CREATE TABLE `teacher` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
);

CREATE TABLE `teacher_info` (
  `t_id` int(11) NOT NULL,
  `info` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`t_id`)
);

CREATE TABLE `college` (
  `student_id` int(11) NOT NULL,
  `teacher_id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`student_id`,`teacher_id`)
);