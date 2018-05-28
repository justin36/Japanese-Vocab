create table member(
	memNo 		number,
	id 			varchar2(20) 	primary key,		-- 아이디 (준형 수정)
	pw 			varchar2(20) 	not null,			-- 비번 (준형 수정)
	name 		varchar2(100) 	not null,			-- 이름 (준형 수정)
	attendance 	number 			default 0,			-- 방문횟수
	joinDate 	date 			default sysdate		-- 가입일자
	notice 		number 			default 0			-- 공유
);


create table vocalist(
	listNo 		number 			constraint vocalist primary key,	-- 리스트번호
	listName 	varchar(15) 	not null,							-- 리스트이름
	id 			varchar(20) 	constraint vocalist_id_fk references member on delete cascade, -- 아이디
	studyCount 	number 			default 0,							-- 공부횟수
	sharing 	number 			default 0,							-- 공유
	indate 		date 			default sysdate						-- 단어 생성일자
	easynumber 	number 			default 0
	);
	
create table voca(
	vocaNo 		number 			primary key,						-- 단어번호
	japan 		varchar2(1000) 	not null,							-- 일어 단어
	hira 		varchar2(1000) 	not null,							-- 히라가나
	mean 		varchar2(1000) 	not null,							-- 뜻
	listNo 		number 			references vocalist on delete cascade,	-- 리스트번호
	listName 	varchar2(15) 	not null,							-- 리스트 이름
	wrongCount 	number 			default 0							-- 오답횟수
	);