package com.cafe24.mysite.action.guestbook;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.GuestBookDao;
import com.cafe24.mysite.vo.GuestBook;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));
		String password = request.getParameter("password");
		
		GuestBookDao dao = new GuestBookDao();
		GuestBook gb = dao.get(no);
		if(password.equals(gb.getPassword())) {
			dao.delete(no);
		}
		WebUtil.redirect(request, response, "/mysite/gb?cmd=list");
	}

}
