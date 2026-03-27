create table message_code_primary
(
    id          bigint auto_increment primary key,
    code        bigint       not null comment 'message identity code.',
    level       char         not null comment 'I is Info, W is Warning, E is Error.',
    description varchar(255) not null comment 'message content.',
    created_by  bigint       not null,
    created_dtm datetime     not null,
    updated_by  bigint       not null,
    updated_dtm datetime     not null,
    version     datetime(6)  not null,
    constraint message_code_primary_uk unique (code)
) auto_increment = 1000;

-- //@UNDO
drop table message_code_primary;