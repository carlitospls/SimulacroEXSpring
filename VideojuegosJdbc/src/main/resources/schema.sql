DROP TABLE IF EXISTS videojuego;
DROP TABLE IF EXISTS plataforma;

CREATE TABLE plataforma (
    codigo INT          AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL
);

CREATE TABLE videojuego (
    codigo            INT          AUTO_INCREMENT PRIMARY KEY,
    titulo            VARCHAR(100) NOT NULL,
    precio            DOUBLE       NOT NULL,
    stock             INT       NOT NULL,
    codigo_plataforma INT          NOT NULL,

    CONSTRAINT fk_videojuego_plataforma
        FOREIGN KEY (codigo_plataforma) REFERENCES plataforma (codigo)
);
