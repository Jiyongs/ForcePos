package com.kitri.pos;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTable;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import com.kitri.pos.stat.*;

import oracle.sql.ConverterArchive;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;

import com.kitri.pos.stat.*;
import com.kitri.pos.account.*;
import com.kitri.pos.calc.*;
import com.kitri.pos.sale.*;
import com.kitri.pos.db.*;
import com.kitri.pos.stock.*;

public class MainFrame extends JFrame implements ActionListener, Runnable {
	PosDto posdto;
	public SalesDao salesDao = new SalesDao();
	StockDao stockdao = new StockDao();
	StockPopupIn stockpopupin = new StockPopupIn();
	StockPopupChg stockpopupchg = new StockPopupChg();
	StockPopupSearch stockpopupsearch = new StockPopupSearch();
	StockBtn stockbtn;
	StockMonitor stockmonitor = new StockMonitor();
	public CardLayout monitor;
	public CardLayout btn;
	Thread thread;

	public JButton logout;

	private JPanel contentPane;
	public JTextField notice;
	private JPanel pStatusBar;
	private JLabel titleLabel;
	private JLabel dateLabel;
	private JPanel pMainBtn;
	public JButton mBtnInven;
	public JButton mBtnSale;
	public JButton mBtnCalc;
	public JButton mBtnStat;
	public JButton mBtnAccount;
	public JPanel pMonitor;
	public JPanel pFBtn;

	ViewStatProduct v1 = new ViewStatProduct(); // 상품별 패널
	ViewStatYear v2 = new ViewStatYear(); // 연도별 패널
	ViewStatMonth v3 = new ViewStatMonth(); // 월별 패널
	ViewStatDay v4 = new ViewStatDay(); // 일별 패널
	ViewStatButtons statbtn;

	Administrator admin = new Administrator();
	AccountBtn accbtn;

	CalcService calcService = new CalcService(this);
	public PCalc pCalc = new PCalc();
	public CalcBtn calcbtn = new CalcBtn();

	public ViewSalesCustomer viewSalesCustomer = new ViewSalesCustomer();
	public ViewSalesDisuse viewSalesDisuse = new ViewSalesDisuse();
	public ViewSalesInput viewSalesInput = new ViewSalesInput();
	public SalesInputService salesInputService = new SalesInputService(this);

	// 결제및 결제취소창 프레임
	public DealCancel dealCancel = new DealCancel();
	public Payment_1 payment_1 = new Payment_1();
	public Payment_2 payment_2 = new Payment_2();
	public Payment_3 payment_3 = new Payment_3();
	public Payment_4 payment_4 = new Payment_4();

	public SaleBtn salebtn = new SaleBtn();

//	상품보류 버튼 눌림 여부 flag
	boolean isHold = false;
//	보류한 상품들의 PosDto객체들을 저장할 벡터
	Vector<PosDto> hodingProductList = null;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					MainFrame frame = new MainFrame();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}

	/**
	 * Create the frame.
	 */
	public MainFrame() {
		monitor = new CardLayout();
		btn = new CardLayout();
		stockbtn = new StockBtn();
		statbtn = new ViewStatButtons();
		accbtn = new AccountBtn();

		stockpopupin.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		stockpopupsearch.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		stockpopupchg.setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);

		setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setTitle("Force.pos");
//		setAlwaysOnTop(true);
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1326, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		pStatusBar = new JPanel();
		pStatusBar.setBackground(new Color(0, 0, 128));
		pStatusBar.setBounds(0, 0, 1320, 51);
		contentPane.add(pStatusBar);
		pStatusBar.setLayout(null);

		titleLabel = new JLabel("Force. pos");
		titleLabel.setForeground(Color.WHITE);
		titleLabel.setBackground(new Color(0, 0, 128));
		titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
		titleLabel.setFont(new Font("맑은 고딕", Font.BOLD, 25));
		titleLabel.setBounds(14, 8, 241, 31);
		pStatusBar.add(titleLabel);

		notice = new JTextField();
		notice.setDisabledTextColor(Color.BLACK);
		notice.setEnabled(false);
		notice.setEditable(false);
		notice.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		notice.setText("");
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		notice.setBounds(258, 8, 726, 31);
		pStatusBar.add(notice);
		notice.setColumns(10);

		dateLabel = new JLabel();
		dateLabel.setBackground(new Color(0, 0, 128));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		dateLabel.setBounds(1016, 8, 278, 31);
		pStatusBar.add(dateLabel);

		pMainBtn = new JPanel();
		pMainBtn.setBackground(new Color(255, 255, 255));
		pMainBtn.setBounds(0, 600, 1157, 125);
		contentPane.add(pMainBtn);
		pMainBtn.setLayout(null);

		mBtnInven = new JButton("\uC7AC\uACE0");
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnInven.setBounds(0, 0, 157, 125);
		pMainBtn.add(mBtnInven);

		logout = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logout.setBackground(Color.BLACK);
		logout.setForeground(Color.WHITE);
		logout.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		logout.setBounds(988, 0, 157, 125);
		pMainBtn.add(logout);

		mBtnSale = new JButton("\uD310\uB9E4");
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		mBtnSale.setBounds(156, 0, 157, 125);
		pMainBtn.add(mBtnSale);

		mBtnCalc = new JButton("\uC815\uC0B0");
		mBtnCalc.setBackground(new Color(28, 94, 94));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnCalc.setBounds(313, 0, 157, 125);
		pMainBtn.add(mBtnCalc);

		mBtnStat = new JButton("\uD1B5\uACC4");
		mBtnStat.setBackground(new Color(99, 166, 166));
		mBtnStat.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(470, 0, 157, 125);
		pMainBtn.add(mBtnStat);

		mBtnAccount = new JButton("\uACC4\uC815");
		mBtnAccount.setBackground(new Color(28, 94, 94));
		mBtnAccount.setForeground(new Color(255, 255, 255));
		mBtnAccount.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		mBtnAccount.setBounds(626, 0, 157, 125);
		pMainBtn.add(mBtnAccount);

		pMonitor = new JPanel();
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1157, 552);
		contentPane.add(pMonitor);
		pMonitor.setLayout(monitor);
