package kr.show.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;

public class ShowDetailFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, String> mapAjax = new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer me_num = (Integer)session.getAttribute("me_num");
//		if(me_num==null) {
//			mapAjax.put("result", "logout");
//		}
		
		
		
		return "/WEB-INF/views/show/showDetailForm.jsp";
	}
}
