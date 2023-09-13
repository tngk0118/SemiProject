package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.WishlistDAO;
import model.WishlistVO;

public class WishlistPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;
		
		WishlistDAO wDAO=new WishlistDAO();
		WishlistVO wVO=new WishlistVO();
		
		HttpSession session = request.getSession();
		wVO.setMemberId((String)session.getAttribute("memberId"));
		
		ArrayList<WishlistVO> wdatas = wDAO.selectAll(wVO);
		
		System.out.println("log : WishlistPageAction : wdatas :"+wdatas);
		
		if(wdatas!=null) {
			forward = new ActionForward();
			request.setAttribute("wdatas", wdatas);
			forward.setRedirect(false);
			forward.setPath("wishList.jsp");
		}
		return forward;
	}
}
