INSERT INTO role_table ( role_name)
VALUES ( 'ADMIN');
INSERT INTO role_table ( role_name)
VALUES ( 'USER');


INSERT INTO service_user (username, password)
VALUES ('user', '$2a$10$ROjW8EN7bL479X.iZL6/aevgVtyFHWxy0lRjES9HlGVkjdaRIvsDi');
INSERT INTO service_user (username, password)
VALUES ('admin', '$2a$10$5hgwq9bLHqUJrhkoGh7BDOXwTDRu7yQ131RHy6Z3vfojE4r13wb9e');

INSERT INTO SERVICE_USER_ROLES (service_user_id, roles_id)
VALUES (1, 2);
INSERT INTO SERVICE_USER_ROLES (service_user_id, roles_id)
VALUES (2, 1);
INSERT INTO SERVICE_USER_ROLES (service_user_id, roles_id)
VALUES (2, 2);


