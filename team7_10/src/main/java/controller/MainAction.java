package controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.CategorydetailDAO;
import model.CategorydetailVO;
import model.ProductDAO;
import model.ProductVO;

public class MainAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();

		Random rand =new Random();


		CategorydetailDAO cdDAO=new CategorydetailDAO();
		CategorydetailVO cdVO=new CategorydetailVO();
		/////////
		//		cdVO.setSerchCondition("추천카테고리");
		//		cdVO.setListcnt(3);
		//		
		//		ArrayList<CategorydetailVO> cddatas = cdDAO.selectAll(cdVO);
		/////////

		ArrayList<CategorydetailVO> tmpcddatas = cdDAO.selectAll(cdVO);
		ArrayList<CategorydetailVO> cddatas = new ArrayList<CategorydetailVO>();
		int categorycount = 3;
		int randcategory[]= new int[categorycount];

		for(int i=0;i<categorycount;i++) {
			randcategory[i] = rand.nextInt(tmpcddatas.size())+1;
			for(int j=0;j<i;j++) {
				if(randcategory[i]==randcategory[j]) {
					i--;
				}
			}
		}
		for(int i=0;i<categorycount;i++) {
			cdVO=new CategorydetailVO();
			cdVO.setCategoryDetailNum(randcategory[i]);
			System.out.println("log : MainAction : 세부카테고리 랜덤 넘버 :"+randcategory[i]);
			cdVO=cdDAO.selectOne(cdVO);
			cddatas.add(cdVO);
		}
		System.out.println("log : MainAction : cddatas = "+cddatas);

		
		
		ProductDAO pDAO=new ProductDAO();
		ProductVO pVO=new ProductVO();
		pVO.setSearchCondition("상품목록전체출력");

		ArrayList<ProductVO> tmppdatas = pDAO.selectAll(pVO);
		ArrayList<ProductVO> pdatas = new ArrayList<ProductVO>();
		int productcount = 3;
		int randproduct[]= new int[productcount];

		for(int i=0;i<productcount;i++) {
			randproduct[i] = rand.nextInt(tmppdatas.size())+1;
			for(int j=0;j<i;j++) {
				if(randproduct[i]==randproduct[j]) {
					i--;
				}
			}
		}
		for(int i=0;i<productcount;i++) {
			pVO=new ProductVO();
			pVO.setProductNum(randproduct[i]);
			System.out.println("log : MainAction : 제품 랜덤 넘버 :"+randproduct[i]);
			pVO=pDAO.selectOne(pVO);
			pdatas.add(pVO);
		}
		System.out.println("log: MainAction : pdatas = "+pdatas);




		if(cddatas!=null) {
			request.setAttribute("pdatas", pdatas);
			request.setAttribute("cddatas", cddatas);
			forward.setRedirect(false);
			forward.setPath("main.jsp");
		}
		return forward;
	}

}
