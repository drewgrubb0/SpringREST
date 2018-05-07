-- noinspection SqlNoDataSourceInspectionForFile

CREATE TABLE advertiser
(
  id INTEGER NOT NULL,
  name VARCHAR(255) NOT NULL,
  contactName VARCHAR(255) NOT NULL,
  creditLimit DOUBLE NOT NULL,
  PRIMARY KEY(id)
);