package kr.show.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowVO;

public class ShowDeleteAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int sh_key = Integer.parseInt(request.getParameter("sh_key"));
		
		ShowDAO dao = ShowDAO.getInstance();
		dao.showDelete(sh_key);
		
		return "/WEB-INF/views/show/showDeleteFrom.jsp";
	}
}
