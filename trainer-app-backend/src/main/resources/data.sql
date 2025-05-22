INSERT INTO cancel_period (id, cancel_limit_hours) VALUES (1, 24);

INSERT INTO trainer (id, name, access_code, email, created_at)
VALUES (1, 'Marko Markovic', 'ACCESS123', 'marko@example.com', '2025-05-21T10:00:00');

INSERT INTO trainer (id, name, access_code, email, created_at)
VALUES (3, 'Marko Markovic', 'ACCESS1234', 'marko@example.com', '2025-05-21T10:00:00');

INSERT INTO users (id, name, phone, email, created_at)
VALUES (1, 'Jovan Jovanovic', '+381601234567', 'milutinovic.stefan2003@gmail.com', '2025-05-20T15:30:00');

INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (1, 'ONE_HOUR', 'ACTIVE', '2025-06-22T08:00:00', '2025-06-22T09:00:00', '2025-05-21T12:00:00', NULL, 1, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (2, 'ONE_HOUR', 'RESERVED', '2025-05-22T08:00:00', '2025-05-22T09:00:00', '2025-05-21T12:00:00', NULL, 1, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (3, 'ONE_HOUR', 'RESERVED', '2025-05-22T18:30:00', '2025-05-22T19:30:00', '2025-05-21T13:00:00', NULL, 1, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (4, 'ONE_HOUR', 'RESERVED', '2025-05-22T06:00:00', '2025-05-22T07:00:00', '2025-05-21T10:00:00', NULL, 1, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (5, 'ONE_HOUR', 'RESERVED', '2025-05-23T10:00:00', '2025-05-23T11:00:00', '2025-05-21T09:00:00', NULL, 1, 1);

INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (6, 'ONE_HOUR', 'RESERVED', '2025-05-20T15:00:00', '2025-05-20T16:00:00', '2025-05-19T12:00:00', NULL, 1, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (7, 'ONE_HOUR', 'RESERVED', '2025-05-22T22:00:00', '2025-05-22T23:00:00', '2025-05-21T16:00:00', NULL, 1, 1);

INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (8, 'ONE_HOUR', 'RESERVED', '2025-04-24T10:00:00', '2025-04-24T11:00:00', '2025-05-21T09:00:00', NULL, 1, 1);
