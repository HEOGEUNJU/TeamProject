package teamProject.check;

import java.util.Scanner;

public class CheckApplication {
	public static void main(String[] args) throws Exception {
		CheckView view = new CheckView();
		Scanner scanner = new Scanner(System.in);
		CheckController checkController = new CheckController();
		
		menu: while (true) {
			view.init();
			int menu = Integer.parseInt(scanner.nextLine());
			switch (menu) {
			case 1:
				break;
			case 2:
				view.viewCheck(checkController);
				if ( view.getAnswer().equals("Y")) {
					view.viewCheck(checkController, scanner);	
				} else if ( view.getAnswer().equals("N")) {
					break;
				} else {
					System.out.println("프로그램을 종료합니다.");
					break menu;
				}
			case 3:
				break;
			}
		}
	}
}