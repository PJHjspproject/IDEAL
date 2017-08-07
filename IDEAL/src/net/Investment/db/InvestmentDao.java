package net.Investment.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import net.investRequest.db.InvestRequestDto;

public class InvestmentDao implements InvestmentMethod{
	Connection con = null;
	PreparedStatement pstmt = null;
	ResultSet rs = null;
	public Connection getCon() throws Exception{
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
	
	

	
	//투자 신청 메소드
	@Override
	public void InsertInvestment(InvestmentDto imDto) {
	
		
		String sql="";
		
		try {
			
			con=getCon();
			sql = "insert into investment (investRequestNum, memberEmail, investMentName, investMentMoney, program)"
					+ "values(?,?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, imDto.getInvestRequestNum());
			pstmt.setString(2, imDto.getMemberEmail());
			pstmt.setString(3,  imDto.getInvestName());
			pstmt.setInt(4,  imDto.getInvestMoney());
			pstmt.setString(5,  imDto.getProgram());
			
			System.out.println("DAO에서 정보 세팅 확인" + imDto.getInvestRequestNum());
			System.out.println("DAO에서 정보 세팅 확인" + imDto.getMemberEmail());
			System.out.println("DAO에서 정보 세팅 확인" + imDto.getInvestName());
			System.out.println("DAO에서 정보 세팅 확인" + imDto.getInvestMoney());
			System.out.println("DAO에서 정보 세팅 확인" + imDto.getProgram());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("InsertInvestment()筌롫뗄�꺖占쎈굡占쎈퓠占쎄퐣 占쎌궎�몴占� 獄쏆뮇源�:"+e);
		} finally {
			freeRes(con, pstmt);
		}
	}

	@Override
	public int UpdateInvestment(InvestmentDto idto) {
		//占쎈땾占쎌젟 占쎌넇占쎌뵥 int癰귨옙占쎈땾 占쎄퐨占쎈섧
		
		//DB연결 3총사
		
		//DB연결
				
		//SQL쿼리작성
				
		//SQL쿼리 실행값 resultset에 담기
				
		//resultset이 끝나기 전까지 반복문
				
		//arraylist에 객체 단위로 담기위해 dto객체 생성
				
		//dto객체 내부에 rs에서 각각 값을 꺼내와서 저장
				
		//dto객체 자체를 가변길이 배열인 ArrayList에 저장
				
		//자원해제 잊지마시고
				
		//리턴
		return 0;
	}

	//투자 진행률 메소드
	@Override
	public ArrayList<InvestmentDto> ListInvestment(InvestmentDto idto) {
		
		ArrayList<InvestmentDto> list = new ArrayList<InvestmentDto>();
		
		return list;
	}

	//투자 진행률 메소드
	@Override
	public int PrograssRateInvest(InvestmentDto idto, InvestRequestDto irdto) {
		//반환값 리턴할 int변수 선언
		
				//DB연결 3총사
				
				//DB연결
				
				//SQL쿼리 구문 작성(그룹함수 사용하셔야해요 ㅎㅎ)
				
				//SQL쿼리 구문 실행하여 값 RS에 담기
				
				//RS안에 값이 있다면??
				
				//그룹함수 컬럼에서 값을 꺼내와서 리턴할 값에 저장
				
				//RS안에 값이 없다면??
				
				//자원해제
				
				//리턴값 리턴
				return 0;
		
	}
}
