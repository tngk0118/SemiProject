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

@WebServlet("/addCartlist.do")
public class AddCartlist extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AddCartlist() {
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
		
		CartVO cdata = new CartVO();
		cdata = cDAO.selectOne(cVO);
		
		if(cdata == null) {
			cVO.setCartCount(Integer.parseInt(request.getParameter("cartcount")));
			if(cDAO.insert(cVO)) {
				System.out.println("새로운 상품 장바구니 추가 성공");
				out.print("insert");
			}
		}else {
			cdata.setTmpcnt(Integer.parseInt(request.getParameter("cartcount")));
			if(cDAO.update(cdata)) {
				System.out.println("기존 상품 장바구니 변경 성공");
				out.print("update");
			}
		}
		
		
		/*
		 * 장바구니에 같은 상품이 없는데, 상품 상세페이지에서 개수 정해서 오면 -> 정해진 개수를 토대로 생성 하고,
		 * 장바구니에 같은 상품이 있으면, 상품 상세페이지에서 개수 정해서 오면 -> 정해진 개수를 토대로 개수를 업데이트 해주고,
		 * 
		 * 상품 리스트에서 버튼 누르면, 장바구니에 같은 상품이 없으면-> 1개 추가
		 * 상품 리스트에서 버튼 누르면, 장바구니에 같은 상품이 있으면-> 기존거에서 1개 추가
		 * 
		 */
		
	}

}
