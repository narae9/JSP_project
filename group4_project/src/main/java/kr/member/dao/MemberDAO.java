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

		System.out.println("ID 중복체크 및 로그인 처리 시작");

		try {
			System.out.println("커넥션 할당부터 시작");
			System.out.println("찾고자 하는 아이디: "+id);
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
				System.out.println("회원 찾기 성공, 데이터 저장~");
				member = new MemberVO();
				member.setMe_key(rs.getInt("me_key"));
				member.setMe_id(rs.getString("me_id"));
				member.setMe_path(String.valueOf(rs.getInt("me_path")));
				member.setMe_passwd(rs.getString("me_passwd"));
				member.setMe_email(rs.getString("me_email"));

				System.out.println("데이터 저장 끝!");
			}

		}catch(Exception e) {
			throw new Exception(e);
		}finally {
			//자원정리
			DBUtil.executeClose(rs,pstmt,conn);		
		}

		return member;
	}
}
