DROP DATABASE IF EXISTS sb_app_2022_10_14;
CREATE DATABASE sb_app_2022_10_14;
USE sb_app_2022_10_14;

CREATE TABLE article (
	id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT,
	PRIMARY KEY(id),
	createDate DATETIME NOT NULL,
	modifyDate DATETIME NOT NULL,
	`subject` CHAR(100) NOT NULL,
	content LONGTEXT NOT NULL
);

INSERT INTO article
SET createDate = NOW(),
modifyDate = NOW(),
`subject` = '제목1',
content = '내용1';

INSERT INTO article
SET createDate = NOW(),
modifyDate = NOW(),
`subject` = '제목2',
content = '내용2';