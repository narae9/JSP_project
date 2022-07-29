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

public class WriteReplyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String,String> mapAjax = 
				              new HashMap<String,String>();
		
		HttpSession session = request.getSession();
		Integer user_num = 
				(Integer)session.getAttribute("user_num");
		if(user_num==null) {
			mapAjax.put("result", "logout");
		}else {
			request.setCharacterEncoding("utf-8");
			
			BoardReplyVO reply = new BoardReplyVO();
			reply.setMe_key(user_num);
			reply.setBom_write(request.getParameter("bom_write"));
			reply.setBo_key(Integer.parseInt(request.getParameter("bo_key")));
			
			BoardDAO dao = BoardDAO.getInstance();
			dao.insertReplyBoard(reply);
			
			mapAjax.put("result", "success");
		}
		
		//JSON 데이터 생성
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = 
				mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
