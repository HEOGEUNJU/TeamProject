package teamProject.reservation;

import java.util.List;

public class ReservController {
	private static ReservController instance = new ReservController();
	public static ReservController getInstance() {
		return instance;
	}
	private ReservController() { }
	
	private ReservService service = ReservService.getInstance();

	/**
	 * country index
	 * 1. 베이징	2. 동경		3. 하노이
	 * date index
	 * 1. 2022/09/14	2. 2022/09/15	3.2022/09/16
	 * @param country 나라 인덱스
	 * @param date	날짜 인덱스
	 * @param num 탑승자수
	 * @return	항공편 리스트
	 * @throws Exception
	 */
	public List<CourseInfoVO> getCourseInfo(int country, int date, int num) throws Exception {
		CourseInfoVO vo = new CourseInfoVO();
		//도착지
		switch (country) {
		case 1:
			vo.setAirportID("PEK");
			break;
		case 2:
			vo.setAirportID("NRT");
			break;
		case 3:
			vo.setAirportID("HAN");
			break;
		}
		//날짜
		switch (country) {
		case 1:
			vo.setDepartDate("2022/09/14");
			break;
		case 2:
			vo.setAirportID("2022/09/15");
			break;
		case 3:
			vo.setAirportID("2022/09/16");
			break;
		}
		//탑승자수
		vo.setNumPassengers(num);
		return service.getCourseInfo(vo);
	}
	
	
}