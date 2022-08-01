package kr.show.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.show.dao.ShowDAO;


public class ReserveShowAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer me_key = (Integer)session.getAttribute("me_key");
		Map<String,String> mapAjax = new HashMap<String, String>();
		
		if(me_key==null) {
			mapAjax.put("result", "logout");
			return "/WEB-INF/views/member/login.jsp";
		}
		
		
		int re_spon = Integer.parseInt(request.getParameter("re_spon"));		
		int sh_key = Integer.parseInt(request.getParameter("sh_key"));
		
		
		ShowDAO dao = ShowDAO.getInstance();
		dao.reserveShow(re_spon,sh_key,0, me_key); //0예약 /  1취소

		
		return "/WEB-INF/views/show/showReserveResult.jsp";
	}
}
