/*
Navicat Oracle Data Transfer
Oracle Client Version : 11.2.0.1.0

Source Server         : ceshi
Source Server Version : 100200
Source Host           : 192.168.10.44:1521
Source Schema         : WTGK

Target Server Type    : ORACLE
Target Server Version : 100200
File Encoding         : 65001

Date: 2018-09-30 13:08:22
*/


-- ----------------------------
-- Table structure for T_FILE_INFO
-- ----------------------------
DROP TABLE "WTGK"."T_FILE_INFO";
CREATE TABLE "WTGK"."T_FILE_INFO" (
"ID" NUMBER(8) NOT NULL ,
"USER_ID" NUMBER(8) NOT NULL ,
"FILE_NAME" VARCHAR2(255 BYTE) NOT NULL ,
"FILE_TYPE" NUMBER(8) NOT NULL ,
"FILE_PATH" VARCHAR2(255 BYTE) NOT NULL ,
"CREATE_TIME" DATE NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_FILE_INFO
-- ----------------------------
INSERT INTO "WTGK"."T_FILE_INFO" VALUES ('51', '1', '210.jpg', '0', '/20180930/20180930095923927.jpg', TO_DATE('2018-09-30 09:59:23', 'YYYY-MM-DD HH24:MI:SS'));
INSERT INTO "WTGK"."T_FILE_INFO" VALUES ('53', '1', '212.jpeg', '0', '/20180930/20180930095923926.jpeg', TO_DATE('2018-09-30 09:59:23', 'YYYY-MM-DD HH24:MI:SS'));

-- ----------------------------
-- Table structure for T_PERMISSION
-- ----------------------------
DROP TABLE "WTGK"."T_PERMISSION";
CREATE TABLE "WTGK"."T_PERMISSION" (
"PERMISSION_ID" NUMBER(8) NOT NULL ,
"NAME" VARCHAR2(50 BYTE) NULL ,
"PID" NUMBER(8) NULL ,
"PARENT_NAME" VARCHAR2(50 BYTE) NULL ,
"TYPE" NUMBER NULL ,
"URL" VARCHAR2(255 BYTE) NULL ,
"CODE" VARCHAR2(50 BYTE) NULL ,
"COLOR" VARCHAR2(32 BYTE) NULL ,
"ICON" VARCHAR2(32 BYTE) NULL ,
"SORT" NUMBER(8) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_PERMISSION
-- ----------------------------
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('20', '组件测试', '1', '系统管理', '2', 'fileinfo/index', 'fileinfo:index', null, null, '0');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('1', '系统管理', '0', null, '1', null, null, null, null, '1');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('2', '用户管理', '1', '系统管理', '2', 'user/listUI', 'user:listUI', null, null, '1');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('3', '新增', '2', '用户管理', '3', null, 'user:add', 'btn-primary', 'icon-ok', '2');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('4', '编辑', '2', '用户管理', '3', null, 'user:update', 'btn-warning', 'icon-edit', '3');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('5', '删除', '2', '用户管理', '3', null, 'user:delete', 'btn-danger', 'icon-trash', '4');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('6', '角色管理', '1', '系统管理', '2', 'role/listUI', 'role:listUI', null, null, '2');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('7', '新增', '6', '角色管理', '3', null, 'role:add', 'btn-primary', 'icon-ok', '2');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('8', '编辑', '6', '角色管理', '3', null, 'role:update', 'btn-warning', 'icon-edit', '3');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('9', '删除', '6', '角色管理', '3', null, 'role:delete', 'btn-danger', 'icon-trash', '4');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('10', '权限管理', '1', '系统管理', '2', 'permission/listUI', 'permission:listUI', null, null, '3');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('11', '新增', '10', '权限管理', '3', null, 'permission:add', 'btn-primary', 'icon-ok', '1');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('12', '编辑', '10', '权限管理', '3', null, 'permission:update', 'btn-warning', 'icon-edit', '2');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('13', '删除', '10', '权限管理', '3', null, 'permission:delete', 'btn-danger', 'icon-trash', '3');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('14', '设置角色', '2', '用户管理', '3', null, 'user:setRole', 'btn-success', 'icon-cog', '1');
INSERT INTO "WTGK"."T_PERMISSION" VALUES ('15', '设置权限', '6', '角色管理', '3', null, 'role:setPermission', 'btn-success', 'icon-cog', '1');

-- ----------------------------
-- Table structure for T_ROLE
-- ----------------------------
DROP TABLE "WTGK"."T_ROLE";
CREATE TABLE "WTGK"."T_ROLE" (
"ROLE_ID" NUMBER(8) NOT NULL ,
"NAME" VARCHAR2(50 BYTE) NOT NULL ,
"DESCR" VARCHAR2(255 BYTE) DEFAULT ''  NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ROLE
-- ----------------------------
INSERT INTO "WTGK"."T_ROLE" VALUES ('1', '超级管理员', '最高权限');
INSERT INTO "WTGK"."T_ROLE" VALUES ('2', 'SQL测试角色', '测试222');

-- ----------------------------
-- Table structure for T_ROLE_PERMISSION
-- ----------------------------
DROP TABLE "WTGK"."T_ROLE_PERMISSION";
CREATE TABLE "WTGK"."T_ROLE_PERMISSION" (
"ID" NUMBER(8) NOT NULL ,
"ROLE_ID" NUMBER(8) NOT NULL ,
"PERMISSION_ID" NUMBER(8) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_ROLE_PERMISSION
-- ----------------------------
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('29', '2', '1');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('30', '2', '10');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('32', '1', '1');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('33', '1', '20');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('34', '1', '21');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('35', '1', '22');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('36', '1', '2');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('37', '1', '3');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('38', '1', '4');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('39', '1', '5');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('40', '1', '14');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('41', '1', '6');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('42', '1', '7');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('43', '1', '8');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('44', '1', '9');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('45', '1', '15');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('46', '1', '10');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('47', '1', '11');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('48', '1', '12');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('49', '1', '13');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('18', '3', '1');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('19', '3', '2');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('20', '3', '3');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('21', '3', '6');
INSERT INTO "WTGK"."T_ROLE_PERMISSION" VALUES ('22', '3', '7');

-- ----------------------------
-- Table structure for T_USER
-- ----------------------------
DROP TABLE "WTGK"."T_USER";
CREATE TABLE "WTGK"."T_USER" (
"USER_ID" NUMBER(8) NOT NULL ,
"USER_NAME" VARCHAR2(20 BYTE) NOT NULL ,
"PASSWORD" VARCHAR2(64 BYTE) NOT NULL ,
"EMAIL" VARCHAR2(64 BYTE) DEFAULT NULL  NULL ,
"PHONE" VARCHAR2(11 BYTE) DEFAULT NULL  NULL ,
"SALT" VARCHAR2(10 BYTE) DEFAULT NULL  NULL ,
"STATUS" NUMBER(8) NOT NULL ,
"CREATE_TIME" DATE NULL ,
"UPDATE_TIME" DATE NULL ,
"PHOTO" VARCHAR2(255 BYTE) NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_USER
-- ----------------------------
INSERT INTO "WTGK"."T_USER" VALUES ('10', 'testsql3', 'e10adc3949ba59abbe56e057f20f883e', '774819654@qq.com', '13869439430', null, '1', TO_DATE('2018-09-28 09:05:30', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:05:30', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('11', 'syl', 'e10adc3949ba59abbe56e057f20f883e', '45352654@qq.com', '13869423430', null, '0', TO_DATE('2018-09-28 09:09:19', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:09:19', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('12', 'sql', 'e10adc3949ba59abbe56e057f20f883e', '765419654@qq.com', '13869404304', null, '1', TO_DATE('2018-09-28 09:09:44', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:09:44', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('13', 'syl21', 'e10adc3949ba59abbe56e057f20f883e', '45352654@qq.com', '13869404304', null, '1', TO_DATE('2018-09-28 09:09:56', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:09:56', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('14', 'yali', 'e10adc3949ba59abbe56e057f20f883e', '798669654@qq.com', '13860823430', null, '0', TO_DATE('2018-09-28 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:10:10', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('15', 'ceshi21', 'e10adc3949ba59abbe56e057f20f883e', '798819875@qq.com', '1386739430', null, '1', TO_DATE('2018-09-28 09:10:36', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:10:36', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('16', 'syli8', 'e10adc3949ba59abbe56e057f20f883e', '678669654@qq.com', '1386732430', null, '0', TO_DATE('2018-09-28 09:10:57', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:10:57', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('17', 'woniu', 'e10adc3949ba59abbe56e057f20f883e', '678679654@qq.com', '13854823444', null, '1', TO_DATE('2018-09-28 09:11:14', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:11:14', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('18', 'liuhui', 'e10adc3949ba59abbe56e057f20f883e', '984819654@qq.com', '13843223430', null, '1', TO_DATE('2018-09-28 09:11:34', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-28 09:11:34', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('25', '测试121-44', 'e10adc3949ba59abbe56e057f20f883e', '7748321@qq.com', '13860320328', null, '1', TO_DATE('2018-09-30 10:27:31', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 10:27:31', 'YYYY-MM-DD HH24:MI:SS'), '/20180930/20180930113116827.jpg');
INSERT INTO "WTGK"."T_USER" VALUES ('23', '测试121-11', 'e10adc3949ba59abbe56e057f20f883e', '7748321@qq.com', '13860320328', null, '0', TO_DATE('2018-09-30 10:26:48', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 10:26:48', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('21', '测试121', 'e10adc3949ba59abbe56e057f20f883e', '7748321@qq.com', '13860320328', null, '1', TO_DATE('2018-09-30 10:24:06', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 10:24:06', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('27', 'sysceshi1', 'afdd0b4ad2ec172c586e2150770fbf9e', '774819876@qq.com', '13860430438', null, '1', TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), '/20180930/20180930123854650.jpeg');
INSERT INTO "WTGK"."T_USER" VALUES ('28', 'sysceshi2', 'dc09cda0a65f2fc067818dd6f6384750', '774819877@qq.com', '13860430438', null, '0', TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('29', 'sysceshi3', '974fc7510e3dc8f1de47092b01a8a58c', '774819878@qq.com', '13860430438', null, '1', TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('30', 'sysceshi4', '4abf0f5ceb4ffb1f1ec6b3c1e5c8bdd2', '774819879@qq.com', '13860430438', null, '0', TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('31', 'sysceshi5', 'e10adc3949ba59abbe56e057f20f883e', '774819880@qq.com', '13860430438', null, '1', TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('32', 'sysceshi6', '54c47faed69aadb58b474d323fb0b420', '774819881@qq.com', '13860430438', null, '0', TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('33', 'sysceshi7', '762fc43bc9e15c83fe9a529abf13eadc', '774819882@qq.com', '13860430438', null, '0', TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('34', 'sysceshi8', '29688dcfb5ce4754a10dba78749943c1', '774819883@qq.com', '13860430438', null, '1', TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-30 12:35:09', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('1', 'admin', '21232f297a57a5a743894a0e4a801fc3', 'admin@163.com', null, 'abc', '1', TO_DATE('2018-09-27 15:13:07', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-27 15:13:07', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('9', 'testsql2', 'e10adc3949ba59abbe56e057f20f883e', '774819875@qq.com', '13869439430', null, '1', TO_DATE('2018-09-27 17:27:30', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-27 17:27:30', 'YYYY-MM-DD HH24:MI:SS'), null);
INSERT INTO "WTGK"."T_USER" VALUES ('8', 'testsql', 'e10adc3949ba59abbe56e057f20f883e', '774819876@qq.com', '1360420427', null, '0', TO_DATE('2018-09-27 15:54:21', 'YYYY-MM-DD HH24:MI:SS'), TO_DATE('2018-09-27 15:54:21', 'YYYY-MM-DD HH24:MI:SS'), null);

-- ----------------------------
-- Table structure for T_USER_ROLE
-- ----------------------------
DROP TABLE "WTGK"."T_USER_ROLE";
CREATE TABLE "WTGK"."T_USER_ROLE" (
"ID" NUMBER(8) NOT NULL ,
"USER_ID" NUMBER(8) NOT NULL ,
"ROLE_ID" NUMBER(8) NOT NULL 
)
LOGGING
NOCOMPRESS
NOCACHE

;

-- ----------------------------
-- Records of T_USER_ROLE
-- ----------------------------
INSERT INTO "WTGK"."T_USER_ROLE" VALUES ('1', '1', '1');
INSERT INTO "WTGK"."T_USER_ROLE" VALUES ('5', '3', '1');
INSERT INTO "WTGK"."T_USER_ROLE" VALUES ('6', '3', '3');
INSERT INTO "WTGK"."T_USER_ROLE" VALUES ('7', '10', '2');

-- ----------------------------
-- Sequence structure for S_T_FILE_INFO
-- ----------------------------
DROP SEQUENCE "WTGK"."S_T_FILE_INFO";
CREATE SEQUENCE "WTGK"."S_T_FILE_INFO"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 99999999
 START WITH 55
 NOCACHE ;

-- ----------------------------
-- Sequence structure for S_T_PERMISSION
-- ----------------------------
DROP SEQUENCE "WTGK"."S_T_PERMISSION";
CREATE SEQUENCE "WTGK"."S_T_PERMISSION"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 99999999
 START WITH 23
 NOCACHE ;

-- ----------------------------
-- Sequence structure for S_T_ROLE
-- ----------------------------
DROP SEQUENCE "WTGK"."S_T_ROLE";
CREATE SEQUENCE "WTGK"."S_T_ROLE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 99999999
 START WITH 3
 NOCACHE ;

-- ----------------------------
-- Sequence structure for S_T_ROLE_PERMISSION
-- ----------------------------
DROP SEQUENCE "WTGK"."S_T_ROLE_PERMISSION";
CREATE SEQUENCE "WTGK"."S_T_ROLE_PERMISSION"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 99999999
 START WITH 51
 NOCACHE ;

-- ----------------------------
-- Sequence structure for S_T_USER
-- ----------------------------
DROP SEQUENCE "WTGK"."S_T_USER";
CREATE SEQUENCE "WTGK"."S_T_USER"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 99999999
 START WITH 66
 NOCACHE ;

-- ----------------------------
-- Sequence structure for S_T_USER_ROLE
-- ----------------------------
DROP SEQUENCE "WTGK"."S_T_USER_ROLE";
CREATE SEQUENCE "WTGK"."S_T_USER_ROLE"
 INCREMENT BY 1
 MINVALUE 1
 MAXVALUE 99999999
 START WITH 8
 NOCACHE ;

-- ----------------------------
-- Indexes structure for table T_FILE_INFO
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_FILE_INFO
-- ----------------------------
ALTER TABLE "WTGK"."T_FILE_INFO" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "WTGK"."T_FILE_INFO" ADD CHECK ("USER_ID" IS NOT NULL);
ALTER TABLE "WTGK"."T_FILE_INFO" ADD CHECK ("FILE_NAME" IS NOT NULL);
ALTER TABLE "WTGK"."T_FILE_INFO" ADD CHECK ("FILE_TYPE" IS NOT NULL);
ALTER TABLE "WTGK"."T_FILE_INFO" ADD CHECK ("FILE_PATH" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_FILE_INFO
-- ----------------------------
ALTER TABLE "WTGK"."T_FILE_INFO" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_PERMISSION
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table T_PERMISSION
-- ----------------------------
ALTER TABLE "WTGK"."T_PERMISSION" ADD PRIMARY KEY ("PERMISSION_ID");

-- ----------------------------
-- Indexes structure for table T_ROLE
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_ROLE
-- ----------------------------
ALTER TABLE "WTGK"."T_ROLE" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE "WTGK"."T_ROLE" ADD CHECK ("NAME" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ROLE
-- ----------------------------
ALTER TABLE "WTGK"."T_ROLE" ADD PRIMARY KEY ("ROLE_ID");

-- ----------------------------
-- Indexes structure for table T_ROLE_PERMISSION
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_ROLE_PERMISSION
-- ----------------------------
ALTER TABLE "WTGK"."T_ROLE_PERMISSION" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "WTGK"."T_ROLE_PERMISSION" ADD CHECK ("ROLE_ID" IS NOT NULL);
ALTER TABLE "WTGK"."T_ROLE_PERMISSION" ADD CHECK ("PERMISSION_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_ROLE_PERMISSION
-- ----------------------------
ALTER TABLE "WTGK"."T_ROLE_PERMISSION" ADD PRIMARY KEY ("ID");

-- ----------------------------
-- Indexes structure for table T_USER
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_USER
-- ----------------------------
ALTER TABLE "WTGK"."T_USER" ADD CHECK ("USER_ID" IS NOT NULL);
ALTER TABLE "WTGK"."T_USER" ADD CHECK ("USER_NAME" IS NOT NULL);
ALTER TABLE "WTGK"."T_USER" ADD CHECK ("PASSWORD" IS NOT NULL);
ALTER TABLE "WTGK"."T_USER" ADD CHECK ("STATUS" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_USER
-- ----------------------------
ALTER TABLE "WTGK"."T_USER" ADD PRIMARY KEY ("USER_ID");

-- ----------------------------
-- Indexes structure for table T_USER_ROLE
-- ----------------------------

-- ----------------------------
-- Checks structure for table T_USER_ROLE
-- ----------------------------
ALTER TABLE "WTGK"."T_USER_ROLE" ADD CHECK ("ID" IS NOT NULL);
ALTER TABLE "WTGK"."T_USER_ROLE" ADD CHECK ("USER_ID" IS NOT NULL);
ALTER TABLE "WTGK"."T_USER_ROLE" ADD CHECK ("ROLE_ID" IS NOT NULL);

-- ----------------------------
-- Primary Key structure for table T_USER_ROLE
-- ----------------------------
ALTER TABLE "WTGK"."T_USER_ROLE" ADD PRIMARY KEY ("ID");
