package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MemberDAO;
import model.MemberVO;

public class SignupAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward=null;

		MemberDAO mDAO = new MemberDAO();
		MemberVO mVO = new MemberVO();

		String memberId = (String)request.getParameter("memberId");
		String memberPw = (String)request.getParameter("memberPw");
		
		mVO.setMemberId(memberId);
		mVO.setMemberPw(memberPw);
		mVO.setMemberName(request.getParameter("memberName"));
		mVO.setPhonenumber(request.getParameter("phoneNumber"));
		mVO.setZipcode(request.getParameter("zipcode"));
		mVO.setAddress(request.getParameter("address"));
		mVO.setAddressdetail(request.getParameter("addressdetail"));

		String email=null;
		
		if(memberId.contains("G@")) {
			mVO.setEmail(request.getParameter("email"));
			if(mDAO.insert(mVO)){
				System.out.println("구글 회원가입 성공!");
			}
			forward=new ActionForward();
			request.setAttribute("memberId", memberId);
			request.setAttribute("memberPw", memberPw);
			forward.setRedirect(false);
			forward.setPath("login.do");
		}else {
			if(request.getParameter("domaintxt")==null) {
				email=request.getParameter("email")+"@"+request.getParameter("domainlist");
			}else {
				email=request.getParameter("email")+"@"+request.getParameter("domaintxt");
			}
			mVO.setEmail(email);

			boolean flag = mDAO.insert(mVO);
			if(flag) {
				forward=new ActionForward();
				forward.setRedirect(true);
				forward.setPath("loginPage.do");
			}else {
				forward = new ActionForward();
				request.setAttribute("title", "오류");
				request.setAttribute("msg", "회원가입실패 | 중복된아이디입니다.");
				request.setAttribute("icon", "error");
				request.setAttribute("confirmtext", "닫기");
				
				forward.setRedirect(false);
				forward.setPath("error.do");
			}
		}
		return forward;
	}

}
