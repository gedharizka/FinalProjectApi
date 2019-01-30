create table color_device(
  id          varchar(64)  not null primary key,
  name        varchar(150) not null,
  code  varchar(150) not null,
  description text
) engine = InnoDB;
