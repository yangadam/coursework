DROP DATABASE comm;
CREATE DATABASE comm DEFAULT CHARACTER
SET utf8
    COLLATE utf8_general_ci;
USE comm;
INSERT
INTO user (username, password, salt, name, locked)
VALUES ('admin', '3b6e389b430bd8255156136905a1259c',
        '661205464b49a25295c592526f12191e', '管理员', FALSE);