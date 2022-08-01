package kr.show.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowVO;


public class ShowDetailFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
//	    HttpSession session = request.getSession();
//		Integer user_num = (Integer)session.getAttribute("user_num");
		
		int sh_key = Integer.parseInt(request.getParameter("sh_key"));
		request.setCharacterEncoding("utf-8");
			
		
		ShowDAO dao = ShowDAO.getInstance();
		ShowVO show = dao.showDetail(sh_key);
		
		request.setAttribute("show", show);
//		request.setAttribute("user_num", user_num);
		
		return "/WEB-INF/views/show/showDetailForm.jsp";
	}
}
