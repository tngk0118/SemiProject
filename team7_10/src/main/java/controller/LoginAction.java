package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class LoginAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;
		MemberDAO mDAO= new MemberDAO();
		MemberVO mVO= new MemberVO();
		
		mVO.setMemberId(request.getParameter("memberId"));
		mVO.setMemberPw(request.getParameter("memberPw"));
		
		mVO = mDAO.selectOne(mVO);
		
		System.out.println("log : LoginAction : mVO = "+mVO);
		
		if(mVO==null) {
			forward = new ActionForward();
			request.setAttribute("title", "오류");
			request.setAttribute("msg", "로그인실패");
			request.setAttribute("icon", "error");
			request.setAttribute("confirmtext", "닫기");
			
			forward.setRedirect(false);
			forward.setPath("error.do");
		}else{
			HttpSession session=request.getSession();
	        session.setAttribute("memberId", mVO.getMemberId());
	        session.setAttribute("memberName", mVO.getMemberName());
	        forward = new ActionForward();
	        forward.setRedirect(false);
	        forward.setPath("main.do");
	    //로그인 실패    
		}
		return forward;
	}

}
