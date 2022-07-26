package kr.community.vo;

import java.sql.Date;

public class CommunityReplyVO {
	private int com_key;
	private String com_write;
	private Date com_date;
	private int co_key;
	private int me_key;
	
	public int getCom_key() {
		return com_key;
	}
	public void setCom_key(int com_key) {
		this.com_key = com_key;
	}
	public String getCom_write() {
		return com_write;
	}
	public void setCom_write(String com_write) {
		this.com_write = com_write;
	}
	public Date getCom_date() {
		return com_date;
	}
	public void setCom_date(Date com_date) {
		this.com_date = com_date;
	}
	public int getCo_key() {
		return co_key;
	}
	public void setCo_key(int co_key) {
		this.co_key = co_key;
	}
	public int getMe_key() {
		return me_key;
	}
	public void setMe_key(int me_key) {
		this.me_key = me_key;
	}
	
	
}
