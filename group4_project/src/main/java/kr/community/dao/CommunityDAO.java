package kr.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.board.vo.BoardVO;
import kr.community.vo.CommunityVO;
import kr.util.DBUtil;
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

			sql = "INSERT INTO community (co_key,co_index,co_title,"
				+ "co_write,me_key) VALUES ("
				+ "community_seq.nextval,?,?,?,?)";

			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, comm.getCo_index());
			pstmt.setString(2, comm.getCo_title());
			pstmt.setString(3, comm.getCo_write());
			pstmt.setInt(4, comm.getMe_key());
			
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
				else if(keyfield.equals("3")) sub_sql = "WHERE c.co_write LIKE ?";
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
	
	//글목록
	public List<CommunityVO> getListBoard(int start, int end,
			String keyfield,String keyword)
					throws Exception{
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
				else if(keyfield.equals("3")) sub_sql = "WHERE c.co_write LIKE ?";
			}

			sql = "SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM community c JOIN member m "
					+ "USING (me_key) JOIN member_detail d "
					+ "USING (me_key) "+ sub_sql
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
				comm.setCo_index(rs.getInt("co_index"));
				comm.setCo_title(StringUtil.useNoHtml(rs.getString("co_title")));
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
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			//SQL문 작성
			sql = "SELECT * FROM community c JOIN member m "
				+ "USING(me_key) JOIN member_detail d "
				+ "USING(me_key) WHERE c.co_key=?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt.setInt(1, co_key);
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				comm = new CommunityVO();
				comm.setCo_key(rs.getInt("co_key"));
				comm.setCo_index(rs.getInt("co_index"));
				comm.setCo_title(rs.getString("co_title"));
				comm.setCo_write(rs.getString("co_write"));
				comm.setCo_reg_date(rs.getDate("co_reg_date"));
				comm.setCo_mod_date(rs.getDate("co_mod_date"));
				comm.setMe_key(rs.getInt("me_key"));
				comm.setMe_id(rs.getString("me_id"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		return comm;
		
	}
	//조회수 증가(co_index)
	//글 수정
	//글 삭제
	
	//좋아요 등록
	//좋아요 개수
	//회원번호와 게시물 번호를 이용한 좋아요 정보
	//좋아요 삭제
	
	//댓글 등록
	//댓글 개수
	//댓글 목록
	//댓글 상세
	//댓글 수정
	//댓글 삭제
	
}





