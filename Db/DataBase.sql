CREATE TABLE user (
  id_user INT AUTO_INCREMENT NOT NULL,
  username VARCHAR(50) NOT NULL,
  pwd VARCHAR(50) NOT NULL,
  nome VARCHAR(50),
  email VARCHAR(200) NOT NULL,
  number_phone VARCHAR(10),
  PRIMARY KEY(id_user),
  UNIQUE (username),
  UNIQUE (email)
);

CREATE TABLE credentials(
    id_credential INT AUTO_INCREMENT NOT NULL,
    id_user_credentials INT NOT NULL,
    pwd VARCHAR(50),
    username VARCHAR(50),
    email VARCHAR(200),
    product VARCHAR(200),
    PRIMARY KEY (id_credential , id_user_credentials),
    FOREIGN KEY(id_user_credentials) REFERENCES user(id_user) ON DELETE CASCADE
);


DROP TABLE credentials;

DROP TABLE user;
