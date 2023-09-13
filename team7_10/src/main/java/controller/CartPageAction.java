package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDAO;
import model.CartVO;

public class CartPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		CartDAO cDAO = new CartDAO();
		CartVO cVO = new CartVO();
		HttpSession session=request.getSession();
		cVO.setMemberId((String)session.getAttribute("memberId"));
		
		ArrayList<CartVO> cdatas = cDAO.selectAll(cVO);
		
		request.setAttribute("cdatas", cdatas);
		System.out.println("log : CartPageAction : cdatas = "+cdatas);
		forward.setRedirect(false);
		forward.setPath("cart.jsp");
		return forward;
	}

}
