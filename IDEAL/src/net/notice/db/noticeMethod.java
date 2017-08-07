package net.notice.db;

import java.util.ArrayList;

public interface noticeMethod {

	//공지사항 글 전체목록 불러오는 메소드
	public ArrayList<noticeDto> AllNotice();
	
	//공지사항 글 등록메소드
	public void InsertNotice(noticeDto ndto);
	
	//공지사항 글 수정메소드
	public void UpdateNotice(noticeDto ndto);
	
	//공지사항 글 삭제메소드
	public void DeleteNotice(int num);
	
	//공지사항 글 중 하나의 글 불러오는 메소드
	public noticeDto OneNotice(int num);
}
