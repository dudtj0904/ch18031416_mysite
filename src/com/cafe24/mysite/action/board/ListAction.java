package com.cafe24.mysite.action.board;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.BoardDao;
import com.cafe24.mysite.vo.Board;

public class ListAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		BoardDao dao = new BoardDao();
		
		int page = 1;
		String str = request.getParameter("page");
		if(str != null) {
			page = Integer.parseInt(str);
			if(page == 0) page=1;
		}
		
		String kwd = request.getParameter("kwd");
		if(kwd == null) {
			kwd = "";
		}
		// board 데이터베이스의 전체 size
		List<Board> boardList = dao.getList(kwd);
		int boardCount = boardList.size();
		System.out.println("BOARD DATA COUNT: "+boardCount);
		
		if(boardCount != 0) {
			if(boardCount%10 == 0) {
				if(boardCount/10 < page) page--;
			} else {
				if((boardCount/10+1) < page) page--;
			}
		}
		List<Board> list = dao.getList(page, kwd);

		request.setAttribute("kwd", kwd);
		request.setAttribute("page", page);
		request.setAttribute("boardCount", boardCount);
		request.setAttribute("list", list);
		WebUtil.forward(request, response, "/WEB-INF/views/board/list.jsp");

	}

}
