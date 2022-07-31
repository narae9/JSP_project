package kr.community.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.community.dao.CommunityDAO;
import kr.community.vo.CommunityFavVO;
import kr.controller.Action;

public class GetFavAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		request.setCharacterEncoding("utf-8");
		int co_key = Integer.parseInt(request.getParameter("co_key"));
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		CommunityDAO dao = CommunityDAO.getInstance();
		if(user_num==null) {
			mapAjax.put("status", "noFav");
			mapAjax.put("count", dao.selectFavCount(co_key));
		}else {
			CommunityFavVO boardFav = dao.selectFav(co_key, user_num);
			
			if(boardFav!=null) {
				mapAjax.put("status", "yesFav");
				mapAjax.put("count", dao.selectFavCount(co_key));
			}else {
				mapAjax.put("status", "noFav");
				mapAjax.put("count", dao.selectFavCount(co_key));
			}
		}
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}

}
