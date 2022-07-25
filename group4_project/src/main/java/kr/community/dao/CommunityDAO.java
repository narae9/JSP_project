package kr.community.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

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
	//댓글 개수
	//댓글 목록
	//댓글 상세
	//댓글 수정
	//댓글 삭제
	
}