//		모니터패널에 카드레이아웃주고 각화면 패널 객체생성하여 추가
		pMonitor.add("ViewSalesInput", viewSalesInput);
		pMonitor.add(stockmonitor, "Stock");
		pMonitor.add("viewStatProduct", v1);
		pMonitor.add("viewStatYear", v2);
		pMonitor.add("viewStatMonth", v3);
		pMonitor.add("viewStatDay", v4);
		admin.pAcMonitor.setLocation(12, 10);
		admin.pAcMonitor.setSize(1133, 532);
		pMonitor.add(admin, "admin");
		pMonitor.add("Calc", pCalc);
		pMonitor.add("ViewSalesCustomer", viewSalesCustomer);
		pMonitor.add("ViewSalesDisuse", viewSalesDisuse);

		pFBtn = new JPanel();
		pFBtn.setBackground(Color.WHITE);
		pFBtn.setBounds(1156, 50, 164, 675);
		contentPane.add(pFBtn);
		pFBtn.setLayout(btn);
		salebtn.sBtnAcencel.setText("\uAC70\uB798\uCD08\uAE30\uD654");
//		기능버튼패널 카드 각기능버튼 패널 객체생성후 추가
		pFBtn.add(salebtn, "salebtn");
		pFBtn.add(stockbtn, "Stockbtn");
		pFBtn.add(statbtn, "Statbtn");
		pFBtn.add(accbtn, "Accbtn");
		pFBtn.add(calcbtn, "Calcbtn");

		// 메인기능버튼
		mBtnInven.addActionListener(this);
		mBtnSale.addActionListener(this);
		mBtnCalc.addActionListener(calcService);
		mBtnStat.addActionListener(this);
		mBtnAccount.addActionListener(this);
		// 재고 기능버튼
		stockbtn.stockSearch.addActionListener(this);
		stockbtn.stockIn.addActionListener(this);
		stockbtn.stockChg.addActionListener(this);
		stockbtn.stockevery.addActionListener(this);

		// 재고-검색 팝업 액션리스너
		stockpopupsearch.sCbtn.addActionListener(this);
		stockpopupsearch.SearchCode.addActionListener(this);
		stockpopupsearch.searchCancel.addActionListener(this);
		stockpopupsearch.SearchName.addActionListener(this);
		stockpopupsearch.sNbtn.addActionListener(this);

		// 재고-수정 팝업 액션리스너
		stockpopupchg.chgOk.addActionListener(this);
		stockpopupchg.chgCancel.addActionListener(this);
		stockpopupchg.ChgCountTf.addActionListener(this);
		// 재고-삽입 팝업 액션리스너 ( 입고버튼)
		stockpopupin.inOk.addActionListener(this);
		stockpopupin.inCancel.addActionListener(this);
		stockpopupin.PcodeTf.addActionListener(this);
		stockpopupin.IndateTf.addActionListener(this);
		stockpopupin.PcntTf.addActionListener(this);
		stockpopupin.pcodeInput.addActionListener(this);
		stockpopupin.indateInput.addActionListener(this);
		stockpopupin.pcntInput.addActionListener(this);

		// 통계 기능버튼
		statbtn.sBtnDay.addActionListener(this);
		statbtn.sBtnMonth.addActionListener(this);
		statbtn.sBtnProduct.addActionListener(this);
		statbtn.sBtnYear.addActionListener(this);

		// 계정 기능버튼
		accbtn.userInsert.addActionListener(this);
		accbtn.userDelete.addActionListener(this);
		accbtn.userUpdate.addActionListener(this);
		logout.addActionListener(this);

		// 계정 이벤트 리스너 등록
		admin.authority.addActionListener(this);
		admin.authorityUp.addActionListener(this);
		admin.ok.addActionListener(this);
		admin.cancel.addActionListener(this);
		admin.button.addActionListener(this);
		admin.button_1.addActionListener(this);

		// 계정기능 마우스 클릭 이벤트
		admin.table.addMouseListener(ms);

		// 정산 기능 리스너
		pCalc.btnCalc_0.addActionListener(calcService);
		pCalc.btnCalc_1.addActionListener(calcService);
		pCalc.btnCalc_2.addActionListener(calcService);
		pCalc.btnCalc_3.addActionListener(calcService);
		pCalc.btnCalc_4.addActionListener(calcService);
		pCalc.btnCalc_5.addActionListener(calcService);
		pCalc.btnCalc_6.addActionListener(calcService);
		pCalc.btnCalc_7.addActionListener(calcService);
		pCalc.btnCalc_8.addActionListener(calcService);
		pCalc.btnCalc_9.addActionListener(calcService);
		pCalc.btnCalc_00.addActionListener(calcService);
		pCalc.btnCalc_del.addActionListener(calcService);
		pCalc.btnCalc_C.addActionListener(calcService);
		pCalc.btnCalc_Apply.addActionListener(calcService);

		// 판매 기능 리스너
		salebtn.sBtnCustomer.addActionListener(this);
		salebtn.sBtnDisuse.addActionListener(this);
		salebtn.sBtnPdHold.addActionListener(this);
		salebtn.sBtnCancel.addActionListener(this);
		salebtn.sBtnPay.addActionListener(salesInputService);
		salebtn.sBtnAcencel.addActionListener(salesInputService);
		salebtn.sBtnPdChange.addActionListener(salesInputService);
		salebtn.sBtnPdCancel.addActionListener(salesInputService);

		viewSalesCustomer.enroll.addActionListener(this);
		viewSalesCustomer.search.addActionListener(this);
		viewSalesCustomer.delete.addActionListener(this);

