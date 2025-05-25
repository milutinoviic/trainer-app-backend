INSERT INTO cancel_period (id, cancel_limit_hours) VALUES (1, 24);

INSERT INTO trainer (id, name, access_code, email, created_at)
VALUES (1, 'Risto Milutinovic', 'ACCESS123', 'milutinovicristo1@gmail.com', '2025-05-21T10:00:00');

INSERT INTO trainer (id, name, access_code, email, created_at)
VALUES (3, 'Dragana Madzun', 'ACCESS1234', 'draganamadzun7@gmail.com', '2025-05-21T10:00:00');

INSERT INTO users (id, name, phone, email, created_at)
VALUES (1, 'Stefan Milutiovic', '+381601234567', 'milutinovic.stefan2003@gmail.com', '2025-05-20T15:30:00');

INSERT INTO users (id, name, phone, email, created_at)
VALUES (2, 'Stefan Ter', '+381601234567', 'stefanter11@gmail.com', '2025-05-20T15:30:00');
INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id) VALUES
                                                                                                                             (1, 'ONE_HOUR', 'ACTIVE', '2024-05-01T10:00:00', '2024-05-01T11:00:00', '2024-04-20T08:00:00', NULL, NULL, 1),
                                                                                                                             (2, 'HALF_HOUR', 'RESERVED', '2023-12-15T09:30:00', '2023-12-15T10:00:00', '2023-12-01T07:00:00', NULL, 2, 1),
                                                                                                                             (3, 'ONE_HOUR', 'RESERVED', '2025-06-10T14:00:00', '2025-06-10T15:00:00', '2025-05-23T12:00:00', NULL, 1, 3),
                                                                                                                             (4, 'HALF_HOUR', 'ACTIVE', '2025-05-24T08:00:00', '2025-05-24T08:30:00', '2025-05-23T14:00:00', NULL, NULL, 3),
                                                                                                                             (5, 'ONE_HOUR', 'RESERVED', '2022-11-01T17:00:00', '2022-11-01T18:00:00', '2022-10-20T15:00:00', NULL, 2, 3),
                                                                                                                             (6, 'HALF_HOUR', 'RESERVED', '2025-05-25T14:00:00', '2025-05-25T14:30:00', '2025-05-23T11:00:00', NULL, 1, 1),
                                                                                                                             (7, 'ONE_HOUR', 'ACTIVE', '2024-12-01T09:00:00', '2024-12-01T10:00:00', '2024-11-20T08:00:00', NULL, NULL, 3),
                                                                                                                             (8, 'HALF_HOUR', 'RESERVED', '2024-04-10T07:00:00', '2024-04-10T07:30:00', '2024-03-25T06:00:00', NULL, 1, 1),
                                                                                                                             (9, 'ONE_HOUR', 'ACTIVE', '2025-07-05T15:00:00', '2025-07-05T16:00:00', '2025-05-24T16:00:00', NULL, NULL, 3),
                                                                                                                             (10, 'HALF_HOUR', 'RESERVED', '2024-08-01T12:00:00', '2024-08-01T12:30:00', '2024-07-25T09:00:00', NULL, 2, 3),
                                                                                                                             (11, 'ONE_HOUR', 'RESERVED', '2023-05-10T18:00:00', '2023-05-10T19:00:00', '2023-04-28T10:00:00', NULL, 1, 1),
                                                                                                                             (12, 'HALF_HOUR', 'ACTIVE', '2025-01-15T06:30:00', '2025-01-15T07:00:00', '2025-01-01T10:00:00', NULL, NULL, 3),
                                                                                                                             (13, 'ONE_HOUR', 'RESERVED', '2025-05-24T17:00:00', '2025-05-24T18:00:00', '2025-05-20T13:00:00', NULL, 1, 1),
                                                                                                                             (14, 'HALF_HOUR', 'ACTIVE', '2024-10-10T16:00:00', '2024-10-10T16:30:00', '2024-09-30T09:00:00', NULL, NULL, 3),
                                                                                                                             (15, 'ONE_HOUR', 'RESERVED', '2024-02-20T11:00:00', '2024-02-20T12:00:00', '2024-02-01T08:00:00', NULL, 1, 3),
                                                                                                                             (16, 'HALF_HOUR', 'RESERVED', '2025-09-01T19:00:00', '2025-09-01T19:30:00', '2025-08-20T10:00:00', NULL, 2, 1),
                                                                                                                             (17, 'ONE_HOUR', 'ACTIVE', '2023-07-07T10:00:00', '2023-07-07T11:00:00', '2023-06-15T08:00:00', NULL, NULL, 3),
                                                                                                                             (18, 'HALF_HOUR', 'RESERVED', '2022-08-12T08:00:00', '2022-08-12T08:30:00', '2022-07-28T09:00:00', NULL, NULL, 3),
                                                                                                                             (19, 'ONE_HOUR', 'RESERVED', '2025-12-24T14:00:00', '2025-12-24T15:00:00', '2025-11-30T09:00:00', NULL, 1, 1),
                                                                                                                             (20, 'HALF_HOUR', 'ACTIVE', '2025-03-03T10:30:00', '2025-03-03T11:00:00', '2025-02-20T08:00:00', NULL, NULL, 3);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (21, 'ONE_HOUR', 'ACTIVE', '2025-05-25T16:00:00', '2025-05-25T17:00:00', '2025-05-24T10:00:00', NULL, NULL, 3);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (22, 'HALF_HOUR', 'ACTIVE', '2025-05-26T10:30:00', '2025-05-26T11:00:00', '2025-05-24T11:00:00', NULL, NULL, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (23, 'HALF_HOUR', 'ACTIVE', '2025-05-26T10:30:00', '2025-05-26T11:00:00', '2025-05-24T11:00:00', NULL, NULL, 3);



INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (24, 'ONE_HOUR', 'ACTIVE', '2025-05-27T09:00:00', '2025-05-27T10:00:00', '2025-05-24T12:00:00', NULL, NULL, 3);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (25, 'HALF_HOUR', 'ACTIVE', '2025-05-28T18:00:00', '2025-05-28T18:30:00', '2025-05-24T13:00:00', NULL, NULL, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (26, 'HALF_HOUR', 'ACTIVE', '2025-05-28T18:00:00', '2025-05-28T18:30:00', '2025-05-24T13:00:00', NULL, NULL, 3);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (27, 'HALF_HOUR', 'ACTIVE', '2025-05-26T12:00:00', '2025-05-26T12:30:00', '2025-05-24T13:00:00', NULL, NULL, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (28, 'HALF_HOUR', 'ACTIVE', '2025-05-27T14:00:00', '2025-05-27T14:30:00', '2025-05-24T13:00:00', NULL, NULL, 1);


INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (29, 'HALF_HOUR', 'ACTIVE', '2025-05-29T08:30:00', '2025-05-29T09:00:00', '2025-05-24T13:00:00', NULL, NULL, 1);

INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (30, 'HALF_HOUR', 'ACTIVE', '2025-05-30T17:00:00', '2025-05-30T17:30:00', '2025-05-24T13:00:00', NULL, NULL, 1);

INSERT INTO training (id, duration_training, status, start_time, end_time, created_at, canceled_at, user_id, trainer_id)
VALUES (31, 'HALF_HOUR', 'ACTIVE', '2025-06-01T19:30:00', '2025-06-01T20:00:00', '2025-05-24T13:00:00', NULL, NULL, 1);

SELECT setval('training_id_seq', (SELECT MAX(id) FROM training));
