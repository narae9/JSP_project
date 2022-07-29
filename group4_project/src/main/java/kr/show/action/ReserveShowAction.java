package kr.show.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ReserveVO;
import kr.show.vo.ShowVO;

public class ReserveShowAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer me_num = (Integer)session.getAttribute("me_num");
//		if(me_num==null) {
//			return /WEB-INF/views/member/login.jsp;
//		}
		
		int re_spon = Integer.parseInt(request.getParameter("re_spon"));
		
		int sh_key = Integer.parseInt(request.getParameter("sh_key"));
		
		
		ShowDAO dao = ShowDAO.getInstance();
		dao.reserveShow(re_spon,sh_key,0, 1); //0예약 /  1취소

		
		
		return "/WEB-INF/views/show/showReserveResult.jsp";
	}
}
