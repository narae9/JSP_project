package kr.main.action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.board.dao.BoardDAO;
import kr.board.vo.BoardVO;
import kr.controller.Action;

public class MainAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		
		BoardDAO boardDao = BoardDAO.getInstance();
		List<BoardVO> boardList = 
				 boardDao.getMainListBoard(1, 10);
		
		request.setAttribute("boardList", boardList);
		
		return "/WEB-INF/views/main/main.jsp";
	}

}
