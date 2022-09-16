package teamProject.check;

import java.util.List;

public class CheckSerivce {
	CheckDAO dao = new CheckDAO();

	public List<CheckVO> checkList(String memId) throws Exception {
		return dao.checkList(memId);
	}

	public CheckVO checkRes(String typeReservId) throws Exception {
		return dao.checkRes(typeReservId);
	}

}