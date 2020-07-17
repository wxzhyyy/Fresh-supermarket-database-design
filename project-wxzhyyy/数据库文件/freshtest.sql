/*==============================================================*/
/* DBMS name:      MySQL 5.0                                    */
/* Created on:     2020/7/14 20:39:19                           */
/*==============================================================*/


drop table if exists admin;

drop table if exists category;

drop table if exists comd_purc_record;

drop table if exists commodity;

drop table if exists commodity_evaluation;

drop table if exists commodity_menu;

drop table if exists commodity_order;

drop table if exists commodity_purchase;

drop table if exists coupon;

drop table if exists disc_ass;

drop table if exists full_discount;

drop table if exists menu;

drop table if exists order_details;

drop table if exists promotion;

drop table if exists shipping_address;

drop table if exists user;

drop table if exists user_coupon;

/*==============================================================*/
/* Table: admin                                                 */
/*==============================================================*/
create table admin
(
   admin_id             varchar(20) not null,
   admin_name           varchar(50),
   admin_pwd            varchar(40),
   primary key (admin_id)
);

/*==============================================================*/
/* Table: category                                              */
/*==============================================================*/
create table category
(
   cat_id               varchar(20) not null,
   cat_name             varchar(50),
   cat_describe         varchar(1024),
   primary key (cat_id)
);

/*==============================================================*/
/* Table: comd_purc_record                                      */
/*==============================================================*/
create table comd_purc_record
(
   purc_id              varchar(20) not null,
   comd_id              varchar(20) not null,
   purc_quantity        int,
   purc_status          varchar(20),
   primary key (purc_id, comd_id)
);

/*==============================================================*/
/* Table: commodity                                             */
/*==============================================================*/
create table commodity
(
   comd_id              varchar(20) not null,
   cat_id               varchar(20),
   promotion_id         varchar(50),
   comd_name            varchar(50),
   comd_price           float,
   comd_vip_price       float,
   comd_quantity        int,
   comd_specification   varchar(1024),
   comd_details         varchar(1024),
   primary key (comd_id)
);

/*==============================================================*/
/* Table: commodity_evaluation                                  */
/*==============================================================*/
create table commodity_evaluation
(
   comd_id              varchar(20) not null,
   user_id              varchar(20) not null,
   eval_content         varchar(1024),
   eval_time            datetime,
   eval_star            int,
   eval_pic             longblob,
   primary key (comd_id, user_id)
);

/*==============================================================*/
/* Table: commodity_menu                                        */
/*==============================================================*/
create table commodity_menu
(
   comd_id              varchar(20) not null,
   menu_id              varchar(20) not null,
   commodity_menu_describe varchar(1024),
   primary key (comd_id, menu_id)
);

/*==============================================================*/
/* Table: commodity_order                                       */
/*==============================================================*/
create table commodity_order
(
   order_id             varchar(20) not null,
   user_id              varchar(20),
   original_price       float,
   actual_price         float,
   coupon_id            varchar(20),
   required_delivery_time datetime,
   addr_id              varchar(20),
   order_status         varchar(20),
   primary key (order_id)
);

/*==============================================================*/
/* Table: commodity_purchase                                    */
/*==============================================================*/
create table commodity_purchase
(
   purc_id              varchar(20) not null,
   admin_id             varchar(20),
   comd_id              varchar(20),
   purc_status          varchar(40),
   primary key (purc_id)
);

/*==============================================================*/
/* Table: coupon                                                */
/*==============================================================*/
create table coupon
(
   coupon_id            varchar(20) not null,
   coupon_content       varchar(1024),
   coupon_fit_money     float,
   coupon_price         float,
   coupon_start_time    datetime,
   coupon_end_time      datetime,
   primary key (coupon_id)
);

/*==============================================================*/
/* Table: disc_ass                                              */
/*==============================================================*/
create table disc_ass
(
   comd_id              varchar(20) not null,
   disc_id              varchar(50) not null,
   disc_start_time      datetime,
   disc_end_time        datetime,
   primary key (comd_id, disc_id)
);

/*==============================================================*/
/* Table: full_discount                                         */
/*==============================================================*/
create table full_discount
(
   disc_id              varchar(50) not null,
   disc_content         varchar(1024),
   disc_fitnumber       int,
   disc_discount        float,
   disc_start_time      datetime,
   disc_end_time        datetime,
   primary key (disc_id)
);

