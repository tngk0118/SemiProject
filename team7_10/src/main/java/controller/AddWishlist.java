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

@WebServlet("/addWishlist.do")
public class AddWishlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddWishlist() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		WishlistDAO wDAO=new WishlistDAO();
		WishlistVO wVO= new WishlistVO();
		
		HttpSession session = request.getSession();
		wVO.setMemberId((String)session.getAttribute("memberId"));
		// wVO.setMemberId("admin"); // 테스트용
		wVO.setProductNum(Integer.parseInt(request.getParameter("productNum")));
		PrintWriter out=response.getWriter();
		
		if(wDAO.insert(wVO)) {
			System.out.println("위시 추가 성공");
			out.print("insert");
		}else {
			if(wDAO.delete(wVO)) {
				System.out.println("위시 삭제 성공");
				out.print("delete");
			}
		}
	}
}