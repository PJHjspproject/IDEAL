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
	
	//투자 요청 현황에 관한 메소드들 시작
	
	//투자 요청 리스트 가져오는 메소드 시작
	public ArrayList<InvestRequestDto> InvestRequestList(){
		ArrayList<InvestRequestDto> InvestRequestList = new ArrayList<InvestRequestDto>();
		
		Connection con = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try{
			con = getCon();
			String sql = "SELECT * FROM investrequest WHERE permitChk=? AND updateChk=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setBoolean(1, false);
			pstmt.setBoolean(2, false);
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
			System.out.println("InvestRequestList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return InvestRequestList;
	}
	//투자 요청 리스트 가져오는 메소드 끝
	
	//투자 요청 현황에 관한 메소드들 끝
	
	//투자자에 관한 메소드들 시작
	
	//투자 요청글에대한 투자자들을 불러오는 메소드 시작
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
			System.out.println("InvestMentList메소드 내부에서 오류  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	//투자 요청글에대한 투자자들을 불러오는 메소드 끝
	
	//회원이 투자한 내역 불러오는 메소드 시작
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
			System.out.println("InvestMentList메소드 내부에서 오류  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	//회원이 투자한 내역 불러오는 메소드 끝
	
	//투자자에 관한 메소드들 끝
	
	/*멤버에 관한 메소드들 시작*/
	
	//회원 리스트 가져오는 메소드 시작
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
			System.out.println("MemberList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}

		return MemberList;
	}
	//회원 리스트 가져오는 메소드 끝
		
	
	//희준 투자 진행 리스트 가져오는 메소드 시작
	//오버로딩으로 불리언 값이 있으면 여기로 와서 진행
	//그냥 구분짓기 위해 하나 더 만든거
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
			System.out.println("InvestRequestList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return InvestRequestList;
	}
	//희준 투자 진행 리스트 가져오는 메소드 끝
	//회원 한명의 정보 가져오는 메소드 시작
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
			System.out.println("getOneMember메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return mdto;
	}
	//회원 한명의 정보 가져오는 메소드 끝
	
	//회원 강제 탈퇴시키는 메소드 시작
	public void DeleteMember(String nickname){
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try{
			con = getCon();
			String sql = "DELETE FROM member WHERE nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("DeleteMember메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//회원 강제 탈퇴시키는 메소드 끝
	
	/*멤버 관련 메소드 끝*/
	
	/*게시판에 관한 메소드들 시작*/
	
	//게시판 글 리스트 뿌려주는 메소드 시작
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
			System.out.println("BoardList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return BoardList;
	}
	//게시판 글 리스트 뿌려주는 메소드 끝
	
	//한명의 회원이 쓴 글 리스트 뿌려주는 메소드 시작
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
			System.out.println("BoardList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return BoardList;
	}
	//한명의 회원이 쓴 글 리스트 뿌려주는 메소드 끝
	
	//게시판 하나의 글 뿌려주는 메소드 시작
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
			System.out.println("getOneBoard메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return bdto;
	}
	//게시판 하나의 글 뿌려주는 메소드 끝
	
	//게시판 하나의 글 삭제시키는 메소드 시작
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
			System.out.println("DeleteBoard메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		return check;
	}
	//게시판 하나의 글 삭제시키는 메소드 끝
	
	/*게시판에 관한 메소드들 끝*/
	
	/*댓글에 관한 메소드들 시작*/
	
	//댓글 글번호관련해서 뿌려주기 시작
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
			System.out.println("replyListNum메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return replyListNum;
	}
	//댓글 글번호 관련해서 뿌려주기 끝
	
	//내가 작성한 댓글 가져오기 시작
	public ArrayList<CommentDto> commentListName(String nickName){
		ArrayList<CommentDto> replyListName = new ArrayList<CommentDto>();
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
				CommentDto rdto = new CommentDto();
				rdto.setContent(rs.getString("content"));
				rdto.setDate(rs.getTimestamp("date"));
				rdto.setNickName(rs.getString("nickName"));
				rdto.setNum(rs.getInt("num"));
				rdto.setcNum(rs.getInt("cNum"));
				
				replyListName.add(rdto);
				}
			
		}catch(Exception e){
			System.out.println("CommentListName메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return replyListName;
	}
	//내가 작성한 댓글 가져오기 끝
	
	/*댓글에 관한 메소드들 끝*/
	
	//1:1문의에 관한 메소드들 시작
	//1:1문의글 (답변안달아준) 리스트뿌려주는 메소드 시작
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
			System.out.println("QuestionList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//1:1문의글 (답변안달아준) 리스트 뿌려주는 메소드 끝
	
	//1:1문의글 (답변달아준) 리스트 뿌려주는 메소드 시작
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
			System.out.println("QuestionList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//1:1문의글 (답변달아준) 리스트 뿌려주는 메소드 끝
	
	//회원이 작성한 개개인의 1:1문의글 리스트 뿌려주는 메소드 시작
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
			System.out.println("QuestionList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//회원이 작성한 개개인의 1:1문의글 리스트 뿌려주는 메소드 끝
	
	//1:1문의글 답변글 달아주는 메소드 시작
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
			System.out.println("UpdateQuestion메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	//1:1문의글 답변글 달아주는 메소드 끝
	
	//하나의 1:1문의글 보는 메소드 시작
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
			System.out.println("getOneQuestion메소드 내부에서 오류 : "+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return qdto;
	}
	//하나의 1:1문의글 보는 메소드 끝
	//1:1문의에 관한 메소드들 끝
	
	//공지사항에 관한 메소드들 시작
	
	//공지사항 글 리스트뿌려주는 메소드 시작
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
			System.out.println("noticeList메소드 내부에서 오류 : " + e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return nList;
	}
	//공지사항 글 리스트뿌려주는 메소드 끝
	
	//공지사항 글 등록 메소드 시작
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
			System.out.println("insertNotice 메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	
	
	//공지사항 글 등록 메소드 끝
	
	//하나의 공지사항 글 가져오는 메소드 시작
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
			System.out.println("getOneNotice메소드 내부에서 오류 : "+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return ndto;
	}
	//하나의 공지사항 글 가져오는 메소드 끝
	
	//공지사항 글 삭제메소드 시작
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
			System.out.println("deleteNotice메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//공지사항 글 삭제메소드 끝
	
	//공지사항 글 수정메소드 시작
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
			System.out.println("UpdateNotice메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//공지사항 글 수정메소드 끝
	
	//공지사항에 관한 메소드들 끝
	
	//FAQ에 관한 메소드 시작
	
	//FAQ 글 목록 뿌려주는 메소드 시작
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
			System.out.println("informationUseList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return list;
	}
	
	//FAQ 글 목록 뿌려주는 메소드 �P
	//FAQ 글 올리는 메소드 시작
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
				System.out.println("InsertInformation메소드 내부에서 오류 : "+e);
			}finally{
				freeRes(con, pstmt);
			}
		}
		//FAQ 글 올리는 메소드 끝
		
	//FAQ 하나의 글 가져오는 메소드 시작
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
			System.out.println("getOneInfo 메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return idto;
	}
	//FAQ 하나의 글 가져오는 메소드 끝
	
	//FAQ 하나의 글 수정 메소드 시작
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
			System.out.println("UpdateInfo메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	//FAQ 하나의 글 수정 메소드 끝
	
	//FAQ 하나의 글 삭제 메소드 시작
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
			System.out.println("deleteInfo 메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//FAQ 하나의 글 삭제 메소드 끝
	//FAQ에 관한 메소드 끝
	
	//희준_ 투자요청 수락여부 메소드 시작
	//리턴값 필요 없이 DB안의 investRequest테이블의 permitChk값 true 변환
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
			System.out.println("AdminPermitCheck메소드 내부에서 오류 : "+e);
		}finally {
			freeRes(con, pstmt);
		}
		
	}//희준_ 투자요청 수락여부 메소드 끝
	//희준_ 투자요청 수락여부 메소드 시작
		//리턴값 필요 없이 DB안의 investRequest테이블의 updateChk= true 변환
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
				System.out.println("AdminPermitCheck메소드 내부에서 오류 : "+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}//희준_ 투자요청 수락여부 메소드 끝
	
	
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
	//자원해제 메소드
	public void freeRes(Connection con, PreparedStatement pstmt, ResultSet rs){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
		if(rs!=null){try{rs.close();}catch(Exception e){e.printStackTrace();}}
	}
	public void freeRes(Connection con, PreparedStatement pstmt){
		if(con!=null){try{con.close();}catch(Exception e){e.printStackTrace();}}
		if(pstmt!=null){try{pstmt.close();}catch(Exception e){e.printStackTrace();}}
	}//자원해제 메소드 끝
	
	//투자 요청 현황에 관한 메소드들 시작
	
	//투자 요청 리스트 가져오는 메소드 시작
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
			System.out.println("InvestRequestList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return InvestRequestList;
	}
	//투자 요청 리스트 가져오는 메소드 끝
	//희준 투자 진행 리스트 가져오는 메소드 시작
	//오버로딩으로 불리언 값이 있으면 여기로 와서 진행
	//그냥 구분짓기 위해 하나 더 만든거
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
				System.out.println("InvestRequestList메소드 내부에서 오류 : "+e);
			}finally{
				freeRes(con, pstmt, rs);
			}
			
			return InvestRequestList;
		}
		//희준 투자 진행 리스트 가져오는 메소드 끝
	
	//투자 요청 현황에 관한 메소드들 끝
	
	//투자자에 관한 메소드들 시작
	
	//투자 요청글에대한 투자자들을 불러오는 메소드 시작
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
			System.out.println("InvestMentList메소드 내부에서 오류  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	//투자 요청글에대한 투자자들을 불러오는 메소드 끝
	
	//회원이 투자한 내역 불러오는 메소드 시작
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
			System.out.println("InvestMentList메소드 내부에서 오류  : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return InvestMentList;
	}
	//회원이 투자한 내역 불러오는 메소드 끝
	
	//투자자에 관한 메소드들 끝
	
	멤버에 관한 메소드들 시작
	
	//회원 리스트 가져오는 메소드 시작
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
			System.out.println("MemberList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}

		return MemberList;
	}
	//회원 리스트 가져오는 메소드 끝
	
	//회원 한명의 정보 가져오는 메소드 시작
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
			System.out.println("getOneMember메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return mdto;
	}
	//회원 한명의 정보 가져오는 메소드 끝
	
	//회원 강제 탈퇴시키는 메소드 시작
	public void DeleteMember(String nickname){
		Connection con = null;
		PreparedStatement pstmt =null;
		
		try{
			con = getCon();
			String sql = "DELETE FROM member WHERE nickname=?";
			pstmt = con.prepareStatement(sql);
			pstmt.executeUpdate();
		}catch(Exception e){
			System.out.println("DeleteMember메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		
	}
	//회원 강제 탈퇴시키는 메소드 끝
	
	멤버 관련 메소드 끝
	
	게시판에 관한 메소드들 시작
	
	//게시판 글 리스트 뿌려주는 메소드 시작
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
			System.out.println("BoardList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return BoardList;
	}
	//게시판 글 리스트 뿌려주는 메소드 끝
	
	//한명의 회원이 쓴 글 리스트 뿌려주는 메소드 시작
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
			System.out.println("BoardList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return BoardList;
	}
	//한명의 회원이 쓴 글 리스트 뿌려주는 메소드 끝
	
	//게시판 하나의 글 뿌려주는 메소드 시작
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
			System.out.println("getOneBoard메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return bdto;
	}
	//게시판 하나의 글 뿌려주는 메소드 끝
	
	//게시판 하나의 글 삭제시키는 메소드 시작
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
			System.out.println("DeleteBoard메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
		return check;
	}
	//게시판 하나의 글 삭제시키는 메소드 끝
	
	게시판에 관한 메소드들 끝
	
	댓글에 관한 메소드들 시작
	
	//댓글 글번호관련해서 뿌려주기 시작
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
			System.out.println("replyListNum메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return replyListNum;
	}
	//댓글 글번호 관련해서 뿌려주기 끝
	
	//내가 작성한 댓글 가져오기 시작
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
			System.out.println("replyListName메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		return replyListName;
	}
	//내가 작성한 댓글 가져오기 끝
	
	댓글에 관한 메소드들 끝
	
	//1:1문의에 관한 메소드들 시작
	//1:1문의글 (답변안달아준) 리스트뿌려주는 메소드 시작
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
			System.out.println("QuestionList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//1:1문의글 리스트 뿌려주는 메소드 끝
	
	//회원이 작성한 개개인의 1:1문의글 리스트 뿌려주는 메소드 시작
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
			System.out.println("QuestionList메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt, rs);
		}
		
		return questionList;
	}
	//회원이 작성한 개개인의 1:1문의글 리스트 뿌려주는 메소드 끝
	
	//1:1문의글 답변글 달아주는 메소드 시작
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
			System.out.println("UpdateQuestion메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	//1:1문의글 답변글 달아주는 메소드 끝
	
	//하나의 1:1문의글 보는 메소드 시작
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
			System.out.println("getOneQuestion메소드 내부에서 오류 : "+e);
		}finally {
			freeRes(con, pstmt, rs);
		}
		
		return qdto;
	}
	//하나의 1:1문의글 보는 메소드 끝
	
	//1:1문의에 관한 메소드들 끝
	
	//공지사항에 관한 메소드들 시작
	
	//공지사항 글 올리는 메소드 시작
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
			System.out.println("InsertInformation메소드 내부에서 오류 : "+e);
		}finally{
			freeRes(con, pstmt);
		}
	}
	//공지사항 글 올리는 메소드 시작
	
	//공지사항에 관한 메소드들 끝
	
	//희준_ 투자요청 수락여부 메소드 시작
	//리턴값 필요 없이 DB안의 investRequest테이블의 permitChk값 true 변환
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
			System.out.println("AdminPermitCheck메소드 내부에서 오류 : "+e);
		}finally {
			freeRes(con, pstmt);
		}
		
	}//희준_ 투자요청 수락여부 메소드 끝
	//희준_ 투자요청 수락여부 메소드 시작
		//리턴값 필요 없이 DB안의 investRequest테이블의 updateChk= true 변환
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
				System.out.println("AdminPermitCheck메소드 내부에서 오류 : "+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}//희준_ 투자요청 수락여부 메소드 끝
	
	
	
}
*/