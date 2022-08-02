package kr.member.dao;

import java.io.Console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.show.vo.ShowVO;
import kr.util.DBUtil;
import kr.util.StringUtil;

public class MemberDAO {
	// 싱글턴 패턴
	private static MemberDAO instance = new MemberDAO();

	public static MemberDAO getInstance() {
		return instance;
	}

	private MemberDAO() {}

	//회원가입
	public void insertMember(MemberVO member)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		PreparedStatement pstmt3 = null;
		ResultSet rs = null;
		String sql = null;
		int num = 0;	//시퀀스 번호 저장

		try {
			//커넥션풀에서 커넥션 할당
			conn = DBUtil.getConnection();
			//오토 커밋 해제
			conn.setAutoCommit(false);

			//회원번호(me_key) 생성
			sql = "SELECT MEMBER_SEQ.nextval FROM dual";
			//PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next())
				num=rs.getInt(1);

			//MEMBER 테이블에 데이터 저장
			sql = "INSERT INTO MEMBER (me_key, me_id, me_path) VALUES (?,?,?)";
			//PreparedStatement 객체 생성
			pstmt2 = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt2.setInt(1, num);
			pstmt2.setString(2,member.getMe_id());
			pstmt2.setInt(3, member.getMe_path());
			pstmt2.executeUpdate();

			//MEMBER_DETAIL 테이블에 데이터 저장
			sql = "INSERT INTO MEMBER_DETAIL (me_passwd, me_name, me_agecode, me_email, me_phone, "
					+ "me_zipcode, me_add1, me_add2, me_date, me_key) "
					+ "VALUES (?,?,?,?,?,?,?,?,sysdate,?)";
			pstmt3 = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			pstmt3.setString(1, member.getMe_passwd());
			pstmt3.setString(2, member.getMe_name());
			pstmt3.setString(3, member.getMe_agecode());
			pstmt3.setString(4,member.getMe_email());
			pstmt3.setString(5, member.getMe_phone());
			pstmt3.setString(6, member.getMe_zipcode());
			pstmt3.setString(7, member.getMe_add1());
			pstmt3.setString(8, member.getMe_add2());
			pstmt3.setInt(9, num);
			pstmt3.executeUpdate();

			//SQL 실행 및 성공시 commit
			conn.commit();	

		}catch(Exception e) {
			conn.rollback();
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt3, null);
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(rs, pstmt, conn);
		}

	}

	//ID 중복체크 및 로그인 처리
	public MemberVO checkMember(String id)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;

		try {
			// 커넥션 할당
			conn = DBUtil.getConnection();

			//SQL문 작성
			sql = "SELECT * FROM MEMBER m LEFT OUTER JOIN MEMBER_DETAIL d ON m.ME_KEY=d.ME_KEY WHERE m.me_id=?";
			// 객체 생성
			pstmt = conn.prepareStatement(sql);
			//데이터 바인딩
			pstmt.setString(1, id);			

			rs = pstmt.executeQuery();			

			if(rs.next()) {
				member = new MemberVO();
				member.setMe_key(rs.getInt("me_key"));
				member.setMe_id(rs.getString("me_id"));
				member.setMe_path(String.valueOf(rs.getInt("me_path")));
				member.setMe_passwd(rs.getString("me_passwd"));
				member.setMe_name(rs.getString("me_name"));
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs,pstmt,conn);		
		}

		return member;
	}
	
	//회원 상세정보
	public MemberVO getMember(int me_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberVO member = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "SELECT * FROM MEMBER M JOIN MEMBER_DETAIL D ON M.ME_KEY=D.ME_KEY WHERE M.ME_KEY=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, me_key);
			
			rs=pstmt.executeQuery();
			if(rs.next()) {
				member = new MemberVO();
				member.setMe_key(rs.getInt("me_key"));
				member.setMe_id(rs.getString("me_id"));
				member.setMe_path(String.valueOf(rs.getInt("me_path")));
				member.setMe_passwd(rs.getString("me_passwd"));
				member.setMe_name(rs.getString("me_name"));
				member.setMe_phone(rs.getString("me_phone"));
				member.setMe_email(rs.getString("me_email"));
				member.setMe_zipcode(rs.getString("me_zipcode"));
				member.setMe_add1(rs.getString("me_add1"));
				member.setMe_add2(rs.getString("me_add2"));
				member.setMe_agecode(rs.getString("me_agecode"));
				member.setMe_date(rs.getDate("me_date"));				
			}
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs, pstmt, conn);
		}
		
		return member;
	}
	
	
	// 회원정보 수정
	public void updateMember(MemberVO member)throws Exception{
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			sql = "UPDATE MEMBER_DETAIL SET ME_NAME=?, ME_AGECODE=?, ME_PHONE=?, ME_EMAIL=?, ME_ZIPCODE=?, ME_ADD1=?, ME_ADD2=? WHERE ME_KEY=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMe_name());
			pstmt.setString(2,member.getMe_agecode());
			pstmt.setString(3, member.getMe_phone());
			pstmt.setString(4, member.getMe_email());
			pstmt.setString(5, member.getMe_zipcode());
			pstmt.setString(6, member.getMe_add1());
			pstmt.setString(7,member.getMe_add2());
			pstmt.setInt(8, member.getMe_key());
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}	
	}
	
	//예매내역 검색
	public int getBoardCount(String keyfield,String keyword,int me_key) throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql = null;
		String sub_sql = "";
		int count = 0;
		int cnt = 0;
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("0")) sub_sql = "and r.re_reserve = ?";
				else if(keyfield.equals("1")) sub_sql = "and r.re_reserve = ?";
				else if(keyfield.equals("2")) sub_sql = "";
			}
			
			sql = "SELECT COUNT(*) FROM reserve r JOIN show s on s.sh_key = r.sh_key where r.me_key = ? " + sub_sql;
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(++cnt, me_key);
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("2")) {
					
				}
				else{
					pstmt.setString(++cnt, keyfield);
				}
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
	
	
	//회원탈퇴
	public void deleteMember(int me_key)throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		PreparedStatement pstmt2 = null;
		String sql = null;
		
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			
			// MEMBER me_path 수정
			sql = "UPDATE MEMBER SET me_path=0 WHERE me_key=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, me_key);
			pstmt.executeUpdate();
			
			//MEMBER_DETAIL의 레코드 삭제
			sql = "DELETE FROM MEMBER_DETAIL WHERE me_key=?";
			pstmt2 = conn.prepareStatement(sql);
			pstmt2.setInt(1, me_key);
			pstmt2.executeUpdate();
			
			//모든 SQL문의 실행이 성공하면 커밋
			conn.commit();
		}catch(Exception e) {
			//SQL문이 하나라도 실패하면 롤백
			conn.rollback();
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(null, pstmt2, null);
			DBUtil.executeClose(null, pstmt, conn);
		}
	}
	
	
	
	//글목록(검색글 목록)
	public List<ShowVO> getListBoard(int start, int end,
			          String keyfield,String keyword,int me_key)
	                                   throws Exception{
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<ShowVO> list = null;
		String sql = null;
		String sub_sql = "";
		int cnt = 0;
		
		
		try {
			//JDBC 수행 1,2단계 : 커넥션풀로부터 커넥션 할당
			conn = DBUtil.getConnection();
			
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("0")) sub_sql = "and r.re_reserve = ?";
				else if(keyfield.equals("1")) sub_sql = "and r.re_reserve = ?";
				else if(keyfield.equals("2")) sub_sql = "";
			}
			
			sql = "SELECT * FROM (SELECT a.*, rownum rnum FROM (SELECT * FROM reserve r JOIN show s on s.sh_key = r.sh_key where r.me_key = ? "+sub_sql+ " ORDER BY r.re_key DESC)a) WHERE rnum >= ? AND rnum <= ?";
			
			//JDBC 수행 3단계 : PreparedStatement 객체 생성
			pstmt = conn.prepareStatement(sql);
			//?에 데이터 바인딩
			
			pstmt.setInt(++cnt, me_key);
			if(keyword!=null && !"".equals(keyword)) {
				if(keyfield.equals("2")) {
					
				}
				else{
					pstmt.setString(++cnt, keyfield);
				}
			}
			pstmt.setInt(++cnt, start);
			pstmt.setInt(++cnt, end);
			
			//JDBC 수행 4단계
			rs = pstmt.executeQuery();
			list = new ArrayList<ShowVO>();
			while(rs.next()) {
				ShowVO show = new ShowVO();
				show.setSh_title(rs.getString("sh_title"));
				show.setSh_place(rs.getString("sh_place"));
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
