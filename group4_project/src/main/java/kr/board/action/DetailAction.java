package kr.board.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;
import kr.util.StringUtil;

public class DetailAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		//글번호 반환
		int bo_key = Integer.parseInt(
				        request.getParameter("bo_key"));
		BoardDAO dao = BoardDAO.getInstance();
		
		//조회수 증가
//		dao.updateReadcount(board_num);
		
		//글상세 정보 반환
		BoardVO board = dao.getBoard(bo_key);
		
		//HTML를 허용하지 않음
		board.setBo_title(StringUtil.useNoHtml(
				                    board.getBo_title()));
		//HTML를 허용하지 않으면서 줄바꿈 처리
		board.setBo_write(StringUtil.useBrNoHtml(
				                   board.getBo_write()));
		
		request.setAttribute("board", board);
		
		return "/WEB-INF/views/board/detail.jsp";
	}

}





