package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class LoginAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//전송된 데이터 인코딩
		request.setCharacterEncoding("utf-8");
		  
		//전송된 데이터 반환
		String id = request.getParameter("id");
		String pw = request.getParameter("passwd");

		MemberDAO dao = MemberDAO.getInstance();
		MemberVO member = dao.checkMember(id);
		boolean check = false;
		
		if(member!=null) {
			//비밀번호 일치 여부 
			check = member.isCheckedPassword(pw);
			//로그인 실패시 path 체크
			request.setAttribute("path", member.getMe_path());
		}
		
		if(check) {	//인증 성공
			//로그인 처리
			HttpSession session = request.getSession();
			session.setAttribute("user_name", member.getMe_name());
			session.setAttribute("user_id", member.getMe_id());
			session.setAttribute("user_path", member.getMe_path());
			
			return "redirect:/main/main.do";			
		}
		  
		//인증 실패시 호출
		return "/WEB-INF/views/member/login.jsp";
	}
	
}
