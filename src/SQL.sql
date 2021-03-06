SELECT A.SEQ, A.MYID, A.WDATE, A.RDATE, A.MYCONTENT, A.PHONENUM, A.TITLE, 
				 R.WDATE AS RDWDATE, R.TITLE AS RDTITLE, R.MYCONTENT AS RDMYCONTENT, R.newFILENAME AS RDFILENAME 
					 FROM ADOPTDOG A, RESCUEDDOG R 
					 WHERE A.SEQ=174 AND A.REF = R.SEQ

			
				select * from ADOPTDOG //174;			
				rdfilenmae = rescueddog filename = 1617777580
				
select * from rescueddog where seq=185 //185


DROP TABLE RESCUEDDOG
CASCADE CONSTRAINTS;

SELECT *
FROM ADOPTDOG


CREATE TABLE MYMEMBER 
(
SEQ NUMBER NOT NULL,             --시퀀스
MYID VARCHAR2(50) PRIMARY KEY,   --아이디
PWD VARCHAR2(50) NOT NULL,       --패스워드
EMAIL VARCHAR2(100) NOT NULL,    --패스워드
MYNAME VARCHAR2(50) NOT NULL,    --이름
PHONENUM VARCHAR2(100),          --연락처
MYMANAGER NUMBER NOT NULL        --관리자인지 여부 확인 (0:회원, 1:관리자)
);




CREATE TABLE ABANDONEDDOG
(
SEQ NUMBER NOT NULL,                --시퀀스
MYID VARCHAR2(50) NOT NULL,         --아이디(FK)
PHONENUM NUMBER NOT NULL,           --연락처
ADDRESS VARCHAR2(200) NOT NULL,     --주소
MYCONTENT VARCHAR2(500),            --내용
FILENAME VARCHAR2(100) PRIMARY KEY, 
NEWFILENAME VARCHAR2(100) NOT NULL,          
DANGER NUMBER NOT NULL,             --위험 정도(1-5)
WDATE DATE NOT NULL,                --등록일
DEL NUMBER NOT NULL,                --삭제(0:def, 1:삭제)
REF NUMBER NOT NULL
);





CREATE TABLE RESCUEDDOG
(
SEQ NUMBER NOT NULL,               --시퀀스
WDATE DATE NOT NULL,               --작성일
VCOUNT NUMBER NOT NULL,            --조회수
CONDITION NUMBER NOT NULL,         --입양진행유무  (0:default, 1:입양진행중, 2:입양완료)
TITLE VARCHAR2(80) NOT NULL,      --제목
MYCONTENT VARCHAR2(1200) NOT NULL,  --내용
FILENAME VARCHAR2(100) NOT NULL,   --이미지파일 
DEL NUMBER(10) NOT NULL
);









--커뮤니티 
CREATE TABLE BBS
(
SEQ NUMBER NOT NULL,              --시퀀스
MYID VARCHAR2(50) NOT NULL,       --아이디(FK)
TITLE VARCHAR2(50) NOT NULL,      --제목
MYCONTENT VARCHAR2(500) NOT NULL, --내용
WDATE DATE NOT NULL,              --작성일
FILENAME VARCHAR2(100) NOT NULL,  --파일네임
VCOUNT NUMBER NOT NULL            --조회수
);




-- 댓글
CREATE TABLE MYCOMMENT
(
MYID VARCHAR2(50) NOT NULL,      --댓글 다는 사람의 아이디(FK)
CONTENT VARCHAR2(500) NOT NULL,  --댓글 내용
REF NUMBER NOT NULL,             --정렬용
FREE NUMBER NOT NULL,            --글 카테고리를 구분짓기위한 변수 (bbs_0, freeboard_1, after_2
DEL NUMBER NOT NULL,             --삭제유무 (default0, 삭제1)
SEQ NUMBER NOT NULL            --시퀀스
);




-- 후기 
CREATE TABLE AFTERSTORY 
(
SEQ NUMBER NOT NULL,              --시퀀스
MYID VARCHAR2(50) NOT NULL,       --아이디(FK)
TITLE VARCHAR2(50) NOT NULL,      --제목
MYCONTENT VARCHAR2(500) NOT NULL, --내용
WDATE DATE NOT NULL,              --작성일
FILENAME VARCHAR2(100) NOT NULL,  --파일네임
VCOUNT NUMBER NOT NULL            --조회수
);



--멤버 시퀀스
CREATE SEQUENCE SEQ_MYMEMBER
START WITH 1
INCREMENT BY 1; 

--인펫 시퀀스
CREATE SEQUENCE SEQ_ABANDONEDDOG
START WITH 1
INCREMENT BY 1; 

--아웃펫 시퀀스
CREATE SEQUENCE SEQ_RESCUEDDOG
START WITH 1
INCREMENT BY 1;


--WRITE PET
CREATE SEQUENCE SEQ_ADOPTDOG
START WITH 1
INCREMENT BY 1; 

--마이펫 시퀀스
CREATE SEQUENCE SEQ_BBS
START WITH 1
INCREMENT BY 1; 

--COMTPET
CREATE SEQUENCE SEQ_MYCOMMENT
START WITH 1
INCREMENT BY 1; 


--에프터(후기) 시퀀스
CREATE SEQUENCE SEQ_AFTERSTORY 
START WITH 1
INCREMENT BY 1; 




--외래키 연결 
ALTER TABLE ABANDONEDDOG
ADD
CONSTRAINT FK_INPET_ID FOREIGN KEY(MYID)
REFERENCES MYMEMBER(MYID);


ALTER TABLE RESCUEDDOG
ADD
CONSTRAINT FK_WRITE_ID FOREIGN KEY(FILENAME)
REFERENCES ABANDONEDDOG(FILENAME);


ALTER TABLE ADOPTDOG
ADD
CONSTRAINT FK_OUTPET_ID FOREIGN KEY(MYID)
REFERENCES MYMEMBER(MYID);


ALTER TABLE BBS
ADD
CONSTRAINT FK_MYPET_ID FOREIGN KEY(MYID)
REFERENCES MYMEMBER(MYID);


ALTER TABLE AFTERSTORY 
ADD
CONSTRAINT FK_AFPET_ID FOREIGN KEY(MYID)
REFERENCES MYMEMBER(MYID);


ALTER TABLE MYCOMMENT
ADD
CONSTRAINT FK_COM_ID FOREIGN KEY(MYID)
REFERENCES MYMEMBER(MYID);

ALTER TABLE MYCOMMENT
ADD
CONSTRAINT FK_COM_ID FOREIGN KEY(REF)
REFERENCES MYMEMBER(REF);

ALTER TABLE ADOPTDOG 
ADD 
PHONENUM VARCHAR2(100) NOT NULL;