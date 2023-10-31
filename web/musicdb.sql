-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Oct 31, 2023 at 04:29 AM
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

-- --------------------------------------------------------

--
-- Table structure for table `genresong`
--

CREATE TABLE `genresong` (
  `genreID` int(11) NOT NULL,
  `songID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `playlist`
--

CREATE TABLE `playlist` (
  `playlistID` int(11) NOT NULL,
  `userID` int(11) NOT NULL,
  `playlistName` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `playlistsong`
--

CREATE TABLE `playlistsong` (
  `playlistID` int(11) NOT NULL,
  `songID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `song`
--

CREATE TABLE `song` (
  `songID` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `artist` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `userID` int(11) NOT NULL,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  `usertype` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

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
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`userID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `genre`
--
ALTER TABLE `genre`
  MODIFY `genreID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `playlist`
--
ALTER TABLE `playlist`
  MODIFY `playlistID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `song`
--
ALTER TABLE `song`
  MODIFY `songID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `userID` int(11) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `genresong`
--
ALTER TABLE `genresong`
  ADD CONSTRAINT `fk_genre` FOREIGN KEY (`genreID`) REFERENCES `genre` (`genreID`),
  ADD CONSTRAINT `fk_songGenre` FOREIGN KEY (`songID`) REFERENCES `song` (`songID`);

--
-- Constraints for table `playlist`
--
ALTER TABLE `playlist`
  ADD CONSTRAINT `fk_user` FOREIGN KEY (`userID`) REFERENCES `user` (`userID`);

--
-- Constraints for table `playlistsong`
--
ALTER TABLE `playlistsong`
  ADD CONSTRAINT `fk_playlist` FOREIGN KEY (`playlistID`) REFERENCES `playlist` (`playlistID`),
  ADD CONSTRAINT `fk_song` FOREIGN KEY (`songID`) REFERENCES `song` (`songID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
