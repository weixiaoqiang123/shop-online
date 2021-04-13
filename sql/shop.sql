drop database if exists shoponline;
create database shoponline;
use shoponline;

drop table if exists user;
drop table if exists user_address;
drop table if exists business;
drop table if exists business_cate;
drop table if exists province;
drop table if exists city;
drop table if exists county;
drop table if exists town;
drop table if exists comment;
drop table if exists spu;
drop table if exists spu_detail;
drop table if exists spu_images;
drop table if exists attr;
drop table if exists attr_value;
drop table if exists spu_attr;
drop table if exists sku;
drop table if exists sku_attr_value;
drop table if exists root_cate;
drop table if exists cate1;
drop table if exists cate2;
drop table if exists banner;
drop table if exists shop_cart;
drop table if exists shop_cart_attr_value;
drop table if exists shop_order;
drop table if exists order_details;

-- 用户表
create table user(
     id int primary key AUTO_INCREMENT,
     nickname varchar(20),
     password varchar(32),
     balance decimal(8,2),
     account varchar(20),
     role int,
     method int,
     name varchar(10),
     head_img varchar(100)
);

-- 用户收货地址表
create table user_address(
     id int primary key AUTO_INCREMENT,
     account varchar(20),
     receive_name varchar(10),
     phone varchar(11),
     address varchar(50),
     province_id int,
     city_id int,
     county_id int,
     town_id int,
     tag_code varchar(32)
);


-- 商家表
create table business(
     id int primary key AUTO_INCREMENT,
     business_name varchar(50),
     business_code varchar(32),
     account varchar(20),
     address varchar(50)
);

-- 商家分类表
create table business_cate(
    id int primary key AUTO_INCREMENT,
    busiess_code varchar(32),
    cate_name varchar(10),
    cate_code varchar(32)
);

-- 省
create table province(
    id int primary key AUTO_INCREMENT,
    name varchar(10)
);

-- 市
create table city(
    id int primary key AUTO_INCREMENT,
    name varchar(10),
    province_id int
);

-- 县
create table county(
    id int primary key AUTO_INCREMENT,
    name varchar(10),
    city_id int
);

-- 镇
create table town(
    id int primary key AUTO_INCREMENT,
    name varchar(10),
    county_id int
);

-- 商品评论表
create table comment(
    id int primary key AUTO_INCREMENT,
    business_code varchar(32),
    sku_code varchar(32),
    comment_account varchar(20),
    reply_account varchar(20),
    content varchar(500),
    comment_code varchar(32),
    comment_time datetime
);


-- spu
create table spu(
    id int primary key AUTO_INCREMENT,
    spu_name varchar(50),
    spu_code varchar(32),
    spu_introduction varchar(1000),
    spu_status int,
    original_price decimal(8,2),
    sale_price decimal(8,2),
    sale_num int,
    cate_code varchar(32),
    image_path varchar(100)
);

-- spu详情信息
create table spu_detail(
    id int primary key AUTO_INCREMENT,
    spu_code varchar(32),
    info varchar(100)
);

-- spu图片表
create table spu_images(
    id int primary key AUTO_INCREMENT,
    spu_code varchar(32),
    image_path varchar(100)
);

-- 属性表
create table attr(
    id int primary key AUTO_INCREMENT,
    name varchar(10),
    is_delete int
);

-- 属性值表
create table attr_value(
    id int primary key AUTO_INCREMENT,
    name varchar(50),
    attr_id int,
    is_delete int
);

-- spu属性关系表
create table spu_attr(
    id int primary key AUTO_INCREMENT,
    spu_code varchar(32),
    attr_id int
);

-- sku
create table sku(
    id int primary key AUTO_INCREMENT,
    price decimal(8,2),
    sku_code varchar(32),
    spu_code varchar(32),
    store int,
    image_path varchar(50)
);

-- sku属性、属性值关系表
create table sku_attr_value(
    id int primary key AUTO_INCREMENT,
    sku_code varchar(32),
    attr_id int,
    value_id int
);

-- 系统配置分类
create table root_cate(
    id int primary key AUTO_INCREMENT,
    cate_name varchar(20),
    cate_code varchar(32)
);

-- 一级分类
create table cate1(
    id int primary key AUTO_INCREMENT,
    cate_name varchar(20),
    cate_code varchar(32),
    parent_cate_code varchar(32)
);

-- 二级分类
create table cate2(
    id int primary key AUTO_INCREMENT,
    cate_name varchar(20),
    cate_code varchar(32),
    parent_cate_code varchar(32)
);

-- 轮播图
create table banner(
    id int primary key AUTO_INCREMENT,
    path varchar(100),
    status int
);

-- 购物车
create table shop_cart(
    id int primary key AUTO_INCREMENT,
    spu_name varchar(50),
    spu_introduction varchar(1000),
    sku_code varchar(32),
    price decimal(8,2),
    shop_num int,
    business_name varchar(50),
    business_code varchar(32),
    create_time datetime
);

-- 购物车商品属性、属性值关系
create table shop_cart_attr_value(
    id int primary key AUTO_INCREMENT,
    sku_code varchar(32),
    attr_id int,
    value_id int
);

-- 订单表
create table shop_order(
    id int primary key AUTO_INCREMENT,
    order_code varchar(32),
    account varchar(20),
    order_status int,
    pay_status int,
    order_amount decimal(8,2),
    create_time datetime,
    create_by varchar(20)
);

-- 订单详情表
create table order_details(
    id int primary key AUTO_INCREMENT,
    order_code varchar(32),
    sku_code varchar(32),
    shop_num int,
    sku_amount decimal(8,2)
);