package com.cafe24.mysite.action.board;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.Board;

public class UpdateAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));
		
		BoardDao dao = new BoardDao();
		Board board = dao.get(no);
		
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		board.setTitle(title);
		board.setContent(content);
		
		dao.update(board);
		
		WebUtil.redirect(request, response, "/mysite/board?cmd=view&no="+no);
		
	}

}
