package net.investRequest.db;

import java.util.Date;

public class InvestRequestDto {


	private String memberEmail;
	private int investRequestNum;//투자요청번호
	private String program; //투자프로그램이름
	private String dName; //대표자이름
	private String introduce;//프로그램 간략 소개
	private String category; //카테고리
	private String hashTag;//해쉬태그
	private Date startDay;//모집시작일
	private Date endDay;//모집 종료일
	private Date payDay;//교부 예정일
	private int successMoney;//목표금액
	private int nowMoney; //현재금액
	private String thumbImage; //섬네일 이미지
	private String mainThumb;//메인 fotorama용 이미지
	private String mainImage;//본문내용 이미지
	private String mainText;//본문내용 글
	private String file; //파일업로드
	private int fundSituation;//펀딩진행상황
	private boolean permitChk;//심사허가체크
	private boolean updateChk;//수정허가체크
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
