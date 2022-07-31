package kr.community.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.community.dao.CommunityDAO;
import kr.community.vo.CommunityVO;
import kr.controller.Action;

public class UpdateFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		int co_key = Integer.parseInt(request.getParameter("co_key"));
		CommunityDAO dao = CommunityDAO.getInstance();
		CommunityVO comm = dao.getBoard(co_key);
		if(user_num != comm.getMe_key()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		request.setAttribute("comm", comm);
		
		return "/WEB-INF/views/community/updateForm.jsp";
	}

}





