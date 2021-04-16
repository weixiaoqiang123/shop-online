drop database if exists shoponline;
create database shoponline character set utf8 collate utf8_general_ci;
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
drop table if exists cate;
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
     head_img varchar(100),
     is_delete int
)character set utf8 collate utf8_general_ci;

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
)character set utf8 collate utf8_general_ci;


-- 商家表
create table business(
     id int primary key AUTO_INCREMENT,
     business_name varchar(50),
     business_code varchar(32),
     account varchar(20),
     address varchar(50)
)character set utf8 collate utf8_general_ci;

-- 商家分类表
create table business_cate(
    id int primary key AUTO_INCREMENT,
    busiess_code varchar(32),
    cate_name varchar(10),
    cate_code varchar(32)
)character set utf8 collate utf8_general_ci;

-- 省
create table province(
    id int primary key AUTO_INCREMENT,
    name varchar(10)
)character set utf8 collate utf8_general_ci;

-- 市
create table city(
    id int primary key AUTO_INCREMENT,
    name varchar(10),
    province_id int
)character set utf8 collate utf8_general_ci;

-- 县
create table county(
    id int primary key AUTO_INCREMENT,
    name varchar(10),
    city_id int
)character set utf8 collate utf8_general_ci;

-- 镇
create table town(
    id int primary key AUTO_INCREMENT,
    name varchar(10),
    county_id int
)character set utf8 collate utf8_general_ci;

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
)character set utf8 collate utf8_general_ci;


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
    business_cate_code varchar(32),
    image_path varchar(100)
)character set utf8 collate utf8_general_ci;

-- spu详情信息
create table spu_detail(
    id int primary key AUTO_INCREMENT,
    spu_code varchar(32),
    info varchar(100)
)character set utf8 collate utf8_general_ci;

-- spu图片表
create table spu_images(
    id int primary key AUTO_INCREMENT,
    spu_code varchar(32),
    image_path varchar(100)
)character set utf8 collate utf8_general_ci;

-- 属性表
create table attr(
    id int primary key AUTO_INCREMENT,
    name varchar(10),
    is_delete int
)character set utf8 collate utf8_general_ci;

-- 属性值表
create table attr_value(
    id int primary key AUTO_INCREMENT,
    name varchar(50),
    attr_id int,
    is_delete int
)character set utf8 collate utf8_general_ci;

-- spu属性关系表
create table spu_attr(
    id int primary key AUTO_INCREMENT,
    spu_code varchar(32),
    attr_id int
)character set utf8 collate utf8_general_ci;

-- sku
create table sku(
    id int primary key AUTO_INCREMENT,
    price decimal(8,2),
    sku_code varchar(32),
    spu_code varchar(32),
    store int,
    image_path varchar(50)
)character set utf8 collate utf8_general_ci;

-- sku属性、属性值关系表
create table sku_attr_value(
    id int primary key AUTO_INCREMENT,
    sku_code varchar(32),
    attr_id int,
    value_id int
)character set utf8 collate utf8_general_ci;

-- 分类
create table cate(
    id int primary key AUTO_INCREMENT,
    cate_name varchar(20),
    cate_code varchar(32),
    parent_cate_code varchar(32)
)character set utf8 collate utf8_general_ci;

-- 轮播图
create table banner(
    id int primary key AUTO_INCREMENT,
    path varchar(100),
    status int
)character set utf8 collate utf8_general_ci;

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
)character set utf8 collate utf8_general_ci;

-- 购物车商品属性、属性值关系
create table shop_cart_attr_value(
    id int primary key AUTO_INCREMENT,
    sku_code varchar(32),
    attr_id int,
    value_id int
)character set utf8 collate utf8_general_ci;

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
)character set utf8 collate utf8_general_ci;

-- 订单详情表
create table order_details(
    id int primary key AUTO_INCREMENT,
    order_code varchar(32),
    sku_code varchar(32),
    shop_num int,
    sku_amount decimal(8,2)
)character set utf8 collate utf8_general_ci;


