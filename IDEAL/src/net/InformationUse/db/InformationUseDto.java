package net.InformationUse.db;

//FAQ dto
public class InformationUseDto {
	
	private int infoNum; //�̿�ȳ��� ���� ��ȣ
	private String infoSubject; //�̿�ȳ��� ������ ����
	private String infoTitle; //�̿�ȳ��� ����
	private String infoContent; //�̿�ȳ��� ����
	private String infoImage; //�̿�ȳ��� ÷�� �̹���
	
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
