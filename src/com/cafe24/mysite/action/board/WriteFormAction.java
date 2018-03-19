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

public class WriteFormAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User authUser = (User) session.getAttribute("authUser");
		if(authUser == null) {
			WebUtil.redirect(request, response, "/mysite/board?cmd=list");
			return;
		}
		Board board = new Board();
		BoardDao dao = new BoardDao();
		board.setGroupNo(dao.getGroupNo());
		request.setAttribute("writeboard", board);
		WebUtil.forward(request, response, "/WEB-INF/views/board/write.jsp");
	}

}
