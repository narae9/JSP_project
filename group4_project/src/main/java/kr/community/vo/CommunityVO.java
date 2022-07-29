package kr.community.vo;

import java.sql.Date;

public class CommunityVO {
	private int co_key;
	private int co_index; //hit >> 조회수?
	private String co_title;
	private String co_write;
	private Date co_reg_date;
	private Date co_mod_date;
	private int me_key;
	private String me_id;
	
	public int getCo_key() {
		return co_key;
	}
	public void setCo_key(int co_key) {
		this.co_key = co_key;
	}
	public int getCo_index() {
		return co_index;
	}
	public void setCo_index(int co_index) {
		this.co_index = co_index;
	}
	public String getCo_title() {
		return co_title;
	}
	public void setCo_title(String co_title) {
		this.co_title = co_title;
	}
	public String getCo_write() {
		return co_write;
	}
	public void setCo_write(String co_write) {
		this.co_write = co_write;
	}
	public Date getCo_reg_date() {
		return co_reg_date;
	}
	public void setCo_reg_date(Date co_reg_date) {
		this.co_reg_date = co_reg_date;
	}
	public Date getCo_mod_date() {
		return co_mod_date;
	}
	public void setCo_mod_date(Date co_mod_date) {
		this.co_mod_date = co_mod_date;
	}
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
}
