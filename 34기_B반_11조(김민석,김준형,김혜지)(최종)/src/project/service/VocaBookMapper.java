package project.service;

import java.util.ArrayList;
import org.apache.ibatis.annotations.Param;
import project.vo.Member;
import project.vo.Voca;
import project.vo.VocaList;

public interface VocaBookMapper {
	
	// 회원가입 - 가입 성공 시 true 반환 (UI에서 가입성공문구 기입)
	public int registerAccount(Member m);	
	// 아이디 중복체크
	public Member checkId(String id);	
	// 로그인
	public Member login(Member m);
	/** 로그인 성공 시 Member객체 반환
	 * 그래야 ID 별로 단어장 만들기 가능(사실모름)
	 */
	// 비밀번호 찾기
	public Member findPw(String id);
	// id 에 맞는 pw 출력 (id는 고유값임)	
	// 회원탈퇴
	public Integer deleteAccount(Member m);
	// 출석일 수 증가
	public int addAttendance(String id);
	// 열람횟수 증가
	public int addStudyCount(VocaList value2);
	// 단어장 등록
	public int insertList(VocaList voca);
	// 단어장 안에 들어갈 단어 등록
	public int insertWordInList(Voca words);
	// 단어장 생성날짜 별로 출력
	public ArrayList<VocaList> loadListByDate(String id);
	// Find VocaList By Name
	public ArrayList<Voca> searchVocaList(VocaList find);
	// Delete VocaList 
	public int deleteList(int listNo);
	// Print every vocaList
	public ArrayList<Voca> getAllList(String id);
	// 단어장 안의 단어 수정
	public int modifyList(Voca voca);
	// 단어 의미 맞나 체크
	public int checkAnswer(Voca answer);
	// 히라가나 맞나 체크 (준형 추가)
	public int checkAnswer2(Voca answer);
	// 오답노트
	public ArrayList<Voca> seeWrongWords(VocaList find);
	// 단어 삭제
	public int deleteVoca(int vocaNo);
	// 공유목록 가져오기
	public ArrayList<VocaList> getSharing(String id);
	//공유하기
	public int sharing(@Param("sharing")int sharing ,@Param("listNo")int listNo);
	//공지하기
	public int notice();
	//공지끄기
	public int noticeoff(Member m);
	public int changeListName(VocaList value2);
	public int changeListName2(VocaList value2);
}
