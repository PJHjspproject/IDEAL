package net.Admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import org.w3c.dom.Comment;

import net.InformationUse.db.InformationUseDto;
import net.Investment.db.InvestmentDto;
import net.board.db.BoardDto;
import net.board.db.CommentDto;
import net.investRequest.db.InvestRequestDto;
import net.member.db.MemberDTO;
import net.notice.db.noticeDto;
import net.question.db.questionDto;


public class AdminDao {

	private Connection getCon() throws Exception{

		Connection con = null;
		
		Context init = new InitialContext();
		
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		
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
	
	// 占쎈떮占쎌쁽 占쎌뒄筌ｏ옙 占쎌겱占쎌넺占쎈퓠 �꽴占쏙옙釉� 筌롫뗄�꺖占쎈굡占쎈굶 占쎈뻻占쎌삂
	
	
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럩�뭵嶺뚳퐦�삕 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
		public ArrayList<InvestRequestDto> InvestRequestList(){
			ArrayList<InvestRequestDto> InvestRequestList = new ArrayList<InvestRequestDto>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try{
				con = getCon();
				String sql = "SELECT * FROM investrequest WHERE permitChk=? ";
				pstmt = con.prepareStatement(sql);
				pstmt.setBoolean(1, false);
				
				rs = pstmt.executeQuery();
				while(rs.next()){
					InvestRequestDto irdto = new InvestRequestDto();
					irdto.setCategory(rs.getString("category"));
					irdto.setDName(rs.getString("dName"));
					irdto.setEndDay(rs.getTimestamp("endDay"));
					irdto.setFile(rs.getString("file"));
					irdto.setFundSituation(rs.getInt("fundSituation"));
					irdto.setHashTag(rs.getString("hashTag"));
					irdto.setIntroduce(rs.getString("introduce"));
					irdto.setInvestRequestNum(rs.getInt("investRequestNum"));
					irdto.setMainImage(rs.getString("mainImage"));
					irdto.setMainText(rs.getString("mainText"));
					irdto.setMainThumb(rs.getString("mainThumb"));
					irdto.setMemberEmail(rs.getString("memberEmail"));
					irdto.setNowMoney(rs.getInt("nowMoney"));
					irdto.setPayDay(rs.getTimestamp("payDay"));
					irdto.setPermitChk(rs.getBoolean("permitChk"));
					irdto.setProgram(rs.getString("program"));
					irdto.setStartDay(rs.getTimestamp("startDay"));
					irdto.setSuccessMoney(rs.getInt("successMoney"));
					irdto.setThumbImage(rs.getString("thumbImage"));
					irdto.setUpdateChk(rs.getBoolean("updateChk"));
					InvestRequestList.add(irdto);
				}			
			}catch(Exception e){
				System.out.println("InvestRequestList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
			}finally{
				freeRes(con, pstmt, rs);
			}
			
			return InvestRequestList;
		}
	
	// 占쎈떮占쎌쁽 占쎌뒄筌ｏ옙 �뵳�딅뮞占쎈뱜 揶쏉옙占쎌죬占쎌궎占쎈뮉 筌롫뗄�꺖占쎈굡 占쎈뻻占쎌삂
	public ArrayList<InvestRequestDto> InvestRequestList(String memberEmail){
		ArrayList<InvestRequestDto> InvestRequestList = new ArrayList<InvestRequestDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM investrequest WHERE memberEmail=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			while(rs.next()){
				InvestRequestDto irdto = new InvestRequestDto();
				irdto.setCategory(rs.getString("category"));
				irdto.setDName(rs.getString("dName"));
				irdto.setEndDay(rs.getTimestamp("endDay"));
				irdto.setFile(rs.getString("file"));
				irdto.setFundSituation(rs.getInt("fundSituation"));
				irdto.setHashTag(rs.getString("hashtag"));
				irdto.setIntroduce(rs.getString("introduce"));
				irdto.setInvestRequestNum(rs.getInt("investRequestNum"));
				irdto.setMainImage(rs.getString("mainImage"));
				irdto.setMainText(rs.getString("mainText"));
				irdto.setMainThumb(rs.getString("mainThumb"));
				irdto.setMemberEmail(rs.getString("memberEmail"));
				irdto.setNowMoney(rs.getInt("nowMoney"));
				irdto.setPayDay(rs.getTimestamp("payDay"));
				irdto.setPermitChk(rs.getBoolean("permitChk"));
				irdto.setProgram(rs.getString("program"));
				irdto.setStartDay(rs.getTimestamp("startDay"));
				irdto.setSuccessMoney(rs.getInt("successMoney"));
				irdto.setThumbImage(rs.getString("thumbImage"));
				irdto.setUpdateChk(rs.getBoolean("updateChk"));
				InvestRequestList.add(irdto);
			}			
		}catch(Exception e){
			System.out.println("InvestRequestList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return InvestRequestList;
	}
	// 占쎈떮占쎌쁽 占쎌뒄筌ｏ옙 �뵳�딅뮞占쎈뱜 揶쏉옙占쎌죬占쎌궎占쎈뮉 筌롫뗄�꺖占쎈굡 占쎄국
	
	// 占쎈떮占쎌쁽 占쎌뒄筌ｏ옙 占쎌겱占쎌넺占쎈퓠 �꽴占쏙옙釉� 筌롫뗄�꺖占쎈굡占쎈굶 占쎄국
	
	//�뜝�럥�뼫�뜝�럩�겱�뜝�럩�겱�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	
	
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럩�뭵嶺뚳퐦�삕�뼨�먯삕�뜝�럥�뱺�뜝�룞�삕�뜝�럥由� �뜝�럥�뼫�뜝�럩�겱�뜝�럩�겱�뜝�럥援뜹뜝�럩諭� 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<InvestmentDto> InvestMentList(int investRequestNum){
		ArrayList<InvestmentDto> InvestMentList = new ArrayList<InvestmentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM investment where investRequestNum=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, investRequestNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InvestmentDto idto = new InvestmentDto();
				
				idto.setInvestNum(rs.getInt("investMentNum"));
				
				idto.setMemberEmail(rs.getString("memberEmail"));
				
				idto.setProgram(rs.getString("program"));
				
				InvestMentList.add(idto);
			}
		}catch(Exception e){
			System.out.println("InvestMentList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占�  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럩�뭵嶺뚳퐦�삕�뼨�먯삕�뜝�럥�뱺�뜝�룞�삕�뜝�럥由� �뜝�럥�뼫�뜝�럩�겱�뜝�럩�겱�뜝�럥援뜹뜝�럩諭� 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럥�뼫�뜝�럩�겱�뜝�럥由� �뜝�럡���뜝�럥�뿴 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<InvestmentDto> InvestMentList(String memberEmail){
		ArrayList<InvestmentDto> InvestMentList = new ArrayList<InvestmentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM investment where memberEmail=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InvestmentDto idto = new InvestmentDto();
				
				idto.setInvestNum(rs.getInt("investMentNum"));
				idto.setInvestRequestNum(rs.getInt("investRequestNum"));
				idto.setMemberEmail(rs.getString("memberEmail"));
				idto.setInvestMoney(rs.getInt("investMentMoney"));
				idto.setProgram(rs.getString("program"));
				
				InvestMentList.add(idto);
			}
		}catch(Exception e){
			System.out.println("InvestMentList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占�  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	//�뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럥�뼫�뜝�럩�겱�뜝�럥由� �뜝�럡���뜝�럥�뿴 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥�뼫�뜝�럩�겱�뜝�럩�겱�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	/*嶺뚮、�걞�떋占썲뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚*/
	
	//�뜝�럩�뤂�뜝�럩�쐸 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<MemberDTO> MemberList(){
		ArrayList<MemberDTO> MemberList = new ArrayList<MemberDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql = "SELECT * FROM member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MemberDTO mdto = new MemberDTO();
				mdto.setBirth(rs.getString("birth"));
				mdto.setMemberEmail(rs.getString("memberemail"));
				mdto.setName(rs.getString("name"));
				mdto.setNickName(rs.getString("nickname"));
				mdto.setPass(rs.getString("pass"));
				mdto.setPhone(rs.getString("phone"));
				
				MemberList.add(mdto);
			}
			
		}catch(Exception e){
			System.out.println("MemberList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}

		return MemberList;
	}
	//�뜝�럩�뤂�뜝�럩�쐸 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
		
	// 占쎈떮占쎌쁽占쎈립 占쎄땀占쎈열 占쎄맒占쎄쉭癰귣떯由� 占쎈뻻占쎌삂
	public InvestmentDto getOneInvestMent(int investMentNum){
		InvestmentDto imdto = new InvestmentDto();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql = "SELECT * FROM investment WHERE investMentNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, investMentNum);
			rs = pstmt.executeQuery();
			if(rs.next()){
				
				imdto.setInvestNum(rs.getInt("investMentNum"));
				imdto.setInvestRequestNum(rs.getInt("investRequestNum"));
				imdto.setMemberEmail(rs.getString("memberEmail"));
				imdto.setInvestMoney(rs.getInt("investMentMoney"));
				imdto.setInvestName(rs.getString("investMentName"));
				imdto.setProgram(rs.getString("program"));				
			}
				
		}catch(Exception e){
			System.out.println("getOneMember筌롫뗄�꺖占쎈굡 占쎄땀�겫占쏙옙肉됵옙苑� 占쎌궎�몴占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
			return imdto;
	}
	
	public ArrayList<InvestmentDto> InvestMentList(){
		ArrayList<InvestmentDto> InvestMentList = new ArrayList<InvestmentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM investment";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InvestmentDto idto = new InvestmentDto();
				
				idto.setInvestNum(rs.getInt("investMentNum"));
				idto.setInvestRequestNum(rs.getInt("investRequestNum"));
				idto.setMemberEmail(rs.getString("memberEmail"));
				idto.setInvestName(rs.getString("investMentName"));
				idto.setInvestMoney(rs.getInt("investMentMoney"));
				idto.setProgram(rs.getString("program"));									
				InvestMentList.add(idto);
			}
		}catch(Exception e){
			System.out.println("InvestMentList筌롫뗄�꺖占쎈굡 占쎄땀�겫占쏙옙肉됵옙苑� 占쎌궎�몴占�  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	
	
	//�뜝�럩逾뺡쨹恝�삕 �뜝�럥�뼫�뜝�럩�겱 嶺뚯쉳�뫓筌묕옙 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	//�뜝�럩沅롧뵓怨뚯뫊餓λ뜉�삕�얇꺈�삕占쎈さ�슖�댙�삕 占쎄껀占쎈쐞占쎈뉴�뜝�럥�꽘 �뤆�룆占썬굦逾� �뜝�럩肉녑뜝�럩紐든춯濡녹삕 �뜝�럥�뿰�뼨轅명�▽빳占� �뜝�룞�삕�뜝�럡�맋 嶺뚯쉳�뫓筌묕옙
	//�윜諛몄굡繹먲옙 占쎈쨨占쎈맪�뗧춯�쉶���뵳占� �뜝�럩留꾢뜝�럥�돵 �뜝�럥由��뜝�럡�룎 �뜝�럥�맠 嶺뚮씭�뒧獄�酉댁퀪�뜝占�
	public ArrayList<InvestRequestDto> InvestRequestList(boolean permitChk){
		ArrayList<InvestRequestDto> InvestRequestList = new ArrayList<InvestRequestDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql="";
			
			 sql= "SELECT * FROM investrequest WHERE permitChk=?";	
			
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, true);
			
			rs = pstmt.executeQuery();
			while(rs.next()){
				InvestRequestDto irdto = new InvestRequestDto();
				irdto.setCategory(rs.getString("category"));
				irdto.setDName(rs.getString("dName"));
				irdto.setEndDay(rs.getTimestamp("endDay"));
				irdto.setFile(rs.getString("file"));
				irdto.setFundSituation(rs.getInt("fundSituation"));
				irdto.setHashTag(rs.getString("hashTag"));
				irdto.setIntroduce(rs.getString("introduce"));
				irdto.setInvestRequestNum(rs.getInt("investRequestNum"));
				irdto.setMainImage(rs.getString("mainImage"));
				irdto.setMainText(rs.getString("mainText"));
				irdto.setMainThumb(rs.getString("mainThumb"));
				irdto.setMemberEmail(rs.getString("memberEmail"));
				irdto.setNowMoney(rs.getInt("nowMoney"));
				irdto.setPayDay(rs.getTimestamp("payDay"));
				irdto.setPermitChk(rs.getBoolean("permitChk"));
				irdto.setProgram(rs.getString("program"));
				irdto.setStartDay(rs.getTimestamp("startDay"));
				irdto.setSuccessMoney(rs.getInt("successMoney"));
				irdto.setThumbImage(rs.getString("thumbImage"));
				irdto.setUpdateChk(rs.getBoolean("updateChk"));
				InvestRequestList.add(irdto);
			}			
		}catch(Exception e){
			System.out.println("InvestRequestList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return InvestRequestList;
	}
	//�뜝�럩逾뺡쨹恝�삕 �뜝�럥�뼫�뜝�럩�겱 嶺뚯쉳�뫓筌묕옙 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	//�뜝�럩�뤂�뜝�럩�쐸 �뜝�럥由썹춯琉욧턂占쎈꺄 �뜝�럩�젧�솻洹⑥삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public MemberDTO getOneMember(String memberEmail){
		MemberDTO mdto = new MemberDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql = "SELECT * FROM member WHERE memberEmail=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			if(rs.next()){
				mdto.setBirth(rs.getString("birth"));
				mdto.setMemberEmail(rs.getString("memberEmail"));
				mdto.setName(rs.getString("name"));
				mdto.setNickName(rs.getString("nickName"));
				mdto.setPass(rs.getString("pass"));
				mdto.setPhone(rs.getString("phone"));
			}
			
		}catch(Exception e){
			System.out.println("getOneMember嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return mdto;
	}
	//�뜝�럩�뤂�뜝�럩�쐸 �뜝�럥由썹춯琉욧턂占쎈꺄 �뜝�럩�젧�솻洹⑥삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럩�뤂�뜝�럩�쐸 �뤆�룆踰∽옙�젷 �뜝�럡�돮�뜝�럥�떄�뜝�럥六삣뜝�럡�뀞�뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void DeleteMember(String nickname){
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try{
			con = getCon();
			String sql = "DELETE FROM member WHERE nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("DeleteMember嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//�뜝�럩�뤂�뜝�럩�쐸 �뤆�룆踰∽옙�젷 �뜝�럡�돮�뜝�럥�떄�뜝�럥六삣뜝�럡�뀞�뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	/*嶺뚮、�걞�떋占� 占쎄슈�뜝�룞�삕占쎌죨 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�*/
	
	/*�뇦猿딆뒩占쎈뻣�뜝�럥�냷�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚*/
	
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<BoardDto> BoardList(){
		ArrayList<BoardDto> BoardList = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDto bdto = new BoardDto();
				
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getTimestamp("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setNickName(rs.getString("nickName"));
				bdto.setNum(rs.getInt("num"));
				bdto.setTitle(rs.getString("title"));
				
				BoardList.add(bdto);
			}
			
		}catch(Exception e){
			System.out.println("BoardList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return BoardList;
	}
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥由썹춯琉욧턂占쎈꺄 �뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럥荑� �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<BoardDto> BoardList(String nickName){
		ArrayList<BoardDto> BoardList = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM board where nickName=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDto bdto = new BoardDto();
				
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getTimestamp("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setNickName(rs.getString("nickName"));
				bdto.setNum(rs.getInt("num"));
				bdto.setTitle(rs.getString("title"));
				
				BoardList.add(bdto);
			}
			
		}catch(Exception e){
			System.out.println("BoardList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return BoardList;
	}
	//�뜝�럥由썹춯琉욧턂占쎈꺄 �뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럥荑� �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public BoardDto getOneBoard(int num){
		BoardDto bdto = new BoardDto();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql = "SELECT * FROM board WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getTimestamp("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setNickName(rs.getString("nickName"));
				bdto.setNum(rs.getInt("num"));
				bdto.setTitle(rs.getString("title"));
			}
		}catch(Exception e){
			System.out.println("getOneBoard嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return bdto;
	}
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뜝�럡�뀭�뜝�럩�젷�뜝�럥六삣뜝�럡�뀞�뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public int DeleteBoard(int num){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int check = 0;
		try{
			con = getCon();
			
			String sql = "DELETE FROM board WHERE num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			check = pstmt.executeUpdate();
			System.out.println(check);
		}catch(Exception e){
			System.out.println("DeleteBoard嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		return check;
	}
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뜝�럡�뀭�뜝�럩�젷�뜝�럥六삣뜝�럡�뀞�뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	/*�뇦猿딆뒩占쎈뻣�뜝�럥�냷�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�*/
	
	/*�뜝�럥�냺�뼨�먯삕�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚*/
	
	//�뜝�럥�냺�뼨�먯삕 �뼨�먯삕�뵓怨뺣쐡占쎄퉰占쎄슈�뜝�룞�삕占쎌죨�뜝�럥�돵�뜝�럡�맋 占쎄덩占쎌뒧占쎌졎�썒�슣�닁�뵳占� �뜝�럥六삣뜝�럩�굚
	public ArrayList<CommentDto> commentListNum(int num){
		ArrayList<CommentDto> replyListNum = new ArrayList<CommentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM comment WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				CommentDto rdto = new CommentDto();
				rdto.setContent(rs.getString("content"));
				rdto.setDate(rs.getTimestamp("date"));
				rdto.setNickName(rs.getString("nickName"));
				rdto.setNum(rs.getInt("num"));
				rdto.setcNum(rs.getInt("cNum"));
				
				replyListNum.add(rdto);
			}
		}catch(Exception e){
			System.out.println("replyListNum嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return replyListNum;
	}
	//�뜝�럥�냺�뼨�먯삕 �뼨�먯삕�뵓怨뺣쐡占쎄퉰 占쎄슈�뜝�룞�삕占쎌죨�뜝�럥�돵�뜝�럡�맋 占쎄덩占쎌뒧占쎌졎�썒�슣�닁�뵳占� �뜝�럡援�
	
	//�뜝�럡���뤆�룊�삕 �뜝�럩�굚�뜝�럡�뎽�뜝�럥由� �뜝�럥�냺�뼨�먯삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕 �뜝�럥六삣뜝�럩�굚
	public ArrayList<CommentDto> commentListName(String nickName){
		ArrayList<CommentDto> commentListName = new ArrayList<CommentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM comment WHERE nickName=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				CommentDto cdto = new CommentDto();
				cdto.setContent(rs.getString("content"));
				cdto.setDate(rs.getTimestamp("date"));
				cdto.setNickName(rs.getString("nickName"));
				cdto.setNum(rs.getInt("num"));
				cdto.setcNum(rs.getInt("cNum"));
				
				commentListName.add(cdto);
				}
			
		}catch(Exception e){
			System.out.println("CommentListName嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return commentListName;
	}
	// 占쎈솊疫뀐옙 占쎄맒占쎄쉭癰귣떯由�
	public CommentDto getOneComment(int cNum){
		CommentDto cdto = new CommentDto();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		System.out.println("筌롫뗄�꺖占쎈굡占쎌뿯�뤃占�");
		try{
			con = getCon();
			String sql = "SELECT * FROM commnet WHERE cNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, cNum);
			
			rs = pstmt.executeQuery();
			System.out.println("db占쎈염野껉퀡留�");
			if(rs.next()){
				System.out.println("if占쎈굶占쎈선占쎌긾");
				
			}
			
		}catch(Exception e){
			System.out.println("getOneMember筌롫뗄�꺖占쎈굡 占쎄땀�겫占쏙옙肉됵옙苑� 占쎌궎�몴占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return cdto;
	}
	
	//�뜝�럡���뤆�룊�삕 �뜝�럩�굚�뜝�럡�뎽�뜝�럥由� �뜝�럥�냺�뼨�먯삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕 �뜝�럡援�
	
	/*�뜝�럥�냺�뼨�먯삕�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�*/
	
	//1:1占쎈닱筌뤾쑴踰ε뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 (�뜝�럥堉쀧솻洹⑥삕�뜝�럥�닱�뜝�럥堉롥뜝�럥�닡繞벿우삕) 占쎈뎨占쎈봾裕욃뜝�럥諭쒙옙援�占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<questionDto> QuestionList() {
		ArrayList<questionDto> questionList = new ArrayList<questionDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM question WHERE questionStatement=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setBoolean(1, false);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				questionDto qdto = new questionDto();
				
				qdto.setNum(rs.getInt("num"));
				qdto.setTitle(rs.getString("title"));
				qdto.setNickName(rs.getString("nickName"));
				qdto.setInputDate(rs.getTimestamp("inputDate"));
				qdto.setContent(rs.getString("content"));
		
				questionList.add(qdto);
			}
		}catch(Exception e){
			System.out.println("QuestionList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 (�뜝�럥堉쀧솻洹⑥삕�뜝�럥�닱�뜝�럥堉롥뜝�럥�닡繞벿우삕) 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 (�뜝�럥堉쀧솻洹⑥삕�뜝�럥堉롥뜝�럥�닡繞벿우삕) 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<questionDto> CompleteQuestionList() {
		ArrayList<questionDto> questionList = new ArrayList<questionDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM question WHERE questionStatement=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setBoolean(1, true);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				questionDto qdto = new questionDto();
				
				qdto.setNum(rs.getInt("num"));
				qdto.setTitle(rs.getString("title"));
				qdto.setNickName(rs.getString("nickName"));
				qdto.setInputDate(rs.getTimestamp("inputDate"));
				qdto.setContent(rs.getString("content"));
		
				questionList.add(qdto);
			}
		}catch(Exception e){
			System.out.println("QuestionList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	public ArrayList<questionDto> CompleteQuestionList(String nickName) {
		ArrayList<questionDto> questionList = new ArrayList<questionDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM question WHERE questionStatement=? and nickName=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setBoolean(1, true);
			pstmt.setString(2, nickName);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				questionDto qdto = new questionDto();
				
				qdto.setNum(rs.getInt("num"));
				qdto.setTitle(rs.getString("title"));
				qdto.setNickName(rs.getString("nickName"));
				qdto.setInputDate(rs.getTimestamp("inputDate"));
				qdto.setContent(rs.getString("content"));
		
				questionList.add(qdto);
			}
		}catch(Exception e){
			System.out.println("QuestionList筌롫뗄�꺖占쎈굡 占쎄땀�겫占쏙옙肉됵옙苑� 占쎌궎�몴占� : "+e);
		}finally{
			if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
			if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
			if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
		}
		
		return questionList;
	}
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 (�뜝�럥堉쀧솻洹⑥삕�뜝�럥堉롥뜝�럥�닡繞벿우삕) 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럩�굚�뜝�럡�뎽�뜝�럥由� �뤆�룇裕꾥�뚯궪�삕占쎈데�뜝�럩踰� 1:1占쎈닱筌뤾쑴踰η뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<questionDto> QuestionList(String nickName) {
		ArrayList<questionDto> questionList = new ArrayList<questionDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM question WHERE nickName=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, nickName);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				questionDto qdto = new questionDto();
				
				qdto.setNum(rs.getInt("num"));
				qdto.setTitle(rs.getString("title"));
				qdto.setNickName(rs.getString("nickName"));
				qdto.setInputDate(rs.getTimestamp("inputdate"));
				qdto.setContent(rs.getString("content"));
		
				questionList.add(qdto);
			}
		}catch(Exception e){
			System.out.println("QuestionList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//�뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럩�굚�뜝�럡�뎽�뜝�럥由� �뤆�룇裕꾥�뚯궪�삕占쎈데�뜝�럩踰� 1:1占쎈닱筌뤾쑴踰η뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 �뜝�럥堉쀧솻洹⑥삕�뼨�먯삕 �뜝�럥堉롥뜝�럥�닡�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void UpdateQuestion(int num, String reContent){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "UPDATE question SET reContent=?, questionStatement=? WHERE num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, reContent);
			pstmt.setBoolean(2, true);
			pstmt.setInt(3, num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("UpdateQuestion嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 �뜝�럥堉쀧솻洹⑥삕�뼨�먯삕 �뜝�럥堉롥뜝�럥�닡�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥由��뜝�럡�룎�뜝�럩踰� 1:1占쎈닱筌뤾쑴踰η뼨�먯삕 �솻洹ｏ옙占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public questionDto getOneQuestion(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		questionDto qdto = new questionDto();
		try{
			con = getCon();
			
			String sql = "SELECT * FROM question WHERE num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				qdto.setNum(rs.getInt(1));
				qdto.setTitle(rs.getString(2));
				qdto.setNickName(rs.getString(3));
				qdto.setInputDate(rs.getTimestamp(4));
				qdto.setQuestionStatement(rs.getBoolean(5));
				qdto.setContent(rs.getString(6));
				qdto.setImage(rs.getString(7));
				qdto.setReContent(rs.getString(8));
			}
		}catch(Exception e){
			System.out.println("getOneQuestion嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return qdto;
	}
	//�뜝�럥由��뜝�럡�룎�뜝�럩踰� 1:1占쎈닱筌뤾쑴踰η뼨�먯삕 �솻洹ｏ옙占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	//1:1占쎈닱筌뤾쑴踰ε뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮�냵�삕�굢占� 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭쒙옙援�占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<noticeDto> noticeList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<noticeDto> nList = new ArrayList<noticeDto>();
		try{
			con = getCon();
			
			String sql = "SELECT * FROM notice";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				noticeDto ndto = new noticeDto();
				ndto.setNoticeContent(rs.getString("noticeContent"));
				ndto.setNoticeDate(rs.getTimestamp("noticeDate"));
				ndto.setNoticeImg(rs.getString("noticeImg"));
				ndto.setNoticeNum(rs.getInt("noticeNum"));
				ndto.setNoticeSubject(rs.getString("noticeSubject"));
				nList.add(ndto);
			}
		}catch(Exception e){
			System.out.println("noticeList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : " + e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return nList;
	}
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭쒙옙援�占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뜝�럥苡삣슖�댙�삕 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void insertNotice(noticeDto ndto){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "INSERT INTO notice(noticeSubject, noticeContent, noticeDate, noticeImg) VALUES(?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, ndto.getNoticeSubject());
			pstmt.setString(2, ndto.getNoticeContent());
			pstmt.setTimestamp(3, ndto.getNoticeDate());
			pstmt.setString(4, ndto.getNoticeImg());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("insertNotice 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뜝�럥苡삣슖�댙�삕 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥由��뜝�럡�룎�뜝�럩踰� 占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public noticeDto getOneNotice(int noticeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		noticeDto ndto = new noticeDto();
		try{
			con = getCon();
			
			String sql = "SELECT * FROM notice WHERE noticeNum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				ndto.setNoticeContent(rs.getString("noticeContent"));
				ndto.setNoticeDate(rs.getTimestamp("noticeDate"));
				ndto.setNoticeImg(rs.getString("noticeImg"));
				ndto.setNoticeNum(rs.getInt("noticeNum"));
				ndto.setNoticeSubject(rs.getString("noticeSubject"));
			}
			
		}catch(Exception e){
			System.out.println("getOneNotice嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return ndto;
	}
	//�뜝�럥由��뜝�럡�룎�뜝�럩踰� 占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뜝�럡�뀭�뜝�럩�젷嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void deleteNotice(int noticeNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "delete from notice where noticeNum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, noticeNum);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("deleteNotice嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뜝�럡�뀭�뜝�럩�젷嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뜝�럥�빢�뜝�럩�젧嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void UpdateNotice(noticeDto ndto) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "UPDATE notice SET noticeDate=?, noticeContent=?, noticeSubject=? WHERE noticeNum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setTimestamp(1, ndto.getNoticeDate());
			pstmt.setString(2, ndto.getNoticeContent());
			pstmt.setString(3, ndto.getNoticeSubject());
			pstmt.setInt(4, ndto.getNoticeNum());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("UpdateNotice嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뜝�럥�빢�뜝�럩�젧嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮�냵�삕�굢占� 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	//FAQ�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	//FAQ �뼨�먯삕 嶺뚮ㅄ維뽨빳占� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<InformationUseDto> informationUseList(){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<InformationUseDto> list = new ArrayList<InformationUseDto>();
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM informationUse";
			
			pstmt = con.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InformationUseDto idto = new InformationUseDto();
				idto.setInfoContent(rs.getString("infoContent"));
				idto.setInfoImage(rs.getString("infoImage"));
				idto.setInfoNum(rs.getInt("infoNum"));
				idto.setInfoSubject(rs.getString("infoSubject"));
				idto.setInfoTitle(rs.getString("infoTitle"));
				list.add(idto);
			}
			
		}catch(Exception e){
			System.out.println("informationUseList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return list;
	}
	
	//FAQ �뼨�먯삕 嶺뚮ㅄ維뽨빳占� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� 占쎈쐻占쎈４
	//FAQ �뼨�먯삕 �뜝�럩沅욑옙逾놂옙�맧裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
		public void InsertInformation(InformationUseDto iudto){
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try{
				con = getCon();
				
				String sql = "INSERT INTO informationuse(infoSubject,infoTitle,infoContent,infoImage) VALUES(?,?,?,?)";
				
				pstmt = con.prepareStatement(sql);
				pstmt.setString(1, iudto.getInfoSubject());
				pstmt.setString(2, iudto.getInfoTitle());
				pstmt.setString(3, iudto.getInfoContent());
				pstmt.setString(4, iudto.getInfoImage());
				
				pstmt.executeUpdate();
				
			}catch(Exception e){
				System.out.println("InsertInformation嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
			}finally{
				freeRes(con, pstmt);
			}
		}
		//FAQ �뼨�먯삕 �뜝�럩沅욑옙逾놂옙�맧裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
		
	//FAQ �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public InformationUseDto getOneInfo(int infoNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		InformationUseDto idto = new InformationUseDto();
		try{
			con = getCon();
			
			String sql = "SELECT * FROM informationUse WHERE infoNum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, infoNum);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				idto.setInfoContent(rs.getString("infoContent"));
				idto.setInfoImage(rs.getString("infoImage"));
				idto.setInfoNum(rs.getInt("infoNum"));
				idto.setInfoSubject(rs.getString("infoSubject"));
				idto.setInfoTitle(rs.getString("infoTitle"));
			}
			
		}catch(Exception e){
			System.out.println("getOneInfo 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return idto;
	}
	//FAQ �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//FAQ �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뜝�럥�빢�뜝�럩�젧 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void UpdateInfo(InformationUseDto iudto){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "UPDATE informationUse SET infoSubject=?, infoTitle=?, infoContent=? WHERE infoNum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, iudto.getInfoSubject());
			pstmt.setString(2, iudto.getInfoTitle());
			pstmt.setString(3, iudto.getInfoContent());
			pstmt.setInt(4, iudto.getInfoNum());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("UpdateInfo嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	//FAQ �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뜝�럥�빢�뜝�럩�젧 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//FAQ �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뜝�럡�뀭�뜝�럩�젷 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void deleteInfo(int infoNum) {
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "DELETE FROM informationUse WHERE infoNum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, infoNum);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("deleteInfo 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//FAQ �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뜝�럡�뀭�뜝�럩�젷 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	//FAQ�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럩逾뺡쨹恝�삕_ �뜝�럥�뼫�뜝�럩�겱�뜝�럩�뭵嶺뚳퐦�삕 �뜝�럥�빢�뜝�럩逾��뜝�럥�뿰占쎄껀�뜝占� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	//占쎈뎨占쎈뿪�돇�뤆�룊�삕 �뜝�럥�닡�뜝�럩�뭵 �뜝�럥�뵪�뜝�럩逾� DB�뜝�럥�닱�뜝�럩踰� investRequest�뜝�럥占쎈���삕占쎈턄占쎈눀�겫�슦踰� permitChk�뤆�룊�삕 true �솻洹⑥삕�뜝�럩�꼶
	public void  AdminPermitCheck(String IRnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "update investRequest set permitChk=true where investRequestNum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, IRnum);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("AdminPermitCheck嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally {
			freeRes(con, pstmt);
		}
		
	}//占쎌뵕餓ο옙_ 占쎈떮占쎌쁽占쎌뒄筌ｏ옙 占쎈땾占쎌뵭占쎈연�겫占� 筌롫뗄�꺖占쎈굡 占쎄국
	//占쎌뵕餓ο옙_ 占쎈떮占쎌쁽占쎌뒄筌ｏ옙 占쎈땾占쎌뵭占쎈연�겫占� 筌롫뗄�꺖占쎈굡 占쎈뻻占쎌삂
	//�뵳�뗪쉘揶쏉옙 占쎈툡占쎌뒄 占쎈씨占쎌뵠 DB占쎈툧占쎌벥 investRequest占쎈�믭옙�뵠�뇡遺우벥 updateChk= true 癰귨옙占쎌넎
		public void  AdminUpdateCheck(String IRnum){
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try{
				con = getCon();
				
				String sql = "update investRequest set updateChk=true where investRequestNum=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, IRnum);
				
				pstmt.executeUpdate();
				
			}catch(Exception e){
				System.out.println("AdminPermitCheck嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}//占쎌뵕餓ο옙_ 占쎈떮占쎌쁽占쎌뒄筌ｏ옙 占쎈땾占쎌뵭占쎈연�겫占� 筌롫뗄�꺖占쎈굡 占쎄국
		
		// 揶쏆뮇�뵥 占쎈떮占쎌쁽占쎌뒄筌ｏ옙 占쎄땀占쎈열 �겫�뜄�쑎占쎌궎占쎈뮉 筌롫뗄�꺖占쎈굡 占쎈뻻占쎌삂
				public ArrayList<InvestRequestDto> InvestRequestListPro(String memberEmail){
					ArrayList<InvestRequestDto> InvestRequestList = new ArrayList<InvestRequestDto>();
					
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					try{
						con = getCon();
						String sql = "SELECT * FROM investrequest WHERE memberEmail=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setString(1, memberEmail);
						rs = pstmt.executeQuery();
						while(rs.next()){
							InvestRequestDto irdto = new InvestRequestDto();
							irdto.setCategory(rs.getString("category"));
							irdto.setDName(rs.getString("dName"));
							irdto.setEndDay(rs.getTimestamp("endDay"));
							irdto.setFile(rs.getString("file"));
							irdto.setFundSituation(rs.getInt("fundSituation"));
							irdto.setHashTag(rs.getString("hashtag"));
							irdto.setIntroduce(rs.getString("introduce"));
							irdto.setInvestRequestNum(rs.getInt("investRequestNum"));
							irdto.setMainImage(rs.getString("mainImage"));
							irdto.setMainText(rs.getString("mainText"));
							irdto.setMainThumb(rs.getString("mainThumb"));
							irdto.setMemberEmail(rs.getString("memberEmail"));
							irdto.setNowMoney(rs.getInt("nowMoney"));
							irdto.setPayDay(rs.getTimestamp("payDay"));
							irdto.setPermitChk(rs.getBoolean("permitChk"));
							irdto.setProgram(rs.getString("program"));
							irdto.setStartDay(rs.getTimestamp("startDay"));
							irdto.setSuccessMoney(rs.getInt("successMoney"));
							irdto.setThumbImage(rs.getString("thumbImage"));
							irdto.setUpdateChk(rs.getBoolean("updateChk"));
							System.out.println(rs.getString("category"));
							System.out.println(rs.getString("mainImage"));
							System.out.println(rs.getString("mainText"));
							System.out.println(rs.getString("mainThumb"));
							System.out.println(rs.getString("thumbImage"));
							
							InvestRequestList.add(irdto);
						}			
					}catch(Exception e){
						System.out.println("InvestRequestList筌롫뗄�꺖占쎈굡 占쎄땀�겫占쏙옙肉됵옙苑� 占쎌궎�몴占� : "+e);
					}finally{
						freeRes(con, pstmt, rs);
					}			
					return InvestRequestList;
				}
				
				// 揶쏆뮇�뵥 占쎈떮占쎌쁽占쎌뒄筌ｏ옙 占쎄땀占쎈열 �겫�뜄�쑎占쎌궎占쎈뮉 筌롫뗄�꺖占쎈굡 占쎄국		
				
				public InvestRequestDto getOneInvestRequest(int investRequestNum){
					InvestRequestDto irdto = new InvestRequestDto();
					Connection con = null;
					PreparedStatement pstmt = null;
					ResultSet rs = null;
					System.out.println("筌롫뗄�꺖占쎈굡占쎌뿯�뤃占�");
					try{
						con = getCon();
						String sql = "SELECT * FROM investRequest WHERE investRequestNum=?";
						pstmt = con.prepareStatement(sql);
						pstmt.setInt(1, investRequestNum);
						
						rs = pstmt.executeQuery();
						System.out.println("db占쎈염野껉퀡留�");
						if(rs.next()){
							System.out.println("if占쎈굶占쎈선占쎌긾");
							irdto.setCategory(rs.getString("category"));
							irdto.setDName(rs.getString("dName"));
							irdto.setEndDay(rs.getTimestamp("endDay"));
							irdto.setFile(rs.getString("file"));
							irdto.setFundSituation(rs.getInt("fundSituation"));
							irdto.setHashTag(rs.getString("hashtag"));
							irdto.setIntroduce(rs.getString("introduce"));
							irdto.setInvestRequestNum(rs.getInt("investRequestNum"));
							irdto.setMainImage(rs.getString("mainImage"));
							irdto.setMainText(rs.getString("mainText"));
							irdto.setMainThumb(rs.getString("mainThumb"));
							irdto.setMemberEmail(rs.getString("memberEmail"));
							irdto.setNowMoney(rs.getInt("nowMoney"));
							irdto.setPayDay(rs.getTimestamp("payDay"));
							irdto.setPermitChk(rs.getBoolean("permitChk"));
							irdto.setProgram(rs.getString("program"));
							irdto.setStartDay(rs.getTimestamp("startDay"));
							irdto.setSuccessMoney(rs.getInt("successMoney"));
							irdto.setThumbImage(rs.getString("thumbImage"));
							irdto.setUpdateChk(rs.getBoolean("updateChk"));
							System.out.println(rs.getString("category"));
							System.out.println(rs.getString("dName"));
							System.out.println(rs.getTimestamp("endDay"));
							System.out.println(rs.getString("file"));
							System.out.println(rs.getInt("fundSituation"));
							System.out.println(rs.getString("hashtag"));
							System.out.println(rs.getString("introduce"));
							System.out.println(rs.getInt("investRequestNum"));
							System.out.println(rs.getString("mainImage"));
							System.out.println(rs.getString("mainThumb"));
							System.out.println(rs.getString("memberEmail"));
							System.out.println(rs.getInt("nowMoney"));
							System.out.println(rs.getTimestamp("payDay"));
							System.out.println(rs.getBoolean("permitChk"));
							System.out.println(rs.getString("program"));
							System.out.println(rs.getTimestamp("startDay"));
							System.out.println(rs.getInt("successMoney"));
							System.out.println(rs.getString("thumbImage"));
							System.out.println(rs.getBoolean("updateChk"));
							
						}
						
					}catch(Exception e){
						System.out.println("getOneMember筌롫뗄�꺖占쎈굡 占쎄땀�겫占쏙옙肉됵옙苑� 占쎌궎�몴占� : "+e);
					}finally{
						freeRes(con, pstmt, rs);
					}
					return irdto;
				}	
}



/*package net.Admin.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;


import net.InformationUse.db.InformationUseDto;

import net.Investment.db.InvestmentDto;
import net.board.db.BoardDto;
import net.investRequest.db.InvestRequestDto;
import net.member.db.MemberDTO;
import net.question.db.questionDto;
import net.reply.db.ReplyDto;


public class AdminDao {

	private Connection getCon() throws Exception{

		Connection con = null;
		
		Context init = new InitialContext();
		
		DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
		
		con = ds.getConnection();
		
		return con;
	}
	//�뜝�럩�겱�뜝�럩�쐸�뜝�럥�돵�뜝�럩�젷 嶺뚮∥�뾼占쎄틬�뜝�럥援�
	public void freeRes(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
	}
	public void freeRes(Connection con, PreparedStatement pstmt){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
	}//�뜝�럩�겱�뜝�럩�쐸�뜝�럥�돵�뜝�럩�젷 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럩�뭵嶺뚳퐦�삕 �뜝�럩寃긷뜝�럩�꽯�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럩�뭵嶺뚳퐦�삕 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	//�뜝�럩逾뺡쨹恝�삕 �뜝�럥�뼫�뜝�럩�겱 嶺뚯쉳�뫓筌묕옙 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	//�뜝�럩沅롧뵓怨뚯뫊餓λ뜉�삕�얇꺈�삕占쎈さ�슖�댙�삕 占쎄껀占쎈쐞占쎈뉴�뜝�럥�꽘 �뤆�룆占썬굦逾� �뜝�럩肉녑뜝�럩紐든춯濡녹삕 �뜝�럥�뿰�뼨轅명�▽빳占� �뜝�룞�삕�뜝�럡�맋 嶺뚯쉳�뫓筌묕옙
	//�윜諛몄굡繹먲옙 占쎈쨨占쎈맪�뗧춯�쉶���뵳占� �뜝�럩留꾢뜝�럥�돵 �뜝�럥由��뜝�럡�룎 �뜝�럥�맠 嶺뚮씭�뒧獄�酉댁퀪�뜝占�
		public ArrayList<InvestRequestDto> InvestRequestList(boolean permitChk){
			ArrayList<InvestRequestDto> InvestRequestList = new ArrayList<InvestRequestDto>();
			
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			
			try{
				con = getCon();
				String sql="";
				
				 sql= "SELECT * FROM investrequest WHERE permitChk=?";	
				
				pstmt = con.prepareStatement(sql);
				pstmt.setBoolean(1, true);
				
				rs = pstmt.executeQuery();
				while(rs.next()){
					InvestRequestDto irdto = new InvestRequestDto();
					irdto.setCategory(rs.getString("category"));
					irdto.setDName(rs.getString("dName"));
					irdto.setEndDay(rs.getTimestamp("endDay"));
					irdto.setFile(rs.getString("file"));
					irdto.setFundSituation(rs.getInt("fundSituation"));
					irdto.setHashTag(rs.getString("hashTag"));
					irdto.setIntroduce(rs.getString("introduce"));
					irdto.setInvestRequestNum(rs.getInt("investRequestNum"));
					irdto.setMainImage(rs.getString("mainImage"));
					irdto.setMainText(rs.getString("mainText"));
					irdto.setMainThumb(rs.getString("mainThumb"));
					irdto.setMemberEmail(rs.getString("memberEmail"));
					irdto.setNowMoney(rs.getInt("nowMoney"));
					irdto.setPayDay(rs.getTimestamp("payDay"));
					irdto.setPermitChk(rs.getBoolean("permitChk"));
					irdto.setProgram(rs.getString("program"));
					irdto.setStartDay(rs.getTimestamp("startDay"));
					irdto.setSuccessMoney(rs.getInt("successMoney"));
					irdto.setThumbImage(rs.getString("thumbImage"));
					irdto.setUpdateChk(rs.getBoolean("updateChk"));
					InvestRequestList.add(irdto);
				}			
			}catch(Exception e){
				System.out.println("InvestRequestList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
			}finally{
				freeRes(con, pstmt, rs);
			}
			
			return InvestRequestList;
		}
		//�뜝�럩逾뺡쨹恝�삕 �뜝�럥�뼫�뜝�럩�겱 嶺뚯쉳�뫓筌묕옙 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럩�뭵嶺뚳퐦�삕 �뜝�럩寃긷뜝�럩�꽯�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	//�뜝�럥�뼫�뜝�럩�겱�뜝�럩�겱�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럩�뭵嶺뚳퐦�삕�뼨�먯삕�뜝�럥�뱺�뜝�룞�삕�뜝�럥由� �뜝�럥�뼫�뜝�럩�겱�뜝�럩�겱�뜝�럥援뜹뜝�럩諭� 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<InvestmentDto> InvestMentList(int investRequestNum){
		ArrayList<InvestmentDto> InvestMentList = new ArrayList<InvestmentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM investment where investRequestNum=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, investRequestNum);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InvestmentDto idto = new InvestmentDto();
				
				idto.setInvestNum(rs.getInt("InvestNum"));
				idto.setInvestRequestNum(rs.getInt("investRequestNum"));
				idto.setInvestMoney(rs.getInt("InvestMoney"));
				idto.setMemberEmail(rs.getString("memberEmail"));
				idto.setInvestName(rs.getString("InvestName"));
				idto.setProgram(rs.getString("program"));
				
				InvestMentList.add(idto);
			}
		}catch(Exception e){
			System.out.println("InvestMentList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占�  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	//�뜝�럥�뼫�뜝�럩�겱 �뜝�럩�뭵嶺뚳퐦�삕�뼨�먯삕�뜝�럥�뱺�뜝�룞�삕�뜝�럥由� �뜝�럥�뼫�뜝�럩�겱�뜝�럩�겱�뜝�럥援뜹뜝�럩諭� 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럥�뼫�뜝�럩�겱�뜝�럥由� �뜝�럡���뜝�럥�뿴 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<InvestmentDto> InvestMentList(String memberEmail){
		ArrayList<InvestmentDto> InvestMentList = new ArrayList<InvestmentDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM investment where memberEmail=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				InvestmentDto idto = new InvestmentDto();
				
				idto.setInvestNum(rs.getInt("InvestNum"));
				idto.setInvestRequestNum(rs.getInt("investRequestNum"));
				idto.setInvestMoney(rs.getInt("InvestMoney"));
				idto.setMemberEmail(rs.getString("memberEmail"));
				idto.setInvestName(rs.getString("InvestName"));
				idto.setProgram(rs.getString("program"));
				
				InvestMentList.add(idto);
			}
		}catch(Exception e){
			System.out.println("InvestMentList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占�  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	//�뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럥�뼫�뜝�럩�겱�뜝�럥由� �뜝�럡���뜝�럥�뿴 占쎄껀占쎈쐞占쎌몠�뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥�뼫�뜝�럩�겱�뜝�럩�겱�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	嶺뚮、�걞�떋占썲뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	//�뜝�럩�뤂�뜝�럩�쐸 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<MemberDTO> MemberList(){
		ArrayList<MemberDTO> MemberList = new ArrayList<MemberDTO>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql = "SELECT * FROM member";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				MemberDTO mdto = new MemberDTO();
				mdto.setBirth(rs.getString("birth"));
				mdto.setMemberEmail(rs.getString("memberemail"));
				mdto.setName(rs.getString("name"));
				mdto.setNickName(rs.getString("nickname"));
				mdto.setPass(rs.getString("pass"));
				mdto.setPhone(rs.getString("phone"));
				
				MemberList.add(mdto);
			}
			
		}catch(Exception e){
			System.out.println("MemberList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}

		return MemberList;
	}
	//�뜝�럩�뤂�뜝�럩�쐸 占쎈뎨占쎈봾裕욃뜝�럥諭� �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럩�뤂�뜝�럩�쐸 �뜝�럥由썹춯琉욧턂占쎈꺄 �뜝�럩�젧�솻洹⑥삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public MemberDTO getOneMember(String memberEmail){
		MemberDTO mdto = new MemberDTO();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql = "SELECT * FROM member WHERE memberEmail=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, memberEmail);
			rs = pstmt.executeQuery();
			if(rs.next()){
				mdto.setBirth(rs.getString("birth"));
				mdto.setMemberEmail(rs.getString("memberEmail"));
				mdto.setName(rs.getString("name"));
				mdto.setNickName(rs.getString("nickName"));
				mdto.setPass(rs.getString("pass"));
				mdto.setPhone(rs.getString("phone"));
			}
			
		}catch(Exception e){
			System.out.println("getOneMember嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return mdto;
	}
	//�뜝�럩�뤂�뜝�럩�쐸 �뜝�럥由썹춯琉욧턂占쎈꺄 �뜝�럩�젧�솻洹⑥삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롥뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럩�뤂�뜝�럩�쐸 �뤆�룆踰∽옙�젷 �뜝�럡�돮�뜝�럥�떄�뜝�럥六삣뜝�럡�뀞�뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void DeleteMember(String nickname){
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try{
			con = getCon();
			String sql = "DELETE FROM member WHERE nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("DeleteMember嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//�뜝�럩�뤂�뜝�럩�쐸 �뤆�룆踰∽옙�젷 �뜝�럡�돮�뜝�럥�떄�뜝�럥六삣뜝�럡�뀞�뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	嶺뚮、�걞�떋占� 占쎄슈�뜝�룞�삕占쎌죨 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	�뇦猿딆뒩占쎈뻣�뜝�럥�냷�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<BoardDto> BoardList(){
		ArrayList<BoardDto> BoardList = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM board";
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDto bdto = new BoardDto();
				
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getTimestamp("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setNickName(rs.getString("nickName"));
				bdto.setNum(rs.getInt("num"));
				bdto.setTitle(rs.getString("title"));
				
				BoardList.add(bdto);
			}
			
		}catch(Exception e){
			System.out.println("BoardList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return BoardList;
	}
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥由썹춯琉욧턂占쎈꺄 �뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럥荑� �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<BoardDto> BoardList(String nickName){
		ArrayList<BoardDto> BoardList = new ArrayList<BoardDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM board where nickName=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			rs = pstmt.executeQuery();
			while(rs.next()){
				BoardDto bdto = new BoardDto();
				
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getTimestamp("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setNickName(rs.getString("nickName"));
				bdto.setNum(rs.getInt("num"));
				bdto.setTitle(rs.getString("title"));
				
				BoardList.add(bdto);
			}
			
		}catch(Exception e){
			System.out.println("BoardList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return BoardList;
	}
	//�뜝�럥由썹춯琉욧턂占쎈꺄 �뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럥荑� �뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public BoardDto getOneBoard(int num){
		BoardDto bdto = new BoardDto();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			String sql = "SELECT * FROM board WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			if(rs.next()){
				bdto.setContent(rs.getString("content"));
				bdto.setDate(rs.getTimestamp("date"));
				bdto.setFile(rs.getString("file"));
				bdto.setNickName(rs.getString("nickName"));
				bdto.setNum(rs.getInt("num"));
				bdto.setTitle(rs.getString("title"));
			}
		}catch(Exception e){
			System.out.println("getOneBoard嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return bdto;
	}
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뜝�럡�뀭�뜝�럩�젷�뜝�럥六삣뜝�럡�뀞�뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public int DeleteBoard(int num){
		
		Connection con = null;
		PreparedStatement pstmt = null;
		int check = 0;
		try{
			con = getCon();
			
			String sql = "DELETE FROM board WHERE num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			check = pstmt.executeUpdate();
			System.out.println(check);
		}catch(Exception e){
			System.out.println("DeleteBoard嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		return check;
	}
	//�뇦猿딆뒩占쎈뻣�뜝�럥�냷 �뜝�럥由��뜝�럡�룎�뜝�럩踰� �뼨�먯삕 �뜝�럡�뀭�뜝�럩�젷�뜝�럥六삣뜝�럡�뀞�뜝�럥裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	�뇦猿딆뒩占쎈뻣�뜝�럥�냷�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	�뜝�럥�냺�뼨�먯삕�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	//�뜝�럥�냺�뼨�먯삕 �뼨�먯삕�뵓怨뺣쐡占쎄퉰占쎄슈�뜝�룞�삕占쎌죨�뜝�럥�돵�뜝�럡�맋 占쎄덩占쎌뒧占쎌졎�썒�슣�닁�뵳占� �뜝�럥六삣뜝�럩�굚
	public ArrayList<ReplyDto> replyListNum(int num){
		ArrayList<ReplyDto> replyListNum = new ArrayList<ReplyDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM reply WHERE num=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, num);
			rs = pstmt.executeQuery();
			while(rs.next()){
				ReplyDto rdto = new ReplyDto();
				rdto.setContent(rs.getString("content"));
				rdto.setDate(rs.getTimestamp("date"));
				rdto.setNickName(rs.getString("nickName"));
				rdto.setNum(rs.getInt("num"));
				rdto.setrNum(rs.getInt("rNum"));
				
				replyListNum.add(rdto);
			}
		}catch(Exception e){
			System.out.println("replyListNum嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return replyListNum;
	}
	//�뜝�럥�냺�뼨�먯삕 �뼨�먯삕�뵓怨뺣쐡占쎄퉰 占쎄슈�뜝�룞�삕占쎌죨�뜝�럥�돵�뜝�럡�맋 占쎄덩占쎌뒧占쎌졎�썒�슣�닁�뵳占� �뜝�럡援�
	
	//�뜝�럡���뤆�룊�삕 �뜝�럩�굚�뜝�럡�뎽�뜝�럥由� �뜝�럥�냺�뼨�먯삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕 �뜝�럥六삣뜝�럩�굚
	public ArrayList<ReplyDto> replyListName(String nickName){
		ArrayList<ReplyDto> replyListName = new ArrayList<ReplyDto>();
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM reply WHERE nickName=?";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, nickName);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				ReplyDto rdto = new ReplyDto();
				rdto.setContent(rs.getString("content"));
				rdto.setDate(rs.getTimestamp("date"));
				rdto.setNickName(rs.getString("nickName"));
				rdto.setNum(rs.getInt("num"));
				rdto.setrNum(rs.getInt("rNum"));
				
				replyListName.add(rdto);
				}
			
		}catch(Exception e){
			System.out.println("replyListName嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return replyListName;
	}
	//�뜝�럡���뤆�룊�삕 �뜝�럩�굚�뜝�럡�뎽�뜝�럥由� �뜝�럥�냺�뼨�먯삕 �뤆�룊�삕�뜝�럩二у뜝�럩沅롧뼨�먯삕 �뜝�럡援�
	
	�뜝�럥�냺�뼨�먯삕�뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	//1:1占쎈닱筌뤾쑴踰ε뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 (�뜝�럥堉쀧솻洹⑥삕�뜝�럥�닱�뜝�럥堉롥뜝�럥�닡繞벿우삕) 占쎈뎨占쎈봾裕욃뜝�럥諭쒙옙援�占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<questionDto> QuestionList() {
		ArrayList<questionDto> questionList = new ArrayList<questionDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM question WHERE questionStatement=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setBoolean(1, false);
			
			rs = pstmt.executeQuery();
			
			while(rs.next()){
				questionDto qdto = new questionDto();
				
				qdto.setNum(rs.getInt("num"));
				qdto.setTitle(rs.getString("title"));
				qdto.setNickName(rs.getString("nickName"));
				qdto.setInputDate(rs.getTimestamp("inputDate"));
				qdto.setContent(rs.getString("content"));
		
				questionList.add(qdto);
			}
		}catch(Exception e){
			System.out.println("QuestionList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럩�굚�뜝�럡�뎽�뜝�럥由� �뤆�룇裕꾥�뚯궪�삕占쎈데�뜝�럩踰� 1:1占쎈닱筌뤾쑴踰η뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public ArrayList<questionDto> QuestionList(String nickName) {
		ArrayList<questionDto> questionList = new ArrayList<questionDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try{
			con = getCon();
			
			String sql = "SELECT * FROM question WHERE nickName=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, nickName);

			rs = pstmt.executeQuery();
			
			while(rs.next()){
				questionDto qdto = new questionDto();
				
				qdto.setNum(rs.getInt("num"));
				qdto.setTitle(rs.getString("title"));
				qdto.setNickName(rs.getString("nickName"));
				qdto.setInputDate(rs.getTimestamp("inputdate"));
				qdto.setContent(rs.getString("content"));
		
				questionList.add(qdto);
			}
		}catch(Exception e){
			System.out.println("QuestionList嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//�뜝�럩�뤂�뜝�럩�쐸�뜝�럩逾� �뜝�럩�굚�뜝�럡�뎽�뜝�럥由� �뤆�룇裕꾥�뚯궪�삕占쎈데�뜝�럩踰� 1:1占쎈닱筌뤾쑴踰η뼨�먯삕 占쎈뎨占쎈봾裕욃뜝�럥諭� 占쎄덩占쎌뒧占쎌졎�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 �뜝�럥堉쀧솻洹⑥삕�뼨�먯삕 �뜝�럥堉롥뜝�럥�닡�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void UpdateQuestion(int num, String reContent){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "UPDATE question SET reContent=?, questionStatement=? WHERE num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, reContent);
			pstmt.setBoolean(2, true);
			pstmt.setInt(3, num);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("UpdateQuestion嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	//1:1占쎈닱筌뤾쑴踰η뼨�먯삕 �뜝�럥堉쀧솻洹⑥삕�뼨�먯삕 �뜝�럥堉롥뜝�럥�닡�썒�슣�닑占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//�뜝�럥由��뜝�럡�룎�뜝�럩踰� 1:1占쎈닱筌뤾쑴踰η뼨�먯삕 �솻洹ｏ옙占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public questionDto getOneQuestion(int num){
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		questionDto qdto = new questionDto();
		try{
			con = getCon();
			
			String sql = "SELECT * FROM question WHERE num=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setInt(1, num);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()){
				qdto.setNum(rs.getInt(1));
				qdto.setTitle(rs.getString(2));
				qdto.setNickName(rs.getString(3));
				qdto.setInputDate(rs.getTimestamp(4));
				qdto.setQuestionStatement(rs.getBoolean(5));
				qdto.setContent(rs.getString(6));
				qdto.setReContent(rs.getString(7));
			}
		}catch(Exception e){
			System.out.println("getOneQuestion嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return qdto;
	}
	//�뜝�럥由��뜝�럡�룎�뜝�럩踰� 1:1占쎈닱筌뤾쑴踰η뼨�먯삕 �솻洹ｏ옙占쎈츎 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	//1:1占쎈닱筌뤾쑴踰ε뜝�럥�뱺 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮�냵�삕�굢占� 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뜝�럩沅욑옙逾놂옙�맧裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	public void InsertInformation(InformationUseDto iudto){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "INSERT INTO informationuse(infoSubject,infoTitle,infoContent,infoImage) VALUES(?,?,?,?)";
			
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, iudto.getInfoSubject());
			pstmt.setString(2, iudto.getInfoTitle());
			pstmt.setString(3, iudto.getInfoContent());
			pstmt.setString(4, iudto.getInfoImage());
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("InsertInformation嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮占� �뼨�먯삕 �뜝�럩沅욑옙逾놂옙�맧裕� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	
	//占썩뫀踰듿뜝�룞�삕亦낆���삕�뜮�냵�삕�굢占� 占쎄슈�뜝�룞�삕�뇡占� 嶺뚮∥�뾼占쎄틬�뜝�럥援▼뜝�럥援� �뜝�럡援�
	
	//�뜝�럩逾뺡쨹恝�삕_ �뜝�럥�뼫�뜝�럩�겱�뜝�럩�뭵嶺뚳퐦�삕 �뜝�럥�빢�뜝�럩逾��뜝�럥�뿰占쎄껀�뜝占� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
	//占쎈뎨占쎈뿪�돇�뤆�룊�삕 �뜝�럥�닡�뜝�럩�뭵 �뜝�럥�뵪�뜝�럩逾� DB�뜝�럥�닱�뜝�럩踰� investRequest�뜝�럥占쎈���삕占쎈턄占쎈눀�겫�슦踰� permitChk�뤆�룊�삕 true �솻洹⑥삕�뜝�럩�꼶
	public void  AdminPermitCheck(String IRnum){
		Connection con = null;
		PreparedStatement pstmt = null;
		
		try{
			con = getCon();
			
			String sql = "update investRequest set permitChk=true where investRequestNum=?";
			
			pstmt = con.prepareStatement(sql);
			
			pstmt.setString(1, IRnum);
			
			pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("AdminPermitCheck嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
		}finally {
			freeRes(con, pstmt);
		}
		
	}//�뜝�럩逾뺡쨹恝�삕_ �뜝�럥�뼫�뜝�럩�겱�뜝�럩�뭵嶺뚳퐦�삕 �뜝�럥�빢�뜝�럩逾��뜝�럥�뿰占쎄껀�뜝占� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	//�뜝�럩逾뺡쨹恝�삕_ �뜝�럥�뼫�뜝�럩�겱�뜝�럩�뭵嶺뚳퐦�삕 �뜝�럥�빢�뜝�럩逾��뜝�럥�뿰占쎄껀�뜝占� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럥六삣뜝�럩�굚
		//占쎈뎨占쎈뿪�돇�뤆�룊�삕 �뜝�럥�닡�뜝�럩�뭵 �뜝�럥�뵪�뜝�럩逾� DB�뜝�럥�닱�뜝�럩踰� investRequest�뜝�럥占쎈���삕占쎈턄占쎈눀�겫�슦踰� updateChk= true �솻洹⑥삕�뜝�럩�꼶
		public void  AdminUpdateCheck(String IRnum){
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try{
				con = getCon();
				
				String sql = "update investRequest set updateChk=true where investRequestNum=?";
				
				pstmt = con.prepareStatement(sql);
				
				pstmt.setString(1, IRnum);
				
				pstmt.executeUpdate();
				
			}catch(Exception e){
				System.out.println("AdminPermitCheck嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡��占쎄껀�뜝�룞�삕�굢�맮�삕�땻占� �뜝�럩沅롳옙紐닷뜝占� : "+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}//�뜝�럩逾뺡쨹恝�삕_ �뜝�럥�뼫�뜝�럩�겱�뜝�럩�뭵嶺뚳퐦�삕 �뜝�럥�빢�뜝�럩逾��뜝�럥�뿰占쎄껀�뜝占� 嶺뚮∥�뾼占쎄틬�뜝�럥援� �뜝�럡援�
	
	
	
}
*/