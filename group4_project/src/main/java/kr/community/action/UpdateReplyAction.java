package kr.community.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.community.dao.CommunityDAO;
import kr.community.vo.CommunityReplyVO;
import kr.controller.Action;

public class UpdateReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		
		int com_key = Integer.parseInt(request.getParameter("com_key"));
		
		CommunityDAO dao = CommunityDAO.getInstance();
		CommunityReplyVO db_reply = dao.getReplyBoard(com_key);
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		if(user_num==null) {//로그인이 되지 않은 경우
			mapAjax.put("result","logout");
		}else if(user_num!=null && user_num == db_reply.getMe_key()) {
			CommunityReplyVO reply = new CommunityReplyVO();
			reply.setCom_key(com_key);
			reply.setCom_write(request.getParameter("com_write"));
			
			dao.updateReplyBoard(reply);
			
			mapAjax.put("result", "success");
			
		}else {
			mapAjax.put("result", "wrongAccess");
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
