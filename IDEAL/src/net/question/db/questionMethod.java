package net.question.db;

import java.util.ArrayList;

public interface questionMethod {

	//자기가 쓴 모든글을 볼수 있는 메소드
	public ArrayList<questionDto> AllQuestion(String MemberName);
	
	//1:1문의 글 작성메소드
	public int InsertQuestion(questionDto qdto);
	
	//1:1문의 글 수정 메소드
	public void UpdateQuestion(questionDto qdto);
	
	//1:1문의 글 삭제 메소드
	public int DeleteQuestion(int num);
	
	//1:1문의 글 중 하나의 글만 불러오는 메소드
	public questionDto OneQuestion(int num);
	
	//1:1문의 글 답변 달아주는 메소드
	public void UpdateReContent(questionDto qdto);
	
}
