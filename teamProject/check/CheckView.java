package teamProject.check;

import java.util.List;
import java.util.Scanner;

public class CheckView {
	Scanner scanner = new Scanner(System.in);
	private String answer;
	private String id;

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public void init() {
		System.out.println("-----------------------------");
		System.out.println("1.예약하기|2.예약 목록|3.예약 취소|4.종료");
		System.out.print("메뉴를 입력하세요: ");
	}

	public void viewCheck(CheckController controller) throws Exception {
		System.out.println("| 예약번호 | 탑승자 | 좌석번호 | 출발지 | 출발일 | 출발시간 | 도착지 | 도착시간 | 항공사 | 비행기 번호 |");
		List<CheckVO> list = controller.checkList(id);
		for (CheckVO resList : list) {
			System.out.println(resList);
		}
		System.out.print("세부 내역을 보싈??!(Y) 메뉴로 돌아가싈?!(N) 종료하싈?(0): ");
		answer = scanner.nextLine();
	}

	public void viewCheck(CheckController controller, Scanner scanner) throws Exception {
		System.out.print("보고 싶은 예약번호 입력: ");
		String typeReservId = scanner.nextLine();
		CheckVO vo = controller.checkRes(typeReservId);
		System.out.println(
				"| 예약번호 | 회원번호 | 탑승자 | 전화번호 | 생년월일 | 좌석번호 | 출발지 | 출발일 | 출발시간 | 도착지 | 도착시간 | 항공사 | 비행기 번호 | 가격 | 계좌번호 | 은행 |");
		System.out.println(vo);
	}
}
