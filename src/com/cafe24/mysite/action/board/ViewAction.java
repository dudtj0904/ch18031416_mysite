package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.dao.CommentDao;
import com.cafe24.mysite.vo.Board;
import com.cafe24.mysite.vo.Comment;
import com.cafe24.mysite.vo.User;

public class ViewAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		Board board = dao.get(no);
		System.out.println(board);
		
		HttpSession session = request.getSession();
		User user = (User) session.getAttribute("authUser");
		long userNo = 0;
		if(user != null) {
			userNo = user.getNo();
		}
		
		if(board.getUserNo()!=userNo) {
			/* cookie -> hit */
			Cookie[] cookies = request.getCookies();
			Cookie viewCookie = null;
			
			if(cookies != null && cookies.length > 0) {
				for(int i=0; i<cookies.length; i++) {
					if(cookies[i].getName().equals("ALREADYVIEW")) {
						viewCookie = cookies[i];
					}
				}
			}
			if(viewCookie == null) {
				System.out.println("NOT ALREADY VIEW");
				Cookie newCookie = new Cookie("ALREADYVIEW", no+"|");
				response.addCookie(newCookie);
				dao.update(no); // hit증가
			} else {
				System.out.println("ALREADY VIEW");
				String  value = viewCookie.getValue();
				System.out.println("cookie: "+value);
				if(value.indexOf(no+"|") < 0) {
					value = value+""+no+"|";
					viewCookie.setValue(value);
					response.addCookie(viewCookie);
					dao.update(no); // hit증가
				}
			}			
		}//
		
		List<Comment> list = new CommentDao().getList(no);
		
		request.setAttribute("comments", list);
		request.setAttribute("boarddetail", board);
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");

	}

}
