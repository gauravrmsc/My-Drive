CREATE TABLE IF NOT EXISTS USERS (
  userId BIGINT PRIMARY KEY auto_increment,
  username VARCHAR(200),
  password VARCHAR(200),
  firstname VARCHAR(200),
  lastname VARCHAR(200)
);

CREATE TABLE IF NOT EXISTS NOTES (
    noteId BIGINT PRIMARY KEY auto_increment,
    notetitle VARCHAR(200),
    notedescription VARCHAR (1000),
    userId BIGINT,
    foreign key (userId) references USERS(userId)
);

CREATE TABLE IF NOT EXISTS FILES (
    fileId BIGINT PRIMARY KEY auto_increment,
    filename VARCHAR(100),
    contenttype VARCHAR(20),
    filesize BIGINT,
    userId BIGINT,
    filedata BLOB,
    foreign key (userId) references USERS(userId)
);

CREATE TABLE IF NOT EXISTS CREDENTIALS (
    credentialId BIGINT PRIMARY KEY auto_increment,
    url VARCHAR(200),
    username VARCHAR(200),
    key1 VARCHAR(200),
    password VARCHAR(200),
    userId BIGINT,
    foreign key (userId) references USERS(userId)
);
