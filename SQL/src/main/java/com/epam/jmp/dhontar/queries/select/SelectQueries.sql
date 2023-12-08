--1. Select all primary skills that contain more than one word
--(please note that both ‘-‘ and ‘ ’ could be used as a separator)
SELECT primary_skill
FROM student
WHERE position(' ' in primary_skill) > 0 OR position('-' in primary_skill) > 0;
--OR
SELECT primary_skill
FROM student
WHERE primary_skill) ~ E'[\\s-]';

--2. Select all students who does not have second name (it is absent or consists from only one letter/letter with dot)
SELECT *
FROM student
WHERE LENGTH(surname) <= 1 OR surname ~ E'^[A-Za-z]\\.?$'

--3. Select number of students passed exams for each subject and order result by number of student descending.
SELECT subject.name, COUNT(DISTINCT exam_result.student_id) AS num_students
FROM subject
LEFT JOIN exam_result ON subject.id = exam_result.subject_id
GROUP BY subject.name
ORDER BY num_students DESC;

--4. Select the number of students with the same exam marks for each subject.
SELECT subject.name as subject, er.mark, COUNT(er.student_id) AS num_students
FROM subject
LEFT JOIN exam_result as er ON subject.id = er.subject_id
GROUP BY subject.name, er.mark;

--5. Select students who passed at least two exams for different subjects.
SELECT student.id, student.name, student.surname, COUNT(DISTINCT exam_result.subject_id) as num_subject
FROM student
JOIN exam_result ON student.id = exam_result.student_id
GROUP BY student.id
HAVING COUNT(DISTINCT exam_result.subject_id) >= 2;

--6. Select students who passed at least two exams for the same subject.
SELECT student.id, student.name, student.surname, MAX(er1.subject_id)
FROM student
JOIN exam_result AS er1 ON student.id = er1.student_id
JOIN exam_result AS er2 ON student.id = er2.student_id
WHERE er1.subject_id = er2.subject_id
  AND er1.id <> er2.id
GROUP BY student.id, student.name;

--7. Select all subjects for which exams were passed only by students with the same primary skills.
SELECT subject.id, subject.name
FROM subject
JOIN exam_result ON subject.id = exam_result.subject_id
JOIN student ON exam_result.student_id = student.id
WHERE EXISTS (
    SELECT 1
    FROM student s
    WHERE s.primary_skill = student.primary_skill
    AND s.id <> student.id
)

--8. Select all subjects which exams passed only students with the different primary skills.
--It means that all students passed the exam for the one subject must have different primary skill.
SELECT subject.id, subject.name
FROM subject
JOIN exam_result ON subject.id = exam_result.subject_id
JOIN student ON exam_result.student_id = student.id
WHERE NOT EXISTS (
    SELECT 1
    FROM student s
    WHERE s.primary_skill = student.primary_skill
    AND s.id <> student.id
)
GROUP BY subject.id, subject.name
HAVING COUNT(DISTINCT student.id) > 1;

--9.Select students who does not pass any exam
-- Outer Join:
SELECT student.id, student.name
FROM student
LEFT JOIN exam_result ON student.id = exam_result.student_id
WHERE exam_result.student_id IS NULL;
--Subquery with 'NOT IN' Clause:
SELECT id, name
FROM student
WHERE id NOT IN (SELECT DISTINCT student_id FROM exam_result);
-- Subquery with 'ANY' Clause:
SELECT id, name
FROM student
WHERE id <> ANY (SELECT DISTINCT student_id FROM exam_result);

--10 Select all students whose average mark is bigger than overall average mark
SELECT student.id, student.name, AVG(exam_result.mark)
FROM student
JOIN exam_result ON student.id = exam_result.student_id
GROUP BY student.id
HAVING AVG(exam_result.mark) > (SELECT AVG(mark) FROM exam_result);
--11 Select top 5 students who passed their last exam better than average students.
WITH LastExamMarks AS (
    SELECT student_id, mark
    FROM exam_result
    WHERE (exam_result.student_id, exam_result.id) IN (
        SELECT student_id, MAX(id) AS last_exam_id
        FROM exam_result
        GROUP BY student_id
    )
)

SELECT student.id, student.name, le.mark AS last_exam_mark
FROM student
JOIN LastExamMarks le ON student.id = le.student_id
WHERE le.mark > (SELECT AVG(mark) FROM LastExamMarks)
ORDER BY le.mark DESC
LIMIT 5;

--12 Select biggest mark for each student and add text description for the mark
WITH StudentMarks AS (
    SELECT
        student.id AS student_id,
		student.name || ', ' || student.surname as student_name,
        COALESCE(MAX(exam_result.mark), 0) AS max_mark
    FROM student
    LEFT JOIN exam_result ON student.id = exam_result.student_id
    GROUP BY student.id
)

SELECT
    sm.student_id,
	sm.student_name,
    CASE
        WHEN sm.max_mark = 0 THEN 'not passed'
        WHEN sm.max_mark IN (1, 2, 3) THEN 'BAD'
        WHEN sm.max_mark IN (4, 5, 6) THEN 'AVERAGE'
        WHEN sm.max_mark IN (7, 8) THEN 'GOOD'
        WHEN sm.max_mark IN (9, 10) THEN 'EXCELLENT'
        ELSE 'UNKNOWN'
    END AS mark_description
FROM StudentMarks sm;


--13 Select number of all marks for each mark type (‘BAD’, ‘AVERAGE’,…)
SELECT
    CASE
		WHEN mark IS NULL THEN 'not passed'
		WHEN mark IN (1, 2, 3) THEN 'BAD'
		WHEN mark IN (4, 5, 6) THEN 'AVERAGE'
		WHEN mark IN (7, 8) THEN 'GOOD'
		WHEN mark IN (9, 10) THEN 'EXCELLENT'
		ELSE 'UNKNOWN'
	END AS mark_type,
    COUNT(*) AS count
FROM exam_result
GROUP BY mark_type;