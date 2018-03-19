package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.GuestBook;

public class GuestBookDao {
	
	public boolean delete(long no) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from guestbook where no=?";
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
	}

	public boolean insert(GuestBook vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into guestbook values(null, ?, ?, ?, sysdate())";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getName());
			pstmt.setString(2, vo.getPassword());
			pstmt.setString(3, vo.getContent());
		
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
	
	public GuestBook get(long num) {
		GuestBook guestbook = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = getConnection();
			
			String sql = "select no, name, password, content, date_format(reg_date, '%Y-%m-%d') from guestbook where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, num);
			rset = pstmt.executeQuery();
			while(rset.next()) {
				long no = rset.getLong(1);
				String name = rset.getString(2);
				String password = rset.getString(3);
				String content = rset.getString(4);
				String regDate = rset.getString(5);
				guestbook = new GuestBook(no, name, password, content, regDate);
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
		
		return guestbook;
	}
	
	public List<GuestBook> getList() {
		List<GuestBook> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = getConnection();
			
			String sql = "select no, name, password, content, date_format(reg_date, '%Y-%m-%d') from guestbook order by no desc";
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				long no = rset.getLong(1);
				String name = rset.getString(2);
				String password = rset.getString(3);
				String content = rset.getString(4);
				String regDate = rset.getString(5);
				GuestBook guestbook = new GuestBook(no, name, password, content, regDate);
				list.add(guestbook);
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
