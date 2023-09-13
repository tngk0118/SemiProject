package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class FrontController
 */
@WebServlet("*.do")
public class FrontController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public FrontController() {
		super();
	}

	private void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 1. url을 분석해야함.
		String uri = request.getRequestURI();
		System.out.println("uri : "+ uri);
		String cp=request.getContextPath();
		System.out.println("cp : "+ cp);
		String command = uri.substring(cp.length());
		System.out.println("command : "+ command);

		// 2. 요청에 맞는 액션클래스의 execute() 메서드 호출
		ActionForward forward = null;
		if(command.equals("/main.do")) {
			forward = new MainAction().execute(request, response);
		}
		else if (command.equals("/loginPage.do")) {
			forward = new LoginPageAction().execute(request, response);
		}
		else if (command.equals("/aboutPage.do")) {
			forward = new AboutPageAction().execute(request, response);
		}
		else if (command.equals("/signupPage.do")) {
			forward = new SignupPageAction().execute(request, response);
		}
		else if (command.equals("/mypagePage.do")) {
			forward = new MypagePageAction().execute(request, response);
		}
		else if (command.equals("/shopPage.do")) {
			forward = new ShopPageAction().execute(request, response);
		}
		else if (command.equals("/shopSinglePage.do")) {
			forward = new ShopSinglePageAction().execute(request, response);
		}
		else if (command.equals("/cartPage.do")) {
			forward = new CartPageAction().execute(request, response);
		}
		else if (command.equals("/wishlistPage.do")) {
			forward = new WishlistPageAction().execute(request, response);
		}
		else if (command.equals("/payPage.do")) {
			forward = new PayPageAction().execute(request, response);
		}
		else if (command.equals("/login.do")) {
			forward = new LoginAction().execute(request, response);
		}
		else if (command.equals("/logout.do")) {
			forward = new LogoutAction().execute(request, response);
		}
		else if (command.equals("/signup.do")) {
			forward = new SignupAction().execute(request, response);
		}
//		else if (command.equals("/addWishlist.do")) {
//			forward = new AddWishlistAction().execute(request, response);
//		}
//		else if (command.equals("/deleteWishlist.do")) {
//			forward = new DeleteWishlistAction().execute(request, response);
//		}
//		else if (command.equals("/addCart.do")) {
//			forward = new AddCartAction().execute(request, response);
//		}
//		else if (command.equals("/deleteCart.do")) {
//			forward = new DeleteCartAction().execute(request, response);
//		}
		else if (command.equals("/pay.do")) {
			forward = new PayAction().execute(request, response);
		}
		else if (command.equals("/updateMember.do")) {
			forward = new UpdateMemberAction().execute(request, response);
		}
		else if (command.equals("/search.do")) {
			forward = new SearchAction().execute(request, response);
		}
		else if (command.equals("/error.do")) {
			forward = new ErrorAction().execute(request, response);
		}
		
		if(forward != null) {
			if(forward.isRedirect()) {
				response.sendRedirect(forward.getPath());
			}else {
				request.getRequestDispatcher(forward.getPath()).forward(request, response);
			}
		}else {
			// 없는 요청
			response.sendRedirect("goback.jsp"); //로 이동하게끔
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doAction(request, response);
	}

}
