package net.investRequest.db;

import java.util.Date;

public class InvestRequestDto{


	private String memberEmail;
	private int investRequestNum; //占쏙옙占쌘울옙청占쏙옙호
	private String program; //占쏙옙占쏙옙占쏙옙占싸그뤄옙占싱몌옙
	private String dName; //占쏙옙표占쏙옙占싱몌옙
	private String introduce; //占쏙옙占싸그뤄옙 占쏙옙占쏙옙 占쌀곤옙
	private String category; //카占쌓곤옙
	private String hashTag; //占쌔쏙옙占승깍옙
	private Date startDay; //占쏙옙占쏙옙占쏙옙占쏙옙占쏙옙
	private Date endDay; //占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
	private Date payDay; //占쏙옙占쏙옙 占쏙옙占쏙옙占쏙옙
	private int successMoney; //占쏙옙표占쌥억옙
	private int nowMoney; //占쏙옙占쏙옙附占�
	private String thumbImage; //占쏙옙占쏙옙占쏙옙 占싱뱄옙占쏙옙
	private String mainThumb; //占쏙옙占쏙옙 fotorama占쏙옙 占싱뱄옙占쏙옙
	private String mainImage; //占쏙옙占쏙옙占쏙옙占쏙옙 占싱뱄옙占쏙옙
	private String mainText; //占쏙옙占쏙옙占쏙옙占쏙옙 占쏙옙
	private String file; //占쏙옙占싹억옙占싸듸옙
	private int fundSituation;//占쌥듸옙占쏙옙占쏙옙占싫�
	private boolean permitChk; //占심삼옙占썬가체크
	private boolean updateChk; //占쏙옙占쏙옙占썬가체크
	public String getMemberEmail() {
		return memberEmail;
	}
	public void setMemberEmail(String memberEmail) {
		this.memberEmail = memberEmail;
	}
	public int getInvestRequestNum() {
		return investRequestNum;
	}
	public void setInvestRequestNum(int investRequestNum) {
		this.investRequestNum = investRequestNum;
	}
	public String getProgram() {
		return program;
	}
	public void setProgram(String program) {
		this.program = program;
	}
	public String getDName() {
		return dName;
	}
	public void setDName(String dName) {
		this.dName = dName;
	}
	public String getIntroduce() {
		return introduce;
	}
	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getHashTag() {
		return hashTag;
	}
	public void setHashTag(String hashTag) {
		this.hashTag = hashTag;
	}
	public Date getStartDay() {
		return startDay;
	}
	public void setStartDay(Date startDay) {
		this.startDay = startDay;
	}
	public Date getEndDay() {
		return endDay;
	}
	public void setEndDay(Date endDay) {
		this.endDay = endDay;
	}
	public Date getPayDay() {
		return payDay;
	}
	public void setPayDay(Date payDay) {
		this.payDay = payDay;
	}
	public int getSuccessMoney() {
		return successMoney;
	}
	public void setSuccessMoney(int successMoney) {
		this.successMoney = successMoney;
	}
	public int getNowMoney() {
		return nowMoney;
	}
	public void setNowMoney(int nowMoney) {
		this.nowMoney = nowMoney;
	}
	public String getThumbImage() {
		return thumbImage;
	}
	public void setThumbImage(String thumbimage) {
		this.thumbImage = thumbimage;
	}
	public String getMainThumb() {
		return mainThumb;
	}
	public void setMainThumb(String mainThumb) {
		this.mainThumb = mainThumb;
	}
	public String getMainImage() {
		return mainImage;
	}
	public void setMainImage(String mainImage) {
		this.mainImage = mainImage;
	}
	public String getMainText() {
		return mainText;
	}
	public void setMainText(String mainText) {
		this.mainText = mainText;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public int getFundSituation() {
		return fundSituation;
	}
	public void setFundSituation(int fundSituation) {
		this.fundSituation = fundSituation;
	}
	public boolean getPermitChk() {
		return permitChk;
	}
	public void setPermitChk(boolean permitChk) {
		this.permitChk = permitChk;
	}
	public boolean getUpdateChk() {
		return updateChk;
	}
	public void setUpdateChk(boolean updateChk) {
		this.updateChk = updateChk;
	}
		
}
