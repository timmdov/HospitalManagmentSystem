INSERT INTO User (UserID, Username, Password, UserType, Name) VALUES
                (1, 'Admin1', '790f48e3ba51e2d0762e7d4a74d4076a62cfb34d44e3dfbc43798fe9ff399602', 'Admin', 'Farid'),
                (2, 'DoctorMiri', '6e161c0220635c04c101fecaf48653eecc71f19f1971acb28a911e56cf81e00d', 'Doctor', 'Mirbaxas'),
                (3, 'DoctorTeymur', 'f95bee37eb94d65e85538cb100b8fd12e6c16209e27614df0c37074a792d08a5', 'Doctor', 'Teymur'),
                (4, 'NurseNuray', '25ab7948571bbc02dac5489c1fe4d56e5bac68b6f37d73d439897eb749caaad8', 'Nurse', 'Nuray'),
                (5, 'NurseMaryam', '4f7031213e2c7eef96673e75e7f9babd5cef705c6d8c63b299c24e48a546eff0', 'Nurse', 'Maryam'),
                (6, 'PatientAlex', '697614530278f10d7d614302a16f60e6f50a294b25a8a074510832c6d56e4970', 'Patient', 'Alex'),
                (7, 'PatientAslan', 'de0d7414431af6c955882bb43ab20146901370feb8a3a823e804dabf4fe7e6f4', 'Patient', 'Aslan');

UPDATE User
SET Name = 'New Name'
WHERE UserID = 1;

DELETE FROM User
WHERE UserID = 2;


INSERT INTO Admin (AdminID, UserID)
VALUES (1, 1);

INSERT INTO Doctor (DoctorID, UserID, PhoneNumber, Specialization, Email)
VALUES (1, 2, '+905364759252', 'Cardiology', 'mirbaxas.dr@gmail.com'),
       (2, 3, '+905364759253', 'Orthopedics', 'teymur.dr@gmail.com');

UPDATE Doctor
SET Email = 'newemail@gmail.com'
WHERE DoctorID = 1;

DELETE FROM Doctor
WHERE DoctorID = 1;



INSERT INTO Nurse (NurseID, UserID, PhoneNumber)
VALUES (1, 4, '+905364759254'),
       (2, 5, '+905364759255');

UPDATE Nurse
SET PhoneNumber = '+905364759256'
WHERE NurseID = 1;

DELETE FROM Nurse
WHERE NurseID = 1;



INSERT INTO Patient (PatientID, UserID, Email, DateOfBirth, Address)
VALUES (1, 6, 'alex.pt@gmail.com', '2003-10-23', 'Ozyegin Universitesi'),
       (2, 7, 'aslan.pt@gmail.com', '1999-09-20', 'Ozyegin Universitesi');

UPDATE Patient
SET Address = 'Yurtlar'
WHERE PatientID = 1;

DELETE FROM Patient
WHERE PatientID = 1;



INSERT INTO Room (RoomID, DepartmentType)
VALUES (1, 'Cardiology'),
       (2, 'Orthopedics');

UPDATE Room
SET DepartmentType = 'X-Ray'
WHERE RoomID = 1;

DELETE FROM Room
WHERE RoomID = 1;



INSERT INTO Schedule (ScheduleID, DoctorID, NurseID, RoomID, StartTime, EndTime, Date)
VALUES (1, 1, 1, 1, '09:00', '11:00', '2024-01-20'),
       (2, 2, 2, 2, '14:00', '16:00', '2024-01-21');

UPDATE Schedule
SET StartTime = '10:00', EndTime = '12:00'
WHERE ScheduleID = 1;

DELETE FROM Schedule
WHERE ScheduleID = 1;



INSERT INTO DoctorStatus (DoctorStatusID, DoctorID, StatusDescription)
VALUES (1, 1, 'Available'),
       (2, 2, 'Unavailable');

UPDATE DoctorStatus
SET StatusDescription = 'Unavailable'
WHERE DoctorStatusID = 1;

DELETE FROM DoctorStatus
WHERE DoctorStatusID = 1;


INSERT INTO RoomStatus (RoomStatusID, RoomID, StatusDescription)
VALUES (1, 1, 'Occupied'),
       (2, 2, 'Available');

UPDATE RoomStatus
SET StatusDescription = 'Available'
WHERE RoomStatusID = 1;

DELETE FROM RoomStatus
WHERE RoomStatusID = 1;


INSERT INTO NurseStatus (NurseStatusID, NurseID, StatusDescription)
VALUES (1, 1, 'Available');

UPDATE NurseStatus
SET StatusDescription = 'Unavailable'
WHERE NurseStatusID = 1;

DELETE FROM NurseStatus
WHERE NurseStatusID = 1;



INSERT INTO Appointment (AppointmentID, RoomID, DoctorID, PatientID, NurseID, Date, AppointmentStatus)
VALUES (1, 1, 1, 1, 1, '2024-02-01', 'Confirmed'),
       (2, 2, 2, 2, 2, '2024-02-08', 'Scheduled');

UPDATE Appointment
SET AppointmentStatus = 'Cancelled'
WHERE AppointmentID = 1;

DELETE FROM Appointment
WHERE AppointmentID = 1;























