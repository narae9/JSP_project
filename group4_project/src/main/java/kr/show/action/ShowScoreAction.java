package kr.show.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowReviewVO;

public class ShowScoreAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		if(user_num==null) {
			return "/WEB-INF/views/member/loginForm.jsp";
		}
		
		
		ShowReviewVO showRe = new ShowReviewVO();
		
		int score = Integer.parseInt(request.getParameter("score"));
		int sh_key = Integer.parseInt(request.getParameter("sh_key"));
		
		showRe.setSre_gpa(score);
		showRe.setSh_key(sh_key);
		showRe.setMe_key(user_num);
		
		ShowDAO dao = ShowDAO.getInstance();
		dao.showInsertGPA(showRe);
		
		
		return "redirect:/show/showReservationForm.do";
	}
}
