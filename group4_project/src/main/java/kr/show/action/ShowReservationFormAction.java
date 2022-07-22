package kr.show.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class ShowReservationFormAction implements Action{
   
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer me_num = (Integer)session.getAttribute("me_num");
//		if(me_num==null) {
//			return "/WEB-INF/views/member/loginForm.jsp";
//		}
		
		
		
		return "/WEB-INF/views/show/showReservationForm.jsp";
	}
}
