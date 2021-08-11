DROP SCHEMA chitchat IF EXISTS;

CREATE SCHEMA chitchat;

CREATE TABLE chitchat.user_status (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (id),
  UNIQUE KEY (name)
);

INSERT INTO chitchat.user_status(name,description)
VALUES
('ACTIVE', 'User is active'),
('INACTIVE', 'User is inactive'),
('BLOCKED', 'User has been disabled by mediator or admin user');

CREATE TABLE chitchat.user_type (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(45) NOT NULL,
  description varchar(255) DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (id),
  UNIQUE KEY (name)
);

INSERT INTO chitchat.user_type(name,description)
VALUES
('ADMIN', 'Master user'),
('MEDIATOR', 'The mediator can remove messages and users from chats'),
('NORMAL', 'Common user');

CREATE TABLE chitchat.chat_room (
  id int NOT NULL AUTO_INCREMENT,
  source_url varchar(255) DEFAULT NULL,
  generated_url varchar(255) DEFAULT NULL,
  creation_date datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (id),
  UNIQUE KEY (id)
);

CREATE TABLE chitchat.user (
  id int NOT NULL AUTO_INCREMENT,
  name varchar(255) NOT NULL,
  type_id int NOT NULL,
  status_id int NOT NULL,
  status_date datetime NOT NULL,
  changed_by int DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY id_UNIQUE (id),
  CONSTRAINT fk_changed_by_user FOREIGN KEY (id) REFERENCES chitchat.user (type_id),
  CONSTRAINT fk_status_id FOREIGN KEY (status_id) REFERENCES chitchat.user_status (id),
  CONSTRAINT fk_type_id FOREIGN KEY (type_id) REFERENCES chitchat.user_type (id)
);

CREATE TABLE chitchat.message (
  id int NOT NULL AUTO_INCREMENT,
  content blob NOT NULL,
  send_date datetime NOT NULL,
  chat_room_id int NOT NULL,
  user_id int NOT NULL,
  removed_by int DEFAULT NULL,
  PRIMARY KEY (id),
  UNIQUE KEY (id),
  CONSTRAINT fk_chat_room_id FOREIGN KEY (chat_room_id) REFERENCES chitchat.chat_room (id),
  CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES chitchat.user (id)
);