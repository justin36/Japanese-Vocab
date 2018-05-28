package project.ui;

import project.service.Kor;
import project.service.Jap;
import java.util.ArrayList;
import java.util.Scanner;

import project.service.VocaBookDAO;
import project.vo.Member;
import project.vo.Voca;
import project.vo.VocaList;

public class VocaBookInsideUI {
	Member login = new Member();
	private Scanner sc = new Scanner(System.in);
	private VocaBookDAO manager = new VocaBookDAO();
	private VocaBookUI ui1;
	ArrayList<Voca> list;
	ArrayList<VocaList> vocaList;
	Voca value1;
	VocaList value2;
	String listName;
	Kor kor = new Kor();
	Jap jap = new Jap();
	int shutDown = 0;
	
	public void login() {
		ui1 = new VocaBookUI();
		ui1.start();
		login = ui1.getMember();

	}

	// 시작
	public void start() {

		while (true) {
			insideMenu();
			int choice = this.getIntValue(" Choice_");

			switch (choice) {
			case 1:
				makeList();
				break;
			case 2:
				vocaListUI();
				break;
			case 3:
				translate();
				break;
			case 4:
				list = manager.getAllList(login.getId());
				if (list.isEmpty()) {
					System.out.println("●● There is no book to share ●●");
					break;
				}
				testUI();
				break;
			case 5:
				shutDown = 0;
				return;
			default:
				System.out.println("[Error]Wrong menu number");
				continue;
			}
		}
	}

	// 1번메뉴 1번 - 단어장 등록기능
	public void makeList() {
		
		list = new ArrayList<>();
		System.out.println("= Make Voca List =");
		System.out.println("1. Writing directly");
		System.out.println("2. Using translater");
		int choose = this.getIntValue("Choice_");
		switch(choose) {
		case 1:break;
		case 2:					
			usingTranslater();
			return;
		default:
			System.out.println("[Error]Wrong menu number");
			return;
		}
		String listName = null;
		AGAIN: do {
			System.out.print("Input the list name_");
			ArrayList<VocaList> check = manager.loadListByDate(login.getId());
			listName = sc.nextLine();
			for (VocaList list : check) {
				if (listName.equals(list.getListName())) {
					System.out.println("This name is already existed");
					listName = null;
					continue AGAIN;
				}
			}
		} while (listName == null);
		int count = 1;
		while (true) {
			System.out.printf("Word %d_", count);
			String japan = sc.next();
			System.out.print("Hiragana_");
			String hira = sc.next();
			sc.nextLine();
			System.out.print("Meaning_");
			String mean = sc.nextLine();
			value1 = new Voca(listName, japan, hira, mean);
			list.add(value1);
			count++;

			if (count > 5) {
				System.out.print("To finish this list : 0");
				String choice = sc.next();
				if (choice.equals("0")) {
					// 단어장 이름 생성
					value2 = new VocaList(login.getId(), listName);
					manager.insertList(value2);

					// 단어장 안의 단어들 생성
					for (Voca list : list) {
						manager.insertWordInList(list);
					}
					break;
				}
			}
		}
	}

	// 2번메뉴의 리스트출력 & 시험 메뉴
	public void vocaListUI() {
		while (true) {
			listMenu();
			int choice = this.getIntValue(" Choice_");

			switch (choice) {
			case 1:
				listByName();
				break;
			case 2:
				listAll();
				break;
			case 3:
				everyWords();
				break;
			case 4:
				sharing();
				break;
			case 5:
				modifyBook();
				break;
			case 6:
				deleteList();
				break;
			case 7:
				return;
			default:
				System.out.println("[Error]Wrong menu number");
				break;
			}
		}
	}

	// 2번의 1번메뉴 - 이름별 리스트안의 있는 단어들 출력
	public void listByName() {
		System.out.println("\n( ˚ ∇˚)つ-●●● My VocaBook");
		list = this.getList();
		// 없는 리스트 입력 시 비어있음 출력
		if (list.isEmpty()){
			System.out.println("● This book isn't existed");
			return;
		}
		System.out.println("[" + this.listName + "]");
		for (Voca voca : list) {
			System.out.println(voca);
		}
		value2 = new VocaList(login.getId(), listName);
		manager.addStudyCount(value2);
	}

