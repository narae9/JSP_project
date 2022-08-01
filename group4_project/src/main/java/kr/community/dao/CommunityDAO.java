package kr.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardReplyVO;
import kr.community.vo.CommunityFavVO;
import kr.community.vo.CommunityReplyVO;
import kr.community.vo.CommunityVO;
import kr.util.DBUtil;
import kr.util.DurationFromNow;
import kr.util.StringUtil;

public class CommunityDAO {
	//싱글턴 패턴
	private static CommunityDAO instance = new CommunityDAO();
	
	public static CommunityDAO getInstance() {
		return instance;
	}
	private CommunityDAO() {}
	
	//글등록
	public void insertBoard(CommunityVO comm)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();

			sql = "INSERT INTO community (co_key,co_title,"
				+ "co_write,me_key) VALUES ("
				+ "community_seq.nextval,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, comm.getCo_title());
			pstmt.setString(2, comm.getCo_write());
			pstmt.setInt(3, comm.getMe_key());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
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
				if(keyfield.equals("1")) sub_sql = "WHERE c.co_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE m.me_id LIKE ?";
			}

			sql = "SELECT COUNT(*) FROM community c JOIN member m USING(me_key) " + sub_sql;

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
	public List<CommunityVO> getListBoard(int start, int end,
			String keyfield,String keyword)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommunityVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();

			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE c.co_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE m.me_id LIKE ?";
			}

			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM community c JOIN member m "
					+ "USING (me_key) JOIN member_detail d "
					+ "USING (me_key) " + sub_sql
					+ " ORDER BY c.co_key DESC)a) "
					+ "WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);

			rs = pstmt.executeQuery();
			
			list = new ArrayList<CommunityVO>();
			
			while(rs.next()) {
				CommunityVO comm = new CommunityVO();
				comm.setCo_key(rs.getInt("co_key"));
				comm.setCo_title(StringUtil.useNoHtml(rs.getString("co_title")));
				comm.setCo_index(rs.getInt("co_index"));
				comm.setCo_reg_date(rs.getDate("co_reg_date"));
				comm.setCo_mod_date(rs.getDate("co_mod_date"));
				comm.setMe_key(rs.getInt("me_key"));
				comm.setMe_id(rs.getString("me_id"));

				list.add(comm);				
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return list;
	}
	
	//글상세
	public CommunityVO getBoard(int co_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommunityVO comm = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT * FROM community c JOIN member m "
				+ "USING(me_key) JOIN member_detail d "
				+ "USING(me_key) WHERE c.co_key=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, co_key);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				comm = new CommunityVO();
				comm.setCo_key(rs.getInt("co_key"));
				comm.setCo_title(rs.getString("co_title"));
				comm.setCo_write(rs.getString("co_write"));
				comm.setCo_index(rs.getInt("co_index"));
				comm.setCo_reg_date(rs.getDate("co_reg_date"));
				comm.setCo_mod_date(rs.getDate("co_mod_date"));
				comm.setMe_key(rs.getInt("me_key"));
				comm.setMe_id(rs.getString("me_id"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return comm;
		
	}
	
	//조회수 증가(co_index)
	public void updateReadcount(int co_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE community SET co_index=co_index+1 WHERE co_key=?";

			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, co_key);
			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//글수정
	public void updateBoard(CommunityVO comm)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;

		try {
			conn = DBUtil.getConnection();


			sql = "UPDATE community SET co_title=?,co_write=?,"
					+ "co_mod_date=SYSDATE" + sub_sql 
					+ " WHERE co_key=?";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(++cnt, comm.getCo_title());
			pstmt.setString(++cnt, comm.getCo_write());
			
			pstmt.setInt(++cnt, comm.getCo_key());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	//글 삭제
	public void deleteBoard(int co_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);

			sql = "DELETE FROM community_fav WHERE co_key=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, co_key);
			pstmt.executeUpdate();

			sql = "DELETE FROM community_com WHERE co_key=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, co_key);
			pstmt2.executeUpdate();

			sql = "DELETE FROM community WHERE co_key=?";
			pstmt3 = conn.prepareStatement(sql);
			pstmt3.setInt(1, co_key);
			pstmt3.executeUpdate();

			conn.commit();
		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//좋아요 등록
	public void insertFav(int co_key, int me_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO community_fav (fav_key,co_key,"
					+ "me_key) VALUES (communityfav_seq.nextval,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, co_key);
			pstmt.setInt(2, me_key);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//좋아요 개수
	public int selectFavCount(int co_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT COUNT(*) FROM community_fav WHERE co_key=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, co_key);
			
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
	
	//회원번호와 게시물 번호를 이용한 좋아요 정보
	public CommunityFavVO selectFav(int co_key, int me_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommunityFavVO fav = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM community_fav WHERE co_key=? AND me_key=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, co_key);
			pstmt.setInt(2, me_key);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fav = new CommunityFavVO();
				fav.setFav_key(rs.getInt("fav_key"));
				fav.setCo_key(rs.getInt("co_key"));
				fav.setMe_key(rs.getInt("me_key"));
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return fav;
	}
	
	//좋아요 삭제
	public void deleteFav(int fav_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "DELETE FROM community_fav WHERE fav_key=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, fav_key);
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 등록
	public void insertReplyBoard(CommunityReplyVO communityReply)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			
			sql = "INSERT INTO community_com (com_key,"
					+ "com_write,me_key,co_key) "
					+ "VALUES (creply_seq.nextval,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, communityReply.getCom_write());
			pstmt.setInt(2, communityReply.getMe_key());
			pstmt.setInt(3, communityReply.getCo_key());
			pstmt.executeUpdate();
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	//댓글 개수
	public int getReplyBoardCount(int co_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;

		try {
			conn = DBUtil.getConnection();
			
			sql = "SELECT COUNT(*) FROM community_com c "
					+ "JOIN member m ON c.me_key=m.me_key "
					+ "WHERE c.co_key=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, co_key);
			
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
	public List<CommunityReplyVO> getListReplyBoard(int start,
			int end, int co_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<CommunityReplyVO> list = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();

			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM community_com c "
					+ "JOIN member m USING (me_key) "
					+ "WHERE c.co_key=? ORDER BY c.com_key "
					+ "DESC)a) WHERE rnum >= ? AND rnum <= ?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, co_key);
			pstmt.setInt(2, start);
			pstmt.setInt(3, end);

			rs = pstmt.executeQuery();
			list = new ArrayList<CommunityReplyVO>();
			
			while(rs.next()) {
				CommunityReplyVO reply = new CommunityReplyVO();
				reply.setCom_key(rs.getInt("com_key"));
				reply.setCom_date(DurationFromNow.getTimeDiffLabel(rs.getString("com_date")));
				reply.setCom_write(StringUtil.useBrNoHtml(rs.getString("com_write")));
				reply.setCo_key(rs.getInt("co_key"));
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
	public CommunityReplyVO getReplyBoard(int com_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		CommunityReplyVO reply = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM community_com WHERE com_key=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, com_key);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				reply = new CommunityReplyVO();
				reply.setCom_key(rs.getInt("com_key"));
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
	public void updateReplyBoard(CommunityReplyVO reply)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			//커넥션풀로부터 커넥션을 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "UPDATE community_com SET com_write=?,"
					+ "com_mod_date=SYSDATE "
					+ "WHERE com_key=?";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터를 바인딩
			pstmt.setString(1, reply.getCom_write());
			pstmt.setInt(2, reply.getCom_key());
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
	public void deleteReplyBoard(int com_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;

		try {
			conn = DBUtil.getConnection();

			sql = "DELETE FROM community_com WHERE com_key=?";

			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, com_key);

			pstmt.executeUpdate();

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
}





