package kr.show.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowVO;

public class ShowModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		HttpSession session = request.getSession();
		Integer me_num = (Integer)session.getAttribute("me_num");
		
		
//		if(user_num == null) {//로그인이 되지 않은 경우
//			return "/WEB-INF/views/member/loginForm.jsp";
//		}
		
		
		ShowVO show = new ShowVO();
		show.setSh_key(Integer.parseInt(request.getParameter("sh_key")));
		show.setSh_title(request.getParameter("sh_title"));
		show.setSh_detail(request.getParameter("sh_detail"));
		show.setSh_place(request.getParameter("sh_place"));
		show.setSh_time(request.getParameter("sh_time"));
		show.setSh_date(request.getParameter("sh_date"));
		show.setSh_img(request.getParameter("sh_img"));
		
		ShowDAO dao = ShowDAO.getInstance();
		dao.showDetailModify(show);
		
		return "/WEB-INF/views/show/showModify.jsp";
	}
}
