-- phpMyAdmin SQL Dump
-- version 4.8.5
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Aug 09, 2020 at 08:02 AM
-- Server version: 5.7.24
-- PHP Version: 7.2.19

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `eems`
--

-- --------------------------------------------------------

--
-- Table structure for table `booking`
--

CREATE TABLE `booking` (
  `student_id` int(11) DEFAULT NULL,
  `event_id` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `booking`
--

INSERT INTO `booking` (`student_id`, `event_id`) VALUES
(1, 30);

-- --------------------------------------------------------

--
-- Table structure for table `data`
--

CREATE TABLE `data` (
  `id` int(11) NOT NULL,
  `name` varchar(50) DEFAULT NULL,
  `password` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `role` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `data`
--

INSERT INTO `data` (`id`, `name`, `password`, `email`, `role`) VALUES
(1, 'Check', '1', '1@1.com', 'Student'),
(2, 'check2', '2', '2@2.com', 'Organiser'),
(3, 'chhhh', '3', '3', 'Administrator'),
(4, '4', '4', '4', 'Student'),
(5, '5', '5', '5', 'Organiser'),
(6, '6', '6', '6', 'Administrator'),
(889, 'testu prasad', 'hello111', 'testu@gmail.com', 'Student'),
(1111, 'Monkey D. Luffy', 'pirateking', 'luffy@pirate.com', 'Student'),
(1122, 'Anuj ', '1122', 'bdsbhd@sdnfb.com', 'Student'),
(1222, 'Trafalgar D. Law', 'law', 'law@law.com', 'Student'),
(5656, 'Roronoa Zoro', 'zoro1', 'zoro@gmail.com', 'Student'),
(412055, 'Anuj Dhakal', 'anuj123', 'anuj1@gmail.com', 'Student'),
(467099, 'Michael Scott', 'michael333', 'michale@yahoo.com', 'Administrator'),
(696988, 'Roshan Parajuli', 'p@ss123', 'roshan.parajuly1@gmail.com', 'Student');

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `event_id` int(11) NOT NULL,
  `event_name` varchar(40) DEFAULT NULL,
  `event_date` varchar(20) DEFAULT NULL,
  `event_time` varchar(20) DEFAULT NULL,
  `event_location` varchar(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `events`
--

INSERT INTO `events` (`event_id`, `event_name`, `event_date`, `event_time`, `event_location`) VALUES
(29, 'cricket', '3rd april', '5 pm', 'side hall'),
(30, 'hackathon', '3rd april', '5 am', 'main hall');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `booking`
--
ALTER TABLE `booking`
  ADD KEY `event_id` (`event_id`);

--
-- Indexes for table `data`
--
ALTER TABLE `data`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `event_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `booking`
--
ALTER TABLE `booking`
  ADD CONSTRAINT `booking_ibfk_1` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
