CREATE TABLE IF NOT EXISTS service_user
(
  id IDENTITY PRIMARY KEY,
  username VARCHAR(100) NOT NULL ,
  password VARCHAR(100) NOT NULL
);
--
CREATE TABLE IF NOT EXISTS role_table
(
    id IDENTITY PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL
    );
--
CREATE TABLE IF NOT EXISTS SERVICE_USER_ROLES (
    service_user_id INT,
    roles_id INT,
    Constraint PK_Books_Authors Primary Key (service_user_id, roles_id),
    FOREIGN KEY (service_user_id) REFERENCES service_user(id),
    FOREIGN KEY (roles_id) REFERENCES role_table(id)
    );


-- );