USE `employee_directory`;

DROP TABLE IF EXISTS `roles`;
DROP TABLE IF EXISTS `members`;

--
-- Table structure for table `members`
--

CREATE TABLE `members` (
  `user_id` varchar(50) NOT NULL,
  `pw` char(68) NOT NULL,
  `active` tinyint NOT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `members`
--
-- NOTE: The passwords are encrypted using BCrypt
--
-- A generation tool is avail at: https://www.luv2code.com/generate-bcrypt-password
--
-- Default passwords here are: fun123
--

INSERT INTO `members`
VALUES
('muthu','{bcrypt}$2a$10$8uZAvPaFw6n27qxO9VIVmufEzXc7P6UP0bocMRhA6xm9CtTz.SHX.',1),
('pinki','{bcrypt}$2a$10$8uZAvPaFw6n27qxO9VIVmufEzXc7P6UP0bocMRhA6xm9CtTz.SHX.',1),
('sanju','{bcrypt}$2a$10$8uZAvPaFw6n27qxO9VIVmufEzXc7P6UP0bocMRhA6xm9CtTz.SHX.',1);


--
-- Table structure for table `authorities`
--

CREATE TABLE `roles` (
  `user_id` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL,
  UNIQUE KEY `authorities5_idx_1` (`user_id`,`role`),
  CONSTRAINT `authorities5_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `members` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Inserting data for table `roles`
--

INSERT INTO `roles`
VALUES
('muthu','ROLE_EMPLOYEE'),
('pinki','ROLE_EMPLOYEE'),
('pinki','ROLE_MANAGER'),
('sanju','ROLE_EMPLOYEE'),
('sanju','ROLE_MANAGER'),
('sanju','ROLE_ADMIN');
