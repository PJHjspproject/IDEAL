package net.question.db;

import java.util.ArrayList;

public interface questionMethod {

	//�ڱⰡ �� ������ ���� �ִ� �޼ҵ�
	public ArrayList<questionDto> AllQuestion(String MemberName);
	
	//1:1���� �� �ۼ��޼ҵ�
	public int InsertQuestion(questionDto qdto);
	
	//1:1���� �� ���� �޼ҵ�
	public void UpdateQuestion(questionDto qdto);
	
	//1:1���� �� ���� �޼ҵ�
	public int DeleteQuestion(int num);
	
	//1:1���� �� �� �ϳ��� �۸� �ҷ����� �޼ҵ�
	public questionDto OneQuestion(int num);
	
	//1:1���� �� �亯 �޾��ִ� �޼ҵ�
	public void UpdateReContent(questionDto qdto);
	
}
