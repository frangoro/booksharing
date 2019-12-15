-- Data insertions
INSERT INTO user ( id, username, password, name ) VALUES ( 1, 'frangoro', 'Fran Gomez', 'frangoro');
INSERT INTO user ( id, username, password, name ) VALUES ( 2, 'pepito', 'Pepe', 'pepito' );
INSERT INTO user ( id, username, password, name ) VALUES ( 3, 'luilli', 'Luis', 'luilli' );

INSERT INTO user_roles (user_id, role)
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