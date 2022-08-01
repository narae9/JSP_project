package kr.show.action;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.controller.Action;
  
public class ShowInsertFormAction implements Action{
   @Override
   public String execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
      
      HttpSession session = request.getSession();
      Integer me_key = (Integer)session.getAttribute("me_key");
      Map<String,String> mapAjax = new HashMap<String, String>();
      
      if(me_key==null) {
    	 mapAjax.put("result", "logout");
    	 return "redirect:/views/member/login.do";
      }
      
      return "/WEB-INF/views/show/showInsertForm.jsp";
      
   }
}