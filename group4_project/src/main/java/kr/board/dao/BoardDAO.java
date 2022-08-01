package kr.board.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardReplyVO;
import kr.board.vo.BoardVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	
	public static BoardDAO getInstance() {
		return instance;
	}
	private BoardDAO() {}
	
	//총 레코드 수(검색 레코드 수)
	public int getBoardCount(String keyfield,String keyword)
			throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;

		try {
			conn = DBUtil.getConnection();

			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE b.bo_title LIKE ?";
			}
			
			sql = "SELECT COUNT(*) FROM board b " + sub_sql;
			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, "%"+keyword+"%");
			}
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
	}
	
	//글목록(검색글 목록)
	public List<BoardVO> getListBoard(int start, int end,
			String keyfield,String keyword)
					throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();

			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE b.bo_title LIKE ?";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM board b " + sub_sql
					+ " ORDER BY b.bo_key DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);

			rs = pstmt.executeQuery();
			
			list = new ArrayList<BoardVO>();
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBo_key(rs.getInt("bo_key"));
				board.setBo_title(StringUtil.useNoHtml(rs.getString("bo_title")));
				board.setBo_reg_date(rs.getDate("bo_reg_date"));
				board.setBo_mod_date(rs.getDate("bo_mod_date"));

				list.add(board);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글상세
	public BoardVO getBoard(int bo_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardVO board = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM board b WHERE b.bo_key=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bo_key);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				board = new BoardVO();
				board.setBo_key(rs.getInt("bo_key"));
				board.setBo_title(rs.getString("bo_title"));
				board.setBo_write(StringUtil.useNoHtml(rs.getString("bo_write")));
				board.setBo_reg_date(rs.getDate("bo_reg_date"));
				board.setBo_mod_date(rs.getDate("bo_mod_date"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return board;
	}
	
	//메인 최신글 목록
	public List<BoardVO> getMainListBoard(int start,int end)
												throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM board b "
					+ "ORDER BY b.bo_key DESC)a) "
					+ "WHERE rnum >= ? AND rnum<=?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, start);
			pstmt.setInt(2, end);

			rs = pstmt.executeQuery();
			list = new ArrayList<BoardVO>();
			
			while(rs.next()) {
				BoardVO board = new BoardVO();
				board.setBo_key(rs.getInt("bo_key"));
				board.setBo_title(StringUtil.useNoHtml(rs.getString("bo_title")));
				board.setBo_reg_date(rs.getDate("bo_reg_date"));

				list.add(board);
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//댓글 등록
	public void insertReplyBoard(BoardReplyVO boardReply)
	                                 throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO board_com (bom_key,"
				+ "bom_write,me_key,bo_key) "
				+ "VALUES (reply_seq.nextval,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, boardReply.getBom_write());
			pstmt.setInt(2, boardReply.getMe_key());
			pstmt.setInt(3, boardReply.getBo_key());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 개수
	public int getReplyBoardCount(int bo_key)
			                            throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT COUNT(*) FROM board_com b "
				+ "JOIN member m ON b.me_key=m.me_key "
				+ "WHERE b.bo_key=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bo_key);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return count;
		
	}
	//댓글 목록
	public List<BoardReplyVO> getListReplyBoard(int start,
			                int end, int bo_key)
	                                    throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<BoardReplyVO> list = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
				+ "FROM (SELECT * FROM board_com b "
				+ "JOIN member m USING (me_key) "
				+ "WHERE b.bo_key=? ORDER BY b.bom_key "
				+ "DESC)a) WHERE rnum >= ? AND rnum <= ?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bo_key);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<BoardReplyVO>();
			while(rs.next()) {
				BoardReplyVO reply = new BoardReplyVO();
				reply.setBom_key(rs.getInt("bom_key"));
				reply.setBom_reg_date(DurationFromNow.getTimeDiffLabel(rs.getString("bom_reg_date")));
				if(rs.getString("bom_mod_date")!=null) {
					reply.setBom_mod_date(DurationFromNow.getTimeDiffLabel(rs.getString("bom_mod_date")));
				}
				reply.setBom_write(StringUtil.useBrNoHtml(rs.getString("bom_write")));
				reply.setBo_key(rs.getInt("bo_key"));
				reply.setMe_key(rs.getInt("me_key"));
				reply.setMe_id(rs.getString("me_id"));
				
				list.add(reply);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//댓글 상세
	public BoardReplyVO getReplyBoard(int bom_key)
	                                   throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		BoardReplyVO reply = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM board_com WHERE bom_key=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bom_key);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply = new BoardReplyVO();
				reply.setBom_key(rs.getInt("bom_key"));
				reply.setMe_key(rs.getInt("me_key"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return reply;
	}
	
	//댓글 수정
	public void updateReplyBoard(BoardReplyVO reply)
			throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE board_com SET bom_write=?,"
					+ "bom_mod_date=SYSDATE "
					+ "WHERE bom_key=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setString(1, reply.getBom_write());
			pstmt.setInt(2, reply.getBom_key());
			//SQL문 실행
			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 삭제
	public void deleteReplyBoard(int bom_key)
			                    throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "DELETE FROM board_com WHERE bom_key=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, bom_key);
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
}





