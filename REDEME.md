# 忠告

​	本系统是一个基于Java（Servlet/JSP）的小型论坛交流平台，未使用任何框架。数据库使用Oracle，建表语句将会放在文后。

​	由于这是在培训机构多人合作完成，人员能力不一致，并且沟通并不到位，可能存在许多重复代码，许多问题也层出不穷，但基本功能稳定。

​	本论坛使用了editor.md用作帖子的格式控制，可以发布帖子、查看帖子、登录注册、评论、点赞收藏、消息提醒、一个临时加入的巨垃圾的积分系统。

​	在数据的查询上十分简单粗暴，注定无法成为一个商用产品，只能用于应付作业只用。







注意！！
用户表中加入了最后登陆时间的字段。
为了避免不可预知的错误，建议删除原来数据表，并重新创建

文章表
create table ARTICLE
(
  aid            NUMBER(10),
  userid         NUMBER(10),
  title          VARCHAR2(50),
  content        VARCHAR2(1500),
  publishtime    DATE default sysdate,
  finalaltertime DATE default sysdate,
  browsenum      NUMBER(10) default 0,
  likenum        NUMBER(10),
  collectnum     NUMBER(10)
)


文章序列

create sequence ARTICLE_SEQ
minvalue 1

收藏点赞表

-- Create table
create table COLLECTLIKE
(
  clid   NUMBER(12),
  aid    NUMBER(10),
  userid NUMBER(10),
  cltype VARCHAR2(10),
  cltime DATE default sysdate   	--收藏点赞时间,
  status NUMBER(2) default 1
)



收藏点赞序列

-- Create sequence 
create sequence COLLECTLIKE_SEQ
minvalue 1



评论表

create table COMMENTS
(
  cid         NUMBER(10) not null,
  aid         NUMBER(10),
  userid      NUMBER(10),
  uname       VARCHAR2(14),
  c_content   VARCHAR2(1500),
  commenttime DATE default sysdate,
  superid     NUMBER(10),
  clevel      NUMBER(10)
)


评论表序列

create sequence SEQ_COMMENTS_CID
minvalue 1



积分表

create table POINT
(
  userid NUMBER(10),
  aid    NUMBER(10),
  time   DATE default sysdate,
  pnum   NUMBER(10),
  ptype  VARCHAR2(10)
)

积分表序列

create sequence POINT_SEQ
minvalue 1


用户表
create table USERS
(
  userid        NUMBER(10),
  uname         VARCHAR2(14),
  email         VARCHAR2(20),
  password      VARCHAR2(20),
  sex           VARCHAR2(2),
  birth         DATE,
  photoname     VARCHAR2(50),
  point         NUMBER(10) default 0           --积分,
  lastlogintime DATE
)


用户序列

create sequence USER_SEQ
minvalue 1

