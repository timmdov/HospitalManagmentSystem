CREATE TABLE User (
                      UserID INT PRIMARY KEY,
                      Username VARCHAR(15),
                      Password VARCHAR(64),
                      UserType ENUM('Admin', 'Doctor', 'Nurse', 'Patient'),
                      Name VARCHAR(30)
);

CREATE TABLE Admin (
                       AdminID INT PRIMARY KEY,
                       UserID INT,
                       FOREIGN KEY (UserID) REFERENCES User(UserID)
);

CREATE TABLE Doctor (
                        DoctorID INT PRIMARY KEY,
                        UserID INT,
                        PhoneNumber VARCHAR(15),
                        Specialization VARCHAR(20),
                        Email VARCHAR(30),
                        FOREIGN KEY (UserID) REFERENCES User(UserID)
);
CREATE TABLE Nurse (
                       NurseID INT PRIMARY KEY,
                       UserID INT,
                       PhoneNumber VARCHAR(15),
                       FOREIGN KEY (UserID) REFERENCES User(UserID)
);
CREATE TABLE Patient (
                         PatientID INT PRIMARY KEY,
                         UserID INT,
                         Email VARCHAR(30),
                         DateOfBirth DATE,
                         Address VARCHAR(50),
                         PhoneNumber VARCHAR(15),
                         FOREIGN KEY (UserID) REFERENCES User(UserID)
);
CREATE TABLE Room(
                     RoomID INT PRIMARY KEY,
                     DepartmentType VARCHAR (20)
);
CREATE TABLE Schedule(
                         ScheduleID INT PRIMARY KEY,
                         DoctorID INT,
                         NurseID INT,
                         RoomID INT,
                         StartTime TIME,
                         EndTime TIME,
                         Date DATE,
                         FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID),
                         FOREIGN KEY (NurseID) REFERENCES Nurse(NurseID),
                         FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);
CREATE TABLE DoctorStatus(
                             DoctorStatusID INT PRIMARY KEY,
                             DoctorID INT,
                             StatusDescription VARCHAR(30),
                             FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID)
);
CREATE TABLE RoomStatus(
                           RoomStatusID INT PRIMARY KEY,
                           RoomID INT,
                           StatusDescription VARCHAR(30),
                           FOREIGN KEY (RoomID) REFERENCES Room(RoomID)
);
CREATE TABLE NurseStatus(
                            NurseStatusID INT PRIMARY KEY,
                            NurseID INT,
                            StatusDescription VARCHAR(30),
                            FOREIGN KEY (NurseID) REFERENCES Nurse(NurseID)
);

CREATE TABLE Appointment(
                            AppointmentID INT PRIMARY KEY,
                            RoomID INT,
                            DoctorID INT,
                            PatientID INT,
                            NurseID INT,
                            Date DATE,
                            AppointmentStatus CHAR(15),
                            FOREIGN KEY (DoctorID) REFERENCES Doctor(DoctorID),
                            FOREIGN KEY (RoomID) REFERENCES Room(RoomID),
                            FOREIGN KEY (PatientID) REFERENCES Patient(PatientID),
                            FOREIGN KEY (NurseID) REFERENCES Nurse(NurseID)
);