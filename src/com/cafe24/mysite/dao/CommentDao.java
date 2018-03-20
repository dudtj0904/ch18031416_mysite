package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.Board;
import com.cafe24.mysite.vo.Comment;

public class CommentDao {
	
	public boolean deleteGroup(long boardNo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from comment where board_no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			int cnt = pstmt.executeUpdate();
			
			result = (cnt==1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}//delete()
	
	public boolean delete(long no) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from comment where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			int cnt = pstmt.executeUpdate();
			
			result = (cnt==1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}//delete()
	
	public long getOrderNo() {
		long orderNo = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = getConnection();
			
			String sql = "select ifnull(max(order_no), 0) from comment";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				orderNo = rset.getLong(1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rset != null) rset.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return orderNo;
	}//getOrderNo()
	
	public boolean insert(Comment comment) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into comment values(null, ?, ?, sysdate(), ?, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, comment.getContent());
			pstmt.setLong(2, comment.getOrderNo());
			pstmt.setLong(3, comment.getBoardNo());
			pstmt.setLong(4, comment.getUserNo());
			pstmt.setString(5, comment.getUserName());
			int cnt = pstmt.executeUpdate();
			
			result = (cnt==1);
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public Comment get(long no) {
		Comment comment = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = getConnection();
			
			String sql = "select board_no from comment where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				long boardNo = rset.getLong(1);
				comment = new Comment();
				comment.setBoardNo(boardNo);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rset != null) rset.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
		return comment;
	}
	
	public List<Comment> getList(long boardNo) {
		List<Comment> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			conn = getConnection();
			
			String sql = "select content, order_no, date_format(reg_date, '%Y-%m-%d'), user_no, user_name, no from comment where board_no=? order by order_no";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, boardNo);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				String content = rset.getString(1);
				long orderNo = rset.getLong(2);
				String regDate = rset.getString(3);
				long userNo = rset.getLong(4);
				String userName = rset.getString(5);
				long no = rset.getLong(6);
				Comment comment = new Comment();
				comment.setContent(content);
				comment.setOrderNo(orderNo);
				comment.setRegDate(regDate);
				comment.setUserNo(userNo);
				comment.setUserName(userName);
				comment.setNo(no);
				list.add(comment);
			}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		} finally {
			try {
				if(conn != null) conn.close();
				if(pstmt != null) pstmt.close();
				if(rset != null) rset.close();
			} catch(SQLException e) {
				e.printStackTrace();
			}
		}
		return list;	
	}
	
	private Connection getConnection() throws SQLException{
		Connection conn = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			
			String url = "jdbc:mysql://localhost/webdb";
			conn = DriverManager.getConnection(url, "webdb", "webdb");
		} catch (ClassNotFoundException e) {
			System.out.println("드라이버 로딩 실패: " + e);
		} 
		return conn;
	}//getConnction()
}
