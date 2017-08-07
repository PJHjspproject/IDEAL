package net.Card.db;

import java.sql.*;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class CardDao implements CardMethod{
	
	private Connection getCon() throws Exception{
		Connection con = null;
		
		Context init = new InitialContext();
		
		DataSource ds = (DataSource) init.lookup("java:comp/env/jdbc/jspbeginner");
		
		con = ds.getConnection();
	
		return con;
	}
	
	public void freeRes(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
	}
	public void freeRes(Connection con, PreparedStatement pstmt){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
	}
	
	
	@Override
	public int InsertCard(CardDto cdto) {
		
		return 0;
	}
	



	
	@Override
	public void paymentCard(int CardNum, int price) {
		
		
		
	}



	// 입력한 카드 정보 비교하기
	public int getOneCard(CardDto cardDto) {
		
		
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
			System.out.println("getOneCard메소드 에서 에러 : " + e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		System.out.println(result);
		return result;
	}
	
	//투자 성공 하여 카드 잔액 차감
	public CardDto deductBalance(int nowMoney, String cardNum) {
		
		
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
				System.out.println("deductBalance메소드 에서 에러 " + e);
			}finally {
				freeRes(con, pstmt, rs);
			}
			
			return cardDto;
		
	}

	//결제 하는 카드 잔액 가져오기
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
			System.out.println("getPayCard메소드 에서 에러 : " + e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return cardDto;
		
	}
	
	
}
