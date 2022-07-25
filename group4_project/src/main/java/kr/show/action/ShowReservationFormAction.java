package kr.show.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowVO;
import kr.util.PagingUtil;

public class ShowReservationFormAction implements Action{
   
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		String keyfield = request.getParameter("sh_title");
		String keyword = request.getParameter("keyword");
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null) pageNum ="1";
		
		HttpSession session = request.getSession();
		Integer me_num = (Integer)session.getAttribute("me_num");
//		if(me_num==null) {
//			return "/WEB-INF/views/member/loginForm.jsp";
//		}
		ShowDAO dao = ShowDAO.getInstance();
		
		int count = dao.getShowCount(keyfield , keyword);

		
		
		PagingUtil page = new PagingUtil(keyfield,keyword,
				Integer.parseInt(pageNum),count,5,10,"showListAction.do");
		
		List<ShowVO> list = dao.getListShow(keyfield, keyword, page.getStartRow(),  page.getEndRow() );
		

		request.setAttribute("count", count);
		request.setAttribute("list", list);
		request.setAttribute("page", page.getPage());
		
		return "/WEB-INF/views/show/showReservationForm.jsp";
	}
}
