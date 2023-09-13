package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class SignupPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();

		MemberDAO mDAO = null;
		MemberVO mVO = null;
		
		HttpSession session=request.getSession();
		String memberId=(String)session.getAttribute("memberId");
		if(!(memberId==null || memberId.isEmpty() || memberId.isBlank() || memberId.equals(""))) {
			mDAO = new MemberDAO();
			mVO = new MemberVO();
			mVO.setMemberId(memberId);
			mVO.setSearchCondition("정보변경");
			mVO = mDAO.selectOne(mVO);
			String memberEmail = mVO.getEmail();
			String[] email = memberEmail.split("@");
			System.out.println("이메일 앞 부분 : "+email[0]);
			mVO.setEmail(email[0]);
			String domain = email[1];
			System.out.println("이메일 도메인 : "+email[1]);
			request.setAttribute("mdata", mVO);
			request.setAttribute("domain",domain);
			forward.setRedirect(false);
		}
		else {
		forward.setRedirect(true);
		}
		
		forward.setPath("join.jsp");
		return forward;
	}

}
