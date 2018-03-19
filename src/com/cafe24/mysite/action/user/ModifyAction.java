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

public class ModifyAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));
		String name = request.getParameter("name");
		String password = request.getParameter("password");
		String passwordCheck = request.getParameter("passwordcheck");
		String gender = request.getParameter("gender");
		
		User user = new User();
		user.setNo(no);
		user.setName(name);
		user.setPassword(password);
		user.setGender(gender);
		if(!password.equals(passwordCheck)) {
			request.setAttribute("result", "fail");
			//WebUtil.redirect(request, response, "/mysite/user?cmd=modifyform");
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			return;
		}
		
		boolean result = new UserDao().update(user);
		if(!result) {
			request.setAttribute("result", "fail");
			//WebUtil.redirect(request, response, "/mysite/user?cmd=modifyform");
			WebUtil.forward(request, response, "/WEB-INF/views/user/modifyform.jsp");
			return;
		}
		HttpSession session = request.getSession();
		User authUser = new UserDao().get(user.getNo());
		session.removeAttribute("authUser");
		session.setAttribute("authUser", authUser);
		
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
