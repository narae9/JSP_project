package kr.show.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.show.vo.ReserveVO;
import kr.show.vo.ShowReviewVO;
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
			
			sql="INSERT INTO show (sh_key,sh_title,sh_date,sh_time,sh_place,sh_detail,sh_img, me_key)"
					+ "VALUES(show_seq.nextval,?,?,?,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, show.getSh_title());
			pstmt.setString(2, show.getSh_date()); 
			pstmt.setString(3, show.getSh_time());
			pstmt.setString(4, show.getSh_place());
			pstmt.setString(5, show.getSh_detail());
			pstmt.setString(6, show.getSh_img()); 
			pstmt.setInt(7, show.getMe_key());
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	//공연 예매

	public ReserveVO reserveShow(int re_spon, int sh_key, int status, int me_key) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql=null;
		
		try {
			conn = DBUtil.getConnection();

			
			sql="INSERT INTO reserve (re_key,re_date ,re_reserve,re_spon,sh_key,me_key)"
					+ "VALUES(reserve_seq.nextval,sysdate,?,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, status);
			pstmt.setInt(2, re_spon);
			pstmt.setInt(3, sh_key );
			pstmt.setInt(4, me_key);
			
			
			pstmt.executeUpdate();
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		return null;
		
	}
	
	//공연 레코드 수
	public int getShowCount(String keyfield, String keyword)throws Exception{
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql="";
		int count = 0;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE sh_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE sh_place LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE sh_detail LIKE ?";
			}
			
			sql = "SELECT COUNT(*) FROM show " + sub_sql+" ORDER BY sh_key DESC";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(1, "%"+keyword+"%");
			
			}
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return count;
	}
	
	//공연 리스트(검색
	public List<ShowVO> getListShow(String keyfield, String keyword, int start, int end) throws Exception{
		Connection conn =null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShowVO> list = null;
		String sql = null;
		String sub_sql="";
		int cnt=0;

		
		try {
			//1,2단계
			conn = DBUtil.getConnection();
			
			//keyword가 있으면 검색
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("1")) sub_sql = "WHERE sh_title LIKE ?";
				else if(keyfield.equals("2")) sub_sql = "WHERE sh_place LIKE ?";
				else if(keyfield.equals("3")) sub_sql = "WHERE sh_detail LIKE ?";
			}
			//sql
			sql ="SELECT * FROM (SELECT a.*, rownum rnum "
					+ "FROM (SELECT * FROM show "+sub_sql 
					+ " ORDER BY sh_key DESC)a)"
					+ " WHERE rnum>= ? AND rnum <=?";
			//3단계
			pstmt = conn.prepareStatement(sql);
			
			if(keyword!=null && !"".equals(keyword)) {
				pstmt.setString(++cnt, "%"+keyword+"%");
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			list = new ArrayList<ShowVO>();
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ShowVO show = new ShowVO();
				show.setSh_key(rs.getInt("sh_key"));
				show.setSh_title(rs.getString("sh_title"));
				show.setSh_place(rs.getString("sh_place"));
				show.setSh_date(rs.getString("sh_date"));
				show.setSh_time(rs.getString("sh_time"));
				show.setSh_img(rs.getString("sh_img"));
				//show.setSre_gpa(showSumGPA(rs.getInt("sh_key"))/(showAvgGPA(rs.getInt("sh_key"))));

				list.add(show);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//공연 상세보기 / 수정전 데이터 가져오기
	public ShowVO showDetail(int sh_key) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		ShowVO show = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql="SELECT * FROM show WHERE sh_key=?";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, sh_key);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				show = new ShowVO();
				show.setSh_key(rs.getInt("sh_key"));
				show.setSh_title(rs.getString("sh_title"));
				show.setSh_img(rs.getString("sh_img"));
				show.setSh_place(rs.getString("sh_place"));
				show.setSh_time(rs.getString("sh_time"));
				show.setSh_date(rs.getString("sh_date"));
				show.setSh_detail(rs.getString("sh_detail"));
				show.setMe_key(rs.getInt("me_key"));
			}
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		
		return show;
	}
	
	//공연정보 수정
	public void showDetailModify(ShowVO show) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql="UPDATE show SET sh_title=?, sh_place=?, sh_detail=?, sh_img=?, "
					+ "sh_date=?, sh_time=? WHERE sh_key=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, show.getSh_title());
			pstmt.setString(2, show.getSh_place());
			pstmt.setString(3, show.getSh_detail());
			pstmt.setString(4, show.getSh_img());
			pstmt.setString(5, show.getSh_date());
			pstmt.setString(6, show.getSh_time());
			pstmt.setInt(7, show.getSh_key());
			
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	//공연삭제
	public void showDelete(int sh_key) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql="DELETE FROM show WHERE sh_key=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sh_key);
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	
	//공연 평점
	public void showInsertGPA(ShowReviewVO showRe) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			
			sql="INSERT INTO SHOW_REVIEW (sre_key, sre_gpa, sh_key, me_key)"
					+ "VALUES(sre_seq.nextval,?,?,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, showRe.getSre_gpa());
			pstmt.setInt(2, showRe.getSh_key());
			pstmt.setInt(3, showRe.getMe_key());
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
	//공연 평점 평균
	public int showAvgGPA(int sh_key) throws Exception {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		int count = 0;
		
		try {
			conn = DBUtil.getConnection();
			
			sql="SELECT COUNT(*) FROM show_review WHERE sh_key=?";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, sh_key);
			
			rs = pstmt.executeQuery();
			if(rs.next()) {
				count = rs.getInt(1);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(null, pstmt, conn);
		}
		
		return count;
	}
	//공연 평점 합
		public int showSumGPA(int sh_key) throws Exception {
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			String sql = null;
			int sum = 0;
			
			try {
				conn = DBUtil.getConnection();
				
				sql="SELECT SUM(sre_gpa) FROM show_review WHERE sh_key=?";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, sh_key);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {
					sum = rs.getInt(1);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				DBUtil.executeClose(null, pstmt, conn);
			}
			
			return sum;
		}

	
}
