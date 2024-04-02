CREATE TABLE tb_todo (
    id UUID NOT NULL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    status BOOLEAN NOT NULL,
    description VARCHAR(1000) NULL
);

CREATE TABLE tb_user (
    id UUID PRIMARY KEY UNIQUE NOT NULL,
    login varchar(150) NOT NULL UNIQUE,
    password varchar(150) NOT NULL,
    role varchar(20) NOT NULL
);