-- 测试值
-- 用户表
insert into user(nickname,name,password,balance,method,account,is_delete,role)
values
('wxq', 'wxq','e10adc3949ba59abbe56e057f20f883e', 100.0,1,18402895524, 0,2),
('admin',NULL, '21232f297a57a5a743894a0e4a801fc3', NULL, NULL, 'admin', 0,0),
('aaaa', 'aaaa', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345671, 0, 2),
('bbbb', 'bbbb', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345672, 0, 2),
('cccc', 'cccc', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345673, 0, 2),
('dddd', 'dddd', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345674, 0, 2),
('eeee', 'eeee', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345675, 0, 2),
('ffff', 'ffff', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345677, 0, 2),
('gggg', 'gggg', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345678, 0, 2),
('hhhh', 'hhhh', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345679, 0, 2),
('iiii', 'iiii', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345680, 0, 2),
('jjjj', 'jjjj', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345681, 0, 2),
('kkkk', 'kkkk', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345682, 0, 2),

('llll', 'llll', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345683, 0, 1),
('mmmm', 'mmmm', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345684, 0, 1),
('nnnn', 'nnnn', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345685, 0, 1),
('oooo', 'oooo', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345686, 0, 1),
('pppp', 'pppp', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345687, 0, 1),
('qqqq', 'qqqq', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345688, 0, 1),
('rrrr', 'rrrr', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345689, 0, 1),
('ssss', 'ssss', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345690, 0, 1),
('tttt', 'tttt', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345691, 0, 1),
('uuuu', 'uuuu', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345692, 0, 1),
('vvvv', 'vvvv', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345693, 0, 1),
('wwww', 'wwww', 'e10adc3949ba59abbe56e057f20f883e', 100.0,1, 12345694, 0, 1)
;

-- 商家表
INSERT INTO business(business_name,business_code,account)
VALUES
('aaa', '029eab8cb8d540d9bf84d11c4d748bed', '12345683'),
('bbb', '2b317694916146ef9f9a3df53ea3e045', '12345684'),
('ccc', '4fed501a6e6b4164aa3245373e278492', '12345685'),
('ddd', '1f90d4df6ccf42c2b6149f1a9048358f', '12345686'),
('eee', 'f27b3fca330242f6b7b79ded55e1112a', '12345687'),
('fff', '8b02b5141eac4d9fabf56bec14658ba7', '12345688'),
('ggg', '911d9a8df91744e89f84cd973e7f822b', '12345689'),
('hhh', '921059cae3ca49ca8f9d01a41c0039d8', '12345690'),
('iii', 'd3e57d41bd1b4a9fb06e05e9563710a7', '12345691'),
('jjj', 'fe7786e5b0db4818bdb976aeb3fa1c02', '12345692'),
('kkk', '46104f15ae39403fafd2b60ad066d0f7', '12345693'),
('lll', '1118a920159f4f819a358ac3d1e05c42', '12345694')
;


DROP FUNCTION IF EXISTS getCode;
delimiter $
CREATE FUNCTION getCode() returns varchar(32)
BEGIN
	DECLARE res VARCHAR(32);
select replace(UUID(), '-', '') into res;
RETURN res;
END
$
delimiter ;

-- 分类表
INSERT INTO cate(cate_name, cate_code, parent_cate_code)
VALUES
('家用电器','271898818ab911eb8d69107b4420fb1c', '0'),
('手机|运营商|数码','2b199e878ab911eb8d69107b4420fb1c', '0'),
('电脑|办公','2e08fdf18ab911eb8d69107b4420fb1c', '0'),
('家居|家具|厨具','30ec08238ab911eb8d69107b4420fb1c', '0'),
('男装|女装|童装','3381352d8ab911eb8d69107b4420fb1c', '0'),
('美妆|个护清洁','36415ad48ab911eb8d69107b4420fb1c', '0'),
('女鞋|箱包|钟表','392a07888ab911eb8d69107b4420fb1c', '0'),
('男鞋|运动|户外','3bc7ee268ab911eb8d69107b4420fb1c', '0'),
('母婴|玩具乐器','3e7720d98ab911eb8d69107b4420fb1c', '0'),
('食品|酒类|生鲜','412ac6898ab911eb8d69107b4420fb1c', '0'),
('图书|文娱|教育','440795c08ab911eb8d69107b4420fb1c', '0'),
('安装|维修|清洗','48fcf6f48ab911eb8d69107b4420fb1c', '0')
;