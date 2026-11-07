-- Plataformas (Game Boy no tiene videojuegos)
INSERT INTO plataforma (nombre) VALUES ('PlayStation 5');   -- 1
INSERT INTO plataforma (nombre) VALUES ('Nintendo Switch'); -- 2
INSERT INTO plataforma (nombre) VALUES ('Xbox Series X');   -- 3
INSERT INTO plataforma (nombre) VALUES ('PC');              -- 4
INSERT INTO plataforma (nombre) VALUES ('Game Boy');        -- 5

-- Videojuegos
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Astro Bot', 59.99, 1, 1);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Mario Kart 8 Deluxe', 49.99, 2, 2);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Gran Turismo 7', 69.99, 4, 1);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Zelda: Tears of the Kingdom', 69.99, 0, 2);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Animal Crossing', 44.99,9, 2);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Forza Horizon 5', 39.99, 1,3);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Halo Infinite', 29.99,3, 3);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Stardew Valley', 13.99,2, 4);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Hollow Knight', 14.99,1, 4);
INSERT INTO videojuego (titulo, precio, stock, codigo_plataforma) VALUES ('Baldur''s Gate 3', 59.99,2, 4);
