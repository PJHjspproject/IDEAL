package net.InformationUse.db;

import java.util.ArrayList;

public interface InformationMethod {
	
	//�̿�ȳ� ��� �۵��� �ҷ����� �޼ҵ�
	public ArrayList<InformationUseDto> Information();
	
	//���� ������ , ������ �Ű������� �ϳ��� ���� �󼼺����ϴ� �޼ҵ�
	public InformationUseDto getInfo(int InfoNum);

	//�����ڰ� ���� ���ֱ� ���� �޼ҵ�
	public void InsertInfo(InformationUseDto iudto);
	
	//�����ڰ� ���� �����ϱ� ���� �޼ҵ�
	public void UpdateInfo(InformationUseDto iudto);
	
	//�����ڰ� ���� �����ϱ� ���� �޼ҵ�
	public void DeleteInfo(int InfoNum);
	
}
