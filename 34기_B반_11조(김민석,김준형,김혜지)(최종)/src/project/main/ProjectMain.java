package project.main;

import project.ui.VocaBookInsideUI;

public class ProjectMain {
	public static void main(String[] args) {
		VocaBookInsideUI ui2 = new VocaBookInsideUI();
		while(true) {
			ui2.login();
			ui2.start();
		}
	}
}
