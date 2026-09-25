CREATE DATABASE IF NOT EXISTS seu_banco;

USE seu_banco;

CREATE TABLE
    IF NOT EXISTS cliente (
        id INT AUTO_INCREMENT PRIMARY KEY,
        cpf CHAR(11) NOT NULL UNIQUE,
        nome VARCHAR(50) NOT NULL,
        rua VARCHAR(30),
        numero VARCHAR(10),
        bairro VARCHAR(30),
        cidade VARCHAR(30),
        uf CHAR(2) DEFAULT 'MS',
        fone VARCHAR(20) NOT NULL,
        email VARCHAR(40) NOT NULL,
        data_nascimento DATE NOT NULL
    );