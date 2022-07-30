package kr.member.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kr.controller.Action;
import kr.member.dao.MemberDAO;
import kr.member.vo.MemberVO;


public class RegisterUserAction implements Action{
	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception{
		//전송된 데이터 인코딩 처리
		request.setCharacterEncoding("utf-8");
		
		//자바빈(VO) 생성
		MemberVO member = new MemberVO();
		
		//전송된 데이터 자바빈에 저장
		member.setMe_path(request.getParameter("path"));
		member.setMe_id(request.getParameter("id"));
		member.setMe_name(request.getParameter("name"));
		member.setMe_passwd(request.getParameter("passwd"));
		member.setMe_name(request.getParameter("name"));
		member.setMe_agecode(request.getParameter("agecode"));
		member.setMe_email(request.getParameter("email"));
		member.setMe_phone(request.getParameter("phone"));
		member.setMe_zipcode(request.getParameter("zipcode"));
		member.setMe_add1(request.getParameter("add1"));
		member.setMe_add2(request.getParameter("add2"));
		
		MemberDAO dao = MemberDAO.getInstance();
		dao.insertMember(member);
		
		return "/WEB-INF/views/member/registerUser.jsp";
	}
}