//		결제1 이벤트 등록
		payment_1.cbP1Cooperation.addActionListener(salesInputService);
		payment_1.btnP1Apply.addActionListener(salesInputService);
		payment_1.btnP1Before.addActionListener(salesInputService);
		payment_1.btnP1Next.addActionListener(salesInputService);

//		결제2 이벤트 등록
		payment_2.btnP2Before.addActionListener(this);
		payment_2.btnP2Cancel.addActionListener(this);
		payment_2.btnP2Next.addActionListener(salesInputService);
		payment_2.btnP2Reference.addActionListener(salesInputService);
		payment_2.btnP2Apply.addActionListener(salesInputService);

//		결제3 이벤트 등록
		payment_3.btnP3Before.addActionListener(this);
		payment_3.btnP3Cancel.addActionListener(this);
		payment_3.btnP3Input.addActionListener(salesInputService);
		payment_3.btnP3Next.addActionListener(salesInputService);

//		결제 4 이벤트 등록
		
		payment_4.btnP4PrintReceipt.addActionListener(this);
		payment_4.btnP4Payment.addActionListener(salesInputService);

		dealCancel.btnRefund.addActionListener(this);
		viewSalesDisuse.disuse.addActionListener(this);

		viewSalesInput.code_input.addKeyListener(salesInputService);
		viewSalesInput.product_name_input.addKeyListener(salesInputService);

		// thread
		thread = new Thread(this);
		thread.start();

	}// 메인프레임메소드 끝

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		Object obb = e.getActionCommand();
		// 메인버튼들 기능
		if (ob == mBtnInven) {
			mBtnInven.setBackground(new Color(255, 69, 0));
			mBtnAccount.setBackground(new Color(28, 94, 94));
			mBtnCalc.setBackground(new Color(28, 94, 94));
			mBtnSale.setBackground(new Color(99, 166, 166));
			mBtnStat.setBackground(new Color(99, 166, 166));
			monitor.show(pMonitor, "Stock");
			btn.show(pFBtn, "Stockbtn");
		} else if (ob == mBtnAccount) {
			mBtnAccount.setBackground(new Color(255, 69, 0));
			mBtnInven.setBackground(new Color(28, 94, 94));
			mBtnCalc.setBackground(new Color(28, 94, 94));
			mBtnSale.setBackground(new Color(99, 166, 166));
			mBtnStat.setBackground(new Color(99, 166, 166));
			btn.show(pFBtn, "Accbtn");
			monitor.show(pMonitor, "admin");
		} else if (ob == mBtnSale) {
			mBtnSale.setBackground(new Color(255, 69, 0));
			mBtnAccount.setBackground(new Color(28, 94, 94));
			mBtnCalc.setBackground(new Color(28, 94, 94));
			mBtnInven.setBackground(new Color(28, 94, 94));
			mBtnStat.setBackground(new Color(99, 166, 166));
			monitor.show(pMonitor, "ViewSalesInput");
			btn.show(pFBtn, "salebtn");
		} else if (ob == mBtnStat) {
			mBtnStat.setBackground(new Color(255, 69, 0));
			mBtnAccount.setBackground(new Color(28, 94, 94));
			mBtnCalc.setBackground(new Color(28, 94, 94));
			mBtnSale.setBackground(new Color(99, 166, 166));
			mBtnInven.setBackground(new Color(28, 94, 94));
			monitor.show(pMonitor, "viewStatProduct");
			btn.show(pFBtn, "Statbtn");
		}
		// 각 메인기능들의 세부기능
		// 판매 탭의 기능들
		else if (ob == salebtn.sBtnCancel) {
			dealCancel.setVisible(true);
		} else if (ob == dealCancel.btnRefund) {
			int choose = JOptionPane.showConfirmDialog(dealCancel, "환불 절차를 진행하시겠습니까?", "환불",
					JOptionPane.OK_CANCEL_OPTION);
			if (choose == 0) {
				refundProcess();
			}
			dealCancel.Sell_id.setText("");
		} else if (ob == salebtn.sBtnDisuse) { // 폐기관리 버튼
			monitor.show(pMonitor, "ViewSalesDisuse"); // 카드레이아웃폐기화면
			showAllDisuse5();
		} else if (ob == salebtn.sBtnPdHold) { // 상품 보류 버튼 이벤트

			// 보류 취소
			if (isHold) {

				// *버튼 색상 원상복귀, 임시저장했던 테이블 값 재세팅, 총 가격 필드 재새팅
				salebtn.sBtnPdHold.setBackground(new Color(0, 0, 128));
				productsHoldCancelProcess(hodingProductList);

				// 현재 목록 테이블의 행 개수 얻어옴
				int rowsNum = viewSalesInput.table.getRowCount();

				// 추가된 목록 테이블의 총 가격 얻은 후, 세팅
				int totalPrice = 0;
				for (int i = 0; i < rowsNum; i++) {
					totalPrice += Integer.parseInt(String.valueOf(viewSalesInput.model.getValueAt(i, 5)));
				}
				viewSalesInput.total_price_input.setText(Integer.toString(totalPrice));

				isHold = false;

				// 보류 설정
			} else if (!isHold) {

				// 현재 목록 테이블의 행 개수 얻어옴
				int rowsNum = viewSalesInput.table.getRowCount();

				// 현재 목록에 상품이 0개인 경우 경고 후, return
				if (rowsNum == 0) {
					JOptionPane.showMessageDialog(null, "보류할 상품이 없습니다.");
					return;
				}

				// 현재 목록에 상품이 1개 이상인 경우,
				// 현재 상품들을 전역 Vector<PosDto> 객체인 holdingProductList에 임시 저장
				hodingProductList = productsHoldProcess();

				// *버튼 색상 변경, 테이블 뷰 리셋, 총 가격 필드 리셋
				salebtn.sBtnPdHold.setBackground(Color.ORANGE);
				clearRows(rowsNum, viewSalesInput.model);
				viewSalesInput.total_price_input.setText("");
				isHold = true;

			}

		}

