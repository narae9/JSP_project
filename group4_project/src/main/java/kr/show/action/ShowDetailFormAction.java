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

public class ShowDetailFormAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		Map<String, String> mapAjax = new HashMap<String,String>();
		
		int sh_key = Integer.parseInt(request.getParameter("sh_key"));
		request.setCharacterEncoding("utf-8");
			
		
		ShowDAO dao = ShowDAO.getInstance();
		ShowVO show = dao.showDetail(sh_key);
		
		request.setAttribute("show", show);
		
		return "/WEB-INF/views/show/showDetailForm.jsp";
	}
}
