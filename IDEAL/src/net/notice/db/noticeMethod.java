package net.notice.db;

import java.util.ArrayList;

public interface noticeMethod {

	//�������� �� ��ü��� �ҷ����� �޼ҵ�
	public ArrayList<noticeDto> AllNotice();
	
	//�������� �� ��ϸ޼ҵ�
	public void InsertNotice(noticeDto ndto);
	
	//�������� �� �����޼ҵ�
	public void UpdateNotice(noticeDto ndto);
	
	//�������� �� �����޼ҵ�
	public void DeleteNotice(int num);
	
	//�������� �� �� �ϳ��� �� �ҷ����� �޼ҵ�
	public noticeDto OneNotice(int num);
}
