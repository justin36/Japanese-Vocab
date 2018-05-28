package project.ui;

import java.util.Scanner;

import project.service.VocaBookDAO;
import project.vo.Member;

public class VocaBookUI {
	private Scanner sc = new Scanner(System.in);
	private VocaBookDAO manager = new VocaBookDAO();
	private Member login;
	public VocaBookUI() {}
	
	public void setMember(Member m) { this.login = login;}
	public Member getMember() { return login;}
	
	public void start() {
		AGAIN:
		while(true) {
			mainMenu();
			int choice = this.getIntValue("-●●● チョイス : ");
			
			if (choice < 1 || 4 < choice) {
				System.out.println("[Error]Wrong menu choice");
				continue AGAIN;
			}
			
			switch (choice) {
			case 1:
				registerAccount();
				break;
			case 2:
				login = login();
				if (login != null) {
					return ;
				}
				break;
			case 3:
				deleteAccount();
				continue;
			case 4:
				System.out.println("ζ(＇Δ＇  )Γ さようなら~~");
				sc.close();
				System.exit(0);
				break;
			}			
		}
	}
	public void deleteAccount() {
		System.out.println("( ˚ ∇˚)つ-●●● Delete Account");
		System.out.print("ID_");
		String id = sc.nextLine();
		
		System.out.print("Password_");
		String pw = sc.nextLine();
		System.out.print("Name_");
		String name = sc.nextLine();
		Member delete = new Member(id, pw);
		
		if (manager.deleteAccount(delete)==1) {
			System.out.println("Your account is deleted");	
		} else {
			System.out.println("[Fail] This account isn't existed");
		}
	}
	
	public Member login() {
		System.out.println("( ˚ ∇˚)つ-●●● Login");
		System.out.print("● Id_");
		String id = sc.next();
		System.out.print("● Password_");
		String pw = sc.next();
		Member m = new Member(id, pw);
		if (manager.login(m) == null) {
			System.out.println("[Error] Login failed,try again");
			return null;
		} else {
			System.out.println("-●●● Login succeed");
			login = manager.login(m);
			manager.addAttendance(id);
			
			System.out.printf("\n(@ 'д')♥ さん ぉかえり %sさん \n",login.getId());
			System.out.printf("%dばんめ 訪問だね~\n\n",login.getAttendance()+1);
			return login;
		}
	}
	
	public void registerAccount() {
		System.out.println("Welcome to My Voca List Book");
		System.out.println("Let's make your own account !");
		String id = null;
		do {
			System.out.print("● ID_");
			id = sc.next();
			if(manager.checkId(id) != null) {
				System.out.println("** Sorry, this id already exist");
				System.out.println("** Choose another one please");
				id = null;
			}
		} while(id == null);
		System.out.print("● Password_");
		String pw = sc.next();
		System.out.print("● Name_");
		String name = sc.next();
		Member m = new Member(id, pw, name);
		if(manager.registerAccount(m) == 1) {
			System.out.println("** Perfect ! Now, let's study Japanese");
		} else {
			System.out.println("[Error] Registration fail");
		}
	}
	
	public int getIntValue(String message) {
		System.out.print(message);
		int value = 0;
		do {
			try {
				value = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				sc.nextLine();
				//e.printStackTrace();
				value = 0;
				System.out.println("[Error]Input the number please!");
				System.out.print("● Again_");
			}
		} while(value==0);
		return value;
	}
	
	public void mainMenu() {
		System.out.println("*:.。..。.:*'(*ο△ο*)'*:.。. .。.:*");
		System.out.println("( ˚ ∇˚)つ-●●● 單語のワ-ルドにおかえり");
		System.out.println(" ● 1. Register Account");
		System.out.println(" ● 2. Login");
		System.out.println(" ● 3. Delete Account");
		System.out.println(" ● 4. Exit System");
	}
	
}
