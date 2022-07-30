package kr.member.vo;

import java.sql.Date;

public class MemberVO {
	private int me_key;			//회원키
	private String me_id;		//아이디
	private int me_path;		//회원구분
	private String me_passwd;	//비밀번호
	private String me_name;		//이름
	private String me_agecode;	//생년월일
	private String me_email;	//이메일
	private String me_phone;	//전화번호
	private int me_zipcode;		//우편번호
	private String me_add1;		//기본주소
	private String me_add2;		//상세주소
	private Date me_date;		//가입일
	
	//비밀번호 일치 여부 체크
	public boolean isCheckedPassword(String userPasswd) {
		//회원등급(me_path): 0.탈퇴 1.일반 2.공연자
		if(me_path > 0 && me_passwd.equals(userPasswd))
			return true;
		return false;
	}


	//private 요소 getter & setter
	public int getMe_key() {
		return me_key;
	}

	public void setMe_key(int me_key) {
		this.me_key = me_key;
	}

	public String getMe_id() {
		return me_id;
	}

	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}

	public int getMe_path() {
		return me_path;
	}

	public void setMe_path(String me_path) {
		this.me_path = Integer.parseInt(me_path);
	}

	public String getMe_passwd() {
		return me_passwd;
	}

	public void setMe_passwd(String me_passwd) {
		this.me_passwd = me_passwd;
	}

	public String getMe_name() {
		return me_name;
	}

	public void setMe_name(String me_name) {
		this.me_name = me_name;
	}

	public String getMe_agecode() {
		return me_agecode;
	}

	public void setMe_agecode(String me_agecode) {
		this.me_agecode = me_agecode;
	}

	public String getMe_email() {
		return me_email;
	}

	public void setMe_email(String me_email) {
		this.me_email = me_email;
	}

	public String getMe_phone() {
		return me_phone;
	}

	public void setMe_phone(String me_phone) {
		this.me_phone = me_phone;
	}

	public int getMe_zipcode() {
		return me_zipcode;
	}

	public void setMe_zipcode(String me_zipcode) {
		this.me_zipcode = Integer.parseInt(me_zipcode);
	}

	public String getMe_add1() {
		return me_add1;
	}

	public void setMe_add1(String me_add1) {
		this.me_add1 = me_add1;
	}

	public String getMe_add2() {
		return me_add2;
	}

	public void setMe_add2(String me_add2) {
		this.me_add2 = me_add2;
	}

	public Date getMe_date() {
		return me_date;
	}

	public void setMe_date(Date me_date) {
		this.me_date = me_date;
	}
	
}
