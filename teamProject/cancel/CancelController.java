package teamProject.cancel;

import java.util.List;

public class CancelController {
   //서비스라는 객체를 생성해주고
   CancelService service = new CancelService();
   //예약정보 목록을 보여준다
   public int updateMileage(String deleteRe) throws Exception {
		return service.updateMileage(deleteRe);
	}
   
   //예약정보를 삭제해준다
   public int deleteReserve(String deleteRe) throws Exception{
      return service.deleteReserve(deleteRe);
   }
}