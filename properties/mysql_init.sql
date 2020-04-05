alter user 'root'@'localhost' identified by 'root';
grant all privileges on *.* to 'root'@'localhost';
create user 'root'@'127.0.0.1' identified by 'root';
grant all privileges on *.* to 'root'@'127.0.0.1';
flush privileges;
