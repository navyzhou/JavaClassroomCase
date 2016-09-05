--角色信息表
create table roles(
       rid number(10) primary key, --角色编号
       rname varchar2(100) not null unique, --角色名称
       mark varchar2(200), --角色描述
       status number(2) --状态
);
--角色表的序列
create sequence seq_roles_rid start with 1001;

insert into roles values(seq_roles_rid.nextval,'超级管理员','具有最高的权限',1);

--后台管理员信息表
create table adminInfo(
       aid number(10) primary key, --管理员编号
       aname varchar2(100), --管理员姓名
       pwd varchar2(100), --密码
       rid number(10)
           constraints FK_adminInfo_roles_rid references roles(rid),
       email varchar2(100) unique, --注册邮箱
       tel varchar2(15), --联系方式
       photo varchar2(400), --图像
       status number(2), --0未审核  1审核未通过  2已审核可以正常登陆 3冻结
       mark varchar2(200) --说明
);

--后台管理员信息表的序列
create sequence seq_adminInfo_aid start with 1001;

insert into adminInfo values(seq_adminInfo_aid.nextval,'navy','aaa',1001,'navy666@vip.qq.com','15096098010','images/userpic.png',2,'顶级用户');

--前台注册用户
create table userInfo(
       usid number(10) primary key, --会员编号
       uname varchar2(100) not null unique, --昵称
       relname varchar2(100), --真实姓名
       pwd varchar2(100), --密码
       tel varchar2(15) not null unique, --手机号码
       photo varchar2(200), --图像
       prov varchar2(100), --省份
       city varchar2(100), --城市
       area varchar2(100), --地区
       grade number(10,2), --积分
       status  number(2) --状态
);
--前台注册用户表的序列
create sequence seq_userInfo_usid start with 1001;

--商品类型
create table goodstype(
       tid number(10) primary key, --类型编号
       tname varchar2(100) not null unique, --类型名称
       des varchar2(200), --类型描述
       status number(2) --类型状态
);
create sequence seq_goodstype_tid start with 1001;

--店铺信息
create table shopping(
       spid number(20) primary key, --店铺编号
       sname varchar2(200), --店铺名称
       aid number(10) --商家编号
           constraints FK_shopping_adminIno_aid references adminInfo(aid),
       tid number(10) --店铺类型
           constraints FK_goods_goodstype references goodstype(tid),
       prov varchar2(100), --省份
       city varchar2(100), --城市
       area varchar2(100), --地区
       points varchar2(400), --详细地址
       tel varchar2(50), --联系方式
       info clob, --店铺信息
       status number(2) --状态
);
create sequence seq_shopping_spid start with 1001;

--商品信息表
create table goods(
       gid number(20) primary key, --商品编号
       gname varchar2(200), --商品名称
       des varchar2(200), --商品描述
       price number(10,8), --商品原价
       pic varchar2(2000), --商品展示的图片
       status number(2), --商品状态
       spid number(10) --商家编号
           constraints FK_goods_shopping_gid references shopping(spid)
       
);
create sequence seq_goods_gid start with 1001;


--商品活动表
create table goodsAction(
       gaid number(10) primary key, --类型编号
       gid number(20)
           constraints FK_goodsAction_goods_gid references goods(gid),
       aprice number(10,2), --活动价格
       personnum number(2), --几人餐
       title varchar2(200), --活动标题
       startdate date, --活动开始时间
       startend date, --活动结束时间
       tishi varchar2(4000), --温馨提示
       content clob --活动介绍
);
create sequence seq_goodsAction_gaid start with 1001;

--消息表
create table message(
       mid number(10) primary key, --类型编号
       title varchar2(200), --消息标题
       content varchar2(4000), --消息内容
       mdate date, --消息时间
       aid number(10)
           constraints FK_message_adminInfo_aid references adminInfo(aid)
);
create sequence seq_message_mid start with 1001;

--订单表
create table orders(
       oid varchar2(200) primary key, --订单编号
       odate date, --订单日期
       usid number(10)
            constraints FK_orders_userInfo_usid references userInfo(usid),
       gaid number(10)
            constraints FK_orders_goodsAction_gaid references goodsAction(gaid),
       nums number(10), --订单份数
       totalprice number(10,8), --总额
       status number(2) --订单状态
);

--赋予scott用户创建视图的权限
grant create view to scott;

--创建一个管理员视图
create view adminInfos as select a.*,rname from adminInfo a,roles r where a.rid=r.rid;


