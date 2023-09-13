package controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ErrorAction implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ActionForward forward = new ActionForward();

		String title = (String)request.getAttribute("title");
		String msg = (String)request.getAttribute("msg");
		
		// warning, success, info, error, question
		String icon = (String)request.getAttribute("icon");
		String confirmtext = (String)request.getAttribute("confirmtext");
		String canceltext = (String)request.getAttribute("canceltext");
		String path = (String)request.getAttribute("path");
		
		
		if(title == null || title.isEmpty() || title.isBlank() || title.equals("")) {
			title="안내";
			msg="로그인이 필요합니다.";
			icon="info";
			confirmtext="로그인하기";
			canceltext="닫기";
			path="loginPage.do";
		}
		
		request.setAttribute("title", title);
		request.setAttribute("msg", msg);
		request.setAttribute("icon", icon);
		request.setAttribute("confirmtext", confirmtext);
		request.setAttribute("canceltext", canceltext);
		request.setAttribute("path", path);
		forward.setRedirect(false);
		forward.setPath("sweet.jsp");
		
		return forward;
	}

}
