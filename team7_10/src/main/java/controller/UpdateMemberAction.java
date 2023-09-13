package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MemberDAO;
import model.MemberVO;

public class UpdateMemberAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		MemberDAO mDAO = new MemberDAO();
		MemberVO mVO = new MemberVO();
		
		HttpSession session=request.getSession();
		mVO.setMemberId((String)session.getAttribute("memberId"));
		mVO.setSearchCondition("정보변경");
		
		MemberVO mdata = mDAO.selectOne(mVO);
		
		System.out.println("log : MainAction : mdata = "+mdata);
		
		mdata.setMemberName(request.getParameter("memberName"));
		mdata.setPhonenumber(request.getParameter("phoneNumber"));
		String email=null;
		if(request.getParameter("domaintxt")==null) {
			email=request.getParameter("email")+"@"+request.getParameter("domainlist");
		}else {
			email=request.getParameter("email")+"@"+request.getParameter("domaintxt");
		}
		mdata.setEmail(email);
		mdata.setZipcode(request.getParameter("zipcode"));
		mdata.setAddress(request.getParameter("address"));
		mdata.setAddressdetail(request.getParameter("addressdetail"));
		
		String tmpPw = (String)request.getParameter("tmpPw");
		System.out.println("log : UpdateMemberAction : tmpPw = "+tmpPw);
		forward=new ActionForward();
		forward.setRedirect(true);
		if(tmpPw==null || tmpPw.isEmpty() || tmpPw.isBlank() || tmpPw.equals("")) {
			mdata.setTmpPw(mdata.getMemberPw());
			forward.setPath("mypagePage.do");
		}else {
			mdata.setTmpPw(tmpPw);
			forward.setPath("logout.do");
		}
		boolean flag = mDAO.update(mdata);
		if(flag) {
			mdata = mDAO.selectOne(mdata);
			System.out.println("log : UpdateMemberAction : mdata = "+mdata);
		}
		return forward;
	}

}
