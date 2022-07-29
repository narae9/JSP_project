package kr.calendar.action;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.codehaus.jackson.map.ObjectMapper;

import kr.calendar.dao.CalendarDAO;
import kr.controller.Action;
import kr.show.vo.ShowVO;


public class CalendarAction implements Action{
	
	/*SimpleDateFormat format1 = new SimpleDateFormat ("yyyy-MM");
	Date time = new Date();
	String time1 = format1.format(time);*/
	
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
				
		int y = Integer.parseInt(request.getParameter("y"));
		int m = Integer.parseInt(request.getParameter("m"));	

		CalendarDAO dao = CalendarDAO.getInstance();
		List<ShowVO> list = null;
		list = dao.getShow(y,m);
		
		Map<String,Object> mapAjax = new HashMap<String,Object>();
		mapAjax.put("list", list);
		
		ObjectMapper mapper = new ObjectMapper();
		String ajaxData = mapper.writeValueAsString(mapAjax);
		
		
		request.setAttribute("ajaxData", ajaxData);
		
		return "/WEB-INF/views/common/ajax_view.jsp";
	}
}
