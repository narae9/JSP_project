package kr.community.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.oreilly.servlet.MultipartRequest;

import kr.community.dao.CommunityDAO;
import kr.community.vo.CommunityVO;
import kr.controller.Action;
import kr.util.FileUtil;

public class UpdateAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num==null) {
			return "redirect:/member/loginForm.do";
		}
		
		MultipartRequest multi = FileUtil.createFile(request);
		int co_key = Integer.parseInt(multi.getParameter("co_key"));
		
		CommunityDAO dao = CommunityDAO.getInstance();
		CommunityVO db_board = dao.getBoard(co_key);
		if(user_num != db_board.getMe_key()) {
			return "/WEB-INF/views/common/notice.jsp";
		}
		
		CommunityVO comm = new CommunityVO();
		comm.setCo_key(co_key);
		comm.setCo_title(multi.getParameter("co_title"));
		comm.setCo_write(multi.getParameter("co_write"));
		
		dao.updateBoard(comm);
		
		return "redirect:/community/detail.do?co_key="+co_key;
	}

}




