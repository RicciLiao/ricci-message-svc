create table `message_code`
(
    `id`           bigint auto_increment primary key,
    `code`         varchar(50)  not null,
    `project_code` varchar(20)  not null,
    `description`  varchar(255) not null,
    `is_active`    int          not null,
    `created_by`   bigint       not null,
    `created_dtm`  datetime(6)  not null,
    `updated_by`   bigint       not null,
    `updated_dtm`  datetime(6)  not null,
    `version`      datetime(6)  not null,
    constraint `message_code_uk` unique (`code`, `project_code`)
) auto_increment = 1000;

-- //@UNDO
drop table `message_code`;