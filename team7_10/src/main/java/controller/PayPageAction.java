package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.CartDAO;
import model.CartVO;
import model.MemberDAO;
import model.MemberVO;

public class PayPageAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * pay페이지로 갈때 받아야될 데이터
		 * 
		 * 아이디를 통한 회원 정보
		 * 
		 * 구매하기 눌러서 들어온거면
		 * 상품번호랑 갯수랑 받아서 카트 리스트에 저장
		 * 
		 * 카트를 데이터 전송
		 * 
		 * 장바구니에서 구매 버튼 눌러서 들어온거면
		 * 
		 * 카트 정보 불러와서
		 * 바로 전달
		 * 
		 * 
		 */
		ActionForward forward = new ActionForward();
				
		MemberDAO mDAO = new MemberDAO();
		MemberVO mdata = new MemberVO();
		HttpSession session=request.getSession();
		mdata.setMemberId((String)session.getAttribute("memberId"));
		mdata.setSearchCondition("구매");
		mdata = mDAO.selectOne(mdata);
		
		CartDAO cDAO = new CartDAO();
		CartVO cVO = new CartVO();
		cVO.setMemberId((String)session.getAttribute("memberId"));
		ArrayList<CartVO> cdatas = cDAO.selectAll(cVO);
		
		
		request.setAttribute("cdatas", cdatas);
		request.setAttribute("mdata", mdata);
		System.out.println("log : PayPageAction : cdatas = "+cdatas);
		System.out.println("log : PayPageAction : mdata = "+mdata);
		forward.setRedirect(false);
		forward.setPath("pay.jsp");
		return forward;
	}
}
