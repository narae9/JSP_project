package kr.board.vo;

import java.sql.Date;

public class BoardReplyVO {
	private int bom_key;
	private String bom_write;
	private String bom_reg_date;
	private String bom_mod_date;
	private int me_key;
	private int bo_key;
	private String me_id;
	
	public int getBom_key() {
		return bom_key;
	}
	public void setBom_key(int bom_key) {
		this.bom_key = bom_key;
	}
	public String getBom_write() {
		return bom_write;
	}
	public void setBom_write(String bom_write) {
		this.bom_write = bom_write;
	}
	public String getBom_reg_date() {
		return bom_reg_date;
	}
	public void setBom_reg_date(String bom_reg_date) {
		this.bom_reg_date = bom_reg_date;
	}
	public String getBom_mod_date() {
		return bom_mod_date;
	}
	public void setBom_mod_date(String bom_mod_date) {
		this.bom_mod_date = bom_mod_date;
	}
	public int getMe_key() {
		return me_key;
	}
	public void setMe_key(int me_key) {
		this.me_key = me_key;
	}
	public int getBo_key() {
		return bo_key;
	}
	public void setBo_key(int bo_key) {
		this.bo_key = bo_key;
	}
	public String getMe_id() {
		return me_id;
	}
	public void setMe_id(String me_id) {
		this.me_id = me_id;
	}
	
}
