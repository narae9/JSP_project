package kr.community.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.community.dao.CommunityDAO;
import kr.community.vo.CommunityVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		int co_key = Integer.parseInt(request.getParameter("co_key"));
		
		CommunityDAO dao = CommunityDAO.getInstance();
		
		dao.updateReadcount(co_key);
		
		CommunityVO comm = dao.getBoard(co_key);
		
		comm.setCo_title(StringUtil.useNoHtml(
				                    comm.getCo_title()));
		comm.setCo_write(StringUtil.useBrNoHtml(
				                   comm.getCo_write()));
		
		request.setAttribute("comm", comm);
		
		return "/WEB-INF/views/community/detail.jsp";
	}

}





