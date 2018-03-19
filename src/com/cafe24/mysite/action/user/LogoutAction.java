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

public class LogoutAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 로그아웃은 인증 처리 해야함(인증된 아이디인지, 아닌지)
		HttpSession session = request.getSession();
		User authUser = (User) session.getAttribute("authUser");
		
		if(session != null && authUser != null) {
			// logout 처리
			session.removeAttribute("authUser");
			session.invalidate(); // 현재 session id를 없앰
		}
		
		WebUtil.redirect(request, response, "/mysite/main");
	}

}
