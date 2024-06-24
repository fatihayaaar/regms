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
                                 id TEXT PRIMARY KEY ,
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

CREATE MATERIALIZED VIEW regms_post.post_by_created_date AS
SELECT *
FROM regms_post.post
WHERE isDeleted IS NOT NULL AND createdDate IS NOT NULL
PRIMARY KEY (isDeleted, createdDate, id)
WITH CLUSTERING ORDER BY (createdDate DESC);

CREATE INDEX ON regms_post.post (isDeleted);