-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: May 05, 2020 at 08:07 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.3

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `health_care`
--

-- --------------------------------------------------------

--
-- Table structure for table `appoinment`
--

CREATE TABLE `appoinment` (
  `appoinment_id` int(11) NOT NULL,
  `date` varchar(10) COLLATE utf8_bin NOT NULL,
  `time` varchar(10) COLLATE utf8_bin NOT NULL,
  `patient_patient_id` int(11) NOT NULL,
  `doctor_doc_id` int(11) NOT NULL,
  `hospital_hosp_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `appoinment`
--

INSERT INTO `appoinment` (`appoinment_id`, `date`, `time`, `patient_patient_id`, `doctor_doc_id`, `hospital_hosp_id`) VALUES
(1, '2020-05-30', '10:00 AM', 1, 2, 1),
(5, '2020-05-23', '4.00pm', 4, 3, 4),
(8, '2020-05-22', '1:30 PM', 1, 2, 1),
(13, '2020-05-07', '12:30 PM', 1, 2, 3),
(14, '2020-05-22', '1:00 PM', 2, 3, 1),
(15, '2020-05-30', '11:30 AM', 1, 4, 4),
(19, '2020-05-29', '12:00 PM', 2, 2, 2),
(20, '0011-09-10', '12:00 PM', 2, 2, 3),
(21, '2020-05-22', '11:30 AM', 2, 2, 2),
(22, '2020-05-24', '1:00 PM', 2, 1, 2);

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `doc_id` int(11) NOT NULL,
  `doc_nic` varchar(45) COLLATE utf8_bin NOT NULL,
  `doc_fname` varchar(45) COLLATE utf8_bin NOT NULL,
  `doc_lname` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `doc_email` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `doc_gender` varchar(45) COLLATE utf8_bin NOT NULL,
  `liscen_no_` varchar(45) COLLATE utf8_bin NOT NULL,
  `specialization` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `phone` varchar(45) COLLATE utf8_bin NOT NULL,
  `doc_charge` float NOT NULL,
  `user_user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`doc_id`, `doc_nic`, `doc_fname`, `doc_lname`, `doc_email`, `doc_gender`, `liscen_no_`, `specialization`, `phone`, `doc_charge`, `user_user_id`) VALUES
(1, '564321990V', 'Dr Suu', 'Leeniyagoda', 'suu65@gmail.com', 'female', 'D764358', 'Dentist', '0772342387', 1200, 6),
(2, '735468654V', 'Dr Sasini', 'De silva', 'sasi60@gmail.com', 'female', 'D734698', 'Cardinology', '0772341276', 1500, 7),
(3, '956423875V', 'Dr Sameera', 'Weerathunga', 'sam50@gmail.com', 'male', 'D951200', 'cardiac sergeon', '0724325564', 1000, 8),
(4, '942475100V', 'Dr Dinesh', 'Weerasighe', 'dinesh88@gmail.com', 'male', 'D700328', 'Endocrinologist', '0712130087', 1300, 9),
(5, '63768278V', 'Dr Hashini', 'Perera', 'hash94@gmail.com', 'female', 'D7386543', 'Cardinology', '077363427', 1800, 10);

-- --------------------------------------------------------

--
-- Table structure for table `doctor_has_hospital`
--

