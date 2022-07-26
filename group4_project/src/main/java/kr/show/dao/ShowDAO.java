package kr.show.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
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
	public void reserveShow(int me_num, int sh_num){
		
	
		
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
				
				//show.setSre_gpa(rs.getInt("sh_gpa"));

				list.add(show);
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return list;
	}
	
	//공연 상세보기
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
				show.setSh_title(rs.getString("sh_title"));
				show.setSh_img(rs.getString("sh_img"));
				show.setSh_place(rs.getString("sh_place"));
				show.setSh_time(rs.getString("sh_time"));
				show.setSh_date(rs.getString("sh_date"));
				show.setSh_detail(rs.getString("sh_detail"));
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		
		return show;
	}
	//공연 평점
	//공연 예매 취소
	//공연 목록
	//페이지
	
}