	// 2번의 2번메뉴 - 소유하고있는 리스트 목록 출력
	public void listAll() {
		System.out.println("\n( ˚ ∇˚)つ-●●● My books' list");
		vocaList = manager.loadListByDate(login.getId());
		if (vocaList.isEmpty()) {
			System.out.println("●● Empty ●●");
			return;
		}
		for (VocaList list : vocaList) {
			list.showListByDate();
		}
	}

	// 2번의 3번메뉴 - 등록된 모든 단어 소환
	public void everyWords() {
		System.out.println("\n( ˚ ∇˚)つ-●●● My Whole Vocabularies");
		list = this.getAllList();
		// getAllList
		vocaList = manager.loadListByDate(login.getId());
		String listName = null;
		int listNo = 0;
		for (VocaList vocaList : vocaList) {
			listName = vocaList.getListName();
			listNo = vocaList.getListNo();
			value2 = new VocaList(login.getId(), listNo, listName);
			list = manager.searchVocaList(value2);

			System.out.println("[" + vocaList.getListName() + "]");
			for (Voca words : list) {
				System.out.println(words);
			}
		}
	}

	// 2번의 4번메뉴 - 공유하기
	public void sharing() {
		while (true) {
			System.out.println("( ˚ ∇˚)つ-●●● Share my list");
			ArrayList<VocaList> list = manager.loadListByDate(login.getId());
			if (list.isEmpty()) {
				System.out.println("●● There is no book to share ●●");
				return;
			}
			int easyNumber = 1;
			for (VocaList vocalist : list) {
				vocalist.setEasyNumber(easyNumber);
				System.out.print(easyNumber + ". ");
				vocalist.showMyList();
				System.out.println();
				easyNumber++;
			}
			System.out.println();
			System.out.print("Choose the list's number_");
			boolean check = false;
			int select = 0;
			try {
				select = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("[Error]Input the number please");
				sc.nextLine();
				continue;
			}
			if (select > easyNumber - 1) {
				System.out.println("[Error]Wrong number choice");
				continue;
			}
			for (VocaList vocalist : list) {
				if (vocalist.getEasyNumber() == select) {
					int number = vocalist.getListNo();
					manager.sharing(1, number);
					System.out.println("● Sharing succeed");
					check = true;
					for (VocaList vocalist2 : list) {
						vocalist2.setEasyNumber(0);
					}
					manager.notice();
					manager.noticeoff(login);
					return;
				}
			}
			if (!check) {
				System.out.println("[Error] Sharing failed");
				break;
			}
		}
	}

	// 2번의 5번메뉴 - 단어장 수정
	public void modifyBook() {
		System.out.println("\n( ˚ ∇˚)つ-●●● Modify my book");
		System.out.println("1. Change the list name");
		System.out.println("2. Change the voca of my list");
		int choice2 = getIntValue("● Choice_");
		if (choice2 == 1) {
			this.changeListName();
			return;
		} else if(choice2 < 0 || 2 < choice2) {
			System.out.println("[Error]Wrong number choice");
			return;
		}
		
		list = this.getList();
		// 없는 리스트 입력 시 비어있음 출력
		// 수정  if 절 안에 null 이 아닌 is empty 로 변경
		
		if (list.isEmpty()) {
			System.out.println("● This book doesn't exist");
			return;
		}
		System.out.println("[" + this.listName + "]");

		for (Voca voca : list) {
			voca.showVocaNo();
		}
		System.out.println("\n( ˚ ∇˚)つ-●●● Let's modify");
		System.out.println("Select the VocaNo first and choose");
		int choice = getIntValue("● Put the voca's No_");
		int menu = 0;
		do {
			System.out.println("(1) change (2) delete (3) cancel ");
			menu = getIntValue("Choice_");
			if (menu < 0 || 3 < menu) {
				System.out.println("[Error]Wrong menu number");
				menu = 0;
			} else if (menu == 3) {
				return;
			}
		} while (menu == 0);

		value1 = new Voca(choice);
		if (menu == 1) {
			for (Voca voca : list) {
				if (choice == voca.getVocaNo()) {
					System.out.print("Change Word_");
					String japan = sc.nextLine();
					System.out.print("Change hiragana_");
					String hira = sc.nextLine();
					System.out.print("Change meaning_");
					String mean = sc.nextLine();
					value1 = new Voca(choice, japan, hira, mean);
					if (manager.modifyList(value1) == 1) {
						System.out.println("It's changed");
					} else {
						System.out.println("[Error]Fail");
					}
				}
			}
		} else if (menu == 2) {
			if (manager.deleteVoca(value1.getVocaNo()) == 1) {
				System.out.println("● Deleted ");
			} else {
				System.out.println("[Error] fail");
			}
		}
		
		
	}

