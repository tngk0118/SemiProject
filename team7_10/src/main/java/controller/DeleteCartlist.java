package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDAO;
import model.CartVO;

@WebServlet("/deleteCartlist.do")
public class DeleteCartlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public DeleteCartlist() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CartDAO cDAO=new CartDAO();
		CartVO cVO= new CartVO();
		PrintWriter out=response.getWriter();
		
		HttpSession session = request.getSession();
		cVO.setMemberId((String)session.getAttribute("memberId"));
		//cVO.setMemberId("admin"); // 테스트용
		cVO.setProductNum(Integer.parseInt(request.getParameter("productNum")));
		
		if(cDAO.delete(cVO)) {
			System.out.println("장바구니 삭제 성공");
			out.print("delete");
		}
	}

}