// 		고객 검색 창 눌렀을 때 이벤트
		else if (ob == salebtn.sBtnCustomer) {
			monitor.show(pMonitor, "ViewSalesCustomer");
			showAll();

		}

		else if (ob == viewSalesCustomer.search) {

//			회원 검색할 때 조건
			String name;
			String cellphone;
			name = viewSalesCustomer.name.getText().trim();
			cellphone = viewSalesCustomer.cellphone.getText().trim();

			if (name.equals("") && cellphone.equals("")) {
				JOptionPane.showMessageDialog(viewSalesCustomer, "회원 이름이나 전화번호를 입력해주세요", "회원 조회 오류",
						JOptionPane.INFORMATION_MESSAGE);
			} else if (name.contentEquals("")) {
				searchNameProcess1();

			} else if (cellphone.contentEquals("")) {
				searchNameProcess();
			} else {
				searchNameProcess2();
			}

		} else if (ob == payment_1.btnP1Before) {
			payment_1.setVisible(false);
			payment_2.setVisible(false);
			payment_4.setVisible(false);
			payment_3.setVisible(false);
		} else if (ob == payment_1.btnP1Next) {
			payment_1.setVisible(false);
			payment_2.setVisible(true);
		} else if (ob == payment_2.btnP2Before) {// 결제2 창 연결
			payment_1.setVisible(true);
			payment_2.setVisible(false);
			payment_3.setVisible(false);
			payment_4.setVisible(false);
		} else if (ob == payment_2.btnP2Next) {
			payment_3.setVisible(true);
			payment_2.setVisible(false);
		} else if (ob == payment_2.btnP2Cancel) {// 결제4창 연결
			payment_2.setVisible(false);
		} else if (ob == payment_3.btnP3Before) {
			payment_1.setVisible(false);
			payment_4.setVisible(false);
			payment_3.setVisible(false);
			payment_2.setVisible(true);

		} else if (ob == payment_3.btnP3Cancel) {
			payment_3.setVisible(false);
		} 

		else if (ob == viewSalesCustomer.enroll) {
			enrollprocess();
			showAll();
		} else if (ob.equals(viewSalesCustomer.delete)) {
			int choose = JOptionPane.showConfirmDialog(viewSalesCustomer, "회원을 삭제하시겠습니까?", "회원 삭제",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choose == 0) {
				deleteprocess();
				showAll();
			}
		} else if (ob.equals(viewSalesDisuse.disuse)) {
			int choose = JOptionPane.showConfirmDialog(viewSalesDisuse, "폐기 처리 하시겠습니가?", "폐기 처리",
					JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
			if (choose == 0) {// 확인누르면
				deleteDisuseProcess();// 삭제 실행!
				setExp();
			}
		}

		// 재고 탭의 기능들
		else if (ob == stockbtn.stockevery) {
			stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);
			stockmonitor.showMon(stockdao.StockAll());
		} else if (ob == stockbtn.stockSearch) {
			stockpopupsearch.setVisible(true);
		} else if (ob == stockbtn.stockChg) {
			// 수정할거 골랐는지 유효성검사 getSelectedRow써야됨

			int tmp = stockmonitor.StockTable.convertRowIndexToModel(stockmonitor.StockTable.getSelectedRow());

			if (tmp < 0) {
				JOptionPane.showMessageDialog(this, "수정할 재고를 선택하세요.", "미선택 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

			String tmp1 = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
			String tmp2 = (String) stockmonitor.tmodel.getValueAt(tmp, 1);
			String tmp3 = (String) stockmonitor.tmodel.getValueAt(tmp, 2);
			String tmp4 = (String) stockmonitor.tmodel.getValueAt(tmp, 3);

			stockpopupchg.selecItem.setText("상품코드 : " + tmp1 + " 입고일 : " + tmp2 + " 상품명 : " + tmp3 + " 수량 : " + tmp4);
			stockpopupchg.setVisible(true);
		} else if (ob == stockbtn.stockIn) {
			stockpopupin.setVisible(true);
		} else if (ob == stockpopupsearch.SearchCode || ob == stockpopupsearch.sCbtn) {
			// 재고조회 코드로 검색하기
			// 유효성 검사 하기 곤란 products테이블에 존재하는 코드로만 검색가능함

			stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

			String scode = stockpopupsearch.SearchCode.getText();

			stockmonitor.showMon(stockdao.StockSearchCode(scode));

			stockpopupsearch.setVisible(false);
			stockpopupsearch.SearchCode.setText("");
			stockpopupsearch.SearchName.setText("");
		} else if (ob == stockpopupsearch.searchCancel) {
			// 재고조회 취소버튼
			stockpopupsearch.setVisible(false);
			stockpopupsearch.SearchCode.setText("");
			stockpopupsearch.SearchName.setText("");
		} else if (ob == stockpopupsearch.SearchName || ob == stockpopupsearch.sNbtn) {
			// 재고조회 이름으로 검색하기
			// 마찬가지로 존재하는 이름인가 아닌가
			stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

			String sname = stockpopupsearch.SearchName.getText();

			stockmonitor.showMon(stockdao.StockSearchName(sname));

			stockpopupsearch.setVisible(false);
			stockpopupsearch.SearchCode.setText("");
			stockpopupsearch.SearchName.setText("");
		} else if (ob == stockpopupchg.chgCancel) {
			// 재고수정 취소버튼
			stockpopupchg.setVisible(false);
			stockpopupchg.ChgCountTf.setText("");
			stockpopupchg.selecItem.setText("");
		} else if (ob == stockpopupchg.chgOk || ob == stockpopupchg.ChgCountTf) {
			// 재고수정 확인버튼, 쿼리문으로 수정한 후에 다시 수정된 버젼으로 테이블 새로 뿌림
			if (stockpopupchg.ChgCountTf.getText().trim().isEmpty()
					|| stockpopupchg.ChgCountTf.getText().trim().equals("")) {
				JOptionPane.showMessageDialog(this, "수량을 입력해주세요!", "입력 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			if (!isNumber(stockpopupchg.ChgCountTf.getText().trim())) {
				JOptionPane.showMessageDialog(this, "숫자만 입력해주세요!", "입력 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}
			int count = Integer.parseInt(stockpopupchg.ChgCountTf.getText().trim());

			int tmp = stockmonitor.StockTable.convertRowIndexToModel(stockmonitor.StockTable.getSelectedRow());
			String productCode = (String) stockmonitor.tmodel.getValueAt(tmp, 0);
			String inDate = (String) stockmonitor.tmodel.getValueAt(tmp, 1);

			stockdao.StockChange(count, productCode, inDate);

			stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

			stockmonitor.showMon(stockdao.StockAll());
			stockpopupchg.ChgCountTf.setText("");
			stockpopupchg.selecItem.setText("");
			stockpopupchg.setVisible(false);
		} else if (ob == stockpopupin.inCancel) {
			// 입고창 취소버튼 내용물 초기화하고 끄기
			stockpopupin.setVisible(false);
			stockpopupin.IndateTf.setText("");
			stockpopupin.PcodeTf.setText("");
			stockpopupin.PcntTf.setText("");
			stockpopupin.pcodeResult.setText("");
			stockpopupin.pcntResult.setText("");
			stockpopupin.indateResult.setText("");
		} else if (ob == stockpopupin.PcodeTf || ob == stockpopupin.pcodeInput) {
			// 코드 입력하면 입력한코드 옆에 표시
			stockpopupin.pcodeResult.setText(stockpopupin.PcodeTf.getText());

		} else if (ob == stockpopupin.IndateTf || ob == stockpopupin.indateInput) {
			// 입고일자 입력하면 입력한날짜 표시
			stockpopupin.indateResult.setText(stockpopupin.IndateTf.getText());

		} else if (ob == stockpopupin.PcntTf || ob == stockpopupin.pcntInput) {
			// 수량 입력하면 입력한 수량 표시
			stockpopupin.pcntResult.setText(stockpopupin.PcntTf.getText());

		} else if (ob == stockpopupin.inOk) {
			// 위의 3개에서 pcodeResult, indateResult, pcntResult로 인자값 받아서 in함
			if (isNumber(stockpopupin.pcntResult.getText())) {

				if (stockpopupin.pcodeResult.getText().isEmpty() || stockpopupin.pcodeResult.getText().trim().equals("")
						|| stockpopupin.indateResult.getText().trim().isEmpty()
						|| stockpopupin.indateResult.getText().trim().equals("")
						|| stockpopupin.pcntResult.getText().trim().isEmpty()
						|| stockpopupin.pcntResult.getText().trim().equals("")) {
					JOptionPane.showMessageDialog(this, "빈칸이 있습니다!", "등록 오류", JOptionPane.ERROR_MESSAGE);
					return;
				} else {
					String pcode = stockpopupin.pcodeResult.getText().trim();
					String indt = stockpopupin.indateResult.getText().trim();
					int pcnt = Integer.parseInt(stockpopupin.pcntResult.getText().trim());

					int rr = stockdao.StockIn(pcode, indt, pcnt);
					if (rr == 0) {
						JOptionPane.showMessageDialog(this, "같은날 입고된 내역이 있습니다!", "등록 오류", JOptionPane.ERROR_MESSAGE);
						return;
					}

					stockmonitor.clearRows(stockmonitor.tmodel.getRowCount(), stockmonitor.tmodel);

					stockmonitor.showMon(stockdao.StockAll());
					stockpopupin.IndateTf.setText("");
					stockpopupin.PcodeTf.setText("");
					stockpopupin.PcntTf.setText("");
					stockpopupin.pcodeResult.setText("");
					stockpopupin.pcntResult.setText("");
					stockpopupin.indateResult.setText("");
					stockpopupin.setVisible(false);
				}

			} else {
				JOptionPane.showMessageDialog(this, "수량은 숫자로 써주세요!", "등록 오류", JOptionPane.ERROR_MESSAGE);
				return;
			}

		}
		// 통계 기능의 버튼들
		else if (ob == statbtn.sBtnProduct) {
			monitor.show(pMonitor, "viewStatProduct");
		} else if (ob == statbtn.sBtnYear) {
			monitor.show(pMonitor, "viewStatYear");
		} else if (ob == statbtn.sBtnMonth) {
			monitor.show(pMonitor, "viewStatMonth");
		} else if (ob == statbtn.sBtnDay) {
			monitor.show(pMonitor, "viewStatDay");
		}
		// 계정기능 버튼들
		// 유저등록 콤보박스에서 권한 설정 시 권한 변경
		if (ob == admin.authority) {
			// 콤보박스 안에서 선택한 값의 문자열을 얻어옴
			String str = admin.authority.getSelectedItem().toString();
			if (str.equals("관리자")) {
				admin.auth = "T";
			} else if (str.equals("직원")) {
				admin.auth = "F";
			}
		}

		// 유저수정 콤보박스에서 권한 설정 시 권한 변경
		if (ob == admin.authorityUp) {
			String str = admin.authorityUp.getSelectedItem().toString();
			if (str.equals("관리자")) {
				admin.auth = "T";
			} else if (str.equals("직원")) {
				admin.auth = "F";
			}
		}

		// 회원등록이라고하죠.
		// 인연이라고하죠
		if (obb.equals("유저등록")) {
			admin.card.show(admin.pAcMonitor, "pRegister");
			admin.tfClear();
		}

		// 회원수정이라고 하죠.
		if (obb.equals("유저수정")) {
			admin.tfUClear();
			// 행의 번호를 뽑아옴.
			int numberRow = admin.table.getSelectedRow();

			if (admin.table.getSelectedRow() < 0) { // 테이블 말고 다른 곳을 클릭 했을 경우
				JOptionPane.showMessageDialog(this, "테이블을 클릭해주세요.");
			} else {
				String id = (String) admin.tm.getValueAt(numberRow, 2);
				admin.card.show(admin.pAcMonitor, "ppRegister");

				// 유저수정 아이디 텍스트필드값을 아이디로 고정
				admin.upuserTF.setText(id);
			}
		}

		// 유저를 지워보도록 하죠.
		if (obb.equals("유저삭제")) {
			UserDao userDao = new UserDao();

			if (admin.table.getSelectedRow() < 0) {
				JOptionPane.showMessageDialog(this, "테이블을 클릭해주세요.");

			} else {
				int x = JOptionPane.showConfirmDialog(this, "정말 삭제 하시겠습니까?", "삭제", JOptionPane.YES_NO_OPTION);
				// 삭제를 눌렀을 경우
				if (x == JOptionPane.OK_OPTION) {
					admin.deleteUser();
					try {
						userDao.userSelectAll(admin.tm);
					} catch (SQLException e1) {
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(this, "삭제를 취소 하였습니다.");
				}
			}
		}
		// 유저수정화면의 수정
		if (obb.equals("수정")) {
			admin.updateUser();
		}

		// 유저등록화면의 확인
		if (obb.equals("확인")) {

			admin.insertUser(); // 유효성 검사

			PosDto re = admin.getViewData(); // 실제 넘어간 데이터 userDto에 저장.
			admin.userDao = new UserDao(); // userDao 객체 생성

			if (admin.result) { // 데이터가 넘어 갔다면.
				try {
					admin.userDao.getMemberList(); // select 문 실행.
					admin.userDao.insertMember(re); // insert문 실행.
					admin.userDao.userSelectAll(admin.tm);
				} catch (SQLException e1) {
					System.out.println("등록 실패");
					e1.printStackTrace();
				}
			} else {
				admin.result = false;
				return;
			}
		}

		// 취소버튼을 누르는 동시에 다시 테이블화면으로.
		if (obb.equals("취소")) {
			System.out.println("취소버튼등록");
			admin.card.show(admin.pAcMonitor, "pTable");
		}

		// 말그대로 로그아웃 로그인 화면으로 넘어감.
		if (obb.equals("로그아웃")) {
			this.setVisible(false);
			admin.forcePos = new ForcePos();
			admin.forcePos.setVisible(true);
		}

	}// 액션퍼폼끝

	// <테이블 내용 가운데 정렬> 메소드
	public static void tableCellCenter(JTable t) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tcm = t.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		// 전체 열에 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}

	private static boolean isNumber(String str) {
		boolean flag = true;
		int len = str.length();
		for (int i = 0; i < len; i++) {
			int num = str.charAt(i) - 48;
			if (num < 0 || num > 9) {
				flag = false;
				break;
			}
		}

		return flag;
	}

	// 테이블을 클릭했을경우 이벤트 발생
	MouseAdapter ms = new MouseAdapter() {
		@Override
		public void mouseClicked(MouseEvent e) {
			super.mouseClicked(e);
			admin.table = (JTable) e.getComponent();
			admin.tm = (DefaultTableModel) admin.table.getModel();
		}
	};

	@Override
	public void run() {
		while (true) {
			SimpleDateFormat df = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			Date d = new Date();
			String str = df.format(d);

			try {
				thread.sleep(1000);
				dateLabel.setText(str);
			} catch (InterruptedException e) {

				e.printStackTrace();
			}
//	         System.out.println("현재시간 :: " + str);
//	         idLabel.setText();
//	         dateLabel.setText(str);
		}
	}

	private void deleteDisuseProcess() {
		int row = viewSalesDisuse.tableResult.getSelectedRow();// 선택된 행을 담아
		if (row == -1) {
			JOptionPane.showMessageDialog(this, "폐기할 상품을 선택해주세요.", "상품 폐기 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

		// 상품코드랑 입고날짜를 인자값으로 받아서 (재고에 입고날짜만 다른 같은상품들은 상품코드가 같아도 따로 나와야함)
		String productCode = null;//
		productCode = String.valueOf((viewSalesDisuse.tmodel.getValueAt(row, 0)));
		String inDate = null;
		//
		inDate = String.valueOf((viewSalesDisuse.tmodel.getValueAt(row, 1)));
		//
		int result = salesDao.deleteDisuse(productCode, inDate);
		if (result > 0) {
			showAllDisuse5();// 유통기한 5일남은 것들 리스트에 보여줘
		}
	}

	private void showAllDisuse5() {

		SalesDao salesDao = new SalesDao();
		SalesDao.clearRows(viewSalesDisuse.tmodel.getRowCount(), viewSalesDisuse.tmodel);
//		이 데이터 테이블의 행의 숫자를 가져온다.    
		Vector<PosDto> results = new Vector<PosDto>();// results 에 차곡차곡 담을꺼야.
		results = salesDao.showAllDisuse5();// 모든조회를 결과에 담는다.

		int size = results.size();

		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>(); // 행
			rows.addElement(results.get(i).getProductCode());
			rows.addElement(results.get(i).getInDate());
			rows.addElement(results.get(i).getProductName());
			rows.addElement(Integer.toString(results.get(i).getPrice()));
			rows.addElement(results.get(i).getRealExp());
			rows.addElement(Integer.toString(results.get(i).getVolume()));
			viewSalesDisuse.tmodel.addRow(rows);
		}
	}

	private void refundProcess() {
		String sellId = null;
		sellId = dealCancel.Sell_id.getText().trim();

		if (sellId.contentEquals("")) {
			JOptionPane.showMessageDialog(dealCancel, "거래 번호를 입력해주세요.", "환불 조회 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

//		receipt.setVisible(true);
//		receipt.refundDetail.append("취소영수증/n" + "판매코드 : \t" );

		Vector<PosDto> list = new Vector<PosDto>();
		list = salesDao.selectUpdateStock(sellId);

		System.out.println(list.toString());

		if (list.isEmpty()) {
			JOptionPane.showMessageDialog(dealCancel, "거래 번호가 올바르지 않습니다.", "환불 조회 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

		salesDao.updateMembership(sellId); // 멤버십 : 멤버십 포인트 빼기
		salesDao.updateMoney(sellId); // 정산 update : 현금 결재액만큼 빼기
		salesDao.updateStock(list); // 재고 update : 구매 수량만큼 재고 수량에 더하기
		salesDao.deletehistory_d(sellId);
		salesDao.deletehisotry(sellId);

		JOptionPane.showMessageDialog(dealCancel, "환불 처리가 완료되었습니다.", "환불 완료", JOptionPane.INFORMATION_MESSAGE);
		dealCancel.dispose();
	}

	private void deleteprocess() {
		int row = viewSalesCustomer.tableResult.getSelectedRow();

		if (row < 0) {
			JOptionPane.showMessageDialog(this, "삭제할 회원을 선택해주세요.", "회원 삭제 오류", JOptionPane.WARNING_MESSAGE);
			return;
		}

		String phone = null;
		phone = String.valueOf((viewSalesCustomer.tmodel.getValueAt(row, 0)));
		salesDao.delete(phone);
	}

	// 1) <상품보류 설정> 메소드
	// : 현재 목록의 상품들을 저장한 Vector<PosDto>를 리턴
	private Vector<PosDto> productsHoldProcess() {

		Vector<PosDto> currentList = new Vector<PosDto>();

		int rowsNum = viewSalesInput.table.getRowCount();

		for (int i = 0; i < rowsNum; i++) {
			PosDto row = new PosDto();
			// PosDto 객체에 현재 목록의 상품 한 개당 정보를 담음
			row.setListNum(Integer.parseInt((String) viewSalesInput.model.getValueAt(i, 0)));
			row.setProductCode((String) viewSalesInput.model.getValueAt(i, 1));
			row.setProductName((String) viewSalesInput.model.getValueAt(i, 2));
			row.setPrice(Integer.parseInt((String) viewSalesInput.model.getValueAt(i, 3)));
			row.setProductSellCount(String.valueOf(viewSalesInput.model.getValueAt(i, 4)));
			row.setProductSellPrice(Integer.parseInt(String.valueOf(viewSalesInput.model.getValueAt(i, 5))));
			row.setInDate(String.valueOf(viewSalesInput.model.getValueAt(i, 6)));

			// 인자로 받은 Vector<PosDto> 객체에 저장
			currentList.add(row);
		}

		// 저장 정보 리턴
		return currentList;

	}

	// 2) <상품보류 취소> 메소드
	// : 이전에 저장한 상품 목록을 다시 테이블에 추가
	private void productsHoldCancelProcess(Vector<PosDto> beforeList) {

		// 보류해놨던 상품 목록 행 개수
		int bfRowsNum = beforeList.size();

		for (int i = 0; i < bfRowsNum; i++) {
			Vector<String> plusRows = new Vector<String>();

			// 보류 목록에, 현재 목록 상품과 상품코드가 겹치는 상품이 있는 경우
			int overlapRow = checkOverlap(beforeList.get(i).getProductCode());
			if (overlapRow > -1) {
				// 현재 목록에 있는 해당 상품의 수량, 총 가격만 변경
				// 수량 +1
				viewSalesInput.model
						.setValueAt(Integer.valueOf(String.valueOf(viewSalesInput.model.getValueAt(overlapRow, 4)))
								+ Integer.valueOf(beforeList.get(i).getProductSellCount()), overlapRow, 4);
				// 총 가격 변경
				viewSalesInput.model.setValueAt(
						Integer.valueOf(String.valueOf(viewSalesInput.model.getValueAt(overlapRow, 3)))
								* Integer.valueOf(String.valueOf(viewSalesInput.model.getValueAt(overlapRow, 4))),
						overlapRow, 5);
				// 해당 상품은 추가 목록에 넣지 않고, 다음 i로 넘어간다
				continue;
			}
			System.out.println(i);
			plusRows.addElement(Integer.toString(beforeList.get(i).getListNum()));
			plusRows.addElement(beforeList.get(i).getProductCode());
			plusRows.addElement(beforeList.get(i).getProductName());
			plusRows.addElement(Integer.toString(beforeList.get(i).getPrice()));
			plusRows.addElement(beforeList.get(i).getProductSellCount());
			plusRows.addElement(Integer.toString(beforeList.get(i).getProductSellPrice()));
			plusRows.addElement(beforeList.get(i).getInDate());

			viewSalesInput.model.addRow(plusRows);
		}

	}

	// 3) <상품 보류 취소 시, 중복 여부 체크> 메소드
	// : 상품 보류 취소 시, 현재 상품 목록과 겹치는 상품 코드가 있는지 체크 후, 해당 행 num을 반환
	public int checkOverlap(String identifier) {
		System.out.println("check" + identifier);

		// 중복 행 저장 변수
		int num = -1;

		// 현재 상품 목록 개수 얻어옴
		int rows = viewSalesInput.model.getRowCount();
		// 중복 상품 여부 체크
		for (int i = 0; i < rows; i++) {
			if (viewSalesInput.model.getValueAt(i, 1).equals(identifier)) {
				num = i;
				return num;
			}
		}
		return num;
	}

	// 4) <테이블 지우기> 메소드
	public static void clearRows(int rowSize, DefaultTableModel dtm) {
		if (rowSize > 0) {
			for (int i = rowSize - 1; i >= 0; i--) {
				dtm.removeRow(i);
			}
		}
	}
//		*******************************************************************************************************************

	private void enrollprocess() {
		SalesDao.clearRows(viewSalesCustomer.tmodel.getRowCount(), viewSalesCustomer.tmodel);
		String name;
		String cellphone;
		name = viewSalesCustomer.name.getText().trim();
		cellphone = viewSalesCustomer.cellphone.getText().trim();
		if (!name.equals("") && !cellphone.equals("")) {
			salesDao.register(name, cellphone);
			viewSalesCustomer.name.setText("");
			viewSalesCustomer.cellphone.setText("");
			JOptionPane.showMessageDialog(viewSalesCustomer, "새로운 회원이 등록되었습니다.", "회원 등록 완료",
					JOptionPane.INFORMATION_MESSAGE);

		} else {
			JOptionPane.showMessageDialog(viewSalesCustomer, "회원 이름과 전화번호를 입력해주세요", "회원 등록 오류",
					JOptionPane.INFORMATION_MESSAGE);
		}

	}

	private void searchNameProcess() {

		SalesDao salesDao = new SalesDao();
		SalesDao.clearRows(viewSalesCustomer.tmodel.getRowCount(), viewSalesCustomer.tmodel);
		Vector<PosDto> results = new Vector<PosDto>();
		String name;
		name = viewSalesCustomer.name.getText().trim();
		results = salesDao.search(name);
		int size = results.size();
		if (size == 0) {
			JOptionPane.showMessageDialog(viewSalesCustomer, "존재하지 않는 회원입니다.", "회원 조회 오류",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>(); // 행
			rows.addElement(results.get(i).getMembershipId());
			rows.addElement(results.get(i).getMemberName());
			rows.addElement(results.get(i).getPhone());
			rows.addElement(Integer.toString(results.get(i).getPoint()));
			viewSalesCustomer.tmodel.addRow(rows);
		}
	}

	private void searchNameProcess1() {

		SalesDao salesDao = new SalesDao();
		SalesDao.clearRows(viewSalesCustomer.tmodel.getRowCount(), viewSalesCustomer.tmodel);
		Vector<PosDto> results = new Vector<PosDto>();
		String cellphone;
		cellphone = viewSalesCustomer.cellphone.getText().trim();
		results = salesDao.search1(cellphone);
		int size = results.size();
		if (size == 0) {
			JOptionPane.showMessageDialog(viewSalesCustomer, "존재하지 않는 회원입니다.", "회원 조회 오류",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>(); // 행
			rows.addElement(results.get(i).getMembershipId());
			rows.addElement(results.get(i).getMemberName());
			rows.addElement(results.get(i).getPhone());
			rows.addElement(Integer.toString(results.get(i).getPoint()));
			viewSalesCustomer.tmodel.addRow(rows);
		}
	}

	private void searchNameProcess2() {
		SalesDao salesDao = new SalesDao();
		SalesDao.clearRows(viewSalesCustomer.tmodel.getRowCount(), viewSalesCustomer.tmodel);
		Vector<PosDto> results = new Vector<PosDto>();
		String name;
		String cellphone;

		name = viewSalesCustomer.name.getText().trim();
		cellphone = viewSalesCustomer.cellphone.getText().trim();
		results = salesDao.search2(name, cellphone);
		int size = results.size();
		if (size == 0) {
			JOptionPane.showMessageDialog(viewSalesCustomer, "존재하지 않는 회원입니다.", "회원 조회 오류",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>(); // 행
			rows.addElement(results.get(i).getMembershipId());
			rows.addElement(results.get(i).getMemberName());
			rows.addElement(results.get(i).getPhone());
			rows.addElement(Integer.toString(results.get(i).getPoint()));
			viewSalesCustomer.tmodel.addRow(rows);
		}
	}

	private void showAll() {

		SalesDao salesDao = new SalesDao();
		SalesDao.clearRows(viewSalesCustomer.tmodel.getRowCount(), viewSalesCustomer.tmodel);
		Vector<PosDto> results = new Vector<PosDto>();
		results = salesDao.searchAll();

		int size = results.size();

		for (int i = 0; i < size; i++) {
			Vector<String> rows = new Vector<String>(); // 행
			rows.addElement(results.get(i).getMembershipId());
			rows.addElement(results.get(i).getMemberName());
			rows.addElement(results.get(i).getPhone());
			rows.addElement(Integer.toString(results.get(i).getPoint()));
			viewSalesCustomer.tmodel.addRow(rows);
		}

	}
	
	public void setExp() {
		posdto = new PosDto();
		stockdao.expCount();
		notice.setText(ForcePos.expName.getExpName() + " 상품은 유통기한이 " + ForcePos.exp.getExp() + "시간 만큼 남았습니다.");
	}

}// 클래스 끝
