package com.cafe24.mysite.action.comment;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cafe24.mvc.action.Action;
import com.cafe24.mvc.util.WebUtil;
import com.cafe24.mysite.dao.CommentDao;
import com.cafe24.mysite.vo.Comment;

public class DeleteAction implements Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		long no = Long.parseLong(request.getParameter("no"));
		CommentDao cdao = new CommentDao();
		Comment comment = cdao.get(no);
		cdao.delete(no);
		
		WebUtil.redirect(request, response, "/mysite/board?cmd=view&no="+comment.getBoardNo());
	}

}
