package controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.CartDAO;
import model.CartVO;
import model.PayDAO;
import model.PayDetailDAO;
import model.PayDetailVO;
import model.PayVO;

public class PayAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();

		String orderId = request.getParameter("orderId");
		String paymentKey = request.getParameter("paymentKey");
		String amount = request.getParameter("amount");
		String secretKey = "test_sk_zXLkKEypNArWmo50nX3lmeaxYG5R:";
		
		
		System.out.println("log : PayAction : orderID = "+orderId);
		System.out.println("log : PayAction : paymentKey = "+paymentKey);
		System.out.println("log : PayAction : amount = "+amount);

		java.util.Base64.Encoder encoder = Base64.getEncoder(); 
		byte[] encodedBytes = encoder.encode(secretKey.getBytes("UTF-8"));
		String authorizations = "Basic "+ new String(encodedBytes, 0, encodedBytes.length);

		paymentKey = URLEncoder.encode(paymentKey, StandardCharsets.UTF_8);

		URL url = new URL("https://api.tosspayments.com/v1/payments/confirm");

		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestProperty("Authorization", authorizations);
		connection.setRequestProperty("Content-Type", "application/json");
		connection.setRequestMethod("POST");
		connection.setDoOutput(true);
		JSONObject obj = new JSONObject();
		obj.put("paymentKey", paymentKey);
		obj.put("orderId", orderId);
		obj.put("amount", amount);

		OutputStream outputStream = connection.getOutputStream();
		outputStream.write(obj.toString().getBytes("UTF-8"));

		int code = connection.getResponseCode();
		boolean isSuccess = code == 200 ? true : false;

		InputStream responseStream = isSuccess? connection.getInputStream(): connection.getErrorStream();

		Reader reader = new InputStreamReader(responseStream, StandardCharsets.UTF_8);
		JSONParser parser = new JSONParser();
		String method = null;
		try {
			JSONObject jsonObject = (JSONObject) parser.parse(reader);
			method = (String)jsonObject.get("method");
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		responseStream.close();
		
//		데이터 가공해서
//		pay table insert 
//		
//		selectall
//		
//		setattr
		
		HttpSession session=request.getSession();
		String memberId =(String)session.getAttribute("memberId");
		
		PayDAO pDAO = new PayDAO();
		PayVO pVO = new PayVO();
		CartDAO cDAO = new CartDAO();
		CartVO cVO = new CartVO();
		
		pVO.setPayMethod(method);
		pVO.setMemberId(memberId);
		
		int payNum=0;
		
		if(pDAO.insert(pVO)) {// pay테이블 한개 생성
			pVO.setSearchCondition("first");
			pVO = pDAO.selectOne(pVO);
			System.out.println("log : PayAction : PAY insert pVO = "+pVO);
			payNum = pVO.getPayNum();
			if(pVO!=null) {
				cVO.setMemberId(memberId);
				ArrayList<CartVO> cdatas = cDAO.selectAll(cVO);
				System.out.println("log : PayAction : CART selectAll cdatas = "+cdatas);
				if(cdatas!=null) {
					PayDetailDAO pdDAO = new PayDetailDAO();
					PayDetailVO pdVO = new PayDetailVO();
					pdVO.setPayNum(payNum);
					for (int i=0;i<cdatas.size();i++) {
						pdVO.setProductNum(cdatas.get(i).getProductNum());
						pdVO.setPayCount(cdatas.get(i).getCartCount());
						System.out.println("log : PayAction : PAYDETAIL insert 전 pdVO = "+pdVO);
						pdDAO.insert(pdVO);
						System.out.println("log : PayAction : insert pdVO 성공");
						cVO.setCartNum(cdatas.get(i).getCartNum());
						cVO.setProductNum(cdatas.get(i).getProductNum());
						System.out.println("log : PayAction : CART delete 전 cVO = "+cVO);
						cDAO.delete(cVO);
						System.out.println("log : PayAction : delete cVO 성공");
					}
				}
			}
		}
		PayVO paydata = new PayVO();
		paydata.setMemberId(memberId);
		paydata.setPayNum(payNum);
		paydata.setSearchCondition("payone");
		ArrayList<PayVO> paydatas = pDAO.selectAll(paydata); 
		
		
		System.out.println("log : PayAction : PAY selectAll 후 paydatas = "+paydatas);
		
		request.setAttribute("paydatas", paydatas);
		forward.setRedirect(false);
		forward.setPath("payDone.jsp");
		return forward;
	}

}
