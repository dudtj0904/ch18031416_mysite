package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.Board;
import com.cafe24.mysite.vo.User;

public class WriteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		String str1 = request.getParameter("groupNo");
		String str2 = request.getParameter("orderNo");
		String str3 = request.getParameter("depth");
		
		long groupNo = 0;
		long orderNo = 0;
		long depth = 0;
		
		if(str1 != null && str2 != null && str2 != null) {
			groupNo = Long.parseLong(str1);
			orderNo = Long.parseLong(str2);
			depth = Long.parseLong(str3);
		}
		
		HttpSession session = request.getSession();
		User authUser = (User) session.getAttribute("authUser");
		
		BoardDao dao = new BoardDao();
		Board board = new Board(title, content, groupNo, orderNo, depth, authUser.getNo(), authUser.getName());
		
		if(depth > 1) {	// 댓글인 경우 orderNo를 1씩 증가,,
			dao.update(groupNo, orderNo);
		}
		
		dao.insert(board);
		
		WebUtil.redirect(request, response, "/mysite/board?cmd=list");
		
		
		
	}

}
