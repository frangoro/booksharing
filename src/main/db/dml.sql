USE booksharing;

-- Data insertions
INSERT INTO user ( id, username, password, firstName, lastName, email, enabled ) VALUES ( 1, 'frangoro', 'frangoro', 'Fran', 'Gómez', 'frangoro@gmail.com', 1);
INSERT INTO user ( id, username, password, firstName, lastName, email, enabled ) VALUES ( 2, 'pepito', 'Pepe', 'Jose', 'Ruiz', 'asdf@gmail.com', 1);
INSERT INTO user ( id, username, password, firstName, lastName, email, enabled ) VALUES ( 3, 'luilli', 'Luis', 'Luiz', 'Martel', 'franwergoro@gmail.com', 1);

INSERT INTO role (user_id, role)
VALUES (1, 'USER');
INSERT INTO role (user_id, role)
VALUES (2, 'MANAGER');
INSERT INTO role (user_id, role)
VALUES (3, 'USER');

INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'No shortcuts to the summit', 'Ed Viesturs', 'AVAILABLE', 1, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'The little prince', 'Antonie', 'AVAILABLE', 1, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'War and peace', 'Leo Tolstoy', 'AVAILABLE', 1, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'The hobbit', 'Tolkien', 'AVAILABLE', 2, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'The lord of the rings', 'Tolkien', 'AVAILABLE', 2, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'The two towers', 'Tolkien', 'AVAILABLE', 2, null );