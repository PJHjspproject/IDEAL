package net.investRequest.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

public class InvestRequestDao implements InvestRequestMethod{
	
	//Connection 媛앹껜 �뼸�뼱�삤�뒗 硫붿냼�뱶瑜� �옉�꽦�빐二쇱꽭�슂!
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
	
	//투자상품등록 예정인 리스트
	public ArrayList<InvestRequestDto> expectedIr(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InvestRequestDto irdto = null;
		ArrayList<InvestRequestDto> eirlist = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM investRequest WHERE permitChk = 1 AND startDay>now() order by startDay asc limit 4";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			eirlist = new ArrayList<InvestRequestDto>();
			while(rs.next()){
				irdto = new InvestRequestDto();
				String memberEmail = rs.getString(1);
				int investRequestNum = rs.getInt(2);
				String program  = rs.getString(3);
				String dName = rs.getString(4);
				String introduce = rs.getString(5);
				String DBcategory = rs.getString(6);
				String hashtag = rs.getString(7);
				java.sql.Date startDay = rs.getDate(8);
				java.sql.Date endDay = rs.getDate(9);
				java.sql.Date payDay = rs.getDate(10);
				int successMoney = rs.getInt(11);
				int nowMoney = rs.getInt(12);
				String thumbImage = rs.getString(13);
				String mainThumb = rs.getString(14);
				String mainImage = rs.getString(15);
				String mainText = rs.getString(16);
				String file = rs.getString(17);
				int fundSituation = rs.getInt(18);
				boolean permitChk = rs.getBoolean(19);
				boolean updateChk = rs.getBoolean(20);
				
				irdto.setMemberEmail(memberEmail);
				irdto.setInvestRequestNum(investRequestNum);
				irdto.setProgram(program);
				irdto.setDName(dName);
				irdto.setIntroduce(introduce);
				irdto.setCategory(DBcategory);
				irdto.setHashTag(hashtag);
				irdto.setStartDay(startDay);
				irdto.setEndDay(endDay);
				irdto.setPayDay(payDay);
				irdto.setSuccessMoney(successMoney);
				irdto.setNowMoney(nowMoney);
				irdto.setThumbImage(thumbImage);
				irdto.setMainThumb(mainThumb);
				irdto.setMainImage(mainImage);
				irdto.setMainText(mainText);
				irdto.setFile(file);
				irdto.setFundSituation(fundSituation);
				irdto.setPermitChk(permitChk);
				irdto.setUpdateChk(updateChk);
				
				eirlist.add(irdto);
			}
		}catch(Exception e){
			System.out.println("expectedIr메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return eirlist;
	}
	
	
	//종료되지 않았고, 심사통과 하였으며, 가장 투자금이 많은 캠페인 가져오기
	public InvestRequestDto getHighestNowMoneyChampain(){
		InvestRequestDto irdto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from investRequest where permitchk = 1 and fundSituation = 1 and date(startday) <= date(now()) order by nowMoney desc limit 1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				irdto = new InvestRequestDto();
				String memberEmail = rs.getString(1);
				int investRequestNum = rs.getInt(2);
				String program  = rs.getString(3);
				String dName = rs.getString(4);
				String introduce = rs.getString(5);
				String DBcategory = rs.getString(6);
				String hashtag = rs.getString(7);
				java.sql.Date startDay = rs.getDate(8);
				java.sql.Date endDay = rs.getDate(9);
				java.sql.Date payDay = rs.getDate(10);
				int successMoney = rs.getInt(11);
				int nowMoney = rs.getInt(12);
				String thumbImage = rs.getString(13);
				String mainThumb = rs.getString(14);
				String mainImage = rs.getString(15);
				String mainText = rs.getString(16);
				String file = rs.getString(17);
				int fundSituation = rs.getInt(18);
				boolean permitChk = rs.getBoolean(19);
				boolean updateChk = rs.getBoolean(20);
				
//				System.out.println("getHighestNowMoneyChampain()메소드에서 확인");
//				System.out.println(memberEmail);
//				System.out.println(investRequestNum);
//				System.out.println(program);
//				System.out.println(dName);
//				System.out.println(introduce);
//				System.out.println(DBcategory);
//				System.out.println(hashtag);
//				System.out.println(startDay);
//				System.out.println(endDay);
//				System.out.println(payDay);
//				System.out.println(successMoney);
//				System.out.println(nowMoney);
//				System.out.println(thumbImage);
//				System.out.println(mainThumb);
//				System.out.println(mainImage);
//				System.out.println(mainText);
//				System.out.println(file);
//				System.out.println(fundSituation);
//				System.out.println(permitChk);
//				System.out.println(updateChk);
				
				irdto.setMemberEmail(memberEmail);
				irdto.setInvestRequestNum(investRequestNum);
				irdto.setProgram(program);
				irdto.setDName(dName);
				irdto.setIntroduce(introduce);
				irdto.setCategory(DBcategory);
				irdto.setHashTag(hashtag);
				irdto.setStartDay(startDay);
				irdto.setEndDay(endDay);
				irdto.setPayDay(payDay);
				irdto.setSuccessMoney(successMoney);
				irdto.setNowMoney(nowMoney);
				irdto.setThumbImage(thumbImage);
				irdto.setMainThumb(mainThumb);
				irdto.setMainImage(mainImage);
				irdto.setMainText(mainText);
				irdto.setFile(file);
				irdto.setFundSituation(fundSituation);
				irdto.setPermitChk(permitChk);
				irdto.setUpdateChk(updateChk);
			}
		} catch (Exception e) {
			System.out.println("getHighestNowMoneyChampain()메소드에서 오류:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		
		return irdto;
	}
	
	//종료되지 않았고, 심사통과 하였으며, 가장 종료날짜가 임박한 캠페인 가져오기
	public InvestRequestDto getImminentChampain(){
		InvestRequestDto irdto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from investRequest where permitchk = 1 and fundSituation = 1 and date(startday) <= date(now()) order by endDay desc limit 1";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				irdto = new InvestRequestDto();
				String memberEmail = rs.getString(1);
				int investRequestNum = rs.getInt(2);
				String program  = rs.getString(3);
				String dName = rs.getString(4);
				String introduce = rs.getString(5);
				String DBcategory = rs.getString(6);
				String hashtag = rs.getString(7);
				java.sql.Date startDay = rs.getDate(8);
				java.sql.Date endDay = rs.getDate(9);
				java.sql.Date payDay = rs.getDate(10);
				int successMoney = rs.getInt(11);
				int nowMoney = rs.getInt(12);
				String thumbImage = rs.getString(13);
				String mainThumb = rs.getString(14);
				String mainImage = rs.getString(15);
				String mainText = rs.getString(16);
				String file = rs.getString(17);
				int fundSituation = rs.getInt(18);
				boolean permitChk = rs.getBoolean(19);
				boolean updateChk = rs.getBoolean(20);
				
//				System.out.println("getImminentChampain()메소드에서 확인");
//				System.out.println(memberEmail);
//				System.out.println(investRequestNum);
//				System.out.println(program);
//				System.out.println(dName);
//				System.out.println(introduce);
//				System.out.println(DBcategory);
//				System.out.println(hashtag);
//				System.out.println(startDay);
//				System.out.println(endDay);
//				System.out.println(payDay);
//				System.out.println(successMoney);
//				System.out.println(nowMoney);
//				System.out.println(thumbImage);
//				System.out.println(mainThumb);
//				System.out.println(mainImage);
//				System.out.println(mainText);
//				System.out.println(file);
//				System.out.println(fundSituation);
//				System.out.println(permitChk);
//				System.out.println(updateChk);
				
				irdto.setMemberEmail(memberEmail);
				irdto.setInvestRequestNum(investRequestNum);
				irdto.setProgram(program);
				irdto.setDName(dName);
				irdto.setIntroduce(introduce);
				irdto.setCategory(DBcategory);
				irdto.setHashTag(hashtag);
				irdto.setStartDay(startDay);
				irdto.setEndDay(endDay);
				irdto.setPayDay(payDay);
				irdto.setSuccessMoney(successMoney);
				irdto.setNowMoney(nowMoney);
				irdto.setThumbImage(thumbImage);
				irdto.setMainThumb(mainThumb);
				irdto.setMainImage(mainImage);
				irdto.setMainText(mainText);
				irdto.setFile(file);
				irdto.setFundSituation(fundSituation);
				irdto.setPermitChk(permitChk);
				irdto.setUpdateChk(updateChk);
			}
		} catch (Exception e) {
			System.out.println("getImminentChampain()메소드에서 오류:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		
		return irdto;
	}
	
	
	//product.jsp�럹�씠吏��뿉�꽌 �꽭濡� �럹�씠吏뺤쓣 �븯湲곗쐞�빐�꽌 寃��깋議곌굔(category, search)�뿉 �뵲瑜� 媛곴린 �떎瑜� count瑜� 泥섎━�빐�빞 �븳�떎.
	//媛�濡쒕줈 理쒕� 4媛쒖쓽 �옄猷뚮�� 異쒕젰�븯寃� �릺�뼱 �엳�쑝誘�濡� 4媛쒕떒�쐞濡� �넂�씠瑜� 蹂�寃쏀븯硫� �맆 寃� 媛숇떎. 
	public int getCountInvestRequestList(String category, String search){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getCon();
			String sql = "";
			//�닾�옄�븯湲� 踰꾪듉�쓣 �닃�읉嫄곕굹, category媛� All�씠怨� 寃��깋�뼱瑜� �엯�젰�븯吏� �븡�븯�쓣 寃쎌슦 紐⑤뱺 permitChk=ture(1)�씤 紐⑸줉�쓣 遺덈윭�삩�떎(fundsituation媛믪뿉 臾닿��븯�떎)
			if(category.equals("All") && (search == null || search.equals(""))){
//				System.out.println("ALL:寃��깋�뼱�뾾�쓬");
				sql = "select count(*) "
					+ "from investRequest "
					+ "where permitChk = true and date(startday) <= date(now())"
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			//category媛� All�씠怨� 寃��깋�뼱瑜� �엯�젰�븳 寃쎌슦
			}else if(category.equals("All") && !(search ==null || search.equals(""))){
//				System.out.println("ALL:寃��깋�뼱 �엳�쓬");
				sql = "select count(*) "
					+ "from investRequest "
					+ "where permitChk = true and date(startday) <= date(now())"
					+ "and (program like ? or introduce like ?) "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
				rs = pstmt.executeQuery();
			//category媛� All�씠 �븘�땲怨� 寃��깋�뼱瑜� �엯�젰�븯吏� �븡�븯�쓣 寃쎌슦 
			}else if(!category.equals("All")  && (search ==null || search.equals(""))){
//				System.out.println("ALL�븘�떂:寃��깋�뼱�뾾�쓬");
				sql = "select count(*) "
					+ "from investRequest "
					+ "where permitChk = true and date(startday) <= date(now())"
					+ "and category = ? "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category);
				rs = pstmt.executeQuery();
			//category媛� All�씠 �븘�땲怨� 寃��깋�뼱瑜� �엯�젰�븳 寃쎌슦
			}else if(!category.equals("All")){
//				System.out.println("ALL�븘�떂:寃��깋�뼱 �엳�쓬");
				sql = "select count(*) "
					+ "from investRequest "
					+ "where permitChk = true and date(startday) <= date(now())"
					+ "and category = ? "
					+ "and (program like ? or introduce like ?) "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category);
				pstmt.setString(2, "%"+search+"%");
				pstmt.setString(3, "%"+search+"%");
				rs = pstmt.executeQuery();
			}
			
