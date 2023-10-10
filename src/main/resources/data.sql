INSERT INTO ticket (name, venue , price, seatnumber, date, time)   VALUES
                                                                       ('Simrat', 'Empire Polo Club', 100, 'A30', '20 August', '9:00PM'),
                                                                       ('Simrat', 'Empire Polo Club1', 100, 'A30', '21 August', '7:00PM'),
                                                                       ('Naman', 'Empire Polo Club2', 100, 'B30', '11 August', '11:00PM'),
                                                                       ('Naman', 'Empire Polo Club3', 200, 'C30', '12 August', '3:00PM'),
                                                                       ('Guru', 'Empire Polo Club4', 100, 'D30', '6 August', '4:00PM'),
                                                                       ('Guru', 'Empire Polo Club5', 200, 'E30', '7 August', '7:00PM'),
                                                                       ('Karan', 'Empire Polo Club6', 100, 'F30', '23 August', '1:00PM'),
                                                                       ('Karan', 'Empire Polo Club7', 200, 'G30', '24 August', '5:00PM'),
                                                                       ('Sahil', 'Empire Polo Club8', 100, 'H30', '24 August', '10:00PM'),
                                                                       ('Sahil', 'Empire Polo Club9', 200, 'I30', '26 August', '11:00PM');





insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$nup7HhOKSsP8VKyNxnNFlOL5gXosG1FKjJd.l8rjREdK8Ge8hM7Ia', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Simrat', '$2a$10$nup7HhOKSsP8VKyNxnNFlOL5gXosG1FKjJd.l8rjREdK8Ge8hM7Ia', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Naman', '$2a$10$nup7HhOKSsP8VKyNxnNFlOL5gXosG1FKjJd.l8rjREdK8Ge8hM7Ia', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Guru', '$2a$10$nup7HhOKSsP8VKyNxnNFlOL5gXosG1FKjJd.l8rjREdK8Ge8hM7Ia', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Karan', '$2a$10$nup7HhOKSsP8VKyNxnNFlOL5gXosG1FKjJd.l8rjREdK8Ge8hM7Ia', 1);

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Sahil', '$2a$10$nup7HhOKSsP8VKyNxnNFlOL5gXosG1FKjJd.l8rjREdK8Ge8hM7Ia', 1);







insert into sec_role (roleName)
values ('ROLE_VENDER');

insert into sec_role (roleName)
values ('ROLE_GUEST');



insert into user_role (userId, roleId)
values (1, 1);

insert into user_role (userId, roleId)
values (2, 2);


insert into user_role (userId, roleId)
values (3, 2);

insert into user_role (userId, roleId)
values (4, 2);

insert into user_role (userId, roleId)
values (5, 2);

insert into user_role (userId, roleId)
values (6, 2);