	// 2번의 6번메뉴 - 단어장삭제
	public void deleteList() {
		this.listAll();
		System.out.print("\n● Input the book's name_");
		listName = sc.nextLine();
		int choice = 0;
		for (VocaList vocaList : vocaList) {
			if (listName.equals(vocaList.getListName())) {
				value2 = new VocaList(login.getId(), vocaList.getListNo(), listName);
				list = manager.searchVocaList(value2);
				for (Voca voca : list) {
					System.out.println(voca);
				}
				System.out.println(" *Are you sure to remove?");
				System.out.println("1.Yes 2.No");
				// 수정 choice 위치 변경 / deleted 밑에 return 추가 / for문 이후에 sysout추가
				do {
					choice = getIntValue("● Choice_");
					if (choice == 1) {
						if (manager.deleteList(vocaList.getListNo()) == 1) {
							System.out.println("● Deleted");
							return;
						}
					} else if (choice == 2) {
						System.out.println("~~ Return to menu\n");
						return;
					} else {
						System.out.println("[Error]Wrong choice");
						choice = 0;
					}
				} while (choice == 0);
			}
		}
		System.out.println("● This name isn't existed");
	}

	// 3번메뉴 - 번역
	public void translate() {

		System.out.println("( ˚ ∇˚)つ-●●● 번역기");
		while (true) {
			System.out.println("1. Korean to Japanese");
			System.out.println("2. Japanese to Korean");
			System.out.println("3. Previous");
			int select = getIntValue("Choice_");
			String word = null;
			String result = null;
			switch (select) {
			case 1:
				System.out.println("번역하고 싶은 단어 또는 문장을 작성하세요");
				word = sc.nextLine();
				result = kor.translate(word);
				System.out.println("-●●●요청한 내용");
				System.out.println("_" + word);
				System.out.println("-●●●번역 결과");
				System.out.println("_" + result);
				break;

			case 2:
				System.out.println("번역하고 싶은 단어 또는 문장을 작성하세요");
				word = sc.nextLine();
				result = jap.translate(word);
				System.out.println("요청한 내용");
				System.out.println(word);
				System.out.println("번역 결과");
				System.out.println(result);
				break;
			case 3:
				return;
			default:
				System.out.println("[Error]Wrong menu number");
				continue;
			}
		}
	}

	public void testUI() {
		while (true) {
			testMenu();
			int choice = getIntValue(" Choice_");

			switch (choice) {
			case 1:
				testMeanByList(); // meaning
				break;
			case 2:
				testMeanAll(); // meaning
				break;
			case 3:
				testHiraByList2(); // hiragana
				break;
			case 4:
				testHiraAll2(); // hiragana
				break;
			case 5:
				showWrongWords();
				break;
			case 6:
				return;
			default:
				System.out.println("[Error]Wrong menu number");
				continue;
			}
		}
	}

	// 4번메뉴의 1번 - list별 테스트 - mean
	public void testMeanByList() {
		System.out.println("\n( ˚ ∇˚)つ-●●● Test ");
		list = this.getList();
		// 없는 리스트 입력 시 비어있음 출력
		if (list.isEmpty()) {
			System.out.println("● This book doesn't exist");
			return;
		} 
		System.out.printf("Let's start %s's test !\n", listName);
		testStart(list); // 준형 추가
	}

	// 4번메뉴의 2번 - 전체 voca테스트 - mean
	public void testMeanAll() {
		list = manager.getAllList(login.getId());
		if (list.isEmpty()) {
			System.out.println("● This book doesn't exist");
			return;
		}
		// getAllList
		System.out.println("\n( ˚ ∇˚)つ-●●● Test ");
		System.out.println("Whole vocabularies' test");
		testStart(list); // 준형 추가
	}

	// 4번메뉴의 3번 - list별 테스트 - hira
	public void testHiraByList2() {
		System.out.println("\n( ˚ ∇˚)つ-●●● Test ");
		list = this.getList();
		// 없는 리스트 입력 시 비어있음 출력
		if (list.isEmpty()) {
			System.out.println("● This book doesn't exist");
			return;
		}
		System.out.printf("Let's start %s's test !\n", listName);
		testStart2(list);
	}

