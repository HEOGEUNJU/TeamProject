package teamProject.reservation;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import oracle.jdbc.driver.OracleDriver;

public class ReservDAO {

	/**
	 * 예약테이블에 예약 정보 저장
	 * 
	 * @param reservinfo 예약정보
	 * @return 0이면 오류
	 * @throws Exception
	 */
	public int insertReservInfo(CourseInfoVO courseInfo) throws Exception {
		// 0. 드라이버 로딩
		DriverManager.registerDriver(new OracleDriver()); // 드라이버를 로딩
		// 1. 접속
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		// 2. 쿼리 작성
		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT");
		String sql = builder.toString();

		// 3.
		PreparedStatement statement = connection.prepareStatement(sql);
		// statement.setInt(1, new C);

		int result = statement.executeUpdate();

		// 4. 자원 반납
		statement.close();
		connection.close();
		return result;
	}

	/**
	 * 입력받은 도착지, 날짜, 탑승자수 를 항공편 정보 얻기 *
	 * 
	 * @param input 도착지, 날짜, 탑승자수
	 * @return list 항공편 리스트 List<CourseInfoVO>
	 * @throws Exception
	 */
	public List<CourseInfoVO> getCourseInfo(CourseInfoVO courseInfo) throws Exception {
		DriverManager.registerDriver(new OracleDriver()); // 드라이버를 로딩
		Connection connection = DriverManager.getConnection("jdbc:oracle:thin:@192.168.35.43:1521:xe", "ks95", "java");

		StringBuilder builder = new StringBuilder();
		builder.append(" SELECT");
		builder.append("     a.course_id,");
		builder.append("     a.dep_location,");
		builder.append("     a.dep_date,");
		builder.append("     a.dep_time,");
		builder.append("     a.airport_id,");
		builder.append("     a.arr_time,");
		builder.append("     a.airplane_id,");
		builder.append("     a.price,");
		builder.append("     a.distance,");
		builder.append("     b.airline,");
		builder.append("     b.seat_remain");
		builder.append(" FROM");
		builder.append("     course a,");
		builder.append("     airplane b");
		builder.append(" WHERE");
		builder.append("     a.airplane_id = b.airplane_id");
		builder.append("     AND   a.airport_id =?");
		builder.append("         AND   a.dep_date = to_date(?,'YYYY/MM/DD')");
		builder.append("             AND   b.seat_remain >=?");
		String sql = builder.toString();

		PreparedStatement statement = connection.prepareStatement(sql);
		statement.setString(1, courseInfo.getAirportID());
		statement.setString(2, courseInfo.getDepartDate());
		statement.setInt(3, courseInfo.getNumPassengers());

		ResultSet resultSet = statement.executeQuery();
		List<CourseInfoVO> list = new ArrayList<>();
		while (resultSet.next()) {
			list.add(new CourseInfoVO(resultSet.getString("a.course_id"), 
				resultSet.getString("a.dep_location"),
				resultSet.getString("a.dep_date"), 
				resultSet.getString("a.dep_time"),
				resultSet.getString("a.airport_id"), 
				resultSet.getString("a.arr_time"),
				resultSet.getString("a.airplane_id"), 
				resultSet.getInt("a.price"), 
				resultSet.getString("a.distance"),
				resultSet.getString("b.airline"), 
				resultSet.getInt("b.seat_remain")));
		}

		resultSet.close();
		statement.close();
		connection.close();
		return list;
	}

}
