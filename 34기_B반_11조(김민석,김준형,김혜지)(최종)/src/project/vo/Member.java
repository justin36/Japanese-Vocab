package project.vo;

public class Member {
	private int memNo;
	private String id;
	private String pw;
	private String name;
	private int attendance;
	private String joinDate;
	private int notice;

	

	public int getNotice() { return notice;}
	public void setNotice(int notice) { this.notice = notice;}
	public int getMemNo() {	return memNo;}
	public void setMemNo(int memNo) { this.memNo = memNo;}
	public String getId() { return id;}
	public void setId(String id) { this.id = id;}
	public String getPw() { return pw;}
	public void setPw(String pw) { this.pw = pw;}
	public String getName() { return name;}
	public void setName(String name) { this.name = name;}
	public int getAttendance() { return attendance;}
	public void setAttendance(int attendance) { this.attendance = attendance;}
	public String getJoinDate() { return joinDate;}
	public void setJoinDate(String joinDate) { this.joinDate = joinDate;}
	
	public Member() {}
	public Member(String id,String pw,String name) {
		this.id = id;
		this.pw = pw;
		this.name = name;
	}
	// login
	public Member(String id,String pw) {
		this.id = id;
		this.pw = pw;
	}
	
	public String toString() {
		return "Member_id: "+id+" Name: "+name+" Join date: "+joinDate;
	}
	public void findPassword() {
		System.out.println("ID: "+id+"\nPassword: "+pw);
	}
	
}
/*
MemNo  / number
id_pk / varchar2(20)
pw_not null / varchar2(20)
name_not null / varchar2(20)
Attendance_default(0) / number
joinDate_sysdate / date
*/