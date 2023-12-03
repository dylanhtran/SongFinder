-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Dec 03, 2023 at 04:56 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `musicdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `genre`
--

CREATE TABLE `genre` (
  `genreID` int(11) NOT NULL,
  `genreName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `genre`
--

INSERT INTO `genre` (`genreID`, `genreName`) VALUES
(2, 'EDM'),
(4, 'Rock'),
(5, 'Country'),
(8, 'Pop'),
(10, 'Rap'),
(11, 'Dubstep'),
(12, 'Hip hop');

-- --------------------------------------------------------

--
-- Table structure for table `genresong`
--

CREATE TABLE `genresong` (
  `genreID` int(11) NOT NULL,
  `songID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `genresong`
--

INSERT INTO `genresong` (`genreID`, `songID`) VALUES
(2, 12),
(2, 15),
(11, 15),
(2, 18),
(8, 18),
(2, 20),
(8, 20),
(2, 21),
(8, 21),
(2, 22),
(8, 22),
(2, 23),
(8, 23),
(2, 24),
(8, 24),
(8, 27),
(10, 27),
(12, 27),
(5, 28),
(8, 29),
(10, 29),
(12, 29),
(2, 30),
(8, 30),
(2, 31),
(8, 31),
(2, 32),
(8, 32),
(4, 33);

-- --------------------------------------------------------

--
-- Table structure for table `playlist`
--

CREATE TABLE `playlist` (
  `playlistID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `playlistName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `playlist`
--

INSERT INTO `playlist` (`playlistID`, `userID`, `playlistName`) VALUES
(1, 2, 'Good songs');

-- --------------------------------------------------------

--
-- Table structure for table `playlistsong`
--

CREATE TABLE `playlistsong` (
  `playlistID` int(11) NOT NULL,
  `songID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `playlistsong`
--

INSERT INTO `playlistsong` (`playlistID`, `songID`) VALUES
(1, 12),
(1, 15),
(1, 18),
(1, 22);

-- --------------------------------------------------------

--
-- Table structure for table `song`
--

CREATE TABLE `song` (
  `songID` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `artist` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `song`
--

INSERT INTO `song` (`songID`, `title`, `artist`) VALUES
(12, 'Good Things Fall Apart', 'Illenium'),
(15, 'Bangarang', 'Skrillex'),
(18, 'Hard To Say Goodbye', 'Illenium'),
(20, 'Faded', 'Alan Walker'),
(21, 'Alive', 'Krewella'),
(22, 'Smoke Filled Room', 'Mako'),
(23, 'Crawl Outta Love', 'Illenium'),
(24, 'Clarity', 'Zedd'),
(27, 'Not Afraid', 'Eminem'),
(28, 'Take Me Home, Country Roads', 'John Denver'),
(29, 'CAREFUL', 'NF, Cordae'),
(30, 'Don\'t Let Me Down', 'The Chainsmokers, Daya'),
(31, 'Starving', 'Hailee Steinfeld, Zedd'),
(32, 'I Wanna Know', 'RL Grime, Daya'),
(33, 'Fire', 'PVRIS');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `userID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `usertype` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`userID`, `username`, `password`, `usertype`) VALUES
(2, 'user1', '80717420f1b60af711995c9c30d2fa29$4096$05e8418ac393869cedddf02b93968b72b10ef8669a32a6a7e2cb0b5d348897cd', 'user'),
(4, 'admin1', '3edb03805d06d80e644e148bb290e5e3$4096$8aaadc47b0e631b2db197079f1e6dc9ff9c5fadd658dee55cfc99d45e95df7dd', 'admin'),
(6, 'user2', '857416976c8f72ba34f497705def635e$4096$4f15f0d9f71c3dc7c36b4d676843a0ace108e44f20c774e27dba992548b87d36', 'admin');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `genre`
--
ALTER TABLE `genre`
  ADD PRIMARY KEY (`genreID`);

--
-- Indexes for table `genresong`
--
ALTER TABLE `genresong`
  ADD KEY `fk_genre` (`genreID`),
  ADD KEY `fk_songGenre` (`songID`);

--
-- Indexes for table `playlist`
--
ALTER TABLE `playlist`
  ADD PRIMARY KEY (`playlistID`),
  ADD KEY `fk_user` (`userID`);

--
-- Indexes for table `playlistsong`
--
ALTER TABLE `playlistsong`
  ADD KEY `fk_playlist` (`playlistID`),
  ADD KEY `fk_song` (`songID`);

--
-- Indexes for table `song`
--
ALTER TABLE `song`
  ADD PRIMARY KEY (`songID`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `genreID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `playlist`
--
ALTER TABLE `playlist`
  MODIFY `playlistID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;

--
-- AUTO_INCREMENT for table `song`
--
ALTER TABLE `song`
  MODIFY `songID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=39;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=8;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `genresong`
--
ALTER TABLE `genresong`
  ADD CONSTRAINT `fk_genre` FOREIGN KEY (`genreID`) REFERENCES `genre` (`genreID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_songGenre` FOREIGN KEY (`songID`) REFERENCES `song` (`songID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`userID`) REFERENCES `users` (`userID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `playlistsong`
--
ALTER TABLE `playlistsong`
  ADD CONSTRAINT `fk_playlist` FOREIGN KEY (`playlistID`) REFERENCES `playlist` (`playlistID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_song` FOREIGN KEY (`songID`) REFERENCES `song` (`songID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
