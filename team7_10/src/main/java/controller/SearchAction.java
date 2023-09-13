package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;
import model.ProductVO;

public class SearchAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward =new ActionForward();

		ProductDAO pDAO = new ProductDAO();
		ProductVO pVO = new ProductVO();
		String keyword = request.getParameter("searchkeyword");
			pVO.setSearchCondition("검색");
			pVO.setSearchKeyword(keyword);
		
		System.out.println("log : SearchAction : pVO.getSK = "+pVO.getSearchKeyword() );
		
		ArrayList<ProductVO> pdatas = pDAO.selectAll(pVO);
		
		
		if(pdatas==null || pdatas.isEmpty()) {
			forward.setRedirect(true);
			forward.setPath("main.do");
		}else {
			request.setAttribute("pdatas", pdatas);
			forward.setRedirect(false);
			forward.setPath("shop.jsp?serachkeyword="+keyword);
		}
		return forward;
	}

}
