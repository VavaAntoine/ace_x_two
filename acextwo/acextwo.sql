-- 1.
-- Database: suite_of_sports_db

-- CREATE DATABASE suite_of_sports_db
--     WITH
--     OWNER = postgres
--     ENCODING = 'UTF8'
--     LC_COLLATE = 'English_United States.1253'
--     LC_CTYPE = 'English_United States.1253'
--     LOCALE_PROVIDER = 'libc'
--     TABLESPACE = pg_default
--     CONNECTION LIMIT = -1
--     IS_TEMPLATE = False;

-- 2.
-- SCHEMA: fb_bb_match_mgmt

CREATE SCHEMA IF NOT EXISTS fb_bb_match_mgt
    AUTHORIZATION postgres;

-- 3.
-- https://www.postgresql.org/docs/current/sql-keywords-appendix.html
-- MATCH non-reserved

CREATE TABLE fb_bb_match_mgt.match (
    id SERIAL PRIMARY KEY,
    description VARCHAR(255) NOT NULL,
    match_date DATE NOT NULL,
    match_time TIME NOT NULL,
    team_a VARCHAR(100) NOT NULL,
    team_b VARCHAR(100) NOT NULL,
    sport INTEGER NOT NULL,
	UNIQUE (sport, match_date, team_a),
    UNIQUE (sport, match_date, team_b)
);

-- 4.

CREATE TABLE fb_bb_match_mgt.match_odd (
    id SERIAL PRIMARY KEY,
    match_id INT NOT NULL,
    specifier CHAR(1) NOT NULL,
    odd NUMERIC(5, 2) NOT NULL,
	UNIQUE (specifier, match_id),
	FOREIGN KEY (match_id) REFERENCES fb_bb_match_mgt.match (id) ON DELETE CASCADE
);

-- 5.

-- ALTER SEQUENCE fb_bb_match_mgt.match_id_seq INCREMENT BY 50;

-- ALTER SEQUENCE fb_bb_match_mgt.match_odd_id_seq INCREMENT BY 50;

-- 6.

INSERT INTO fb_bb_match_mgt.match (description, match_date, match_time, team_a, team_b, sport)
VALUES
( 'AEK VS PAO', '2025-04-01', '22:15:00', 'AEK', 'PAO', 2);

INSERT INTO fb_bb_match_mgt.match (description, match_date, match_time, team_a, team_b, sport)
VALUES
( 'ARHS VS PAOK', '2025-04-01', '22:15:00', 'ARHS', 'PAOK', 2);

-- INSERT INTO fb_bb_match_mgt.match (description, match_date, match_time, team_a, team_b, sport)
-- VALUES
-- ( 'PAO VS PAO', '2025-04-01', '22:15:00', 'PAO', 'PAO', 2);

-- 7.

INSERT INTO fb_bb_match_mgt.match_odd (match_id, specifier, odd)
VALUES
	((SELECT m.id FROM fb_bb_match_mgt.match AS m WHERE m.sport = 2 AND m.team_a = 'AEK' AND m.match_date = '2025-04-01'), 'X', 3.15 );

INSERT INTO fb_bb_match_mgt.match_odd (match_id, specifier, odd)
VALUES
	((SELECT m.id FROM fb_bb_match_mgt.match AS m WHERE m.sport = 2 AND m.team_a = 'AEK' AND m.match_date = '2025-04-01'), '1', 1.85 );

INSERT INTO fb_bb_match_mgt.match_odd (match_id, specifier, odd)
VALUES
	((SELECT m.id FROM fb_bb_match_mgt.match AS m WHERE m.sport = 2 AND m.team_a = 'AEK' AND m.match_date = '2025-04-01'), '2', 1.15 );


INSERT INTO fb_bb_match_mgt.match_odd (match_id, specifier, odd)
VALUES
	((SELECT m.id FROM fb_bb_match_mgt.match AS m WHERE m.sport = 2 AND m.team_a = 'ARHS' AND m.match_date = '2025-04-01'), 'X', 4.15 );

INSERT INTO fb_bb_match_mgt.match_odd (match_id, specifier, odd)
VALUES
	((SELECT m.id FROM fb_bb_match_mgt.match AS m WHERE m.sport = 2 AND m.team_a = 'ARHS' AND m.match_date = '2025-04-01'), '1', 1.15 );

INSERT INTO fb_bb_match_mgt.match_odd (match_id, specifier, odd)
VALUES
	((SELECT m.id FROM fb_bb_match_mgt.match AS m WHERE m.sport = 2 AND m.team_a = 'ARHS' AND m.match_date = '2025-04-01'), '2', 3.15 );

--

SELECT * FROM fb_bb_match_mgt.match;

SELECT * FROM fb_bb_match_mgt.match_odd;

-- SELECT m.*
-- FROM fb_bb_match_mgt.match as m
-- WHERE m.sport = 1
--   AND m.match_date BETWEEN '2025-03-25' AND '2025-04-15'
--   AND (m.team_a = 'AEK' OR m.team_b = 'AEK' OR m.team_a = 'ARHS' OR m.team_b = 'ARHS');


-- SELECT m.id
-- 	FROM fb_bb_match_mgt.match AS m 
-- 	WHERE m.sport = 2 AND m.team_a = 'AEK' AND m.match_date = '2025-04-01';







