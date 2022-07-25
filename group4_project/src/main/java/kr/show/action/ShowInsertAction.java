package kr.show.action;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
import kr.show.dao.ShowDAO;
import kr.show.vo.ShowVO;

public class ShowInsertAction implements Action{

   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      HttpSession session = request.getSession();
      Integer me_num = (Integer)session.getAttribute("me_num");
      
      request.setCharacterEncoding("utf-8");
      
//      if(me_num==null) {
//         return "/WEB-INF/views/member/loginForm.jsp";
//      }
      
      //자바빈 생성
      ShowVO showVO = new ShowVO();
      
      //전송된 데이터를 자바빈에 저장
      showVO.setSh_title(request.getParameter("sh_title"));
      showVO.setSh_date(request.getParameter("sh_date"));
      showVO.setSh_time(request.getParameter("sh_time"));
      showVO.setSh_place(request.getParameter("sh_place"));
      showVO.setSh_detail(request.getParameter("sh_detail"));
      showVO.setSh_img(request.getParameter("sh_img"));
      
      ShowDAO dao = ShowDAO.getInstance();
      dao.showInsert(showVO);
      
      return "/WEB-INF/views/show/showInsertResult.jsp";
   }

}