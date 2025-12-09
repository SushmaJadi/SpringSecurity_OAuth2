CREATE TABLE users(id INTEGER, username VARCHAR(225) NOT NULL, password VARCHAR(225) NOT NULL, enabled VARCHAR(225) NOT NULL );
CREATE TABLE authorities( username VARCHAR(225) NOT NULL, authority VARCHAR(225) NOT NULL );
CREATE UNIQUE INDEX ix_auth_username ON authorities(username, authority);

INSERT INTO users VALUES(1,'kitchenUser', '{noop}12345Kitchen','cooking');
INSERT INTO authorities values('kitchenUser', 'read');
INSERT INTO users VALUES(2,'laundryUser', '{bcrypt}$2y$10$lGAi1qCuGPNmSRmCRTm7DObmidTeNL9Vq0TLberYyYLsSbmK4ZQFq','washing');
INSERT INTO users VALUES(3,'hallUser', '{SHA-256}858e1f305443116c22100e06fdd49cc11679362587dcca2a4e01bf2e1162777d','interior designing');
INSERT INTO users VALUES(4,'roomsUser', '{ldap}03F51CE2bdnsbuyweui2','owner');
INSERT INTO users VALUES(5,'garageUser', '{noop}12345@Garage','garage cleaner');
INSERT INTO authorities values(1,'kitchenUser', 'admin');
INSERT INTO authorities values(2,'laundryUser', 'admin');
INSERT INTO authorities values(3,'hallUser', 'read');
INSERT INTO authorities values(4,'roomsUser', 'admin');
INSERT INTO authorities values(5,'garageUser', 'read');


#########Latest
CREATE TABLE Users (
id INT NOT NULL AUTO_INCREMENT,
userName VARCHAR(225) NOT NULL,
 passwords VARCHAR(225) NOT NULL,
roles VARCHAR(225) NOT NULL,
CONSTRAINT FK_UserName  PRIMARY KEY(id, userName)
);

ALTER TABLE Users ADD passwords VARCHAR(225) NOT NULL;
ALTER TABLE Users DROP PRIMARY KEY, ADD CONSTRAINT FK_UserName  PRIMARY KEY(id, userName);


CREATE TABLE authorities(
id INT NOT NULL AUTO_INCREMENT,
userName VARCHAR(225) NOT NULL,
authority VARCHAR(225) NOT NULL,
FK_UserNameAuth FOREIGN KEY(id,userName) REFERENCES Users(id,userName));

ALTER TABLE authorities ADD CONSTRAINT FK_UserNameAuth FOREIGN KEY(id,userName) REFERENCES Users(id,userName);

INSERT INTO authorities VALUES(1,'kitchenUser', 'admin');
INSERT INTO authorities VALUES(2,'laundryUser', 'admin');
INSERT INTO authorities VALUES(3,'hallUser', 'read');
INSERT INTO authorities VALUES(4,'roomsUser', 'admin');
INSERT INTO authorities VALUES(5,'garageUser', 'read');

SELECT * FROM`authorities`;