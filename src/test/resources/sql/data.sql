INSERT INTO company (id, name)
VALUES (1, 'Google'),
       (2, 'Meta'),
       (3, 'Amazon');
select setval('company_id_seq', (select max(id) from company));

INSERT INTO company_locales (company_id, lang, description)
VALUES ((SELECT id FROM company WHERE name = 'Google'), 'en', 'Google description'),
       ((SELECT id FROM company WHERE name = 'Google'), 'ru', 'Google описание'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'en', 'Meta description'),
       ((SELECT id FROM company WHERE name = 'Meta'), 'ru', 'Meta описание'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'en', 'Amazon description'),
       ((SELECT id FROM company WHERE name = 'Amazon'), 'ru', 'Amazon описание');

INSERT INTO users (id, birth_date, firstname, lastname, role, username, company_id)
VALUES (1, '1990-01-10', 'Ivan', 'Ivanov', 'ADMIN', 'ivan@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       (2, '1995-10-19', 'Petr', 'Petrov', 'USER', 'petr@gmail.com', (SELECT id FROM company WHERE name = 'Google')),
       (3, '2001-12-23', 'Sveta', 'Svetikova', 'USER', 'sveta@gmail.com', (SELECT id FROM company WHERE name = 'Meta')),
       (4, '1984-03-14', 'Vlad', 'Vladikov', 'USER', 'vlad@gmail.com', (SELECT id FROM company WHERE name = 'Amazon')),
       (5, '1984-03-14', 'Kate', 'Smith', 'ADMIN', 'kate@gmail.com', (SELECT id FROM company WHERE name = 'Amazon'));
select setval('users_id_seq', (select max(id) from users));

INSERT INTO payment (amount, receiver_id)
VALUES (100, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'ivan@gmail.com')),
       (250, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (600, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'petr@gmail.com')),
       (400, (SELECT id FROM users WHERE username = 'sveta@gmail.com')),
       (300, (SELECT id FROM users WHERE username = 'sveta@gmail.com')),
       (500, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (700, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (340, (SELECT id FROM users WHERE username = 'vlad@gmail.com')),
       (440, (SELECT id FROM users WHERE username = 'kate@gmail.com')),
       (510, (SELECT id FROM users WHERE username = 'kate@gmail.com')),
       (630, (SELECT id FROM users WHERE username = 'kate@gmail.com'));

INSERT INTO chat (name)
VALUES ('dmdev'),
       ('java'),
       ('database');

INSERT INTO users_chat(user_id, chat_id)
VALUES ((SELECT id FROM users WHERE username = 'ivan@gmail.com'), (SELECT id FROM chat WHERE name = 'dmdev')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE name = 'dmdev')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'), (SELECT id FROM chat WHERE name = 'dmdev')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'sveta@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'vlad@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE name = 'java')),
       ((SELECT id FROM users WHERE username = 'petr@gmail.com'), (SELECT id FROM chat WHERE name = 'database')),
       ((SELECT id FROM users WHERE username = 'kate@gmail.com'), (SELECT id FROM chat WHERE name = 'database'));

insert into car (id, brand, model, user_id)
VALUES (gen_random_uuid(), 'Skoda', 'Rapid', 1),
       (gen_random_uuid(), 'Skoda', 'Caroq', 2),
       (gen_random_uuid(), 'Skoda', 'Codiaq', 3),
       (gen_random_uuid(), 'Lada', 'Vesta', 5),
       (gen_random_uuid(), 'Lada', 'Priora', 2),
       (gen_random_uuid(), 'Lada', 'Grantaa', 1),
       (gen_random_uuid(), 'Kia', 'Ceed', 3),
       (gen_random_uuid(), 'Kia', 'Rio', 1),
       (gen_random_uuid(), 'Hyundai', 'Solaris', 4),
       (gen_random_uuid(), 'Hyundai', 'Tuscon', 5),
       (gen_random_uuid(), 'Hyundai', 'SantaFe', 1),
       (gen_random_uuid(), 'VW', 'Tuareg', 3),
       (gen_random_uuid(), 'VW', 'Tiguan', 2),
       (gen_random_uuid(), 'VW', 'Polo', 4),
       (gen_random_uuid(), 'Mercedes', 'Galentwagen', 3),
       (gen_random_uuid(), 'Mercedes', 'E200', 1),
       (gen_random_uuid(), 'VW', 'Passat', 2),
       (gen_random_uuid(), 'VW', 'newmodel', 1),
       (gen_random_uuid(), 'Kia', 'Sportage', 3);