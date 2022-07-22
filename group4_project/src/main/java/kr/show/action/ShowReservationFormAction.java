package kr.show.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowVO;

public class ShowReservationFormAction implements Action{
   
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer me_num = (Integer)session.getAttribute("me_num");
//		if(me_num==null) {
//			return "/WEB-INF/views/member/loginForm.jsp";
//		}
		
		String keyword = request.getParameter("keyword");
		
		ShowDAO dao = ShowDAO.getInstance();
		
		List<ShowVO> list = dao.getListShow(keyword);
		
		request.setAttribute("list", list);
		
		return "/WEB-INF/views/show/showReservationForm.jsp";
	}
}
