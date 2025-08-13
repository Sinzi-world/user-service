CREATE TABLE users
(
    id           INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username     VARCHAR(50)  NOT NULL UNIQUE,
    password     VARCHAR(255) NOT NULL,
    about_me     varchar(4096),
    email        VARCHAR(100) NOT NULL UNIQUE,
    phone_number VARCHAR(20),
    country      VARCHAR(50),
    city         VARCHAR(50),
    street       VARCHAR(100),
    postal_code  VARCHAR(20),
    created_at   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE posts
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    title      VARCHAR(255) NOT NULL,
    content    TEXT         NOT NULL,
    author_id  INT          NOT NULL,
    category   VARCHAR(50),
    tags       TEXT,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_post_author FOREIGN KEY (author_id)
        REFERENCES users (id)
        ON DELETE CASCADE
);

CREATE TABLE comments
(
    id         INT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    post_id    INT  NOT NULL,
    user_id    INT  NOT NULL,
    content    TEXT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id)
        REFERENCES posts (id)
        ON DELETE CASCADE,
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id)
        REFERENCES users (id)
        ON DELETE CASCADE
);