	// 4번메뉴의 4번 - 전체 voca테스트 - hira
	public void testHiraAll2() {
		list = manager.getAllList(login.getId());
		if (list.isEmpty()) {
			System.out.println("● This book doesn't exist");
			return;
		}
		System.out.println("\n( ˚ ∇˚)つ-●●● Test ");
		System.out.println("Whole vocabularies' test");
		testStart2(list); // 준형 추가
	}

	// 4번메뉴의 5번 - 오답노트 (많이 틀린 순서대로)
	public void showWrongWords() {
		System.out.println("\n( ˚ ∇˚)つ-●●● Check one more time these words");
		list = manager.getAllList(login.getId());
		// getAllList
		vocaList = manager.loadListByDate(login.getId());
		String listName = null;
		int listNo = 0;
		for (VocaList vocaList : vocaList) {
			listName = vocaList.getListName();
			listNo = vocaList.getListNo();
			value2 = new VocaList(login.getId(), listNo, listName);
			list = manager.seeWrongWords(value2);
			int count = 0;
			System.out.println("[" + vocaList.getListName() + "]");
			for (Voca words : list) {
				if (words.getWrongCount() > 0) {
					words.showWrong();
					count++;
				}
			}
			if (count == 0) {
				System.out.println("-●●● Nothing");
			}
		}
	}

	// 중복 회피 1 - 전체리스트소환
	public ArrayList<Voca> getAllList() {
		list = manager.getAllList(login.getId());
		if (list.isEmpty()) {
			System.out.println("●● There is no book to test ●●");
			return list;
		} else {
			return null;
		}
	}

	// 중복 회피2 - 출력을 위한 리스트소환
	public ArrayList<Voca> getList() {
		vocaList = manager.loadListByDate(login.getId());
		System.out.println("My Book List ");
		// 비어있을 때 empty출력
		if (vocaList.isEmpty()) {
			System.out.println("●● Empty ●●");
			return null;
		}
		// 비어있지 않으면 소유한list의 이름들 출력
		for (VocaList list : vocaList) {
			list.showMyList();
		}
		// list 선택
		System.out.print("\nChoose the book's name_");
		String listName = sc.nextLine();
		int listNo = 0;
		for (VocaList list : vocaList) {
			if (listName.equals(list.getListName())) {
				listNo = list.getListNo();
				this.listName = listName;
			}
		}

		value2 = new VocaList(login.getId(), listNo, listName);
		list = manager.searchVocaList(value2);
		return list;
	}

	// 중복 회피 3- 시험
	public void testStart(ArrayList<Voca> list) {
		System.out.println("I will show you the word,");
		System.out.println("then you should figure out the meaning.");
		System.out.println("Start!!");
		double right = 0;
		int wrong = 0;
		for (Voca t : list) {
			System.out.println("Word: " + t.getJapan());
			System.out.print("● Answer_");
			String mean = sc.next();
			sc.nextLine();
			Voca answer = new Voca(t.getVocaNo(), mean);
			if (manager.checkAnswer(answer) == 1) {
				System.out.println("ざんねんですね ~ T^ T");
				wrong++;
			} else {
				System.out.println("すごい !! └(＠_＠ˇ)┘");
				right++;
			}
		}
		int total = list.size();
		double aver = right / total * 100;
		System.out.printf("\n-●●● Total: %d \n-●●● Wrong answer: %d \n-●●● Percentage of correct answer: %.2f", total,
				wrong, aver);
	}

	// 중복회피 4 - 시험 2
	public void testStart2(ArrayList<Voca> list) {
		System.out.println("I will show you the word,");
		System.out.println("then you should figure out its hiragana pronunciation.");
		System.out.println("Start!!");
		double right = 0;
		int wrong = 0;
		for (int i = 0; i < list.size(); i++) {
			System.out.println("Word: " + list.get(i).getJapan());
			System.out.print("● Hiragana_");
			String hira = sc.next();
			sc.nextLine();

			if (list.get(i).getHira().equals(hira)) {
				System.out.println("すごい !! └(＠_＠ˇ)┘");
				right++;
			} else {
				System.out.println("ざんねんですね ~ T^ T");
				wrong++;
				manager.checkAnswer2(list.get(i));
			}
		}
		int total = list.size();
		double aver = right / total * 100;
		System.out.printf("\n-●●● Total: %d \n-●●● Wrong answer: %d \n-●●● Percentage of correct answer: %.2f", total,
				wrong, aver);
	}

