-- =====================================================
--  USERS
-- =====================================================
CREATE TABLE users
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    username   VARCHAR(64) UNIQUE NOT NULL,
    email      VARCHAR(64) UNIQUE NOT NULL,
    phone      VARCHAR(32) UNIQUE,
    password   VARCHAR(128)       NOT NULL,
    about_me   VARCHAR(4096),
    city       VARCHAR(64),
    country    VARCHAR(64),
    experience INT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP
);

-- =====================================================
--  USERS_ROLES (для @ElementCollection)
-- =====================================================
CREATE TABLE users_roles
(
    user_id BIGINT      NOT NULL,
    role    VARCHAR(32) NOT NULL,
    CONSTRAINT fk_user_roles_user FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT pk_user_roles PRIMARY KEY (user_id, role)
);

-- =====================================================
--  POSTS
-- =====================================================
CREATE TABLE posts
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    title      VARCHAR(256) NOT NULL,
    content    TEXT         NOT NULL,
    author_id  BIGINT       NOT NULL,
    category   VARCHAR(32),
    tags       TEXT,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_post_author FOREIGN KEY (author_id)
        REFERENCES users (id) ON DELETE CASCADE
);

-- =====================================================
--  COMMENTS
-- =====================================================
CREATE TABLE comments
(
    id         INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    post_id    BIGINT NOT NULL,
    user_id    BIGINT NOT NULL,
    content    TEXT   NOT NULL,
    created_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_comment_post FOREIGN KEY (post_id)
        REFERENCES posts (id) ON DELETE CASCADE,
    CONSTRAINT fk_comment_user FOREIGN KEY (user_id)
        REFERENCES users (id) ON DELETE CASCADE
);

-- =====================================================
--  SUBSCRIPTIONS
-- =====================================================
CREATE TABLE subscriptions
(
    id          INT PRIMARY KEY GENERATED ALWAYS AS IDENTITY UNIQUE,
    follower_id BIGINT NOT NULL,
    followee_id BIGINT NOT NULL,
    created_at  TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    updated_at  TIMESTAMPTZ DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_follower FOREIGN KEY (follower_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT fk_followee FOREIGN KEY (followee_id)
        REFERENCES users (id) ON DELETE CASCADE,
    CONSTRAINT unique_subscription UNIQUE (follower_id, followee_id)
);

