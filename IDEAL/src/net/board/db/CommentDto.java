package net.board.db;

import java.sql.Timestamp;

public class CommentDto {
	 private int num;
	 private int cNum;
	 private String content;
	 private String nickName;
	 private Timestamp date;
	 
	 public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}


	public int getcNum() {
		return cNum;
	}
	public void setcNum(int cNum) {
		this.cNum = cNum;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Timestamp getDate() {
		return date;
	}
	public void setDate(Timestamp date) {
		this.date = date;
	}
	
}