package com.cafe24.mysite.action.user;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.UserDao;
import com.cafe24.mysite.vo.User;

public class LoginAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String password = request.getParameter("password");
				
		User user = new User(email, password);
		User authUser = new UserDao().get(user);
		
		if(authUser == null) {
			/* ���� ���� */
			
			/* Redirect ���
			 * WebUtil.redirect(request, response, "/mysite/user?cmd=loginform&result=fail");
			 */
			
			/* Forward ���
			 * setAttribute()�� �̸����� ���� �� ����
			 */
			request.setAttribute("email", email);
			request.setAttribute("result", "fail");
			WebUtil.forward(request, response, "/WEB-INF/views/user/loginform.jsp");
			return;
		}
		
		/* ���� ���� */
		HttpSession session = request.getSession(true);
		session.setAttribute("authUser", authUser);
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
