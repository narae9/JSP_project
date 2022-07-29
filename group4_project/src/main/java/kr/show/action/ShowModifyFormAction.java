package kr.show.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowVO;

public class ShowModifyFormAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		request.setCharacterEncoding("utf-8");
		
		int sh_key = Integer.parseInt(request.getParameter("sh_key"));
	

		ShowDAO dao = ShowDAO.getInstance(); 
		ShowVO show = dao.showDetail(sh_key);
		
		request.setAttribute("show", show);
		
		return "/WEB-INF/views/show/showDetailModifyForm.jsp";
	}
}
