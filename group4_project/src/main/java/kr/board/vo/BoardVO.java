package kr.board.vo;

import java.sql.Date;

//자바빈(VO)
public class BoardVO {
	private int me_key;
	private int bo_key;
	private int bom_key;
	private String bo_title;
	private String bo_write;
	private String bom_write;
	private Date bo_reg_date;
	private Date bom_reg_date;
	private Date bo_mod_date;
	private Date bom_mod_date;
	
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
	public int getBom_key() {
		return bom_key;
	}
	public void setBom_key(int bom_key) {
		this.bom_key = bom_key;
	}
	public String getBo_title() {
		return bo_title;
	}
	public void setBo_title(String bo_title) {
		this.bo_title = bo_title;
	}
	public String getBo_write() {
		return bo_write;
	}
	public void setBo_write(String bo_write) {
		this.bo_write = bo_write;
	}
	public String getBom_write() {
		return bom_write;
	}
	public void setBom_write(String bom_write) {
		this.bom_write = bom_write;
	}
	public Date getBo_reg_date() {
		return bo_reg_date;
	}
	public void setBo_reg_date(Date bo_reg_date) {
		this.bo_reg_date = bo_reg_date;
	}
	public Date getBom_reg_date() {
		return bom_reg_date;
	}
	public void setBom_reg_date(Date bom_reg_date) {
		this.bom_reg_date = bom_reg_date;
	}
	public Date getBo_mod_date() {
		return bo_mod_date;
	}
	public void setBo_mod_date(Date bo_mod_date) {
		this.bo_mod_date = bo_mod_date;
	}
	public Date getBom_mod_date() {
		return bom_mod_date;
	}
	public void setBom_mod_date(Date bom_mod_date) {
		this.bom_mod_date = bom_mod_date;
	}
}






