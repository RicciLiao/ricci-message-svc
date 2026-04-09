create table message_code_secondary
(
    id          bigint auto_increment primary key,
    code        decimal(3)   not null comment 'message identity code, unique with consumer.',
    consumer    varchar(50)  not null comment 'message identity consumer(project/application), unique with code.',
    level       char         null comment 'override primary level.',
    description varchar(255) not null comment 'message content.',
    created_by  bigint       not null,
    created_dtm datetime     not null,
    updated_by  bigint       not null,
    updated_dtm datetime     not null,
    version     datetime(6)  not null,
    constraint message_code_secondary_uk
        unique (code, consumer)
) auto_increment = 1000;

-- //@UNDO
drop table message_code_secondary;