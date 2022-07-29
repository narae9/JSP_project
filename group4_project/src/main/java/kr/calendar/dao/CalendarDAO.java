package kr.calendar.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.show.vo.ShowVO;
import kr.util.DBUtil;

public class CalendarDAO {
	//싱글턴 패턴
		private static CalendarDAO instance = new CalendarDAO();
		
		public static CalendarDAO getInstance(){
			return instance;
		}
		CalendarDAO(){}
		//------------------------공연 정보 가져오기----------------------------//
		public List<ShowVO> getShow(int y,int m)throws Exception{
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<ShowVO> list = null;
			String sql = null;
			
			try {
				//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
				conn = DBUtil.getConnection();
				//SQL문 작성
				sql = "select * from show where substr(trim(sh_date),0,4) = ? and substr(trim(sh_date),6,2) = ? order by sh_date";
				//JDBC 수행 3단계 : PreparedStatement 객체 생성
				pstmt = conn.prepareStatement(sql);
				//?에 데이터 바인딩
				pstmt.setInt(1, y);
				pstmt.setInt(2, m);
				//JDBC 수행 4단계
				rs = pstmt.executeQuery();
				list = new ArrayList<ShowVO>();
				
				while(rs.next()) {
					ShowVO show = new ShowVO();
					show.setSh_key(rs.getInt("sh_key"));
					show.setSh_title(rs.getString("sh_title"));
					show.setSh_place(rs.getString("sh_place"));
					show.setSh_detail(rs.getString("sh_detail"));
					show.setSh_img(rs.getString("sh_img"));
					show.setMe_key(rs.getInt("me_key"));
					show.setSh_date(rs.getString("sh_date"));
					show.setSh_time(rs.getString("sh_time"));
					
					list.add(show);
				}
				
			}catch(Exception e) {
				throw new Exception(e);
			}finally {
				//자원정리
				DBUtil.executeClose(rs, pstmt, conn);
			}
			return list;
			
		}
}
