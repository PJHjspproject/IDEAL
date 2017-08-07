package net.question.db;

import java.sql.Timestamp;

public class questionDto {

	private int num;//글번호
	private String title;//글제목
	private String nickName;//회원이름
	private Timestamp inputDate;//등록일
	private boolean questionStatement;//답변상태
	private String content;//문의내용
	private String reContent;//답변내용
	private String image;
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public Timestamp getInputDate() {
		return inputDate;
	}
	public void setInputDate(Timestamp inputDate) {
		this.inputDate = inputDate;
	}
	public boolean getQuestionStatement() {
		return questionStatement;
	}
	public void setQuestionStatement(boolean questionStatement) {
		this.questionStatement = questionStatement;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getReContent() {
		return reContent;
	}
	public void setReContent(String reContent) {
		this.reContent = reContent;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	
	
	
	
	
	
}
