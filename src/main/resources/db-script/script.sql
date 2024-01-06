CREATE TABLE tb_todo (
    id UUID NOT NULL PRIMARY KEY,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP NULL,
    status BOOLEAN NOT NULL,
    description VARCHAR(1000) NULL
);