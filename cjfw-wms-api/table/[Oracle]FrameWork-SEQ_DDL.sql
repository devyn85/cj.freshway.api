
-- 공지사항 Sequence
create sequence SQ_BS_BBS_01
    minvalue 1
    maxvalue 9223372036854775807
    start with 1
    increment by 1
    cache 20;

-- 파일 Sequence
create sequence SEQ_CF_ATTCFILE_GRP_01
    minvalue 1
    maxvalue 9223372036854775807
    start with 1
    increment by 1
    cache 20;

-- Canal Frame 에러로그 Sequence
create sequence SEQ_CF_EXN_LOG_EXN_NO
    minvalue 1
    maxvalue 9999999999999999999999999999
    start with 1
    increment by 1
    cache 100;