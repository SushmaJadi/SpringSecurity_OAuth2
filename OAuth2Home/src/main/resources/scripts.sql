CREATE TABLE users(id INTEGER, username VARCHAR(225) NOT NULL, password VARCHAR(225) NOT NULL, enabled VARCHAR(225) NOT NULL );
CREATE TABLE authorities( username VARCHAR(225) NOT NULL, authority VARCHAR(225) NOT NULL );
CREATE UNIQUE INDEX ix_auth_username ON authorities(username, authority);

INSERT INTO users VALUES(1,'kitchenUser', '{noop}12345Kitchen','1');
INSERT INTO authorities values('kitchenUser', 'read');
INSERT INTO users VALUES(2,'laundryUser', '{bcrypt}$2y$10$lGAi1qCuGPNmSRmCRTm7DObmidTeNL9Vq0TLberYyYLsSbmK4ZQFq','1');
INSERT INTO users VALUES(3,'hallUser', '{SHA-256}858e1f305443116c22100e06fdd49cc11679362587dcca2a4e01bf2e1162777d','1');
INSERT INTO users VALUES(4,'roomsUser', '{ldap}03F51CE2bdnsbuyweui2','1');
INSERT INTO users VALUES(5,'garageUser', '{noop}12345@Garage','1');
INSERT INTO authorities values('kitchenUser', 'admin');
INSERT INTO authorities values('laundryUser', 'admin');
INSERT INTO authorities values('hallUser', 'read');
INSERT INTO authorities values('roomsUser', 'admin');
INSERT INTO authorities values('garageUser', 'read');
