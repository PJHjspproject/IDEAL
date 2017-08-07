package net.member.db;

import java.util.ArrayList;

public interface MemberMethod {
	//회원가입 메소드
	public Boolean JoinMember(MemberDTO mdto);
	//회원 수정메소드
	public int UpdateMember(MemberDTO mdto);
	//회원 삭제 메소드
	public void DeleteMember(String MemberEmail,String pass);
	//이메일 중복 확인 메소드
	public boolean CheckMember(String MemberEmail);
	//로그인 메소드
	public int LoginMember(String MemberEmail,String pass);
	//마이페이지 이동전 비밀번호 확인 메소드
	public boolean UserCheck(String MemberEmail,String pass);
	//회원정보 뿌려주기 메소드
	public MemberDTO InfoMember(String MemberEmail);
	//닉네임을 뿌려주기위한 메소드
	public String nickNamePick(String MemberEmail);
	//닉네임 중복검사 메소드
	public boolean NickCheck(String nickName);
	
}
