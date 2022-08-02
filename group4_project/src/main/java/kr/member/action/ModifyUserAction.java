package kr.member.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;

public class ModifyUserAction implements Action{

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		HttpSession session = request.getSession();
		Integer user_num = (Integer)session.getAttribute("user_num");
		if(user_num == null) {//로그인이 되지 않은 경우
			return "redirect:/member/loginForm.do";
		}
		
		//로그인 된 경우
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈(VO) 생성		//폼작성 후 수정 필요
		MemberVO member = new MemberVO();
		member.setMe_key(user_num);
		member.setMe_name(request.getParameter("name"));
		member.setMe_agecode(request.getParameter("agecode"));
		member.setMe_phone(request.getParameter("phone"));
		member.setMe_email(request.getParameter("email"));
		member.setMe_zipcode(request.getParameter("zipcode"));
		member.setMe_add1(request.getParameter("add1"));
		member.setMe_add2(request.getParameter("add2"));
		
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.updateMember(member);
		
		return "/WEB-INF/views/member/modifyUser.jsp";
	}

}




