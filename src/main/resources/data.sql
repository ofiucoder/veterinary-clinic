-- table OWNER
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Carlos', 'López', '12345678A', '654321987');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'María', 'Gómez', '87654321B', '612345678');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Luis', 'Martínez', '23456789C', '691234567');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Ana', 'Pérez', '34567890D', '622334455');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Jorge', 'Fernández', '45678901E', '678123456');
INSERT INTO OWNER (id, first_name, last_name, dni, phone_number) VALUES (default, 'Lucía', 'Hernández', '56789012F', '631234987');
-- table PETS
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner) VALUES (default, 'Luna', '2020-03-15', 'Persa', 'Female',1);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner) VALUES (default, 'Max', '2021-11-08', 'Labrador Retriever', 'Male',6);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner) VALUES (default, 'Pelusa', '2019-05-22', 'Siames', 'Female',6);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner) VALUES (default, 'Duque', '2022-02-14', 'Bulldog Francés', 'Male',2);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner) VALUES (default, 'Nina', '2018-09-01', 'Golden Retriever', 'Female',4);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner) VALUES (default, 'Toby', '2023-07-25', 'Beagle', 'Male',3);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner) VALUES (default, 'Misifu', '2021-04-12', 'Scottish Fold', 'Female',5);
INSERT INTO pets (id, name, date_Of_Birth, bread_id, gender, owner) VALUES (default, 'Zeus', '2020-12-20', 'Pastor Alemán', 'Male',1);
-- table APPOINTMENTS
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-03-15 09:30:00', 1, 'URGENT', 'bleeding', 'PASSED', 1);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-04-19 10:30:00', 3, 'ORDINARY', 'sore eyes', 'PASSED', 6);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-03-13 01:00:00', 4, 'ORDINARY', 'check-up', 'PENDING', 2);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-05 07:00:00', 2, 'ORDINARY', 'stomach pain', 'PASSED', 6);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-12-01 13:30:00', 6, 'ORDINARY', 'rash', 'PASSED', 3);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2020-08-18 11:30:00', 7, 'ORDINARY', 'excessive thirst', 'PASSED', 5);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2021-11-09 18:00:00', 3, 'URGENT', 'vomiting', 'PASSED', 6);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-01-19 14:00:00', 6, 'URGENT', 'vomiting', 'PASSED', 3);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-18 05:30:00', 1, 'URGENT', 'allergic reaction', 'PASSED', 1);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-10-28 19:30:00', 3, 'ORDINARY', 'check-up', 'PENDING', 6);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-08-17 08:30:00', 2, 'ORDINARY', 'check-up', 'PENDING', 6);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-09-14 20:00:00', 5, 'ORDINARY', 'cough', 'PASSED', 4);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2022-11-14 22:00:00', 4, 'ORDINARY', 'skin infection', 'PASSED', 2);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2025-04-29 06:30:00', 8, 'ORDINARY', 'check-up', 'PENDING', 1);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2024-02-18 20:30:00', 2, 'ORDINARY', 'injury', 'PASSED', 6);
INSERT INTO appointments (id, date, pet_id, type, reason, status, owner_id) VALUES (default, '2023-01-02 11:30:00', 3, 'ORDINARY', 'injury', 'PASSED', 6);
-- table BREEDS
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Persa', 'Cat');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Golden Retriever', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Beagle', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Siames', 'Cat');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Pastor Alemán', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Bulldog Francés', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Scottish Fold', 'Dog');
INSERT INTO BREEDS (breed_id, breed_name, pet_type) VALUES (default, 'Labrador Retriever', 'Dog');