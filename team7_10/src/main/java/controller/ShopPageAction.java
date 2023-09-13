package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CategoryDAO;
import model.CategoryVO;
import model.CategorydetailDAO;
import model.CategorydetailVO;
import model.ProductDAO;
import model.ProductVO;
import model.WishlistDAO;
import model.WishlistVO;

public class ShopPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = null;
		ProductDAO pDAO = null;
		ProductVO pVO = null;
		ArrayList<ProductVO> pdatas = null;
		ArrayList<WishlistVO> wdatas = null;
		CategorydetailDAO cdDAO = null;
		CategorydetailVO cdVO = null;
		ArrayList<CategorydetailVO> cddatas = null;
		CategoryDAO cDAO = null;
		CategoryVO cVO = null;
		ArrayList<CategoryVO> cdatas = null;
		
		String keyword = request.getParameter("searchkeyword");
		String categorydetailNum = request.getParameter("categorydetailNum");
		
		System.out.println("log : ShopPageAction : pVO.getSK = "+keyword);
		System.out.println("log : ShopPageAction : categorydetailNum = "+categorydetailNum);
		
		//1. 그냥 shop 버튼 눌렀을때 (keyword , cdNum) == null
		if((keyword==null || keyword.isEmpty() || keyword.isBlank() || keyword.equals("")) 
				&& (categorydetailNum==null || categorydetailNum.isEmpty() || categorydetailNum.isBlank() || categorydetailNum.equals(""))) {
			pDAO = new ProductDAO();
			pVO = new ProductVO();
			pVO.setSearchCondition("상품목록전체출력");
			pdatas = pDAO.selectAll(pVO);
			System.out.println("log: ShopPageAction : "+pVO.getSearchCondition()+" | pdatas = "+pdatas);
		}
		//2. 추천 카테고리 버튼 눌러서 들어왔을때 (keyword) == null
		//3. shop에서 상세 카테고리 버튼 눌렀을때 (keyword) == null
		else if(keyword==null || keyword.isEmpty() || keyword.isBlank() || keyword.equals("")) {
			pDAO = new ProductDAO();
			pVO = new ProductVO();
			pVO.setSearchCondition("상품카테고리전체출력");
			pVO.setCategoryDetailNum(Integer.parseInt(categorydetailNum));
			pdatas = pDAO.selectAll(pVO);
			System.out.println("log: ShopPageAction : "+pVO.getSearchCondition()+" | pdatas = "+pdatas);
		}
		//4. 키워드 검색할때, (cdNum) == null
		else if(categorydetailNum==null || categorydetailNum.isEmpty() || categorydetailNum.isBlank() || categorydetailNum.equals("")) {
			pDAO = new ProductDAO();
			pVO = new ProductVO();
			pVO.setSearchCondition("검색");
			pVO.setSearchKeyword(keyword);
			pdatas = pDAO.selectAll(pVO);
			System.out.println("log: ShopPageAction : "+pVO.getSearchCondition()+" | pdatas = "+pdatas);
			
			// 검색목록 없을때
			if(pdatas==null || pdatas.isEmpty()) {
				pVO = new ProductVO();
				pVO.setSearchCondition("상품목록전체출력");
				pdatas = pDAO.selectAll(pVO);
				System.out.println("log: ShopPageAction : "+pVO.getSearchCondition()+" | pdatas = "+pdatas);
			}
		}
		// 카테고리 이름 전부 불러오기
		cDAO = new CategoryDAO();
		cVO = new CategoryVO();
		cdatas = cDAO.selectAll(cVO);
		// 상세 카테고리 이름 전부 불러오기
		cdDAO = new CategorydetailDAO();
		cdVO = new CategorydetailVO();
		cddatas = cdDAO.selectAll(cdVO);
		
		HttpSession session = request.getSession();
		String memberId = (String)session.getAttribute("memberId");
		System.out.println("log: ShopPageAction : memberId = "+memberId);
		
		// 로그인 했으면 위시리스트 체크
		if(!(memberId==null || memberId.isEmpty() || memberId.isBlank() || memberId.equals(""))) {
			WishlistDAO wDAO=new WishlistDAO();
			WishlistVO wVO=new WishlistVO();
			wVO.setMemberId((String)session.getAttribute("memberId"));
			wVO.setSearchCondition("위시리스트체크");
			wdatas = wDAO.selectAll(wVO);
			System.out.println("log: ShopPageAction : wdatas = "+wdatas);
			for (int i = 0; i < pdatas.size(); i++) {
			    pdatas.get(i).setCheck(false); // 모든 pdatas 요소를 false로 초기화
			    for (int j = 0; j < wdatas.size(); j++) {
			        if (pdatas.get(i).getProductNum() == wdatas.get(j).getProductNum()) {
			            pdatas.get(i).setCheck(true); // 일치하는 경우 true로 설정
			            break; // 이미 true로 설정되었으므로 더 이상 비교할 필요 없음
			        }
			    }
			    //System.out.println(pdatas.get(i).getProductNum() + "번 상품 : " + pdatas.get(i).isCheck());
			}
		}
		// 제품이 비어있지 않아야 정상 작동 가능
		
		
		
		if(pdatas != null) {
			if(cdatas == null) {
				cdatas = new ArrayList<CategoryVO>();
			}
			if(cddatas == null) {
				cddatas = new ArrayList<CategorydetailVO>();
			}
			if(wdatas == null) {
				wdatas = new ArrayList<WishlistVO>();
			}
			request.setAttribute("pdatas", pdatas);
			request.setAttribute("cdatas", cdatas);
			request.setAttribute("cddatas", cddatas);
			request.setAttribute("wdatas", wdatas);
			
			System.out.println("log: ShopPageAction : 마지막 pdatas = "+pdatas);
			System.out.println("log: ShopPageAction : 마지막 cddatas = "+cddatas);
			System.out.println("log: ShopPageAction : 마지막 cdatas = "+cdatas);
			System.out.println("log: ShopPageAction : 마지막 wdatas = "+wdatas);
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("shop.jsp");
		}
		return forward;
	}
}
