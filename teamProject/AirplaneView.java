package teamProject;

import java.util.List;
import java.util.Scanner;

import teamProject.cancel.CancelController;
import teamProject.cancel.CancelVO;
import teamProject.check.CheckController;
import teamProject.check.CheckVO;
import teamProject.login.LoginController;
import teamProject.reservation.CourseInfoVO;
import teamProject.reservation.ReservController;
import teamProject.utils.ScanUtil;

public class AirplaneView {

private LoginController loginController = LoginController.getInstance();
   private ReservController reservController = ReservController.getInstance();
   private CancelController cancelController = new CancelController();
   

   private String id;
   private String passwd;
   private boolean isLogin = false;

   /**
    * 로그인 화면 scanner로 로그인 정보 받음 db에 저장된 로그인 정보랑 비교해서 로그인 성공 여부 리턴
    * 
    * @return 로그인 성공 여부
    * @throws Exception
    */
   public boolean login() throws Exception {
      if (!isLogin) {
         System.out.println(">>>>>>>>>로그인<<<<<<<<<");
         System.out.print("아이디 > ");
         id = ScanUtil.nextLine();
         System.out.print("비밀번호 > ");
         passwd = ScanUtil.nextLine();
         if (loginController.isLoginSuccess(id, passwd)) {
            System.out.println("로그인 성공");
            isLogin = true;
            return true;
         } else {
            System.out.println("로그인 실패");
            isLogin = false;
            return false;
         }
      }
      return true;
   }

   /**
    * 메인메뉴
    * 
    * @return 선택한 메뉴번호
    */
   public int menu() {
      System.out.println(">>>>>>>>>메뉴<<<<<<<<<");
      System.out.println("| 1. 예약 | 2. 예약취소 | 3. 예약확인 | 0. 종료 |");
      System.out.print("메뉴 선택 >> ");
      return ScanUtil.nextInt();
   }

   /**
    * 예약 진행
    * 
    * @throws Exception
    */
   public void reservation() throws Exception {
      System.out.println(">>>>>>>>>예약<<<<<<<<<");

      System.out.println(">>>>>>>>>행선지선택<<<<<<<<<");
      System.out.println("1. 베이징");
      System.out.println("2. 동경");
      System.out.println("3. 하노이");
      System.out.print("도착지 선택 >> ");
      int country = ScanUtil.nextInt();

      System.out.println(">>>>>>>>>날짜선택<<<<<<<<<");
      System.out.println("1. 2022/09/14");
      System.out.println("2. 2022/09/15");
      System.out.println("3. 2022/09/16");
      System.out.print("날짜 선택 >> ");
      int date = ScanUtil.nextInt();

      System.out.println(">>>>>>>>>탑승객 수 입력<<<<<<<<<");
      System.out.print(" >> ");
      int num = ScanUtil.nextInt();

      // 시간 리스트 출력
      printCourseList(country, date, num);
      System.out.printf("항공편ID  입력 >>");
      String courseID = ScanUtil.nextLine();
      // 항공편의 비행기의 좌석 리스트 반환
      // 좌석 리스트 출력
      printSeats();
      // db 에 저장
   }

   /**
    * 항공편 리스트 표로 출력
    * 
    * @param country 도착지
    * @param date    날짜
    * @param num     탑승수
    * @throws Exception
    */
   public void printCourseList(int country, int date, int num) throws Exception {
      List<CourseInfoVO> list = reservController.getCourseInfo(country, date, num);
      System.out.println("| 항공편ID | 출발지 |  출발일자  | 출발시간 | 도착지 | 도착시간 | 잔여 좌석 |    항공사   |  거리  |   금액   |");
      for (int i = 0; i < list.size(); i++) {
         System.out.printf("| %s |  %s   | %s |  %s  |  %s  |  %s  |    %d   | %s | %s | %d |\n",
               list.get(i).getCourseId(), list.get(i).getDeparture(), list.get(i).getDepartDate(),
               list.get(i).getDepartTime(), list.get(i).getAirportID(), list.get(i).getArrivalTime(),
               list.get(i).getRemainSeat(), list.get(i).getAirline(), list.get(i).getDistance(),
               list.get(i).getPrice());
      }
   }

