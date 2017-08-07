package net.Email.action;
/*
 * ActionForwardŬ������ Action �������̽����� ����� �����ϰ�..
 * ��� ���� ������ �������� ������(�̵�)�Ҷ� ����ϴ� Ŭ����
 * 
 * �ϴ���1. ������ �̵���� ���ΰ� ������ ����
 * 				������ �̵���� ���ΰ� true ->Response.sendRedirect()���
 * 				������ �̵���� false�϶� -> forward()���
 * 
 * �ϴ��� 2. �̵������� ��ΰ� �����Ͽ� ���� ���ִ� ����.
 * 			
 * */
public class ActionForward {
	//������ �̵���� ���� �� ������ ����
	private boolean isRedirect = false;
	//true sendRedirect() -- �ּҰ�� ����
	//false forward() -- ���� ����
	
	//�̵� ������ ��� �ּҰ� ���庯��
	private String path = null;
	
	//������ �̵���� ���ΰ� �����ϴ� �޼ҵ�
	public void setisRedirect(boolean isRedirect){
		this.isRedirect = isRedirect;
	}
	//������ �̵���� ���ΰ� ���� �޼ҵ�
	public boolean getisRedirect(){
		return isRedirect;
	}
	//�̵��� ������ ��� �ּҰ� ������ �޼ҵ�
	public void setPath(String path){
		this.path = path;
	}
	//�̵��� ������ ��� �ּҰ� ���� �޼ҵ�
	public String getPath(){
		return path;
	}

	
	
	
	
	
	
}
