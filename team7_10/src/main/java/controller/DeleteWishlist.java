package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.WishlistDAO;
import model.WishlistVO;

@WebServlet("/deleteWishlist.do")
public class DeleteWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteWishlist() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		WishlistDAO wDAO=new WishlistDAO();
		WishlistVO wVO= new WishlistVO();
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession();
		wVO.setMemberId((String)session.getAttribute("memberId"));
		wVO.setProductNum(Integer.parseInt(request.getParameter("productNum")));
		
		if(wDAO.delete(wVO)) {
			System.out.println("위시리스트 삭제 성공");
			out.print("delete");
		}
	}

}
