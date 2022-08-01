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
		
		HttpSession session = request.getSession();
		Integer me_key = (Integer)session.getAttribute("me_key");
//자바스크립트로 로그인 안되어있을 때 예매 버튼 비활성화
//		if(me_key==null) {
//			mapAjax.put("result", "logout");
//		}
		
		//MultipartRequest multi = FileUtil.createFile(request);
		
		
		ShowDAO dao = ShowDAO.getInstance();
		ShowVO show = dao.showDetail(sh_key);
		
		request.setAttribute("show", show);
		
		return "/WEB-INF/views/show/showDetailForm.jsp";
	}
}
