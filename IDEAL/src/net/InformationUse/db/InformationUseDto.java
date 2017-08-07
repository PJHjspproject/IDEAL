package net.InformationUse.db;

//FAQ dto
public class InformationUseDto {
	
	private int infoNum; //이용안내글 고유 번호
	private String infoSubject; //이용안내글 나누는 주제
	private String infoTitle; //이용안내글 제목
	private String infoContent; //이용안내글 내용
	private String infoImage; //이용안내글 첨부 이미지
	
	public int getInfoNum() {
		return infoNum;
	}
	public void setInfoNum(int infoNum) {
		this.infoNum = infoNum;
	}
	public String getInfoSubject() {
		return infoSubject;
	}
	public void setInfoSubject(String infoSubject) {
		this.infoSubject = infoSubject;
	}
	public String getInfoTitle() {
		return infoTitle;
	}
	public void setInfoTitle(String infoTitle) {
		this.infoTitle = infoTitle;
	}
	public String getInfoContent() {
		return infoContent;
	}
	public void setInfoContent(String infoContent) {
		this.infoContent = infoContent;
	}
	public String getInfoImage() {
		return infoImage;
	}
	public void setInfoImage(String infoImage) {
		this.infoImage = infoImage;
	}
	
	
}
