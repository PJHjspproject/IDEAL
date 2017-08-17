package net.Investment.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.Card.db.CardDao;
import net.Card.db.CardDto;
import net.Investment.db.InvestmentDao;
import net.Investment.db.InvestmentDto;
import net.investRequest.db.InvestRequestDao;
import net.investRequest.db.InvestRequestDto;


public class investmentAction implements Action{

	@Override
	public ActionForward excute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		
		CardDto cardDto = new CardDto();
		CardDao cardDao = new CardDao();
		InvestRequestDto ivrDto = new InvestRequestDto();
		InvestRequestDao ivrDao = new InvestRequestDao();
		InvestmentDto imDto = new InvestmentDto();
		InvestmentDao imDao = new InvestmentDao();
		HttpSession session = request.getSession();
		
		// ī������ ���ϱ�
		
		String cardNum1 = request.getParameter("cardNum1");		// ī������ ù��° �ڸ� �޾ƿ���
		String cardNum2 = request.getParameter("cardNum2");		// ī������ �ι�° �ڸ� �޾ƿ���
		String cardNum3 = request.getParameter("cardNum3");		// ī������ ����° �ڸ� �޾ƿ���
		String cardNum4 = request.getParameter("cardNum4");		// ī������ �׹�° �ڸ� �޾ƿ���
		String cardNum = cardNum1+cardNum2+cardNum3+cardNum4;	// �޾ƿ� ī�� ��ȣ cardNum�� �����Ͽ� ���
		
		String cardExpiryM = request.getParameter("cardExpiryM");	//ī�� ��ȿ�Ⱓ �� �޾ƿ���
		String cardExpiryY = request.getParameter("cardExpiryY");	//ī�� ��ȿ�Ⱓ �� �޾ƿ���
		int cardExpiry = Integer.parseInt(cardExpiryY+cardExpiryM); //�޾ƿ� ī�� ��ȿ�Ⱓ �����ϱ�
		
		int cardPass = Integer.parseInt(request.getParameter("cardPass")); //ī�� ��й�ȣ �޾ƿ���
		String memberBirth = request.getParameter("memberBirth");	//��� ������� �޾ƿ���
		String cardBank = request.getParameter("cardBank");	// ī�� �������� �޾ƿ���
		 
		 
		cardDto.setCardNum(cardNum);		//ī���ȣ DTO�� ����
		cardDto.setCardPass(cardPass); 		//ī�� ��й�ȣ 2�ڸ� DTO�� ����
		cardDto.setMemberBirth(memberBirth);//ī�� ������ ������� DTO�� ����
		cardDto.setCardExpiry(cardExpiry);	//ī�� ��ȿ�Ⱓ DTO�� ����
		cardDto.setCardBank(cardBank);		//ī�� ���� DTO�� ����
		
		
		int result = cardDao.getOneCard(cardDto);	//ī�� ���� ���ϱ� �޼ҵ�
		System.out.println(result);
		
		//investment ���� �޾ƿ���
		int investRequestNum = Integer.parseInt(request.getParameter("InvestRequestNum"));
		String memberEmail = request.getParameter("memberEmail");
		String investName = request.getParameter("investName");
		int investMoney = Integer.parseInt(request.getParameter("InvestMoney"));
		String program = request.getParameter("program");
		
//		System.out.println("investment ���� ���� Ȯ��" + investRequestNum);
//		System.out.println("investment ���� ���� Ȯ��" + memberEmail);
//		System.out.println("investment ���� ���� Ȯ��" + investName);
//		System.out.println("investment ���� ���� Ȯ��" + investMoney);
//		System.out.println("investment ���� ���� Ȯ��" + program);
		
		//investment ���� �����ϱ�
		
		imDto.setInvestRequestNum(investRequestNum);
		imDto.setMemberEmail(memberEmail);
		imDto.setInvestName(investName);
		imDto.setInvestMoney(investMoney);
		imDto.setProgram(program);
		
//		System.out.println("investment ���� ���� Ȯ��" + imDto.getInvestRequestNum());
//		System.out.println("investment ���� ���� Ȯ��" + imDto.getMemberEmail());
//		System.out.println("investment ���� ���� Ȯ��" + imDto.getInvestName());
//		System.out.println("investment ���� ���� Ȯ��" + imDto.getInvestMoney());
//		System.out.println("investment ���� ���� Ȯ��" + imDto.getProgram());
		
		
		
		
		// ī�� ���� ���Ͽ� �Է��� ī�������� DB ī�������� ��ġ�ϸ� result�� 1�� ���� ��
		//result�� 1�� �� 
		if(result==1){
			
			//���ڿ�û �Խù� ���� �ݾ�(nowMoney)�� ���ڱݾ� ����
			int nowMoney=Integer.parseInt(request.getParameter("InvestMoney"));
			int InvestRequestNum = Integer.parseInt(request.getParameter("InvestRequestNum"));
			
			ivrDto.setNowMoney(nowMoney);

			ivrDao.increaseNowMoney(nowMoney, InvestRequestNum);
			
			//ī�� ���� ����
			cardDao.getPayCard(cardNum);
			
			//ī�� �ݾ� ����
			
			cardDao.deductBalance(nowMoney, cardNum);
			
			//ī�� �ݾ� ���� �� investment DB�� ���� �ø���
			
			imDao.InsertInvestment(imDto);
			
			//���ڱݾ� 100% ������ fundsituation ����
			
			ivrDao.updateFundsituation(InvestRequestNum);
			
			//���ڿϷ� �޼��� ����ֱ�
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('������ �ּż� �����մϴ�.');");
			out.print("location.href='index.im';");
			out.print("</script>");
			out.close();
			
			return null;
		//result�� 0�̸� ī�� ������ ��ġ���� �ʽ��ϴ� �ȳ��� ����ֱ�
		}else if(result==0){
			response.setContentType("text/html; charset=UTF-8");
			PrintWriter out = response.getWriter();
			out.print("<script>");
			out.print("alert('ī�� ������ ��ġ ���� �ʽ��ϴ�.\\nī�������� �ٽ� Ȯ���Ͽ� �ּ���.');");
			out.print("location.href='investment.im?investRequestNum="+investRequestNum+"';");
			out.print("</script>");
			out.close();
			return null;
	}
		return null;
		
	
	}
}
