
    create sequence t_artists_SEQ start with 1 increment by 50;

    create sequence t_customers_SEQ start with 1 increment by 50;

    create sequence t_items_SEQ start with 1 increment by 50;

    create sequence t_publishers_SEQ start with 1 increment by 50;

    create sequence t_purchase_order_lines_SEQ start with 1 increment by 50;

    create sequence t_purchase_orders_SEQ start with 1 increment by 50;

    create sequence t_tracks_SEQ start with 1 increment by 50;

    create table t_artists (
        created_at timestamp(6) with time zone not null,
        id bigint not null,
        name varchar(100) not null,
        bio varchar(3000),
        primary key (id)
    );

    create table t_books (
        nb_of_pages integer,
        published_at date,
        id bigint not null,
        publisher_fk bigint,
        isbn varchar(15),
        language varchar(20) check (language in ('ENGLISH','FRENCH','SPANISH','PORTUGUESE','RUSSIAN','CHINESE','INDIAN','GERMAN','JAPANESE')),
        primary key (id)
    );

    create table t_cds (
        id bigint not null,
        genre varchar(100),
        music_company varchar(255),
        primary key (id)
    );

    create table t_customers (
        created_at timestamp(6) with time zone not null,
        id bigint not null,
        first_name varchar(50) not null,
        last_name varchar(50) not null,
        e_mail varchar(255) not null,
        primary key (id)
    );

    create table t_items (
        price numeric(38,2) not null,
        artist_fk bigint,
        created_at timestamp(6) with time zone not null,
        id bigint not null,
        title varchar(100) not null,
        description varchar(3000),
        primary key (id)
    );

    create table t_publishers (
        created_at timestamp(6) with time zone not null,
        id bigint not null,
        name varchar(50) not null,
        primary key (id)
    );

    create table t_purchase_order_lines (
        quantity integer not null,
        created_at timestamp(6) with time zone not null,
        id bigint not null,
        item_fk bigint,
        purchase_order_fk bigint,
        primary key (id)
    );

    create table t_purchase_orders (
        purchase_order_date date not null,
        created_at timestamp(6) with time zone not null,
        customer_fk bigint,
        id bigint not null,
        primary key (id)
    );

    create table t_tracks (
        duration numeric(21,0) not null,
        cd_fk bigint,
        created_at timestamp(6) with time zone not null,
        id bigint not null,
        title varchar(255) not null,
        primary key (id)
    );

    alter table if exists t_books 
       add constraint FKt7eqtrprgi3i343ve25sbsylu 
       foreign key (publisher_fk) 
       references t_publishers;

    alter table if exists t_books 
       add constraint FKqhbwk9qekl2a77l2b624snm14 
       foreign key (id) 
       references t_items;

    alter table if exists t_cds 
       add constraint FKq973yu2pg6p22v1k1t1yehtxb 
       foreign key (id) 
       references t_items;

    alter table if exists t_items 
       add constraint FKr3152tukbog585dik5qwonldg 
       foreign key (artist_fk) 
       references t_artists;

    alter table if exists t_purchase_order_lines 
       add constraint FKf51l5n972qc282ubbv97c1kfa 
       foreign key (item_fk) 
       references t_items;

    alter table if exists t_purchase_order_lines 
       add constraint FKbjsagtstxdmdm55cxqvbxkaji 
       foreign key (purchase_order_fk) 
       references t_purchase_orders;

    alter table if exists t_purchase_orders 
       add constraint FK93wd2w995ng3vyj51y4fur1hg 
       foreign key (customer_fk) 
       references t_customers;

    alter table if exists t_tracks 
       add constraint FKrodt7vwpxopna1rurbuu42u6s 
       foreign key (cd_fk) 
       references t_cds;
