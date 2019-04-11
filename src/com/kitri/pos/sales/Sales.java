package com.kitri.pos.sales;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

public class Sales extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField notice;
	private JPanel pMonitor;
//	카드 레이아웃에 붙일 패널
	ViewSalesCustomer viewSalesCustomer = new ViewSalesCustomer();
	ViewSalesDisuse viewSalesDisues = new ViewSalesDisuse();
	ViewSalesInput viewSalesInput = new ViewSalesInput();
	ViewSalesChange viewSalesChange = new ViewSalesChange();

	DealCancel dealCancel = new DealCancel();
	
	
	Payment_1 payment_1 = new Payment_1();
	Payment_2 payment_2 = new Payment_2();
	Payment_3 payment_3 = new Payment_3();
	Payment_4 payment_4 = new Payment_4();
	Reciept reciept = new Reciept();
//	카드레이아웃 다른 클래스에서 쓰기 위해 전역 변수로 지정
	CardLayout cardLayout = new CardLayout();
//	카드레이아웃에 사용할 버튼 전역 변수 지정
//	판매 기본 버튼 
	JButton sBtnCustomer;
	JButton sBtnDisuse;
	JButton sBtnPdInput;
	JButton sBtnPdChange;
	JButton sBtnPdCancel;
	JButton sBtnPdHold;
	JButton sBtnPay;
	JButton sBtnCancel;

//	결제창 버튼	
	JButton btnP1Before;
	JButton btnP1Next;
	
//	결제 2
	JButton btnP2Before;
	JButton btnP2Next;
	JButton btnP2Cancel;
	JButton btnP2Register;
	JButton btnP2Save;
	JButton btnP2Apply;
	
//	결제 4
	JButton btnP3Before;
	JButton btnP3Next;
	JButton btnP3Cancel;
	
//	결제3
	JButton btnP4Before;
	JButton btnP4Cancel;
	JButton btnP4Payment;
	JButton btnP4PrintReceipt;
	
