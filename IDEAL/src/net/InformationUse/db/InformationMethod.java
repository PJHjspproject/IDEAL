package net.InformationUse.db;

import java.util.ArrayList;

public interface InformationMethod {
	
	//이용안내 모든 글들을 불러오는 메소드
	public ArrayList<InformationUseDto> Information();
	
	//글의 주제와 , 제목을 매개변수로 하나의 글을 상세보기하는 메소드
	public InformationUseDto getInfo(int InfoNum);

	//관리자가 글을 써주기 위한 메소드
	public void InsertInfo(InformationUseDto iudto);
	
	//관리자가 글을 수정하기 위한 메소드
	public void UpdateInfo(InformationUseDto iudto);
	
	//관리자가 글을 삭제하기 위한 메소드
	public void DeleteInfo(int InfoNum);
	
}
