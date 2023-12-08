INSERT INTO student (name, surname, dob, primary_skill, created_datetime, updated_datetime)
VALUES
  ('Mentor', '', '1932-03-31', '7a436c7eb41ff79e555a62d6ad44e11f', '2023-03-30 21:16:50.478142', '2022-03-10 18:20:32.958142'),
  ('Traver', '', '1977-03-29', null, '2022-03-13 10:11:34.196542', '2022-02-15 00:42:22.695742'),
  ('Simba', 'Mufasovich', '1962-11-15', null, '2023-09-01 19:52:04.974142', '2023-05-26 04:20:12.769342'),
  ('Iron Man', 'Downey', '1976-03-24', 'Test Automation', '2022-09-25 14:09:10.369342', '2023-01-23 18:37:11.655742'),
  ('Leonardo', 'Donatello Lopez Castilio', '1963-07-18', '5b50f3b24f492091769930ed2950c270', '2022-09-07 17:48:21.313342', '2023-10-30 02:50:12.596542'),
  ('John', 'Doe', '1980-01-01', 'Java', '2023-12-06 19:13:14.074942', '2023-12-06 19:13:14.074942'),
  ('Jane', 'Doe.', '1990-01-01', 'Python', '2023-12-06 19:13:14.074942', '2023-12-06 19:13:14.074942'),
  ('Alice', 'A', '2000-01-01', 'C++', '2023-12-06 19:13:14.074942', '2023-12-06 19:13:14.074942'),
  ('Bob', 'B.', '1970-01-01', 'JavaScript', '2023-12-06 19:13:14.074942', '2023-12-06 19:13:14.074942'),
  ('Charlie', 'Brown', '1960-01-01', 'HTML-CSS', '2023-12-06 19:13:14.074942', '2023-12-06 19:13:14.074942');


--OR


INSERT INTO small.student (name, surname, dob, primary_skill, created_datetime, updated_datetime)
SELECT * FROM (
  SELECT
    CASE WHEN i < 3 THEN NULL ELSE md5(random()::text) END, -- name
    CASE WHEN i < 3 THEN NULL ELSE md5(random()::text) END, -- surname
    '1920-01-01'::date + (random() * ('2010-12-31'::date - '1920-01-01'::date))::int, -- dob
    md5(random()::text), -- primary_skill
    NOW() - '2 years'::interval * random(), -- created_datetime
    NOW() - '2 years'::interval * random() -- updated_datetime
  FROM generate_series(1, 5) AS s(i)
  UNION ALL
  SELECT
    'John', 'Doe', '1980-01-01', 'Java', NOW(), NOW()
  UNION ALL
  SELECT
    'Jane', 'Doe.', '1990-01-01', 'Python', NOW(), NOW()
  UNION ALL
  SELECT
    'Alice', 'A.', '2000-01-01', 'C++', NOW(), NOW()
  UNION ALL
  SELECT
    'Bob', 'B.', '1970-01-01', 'JavaScript', NOW(), NOW()
  UNION ALL
  SELECT
    'Charlie', 'Brown', '1960-01-01', 'HTML-CSS', NOW(), NOW()
) AS t;