CREATE TABLE "user"
(
    "id"       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "email"    text NOT NULL,
    "password" text NOT NULL
);

CREATE TABLE "role"
(
    "id"   bigserial PRIMARY KEY,
    "name" text NOT NULL
);

CREATE TABLE "user_roles"
(
    "user_id" bigint NOT NULL,
    "role_id" bigint NOT NULL,
    UNIQUE (USER_ID, ROLE_ID)
);


CREATE TABLE "car"
(
    "id"             bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "user_id"        bigint NOT NULL,
    "number"         text   NOT NULL,
    "current_status" text   NOT NULL,
    UNIQUE (number)

);

CREATE TABLE "spot"
(
    "id"       bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "location" text NOT NULL,
    UNIQUE (location)

);

CREATE TABLE "booking"
(
    "id"           bigint PRIMARY KEY GENERATED ALWAYS AS IDENTITY,
    "car_id"       bigint    not null,
    "spot_id"      bigint    not null,
    "booking_from" timestamp NOT null,
    "booking_to"   timestamp NOT NULL
);


alter TABLE "user_roles"
    add constraint USER_ROLES_USER_ID_FKEY foreign key (user_id) references "user" (id);
alter TABLE "user_roles"
    add constraint USER_ROLES_ROLE_ID_FKEY foreign key (role_id) references "role" (id);

alter TABLE "car"
    add constraint car_user_id_fkey foreign key (user_id) references "user" (id);
alter TABLE "booking"
    add constraint booking_car_id_fkey foreign key (car_id) references "car" (id);
alter TABLE "booking"
    add constraint booking_spot_id_fkey foreign key (spot_id) references "spot" (id);

INSERT INTO "role"
VALUES (1, 'role_user');
INSERT INTO "role"
VALUES (2, 'role_admin');

INSERT INTO "user" (email, password) VALUES ('maria@gmail.com', '$2a$12$x4NSRXAeMaGvlZ2PLTUefuRz81xqzskLoaFqPqoSBjvZ1fEbh/oQO');
INSERT INTO "user" (email, password) VALUES ('olga@gmail.com', '$2a$12$x4NSRXAeMaGvlZ2PLTUefuRz81xqzskLoaFqPqoSBjvZ1fEbh/oQO');
INSERT INTO "user" (email, password) VALUES ('kate@gmail.com', '$2a$12$x4NSRXAeMaGvlZ2PLTUefuRz81xqzskLoaFqPqoSBjvZ1fEbh/oQO');
INSERT INTO "user" (email, password) VALUES ('alex@gmail.com', '$2a$12$x4NSRXAeMaGvlZ2PLTUefuRz81xqzskLoaFqPqoSBjvZ1fEbh/oQO');
INSERT INTO "user" (email, password) VALUES ('anna@gmail.com', '$2a$12$x4NSRXAeMaGvlZ2PLTUefuRz81xqzskLoaFqPqoSBjvZ1fEbh/oQO');

INSERT INTO "user_roles" VALUES (1, 2);
INSERT INTO "user_roles" VALUES (2, 1);
INSERT INTO "user_roles" VALUES (3, 1);
INSERT INTO "user_roles" VALUES (4, 1);
INSERT INTO "user_roles" VALUES (5, 1);

INSERT INTO "spot" (location) VALUES ('201');
INSERT INTO "spot" (location) VALUES ('202');
INSERT INTO "spot" (location) VALUES ('203');
INSERT INTO "spot" (location) VALUES ('204');
INSERT INTO "spot" (location) VALUES ('205');
INSERT INTO "spot" (location) VALUES ('206');
INSERT INTO "spot" (location) VALUES ('207');
INSERT INTO "spot" (location) VALUES ('208');
INSERT INTO "spot" (location) VALUES ('209');
INSERT INTO "spot" (location) VALUES ('210');

INSERT INTO "car" (user_id, number, current_status) VALUES (1, 'А123ВС177', 'approved');
INSERT INTO "car" (user_id, number, current_status) VALUES (2, 'А456ВС177', 'approved');
INSERT INTO "car" (user_id, number, current_status) VALUES (2, 'А789ВС177', 'approved');
INSERT INTO "car" (user_id, number, current_status) VALUES (3, 'А159ВС177', 'approved');
INSERT INTO "car" (user_id, number, current_status) VALUES (4, 'А753ВС177', 'approved');
INSERT INTO "car" (user_id, number, current_status) VALUES (5, 'ВС123А177', 'approved');

INSERT INTO "booking" (car_id, spot_id, booking_from, booking_to) VALUES (1, 2, '2021-12-28T10:00', '2021-12-28T16:00');
INSERT INTO "booking" (car_id, spot_id, booking_from, booking_to) VALUES (2, 4, '2021-12-28T09:00', '2021-12-28T12:00');
INSERT INTO "booking" (car_id, spot_id, booking_from, booking_to) VALUES (3, 5, '2021-12-28T13:00', '2021-12-28T17:00');
INSERT INTO "booking" (car_id, spot_id, booking_from, booking_to) VALUES (4, 6, '2021-12-28T11:00', '2021-12-28T13:00');
INSERT INTO "booking" (car_id, spot_id, booking_from, booking_to) VALUES (5, 8, '2021-12-28T15:00', '2021-12-28T20:00');