	public void testMenu() {
		System.out.println("\n( ˚ ∇˚)つ-●●● Test");
		System.out.println("1. Test Meaning(choose a list)");
		System.out.println("2. Test Meaning(all words)");
		System.out.println("3. Test Hiragana(choose a list)");
		System.out.println("4. Test Hiragana(all words)");
		System.out.println("5. Review wrong answer");
		System.out.println("6. Previous");
	}

	public void listMenu() {
		System.out.println("\n( ˚ ∇˚)つ-●●● My VocaBook List");
		System.out.println("1. Choose the book");
		System.out.println("2. See my book list");
		System.out.println("3. See all my vocabularies ");
		System.out.println("4. Share the book ");
		System.out.println("5. Modify the book");
		System.out.println("6. Delete the book");
		System.out.println("7. Previous");
	}

	public void insideMenu() {
		if (shutDown==0) {
			if (login.getNotice() == 1) {
				checkShare();
				shutDown++;
			}
			if(login.getAttendance()<1 && null!=manager.getSharing(login.getId())) {
				checkShare();
				shutDown++;
			}
		}
		

		System.out.println("○●○●○●○●○●○●○●○●○●○●○");
		System.out.println("= LET'S GO TO JAPAN =");
		System.out.println("○●○●○●○●○●○●○●○●○●○●○");
		System.out.println("1. Make voca book");
		System.out.println("2. Study my book");
		System.out.println("3. Translation");
		System.out.println("4. Test");
		System.out.println("5. Log out");
	}

	public int getIntValue(String message) {
		System.out.print(message);
		int value = 0;
		do {
			try {
				value = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				sc.next();
				// e.printStackTrace();
				System.out.println("[Error]Input the number please!");
				System.out.print("● Again_");
				value = 0;
			}

		} while (value == 0);
		return value;
	}

