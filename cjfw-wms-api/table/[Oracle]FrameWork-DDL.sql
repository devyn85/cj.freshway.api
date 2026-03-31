-- Create table
create table TB_BS_BBS
(
  bbs_seq           NUMBER not null,
  bbs_tp_cd         VARCHAR2(10) not null,
  bbs_scp_cd        VARCHAR2(2) not null,
  from_dt           VARCHAR2(8) not null,
  thru_dt           VARCHAR2(8) not null,
  pop_yn            VARCHAR2(1) not null,
  top_yn            VARCHAR2(1) not null,
  bbs_title         VARCHAR2(200) not null,
  bbs_note          CLOB not null,
  attch_file_grp_no NUMBER,
  vw_yn             VARCHAR2(1),
  vw_cnt            NUMBER default 0 not null,
  reg_id            VARCHAR2(24) not null,
  reg_dt            DATE default SYSDATE not null,
  mod_id            VARCHAR2(24) not null,
  mod_dt            DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_BS_BBS
  is '공지사항';
-- Add comments to the columns 
comment on column TB_BS_BBS.bbs_seq
  is '공지사항번호';
comment on column TB_BS_BBS.bbs_tp_cd
  is '공지사항 게시 분류코드';
comment on column TB_BS_BBS.bbs_scp_cd
  is '공지대상코드';
comment on column TB_BS_BBS.from_dt
  is '공지시작일자';
comment on column TB_BS_BBS.thru_dt
  is '공지종료일자';
comment on column TB_BS_BBS.pop_yn
  is '팝업표시여부';
comment on column TB_BS_BBS.top_yn
  is '최상위표시여부';
comment on column TB_BS_BBS.bbs_title
  is '제목';
comment on column TB_BS_BBS.bbs_note
  is '내용';
comment on column TB_BS_BBS.attch_file_grp_no
  is '첨부파일그룹번호';
comment on column TB_BS_BBS.vw_yn
  is '의견유무';
comment on column TB_BS_BBS.vw_cnt
  is '조회수';
comment on column TB_BS_BBS.reg_id
  is '등록자ID';
comment on column TB_BS_BBS.reg_dt
  is '등록일시';
comment on column TB_BS_BBS.mod_id
  is '수정자ID';
comment on column TB_BS_BBS.mod_dt
  is '수정일시';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_BS_BBS
  add constraint PK_TB_BS_BBS primary key (BBS_SEQ)
  using index ;
  
-- Create/Recreate indexes 
create index IX_TB_BS_BBS_01 on TB_BS_BBS (FROM_DT, THRU_DT, VW_YN);
  


-- Create table
create table TB_CM_CD_DTL
(
  com_grp_cd   VARCHAR2(20) not null,
  com_cd       VARCHAR2(10) not null,
  cd_nm        VARCHAR2(500),
  cd_desc      VARCHAR2(500),
  use_yn       VARCHAR2(1),
  sort_no      NUMBER(5),
  rsv_str1_val VARCHAR2(500),
  rsv_str2_val VARCHAR2(500),
  rsv_str3_val VARCHAR2(500),
  rsv_str4_val VARCHAR2(500),
  rsv_str5_val VARCHAR2(500),
  rsv_str6_val VARCHAR2(500),
  rsv_str7_val VARCHAR2(500),
  rsv_str8_val VARCHAR2(500),
  rsv_num1_val NUMBER,
  rsv_num2_val NUMBER,
  rsv_num3_val NUMBER,
  rsv_num4_val NUMBER,
  rsv_num5_val NUMBER,
  rsv_num6_val NUMBER,
  rsv_num7_val NUMBER,
  rsv_num8_val NUMBER,
  reg_id       VARCHAR2(24) not null,
  reg_dt       DATE default SYSDATE not null,
  mod_id       VARCHAR2(24) not null,
  mod_dt       DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CM_CD_DTL
  is '공통코드상세';
-- Add comments to the columns 
comment on column TB_CM_CD_DTL.com_grp_cd
  is '공통그룹코드';
comment on column TB_CM_CD_DTL.com_cd
  is '공통코드';
comment on column TB_CM_CD_DTL.cd_nm
  is '코드명';
comment on column TB_CM_CD_DTL.cd_desc
  is '코드설명';
comment on column TB_CM_CD_DTL.use_yn
  is '사용여부';
comment on column TB_CM_CD_DTL.sort_no
  is '정렬순서';
comment on column TB_CM_CD_DTL.rsv_str1_val
  is '예약_문자1';
comment on column TB_CM_CD_DTL.rsv_str2_val
  is '예약_문자2';
comment on column TB_CM_CD_DTL.rsv_str3_val
  is '예약_문자3';
comment on column TB_CM_CD_DTL.rsv_str4_val
  is '예약_문자4';
comment on column TB_CM_CD_DTL.rsv_str5_val
  is '예약_문자5';
comment on column TB_CM_CD_DTL.rsv_str6_val
  is '예약_문자6';
comment on column TB_CM_CD_DTL.rsv_str7_val
  is '예약_문자7';
comment on column TB_CM_CD_DTL.rsv_str8_val
  is '예약_문자8';
comment on column TB_CM_CD_DTL.rsv_num1_val
  is '예약_숫자1';
comment on column TB_CM_CD_DTL.rsv_num2_val
  is '예약_숫자2';
comment on column TB_CM_CD_DTL.rsv_num3_val
  is '예약_숫자3';
comment on column TB_CM_CD_DTL.rsv_num4_val
  is '예약_숫자4';
comment on column TB_CM_CD_DTL.rsv_num5_val
  is '예약_숫자5';
comment on column TB_CM_CD_DTL.rsv_num6_val
  is '예약_숫자6';
comment on column TB_CM_CD_DTL.rsv_num7_val
  is '예약_숫자7';
comment on column TB_CM_CD_DTL.rsv_num8_val
  is '예약_숫자8';
comment on column TB_CM_CD_DTL.reg_id
  is '등록자ID';
comment on column TB_CM_CD_DTL.reg_dt
  is '등록일시';
comment on column TB_CM_CD_DTL.mod_id
  is '수정자ID';
comment on column TB_CM_CD_DTL.mod_dt
  is '수정일시';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CM_CD_DTL
  add constraint PK_TB_CM_CD_DTL primary key (COM_GRP_CD, COM_CD)
  using index ;
  
-- Create/Recreate indexes 
create unique index UX_TB_CM_CD_DTL_01 on TB_CM_CD_DTL (COM_GRP_CD, COM_CD, CD_NM);
  


-- Create table
create table TB_CM_CD_DTL_I18N
(
  com_grp_cd  VARCHAR2(20) not null,
  com_cd      VARCHAR2(10) not null,
  language_cd VARCHAR2(5) not null,
  cd_nm       VARCHAR2(500),
  use_yn      VARCHAR2(1),
  reg_id      VARCHAR2(24) not null,
  reg_dt      DATE default SYSDATE not null,
  mod_id      VARCHAR2(24) not null,
  mod_dt      DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CM_CD_DTL_I18N
  is '공통코드 다국어';
-- Add comments to the columns 
comment on column TB_CM_CD_DTL_I18N.com_grp_cd
  is '공통그룹코드';
comment on column TB_CM_CD_DTL_I18N.com_cd
  is '공통코드';
comment on column TB_CM_CD_DTL_I18N.language_cd
  is '다국어코드';
comment on column TB_CM_CD_DTL_I18N.cd_nm
  is '코드명';
comment on column TB_CM_CD_DTL_I18N.use_yn
  is '사용여부';
comment on column TB_CM_CD_DTL_I18N.reg_id
  is '등록자ID';
comment on column TB_CM_CD_DTL_I18N.reg_dt
  is '등록일시';
comment on column TB_CM_CD_DTL_I18N.mod_id
  is '수정자ID';
comment on column TB_CM_CD_DTL_I18N.mod_dt
  is '수정일시';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CM_CD_DTL_I18N
  add constraint PK_TB_CM_CD_DTL_I18N primary key (COM_GRP_CD, COM_CD, LANGUAGE_CD)
  using index ;
  


-- Create table
create table TB_CM_CD_GRP
(
  com_grp_cd   VARCHAR2(20) not null,
  grp_cd_nm    VARCHAR2(500),
  grp_cd_desc  VARCHAR2(500),
  use_yn       VARCHAR2(1),
  rsv_str1_val VARCHAR2(500),
  rsv_str2_val VARCHAR2(500),
  rsv_str3_val VARCHAR2(500),
  rsv_str4_val VARCHAR2(500),
  rsv_str5_val VARCHAR2(500),
  rsv_str6_val VARCHAR2(500),
  rsv_str7_val VARCHAR2(500),
  rsv_str8_val VARCHAR2(500),
  rsv_num1_val VARCHAR2(500),
  rsv_num2_val VARCHAR2(500),
  rsv_num3_val VARCHAR2(500),
  rsv_num4_val VARCHAR2(500),
  rsv_num5_val VARCHAR2(500),
  rsv_num6_val VARCHAR2(500),
  rsv_num7_val VARCHAR2(500),
  rsv_num8_val VARCHAR2(500),
  reg_id       VARCHAR2(24) not null,
  reg_dt       DATE default SYSDATE not null,
  mod_id       VARCHAR2(24) not null,
  mod_dt       DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CM_CD_GRP
  is '공통코드그룹';
-- Add comments to the columns 
comment on column TB_CM_CD_GRP.com_grp_cd
  is '공통그룹코드';
comment on column TB_CM_CD_GRP.grp_cd_nm
  is '그룹코드명';
comment on column TB_CM_CD_GRP.grp_cd_desc
  is '그룹코드설명';
comment on column TB_CM_CD_GRP.use_yn
  is '사용여부';
comment on column TB_CM_CD_GRP.rsv_str1_val
  is '예약_문자1_설명';
comment on column TB_CM_CD_GRP.rsv_str2_val
  is '예약_문자2_설명';
comment on column TB_CM_CD_GRP.rsv_str3_val
  is '예약_문자3_설명';
comment on column TB_CM_CD_GRP.rsv_str4_val
  is '예약_문자4_설명';
comment on column TB_CM_CD_GRP.rsv_str5_val
  is '예약_문자5_설명';
comment on column TB_CM_CD_GRP.rsv_str6_val
  is '예약_문자6_설명';
comment on column TB_CM_CD_GRP.rsv_str7_val
  is '예약_문자7_설명';
comment on column TB_CM_CD_GRP.rsv_str8_val
  is '예약_문자8_설명';
comment on column TB_CM_CD_GRP.rsv_num1_val
  is '예약_숫자1_설명';
comment on column TB_CM_CD_GRP.rsv_num2_val
  is '예약_숫자2_설명';
comment on column TB_CM_CD_GRP.rsv_num3_val
  is '예약_숫자3_설명';
comment on column TB_CM_CD_GRP.rsv_num4_val
  is '예약_숫자4_설명';
comment on column TB_CM_CD_GRP.rsv_num5_val
  is '예약_숫자5_설명';
comment on column TB_CM_CD_GRP.rsv_num6_val
  is '예약_숫자6_설명';
comment on column TB_CM_CD_GRP.rsv_num7_val
  is '예약_숫자7_설명';
comment on column TB_CM_CD_GRP.rsv_num8_val
  is '예약_숫자8_설명';
comment on column TB_CM_CD_GRP.reg_id
  is '등록자ID';
comment on column TB_CM_CD_GRP.reg_dt
  is '등록일시';
comment on column TB_CM_CD_GRP.mod_id
  is '수정자ID';
comment on column TB_CM_CD_GRP.mod_dt
  is '수정일시';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CM_CD_GRP
  add constraint PK_TB_CM_CD_GRP primary key (COM_GRP_CD)
  using index ;
  


-- Create table
create table TB_CM_VRFCTN_CD
(
  user_id   VARCHAR2(20) not null,
  email     VARCHAR2(100) not null,
  vrfctn_cd VARCHAR2(20),
  req_dt    DATE not null
);

-- Add comments to the table 
comment on table TB_CM_VRFCTN_CD
  is '인증코드테이블';
-- Add comments to the columns 
comment on column TB_CM_VRFCTN_CD.user_id
  is '로그인ID';
comment on column TB_CM_VRFCTN_CD.email
  is '이메일';
comment on column TB_CM_VRFCTN_CD.vrfctn_cd
  is '인증코드';
comment on column TB_CM_VRFCTN_CD.req_dt
  is '요청일';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CM_VRFCTN_CD
  add constraint PK_TB_CM_VERIFICATIONCODE primary key (USER_ID, EMAIL, REQ_DT)
  using index ;
  


-- Create table
create table TB_CF_ALLOWED_IP
(
  user_id VARCHAR2(24) not null,
  ip_addr VARCHAR2(16) not null,
  reason  VARCHAR2(100),
  reg_id  VARCHAR2(24) not null,
  reg_dt  DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_ALLOWED_IP
  is '사용자별_접속허용IP';
-- Add comments to the columns 
comment on column TB_CF_ALLOWED_IP.user_id
  is '사용자ID';
comment on column TB_CF_ALLOWED_IP.ip_addr
  is '클라이언트 IP';
comment on column TB_CF_ALLOWED_IP.reason
  is '사유';
comment on column TB_CF_ALLOWED_IP.reg_id
  is '등록자ID';
comment on column TB_CF_ALLOWED_IP.reg_dt
  is '등록일자(''YYYY-MM-DD'')';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_ALLOWED_IP
  add constraint PK_TB_CF_ALLOWED_IP primary key (USER_ID, IP_ADDR)
  using index ;
  


-- Create table
create table TB_CF_ATTCHFILE
(
  attch_file_no     NUMBER not null,
  attch_file_nm     VARCHAR2(4000),
  attch_file_ext_nm VARCHAR2(20),
  attch_file_sz     NUMBER(15,3),
  save_path_nm1     VARCHAR2(100),
  save_path_nm2     VARCHAR2(100),
  attch_file_cmnt   VARCHAR2(300),
  attch_file_grp_no NUMBER not null,
  file_grp_nm       VARCHAR2(30),
  sort_no           NUMBER(4),
  file_tp_cd        VARCHAR2(2),
  use_yn            VARCHAR2(1) default '0' not null,
  reg_id            VARCHAR2(24) not null,
  reg_dt            DATE default SYSDATE not null,
  mod_id            VARCHAR2(24) not null,
  mod_dt            DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_ATTCHFILE
  is '첨부파일';
-- Add comments to the columns 
comment on column TB_CF_ATTCHFILE.attch_file_no
  is '첨부파일번호';
comment on column TB_CF_ATTCHFILE.attch_file_nm
  is '첨부파일명';
comment on column TB_CF_ATTCHFILE.attch_file_ext_nm
  is '첨부파일확장자명';
comment on column TB_CF_ATTCHFILE.attch_file_sz
  is '첨부파일크기';
comment on column TB_CF_ATTCHFILE.save_path_nm1
  is '저장경로명1';
comment on column TB_CF_ATTCHFILE.save_path_nm2
  is '저장경로명2';
comment on column TB_CF_ATTCHFILE.attch_file_cmnt
  is '첨부파일설명';
comment on column TB_CF_ATTCHFILE.attch_file_grp_no
  is '첨부파일그룹번호';
comment on column TB_CF_ATTCHFILE.file_grp_nm
  is '파일그룹명';
comment on column TB_CF_ATTCHFILE.sort_no
  is '정렬순서';
comment on column TB_CF_ATTCHFILE.file_tp_cd
  is '첨부파일타입코드';
comment on column TB_CF_ATTCHFILE.use_yn
  is '사용여부';
comment on column TB_CF_ATTCHFILE.reg_id
  is '등록자ID';
comment on column TB_CF_ATTCHFILE.reg_dt
  is '등록일시';
comment on column TB_CF_ATTCHFILE.mod_id
  is '수정자ID';
comment on column TB_CF_ATTCHFILE.mod_dt
  is '수정일시';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_ATTCHFILE
  add constraint PK_TB_CF_ATTCFILE primary key (ATTCH_FILE_GRP_NO, ATTCH_FILE_NO)
  using index ;
  
-- Create/Recreate indexes 
create index IX_TB_CF_ATTCFILE_01 on TB_CF_ATTCHFILE (ATTCH_FILE_GRP_NO);
  
create index IX_TB_CF_ATTCFILE_02 on TB_CF_ATTCHFILE (ATTCH_FILE_NM, USE_YN, ATTCH_FILE_NO);
  


-- Create table
create table TB_CF_AUTH
(
  user_id   VARCHAR2(24) not null,
  authority VARCHAR2(64) not null,
  reg_id    VARCHAR2(24) not null,
  reg_dt    DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_AUTH
  is '사용자 권한';
-- Add comments to the columns 
comment on column TB_CF_AUTH.user_id
  is '사용자ID';
comment on column TB_CF_AUTH.authority
  is '권한';
comment on column TB_CF_AUTH.reg_id
  is '등록자ID';
comment on column TB_CF_AUTH.reg_dt
  is '등록일자';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_AUTH
  add constraint PK_TB_CF_AUTHORITIES primary key (USER_ID, AUTHORITY)
  using index ;
  


-- Create table
create table TB_CF_AUTH_CHG_LOG
(
  user_id   VARCHAR2(24) not null,
  authority VARCHAR2(64) not null,
  content   VARCHAR2(50),
  reg_id    VARCHAR2(24) not null,
  reg_dt    DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_AUTH_CHG_LOG
  is '사용자 권한 변경 로그';
-- Add comments to the columns 
comment on column TB_CF_AUTH_CHG_LOG.user_id
  is '사용자ID';
comment on column TB_CF_AUTH_CHG_LOG.authority
  is '권한';
comment on column TB_CF_AUTH_CHG_LOG.content
  is '권한 변경(ADD/DELETE)';
comment on column TB_CF_AUTH_CHG_LOG.reg_id
  is '등록자ID';
comment on column TB_CF_AUTH_CHG_LOG.reg_dt
  is '등록일자';


-- Create table
create table TB_CF_EXN_LOG
(
    exn_no     NUMBER not null,
    excpt_cnts VARCHAR2(4000) not null,
    occr_dy    VARCHAR2(32) not null,
    clnt_addr  VARCHAR2(30) not null,
    svr_addr   VARCHAR2(30) not null,
    call_uri   VARCHAR2(1000) not null
);
-- Add comments to the table
comment on table TB_CF_EXN_LOG
  is '시스템 예외로그';
-- Add comments to the columns
comment on column TB_CF_EXN_LOG.exn_no
  is '일련번호';
comment on column TB_CF_EXN_LOG.excpt_cnts
  is '예외내용';
comment on column TB_CF_EXN_LOG.occr_dy
  is '발생일자';
comment on column TB_CF_EXN_LOG.clnt_addr
  is '클라이언트IP';
comment on column TB_CF_EXN_LOG.svr_addr
  is '서버IP';
comment on column TB_CF_EXN_LOG.call_uri
  is '호출URL';
-- Create/Recreate primary, unique and foreign key constraints
alter table TB_CF_EXN_LOG
    add constraint PK_TB_CF_EXN_LOG primary key (EXN_NO)
    using index
;

-- Create table
create table TB_CF_LOGIN_LOG
(
  user_id  VARCHAR2(24) not null,
  log_dtm  VARCHAR2(20) not null,
  log_type CHAR(1) not null,
  ip_addr  VARCHAR2(16) not null
);

-- Add comments to the table 
comment on table TB_CF_LOGIN_LOG
  is '사용자 로그인 기록';
-- Add comments to the columns 
comment on column TB_CF_LOGIN_LOG.user_id
  is '사용자ID';
comment on column TB_CF_LOGIN_LOG.log_dtm
  is '로그기록일시(예:2018-03-06 09:00:25)';
comment on column TB_CF_LOGIN_LOG.log_type
  is '로그타입(1:로그인,0:로그아웃, 2:로그인실패)';
comment on column TB_CF_LOGIN_LOG.ip_addr
  is '클라이언트 IP';
-- Create/Recreate indexes 
create index IX_TB_CF_LOGIN_LOG_01 on TB_CF_LOGIN_LOG (USER_ID);
  


-- Create table
create table TB_CF_MENU
(
  menu_id       VARCHAR2(64) not null,
  menu_nm       VARCHAR2(64) not null,
  menu_url      VARCHAR2(256),
  description   VARCHAR2(128),
  sort_order    NUMBER(10) not null,
  upper_menu_id VARCHAR2(64),
  is_popup      CHAR(1) default '0' not null,
  ref_menu_id   VARCHAR2(64),
  menu_yn       CHAR(1) default '1' not null,
  use_yn        CHAR(1) default '1' not null,
  reg_id        VARCHAR2(24) not null,
  reg_dt        DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_MENU
  is '메뉴';
-- Add comments to the columns 
comment on column TB_CF_MENU.menu_id
  is '메뉴ID';
comment on column TB_CF_MENU.menu_nm
  is '메뉴명';
comment on column TB_CF_MENU.menu_url
  is '자원경로';
comment on column TB_CF_MENU.description
  is '설명';
comment on column TB_CF_MENU.sort_order
  is '정렬순서';
comment on column TB_CF_MENU.upper_menu_id
  is '상위메뉴ID';
comment on column TB_CF_MENU.is_popup
  is '팝업여부';
comment on column TB_CF_MENU.ref_menu_id
  is '참조메뉴ID';
comment on column TB_CF_MENU.menu_yn
  is '메뉴여부';
comment on column TB_CF_MENU.use_yn
  is '사용여부';
comment on column TB_CF_MENU.reg_id
  is '등록자ID';
comment on column TB_CF_MENU.reg_dt
  is '등록일자';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_MENU
  add constraint PK_TB_CF_MENU primary key (MENU_ID)
  using index ;
  


-- Create table
create table TB_CF_MENU_I18N
(
  menu_id     VARCHAR2(64) not null,
  menu_nm     VARCHAR2(64) not null,
  language_cd VARCHAR2(5) not null,
  reg_id      VARCHAR2(24) not null,
  reg_dt      DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_MENU_I18N
  is '다국어 메뉴';
-- Add comments to the columns 
comment on column TB_CF_MENU_I18N.menu_id
  is '메뉴ID';
comment on column TB_CF_MENU_I18N.menu_nm
  is '메뉴명';
comment on column TB_CF_MENU_I18N.language_cd
  is '언어코드_NEW';
comment on column TB_CF_MENU_I18N.reg_id
  is '등록자ID';
comment on column TB_CF_MENU_I18N.reg_dt
  is '등록일자';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_MENU_I18N
  add constraint PK_TB_CF_MENU_I18N primary key (MENU_ID, LANGUAGE_CD)
  using index ;
  


-- Create table
create table TB_CF_MENU_ROLE
(
  menu_id   VARCHAR2(64) not null,
  authority VARCHAR2(64) not null,
  search_yn CHAR(1) default '0' not null,
  new_yn    CHAR(1) default '0' not null,
  delete_yn CHAR(1) default '0' not null,
  save_yn   CHAR(1) default '0' not null,
  btn1_yn   CHAR(1) default '0' not null,
  btn2_yn   CHAR(1) default '0' not null,
  btn3_yn   CHAR(1) default '0' not null,
  btn4_yn   CHAR(1) default '0' not null,
  btn5_yn   CHAR(1) default '0' not null,
  btn6_yn   CHAR(1) default '0' not null,
  btn7_yn   CHAR(1) default '0' not null,
  btn8_yn   CHAR(1) default '0' not null,
  btn9_yn   CHAR(1) default '0' not null,
  btn10_yn  CHAR(1) default '0' not null,
  print_yn  CHAR(1) default '0' not null,
  excel_yn  CHAR(1) default '0' not null,
  menu_yn   CHAR(1) default '1' not null,
  use_yn    CHAR(1) default '1' not null,
  reg_id    VARCHAR2(24) not null,
  reg_dt    DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_MENU_ROLE
  is '권한별 메뉴';
-- Add comments to the columns 
comment on column TB_CF_MENU_ROLE.menu_id
  is '메뉴ID';
comment on column TB_CF_MENU_ROLE.authority
  is '권한';
comment on column TB_CF_MENU_ROLE.search_yn
  is '조회버튼사용여부';
comment on column TB_CF_MENU_ROLE.new_yn
  is '신규버튼사용여부';
comment on column TB_CF_MENU_ROLE.delete_yn
  is '삭제버튼사용여부';
comment on column TB_CF_MENU_ROLE.save_yn
  is '저장버튼사용여부';
comment on column TB_CF_MENU_ROLE.btn1_yn
  is '사용자버튼1사용여부';
comment on column TB_CF_MENU_ROLE.btn2_yn
  is '사용자버튼2사용여부';
comment on column TB_CF_MENU_ROLE.btn3_yn
  is '사용자버튼3사용여부';
comment on column TB_CF_MENU_ROLE.btn4_yn
  is '사용자버튼4사용여부';
comment on column TB_CF_MENU_ROLE.btn5_yn
  is '사용자버튼5사용여부';
comment on column TB_CF_MENU_ROLE.btn6_yn
  is '사용자버튼6사용여부';
comment on column TB_CF_MENU_ROLE.btn7_yn
  is '사용자버튼7사용여부';
comment on column TB_CF_MENU_ROLE.btn8_yn
  is '사용자버튼8사용여부';
comment on column TB_CF_MENU_ROLE.btn9_yn
  is '사용자버튼9사용여부';
comment on column TB_CF_MENU_ROLE.btn10_yn
  is '사용자버튼10사용여부';
comment on column TB_CF_MENU_ROLE.print_yn
  is '인쇄버튼사용여부';
comment on column TB_CF_MENU_ROLE.excel_yn
  is '엑셀버튼사용여부';
comment on column TB_CF_MENU_ROLE.menu_yn
  is '메뉴여부';
comment on column TB_CF_MENU_ROLE.use_yn
  is '사용여부';
comment on column TB_CF_MENU_ROLE.reg_id
  is '등록자ID';
comment on column TB_CF_MENU_ROLE.reg_dt
  is '등록일자';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_MENU_ROLE
  add constraint PK_TB_CF_MENU_ROLE primary key (MENU_ID, AUTHORITY)
  using index ;
  
-- Create/Recreate indexes 
create index IX_TB_CF_MENU_ROLE_01 on TB_CF_MENU_ROLE (AUTHORITY, USE_YN, MENU_YN);
  

-- Create table
create table TB_CF_PWD_HST
(
  user_id      VARCHAR2(24) not null,
  user_pwd_seq VARCHAR2(2) not null,
  user_pwd     VARCHAR2(256) not null,
  reg_id       VARCHAR2(24) not null,
  reg_dt       DATE default SYSDATE not null,
  mod_id       VARCHAR2(24) not null,
  mod_dt       DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_PWD_HST
  is '비밀번호 이력';
-- Add comments to the columns 
comment on column TB_CF_PWD_HST.user_id
  is '사용자ID';
comment on column TB_CF_PWD_HST.user_pwd_seq
  is '비밀번호 순번';
comment on column TB_CF_PWD_HST.user_pwd
  is '비밀번호';
comment on column TB_CF_PWD_HST.reg_id
  is '등록자ID';
comment on column TB_CF_PWD_HST.reg_dt
  is '등록일시';
comment on column TB_CF_PWD_HST.mod_id
  is '수정자ID';
comment on column TB_CF_PWD_HST.mod_dt
  is '수정일시';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_PWD_HST
  add constraint PK_TB_CF_PWD_HST primary key (USER_ID, USER_PWD_SEQ)
  using index ;
  


-- Create table
create table TB_CF_ROLES
(
  authority   VARCHAR2(64) not null,
  role_nm     VARCHAR2(64) not null,
  description VARCHAR2(128),
  reg_id      VARCHAR2(24) not null,
  reg_dt      DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_ROLES
  is '권한 그룹';
-- Add comments to the columns 
comment on column TB_CF_ROLES.authority
  is '권한';
comment on column TB_CF_ROLES.role_nm
  is '역할명';
comment on column TB_CF_ROLES.description
  is '설명';
comment on column TB_CF_ROLES.reg_id
  is '등록자ID';
comment on column TB_CF_ROLES.reg_dt
  is '등록일자';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_ROLES
  add constraint PK_TB_CF_ROLES primary key (AUTHORITY)
  using index ;
  


-- Create table
create table TB_CF_SECURITY_RULES
(
  user_id           VARCHAR2(24) not null,
  final_in_dtm      DATE,
  final_out_dtm     DATE,
  pwd_chg_dt        CHAR(10),
  final_ip_addr     VARCHAR2(16),
  fail_in_cnt       NUMBER(5) default 0 not null,
  fail_tot_cnt      NUMBER(5) default 0 not null,
  final_fail_in_dtm DATE,
  tmp_pwd_yn        CHAR(1),
  allow_all_ip_yn   CHAR(1)
);

-- Add comments to the table 
comment on table TB_CF_SECURITY_RULES
  is '보안요구사항 룰셋 정의';
-- Add comments to the columns 
comment on column TB_CF_SECURITY_RULES.user_id
  is '사용자ID';
comment on column TB_CF_SECURITY_RULES.final_in_dtm
  is '최종로그인일시(''YYYY-MM-DD HH:MM:SS'')';
comment on column TB_CF_SECURITY_RULES.final_out_dtm
  is '최종로그아웃일시(''YYYY-MM-DD HH:MM:SS'')';
comment on column TB_CF_SECURITY_RULES.pwd_chg_dt
  is '최종비밀번호갱신일자(''YYYY-MM-DD'')';
comment on column TB_CF_SECURITY_RULES.final_ip_addr
  is '최종접속IP';
comment on column TB_CF_SECURITY_RULES.fail_in_cnt
  is '연속로그인실패수';
comment on column TB_CF_SECURITY_RULES.fail_tot_cnt
  is '전체로그인실패수';
comment on column TB_CF_SECURITY_RULES.final_fail_in_dtm
  is '최종로그인실패일시(''YYYY-MM-DD HH:MM:SS'')';
comment on column TB_CF_SECURITY_RULES.tmp_pwd_yn
  is '임시비밀번호발급여부(1:발급, 0:미발급)';
comment on column TB_CF_SECURITY_RULES.allow_all_ip_yn
  is '모든 네트워크 아이피 허용 여부(1:허용, 0:불가)';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_SECURITY_RULES
  add constraint PK_TB_CF_SECURITY_RULES primary key (USER_ID)
  using index ;
  


-- Create table
create table TB_CF_USERS
(
  user_id       VARCHAR2(24) not null,
  user_nm       VARCHAR2(768) not null,
  user_pwd      VARCHAR2(256) not null,
  user_status   CHAR(2) default '01' not null,
  user_enable   CHAR(1) default '1' not null,
  emp_no        VARCHAR2(20),
  mail_addr     VARCHAR2(200),
  com_cd        VARCHAR2(10),
  lang_cd       VARCHAR2(5),
  refresh_token VARCHAR2(1000),
  reg_id        VARCHAR2(24) not null,
  reg_dt        DATE default SYSDATE not null,
  mod_id        VARCHAR2(24) not null,
  mod_dt        DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_USERS
  is '사용자';
-- Add comments to the columns 
comment on column TB_CF_USERS.user_id
  is '사용자ID';
comment on column TB_CF_USERS.user_nm
  is '사용자명';
comment on column TB_CF_USERS.user_pwd
  is '비밀번호';
comment on column TB_CF_USERS.user_status
  is '상태코드(01:정상,02:잠김)';
comment on column TB_CF_USERS.user_enable
  is '계정활성여부, Y/N';
comment on column TB_CF_USERS.emp_no
  is '사원번호';
comment on column TB_CF_USERS.mail_addr
  is '이메일주소(암호화)';
comment on column TB_CF_USERS.com_cd
  is '회사코드';
comment on column TB_CF_USERS.lang_cd
  is '언어코드';
comment on column TB_CF_USERS.refresh_token
  is 'Refresh토큰';
comment on column TB_CF_USERS.reg_id
  is '등록자ID';
comment on column TB_CF_USERS.reg_dt
  is '등록일자';
comment on column TB_CF_USERS.mod_id
  is '수정자ID';
comment on column TB_CF_USERS.mod_dt
  is '수정일자';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_USERS
  add constraint PK_TB_CF_USERS primary key (USER_ID)
  using index ;
  
-- Create/Recreate indexes
create unique index UX_TB_CF_USERS_01 on TB_CF_USERS (EMP_NO);
  


-- Create table
create table TB_CF_USERS_MYMENU
(
  user_id  VARCHAR2(24) not null,
  menu_id  VARCHAR2(16) not null,
  menu_nm  VARCHAR2(64) not null,
  menu_url VARCHAR2(256) not null
);

-- Add comments to the table 
comment on table TB_CF_USERS_MYMENU
  is '즐겨찾는 메뉴';
-- Add comments to the columns 
comment on column TB_CF_USERS_MYMENU.user_id
  is '사용자ID';
comment on column TB_CF_USERS_MYMENU.menu_id
  is '메뉴ID';
comment on column TB_CF_USERS_MYMENU.menu_nm
  is '메뉴명';
comment on column TB_CF_USERS_MYMENU.menu_url
  is '메뉴경로';
-- Create/Recreate primary, unique and foreign key constraints 
alter table TB_CF_USERS_MYMENU
  add constraint PK_TB_CF_USERS_MYMENU primary key (USER_ID, MENU_ID)
  using index ;
  
-- Create/Recreate indexes 
create index IX_TB_CF_USERS_MYMENU_01 on TB_CF_USERS_MYMENU (USER_ID);
  


-- Create table
create table TB_CF_USER_LOG
(
  user_id  VARCHAR2(24) not null,
  log_msg  VARCHAR2(128) not null,
  ip_addr  VARCHAR2(16) not null,
  user_pwd VARCHAR2(256) default 'NA' not null,
  reg_id   VARCHAR2(24) not null,
  reg_dt   DATE default SYSDATE not null
);

-- Add comments to the table 
comment on table TB_CF_USER_LOG
  is '사용자 정보 변경기록';
-- Add comments to the columns 
comment on column TB_CF_USER_LOG.user_id
  is '사용자ID';
comment on column TB_CF_USER_LOG.log_msg
  is '사용자 정보 변경내용';
comment on column TB_CF_USER_LOG.ip_addr
  is '클라이언트 IP';
comment on column TB_CF_USER_LOG.user_pwd
  is '사용자 비밀번호';
comment on column TB_CF_USER_LOG.reg_id
  is '등록자ID';
comment on column TB_CF_USER_LOG.reg_dt
  is '등록일자';
-- Create/Recreate indexes 
create index IX_TB_CF_USER_LOG_01 on TB_CF_USER_LOG (USER_ID);


create table TB_CF_SNIPPET
(	FRONT_TYPE VARCHAR2(30) not null,
     GRID_TYPE VARCHAR2(30) not null,
     DB_TYPE VARCHAR2(30) not null,
     FILE_EXT_NM VARCHAR2(30) not null,
     FILE_TYPE VARCHAR2(30) not null,
     SORT_NO NUMBER(3,0) not null,
     GENERATOR CLOB,
     REMK VARCHAR2(2000),
     REG_ID VARCHAR2(24) not null,
     REG_DTM DATE default SYSDATE not null,
     UPDR_ID VARCHAR2(24) not null,
     UPD_DTM DATE default SYSDATE not null,
     USE_YN VARCHAR2(1)
);

COMMENT ON TABLE TB_CF_SNIPPET IS '스니펫';
COMMENT ON COLUMN TB_CF_SNIPPET.FRONT_TYPE IS '프론트종류';
COMMENT ON COLUMN TB_CF_SNIPPET.GRID_TYPE IS '그리드종류';
COMMENT ON COLUMN TB_CF_SNIPPET.DB_TYPE IS '데이터베이스종류';
COMMENT ON COLUMN TB_CF_SNIPPET.FILE_EXT_NM IS '파일 확장자';
COMMENT ON COLUMN TB_CF_SNIPPET.FILE_TYPE IS '파일종류';
COMMENT ON COLUMN TB_CF_SNIPPET.SORT_NO IS '호출순서';
COMMENT ON COLUMN TB_CF_SNIPPET.GENERATOR IS 'Generator';
COMMENT ON COLUMN TB_CF_SNIPPET.REMK IS '비고';
COMMENT ON COLUMN TB_CF_SNIPPET.REG_ID IS '등록ID';
COMMENT ON COLUMN TB_CF_SNIPPET.REG_DTM IS '등록일시';
COMMENT ON COLUMN TB_CF_SNIPPET.UPDR_ID IS '수정자ID';
COMMENT ON COLUMN TB_CF_SNIPPET.UPD_DTM IS '수정일시';
COMMENT ON COLUMN TB_CF_SNIPPET.USE_YN IS '비고';

-- Create/Recreate primary, unique and foreign key constraints
alter table TB_CF_SNIPPET
    add constraint PK_TB_CF_SNIPPET primary key (FRONT_TYPE, GRID_TYPE, DB_TYPE, FILE_EXT_NM, FILE_TYPE, SORT_NO)
    using index;
