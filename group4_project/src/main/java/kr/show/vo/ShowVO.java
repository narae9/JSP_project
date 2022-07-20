package kr.show.vo;

import java.sql.Date;

public class ShowVO {
 private int sh_key;
 private int me_key;
 private String sh_title;
 private String sh_place; //장소
 private String sh_detail; //상세정보
 private Date sh_Date;
 private String sh_img;
 private int sre_gpa; //평점
 private String sre_review; //리뷰
 
public Date getSh_Date() {
	return sh_Date;
}
public void setSh_Date(Date sh_Date) {
	this.sh_Date = sh_Date;
}
public int getSh_key() {
	return sh_key;
}
public void setSh_key(int sh_key) {
	this.sh_key = sh_key;
}
public int getMe_key() {
	return me_key;
}
public void setMe_key(int me_key) {
	this.me_key = me_key;
}
public String getSh_title() {
	return sh_title;
}
public void setSh_title(String sh_title) {
	this.sh_title = sh_title;
}
public String getSh_place() {
	return sh_place;
}
public void setSh_place(String sh_place) {
	this.sh_place = sh_place;
}
public String getSh_detail() {
	return sh_detail;
}
public void setSh_detail(String sh_detail) {
	this.sh_detail = sh_detail;
}
public String getSh_img() {
	return sh_img;
}
public void setSh_img(String sh_img) {
	this.sh_img = sh_img;
}
public int getSre_gpa() {
	return sre_gpa;
}
public void setSre_gpa(int sre_gpa) {
	this.sre_gpa = sre_gpa;
}
public String getSre_review() {
	return sre_review;
}
public void setSre_review(String sre_review) {
	this.sre_review = sre_review;
}

 
 
 
 
 
}
