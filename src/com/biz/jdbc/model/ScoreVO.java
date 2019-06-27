package com.biz.jdbc.model;

public class ScoreVO {

	// sc_seq는 tbl_score의 pkㄹ호 설정되어있고
	private long sc_seq;		//	number
	private String sc_date;	//	varchar2(10 byte)
	private String sc_st_num;	//	char(3 byte)
	private String sc_subject;	//	nvarchar2(50 char)
	private int sc_score;	//	number
	public long getSc_seq() {
		return sc_seq;
	}
	public void setSc_seq(long sc_seq) {
		this.sc_seq = sc_seq;
	}
	public String getSc_date() {
		return sc_date;
	}
	public void setSc_date(String sc_date) {
		this.sc_date = sc_date;
	}
	public String getSc_st_num() {
		return sc_st_num;
	}
	public void setSc_st_num(String sc_st_num) {
		this.sc_st_num = sc_st_num;
	}
	public String getSc_subject() {
		return sc_subject;
	}
	public void setSc_subject(String sc_subject) {
		this.sc_subject = sc_subject;
	}
	public int getSc_score() {
		return sc_score;
	}
	public void setSc_score(int sc_score) {
		this.sc_score = sc_score;
	}
	@Override
	public String toString() {
		return "ScoreVO [sc_seq=" + sc_seq + ", sc_date=" + sc_date + ", sc_st_num=" + sc_st_num + ", sc_subject="
				+ sc_subject + ", sc_score=" + sc_score + "]";
	}
	
	
}
