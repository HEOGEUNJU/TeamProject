package teamProject.check;

import java.util.List;

public class CheckController {
	CheckSerivce service = new CheckSerivce();

	public List<CheckVO> checkList(String memId) throws Exception {
		return service.checkList(memId);
	}

	public CheckVO checkRes(String typeReservId) throws Exception {
		return service.checkRes(typeReservId);
	}
}