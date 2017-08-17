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
	
	

	
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럥六욜춯節륁삕 嶺뚮∥�뾼占쎄틬�뜝�럥援�
	@Override
	public void InsertInvestment(InvestmentDto imDto) {
	
		
		String sql="";
		
		try {
			
			con=getCon();
			sql = "insert into investment (investRequestNum, memberEmail, investMentName, investMentMoney, program, investDate)"
					+ "values(?,?,?,?,?,now())";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, imDto.getInvestRequestNum());
			pstmt.setString(2, imDto.getMemberEmail());
			pstmt.setString(3,  imDto.getInvestName());
			pstmt.setInt(4,  imDto.getInvestMoney());
			pstmt.setString(5,  imDto.getProgram());
			
			System.out.println("DAO�뿉�꽌 �젙蹂� �꽭�똿 �솗�씤" + imDto.getInvestRequestNum());
			System.out.println("DAO�뿉�꽌 �젙蹂� �꽭�똿 �솗�씤" + imDto.getMemberEmail());
			System.out.println("DAO�뿉�꽌 �젙蹂� �꽭�똿 �솗�씤" + imDto.getInvestName());
			System.out.println("DAO�뿉�꽌 �젙蹂� �꽭�똿 �솗�씤" + imDto.getInvestMoney());
			System.out.println("DAO�뿉�꽌 �젙蹂� �꽭�똿 �솗�씤" + imDto.getProgram());
			
			pstmt.executeUpdate();
			
		} catch (Exception e) {
			System.out.println("InsertInvestment()嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥�뱺�뜝�럡�맋 �뜝�럩沅롳옙紐닷뜝占� �뛾�룇裕뉑틦占�:"+e);
		} finally {
			freeRes(con, pstmt);
		}
	}

	
	
	
			
	
	
	@Override
	public int UpdateInvestment(InvestmentDto idto) {
		//�뜝�럥�빢�뜝�럩�젧 �뜝�럩�꼪�뜝�럩逾� int�솻洹⑥삕�뜝�럥�빢 �뜝�럡�맖�뜝�럥�꽘
		
		//DB�뜝�럥�뿼�뇦猿볦삕 3占쎈／占쎈튂亦낉옙
		
		//�뜝�럥�뼫�뜝�럩�겱 �뜝�럥�빢�뜝�럩�젧�뜝�럥留� �뇦猿됲뜑占쎈턄 �뜝�럩逾졿쾬�꼻�삕 �뜝�럡援��뜝�럡�뀊�뇦猿됲뜑占쎈데嶺뚯쉻�삕? 嶺뚯쉳�뫓筌묒뼂鍮녘눧誘る데嶺뚯쉻�삕? �뜝�럥瑜닷뜝�럥�닡�솻洹ｋ뼬�뵳怨ㅼ삕占쎈쭊�뜝�럥由� SQL占쎈쐩占쎈닑占쎈뉴 占쎈쨨占쎈릮占� �뜝�럩�굚�뜝�럡�뎽
		
		//�뜝�럥�뼫�뜝�럩�겱 �뜝�럥�빢�뜝�럩�젧�뜝�럥留� �뇦猿됲뜑占쎈턄 嶺뚯쉳�뫓筌묒뼂鍮녘눧誘る턄占썩뫅�삕 �뜝�럥�빢�뜝�럩�젧�뜝�럩逾� �뤆�룊�삕�뜝�럥裕잌뜝�럥由��뜝�럥堉꾤춯濡녹삕?
		
		//�뜝�럥�빢�뜝�럩�젧�뜝�럩諭� �뜝�럩留꾢뜝�럥由� SQL占쎈쐩占쎈닑占쎈뉴 �뜝�럩�굚�뜝�럡�뎽
		
		//占쎈쐩占쎈닑占쎈뉴 �뜝�럥堉꾢뜝�럥六ф뤆�룊�삕 int�솻洹⑥삕�뜝�럥�빢�뜝�럥�뱺 �뜝�럥堉뽫뼨�먯삕
		
		//�뜝�럥�뼫�뜝�럩�겱 �뜝�럥�빢�뜝�럩�젧�뜝�럥留됮뇦猿됲뜑占쎈턄 �뜝�럡援��뜝�럡�뀊 �뇦猿됲뜑占쎈턄�뜝�럩逾х춯濡녹삕? �뜝�럥�빢�뜝�럩�젧 占쎄껀占쎈쐝�뜝占� int�솻洹⑥삕�뜝�럥�빢�뜝�럥�뱺 占쎄껀占쎈쐝�뜝占� �뤆�룊�삕 �뜝�럥堉뽩뜝�럥�닡�뜝�럡�맋 占쎈뎨占쎈뿪�돇
		
		//�뜝�럩�겱�뜝�럩�쐸�뜝�럥�돵�뜝�럩�젷 �뜝�럩肉며춯�쉻�삕嶺뚮씭�쐠�땻占썲뜝�럩�뭵.
		return 0;
	}

	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럥六욜춯節륁삕 �뜝�럩寃긷뜝�럩�꽯 �뜝�럩�쓧嶺뚳퐢占썲뜝�룞�삕 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援�
	@Override
	public ArrayList<InvestmentDto> ListInvestment(InvestmentDto idto) {
		//�뜝�럩寃긷뜝�럩�꽯 �뜝�럩�쓧嶺뚳퐢占썲뜝�룞�삕 �뜝�룞�삕�뜝�럩�궋�뜝�럥由��뼨轅명�ｏ옙留꾢뜝�럥由� �뤆�룊�삕�솻洹⑥삕�뼨��梨뤄옙逾� �뛾�룄�ｈ굢�떣泥롳옙鍮섊뙼占� �뜝�룞�삕�뜝�럩�궋.
		ArrayList<InvestmentDto> list = new ArrayList<InvestmentDto>();
		//DB�뜝�럥�뿼�뇦猿볦삕 3占쎈／占쎈튂亦낉옙
		
		//DB�뜝�럥�뿼�뇦猿볦삕
		
		//SQL占쎈쐩占쎈닑占쎈뉴�뜝�럩�굚�뜝�럡�뎽
		
		//SQL占쎈쐩占쎈닑占쎈뉴 �뜝�럥堉꾢뜝�럥六ф뤆�룊�삕 resultset�뜝�럥�뱺 �뜝�럥堉뽫뼨�먯삕
		
		//resultset�뜝�럩逾� �뜝�럡援��뜝�럡�룎�뼨�먯삕 �뜝�럩�쓧濚밸Ŧ�뒩�뜝占� �뛾�룇瑗띰옙沅쀯옙�닱�뜝占�
		
		//arraylist�뜝�럥�뱺 �뤆�룇鍮섊뙼占� �뜝�럥堉듿뜝�럩留꾢슖�댙�삕 �뜝�럥堉뽫뼨轅명�ｏ옙留꾢뜝�럥�돵 dto�뤆�룇鍮섊뙼占� �뜝�럡臾멨뜝�럡�뎽
		
		//dto�뤆�룇鍮섊뙼占� �뜝�럡��占쎄껀�뜝�룞�삕�굢占� rs�뜝�럥�뱺�뜝�럡�맋 �뤆�룄�궖�뚳옙 �뤆�룆占썬굦諭� 占쎈뙀占쎈닑亦끸댙�삕�뜝�룞�삕�땻占� �뜝�룞�삕�뜝�럩�궋
		
		//dto�뤆�룇鍮섊뙼占� �뜝�럩�겱嶺뚳퐢占썲뜝�룞�삕 �뤆�룊�삕�솻洹⑥삕�뼨��梨뤄옙逾� �뛾�룄�ｈ굢�뙋�삕占쎈데 ArrayList�뜝�럥�뱺 �뜝�룞�삕�뜝�럩�궋
		
		//�뜝�럩�겱�뜝�럩�쐸�뜝�럥�돵�뜝�럩�젷 �뜝�럩肉며춯�쉻�삕嶺뚮씭�쐠占쎈뻣占썩뫅�삕
		
		//�뤆�룊�삕�솻洹⑥삕�뼨��梨뤄옙逾� �뛾�룄�ｈ굢�뙋�삕占쎈데 ArrayList�뤆�룇鍮섊뙼占� 占쎈뎨占쎈뿪�돇
		return list;
	}

	//�뜝�럥�뼫�뜝�럩�겱 嶺뚯쉳�뫓筌묒뼇紐닷뜝占� 嶺뚮∥�뾼占쎄틬�뜝�럥援�
	@Override
	public int PrograssRateInvest(InvestmentDto idto, InvestRequestDto irdto) {
		//�뛾�룇瑗뱄옙�꼶�뤆�룊�삕 占쎈뎨占쎈뿪�돇�뜝�럥留� int�솻洹⑥삕�뜝�럥�빢 �뜝�럡�맖�뜝�럥�꽘
		
		//DB�뜝�럥�뿼�뇦猿볦삕 3占쎈／占쎈튂亦낉옙
		
		//DB�뜝�럥�뿼�뇦猿볦삕
		
		//SQL占쎈쐩占쎈닑占쎈뉴 占쎈쨨占쎈릮占� �뜝�럩�굚�뜝�럡�뎽(�윜諛몄굡塋딆닂�삕�뇡�슱�삕占쎈빢 �뜝�럡�뀬�뜝�럩�뮔�뜝�럥由��뜝�럥占쎌룊�삕�뜮�쉻�삕�뜮癒뀁삕占쎈뭵 �뜝�럥占쎈벨�삕占쏙옙)
		
		//SQL占쎈쐩占쎈닑占쎈뉴 占쎈쨨占쎈릮占� �뜝�럥堉꾢뜝�럥六у뜝�럥由��뜝�럥�뿰 �뤆�룊�삕 RS�뜝�럥�뱺 �뜝�럥堉뽫뼨�먯삕
		
		//RS�뜝�럥�닱�뜝�럥�뱺 �뤆�룆占썬굦逾� �뜝�럩肉녑뜝�럥堉꾤춯濡녹삕??
		
		//�윜諛몄굡塋딆닂�삕�뇡�슱�삕占쎈빢 占쎈쉸�맩�뱿�뜝�럥�뱺�뜝�럡�맋 �뤆�룆占썬굦諭� 占쎈뙀占쎈닑亦끸댙�삕�뜝�룞�삕�땻占� 占쎈뎨占쎈뿪�돇�뜝�럥留� �뤆�룆占썬굥�뱺 �뜝�룞�삕�뜝�럩�궋
		
		//RS�뜝�럥�닱�뜝�럥�뱺 �뤆�룆占썬굦逾� �뜝�럥�뵪�뜝�럥堉꾤춯濡녹삕??
		
		//�뜝�럩�겱�뜝�럩�쐸�뜝�럥�돵�뜝�럩�젷
		
		//占쎈뎨占쎈뿪�돇�뤆�룊�삕 占쎈뎨占쎈뿪�돇
		return 0;
	}


	

	
	
}
