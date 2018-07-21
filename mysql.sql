-- phpMyAdmin SQL Dump
-- version 4.8.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jul 21, 2018 at 10:25 AM
-- Server version: 10.1.34-MariaDB
-- PHP Version: 7.2.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `mysql`
--

-- --------------------------------------------------------

--
-- Table structure for table `AirlineAircraft`
--

CREATE TABLE `AirlineAircraft` (
  `ID` int(11) NOT NULL,
  `AirlineName` varchar(255) NOT NULL,
  `AirlineCode` varchar(255) NOT NULL,
  `Model` varchar(255) NOT NULL,
  `FirstClassCapacity` int(11) NOT NULL,
  `BusinessClassCapacity` int(11) NOT NULL,
  `EconomyClassCapacity` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `AirlineAircraft`
--

INSERT INTO `AirlineAircraft` (`ID`, `AirlineName`, `AirlineCode`, `Model`, `FirstClassCapacity`, `BusinessClassCapacity`, `EconomyClassCapacity`) VALUES
(30, 'phnom penh', 'pp', 'bb', 1, 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `FlightDepArr`
--

CREATE TABLE `FlightDepArr` (
  `ID` int(11) NOT NULL,
  `Code` varchar(255) NOT NULL,
  `FlightNumber` int(11) NOT NULL,
  `Type` varchar(255) NOT NULL,
  `Status` varchar(255) NOT NULL,
  `DepartureCode` varchar(255) NOT NULL,
  `DepartureGate` varchar(255) NOT NULL,
  `DepartureDay` varchar(255) NOT NULL,
  `DepartureTime` varchar(255) NOT NULL,
  `ArrivalCode` varchar(255) NOT NULL,
  `ArrivalGate` varchar(255) NOT NULL,
  `ArrivalDay` varchar(255) NOT NULL,
  `ArrivalTime` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `FlightDepArr`
--

INSERT INTO `FlightDepArr` (`ID`, `Code`, `FlightNumber`, `Type`, `Status`, `DepartureCode`, `DepartureGate`, `DepartureDay`, `DepartureTime`, `ArrivalCode`, `ArrivalGate`, `ArrivalDay`, `ArrivalTime`) VALUES
(29, 'pp', 101, 'D', 'Domestic', 'pp', '1', '1', '1', 'bk', '1', '1', '1');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `AirlineAircraft`
--
ALTER TABLE `AirlineAircraft`
  ADD PRIMARY KEY (`ID`);

--
-- Indexes for table `FlightDepArr`
--
ALTER TABLE `FlightDepArr`
  ADD PRIMARY KEY (`ID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `AirlineAircraft`
--
ALTER TABLE `AirlineAircraft`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `FlightDepArr`
--
ALTER TABLE `FlightDepArr`
  MODIFY `ID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=30;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