	// 공유 코드
	public void checkShare() {
		while (true) {
			System.out.println();
			System.out.println("★  Someone wants to share their list...");
			ArrayList<VocaList> shareList = null;
			shareList = manager.getSharing(login.getId());
			int n = 1;
			int select;
			for (VocaList list : shareList) {
				System.out.println(n + ". " + list.toString());
				list.setEasyNumber(n);
				System.out.println();
				n++;
			}
			System.out.println("Choose the number you want to check");
			System.out.println("To skip : 0");
			System.out.print("Choice_");
			try {
				select = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("[Error]Input the number please");
				sc.nextLine();
				continue;
			}
			if (select == 0) {
				manager.noticeoff(login);
				for (VocaList list : shareList) {
					list.setEasyNumber(0);
				}
				return;
			}
			if (select > n) {
				System.out.println("[Error]Wrong number choice");
				continue;
			}
			VocaList list = null;
			ArrayList<Voca> vocas = null;
			for (VocaList vocalist : shareList) {
				if (select == vocalist.getEasyNumber()) {
					vocas = manager.searchVocaList(vocalist);
					for (Voca voca : manager.searchVocaList(vocalist)) {
						System.out.println(voca);
					}
					list = vocalist;
					break;
				}
			}
			System.out.println("\nDo you want to save this list?");
			System.out.println("Yes : 1  ||  No : 2");
			System.out.print("Choice_");
			int oneOrTwo;
			try {
				oneOrTwo = sc.nextInt();
				sc.nextLine();
			} catch (Exception e) {
				// e.printStackTrace();
				System.out.println("[Error]Input the number please");
				sc.nextLine();
				continue;
			}
			if (oneOrTwo == 2) {
				System.out.println("Return to main");
				manager.noticeoff(login);
				for (VocaList vocalist : shareList) {
					vocalist.setEasyNumber(0);
				}
				return;
			} else if (oneOrTwo == 1) {
				System.out.println("● Saved this list !");
				list.setId(login.getId());
				AGAIN: do {
					System.out.print("Input the list name_");
					ArrayList<VocaList> check = manager.loadListByDate(login.getId());
					listName = sc.nextLine();
					for (VocaList l : check) {
						if (listName.equals(l.getListName())) {
							System.out.println("This name is already existed");
							listName = null;
							continue AGAIN;
						}
					}
				} while (listName == null);
				list.setListName(listName);
				manager.insertList(list);
				for (Voca voca : vocas) {
					voca.setListName(list.getListName());
					voca.setListNo(list.getListNo());
					manager.insertWordInList(voca);
				}
			} else {
				System.out.println("[Error]Wrong number choice");
			}
			System.out.println("Return to main");
			manager.noticeoff(login);
			for (VocaList vocalist : shareList) {
				vocalist.setEasyNumber(0);
			}
			return;
		}
	}
	public void usingTranslater() {
		list = new ArrayList<>();
		String japan=null;
		String hira=null;
		String mean=null;
		String hira1=null;
		list = new ArrayList<>();
		System.out.println("= Make Voca List =");				
		String listName = null;
		AGAIN: do {
			System.out.print("Input the list name_");
			ArrayList<VocaList> check = manager.loadListByDate(login.getId());
			listName = sc.nextLine();
			for (VocaList list : check) {
				if (listName.equals(list.getListName())) {
					System.out.println("This name is already existed");
					listName = null;
					continue AGAIN;
				}
			}
		} while (listName == null);
		int count = 1;
		while (true) {
			System.out.println("1. 한->일");
			System.out.println("2. 일->한");
			System.out.println();
			int select = this.getIntValue("입력=> ");
			switch(select) {
			case 1:
				System.out.printf("[%d] 단어를 한국어로 입력하세요\n",count);
				mean = sc.nextLine();
				japan = kor.translate(mean);
				System.out.println("일본어 :"+japan+", 한국어 : "+mean);
				System.out.println("가나를 추가로 입력하시겠습니까?");				
				int docchi = this.getIntValue("1.yes  2.no  _");
				switch(docchi) {
				case 1:
					System.out.println("hiragana/katakana 입력");
					hira = sc.nextLine();
					break;
				case 2:
					hira1 = japan;
					break;
				default:
					System.out.println("[Error]Wrong number choice");
					System.out.println("Return to main");
				}
				break;
			case 2:
				System.out.printf("[%d] 단어를 일본어로 입력하세요\n",count);
				japan = sc.nextLine();
				mean = jap.translate(japan);
				System.out.println("일본어 :"+japan+", 한국어 : "+mean);
				System.out.println("가나를 추가로 입력하시겠습니까?");				
				docchi = this.getIntValue("1.yes  2.no  _");
				switch(docchi) {
				case 1:
					System.out.println("hiragana/katakana 입력");
					hira = sc.nextLine();
					break;
				case 2:
					hira1 = japan;
					break;
				default:
					System.out.println("[Error]Wrong number choice");
					System.out.println("Return to main");
				}
				break;
			default:
				System.out.println("[Error]Wrong number choice");
				System.out.println("Return to main");
				return;
			}
			
			if(hira==null) {
				value1 = new Voca(listName, japan,hira1,mean);
				list.add(value1);
				count++;
			}else{
			value1 = new Voca(listName, japan, hira, mean);
			list.add(value1);
			count++;
			}
			

			if (count > 2) {
				System.out.print("To finish this list : 0");
				String choice = sc.next();
				if (choice.equals("0")) {
					// 단어장 이름 생성
					value2 = new VocaList(login.getId(), listName);
					manager.insertList(value2);

					// 단어장 안의 단어들 생성
					for (Voca list : list) {
						manager.insertWordInList(list);
					}
					break;
				}
			}
		}
	}
	
	public void changeListName() {
		System.out.println("\n( ˚ ∇˚)つ-●●● Change the list name");
		vocaList = manager.loadListByDate(login.getId());
		System.out.println("My Book List ");
		// 비어있을 때 empty출력
		if (vocaList.isEmpty()) {
			System.out.println("●● Empty ●●");
			return;
		}
		// 비어있지 않으면 소유한list의 이름들 출력
		for (VocaList list : vocaList) {
			list.showMyList();
		}
		// list 선택
		System.out.print("\nChoose the book's name_");
		String listName = sc.nextLine();
		int listNo = 0;
		for (VocaList list : vocaList) {
			if (listName.equals(list.getListName())) {
				listNo = list.getListNo();
				System.out.println("[New name]");
				System.out.print(listName+" == > ");
				listName = sc.nextLine();
				System.out.println("Are you sure to change the name?");
				System.out.println("(1) Yes (2) No");
				int choice = getIntValue("Choice_");
				if (choice == 2) {
					System.out.println("● Cancle");
					return;
				} else if (choice < 0 || 2 < choice) {
					System.out.println("[Error]Wrong number choice");
					return;
				}
			}
		}
		
		value2 = new VocaList(login.getId(), listNo, listName);
		if(manager.changeListName(value2) == 1 &&
				manager.changeListName2(value2) != 0) {
			System.out.println("● Changing succeed");
		} else {
			System.out.println("[Error] Fail");
		}
		
	}
}
