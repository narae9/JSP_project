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
//			return null;
//		}
		int re_spon = Integer.parseInt(request.getParameter("re_spon"));
		int sh_key = Integer.parseInt(request.getParameter("sh_key"));
		
		ReserveVO reserve = new ReserveVO();
		reserve.setRe_spon(re_spon);
		
		ShowDAO dao = ShowDAO.getInstance();
		reserve = dao.reserveShow(reserve,sh_key);
		
		request.setAttribute("reserve", reserve);
		
		
		return "/WEB-INF/views/show/showReserveResult.jsp";
	}
}
