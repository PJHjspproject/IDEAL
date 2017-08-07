package net.board.db;

import java.sql.Timestamp;

public class BoardDto {

	private int num;
	private String title;
	private String content;
	private String file;
	private Timestamp date;
	private String nickName;
	
	
	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public Timestamp getDate() {
		return date;
	}

	public void setDate(Timestamp date) {
		this.date = date;
	}



	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}
	
}