   public void printSeats() {
      String[][] seats = new String[6][4];
      System.out.println("    A  B   C  D");
      for (int i = 0; i < seats.length; i++) {
         System.out.printf("%d : ", i + 1);
         for (int j = 0; j < seats[i].length; j++) {
            System.out.print(" □");
         }
         System.out.println("");
      }
   }
//-------------------------------------------------------------------------------------------------------------------------
    //예약취소
   public void cancelMileage() throws Exception{
	   cancel();
	   getCancelNumber();
   }
	   
  
    public void cancel() throws Exception{
         // 예약목록 확인
         System.out.println(">>>>>>>>>예약취소<<<<<<<<<");
         System.out.println(">예약목록<");
         CheckList(new CheckController());
    }
   //예약취소
    public void getCancelNumber() throws Exception{
         // 취소할 예약번호 받기
       System.out.print("취소할 예약번호를 입력해주세요 ");
        String deleteRe = ScanUtil.nextLine();
        //마일리지 차감
        cancelController.updateMileage(deleteRe);   
        //예약내역 취소
//      cancelController.deleteReserve(deleteRe);
        System.out.println("예약을 취소합니다");
			
        }      
	
   
   // 목록 확인--------------------------------------------------------------------------------------------------------------------------//

      /**
       * 회원 아이디를 토대로 나오는 예약 목록(간이) 10개
       * 
       * @param controller
       * @throws Exception
       */
      public void CheckList(CheckController controller) throws Exception {
         List<CheckVO> list = controller.checkList(id);
         System.out.println("|     예약번호    | 탑승자 | 좌석번호 | 출발지  |    출발일   | 출발시간 |  도착지 | 도착시간 |    항공사   | 비행기 번호 |");
         for (int i = 0; i < list.size(); i++) {
            System.out.printf("| %s | %s |   %s   |  %s  | %s |  %s |  %s  |  %s | %s |   %s   |\n",
                  list.get(i).getReservId(), list.get(i).getPassName(), list.get(i).getSeatNo(),
                  list.get(i).getDepLocation(), list.get(i).getDepDate(), list.get(i).getDepTime(),
                  list.get(i).getAirportId(), list.get(i).getArrTime(), list.get(i).getAirline(),
                  list.get(i).getAirplaneId());
         }
      }

      public String Choice() {
         System.out.print("세부 내역을 보싈??!(Y) 메뉴로 돌아가싈?!(N) 종료하싈?(0): ");
         return ScanUtil.nextLine();
      }
      // ^선택한 거에 따라 진행하는 것

      /**
       * 예약 목록을 통해 얻은 예약번호를 통해 세부 예약 정보 출력
       * 
       * @param controller
       * @param scanner
       * @throws Exception
       */
      public void CheckRes(CheckController controller, Scanner scanner) throws Exception {
         System.out.print("보고 싶은 예약번호 입력: ");
         String typeReservId = scanner.nextLine();
         CheckVO vo = controller.checkRes(typeReservId);
         System.out.println("-------------------------");
         System.out.printf(
               "예약번호\t: %s\n회원번호\t: %s\n-------------------------\n탑승자\t: %s\n전화번호\t: %s\n생년월일\t: %s\n-------------------------\n좌석번호\t: %s\n출발지\t: %s\n출발일\t: %s\n출발시간\t: %s\n도착지\t: %s\n도착시간\t: %s\n항공사\t: %s\n비행기번호\t: %s\n-------------------------\n가격\t: %d\n계좌번호\t: %s\n은행\t: %s\n",
               vo.getReservId(), vo.getMemId(), vo.getPassName(), vo.getPassPhone(), vo.getPassReg(), vo.getSeatNo(),
               vo.getDepLocation(), vo.getDepDate(), vo.getDepTime(), vo.getAirportId(), vo.getArrTime(),
               vo.getAirline(), vo.getAirplaneId(), vo.getPrice(), vo.getAccount(), vo.getBank());
         System.out.println("-------------------------");
      }
   }