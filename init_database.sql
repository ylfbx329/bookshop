# 创建数据库和数据表
drop database bookshop;
create database bookshop;
use bookshop;

create table seller
(
    seller_id varchar(32) not null,
    phone     varchar(20) not null,
    password  varchar(20) not null,
    shop_name varchar(20) not null,
    unique (seller_id),
    primary key (phone)
);

create table user
(
    user_id   varchar(32) not null,
    phone     varchar(20) not null,
    password  varchar(20) not null,
    nick_name varchar(20) not null,
    unique (user_id),
    primary key (phone)
);

create table admin
(
    admin_id   varchar(32) not null,
    admin_name varchar(20) not null,
    password   varchar(20) not null,
    flag       int         not null comment '权限标志',
    unique (admin_id),
    primary key (admin_name)
);

create table book
(
    book_id     varchar(32)   not null,
    seller_id   varchar(32)   not null,
    isbn        varchar(20)   not null,
    book_name   varchar(20)   not null,
    author      varchar(10)   not null,
    press       varchar(20)   not null,
    pub_date    date          not null,
    price       decimal(8, 2) not null,
    store_mount int           not null,
    deal_mount  int           not null,
    img         blob          not null,
    unique (book_id),
    primary key (book_id, seller_id),
    foreign key (seller_id) references seller (seller_id)
);

create table `order`
(
    order_id    varchar(32)   not null,
    user_id     varchar(32)   not null,
    book_id     varchar(32)   not null,
    order_mount int           not null,
    price       decimal(8, 2) not null,
    primary key (order_id),
    foreign key (user_id) references user (user_id),
    foreign key (book_id) references book (book_id)
);

# 导入数据
insert into seller(seller_id, phone, password, shop_name)
values ('d81f04c7d81a495d90e3ef7286fb26c9', '15111111111', 'abcdef123', '小帅书屋');
insert into seller(seller_id, phone, password, shop_name)
values ('fbc575b6d9e24ba3945e11d530c05e3d', '15122222222', 'javaweb123', 'Web书库');
insert into user(user_id, phone, password, nick_name)
values ('a327496ee5db4bd580c226c1790d0f19', '18611111111', 'tianyaren0329', '叶涟风不息');
insert into user(user_id, phone, password, nick_name)
values ('1279c48cd943425e83be22530a548b16', '18622222222', '1111', 'FY,ATTO');
insert into admin(admin_id, admin_name, password, flag)
values ('45f79a395d1940348d1722f69c201fca', 'admin', 'admin', '1');
insert into admin(admin_id, admin_name, password, flag)
values ('eaf3d7db03d94343a9c85871636b756c', 'root', 'root', '2');
insert into book(book_id, seller_id, isbn, book_name, author, press, pub_date, price, store_mount, deal_mount, img)
values ('fd06daa273a54679af3eea0dfe223435', 'd81f04c7d81a495d90e3ef7286fb26c9', '978-7-04-046661-4', '单片机原理及应用',
        '张毅刚', '高等教育出版社', '2010-1-1', '36.5', '300', '120',
        'init_data_img/单片机原理及应用.png');
insert into book(book_id, seller_id, isbn, book_name, author, press, pub_date, price, store_mount, deal_mount, img)
values ('cc427985edba4072bde9a9aa7b312f0e', 'fbc575b6d9e24ba3945e11d530c05e3d', '978-7-121-42489-2', '操作系统原理',
        '李国华', '人民出版社', '2019-12-6', '461.4', '120', '15',
        'init_data_img/操作系统原理.png');
