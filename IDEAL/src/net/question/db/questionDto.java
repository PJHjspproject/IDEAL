package net.question.db;

import java.sql.Timestamp;

public class questionDto {

	private int num;//�۹�ȣ
	private String title;//������
	private String nickName;//ȸ���̸�
	private Timestamp inputDate;//�����
	private boolean questionStatement;//�亯����
	private String content;//���ǳ���
	private String reContent;//�亯����
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
