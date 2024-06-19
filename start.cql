CREATE KEYSPACE regms_profile
    WITH REPLICATION = {
        'class': 'org.apache.cassandra.locator.SimpleStrategy',
        'replication_factor': '3'
        };

CREATE TABLE regms_profile.profile (
    id UUID PRIMARY KEY,
    userId UUID,
    biography TEXT,
    settingsId UUID,
    isPrivate BOOLEAN
);

CREATE TABLE regms_profile.settings (
    id UUID PRIMARY KEY,
    profileId UUID,
    notificationsEnabled BOOLEAN
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

CREATE KEYSPACE regms_follow
    WITH REPLICATION = {
        'class': 'org.apache.cassandra.locator.SimpleStrategy',
        'replication_factor': '3'
        };

CREATE TABLE regms_follow.follow (
    id UUID PRIMARY KEY,
    followerId UUID,
    followingId UUID
);