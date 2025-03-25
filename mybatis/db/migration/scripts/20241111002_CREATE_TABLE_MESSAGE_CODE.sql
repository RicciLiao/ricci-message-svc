create table `message_code`
(
    `id`          bigint auto_increment primary key,
    `code`        varchar(20)  not null comment 'message identity code, unique with consumer.',
    `level`       varchar(1)   not null comment 'I is Info, W is Warning, E is Error',
    `consumer`    varchar(20)  not null comment 'message identity consumer(project/application), unique with code.',
    `description` varchar(255) not null comment 'message content',
    `active`      tinyint(1)   not null comment 'available indicator',
    `created_by`  bigint       not null,
    `created_dtm` datetime(0)  not null,
    `updated_by`  bigint       not null,
    `updated_dtm` datetime(0)  not null,
    `version`     datetime(6)  not null,
    constraint `message_code_uk` unique (`code`, `consumer`)
) auto_increment = 10000;

-- //@UNDO
drop table `message_code`;