package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.PayDAO;
import model.PayVO;

public class MypagePageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		PayDAO pDAO = new PayDAO();
		PayVO pVO = new PayVO();
		
		HttpSession session=request.getSession();
		pVO.setMemberId((String)session.getAttribute("memberId"));
		
		System.out.println("log: MypagePagePageAction : memberId = "+(String)session.getAttribute("memberId"));
		
		ArrayList<PayVO> paydatas = pDAO.selectAll(pVO);
		if(paydatas!=null) {
			request.setAttribute("paydatas", paydatas);
		}
			forward.setRedirect(false);
			forward.setPath("mypage.jsp");
		
		return forward;
	}

}
