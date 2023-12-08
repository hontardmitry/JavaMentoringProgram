CREATE TABLE student
(
    id               SERIAL PRIMARY KEY,
    name             varchar(255),
    surname          varchar(255),
    dob              date,
    primary_skill    varchar(255),
    created_datetime timestamp,
    updated_datetime timestamp
);

CREATE TABLE phone
(
    id         SERIAL PRIMARY KEY,
    student_id int,
    number     varchar(50),
    CONSTRAINT phone_to_student_rel FOREIGN KEY (student_id)
        REFERENCES student(id) ON UPDATE NO ACTION
        ON DELETE NO ACTION
	 NOT VALID
);

CREATE TABLE subject
(
    id    SERIAL PRIMARY KEY,
    name  varchar(255),
    tutor varchar(255)
);

CREATE TABLE exam_result
(
    id         SERIAL PRIMARY KEY,
    student_id int,
    subject_id int,
    mark       smallint,
    CONSTRAINT result_to_student_rel FOREIGN KEY (student_id)
        REFERENCES student(id) ON UPDATE NO ACTION
        ON DELETE NO ACTION
	 NOT VALID,
    CONSTRAINT result_to_subject_rel FOREIGN KEY (subject_id)
        REFERENCES subject(id) ON UPDATE NO ACTION
        ON DELETE NO ACTION
	 NOT VALID
);