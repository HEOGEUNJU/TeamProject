package teamProject.reservation;

import java.util.List;

public class ReservService {
	private static ReservService instance = new ReservService();
	
	public static ReservService getInstance() {
		return instance;
	}
	
	private ReservService() { }
	
	
	private ReservDAO dao = new ReservDAO();
	
	public List<CourseInfoVO> getCourseInfo(CourseInfoVO courseInfo) throws Exception {
		return dao.getCourseInfo(courseInfo);
	}
}