//	영수증
	
	JButton cancel;
	JButton print;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Sales frame = new Sales();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Sales() {
		setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setTitle("Force.pos");
		setAlwaysOnTop(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1326, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel pStatusBar = new JPanel();
		pStatusBar.setBackground(new Color(0, 0, 128));
		pStatusBar.setBounds(0, 0, 1308, 51);
		contentPane.add(pStatusBar);
		pStatusBar.setLayout(null);

		JLabel titleLabel = new JLabel("Force. pos");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(0, 0, 128));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		titleLabel.setBounds(14, 8, 241, 31);
		pStatusBar.add(titleLabel);

		notice = new JTextField();
		notice.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		notice.setText("\uC0C1\uD488\uBA85(..)\uB294 \uC720\uD1B5\uAE30\uD55C\uC774 \uC9C0\uB0AC\uC2B5\uB2C8\uB2E4.");
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		notice.setBounds(258, 8, 726, 31);
		pStatusBar.add(notice);
		notice.setColumns(10);

		JLabel dateLabel = new JLabel("2019-04-01 \uC624\uD6C4 5:01");
		dateLabel.setBackground(new Color(0, 0, 128));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		dateLabel.setBounds(1016, 8, 278, 31);
		pStatusBar.add(dateLabel);

		JPanel pMainBtn = new JPanel();
		pMainBtn.setBackground(new Color(255, 255, 255));
		pMainBtn.setBounds(0, 585, 1144, 120);
		contentPane.add(pMainBtn);
		pMainBtn.setLayout(null);

		JLabel idLabel = new JLabel("\uAD00\uB9AC\uC790");
		idLabel.setBackground(new Color(105, 105, 105));
		idLabel.setHorizontalAlignment(SwingConstants.CENTER);
		idLabel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		idLabel.setBounds(860, 0, 201, 120);
		pMainBtn.add(idLabel);

		JButton mBtnInven = new JButton("\uC7AC\uACE0");
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnInven.setBounds(0, 0, 157, 120);
		pMainBtn.add(mBtnInven);

		JButton mBtnSale = new JButton("\uD310\uB9E4");
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		mBtnSale.setBounds(156, 0, 157, 120);
		pMainBtn.add(mBtnSale);

		JButton mBtnCalc = new JButton("\uC815\uC0B0");
		mBtnCalc.setBackground(new Color(28, 94, 94));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnCalc.setBounds(313, 0, 157, 120);
		pMainBtn.add(mBtnCalc);

		JButton mBtnStat = new JButton("\uD1B5\uACC4");
		mBtnStat.setBackground(new Color(99, 166, 166));
		mBtnStat.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(470, 0, 157, 120);
		pMainBtn.add(mBtnStat);

		JButton mBtnAccount = new JButton("\uACC4\uC815");
		mBtnAccount.setBackground(new Color(28, 94, 94));
		mBtnAccount.setForeground(new Color(255, 255, 255));
		mBtnAccount.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnAccount.setBounds(626, 0, 157, 120);
		pMainBtn.add(mBtnAccount);

		JPanel pSellFunction = new JPanel();
		pSellFunction.setBackground(new Color(0, 0, 128));
		pSellFunction.setBounds(1144, 50, 164, 655);
		contentPane.add(pSellFunction);
		pSellFunction.setLayout(null);

		sBtnCustomer = new JButton("\uACE0\uAC1D\uAD00\uB9AC");
		sBtnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sBtnCustomer.setForeground(new Color(255, 255, 255));
		sBtnCustomer.setBackground(new Color(0, 0, 128));
		sBtnCustomer.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnCustomer.setBounds(0, 0, 164, 85);
		pSellFunction.add(sBtnCustomer);

		sBtnDisuse = new JButton("\uD3D0\uAE30\uAD00\uB9AC");
		sBtnDisuse.setBackground(new Color(100, 149, 237));
		sBtnDisuse.setForeground(new Color(255, 255, 255));
		sBtnDisuse.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnDisuse.setBounds(0, 83, 164, 85);
		pSellFunction.add(sBtnDisuse);

// #전역 변수로 선언한 BtnEnroll을 사용해야 합니다#
		sBtnPdInput = new JButton("\uC0C1\uD488\uB4F1\uB85D");

		sBtnPdInput.setBackground(new Color(0, 0, 128));
		sBtnPdInput.setForeground(new Color(255, 255, 255));
		sBtnPdInput.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdInput.setBounds(0, 166, 164, 85);
		pSellFunction.add(sBtnPdInput);

		sBtnPdChange = new JButton("\uC0C1\uD488\uC218\uC815");

		sBtnPdChange.setBackground(new Color(100, 149, 237));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 249, 164, 85);
		pSellFunction.add(sBtnPdChange);

		sBtnPdCancel = new JButton("\uC0C1\uD488\uCDE8\uC18C");

		sBtnPdCancel.setBackground(new Color(0, 0, 128));
		sBtnPdCancel.setForeground(new Color(255, 255, 255));
		sBtnPdCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdCancel.setBounds(0, 333, 164, 85);
		pSellFunction.add(sBtnPdCancel);

		sBtnPdHold = new JButton("\uC0C1\uD488\uBCF4\uB958");
		sBtnPdHold.setForeground(Color.WHITE);
		sBtnPdHold.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdHold.setBackground(new Color(100, 149, 237));
		sBtnPdHold.setBounds(0, 416, 164, 85);
		pSellFunction.add(sBtnPdHold);

		sBtnPay = new JButton("\uACB0\uC81C");
		sBtnPay.setForeground(Color.WHITE);
		sBtnPay.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPay.setBackground(new Color(255, 51, 0));
		sBtnPay.setBounds(0, 500, 164, 85);
		pSellFunction.add(sBtnPay);

		sBtnCancel = new JButton("\uAC70\uB798\uCDE8\uC18C");
		sBtnCancel.setForeground(Color.WHITE);
		sBtnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnCancel.setBackground(new Color(153, 0, 0));
		sBtnCancel.setBounds(0, 570, 164, 85);
		pSellFunction.add(sBtnCancel);

		pMonitor = new JPanel();
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1144, 535);
		contentPane.add(pMonitor);

//		카드레이아웃 지정

		pMonitor.setLayout(cardLayout);