/*==============================================================*/
/* Table: menu                                                  */
/*==============================================================*/
create table menu
(
   menu_id              varchar(20) not null,
   menu_name            varchar(50),
   menu_material        varchar(1024),
   menu_step            varchar(1024),
   menu_pic             longblob,
   primary key (menu_id)
);

/*==============================================================*/
/* Table: order_details                                         */
/*==============================================================*/
create table order_details
(
   order_id             varchar(20) not null,
   comd_id              varchar(20) not null,
   order_quantity       int,
   order_price          float,
   order_discount       float,
   disc_id              varchar(20),
   primary key (order_id, comd_id)
);

/*==============================================================*/
/* Table: promotion                                             */
/*==============================================================*/
create table promotion
(
   promotion_id         varchar(50) not null,
   comd_id              varchar(200),
   prom_price           float,
   prom_quantity        int,
   prom_start_time      datetime,
   prom_end_time        datetime,
   primary key (promotion_id)
);

/*==============================================================*/
/* Table: shipping_address                                      */
/*==============================================================*/
create table shipping_address
(
   addr_id              varchar(20) not null,
   user_id              varchar(20),
   province             varchar(200),
   city                 varchar(200),
   cell                 varchar(200),
   address              varchar(200),
   linkman              varchar(50),
   phone                varchar(50),
   primary key (addr_id)
);

/*==============================================================*/
/* Table: user                                                  */
/*==============================================================*/
create table user
(
   user_id              varchar(20) not null,
   user_name            varchar(50),
   user_sex             char(1),
   user_pwd             varchar(40),
   user_vip             char(1),
   user_vip_endtime     datetime,
   user_phone           char(11),
   user_email           varchar(50),
   user_city            varchar(50),
   user_regtime         datetime,
   primary key (user_id)
);

/*==============================================================*/
/* Table: user_coupon                                           */
/*==============================================================*/
create table user_coupon
(
   user_id              varchar(20) not null,
   coupon_id            varchar(20) not null,
   primary key (user_id, coupon_id)
);

alter table comd_purc_record add constraint FK_comd_purc_record foreign key (purc_id)
      references commodity_purchase (purc_id) on delete restrict on update restrict;

alter table comd_purc_record add constraint FK_comd_purc_record2 foreign key (comd_id)
      references commodity (comd_id) on delete restrict on update restrict;

alter table commodity add constraint FK_classify foreign key (cat_id)
      references category (cat_id) on delete restrict on update restrict;

alter table commodity add constraint FK_comd_promotion foreign key (promotion_id)
      references promotion (promotion_id) on delete restrict on update restrict;

alter table commodity_evaluation add constraint FK_commodity_evaluation foreign key (comd_id)
      references commodity (comd_id) on delete restrict on update restrict;

alter table commodity_evaluation add constraint FK_commodity_evaluation2 foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table commodity_menu add constraint FK_commodity_menu foreign key (comd_id)
      references commodity (comd_id) on delete restrict on update restrict;

alter table commodity_menu add constraint FK_commodity_menu2 foreign key (menu_id)
      references menu (menu_id) on delete restrict on update restrict;

alter table commodity_order add constraint FK_buy foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table commodity_purchase add constraint FK_work foreign key (admin_id)
      references admin (admin_id) on delete restrict on update restrict;

alter table disc_ass add constraint FK_disc_ass foreign key (comd_id)
      references commodity (comd_id) on delete restrict on update restrict;

alter table disc_ass add constraint FK_disc_ass2 foreign key (disc_id)
      references full_discount (disc_id) on delete restrict on update restrict;

alter table order_details add constraint FK_order_details foreign key (order_id)
      references commodity_order (order_id) on delete restrict on update restrict;

alter table order_details add constraint FK_order_details2 foreign key (comd_id)
      references commodity (comd_id) on delete restrict on update restrict;

alter table shipping_address add constraint FK_uesr_addr foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table user_coupon add constraint FK_user_coupon foreign key (user_id)
      references user (user_id) on delete restrict on update restrict;

alter table user_coupon add constraint FK_user_coupon2 foreign key (coupon_id)
      references coupon (coupon_id) on delete restrict on update restrict;

