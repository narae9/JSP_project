package kr.board.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardReplyVO;
import kr.controller.Action;

public class UpdateReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		int bom_key = Integer.parseInt(request.getParameter("bom_key"));
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardReplyVO db_reply = dao.getReplyBoard(bom_key);
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		
		Map<String,String> mapAjax = new HashMap<String,String>();
		
		if(user_num==null) { //로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
		} else if(user_num != null && user_num == db_reply.getMe_key()) {
			BoardReplyVO reply = new BoardReplyVO();
			reply.setBom_key(bom_key);
			reply.setBom_write(request.getParameter("bom_write"));
			
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
