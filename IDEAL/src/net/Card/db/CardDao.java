package net.Card.db;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class CardDao implements CardMethod{
	//DB���� �޼ҵ� �ۼ����ּ���.
	private Connection getCon() throws Exception{
		Connection con = null;
		
		Context init = new InitialContext();
		
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspbeginner");
		
		con = ds.getConnection();
	
		return con;
	}
	//�ڿ����� �޼ҵ�
	public void freeRes(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
	}
	public void freeRes(Connection con, PreparedStatement pstmt){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
	}//�ڿ����� �޼ҵ� ��
	
	//ī�� ����ϴ� �޼ҵ�
	@Override
	public int InsertCard(CardDto cdto) {
		//����� �ƴ��� �ȵƴ��� Ȯ���ϴ� int���� ����
		
		//db���� 2�ѻ�
		
		//db����
		
		//sql�������� �ۼ�
		
		//sql���� ����
		
		//���ప ������ ��Ƽ� ����
		
		//�ڿ�����
		return 0;
	}
	



	//ī�� �����ϴ� �޼ҵ�
	@Override
	public void paymentCard(int CardNum, int price) {
		//DB���� 2�ѻ�
		
		//DB����
		
		//SQL�������� �ۼ�
		
		
		
	}



	// �Է��� ī�� ���� ���ϱ�
	public int getOneCard(CardDto cardDto) {
		//	DB ����
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		
		
		int result=0;
		
		try {
			con = getCon();
			sql = "select * from card where cardNum=? and cardPass=? and cardExpiry=? and memberBirth=? and cardBank=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, cardDto.getCardNum());
			pstmt.setInt(2, cardDto.getCardPass());
			pstmt.setInt(3, cardDto.getCardExpiry());
			pstmt.setString(4, cardDto.getMemberBirth());
			pstmt.setString(5,  cardDto.getCardBank());
			
			System.out.println(cardDto.getCardNum());
			System.out.println(cardDto.getCardPass());
			System.out.println(cardDto.getCardExpiry());
			System.out.println(cardDto.getMemberBirth());
			System.out.println(cardDto.getCardBank());
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				result = 1;
			}
			
		} catch (Exception e) {
			System.out.println("getOneCard�޼ҵ忡�� ���� : " + e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		System.out.println(result);
		return result;
	}
	
	//���� ���� �Ͽ� ī�� �ܾ� ����
	public CardDto deductBalance(int nowMoney, String cardNum) {
		//	DB ����
		
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "";
			
			CardDto cardDto = new CardDto();
			
			try {
				con = getCon();
				sql = "update card set cardBalance=cardBalance-? where cardNum=?";
				
				pstmt = con.prepareStatement(sql);
				
				
				pstmt.setInt(1, nowMoney);
				pstmt.setString(2, cardNum);
				
				pstmt.executeUpdate();
				
						
			} catch (Exception e) {
				System.out.println("deductBalance�޼ҵ忡�� ���� : " + e);
			}finally {
				freeRes(con, pstmt, rs);
			}
			
			return cardDto;
		
	}

	//���� �ϴ� ī�� �ܾ� ��������
	public CardDto getPayCard(String cardNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		String sql = "";
		
		CardDto cardDto = new CardDto();
		
		try {
			con = getCon();
			sql = "select cardBalance from card where cardNum=? ";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, cardNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				cardDto.setCardBalance(rs.getInt(1));
			}
			
					
		} catch (Exception e) {
			System.out.println("getPayCard�޼ҵ忡�� ���� : " + e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return cardDto;
		
	}
	
	
}
