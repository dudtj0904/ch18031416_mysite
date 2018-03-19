package com.cafe24.mysite.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.cafe24.mysite.vo.Board;
import com.cafe24.mysite.vo.GuestBook;

public class BoardDao {
	
	public boolean delete(long no) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "delete from board where no=?";
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
	
	public boolean update(Board vo) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update board set title=?, content=? where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getTitle());
			pstmt.setString(2, vo.getContent());
			pstmt.setLong(3, vo.getNo());
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
		
	}//update(board)
	
	public boolean update(long groupNo, long orderNo) { /* 댓글 그룹번호, 오더번호 수정  */
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update board set order_no=order_no+1 where group_no=? and order_no >= ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, groupNo);
			pstmt.setLong(2, orderNo);
		
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
		
	}//update(groupNo, orderNo)
	
	public boolean update(long no) { /* 조회수 증가 */
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "update board set hit=hit+1 where no=?";
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
		
	}//update(no)
	
	public boolean insert(Board board) {
		boolean result = false;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		try {
			conn = getConnection();
			
			String sql = "insert into board values(null, ?, ?, ?, ?, ?, sysdate(), default, ?, ?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, board.getTitle());
			pstmt.setString(2, board.getContent());
			pstmt.setLong(3, board.getGroupNo());
			pstmt.setLong(4, board.getOrderNo());
			pstmt.setLong(5, board.getDepth());
			pstmt.setLong(6, board.getUserNo());
			pstmt.setString(7, board.getUserName());
		
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
	}//insert()
	
	public long getOrderNo(long groupNo) {
		long orderNo = 0;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = getConnection();
			
			String sql = "select max(order_no) from (select * from board where group_no = ?) tbl";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, groupNo);
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
	
	public long getGroupNo() {
		long groupNo = 0L;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = getConnection();
			
			String sql = "select ifnull(max(group_no), 0) from (select group_no from board group by group_no) tbl";
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				groupNo = rset.getLong(1);
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
		return groupNo;
	}//getGroupNo()
	
	public Board get(long no) {
		Board board = null;
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			conn = getConnection();
			
			String sql = "select title, content, group_no, order_no, depth, user_no from board where no=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setLong(1, no);
			rset = pstmt.executeQuery();
			if(rset.next()) {
				String title = rset.getString(1);
				String content = rset.getString(2);
				long groupNo = rset.getLong(3);
				long orderNo = rset.getLong(4);
				long depth = rset.getLong(5);
				long userNo = rset.getLong(6);
				board = new Board();
				board.setNo(no);
				board.setTitle(title);
				board.setContent(content);
				board.setGroupNo(groupNo);
				board.setOrderNo(orderNo);
				board.setDepth(depth);
				board.setUserNo(userNo);
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
		
		return board;
	}
	
	// paging
	public List<Board> getList(int page, String kwd) {
		List<Board> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			conn = getConnection();
			String sql = "select * from board where title like ? or content like ? order by group_no desc, order_no asc limit ?,10";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+kwd+"%");
			pstmt.setString(2, "%"+kwd+"%");
			
			pstmt.setInt(3, (page-1)*10);
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				long no = rset.getLong(1);
				String title =rset.getString(2);
				long groupNo =rset.getLong(4);
				long orderNo = rset.getLong(5);
				long depth = rset.getLong(6);
				String regDate = rset.getString(7);
				long  hit = rset.getLong(8);
				long userNo = rset.getLong(9);
				String userName = rset.getString(10);
				
				Board board = new Board(no, title, groupNo, orderNo, depth, regDate, hit, userNo, userName);
				list.add(board);
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
	
	public List<Board> getList(String kwd) {
		List<Board> list = new ArrayList<>();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		try {
			conn = getConnection();
			
			String sql = "select * from board where title like ? or content like ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%"+kwd+"%");
			pstmt.setString(2, "%"+kwd+"%");
			
			rset = pstmt.executeQuery();
			while(rset.next()) {
				long no = rset.getLong(1);
				String title =rset.getString(2);
				long groupNo =rset.getLong(4);
				long orderNo = rset.getLong(5);
				long depth = rset.getLong(6);
				String regDate = rset.getString(7);
				long  hit = rset.getLong(8);
				long userNo = rset.getLong(9);
				Board board = new Board(no, title, groupNo, orderNo, depth, regDate, hit, userNo);
				list.add(board);
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
