package kr.show.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import kr.show.vo.ShowVO;
import kr.util.DBUtil;

public class ShowDAO {
	
	//싱글턴 패턴
	private static ShowDAO instance = new ShowDAO();
	
	public static ShowDAO getInstance(){
		return instance;
	}
	
	ShowDAO(){}

	//------------------------기능구현----------------------------//
	
	//공연등록
	public void showInsert(ShowVO show)throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql=null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql="INSERT INTO show (sh_key,sh_title,sh_date,sh_time,sh_place,sh_detail,sh_img)"
					+ "VALUES(show_seq.nextval,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, show.getSh_title());
			pstmt.setString(2, show.getSh_date()); 
			pstmt.setString(3, show.getSh_time());
			pstmt.setString(4, show.getSh_place());
			pstmt.setString(5, show.getSh_detail());
			pstmt.setString(6, show.getSh_img()); 
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	//공연 예매
	
	//공연 리스트
	public List<ShowVO> getListShow(String keyword) throws Exception{
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShowVO> list = null;
		String sql = null;
		String sub_sql="";
		int cnt =0;
		
		try {
			
			conn = DBUtil.getConnection();
			
			if(keyword!=null&&!"".equals(keyword)) {
				sub_sql = "WHERE s.title LIKE %"+keyword+"%";
				pstmt.setString(++cnt, sub_sql);
			}
			
			sql ="SELECT * FROM show "+sub_sql;
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		
		return list;
	}
	
	//공연 상세보기
	
	//공연 평점
	//공연 예매 취소
	//공연 목록
	//페이지
	
}
