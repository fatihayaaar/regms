CREATE KEYSPACE regms_profile
    WITH REPLICATION = {
        'class': 'org.apache.cassandra.locator.SimpleStrategy',
        'replication_factor': '3'
        };

CREATE TABLE regms_profile.profile (
    id TEXT PRIMARY KEY,
    userId TEXT,
    biography TEXT,
    isPrivate BOOLEAN,
    notificationsEnabled BOOLEAN,
    backgroundImage TEXT
);

CREATE KEYSPACE regms_post
    WITH REPLICATION = {
        'class': 'org.apache.cassandra.locator.SimpleStrategy',
        'replication_factor': '3'
        };

CREATE TABLE regms_post.post (
    id TEXT PRIMARY KEY,
    userId TEXT,
    uri TEXT,
    text TEXT,
    createdDate TIMESTAMP,
    updatedDate TIMESTAMP,
    likeCount INT,
    commentCount INT,
    visible BOOLEAN,
    isDeleted BOOLEAN,
);