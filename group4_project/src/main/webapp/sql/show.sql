
CREATE TABLE SHOW(
		sh_key                        		NUMBER(30)		 NOT NULL,
		sh_title                      		VARCHAR2(50)		 NOT NULL,
		sh_date                       		VARCHAR2(15)		 NOT NULL,
		sh_time                       		VARCHAR2(15)		 NOT NULL,
		sh_place                      		VARCHAR2(60)		 NOT NULL,
		sh_detail                     		VARCHAR2(1000)		 NULL ,
		sh_img                        		VARCHAR2(100)		 NULL ,
		me_key                        		NUMBER(30)		 NULL 
);

COMMENT ON TABLE SHOW is '공연 정보';
COMMENT ON COLUMN SHOW.sh_key is '공연키값';
COMMENT ON COLUMN SHOW.sh_title is '공연 제목';
COMMENT ON COLUMN SHOW.sh_date is '공연 날짜';
COMMENT ON COLUMN SHOW.sh_time is '공연 시간';
COMMENT ON COLUMN SHOW.sh_place is '공연 장소';
COMMENT ON COLUMN SHOW.sh_detail is '공연설명';
COMMENT ON COLUMN SHOW.sh_img is '대표이미지';
COMMENT ON COLUMN SHOW.me_key is '회원키값';


ALTER TABLE SHOW ADD CONSTRAINT IDX_SHOW_PK PRIMARY KEY (sh_key);
ALTER TABLE SHOW ADD CONSTRAINT IDX_SHOW_FK0 FOREIGN KEY (me_key) REFERENCES MEMBER (me_key);


/**********************************/
/* Table Name: 공연 후기 관리 */
/**********************************/
CREATE TABLE SHOW_REVIEW(
		sre_gpa                       		NUMBER(30)		 NOT NULL,
		sre_review                    		VARCHAR2(200)		 NULL ,
		sh_key                        		NUMBER(30)		 NULL ,
		me_key                        		NUMBER(30)		 NULL 
);

COMMENT ON TABLE SHOW_REVIEW is '공연 후기 관리';
COMMENT ON COLUMN SHOW_REVIEW.sre_gpa is '평점';
COMMENT ON COLUMN SHOW_REVIEW.sre_review is '리뷰';
COMMENT ON COLUMN SHOW_REVIEW.sh_key is '공연키값';
COMMENT ON COLUMN SHOW_REVIEW.me_key is '회원키값';


ALTER TABLE SHOW_REVIEW ADD CONSTRAINT IDX_SHOW_REVIEW_FK0 FOREIGN KEY (sh_key) REFERENCES SHOW (sh_key);
ALTER TABLE SHOW_REVIEW ADD CONSTRAINT IDX_SHOW_REVIEW_FK1 FOREIGN KEY (me_key) REFERENCES MEMBER (me_key);



