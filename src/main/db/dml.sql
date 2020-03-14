-- Data insertions
INSERT INTO user ( id, username, password, firstName, secondName, email, enabled ) VALUES ( 1, 'frangoro', 'frangoro', 'Fran', 'Gómez', 'frangoro@gmail.com', 1);
INSERT INTO user ( id, username, password, firstName, secondName, email, enabled ) VALUES ( 2, 'pepito', 'Pepe', 'Jose', 'Ruiz', 'asdf@gmail.com', 1);
INSERT INTO user ( id, username, password, firstName, secondName, email, enabled ) VALUES ( 3, 'luilli', 'Luis', 'Luiz', 'Martel', 'franwergoro@gmail.com', 1);

INSERT INTO role (user_id, role)
VALUES (1, 'USER');
INSERT INTO user_roles (user_id, role)
VALUES (2, 'MANAGER');
INSERT INTO user_roles (user_id, role)
VALUES (3, 'USER');

INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'No shortcuts to the summit', 'Ed Viesturs', 'available', 1, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'The little prince', 'Antonie', 'available', 1, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'War and peace', 'Leo Tolstoy', 'available', 1, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'The hobbit', 'Tolkien', 'available', 2, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'The lord of the rings', 'Tolkien', 'available', 2, null );
INSERT INTO book ( id, title, author, status, owner, reader ) VALUES ( null, 'The two towers', 'Tolkien', 'available', 2, null );