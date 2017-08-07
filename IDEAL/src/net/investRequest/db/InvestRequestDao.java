package net.investRequest.db;

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







public class InvestRequestDao implements InvestRequestMethod{
	
	//Connection 객체 얻어오는 메소드를 작성해주세요!
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
	
	
	//product.jsp페이지에서 세로 페이징을 하기위해서 검색조건(category, search)에 따른 각기 다른 count를 처리해야 한다.
	//가로로 최대 4개의 자료를 출력하게 되어 있으므로 4개단위로 높이를 변경하면 될 것 같다. 
	public int getCountInvestRequestList(String category, String search){
		int count = 0;
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			con = getCon();
			String sql = "";
			//투자하기 버튼을 눌럿거나, category가 All이고 검색어를 입력하지 않았을 경우 모든 permitChk=ture(1)인 목록을 불러온다(fundsituation값에 무관하다)
			if(category.equals("All") && (search == null || search.equals(""))){
//				System.out.println("ALL:검색어없음");
				sql = "select count(*) "
					+ "from investRequest "
					+ "where permitChk = true "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			//category가 All이고 검색어를 입력한 경우
			}else if(category.equals("All") && !(search ==null || search.equals(""))){
//				System.out.println("ALL:검색어 있음");
				sql = "select count(*) "
					+ "from investRequest "
					+ "where permitChk = true "
					+ "and (program like ? or introduce like ?) "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
				rs = pstmt.executeQuery();
			//category가 All이 아니고 검색어를 입력하지 않았을 경우 
			}else if(!category.equals("All")  && (search ==null || search.equals(""))){
//				System.out.println("ALL아님:검색어없음");
				sql = "select count(*) "
					+ "from investRequest "
					+ "where permitChk = true "
					+ "and category = ? "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category);
				rs = pstmt.executeQuery();
			//category가 All이 아니고 검색어를 입력한 경우
			}else if(!category.equals("All")){
//				System.out.println("ALL아님:검색어 있음");
				sql = "select count(*) "
					+ "from investRequest "
					+ "where permitChk = true "
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
			System.out.println("getCountInvestRequestList()메소드에서 오류 발생:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
//		System.out.println("getCountInvestRequestList()메소드 출력확인 count:"+count);
		return count;
	}
	
	
	//메인에서 투자하기 버튼을 클릭하여서 캠페인 목록을 볼 때 사용. 처음에 투자하기 버튼 클릭시에는 All조건으로 검색하고
	//product.jsp페이지 내의 상단에 Search쪽에 select(category)값을 설정하고 검색할 경우에는 해당 조건에 맞게 검색한다.
	public ArrayList<InvestRequestDto> ListInvestRequest(String category, String search) {
		ArrayList<InvestRequestDto> irlist = new ArrayList<InvestRequestDto>();
		InvestRequestDto irdto = null;
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
//		System.out.println("ListInvestRequest()메소드 체크");
//		System.out.println("category:"+category+": search:"+search);
		
		try {
			con = getCon();
			String sql = "";
			//투자하기 버튼을 눌럿거나, category가 All이고 검색어를 입력하지 않았을 경우 모든 permitChk=ture(1)인 목록을 불러온다(fundsituation값에 무관하다)
			if(category.equals("All") && (search == null || search.equals(""))){
//				System.out.println("ALL:검색어없음");
				sql = "select * "
					+ "from investRequest "
					+ "where permitChk = true "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				rs = pstmt.executeQuery();
			//category가 All이고 검색어를 입력한 경우
			}else if(category.equals("All") && !(search ==null || search.equals(""))){
//				System.out.println("ALL:검색어 있음");
				sql = "select * "
					+ "from investRequest "
					+ "where permitChk = true "
					+ "and (program like ? or introduce like ?) "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, "%"+search+"%");
				pstmt.setString(2, "%"+search+"%");
				rs = pstmt.executeQuery();
			//category가 All이 아니고 검색어를 입력하지 않았을 경우 
			}else if(!category.equals("All")  && (search ==null || search.equals(""))){
//				System.out.println("ALL아님:검색어없음");
				sql = "select * "
					+ "from investRequest "
					+ "where permitChk = true "
					+ "and category = ? "
					+ "order by startDay desc";
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, category);
				rs = pstmt.executeQuery();
			//category가 All이 아니고 검색어를 입력한 경우
			}else if(!category.equals("All")){
//				System.out.println("ALL아님:검색어 있음");
				sql = "select * "
					+ "from investRequest "
					+ "where permitChk = true "
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
//				System.out.println("ListInvestRequest() 캠페인 목록 있음");
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
				
//				System.out.println("ListInvestRequest(String category, String search)메소드에서 출력확인");
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
			System.out.println("ListInvestRequest()메소드에서 오류 발생:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		return irlist;
	}
	
	//투자요청한 모든사람을 보여주는 메소드
	@Override
	public ArrayList<InvestRequestDto> ListInvestRequest(String category, String search, int pageSize) {
		//투자요청한 모든사람을 담을 가변길이 배열 생성
		
		//DB연결 3총사 저장
		
		try{
			//DB연결
			
			//SQL쿼리문 작성(모든걸 select하되, category와 search값을 기준으로)
			
			//preparestatement객체에 연결객체 및 쿼리문 저장
			
			//preparestatement객체에 쿼리문에 있는 ?값 설정
			
			//preparestatement객체로 쿼리문 실행값 resultset에 담기
			
			//resultset이 끝나기 전까지..
			
			//InvestRequestDto객체 생성하여
			
			//resultset에서 각각 꺼내어 담아내기
			
			//가변길이 배열에 InvestRequestDto객체추가하기
		}catch(Exception e){
			System.out.println("ListInvestRequest메소드 내부에서 오류 : "+e);
		}finally{
			
		}
		
		
		//가변길이 배열 리턴
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
			String sql = "select * from investRequest where permitChk = 1 order by startDay desc limit 0, 8";
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
				
//				System.out.println("ListInvestRequest()메소드에서 출력확인");
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
			System.out.println("ListInvestRequest()메소드에서 오류 발생:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		
		return irlist;
	}

	//투자요청 메소드
	@Override
	public int InsertInvestRequst(InvestRequestDto irdto) {
		//DB연결 2총사
		Connection con = null;
		PreparedStatement pstmt = null;
		
		//성공여부저장할 int타입의 변수생성
		int check = 0;
		try{
			//DB연결
			con = getCon();
			//SQL구문 작성
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
			//preparestatement객체에 쿼리문 및 연결객체 저장
			pstmt = con.prepareStatement(sql);
			//preparestatement객체에 쿼리문 ?값 설정
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
			//nowMoney는 0으로 기본값 설정
			pstmt.setString(11, irdto.getThumbImage());
			pstmt.setString(12, irdto.getMainThumb());
			pstmt.setString(13, irdto.getMainImage());
			pstmt.setString(14, irdto.getMainText());
			pstmt.setString(15, irdto.getFile());
			//나머지 3개의 값 funcSituation, permitShk, updateChk값들은 sql구문에 기본값 설정
			
			//preparestatement객체 실행하여 설공여부 저장
			check = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("InsertInvestRequest메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		//int타입의 변수 리턴
		return check;
	}
	
	/*max num 구하는 메소드*/ //AUTO_INCREMENT�㎟� 때문에 보류
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
			System.out.println("getMaxInvestRequestNum()메소드에서 오류 발생:"+e);
		} finally {
			freeRes(con, pstmt, rs);
		}
		if(max==0) max = 1;
		
		return max;
	}
	
	
	//num값을 기준으로 하나의 투자요청 페이지를 가져오는 메소드
	@Override
	public InvestRequestDto getInvestRequest(int investRequestNum) {
		//InvestRequstDto타입의 변수 선언
		InvestRequestDto irdto = new InvestRequestDto();
		//DB3총사
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			//DB연결
			con = getCon();
			//SQL구문 작성
			String sql = "select * from investRequest where investRequestNum = ?";
			//preparestatement객체에 쿼리문 및 연결객체 저장
			pstmt = con.prepareStatement(sql);
			//preparestatement객체내부에 ?값 셋팅
			pstmt.setInt(1, investRequestNum);
			//resultset에 쿼리 실행값 저장
			rs = pstmt.executeQuery();
			//resultset내부에 저장된값이 있을 경우..
			if(rs.next()){
				//InvestRequstDto에 resultset값을 각각 꺼내와 저장
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
			System.out.println("getInvestRequest메소드 내부에서 오류 : "+e);
		}finally{
			//자원해제
			freeRes(con, pstmt, rs);
		}
		//InvestRequstDto객체 참조변수 리턴
		return irdto;
		
		
	}
	
	//투자 현황 확인하는 메소드 성공인지 실패인지 진행중인지?
	//이메소드는 줄일려면 서브쿼리를 사용하여 줄일수 있습니다...
	//서브쿼리로 작성하실래요 ㅎㅎ?
	@Override
	public int ConditionInvest(int num, Date endDay, int successMoney) {
		//리턴값 반환할 int변수 선언
		
		//DB연결 3총사
		
		//DB연결
		
		//SQL쿼리구문 작성(무엇을 가져와서 현황을 확인할 것인지?)
		
		//쿼리 실행값  RS에 담기
		
		//RS의 NEXT값이 있다면?
		
		//SQL쿼리구문 작성(가져온 값에대해서.. 종료일과 목표금액을 비교하여 쿼리실행값이 나오는지
		return 0;
	}
	//투자 가능 금액 불러오기
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
				System.out.println("getmoneys메소드에서 오류 : " + e);
			}	finally {
				freeRes(con, pstmt, rs);
			}
			
			return ivrDto;
		}
		// 투자자가 투자 시 현재금액 증가 
		public InvestRequestDto increaseNowMoney(int nowMoney, int InvestRequestNum) {
				//		DB 연결
			
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
						System.out.println("increaseNowMoney메소드에서 오류 : " + e);
					}finally {
						freeRes(con, pstmt, rs);
					}
					return ivrDto;
			
		}
		//투자금액 100% 달성 시 펀딩진행상황(fundsituation) 변경
		public int updateFundsituation(int InvestRequestNum) {
				//		DB 연결
			
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
							System.out.println("출력" + InvestRequestNum);
							if(rs.getInt(1)==rs.getInt(2)){
								System.out.println(rs.getInt(1)+","+rs.getInt(2));
								sql = "update investRequest set fundsituation=3 where investRequestNum=?";
								pstmt = con.prepareStatement(sql);
								pstmt.setInt(1, InvestRequestNum);
								pstmt.executeUpdate();

							}						
						}				
					} catch (Exception e) {
						System.out.println("updateFundsituation메소드에서 오류 : " + e);
					}finally {
						freeRes(con, pstmt, rs);
					}
					return fundsituation;
		}
		
		
		//사이트에 접속할때 모든 fundSituation이 1인 InvestRequest들을 가져와서 종료날짜와 시스템 날짜 확인해서 2(실패), 4(80%이상 달성 성공)으로 처리
		public void checkInvestRequest() {
			ArrayList<InvestRequestDto> irlist = new ArrayList<InvestRequestDto>();
			InvestRequestDto irdto = null;
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try {
				con = getCon();
				//fundSutiation이 1이고 permitChk가 true인 모든 자료 가져오기
				//필요한 값만 각각 디비에 접속해서 단계마다 디비접속해서 처리할지 X
				//처음 모든 값을 dto에 담고 해당 조건에 따라 수정한 후 일괄처리할지 O
				//일괄처리
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
					
//					System.out.println("checkInvestRequest()메소드에서 출력확인");
//					System.out.println("memberEmail:"+memberEmail);
//					System.out.println("investRequest:"+investRequestNum);
//					System.out.println("program:"+program);
//					System.out.println("dName:"+dName);
//					System.out.println("introduce:"+introduce);
//					System.out.println("category:"+category);
//					System.out.println("hashtag:"+hashtag);
//					System.out.println("startDay:"+startDay);
//					System.out.println("endDay:"+endDay);//시 분 초 미리초가 없는 날자값
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
//					System.out.println("today:"+today);//시 분 초 미리초가 포함된 날자값
//					System.out.println("System-Day");
//					System.out.println(today.getTime()/1000);
//					System.out.println(today.getTime()/1000/60);
//					System.out.println(today.getTime()/1000/60/60);
//					System.out.println(today.getTime()/1000/60/60/24-1);
					long ed = endDay.getTime()/24/60/60/1000;
					long sd = today.getTime()/24/60/60/1000-1;
					//날짜를 비교해야되는데 today는 System.currentTimeMills()이기때문에 시 분 초 미리초까지 포함되어있다.
					//그래서 확실하게 비교가 되지 않기 때문에 시 분 초 미리초를 제거하는 기능을 써야할 것 같다
					if(ed-sd<=0){
//						System.out.println("날짜비교는 끝낫고, 돈을 확인하자.");
//						System.out.println((float)nowMoney/(float)successMoney*100);
						//2번(투자 실패)인 경우 처리
						if((double)nowMoney/(double)successMoney*100 < 80.0){
//							System.out.println("실패다.");
							sql = "update investRequest set fundSituation=2 where investRequestNum=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, investRequestNum);
							pstmt.executeUpdate();
						}else if((double)nowMoney/(double)successMoney*100 >= 80.0){
						//4번(투자 성공 > 80% 이상 모임)인 경우 처리
//							System.out.println("성공이다.");
							sql = "update investRequest set fundSituation=4 where investRequestNum=?";
							pstmt = con.prepareStatement(sql);
							pstmt.setInt(1, investRequestNum);
							pstmt.executeUpdate();
						}
					}
					
					
				}
			} catch (Exception e) {
				System.out.println("checkInvestRequest()메소드에서 오류 발생"+e);
			} finally {
				freeRes(con, pstmt, rs);
			}
			
		}
}
