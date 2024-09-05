CREATE KEYSPACE IF NOT EXISTS regms_profile WITH REPLICATION = { 'class': 'org.apache.cassandra.locator.SimpleStrategy', 'replication_factor': '3' };

CREATE TABLE IF NOT EXISTS regms_profile.profile
(
    id                   TEXT PRIMARY KEY,
    userId               TEXT,
    biography            TEXT,
    isPrivate            BOOLEAN,
    notificationsEnabled BOOLEAN,
    backgroundImage      TEXT
);

CREATE KEYSPACE IF NOT EXISTS regms_post WITH REPLICATION = { 'class': 'org.apache.cassandra.locator.SimpleStrategy', 'replication_factor': '3' };

CREATE TABLE IF NOT EXISTS regms_post.post
(
    id           TEXT PRIMARY KEY,
    userId       TEXT,
    uri          TEXT,
    text         TEXT,
    createdDate  TIMESTAMP,
    updatedDate  TIMESTAMP,
    likeCount    INT,
    commentCount INT,
    visible      BOOLEAN,
    isDeleted    BOOLEAN,
);