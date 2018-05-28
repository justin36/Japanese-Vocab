package project.vo;

public class VocaList {
	private int listNo;
	private String listName;
	private String id;
	private int studyCount;
	private int sharing;
	private String indate;
	private int easyNumber;
	

	public void setEasyNumber(int easyNumber) { this.easyNumber = easyNumber;}
	public int getEasyNumber() { return easyNumber;}
	public int getListNo() { return listNo;}
	public void setListNo(int listNo) { this.listNo = listNo;}
	public String getListName() { return listName;}
	public void setListName(String listName) { this.listName = listName;}
	public String getId() { return id;}
	public void setId(String id) { this.id = id;}
	public int getStudyCount() { return studyCount;}
	public void setStudyCount(int studyCount) { this.studyCount = studyCount;}
	public int getSharing() { return sharing;}
	public void setSharing(int sharing) { this.sharing = sharing;}
	public String getIndate() { return indate;}
	public void setIndate(String indate) { this.indate = indate;}
	
	public VocaList() {}
	
	public VocaList(String id,String listName) {
		this.id = id;
		this.listName = listName;
	}
	
	/** find �� ���� ������**/
	public VocaList(String id,int listNo,String listName) {
		this.id = id;
		this.listNo = listNo;
		this.listName = listName;
	}
	
	public String toString() {
		return "Member_id: "+id+" List name: "+listName;
	}
	// �ڽ��� ������ ����Ʈ ���
	public void showListByDate() {
		System.out.printf("[List name: %-8s Study Count: %-2d"
				+ " List Creation Date: %-10s]\n",listName,studyCount,indate);
	}
	
	// �̸��� ����� ���� ����Ʈ �̸� �����ֱ�
	public void showMyList() {
		System.out.println(" *"+listName);
	}
}

/*
ListNo_pk / number
ListName_not null / varchar(15)
id_fk / varchar(20)
studyCount(����Ƚ��) / number
Sharing(��������) / number
indate(������)_sysdate / date
*/