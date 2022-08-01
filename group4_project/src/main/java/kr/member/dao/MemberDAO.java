package kr.member.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import kr.member.vo.MemberVO;
import kr.util.DBUtil;

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
			pstmt3.setInt(6, member.getMe_zipcode());
			pstmt3.setString(7, member.getMe_add1());
			pstmt3.setString(8, member.getMe_add2());
			pstmt3.setInt(9, num);	// 이부분 잘 모르겠단 말이지,,, 나중에 물어보기
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
			sql = "UPDATE MEMBER_DETAIL SET ME_NAME=?, ME_PHONE=?, ME_EMAIL=?, ME_ZIPCODE=?, ME_ADD1=?, ME_ADD2=? WHERE ME_KEY=?";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, member.getMe_name());
			pstmt.setString(2, member.getMe_phone());
			pstmt.setString(3, member.getMe_email());
			pstmt.setInt(4, member.getMe_zipcode());
			pstmt.setString(5, member.getMe_add1());
			pstmt.setString(6,member.getMe_add2());
			pstmt.setInt(7, member.getMe_key());
			
			pstmt.executeUpdate();
			
			
		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원 정리
			DBUtil.executeClose(null, pstmt, conn);
		}
		
	}
}
