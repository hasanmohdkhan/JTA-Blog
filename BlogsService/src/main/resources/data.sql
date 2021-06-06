DROP TABLE IF EXISTS student;

CREATE TABLE student (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  branch VARCHAR(250) NOT NULL,
  email VARCHAR(250) DEFAULT NULL,
  percentage INT NOT NULL DEFAULT 0,
  phone INT NOT NULL DEFAULT 0
);

INSERT INTO student (name, branch, email,percentage,phone) VALUES
  ('Aliko', 'CS', 'ACS@gmail.com',0,0),
  ('Bill', 'IT', 'bill@gmail.com',90,8080),
  ('Folrunsho', 'EE', 'f_rusho@hotmail.com',0,0);

SELECT * FROM student