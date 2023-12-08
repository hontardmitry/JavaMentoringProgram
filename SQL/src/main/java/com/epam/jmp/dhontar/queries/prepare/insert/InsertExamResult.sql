-- Generate random data for student_id, subject_id, and mark
WITH RandomData AS (
  SELECT
    generate_series(1, 10000) AS id,
    floor(random() * 999 + 1)::integer AS student_id,
    CASE WHEN random() < 0.1 THEN NULL ELSE floor(random() * 49 + 1)::integer END AS subject_id,
    CASE
      WHEN random() < 0.1 THEN NULL
      WHEN random() < 0.05 THEN 0
      WHEN random() < 0.05 THEN 12
      ELSE floor(random() * 10 + 1)::smallint
    END AS mark
)

-- Insert into the exam_result table
INSERT INTO public.exam_result (id, student_id, subject_id, mark)
SELECT id, student_id, subject_id, mark
FROM RandomData;