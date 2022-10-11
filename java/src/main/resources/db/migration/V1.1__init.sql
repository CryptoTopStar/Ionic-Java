insert into user(user_id,locked,enabled,deleted,account_non_expired,credentials_non_expired,username,password,email) values(1,0,1,1,1,1,'admin','$2a$10$bXFcTL0NjuBbd7RfTH6BX.vbwM6l7U/7tKb.1x.4sdJR7L1HopENO','admin@gmail.com');

insert into role(role_id,name) values(1,'ROLE_ADMIN'),(2,'ROLE_USER');

insert into user_role(user_id,role_id)  values(1,1);
