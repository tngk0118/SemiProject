package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ImagesDAO;
import model.ImagesVO;
import model.ProductDAO;
import model.ProductVO;

public class ShopSinglePageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();
		
		ProductDAO pDAO = new ProductDAO();
		ProductVO pVO = new ProductVO();
		
		pVO.setProductNum(Integer.parseInt(request.getParameter("productNum")));
		
		ProductVO pdata=pDAO.selectOne(pVO);
		
		ProductVO tmppVO=new ProductVO();
		tmppVO.setSearchCondition("상품목록전체출력");
		
		ArrayList<ProductVO> tmppdatas = pDAO.selectAll(tmppVO);
		ArrayList<ProductVO> pdatas = new ArrayList<ProductVO>();
		
		Random rand =new Random();
		int count=4;
		int randproduct[]= new int[count];

		for(int i=0;i<count;i++) {
			randproduct[i] = rand.nextInt(tmppdatas.size())+1;
			for(int j=0;j<i;j++) {
				if(randproduct[i]==randproduct[j]) {
					i--;
				}
			}
		}
		for(int i=0;i<count;i++) {
			pVO=new ProductVO();
			pVO.setProductNum(randproduct[i]);
			System.out.println("log : ShopSinglePageAction : 제품 랜덤 넘버 :"+randproduct[i]);
			pVO=pDAO.selectOne(pVO);
			pdatas.add(pVO);
		}
		System.out.println("log: ShopSinglePageAction : pdatas = "+pdatas);
		
		ImagesDAO iDAO = new ImagesDAO();
		ImagesVO iVO = new ImagesVO();
		
		iVO.setProductNum(pdata.getProductNum());
		
		ArrayList<ImagesVO> idatas = iDAO.selectAll(iVO);
		
		if(pdata!=null && idatas!=null) {
			request.setAttribute("pdata", pdata);
			request.setAttribute("pdatas", pdatas);
			request.setAttribute("idatas", idatas);
			forward.setRedirect(false);
			forward.setPath("shop-single.jsp");
		}

		return forward;
	}

}
