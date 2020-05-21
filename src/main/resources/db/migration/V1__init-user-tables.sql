CREATE TABLE users (
  	id            		VARCHAR(36)  NOT NULL PRIMARY KEY,
  	username      		VARCHAR(255) NOT NULL UNIQUE,
  	password      		VARCHAR(60),
  	enabled 			BOOLEAN      NOT NULL,
  	email   			VARCHAR(255) NOT NULL UNIQUE,
  	reset_password_id	VARCHAR(36)  UNIQUE,
  	reset_password_date	VARCHAR(36)
)ROW_FORMAT=DYNAMIC;

CREATE TABLE persons (
  	id       			VARCHAR(36)  NOT NULL PRIMARY KEY,
  	userId   			VARCHAR(36)  NOT NULL UNIQUE,
  	forename 			VARCHAR(255),
  	surname  			VARCHAR(255),
  	CONSTRAINT fk_persons_users FOREIGN KEY (userId) REFERENCES users (id)
)ROW_FORMAT=DYNAMIC;

CREATE TABLE authorities (
  	username  			VARCHAR(255) NOT NULL,
  	authority 			VARCHAR(50)  NOT NULL,
  	CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
)ROW_FORMAT=DYNAMIC;