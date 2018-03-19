package com.cafe24.mysite.action.comment;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
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

public class InsertAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));
		String content = request.getParameter("content");
		
		CommentDao dao = new CommentDao();
		HttpSession session = request.getSession();
		User authUser = (User) session.getAttribute("authUser");
		
		long orderNo = dao.getOrderNo();
		
		Comment comment = new Comment();
		comment.setContent(content);
		comment.setOrderNo(orderNo+1);
		comment.setBoardNo(no);
		comment.setUserNo(authUser.getNo());
		comment.setUserName(authUser.getName());
		
		dao.insert(comment);

		List<Comment> list = new CommentDao().getList(no);
		
		Board board = new BoardDao().get(no);
		System.out.println("(Comment)InsertAction: "+board);
		request.setAttribute("comments", list);
		request.setAttribute("boarddetail", board);
		WebUtil.forward(request, response, "/WEB-INF/views/board/view.jsp");

	}

}
