-- tabla OWNER
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Carlos', 'López', '12345678A', '654321987');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'María', 'Gómez', '87654321B', '612345678');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Luis', 'Martínez', '23456789C', '691234567');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Ana', 'Pérez', '34567890D', '622334455');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Jorge', 'Fernández', '45678901E', '678123456');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Lucía', 'Hernández', '56789012F', '631234987');
-- tabla PETS
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES   (default, 'Luna', '2020-03-15', 'Persa', 'Female',6, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES     (default, 'Max', '2021-11-08', 'Labrador Retriever', 'Male',5, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES     (default, 'Pelusa', '2019-05-22', 'Siames', 'Female',4, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES     (default, 'Duque', '2022-02-14', 'Bulldog Francés', 'Male',3, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES     (default, 'Nina', '2018-09-01', 'Golden Retriever', 'Female',2, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES     (default, 'Toby', '2023-07-25', 'Beagle', 'Male',1, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES     (default, 'Misifu', '2021-04-12', 'Scottish Fold', 'Female',1, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES     (default, 'Zeus', '2020-12-20', 'Pastor Alemán', 'Male',6, false);
-- table APPOINTMENT
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-03-15 09:30:00', 1, "urgent", "bleeding", 'passed', 6);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-04-19 10:30:00', 3, 'ordinary', 'sore eyes', 'passed', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-03-13 01:00:00', 5, 'ordinary', 'check-up', 'pending', 2);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-05 07:00:00', 2, 'ordinary', 'stomach pain', 'passed', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-12-01 13:30:00', 6, 'ordinary', 'rash', 'canceled', 3);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-08-18 11:30:00', 14, 'ordinary', 'excessive thirst', 'passed', 5);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2021-11-09 18:00:00', 4, 'urgent', 'vomiting', 'passed', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-01-19 14:00:00', 7, 'urgent', 'vomiting', 'passed', 3);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-18 05:30:00', 15, 'urgent', 'allergic reaction', 'passed', 1);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-10-28 19:30:00', 5, 'ordinary', 'check-up', 'pending', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-08-17 08:30:00', 2, 'urgent', 'check-up', 'canceled', 8);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-14 20:00:00', 14, 'ordinary', 'cough', 'passed', 4);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-11-14 22:00:00', 5, 'ordinary', 'skin infection', 'passed', 2);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-04-29 06:30:00', 15, 'urgent', 'check-up', 'pending', 7);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2024-02-18 20:30:00', 9, 'ordinary', 'injury', 'passed', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-01-02 11:30:00', 15, 'ordinary', 'injury', 'passed', 9);
