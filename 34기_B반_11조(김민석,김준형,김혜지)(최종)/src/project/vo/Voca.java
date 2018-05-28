package project.vo;

import java.util.ArrayList;

public class Voca {
	private int vocaNo;
	private String japan;
	private String hira;
	private String mean;
	private int listNo;
	private int wrongCount;
	private String listName;
	
	
	public void setListName(String listName) { this.listName = listName;}
	public String getListName() { return listName;}
	public void setVocaNo(int vocaNo) { this.vocaNo = vocaNo;}
	public int getVocaNo() { return vocaNo;}
	public void setJapan(String japan) { this.japan = japan;}
	public String getJapan() { return japan;}
	public void setHira(String hira) { this.hira = hira;}
	public String getHira() { return hira;}
	public void setMean(String mean) { this.mean = mean;}
	public String getMean() { return mean;}
	public void setListNo(int listNo) { this.listNo = listNo;}
	public int getListNo() { return listNo;}
	public void setWrongCount(int wrongCount) { this.wrongCount = wrongCount;}
	public int getWrongCount() { return wrongCount;}
	
	public Voca() {}
	public Voca(String japan,String hira,String mean) {
		this.japan = japan;
		this.hira = hira;
		this.mean = mean;
	}

	public Voca(String listName,String japan,String hira,String mean) {
		this.listName = listName;
		this.japan = japan;
		this.hira = hira;
		this.mean = mean;
	}
	public Voca(int vocaNo) {
		this.vocaNo = vocaNo;
	}
	
	// 답 체크 위한 생성자 (의미);
	public Voca(int vocaNo,String mean) {
		this.vocaNo = vocaNo;
		this.mean = mean;
	}
	
	
	// 수정을 위한 생성자
	public Voca(int vocaNo,String japan,String hira,String mean) {
		this.vocaNo = vocaNo;
		this.japan = japan;
		this.hira = hira;
		this.mean = mean;
	}
	// 기본 단어 출력
	public String toString() {
		return "Japan: "+japan+" Hiragana: "+hira+" Meaning: "+mean;
	}
	// 수정을 위해 VocaNo를 보여주는 출력
	public void showVocaNo() {
		System.out.printf("No.%d Japan: %-10s Hiragana: %-10s Meaning: %-10s\n",vocaNo,japan,hira,mean);
	}
	
	public void showWrong() {
		System.out.printf("Japan: %-10s Hiragana: %-10s Meaning: %-10s Wrong count: %-2d\n",japan,hira,mean,wrongCount);
	}
}

/*
VocaNo_pk number
Japan_not null / varchar2(20)
Hira_not null  / varchar2(20)
Mean_not null / varchar2(100)
ListNo_fk / number
WrongCount_default(0) / number
*/