package kr.show.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowVO;
import kr.util.FileUtil;

public class ShowModifyAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
	
		MultipartRequest multi = FileUtil.createFile(request);
		HttpSession session = request.getSession();
		Integer me_key = (Integer)session.getAttribute("me_key");
		int sh_key = Integer.parseInt(multi.getParameter("sh_key"));
		
		Map<String,String> mapAjax = new HashMap<String, String>();
		
		if(me_key == null) {//로그인이 되지 않은 경우
			mapAjax.put("result", "logout");
			return "/WEB-INF/views/member/loginForm.jsp";
		}
		request.setCharacterEncoding("utf-8");
		
		ShowVO show = new ShowVO();
		show.setSh_key(sh_key);
		show.setSh_title(multi.getParameter("sh_title"));
		show.setSh_detail(multi.getParameter("sh_detail"));
		show.setSh_place(multi.getParameter("sh_place"));
		show.setSh_time(multi.getParameter("sh_time"));
		show.setSh_date(multi.getParameter("sh_date"));
		show.setSh_img(multi.getParameter("sh_img"));
		
		ShowDAO dao = ShowDAO.getInstance();
		dao.showDetailModify(show);
		
		return "/WEB-INF/views/show/showModify.jsp";
	}
}