			while(rs.next()){			
				count = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getCountInvestRequestList()硫붿냼�뱶�뿉�꽌 �삤瑜� 諛쒖깮:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
//		System.out.println("getCountInvestRequestList()硫붿냼�뱶 異쒕젰�솗�씤 count:"+count);
		return count;
	}
	
	
	//硫붿씤�뿉�꽌 �닾�옄�븯湲� 踰꾪듉�쓣 �겢由��븯�뿬�꽌 罹좏럹�씤 紐⑸줉�쓣 蹂� �븣 �궗�슜. 泥섏쓬�뿉 �닾�옄�븯湲� 踰꾪듉 �겢由��떆�뿉�뒗 All議곌굔�쑝濡� 寃��깋�븯怨�
	//product.jsp�럹�씠吏� �궡�쓽 �긽�떒�뿉 Search履쎌뿉 select(category)媛믪쓣 �꽕�젙�븯怨� 寃��깋�븷 寃쎌슦�뿉�뒗 �빐�떦 議곌굔�뿉 留욊쾶 寃��깋�븳�떎.
	public ArrayList<InvestRequestDto> ListInvestRequest(String category, String search) {
		ArrayList<InvestRequestDto> irlist = new ArrayList<InvestRequestDto>();
		InvestRequestDto irdto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		System.out.println("ListInvestRequest()硫붿냼�뱶 泥댄겕");
//		System.out.println("category:"+category+": search:"+search);
		
		try {
			con = getCon();
			String sql = "";
			//�닾�옄�븯湲� 踰꾪듉�쓣 �닃�읉嫄곕굹, category媛� All�씠怨� 寃��깋�뼱瑜� �엯�젰�븯吏� �븡�븯�쓣 寃쎌슦 紐⑤뱺 permitChk=ture(1)�씤 紐⑸줉�쓣 遺덈윭�삩�떎(fundsituation媛믪뿉 臾닿��븯�떎)
			if(category.equals("All") && (search == null || search.equals(""))){
//				System.out.println("ALL:寃��깋�뼱�뾾�쓬");
				sql = "select * "
					+ "from investRequest "
					+ "where permitChk = true and date(startday) <= date(now())"
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			//category媛� All�씠怨� 寃��깋�뼱瑜� �엯�젰�븳 寃쎌슦
			}else if(category.equals("All") && !(search ==null || search.equals(""))){
//				System.out.println("ALL:寃��깋�뼱 �엳�쓬");
				sql = "select * "
					+ "from investRequest "
					+ "where permitChk = true and date(startday) <= date(now())"
					+ "and (program like ? or introduce like ?) "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
				rs = pstmt.executeQuery();
			//category媛� All�씠 �븘�땲怨� 寃��깋�뼱瑜� �엯�젰�븯吏� �븡�븯�쓣 寃쎌슦 
			}else if(!category.equals("All")  && (search ==null || search.equals(""))){
//				System.out.println("ALL�븘�떂:寃��깋�뼱�뾾�쓬");
				sql = "select * "
					+ "from investRequest "
					+ "where permitChk = true and date(startday) <= date(now())"
					+ "and category = ? "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category);
				rs = pstmt.executeQuery();
			//category媛� All�씠 �븘�땲怨� 寃��깋�뼱瑜� �엯�젰�븳 寃쎌슦
			}else if(!category.equals("All")){
//				System.out.println("ALL�븘�떂:寃��깋�뼱 �엳�쓬");
				sql = "select * "
					+ "from investRequest "
					+ "where permitChk = true and date(startday) <= date(now())"
					+ "and category = ? "
					+ "and (program like ? or introduce like ?) "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category);
				pstmt.setString(2, "%"+search+"%");
				pstmt.setString(3, "%"+search+"%");
				rs = pstmt.executeQuery();
			}
			
