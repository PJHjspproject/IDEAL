package net.InformationUse.db;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;



public class InformationUseDao implements InformationMethod{
	//DB���� �޼ҵ� ������ּ���
	
	private Connection getCon(){
		Connection con = null;
		
		try {
			
			//1. WAS������ ����� ��������Ʈ�� ��� ������ ������ �ִ� ���ؽ�Ʈ ��ü ����
			Context init = new InitialContext();
			//2. ����� WAS�������� DataSource(Ŀ�ؼ�Ǯ) �˻��ؼ� ��������
			DataSource ds = (DataSource)init.lookup("java:comp/env/jdbc/jspbeginner");
			//3. DataSource(Ŀ�ؼ�Ǯ)���� �̸� ����� DB������ü(Ŀ�ؼ�) ��������
			con = ds.getConnection();
			
		} catch (Exception err) {
			System.out.println("DB���� ���� : " + err);
		}
		return con;
	}//DB���� �޼ҵ� getcon ��
	
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
	
		//������ �������� ��� �۵��� �������� �޼ҵ�
		public ArrayList<InformationUseDto> Information() {
			//DB���� 3�ѻ�
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			//����Ÿ�Կ� �´� ���� �� ����
			ArrayList<InformationUseDto> Array = new ArrayList<InformationUseDto>();
			
			
			try {
				
			//DB����
				con = getCon();
			//SQL������ �ۼ�
			String sql = "select * from informationUse";
			//SQL������ ���ప RS�� ���
			pstmt = con.prepareStatement(sql);
			rs = pstmt.executeQuery();
			//�ݺ��� ����
			while (rs.next()) {
				//InformationUseDto��ü ����
				InformationUseDto dto = new InformationUseDto();
				//InformationUseDto��ü�ȿ� RS�� ������ ���
				dto.setInfoNum(rs.getInt(1));
				dto.setInfoSubject(rs.getString(2));
				dto.setInfoTitle(rs.getString(3));
				dto.setInfoContent(rs.getString(4));
				dto.setInfoImage(rs.getString(5));
				//InformationUseDto��ü�� ArrayList�� ���
				Array.add(dto);
			}
						
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Information�޼ҵ忡�� ���� : "+e);
			}finally {
				freeRes(con, pstmt, rs);
			}
			
			//ArrayList����
			
			return Array;
		}//Information �޼ҵ� ��

		//��ȣ�� �Ű������� �޾Ƽ� �׹�ȣ�� ���� ���� �󼼺����ϴ� �޼ҵ�
		
		public InformationUseDto getInfo(int infoNum) {
			//����Ÿ���� ���� ����
			InformationUseDto dto = null;
			//DB���� ���ѻ�
			Connection con = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			try {
			//DB����
				con = getCon();
			//SQL������ �ۼ�
				String sql = "select * from informationUse where infoNum=?";
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, infoNum);
			rs = pstmt.executeQuery();
			//RS�� ���� �������~
			if(rs.next()){
				dto = new InformationUseDto();
			//InformationUseDto�ȿ� RS�� ������ ���
				dto.setInfoNum(rs.getInt(1));
				dto.setInfoSubject(rs.getString(2));
				dto.setInfoTitle(rs.getString(3));
				dto.setInfoContent(rs.getString(4));
				dto.setInfoImage(rs.getString(5));
				
				}
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("getInfo�޼ҵ� ����"+e);
			}finally {
				freeRes(con, pstmt, rs);
			}
			//����
			return dto;
		}

		//�����ڰ� �۾��� �޼ҵ�
		
		public void InsertInfo(InformationUseDto iudto) {
			//DB���� 2�ѻ�
			Connection con=null;
			PreparedStatement pstmt = null;
			try {
			//DB����
			con = getCon();
			//SQL������ �ۼ�
			String sql = "insert into informationUse (infoNum,infoSubject,infoTitle,infoContent,infoImage) "
					+ "values(?,?,?,?,?)";
			//���� ����
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, iudto.getInfoNum());
			pstmt.setString(2, iudto.getInfoSubject());
			pstmt.setString(3, iudto.getInfoTitle());
			pstmt.setString(4, iudto.getInfoContent());
			pstmt.setString(5, iudto.getInfoImage());
			pstmt.executeUpdate();
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("InsertInfo�޼ҵ� "+e);
			}finally {
				freeRes(con, pstmt);
			}
		}

		
		public void UpdateInfo(InformationUseDto iudto) {
			//DB���� 2�ѻ�
			Connection con = null;
			PreparedStatement pstmt = null;
			
			try {
			//DB����
			con = getCon();
			//SQL������ �ۼ�
			String sql = "update informationUse set infoNum=?, infoSubject=?, infoTitle=?, infoContent=?,"
					+ " infoImage=?";
			//���� ����
			pstmt = con.prepareStatement(sql);
			pstmt.setInt(1, iudto.getInfoNum());
			pstmt.setString(2, iudto.getInfoSubject());
			pstmt.setString(3, iudto.getInfoTitle());
			pstmt.setString(4, iudto.getInfoContent());
			pstmt.setString(5, iudto.getInfoImage());
			pstmt.executeUpdate();
			
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("UpdateInfo�޼ҵ� ��"+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}

		
		public void DeleteInfo(int infoNum) {
			//DB���� 2�ѻ�
			Connection con = null;
			PreparedStatement pstmt = null;
			try {
			//DB����
				con = getCon();
			//SQL������ �ۼ�
				String sql ="delete from informationUse where infoNum=?";
			//���� ����
				pstmt = con.prepareStatement(sql);
				pstmt.setInt(1, infoNum);
				pstmt.executeUpdate();
			
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("DeleteInfo�޼ҵ� ����: "+e);
			}finally {
				freeRes(con, pstmt);
			}
			
		}

}
