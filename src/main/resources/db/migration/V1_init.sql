CREATE TABLE users
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    username   VARCHAR(64) UNIQUE       NOT NULL,
    password   VARCHAR(128)             NOT NULL,
    email      VARCHAR(64) UNIQUE       NOT NULL,
    phone      VARCHAR(32) UNIQUE,
    about_me   VARCHAR(4096),
    active     BOOLEAN     DEFAULT true NOT NULL,
    city       VARCHAR(64),
    country    VARCHAR(64)              NOT NULL,
    experience INT,
    created_at TIMESTAMPTZ DEFAULT current_timestamp,
    updated_at TIMESTAMPTZ DEFAULT current_timestamp
);

CREATE TABLE subscription
(
    id          INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    follower_id INT NOT NULL,
    followee_id INT NOT NULL,
    created_at  TIMESTAMPTZ DEFAULT current_timestamp,
    updated_at  TIMESTAMPTZ DEFAULT current_timestamp,

    CONSTRAINT fk_follower_id FOREIGN KEY (follower_id) REFERENCES users (id),
    CONSTRAINT fk_followee_id FOREIGN KEY (followee_id) REFERENCES users (id)
);

CREATE TABLE posts
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    title      VARCHAR(256) NOT NULL,
    content    TEXT         NOT NULL,
    author_id  INT          NOT NULL,
    category   VARCHAR(32),
    tags       TEXT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_post_author FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE comments
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    post_id    INT  NOT NULL,
    user_id    INT  NOT NULL,
    content    TEXT NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id) REFERENCES posts (id),
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id) REFERENCES users (id)
);
