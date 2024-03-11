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
