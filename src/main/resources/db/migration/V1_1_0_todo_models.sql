create table TODOV3 (
    id IDENTITY NOT NULL PRIMARY KEY,
    username VARCHAR NOT NULL,
    title VARCHAR NOT NULL,
    createdAt TIMESTAMP,
    updatedAt TIMESTAMP,
    status VARCHAR NOT NULL
);