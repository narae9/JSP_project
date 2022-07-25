package kr.community.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.vo.BoardVO;
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
//		dao.updateReadcount(board_num);
		
//		//글상세 정보 반환
//		CommunityVO comm = dao.get(co_key);
//		
//		//HTML를 허용하지 않음
//		comm.setBo_title(StringUtil.useNoHtml(
//				                    comm.getC()));
//		//HTML를 허용하지 않으면서 줄바꿈 처리
//		comm.setBo_write(StringUtil.useBrNoHtml(
//				                   comm.getBo_write()));
		
//		request.setAttribute("comm", comm);
		
		return "/WEB-INF/views/community/detail.jsp";
	}

}





