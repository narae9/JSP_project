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
		
		//글번호 반환
		int co_key = Integer.parseInt(
				        request.getParameter("co_key"));
		CommunityDAO dao = CommunityDAO.getInstance();
		//조회수 증가
//		dao.updateReadcount(co_key);
		//글상세 정보 반환
		CommunityVO comm = dao.getBoard(co_key);
		
		//HTML를 허용하지 않음
		comm.setCo_title(StringUtil.useNoHtml(
				                    comm.getCo_title()));
		//HTML를 허용하지 않으면서 줄바꿈 처리
		comm.setCo_write(StringUtil.useBrNoHtml(
				                   comm.getCo_write()));
		
		request.setAttribute("comm", comm);
		
		return "/WEB-INF/views/community/detail.jsp";
	}

}