//		패널에 이름 설정 후, 변수명 지정

		pMonitor.add("ViewSalesCustomer", viewSalesCustomer);
		pMonitor.add("ViewSalesDisuse", viewSalesDisues);
		pMonitor.add("ViewSalesInput", viewSalesInput);
		pMonitor.add("ViewSalesChange", viewSalesChange);

//		처음에 보여줄 패널 설정
		cardLayout.show(pMonitor, "Enroll");

//		각 버튼에 이벤트 등록하기
		sBtnCustomer.addActionListener(this);
		sBtnDisuse.addActionListener(this);
		sBtnPdInput.addActionListener(this);
		sBtnPdChange.addActionListener(this);
		sBtnPdCancel.addActionListener(this);
		sBtnPdHold.addActionListener(this);
		sBtnPay.addActionListener(this);
		sBtnCancel.addActionListener(this);

////		결제1 이벤트 등록
		payment_1.btnP1Before.addActionListener(this);
		payment_1.btnP1Next.addActionListener(this);

//		결제2 이벤트 등록
		payment_2.btnP2Before.addActionListener(this);
		payment_2.btnP2Next.addActionListener(this);
		payment_2.btnP2Cancel.addActionListener(this);
		payment_2.btnP2Register.addActionListener(this);
		payment_2.btnP2Save.addActionListener(this);
		payment_2.btnP2Apply.addActionListener(this);
		
//		결제 4 이벤트 등록
		payment_3.btnP3Before.addActionListener(this);
		payment_3.btnP3Next.addActionListener(this);
		payment_3.btnP3Cancel.addActionListener(this);
		
		
//		결제3 이벤트 등록
		payment_4.btnP4Before.addActionListener(this);
		payment_4.btnP4Cancel.addActionListener(this);
		payment_4.btnP4Payment.addActionListener(this);
		payment_4.btnP4PrintReceipt.addActionListener(this);
		
//		영수증 이벤트 등록
		reciept.cancel.addActionListener(this);;
		reciept.print.addActionListener(this);;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if (ob == sBtnCancel) {
			dealCancel.setVisible(true);
		} else if (ob == sBtnDisuse) {
			cardLayout.show(pMonitor, "ViewSalesDisuse");
		} else if (ob == sBtnPdInput) {
			cardLayout.show(pMonitor, "ViewSalesInput");
		} else if (ob == sBtnPdChange) {
			cardLayout.show(pMonitor, "ViewSalesChange");
		} else if (ob == sBtnCustomer) {
			cardLayout.show(pMonitor, "ViewSalesCustomer");// 판매 오른쪽 창 완료
		} else if (ob == sBtnPay) {// 결제 1창 연결
			payment_1.setVisible(true);
		} else if (ob == payment_1.btnP1Before) {
			payment_1.setVisible(false);
		} else if (ob == payment_1.btnP1Next) {
			payment_1.setVisible(false);
			payment_2.setVisible(true);
		} else if (ob == payment_2.btnP2Before) {// 결제2 창 연결
			payment_1.setVisible(true);
		} else if (ob == payment_2.btnP2Next) {
			payment_3.setVisible(true);
			payment_2.setVisible(false);
		} else if (ob == payment_2.btnP2Cancel) {// 결제4창 연결
			payment_2.setVisible(false);
		} else if (ob == payment_3.btnP3Before) {
			payment_2.setVisible(true);
		} else if (ob == payment_3.btnP3Next) {
			payment_3.setVisible(false);
			payment_4.setVisible(true);
		} else if (ob == payment_3.btnP3Cancel) {//결제 4창 끝, 결제 3창 연결
			payment_3.setVisible(false);
		} else if (ob == payment_4.btnP4Before) {
			payment_3.setVisible(true);
		} else if (ob == payment_4.btnP4Cancel) {
			payment_4.setVisible(false);
		} else if (ob == payment_4.btnP4Payment) {
			
		} else if (ob == payment_4.btnP4PrintReceipt) {
			reciept.setVisible(true);
		} else if (ob == reciept.cancel) {
			reciept.setVisible(false);
		} else if (ob == reciept.print) {
			reciept.setVisible(false);
		}
			
	}
}