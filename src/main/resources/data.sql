-- tabla OWNER
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Carlos', 'López', '12345678A', '654321987');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'María', 'Gómez', '87654321B', '612345678');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Luis', 'Martínez', '23456789C', '691234567');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Ana', 'Pérez', '34567890D', '622334455');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Jorge', 'Fernández', '45678901E', '678123456');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Lucía', 'Hernández', '56789012F', '631234987');
-- tabla PETS
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES (default, 'Luna', '2020-03-15', 'Persa', 'Female',6, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES (default, 'Max', '2021-11-08', 'Labrador Retriever', 'Male',5, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES (default, 'Pelusa', '2019-05-22', 'Siames', 'Female',4, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES (default, 'Duque', '2022-02-14', 'Bulldog Francés', 'Male',3, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES (default, 'Nina', '2018-09-01', 'Golden Retriever', 'Female',2, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES (default, 'Toby', '2023-07-25', 'Beagle', 'Male',1, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES (default, 'Misifu', '2021-04-12', 'Scottish Fold', 'Female',1, false);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner, deleted) VALUES (default, 'Zeus', '2020-12-20', 'Pastor Alemán', 'Male',6, false);
-- table APPOINTMENT
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-03-15 09:30:00', 1, 'URGENT', 'bleeding', 'PASSED', 6);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-04-19 10:30:00', 3, 'ORDINARY', 'sore eyes', 'PASSED', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-03-13 01:00:00', 5, 'ORDINARY', 'check-up', 'PENDING', 2);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-05 07:00:00', 2, 'ORDINARY', 'stomach pain', 'PASSED', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-12-01 13:30:00', 6, 'ORDINARY', 'rash', 'CANCELED', 3);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-08-18 11:30:00', 14, 'ORDINARY', 'excessive thirst', 'PASSED', 5);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2021-11-09 18:00:00', 4, 'URGENT', 'vomiting', 'PASSED', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-01-19 14:00:00', 7, 'URGENT', 'vomiting', 'PASSED', 3);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-18 05:30:00', 15, 'URGENT', 'allergic reaction', 'PASSED', 1);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-10-28 19:30:00', 5, 'ORDINARY', 'check-up', 'PENDING', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-08-17 08:30:00', 2, 'URGENT', 'check-up', 'CANCELED', 8);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-14 20:00:00', 14, 'ORDINARY', 'cough', 'PASSED', 4);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-11-14 22:00:00', 5, 'ORDINARY', 'skin infection', 'PASSED', 2);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-04-29 06:30:00', 15, 'URGENT', 'check-up', 'PENDING', 7);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2024-02-18 20:30:00', 9, 'ORDINARY', 'injury', 'PASSED', 9);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-01-02 11:30:00', 15, 'ORDINARY', 'injury', 'PASSED', 9);

-- tabla BREEDS
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Persa', 'Cat');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Golden Retriever', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Beagle', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Siames', 'Cat');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Pastor Alemán', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Bulldog Francés', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Scottish Fold', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Labrador Retriever', 'Dog');