			while(rs.next()){
//				System.out.println("ListInvestRequest() 罹좏럹�씤 紐⑸줉 �엳�쓬");
				irdto = new InvestRequestDto();
				String memberEmail = rs.getString(1);
				int investRequestNum = rs.getInt(2);
				String program  = rs.getString(3);
				String dName = rs.getString(4);
				String introduce = rs.getString(5);
				String DBcategory = rs.getString(6);
				String hashtag = rs.getString(7);
				java.sql.Date startDay = rs.getDate(8);
				java.sql.Date endDay = rs.getDate(9);
				java.sql.Date payDay = rs.getDate(10);
				int successMoney = rs.getInt(11);
				int nowMoney = rs.getInt(12);
				String thumbImage = rs.getString(13);
				String mainThumb = rs.getString(14);
				String mainImage = rs.getString(15);
				String mainText = rs.getString(16);
				String file = rs.getString(17);
				int fundSituation = rs.getInt(18);
				boolean permitChk = rs.getBoolean(19);
				boolean updateChk = rs.getBoolean(20);
				
//				System.out.println("ListInvestRequest(String category, String search)硫붿냼�뱶�뿉�꽌 異쒕젰�솗�씤");
//				System.out.println(memberEmail);
//				System.out.println(investRequestNum);
//				System.out.println(program);
//				System.out.println(dName);
//				System.out.println(introduce);
//				System.out.println(DBcategory);
//				System.out.println(hashtag);
//				System.out.println(startDay);
//				System.out.println(endDay);
//				System.out.println(payDay);
//				System.out.println(successMoney);
//				System.out.println(nowMoney);
//				System.out.println(thumbImage);
//				System.out.println(mainThumb);
//				System.out.println(mainImage);
//				System.out.println(mainText);
//				System.out.println(file);
//				System.out.println(fundSituation);
//				System.out.println(permitChk);
//				System.out.println(updateChk);
				
				irdto.setMemberEmail(memberEmail);
				irdto.setInvestRequestNum(investRequestNum);
				irdto.setProgram(program);
				irdto.setDName(dName);
				irdto.setIntroduce(introduce);
				irdto.setCategory(DBcategory);
				irdto.setHashTag(hashtag);
				irdto.setStartDay(startDay);
				irdto.setEndDay(endDay);
				irdto.setPayDay(payDay);
				irdto.setSuccessMoney(successMoney);
				irdto.setNowMoney(nowMoney);
				irdto.setThumbImage(thumbImage);
				irdto.setMainThumb(mainThumb);
				irdto.setMainImage(mainImage);
				irdto.setMainText(mainText);
				irdto.setFile(file);
				irdto.setFundSituation(fundSituation);
				irdto.setPermitChk(permitChk);
				irdto.setUpdateChk(updateChk);
				
				irlist.add(irdto);
			}
		} catch (Exception e) {
			System.out.println("ListInvestRequest()硫붿냼�뱶�뿉�꽌 �삤瑜� 諛쒖깮:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		return irlist;
	}
	
	//�닾�옄�슂泥��븳 紐⑤뱺�궗�엺�쓣 蹂댁뿬二쇰뒗 硫붿냼�뱶
	@Override
	public ArrayList<InvestRequestDto> ListInvestRequest(String category, String search, int pageSize) {
		//�닾�옄�슂泥��븳 紐⑤뱺�궗�엺�쓣 �떞�쓣 媛�蹂�湲몄씠 諛곗뿴 �깮�꽦
		
		//DB�뿰寃� 3珥앹궗 ���옣
		
		try{
			//DB�뿰寃�
			
			//SQL荑쇰━臾� �옉�꽦(紐⑤뱺嫄� select�븯�릺, category�� search媛믪쓣 湲곗��쑝濡�)
			
			//preparestatement媛앹껜�뿉 �뿰寃곌컼泥� 諛� 荑쇰━臾� ���옣
			
			//preparestatement媛앹껜�뿉 荑쇰━臾몄뿉 �엳�뒗 ?媛� �꽕�젙
			
			//preparestatement媛앹껜濡� 荑쇰━臾� �떎�뻾媛� resultset�뿉 �떞湲�
			
			//resultset�씠 �걹�굹湲� �쟾源뚯�..
			
			//InvestRequestDto媛앹껜 �깮�꽦�븯�뿬
			
			//resultset�뿉�꽌 媛곴컖 爰쇰궡�뼱 �떞�븘�궡湲�
			
			//媛�蹂�湲몄씠 諛곗뿴�뿉 InvestRequestDto媛앹껜異붽��븯湲�
		}catch(Exception e){
			System.out.println("ListInvestRequest硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : "+e);
		}finally{
			
		}
		
		
		//媛�蹂�湲몄씠 諛곗뿴 由ы꽩
		return null;
	}
	
	public ArrayList<InvestRequestDto> ListInvestRequest(){
		ArrayList<InvestRequestDto> irlist = new ArrayList<InvestRequestDto>();
		InvestRequestDto irdto = null;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select * from investRequest where permitChk = 1 and date(startday) <= date(now()) order by startDay desc limit 0, 8";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				irdto = new InvestRequestDto();
				String memberEmail = rs.getString(1);
				int investRequestNum = rs.getInt(2);
				String program  = rs.getString(3);
				String dName = rs.getString(4);
				String introduce = rs.getString(5);
				String category = rs.getString(6);
				String hashtag = rs.getString(7);
				java.sql.Date startDay = rs.getDate(8);
				java.sql.Date endDay = rs.getDate(9);
				java.sql.Date payDay = rs.getDate(10);
				int successMoney = rs.getInt(11);
				int nowMoney = rs.getInt(12);
				String thumbImage = rs.getString(13);
				String mainThumb = rs.getString(14);
				String mainImage = rs.getString(15);
				String mainText = rs.getString(16);
				String file = rs.getString(17);
				int fundSituation = rs.getInt(18);
				boolean permitChk = rs.getBoolean(19);
				boolean updateChk = rs.getBoolean(20);
				
//				System.out.println("ListInvestRequest()硫붿냼�뱶�뿉�꽌 異쒕젰�솗�씤");
//				System.out.println("memberEmail:"+memberEmail);
//				System.out.println("investRequest:"+investRequestNum);
//				System.out.println("program:"+program);
//				System.out.println("dName:"+dName);
//				System.out.println("introduce:"+introduce);
//				System.out.println("category:"+category);
//				System.out.println("hashtag:"+hashtag);
//				System.out.println("startDay:"+startDay);
//				System.out.println("endDay:"+endDay);
//				System.out.println("payDay:"+payDay);
//				System.out.println("successMoney:"+successMoney);
//				System.out.println("nowMoney:"+nowMoney);
//				System.out.println("thumbImage:"+thumbImage);
//				System.out.println("mainThumb:"+mainThumb);
//				System.out.println("mainImage:"+mainImage);
//				System.out.println("mainText:"+mainText);
//				System.out.println("file:"+file);
//				System.out.println("fundSituation:"+fundSituation);
//				System.out.println("permitChk:"+permitChk);
//				System.out.println("updateChk:"+updateChk);
				
				irdto.setMemberEmail(memberEmail);
				irdto.setInvestRequestNum(investRequestNum);
				irdto.setProgram(program);
				irdto.setDName(dName);
				irdto.setIntroduce(introduce);
				irdto.setCategory(category);
				irdto.setHashTag(hashtag);
				irdto.setStartDay(startDay);
				irdto.setEndDay(endDay);
				irdto.setPayDay(payDay);
				irdto.setSuccessMoney(successMoney);
				irdto.setNowMoney(nowMoney);
				irdto.setThumbImage(thumbImage);
				irdto.setMainThumb(mainThumb);
				irdto.setMainImage(mainImage);
				irdto.setMainText(mainText);
				irdto.setFile(file);
				irdto.setFundSituation(fundSituation);
				irdto.setPermitChk(permitChk);
				irdto.setUpdateChk(updateChk);
				
				irlist.add(irdto);
			}
		} catch (Exception e) {
			System.out.println("ListInvestRequest()硫붿냼�뱶�뿉�꽌 �삤瑜� 諛쒖깮:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		
		return irlist;
	}

	//�닾�옄�슂泥� 硫붿냼�뱶
	@Override
	public int InsertInvestRequst(InvestRequestDto irdto) {
		//DB�뿰寃� 2珥앹궗
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//�꽦怨듭뿬遺����옣�븷 int���엯�쓽 蹂��닔�깮�꽦
		int check = 0;
		try{
			//DB�뿰寃�
			con = getCon();
			//SQL援щЦ �옉�꽦
			String sql = "insert into investRequest (memberEmail,"
													+ "program,"
													+ " dName,"
													+ " introduce,"
													+ " category,"
													+ " hashTag,"
													+ " startDay,"
													+ " endDay,"
													+ " payDay,"
													+ " successMoney,"
													+ " nowMoney,"
													+ " thumbImage,"
													+ " mainThumb,"
													+ " mainImage,"
													+ " mainText,"
													+ " file,"
													+ " fundSituation,"
													+ " permitChk,"
													+ " updateChk) "
					+ "values(?,?,?,?,?,?,?,?,?,?,0,?,?,?,?,?,1,false,false)";
			//preparestatement媛앹껜�뿉 荑쇰━臾� 諛� �뿰寃곌컼泥� ���옣
			pstmt = con.prepareStatement(sql);
			//preparestatement媛앹껜�뿉 荑쇰━臾� ?媛� �꽕�젙
			pstmt.setString(1, irdto.getMemberEmail());
			pstmt.setString(2, irdto.getProgram());
			pstmt.setString(3, irdto.getDName());
			pstmt.setString(4, irdto.getIntroduce());
			pstmt.setString(5, irdto.getCategory());
			pstmt.setString(6, irdto.getHashTag());
			pstmt.setDate(7, new java.sql.Date(irdto.getStartDay().getTime()));
			pstmt.setDate(8, new java.sql.Date(irdto.getEndDay().getTime()));
			pstmt.setDate(9, new java.sql.Date(irdto.getPayDay().getTime()));
			pstmt.setInt(10, irdto.getSuccessMoney());
			//nowMoney�뒗 0�쑝濡� 湲곕낯媛� �꽕�젙
			pstmt.setString(11, irdto.getThumbImage());
			pstmt.setString(12, irdto.getMainThumb());
			pstmt.setString(13, irdto.getMainImage());
			pstmt.setString(14, irdto.getMainText());
			pstmt.setString(15, irdto.getFile());
			//�굹癒몄� 3媛쒖쓽 媛� funcSituation, permitShk, updateChk媛믩뱾�� sql援щЦ�뿉 湲곕낯媛� �꽕�젙
			
			//preparestatement媛앹껜 �떎�뻾�븯�뿬 �꽕怨듭뿬遺� ���옣
			check = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("InsertInvestRequest硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		//int���엯�쓽 蹂��닔 由ы꽩
		return check;
	}
	
	/*max num 援ы븯�뒗 硫붿냼�뱶*/ //AUTO_INCREMENT占썬렅占� �븣臾몄뿉 蹂대쪟
	public int getMaxInvestRequestNum(){
		int max = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			con = getCon();
			String sql = "select max(investRequestNum) from investRequest";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()){
				max = rs.getInt(1);
			}
		} catch (Exception e) {
			System.out.println("getMaxInvestRequestNum()硫붿냼�뱶�뿉�꽌 �삤瑜� 諛쒖깮:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		if(max==0) max = 1;
		
		return max;
	}
	
	
	//num媛믪쓣 湲곗��쑝濡� �븯�굹�쓽 �닾�옄�슂泥� �럹�씠吏�瑜� 媛��졇�삤�뒗 硫붿냼�뱶
	@Override
	public InvestRequestDto getInvestRequest(int investRequestNum) {
		//InvestRequstDto���엯�쓽 蹂��닔 �꽑�뼵
		InvestRequestDto irdto = new InvestRequestDto();
		//DB3珥앹궗
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//DB�뿰寃�
			con = getCon();
			//SQL援щЦ �옉�꽦
			String sql = "select * from investRequest where investRequestNum = ?";
			//preparestatement媛앹껜�뿉 荑쇰━臾� 諛� �뿰寃곌컼泥� ���옣
			pstmt = con.prepareStatement(sql);
			//preparestatement媛앹껜�궡遺��뿉 ?媛� �뀑�똿
			pstmt.setInt(1, investRequestNum);
			//resultset�뿉 荑쇰━ �떎�뻾媛� ���옣
			rs = pstmt.executeQuery();
			//resultset�궡遺��뿉 ���옣�맂媛믪씠 �엳�쓣 寃쎌슦..
			if(rs.next()){
				//InvestRequstDto�뿉 resultset媛믪쓣 媛곴컖 爰쇰궡�� ���옣
				irdto.setMemberEmail(rs.getString(1));
				irdto.setInvestRequestNum(rs.getInt(2));
				irdto.setProgram(rs.getString(3));
				irdto.setDName(rs.getString(4));
				irdto.setIntroduce(rs.getString(5));
				irdto.setCategory(rs.getString(6));
				irdto.setHashTag(rs.getString(7));
				irdto.setStartDay(new java.util.Date(rs.getDate(8).getTime()));
				irdto.setEndDay(new java.util.Date(rs.getDate(9).getTime()));
				irdto.setPayDay(new java.util.Date(rs.getDate(10).getTime()));
				irdto.setSuccessMoney(rs.getInt(11));
				irdto.setNowMoney(rs.getInt(12));
				irdto.setThumbImage(rs.getString(13));
				irdto.setMainThumb(rs.getString(14));
				irdto.setMainImage(rs.getString(15));
				irdto.setMainText(rs.getString(16));
				irdto.setFile(rs.getString(17));
				irdto.setFundSituation(rs.getInt(18));
				irdto.setPermitChk(rs.getBoolean(19));
				irdto.setUpdateChk(rs.getBoolean(20));
			}
		}catch(Exception e){
			System.out.println("getInvestRequest硫붿냼�뱶 �궡遺��뿉�꽌 �삤瑜� : "+e);
		}finally{
			//�옄�썝�빐�젣
			freeRes(con, pstmt, rs);
		}
		//InvestRequstDto媛앹껜 李몄“蹂��닔 由ы꽩
		return irdto;
		
		
	}
	
	//�닾�옄 �쁽�솴 �솗�씤�븯�뒗 硫붿냼�뱶 �꽦怨듭씤吏� �떎�뙣�씤吏� 吏꾪뻾以묒씤吏�?
	//�씠硫붿냼�뱶�뒗 以꾩씪�젮硫� �꽌釉뚯옘由щ�� �궗�슜�븯�뿬 以꾩씪�닔 �엳�뒿�땲�떎...
	//�꽌釉뚯옘由щ줈 �옉�꽦�븯�떎�옒�슂 �뀕�뀕?
	@Override
	public int ConditionInvest(int num, Date endDay, int successMoney) {
		//由ы꽩媛� 諛섑솚�븷 int蹂��닔 �꽑�뼵
		
		//DB�뿰寃� 3珥앹궗
		
		//DB�뿰寃�
		
		//SQL荑쇰━援щЦ �옉�꽦(臾댁뾿�쓣 媛��졇���꽌 �쁽�솴�쓣 �솗�씤�븷 寃껋씤吏�?)
		
		//荑쇰━ �떎�뻾媛�  RS�뿉 �떞湲�
		
		//RS�쓽 NEXT媛믪씠 �엳�떎硫�?
		
		//SQL荑쇰━援щЦ �옉�꽦(媛��졇�삩 媛믪뿉���빐�꽌.. 醫낅즺�씪怨� 紐⑺몴湲덉븸�쓣 鍮꾧탳�븯�뿬 荑쇰━�떎�뻾媛믪씠 �굹�삤�뒗吏�
		return 0;
	}
	//�닾�옄 媛��뒫 湲덉븸 遺덈윭�삤湲�
		public InvestRequestDto getmoneys(int investRequestNum) {
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			String sql = "";
			
			InvestRequestDto ivrDto = new InvestRequestDto();
			
			
			
			try {
//				System.out.println(investRequestNum);
				con = getCon();
				sql = "select successMoney, nowMoney, program from investRequest where investRequestNum = ?";
				
				pstmt=con.prepareStatement(sql);
				
				pstmt.setInt(1, investRequestNum);
				
				rs=pstmt.executeQuery();
				
				if(rs.next()){
					
					ivrDto.setSuccessMoney(rs.getInt(1));
					ivrDto.setNowMoney(rs.getInt(2));
					ivrDto.setProgram(rs.getString(3));
				}
				
			} catch (Exception e) {
				System.out.println("getmoneys硫붿냼�뱶�뿉�꽌 �삤瑜� : " + e);
			}	finally {
				freeRes(con, pstmt, rs);
			}
			
			return ivrDto;
		}
		// �닾�옄�옄媛� �닾�옄 �떆 �쁽�옱湲덉븸 利앷� 
		public InvestRequestDto increaseNowMoney(int nowMoney, int InvestRequestNum) {
				//		DB �뿰寃�
			
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					String sql = "";
					
					InvestRequestDto ivrDto = new InvestRequestDto();
					
					try {
						con = getCon();
						sql = "update investRequest set nowMoney=nowMoney+? where investRequestNum=?";
						
						pstmt = con.prepareStatement(sql);
						
						
						pstmt.setInt(1, nowMoney);
						pstmt.setInt(2, InvestRequestNum);
						pstmt.executeUpdate();
						
						
								
					} catch (Exception e) {
						System.out.println("increaseNowMoney硫붿냼�뱶�뿉�꽌 �삤瑜� : " + e);
					}finally {
						freeRes(con, pstmt, rs);
					}
					return ivrDto;
			
		}
		//�닾�옄湲덉븸 100% �떖�꽦 �떆 ���뵫吏꾪뻾�긽�솴(fundsituation) 蹂�寃�
		public int updateFundsituation(int InvestRequestNum) {
				//		DB �뿰寃�
			
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					
					String sql = "";
					
					int fundsituation = 1;
					
					try {
						con = getCon();
						sql = "select nowMoney, successMoney, startDay, endDay from investRequest where investRequestNum=?";
						
						pstmt = con.prepareStatement(sql);
						
						System.out.println(InvestRequestNum);
						pstmt.setInt(1, InvestRequestNum);
						
						rs = pstmt.executeQuery();
						
						if(rs.next()){
							System.out.println("異쒕젰" + InvestRequestNum);
							if(rs.getInt(1)==rs.getInt(2)){
								System.out.println(rs.getInt(1)+","+rs.getInt(2));
								sql = "update investRequest set fundsituation=3 where investRequestNum=?";
								pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, InvestRequestNum);
								pstmt.executeUpdate();

							}						
						}				
					} catch (Exception e) {
						System.out.println("updateFundsituation硫붿냼�뱶�뿉�꽌 �삤瑜� : " + e);
					}finally {
						freeRes(con, pstmt, rs);
					}
					return fundsituation;
		}
		
		
		//�궗�씠�듃�뿉 �젒�냽�븷�븣 紐⑤뱺 fundSituation�씠 1�씤 InvestRequest�뱾�쓣 媛��졇���꽌 醫낅즺�궇吏쒖� �떆�뒪�뀥 �궇吏� �솗�씤�빐�꽌 2(�떎�뙣), 4(80%�씠�긽 �떖�꽦 �꽦怨�)�쑝濡� 泥섎━
		public void checkInvestRequest() {
			ArrayList<InvestRequestDto> irlist = new ArrayList<InvestRequestDto>();
			InvestRequestDto irdto = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = getCon();
				//fundSutiation�씠 1�씠怨� permitChk媛� true�씤 紐⑤뱺 �옄猷� 媛��졇�삤湲�
				//�븘�슂�븳 媛믩쭔 媛곴컖 �뵒鍮꾩뿉 �젒�냽�빐�꽌 �떒怨꾨쭏�떎 �뵒鍮꾩젒�냽�빐�꽌 泥섎━�븷吏� X
				//泥섏쓬 紐⑤뱺 媛믪쓣 dto�뿉 �떞怨� �빐�떦 議곌굔�뿉 �뵲�씪 �닔�젙�븳 �썑 �씪愿꾩쿂由ы븷吏� O
				//�씪愿꾩쿂由�
				String sql = "select * from investRequest where fundSituation=1 and permitChk=true";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
				while(rs.next()){
					irdto = new InvestRequestDto();
					String memberEmail = rs.getString(1);
					int investRequestNum = rs.getInt(2);
					String program  = rs.getString(3);
					String dName = rs.getString(4);
					String introduce = rs.getString(5);
					String category = rs.getString(6);
					String hashtag = rs.getString(7);
					java.sql.Date startDay = rs.getDate(8);
					java.sql.Date endDay = rs.getDate(9);
					java.sql.Date payDay = rs.getDate(10);
					int successMoney = rs.getInt(11);
					int nowMoney = rs.getInt(12);
					String thumbImage = rs.getString(13);
					String mainThumb = rs.getString(14);
					String mainImage = rs.getString(15);
					String mainText = rs.getString(16);
					String file = rs.getString(17);
					int fundSituation = rs.getInt(18);
					boolean permitChk = rs.getBoolean(19);
					boolean updateChk = rs.getBoolean(20);
					
//					System.out.println("checkInvestRequest()硫붿냼�뱶�뿉�꽌 異쒕젰�솗�씤");
//					System.out.println("memberEmail:"+memberEmail);
//					System.out.println("investRequest:"+investRequestNum);
//					System.out.println("program:"+program);
//					System.out.println("dName:"+dName);
//					System.out.println("introduce:"+introduce);
//					System.out.println("category:"+category);
//					System.out.println("hashtag:"+hashtag);
//					System.out.println("startDay:"+startDay);
//					System.out.println("endDay:"+endDay);//�떆 遺� 珥� 誘몃━珥덇� �뾾�뒗 �궇�옄媛�
//					System.out.println("payDay:"+payDay);
//					System.out.println("successMoney:"+successMoney);
//					System.out.println("nowMoney:"+nowMoney);
//					System.out.println("thumbImage:"+thumbImage);
//					System.out.println("mainThumb:"+mainThumb);
//					System.out.println("mainImage:"+mainImage);
//					System.out.println("mainText:"+mainText);
//					System.out.println("file:"+file);
//					System.out.println("fundSituation:"+fundSituation);
//					System.out.println("permitChk:"+permitChk);
//					System.out.println("updateChk:"+updateChk);
					
					irdto.setMemberEmail(memberEmail);
					irdto.setInvestRequestNum(investRequestNum);
					irdto.setProgram(program);
					irdto.setDName(dName);
					irdto.setIntroduce(introduce);
					irdto.setCategory(category);
					irdto.setHashTag(hashtag);
					irdto.setStartDay(startDay);
					irdto.setEndDay(endDay);
					irdto.setPayDay(payDay);
					irdto.setSuccessMoney(successMoney);
					irdto.setNowMoney(nowMoney);
					irdto.setThumbImage(thumbImage);
					irdto.setMainThumb(mainThumb);
					irdto.setMainImage(mainImage);
					irdto.setMainText(mainText);
					irdto.setFile(file);
					irdto.setFundSituation(fundSituation);
					irdto.setPermitChk(permitChk);
					irdto.setUpdateChk(updateChk);
					
					java.sql.Date today = new java.sql.Date(System.currentTimeMillis());
					
//					System.out.println("endDay");
//					System.out.println(endDay.getTime()/1000);
//					System.out.println(endDay.getTime()/1000/60);
//					System.out.println(endDay.getTime()/1000/60/60);
//					System.out.println(endDay.getTime()/1000/60/60/24);
//					System.out.println(irdto.getEndDay().getTime()+":"+today.getTime());
//					System.out.println("today:"+today);//�떆 遺� 珥� 誘몃━珥덇� �룷�븿�맂 �궇�옄媛�
//					System.out.println("System-Day");
//					System.out.println(today.getTime()/1000);
//					System.out.println(today.getTime()/1000/60);
//					System.out.println(today.getTime()/1000/60/60);
//					System.out.println(today.getTime()/1000/60/60/24-1);
					long ed = endDay.getTime()/24/60/60/1000;
					long sd = today.getTime()/24/60/60/1000-1;
					//�궇吏쒕�� 鍮꾧탳�빐�빞�릺�뒗�뜲 today�뒗 System.currentTimeMills()�씠湲곕븣臾몄뿉 �떆 遺� 珥� 誘몃━珥덇퉴吏� �룷�븿�릺�뼱�엳�떎.
					//洹몃옒�꽌 �솗�떎�븯寃� 鍮꾧탳媛� �릺吏� �븡湲� �븣臾몄뿉 �떆 遺� 珥� 誘몃━珥덈�� �젣嫄고븯�뒗 湲곕뒫�쓣 �뜥�빞�븷 寃� 媛숇떎
					if(ed-sd<=0){
//						System.out.println("�궇吏쒕퉬援먮뒗 �걹�궖怨�, �룉�쓣 �솗�씤�븯�옄.");
//						System.out.println((float)nowMoney/(float)successMoney*100);
						//2踰�(�닾�옄 �떎�뙣)�씤 寃쎌슦 泥섎━
						if((double)nowMoney/(double)successMoney*100 < 80.0){
//							System.out.println("�떎�뙣�떎.");
							sql = "update investRequest set fundSituation=2 where investRequestNum=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, investRequestNum);
							pstmt.executeUpdate();
						}else if((double)nowMoney/(double)successMoney*100 >= 80.0){
						//4踰�(�닾�옄 �꽦怨� > 80% �씠�긽 紐⑥엫)�씤 寃쎌슦 泥섎━
//							System.out.println("�꽦怨듭씠�떎.");
							sql = "update investRequest set fundSituation=4 where investRequestNum=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, investRequestNum);
							pstmt.executeUpdate();
						}
					}
					
					
				}
			} catch (Exception e) {
				System.out.println("checkInvestRequest()硫붿냼�뱶�뿉�꽌 �삤瑜� 諛쒖깮"+e);
			} finally {
				freeRes(con, pstmt, rs);
			}
			
		}
		
}