CREATE TABLE `doctor_has_hospital` (
  `doctor_doc_id` int(11) NOT NULL,
  `hospital_hosp_id` int(11) NOT NULL,
  `date` varchar(20) COLLATE utf8_bin NOT NULL,
  `time` time NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `doctor_has_hospital`
--

INSERT INTO `doctor_has_hospital` (`doctor_doc_id`, `hospital_hosp_id`, `date`, `time`) VALUES
(1, 3, 'Wednesday', '07:30:00'),
(2, 4, 'Sunday', '16:30:00'),
(3, 1, 'Monday', '16:00:00'),
(3, 2, 'Saturday', '11:00:00');

-- --------------------------------------------------------

--
-- Table structure for table `hospital`
--

CREATE TABLE `hospital` (
  `hosp_id` int(11) NOT NULL,
  `hosp_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `hosp_address` varchar(100) COLLATE utf8_bin NOT NULL,
  `hosp_email` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `hosp_phone` varchar(12) COLLATE utf8_bin NOT NULL,
  `hosp_reg_date` date NOT NULL,
  `hosp_charge` float NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `hospital`
--

INSERT INTO `hospital` (`hosp_id`, `hosp_name`, `hosp_address`, `hosp_email`, `hosp_phone`, `hosp_reg_date`, `hosp_charge`) VALUES
(1, 'Hemas', 'Hemas Hospital,Colombo', 'hemas@gmail.com', '0112342312', '2000-05-14', 2000),
(2, 'Southern', 'Hakmana road,Matara ', 'southern@gmail.com', '0112342321', '2006-04-15', 1000),
(3, 'Celon Helth Care', 'Ambalangoda road.Alpitiya', 'celon@gmail.com', '0112342000', '2006-05-25', 950),
(4, 'Ruhunu Hospital', 'Karapitiya road,Galle', 'ruhunu@gmail.com', '0112300021', '2006-11-15', 1100),
(5, 'Niroga Hospital', 'Thihagoda,Matara', 'niroga@gmail.com', '0112342444', '2006-03-15', 850);

-- --------------------------------------------------------

--
-- Table structure for table `patient`
--

CREATE TABLE `patient` (
  `patient_id` int(11) NOT NULL,
  `p_nic` varchar(45) COLLATE utf8_bin NOT NULL,
  `p_fname` varchar(45) COLLATE utf8_bin NOT NULL,
  `p_lname` varchar(45) COLLATE utf8_bin NOT NULL,
  `p_dob` date NOT NULL,
  `p_address` varchar(100) COLLATE utf8_bin NOT NULL,
  `p_phone` varchar(45) COLLATE utf8_bin NOT NULL,
  `p_email` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  `p_gender` varchar(45) COLLATE utf8_bin NOT NULL,
  `user_user_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `patient`
--

INSERT INTO `patient` (`patient_id`, `p_nic`, `p_fname`, `p_lname`, `p_dob`, `p_address`, `p_phone`, `p_email`, `p_gender`, `user_user_id`) VALUES
(1, '987200499V', 'Shalini', 'Perera', '1998-08-07', '611,station road,wattala', '0712054107', 'shalini437@gmail.com', 'female', 1),
(2, '977223456V', 'Kamal', 'Fernando', '1997-01-03', 'no 252,Hekkitta road,Piliyandala', '0764231254', 'kamal@kkk@gmail.com', 'male', 2),
(3, '912364990V', 'Supun', 'Kariyawasam', '1991-01-03', 'no 9,Dalupitiya road,mattakkuliya', '0726387654', 'suppa888@gmail.com', 'male', 3),
(4, '967543588V', 'Devon', 'Smith', '1996-05-17', 'no 12,Egodauyana road,Moratuwa', '0782348921', 'devonsmith@gmail.com', 'male', 4),
(5, '987654321V', 'Nimal', 'Perera', '1999-09-09', 'No 5,Station road,Ragama ', '0773659405', 'nimal123@gmail.com', 'male', 5);

-- --------------------------------------------------------

--
-- Table structure for table `payment`
--

CREATE TABLE `payment` (
  `payment_id` int(11) NOT NULL,
  `card_type` varchar(45) COLLATE utf8_bin NOT NULL,
  `card_number` varchar(45) COLLATE utf8_bin NOT NULL,
  `name_on_card` varchar(45) COLLATE utf8_bin NOT NULL,
  `cvc` varchar(45) COLLATE utf8_bin NOT NULL,
  `expire_date` date NOT NULL,
  `status` varchar(45) COLLATE utf8_bin NOT NULL,
  `sub_amount` double NOT NULL,
  `date` date NOT NULL,
  `tax_tax_id` int(11) NOT NULL,
  `appoinment_appoinment_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `tax`
--

CREATE TABLE `tax` (
  `tax_id` int(11) NOT NULL,
  `tax_amount` float NOT NULL,
  `valid_from` date NOT NULL,
  `valid_to` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `user_id` int(11) NOT NULL,
  `user_name` varchar(45) COLLATE utf8_bin NOT NULL,
  `password` varchar(10) COLLATE utf8_bin NOT NULL,
  `user_type` varchar(45) COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`user_id`, `user_name`, `password`, `user_type`) VALUES
(1, 'Shalini', 'shalini123', 'Patient'),
(2, 'Kamal', 'kamal321', 'Patient'),
(3, 'Supun', 'supum999', 'Patient'),
(4, 'Devon', 'devon333', 'Patient'),
(5, 'Nimal', 'nima888', 'Patient'),
(6, 'suu', 'suu65', 'Doctor'),
(7, 'Sasini', 'sasini67', 'Doctor'),
(8, 'Sameera', 'sam50', 'Doctor'),
(9, 'Dinesh', 'dinash83', 'Doctor'),
(10, 'Hashini', 'hash84', 'Doctor');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `appoinment`
--
ALTER TABLE `appoinment`
  ADD PRIMARY KEY (`appoinment_id`,`patient_patient_id`,`doctor_doc_id`,`hospital_hosp_id`),
  ADD KEY `fk_appoinment_patient1_idx` (`patient_patient_id`),
  ADD KEY `fk_appoinment_doctor1_idx` (`doctor_doc_id`),
  ADD KEY `fk_appoinment_hospital1_idx` (`hospital_hosp_id`);

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`doc_id`,`user_user_id`),
  ADD KEY `fk_doctor_user1` (`user_user_id`);

--
-- Indexes for table `doctor_has_hospital`
--
ALTER TABLE `doctor_has_hospital`
  ADD PRIMARY KEY (`doctor_doc_id`,`hospital_hosp_id`),
  ADD KEY `fk_doctor_has_hospital_hospital1_idx` (`hospital_hosp_id`),
  ADD KEY `fk_doctor_has_hospital_doctor1_idx` (`doctor_doc_id`);

--
-- Indexes for table `hospital`
--
ALTER TABLE `hospital`
  ADD PRIMARY KEY (`hosp_id`);

--
-- Indexes for table `patient`
--
ALTER TABLE `patient`
  ADD PRIMARY KEY (`patient_id`,`user_user_id`),
  ADD KEY `fk_patient_user_idx` (`user_user_id`);

--
-- Indexes for table `payment`
--
ALTER TABLE `payment`
  ADD PRIMARY KEY (`payment_id`,`tax_tax_id`,`appoinment_appoinment_id`),
  ADD KEY `fk_payment_tax1_idx` (`tax_tax_id`),
  ADD KEY `fk_payment_appoinment1_idx` (`appoinment_appoinment_id`);

--
-- Indexes for table `tax`
--
ALTER TABLE `tax`
  ADD PRIMARY KEY (`tax_id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `appoinment`
--
ALTER TABLE `appoinment`
  MODIFY `appoinment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=23;

--
-- AUTO_INCREMENT for table `doctor`
--
ALTER TABLE `doctor`
  MODIFY `doc_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `hospital`
--
ALTER TABLE `hospital`
  MODIFY `hosp_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `patient`
--
ALTER TABLE `patient`
  MODIFY `patient_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- AUTO_INCREMENT for table `payment`
--
ALTER TABLE `payment`
  MODIFY `payment_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `tax`
--
ALTER TABLE `tax`
  MODIFY `tax_id` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `appoinment`
--
ALTER TABLE `appoinment`
  ADD CONSTRAINT `fk_appoinment_doctor1` FOREIGN KEY (`doctor_doc_id`) REFERENCES `doctor` (`doc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appoinment_hospital1` FOREIGN KEY (`hospital_hosp_id`) REFERENCES `hospital` (`hosp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_appoinment_patient1` FOREIGN KEY (`patient_patient_id`) REFERENCES `patient` (`patient_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `doctor`
--
ALTER TABLE `doctor`
  ADD CONSTRAINT `fk_doctor_user1` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `doctor_has_hospital`
--
ALTER TABLE `doctor_has_hospital`
  ADD CONSTRAINT `fk_doctor_has_hospital_doctor1` FOREIGN KEY (`doctor_doc_id`) REFERENCES `doctor` (`doc_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_doctor_has_hospital_hospital1` FOREIGN KEY (`hospital_hosp_id`) REFERENCES `hospital` (`hosp_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `patient`
--
ALTER TABLE `patient`
  ADD CONSTRAINT `fk_patient_user` FOREIGN KEY (`user_user_id`) REFERENCES `user` (`user_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- Constraints for table `payment`
--
ALTER TABLE `payment`
  ADD CONSTRAINT `fk_payment_appoinment1` FOREIGN KEY (`appoinment_appoinment_id`) REFERENCES `appoinment` (`appoinment_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_payment_tax1` FOREIGN KEY (`tax_tax_id`) REFERENCES `tax` (`tax_id`) ON DELETE NO ACTION ON UPDATE NO ACTION;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
