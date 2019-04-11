package com.kitri.pos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;

import com.sun.org.apache.bcel.internal.classfile.PMGClass;

import javax.swing.border.EmptyBorder;

import java.io.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.border.LineBorder;

public class Administrator extends JFrame implements ActionListener, ItemListener {

	private JPanel contentPane;
	private JTextField userTf; //아이디 입력
	private JTextField passTf; //패스워드 입력
	private JTextField nameTf; //이름 입력
	private JTextField notice; //상태창
	private ForcePos frame; //메인프레임
	private UserDto userdto; //Dto
	private UserDao userdao; //Dao
	private UserList userList; //유저리스트
	private JComboBox authority; // 권한
//	private JRadioButton job1, job2; // 콤보박스안에 직원, 관리자
	String arrJob[] = { "직원", "관리자" };
	private String auth = "F"; //직원 권한 고정
	private DefaultTableModel tm;
	private JTable table;
	private boolean result = false; // 인서트 결과값 저장
	JPanel pMonitor;

	// 회원등록 패널
	JPanel pRegister = new JPanel();
	// 회원테이블 패널
	JPanel pTable = new JPanel();

	CardLayout card = new CardLayout();

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {

				try {
					Administrator administrator = new Administrator();

					administrator.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	public void showFrameTest() {
//		frame1.setVisible(true);
//		frame.dispose();
//	}
	
	public static void tableCellCenter(JTable table) {
		
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // 디폴트테이블셀렌더러를 생성
		
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // 렌더러의 가로정렬을 CENTER로

		TableColumnModel tcm = table.getColumnModel(); // 정렬할 테이블의 컬럼모델을 가져옴

		// 전체 열에 지정
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}

	//보여줘
	public void showFrame() {
		frame = new ForcePos();
		this.setVisible(false);
		frame.setVisible(true);
	}

// 전역변수의 테이블
//	public Administrator(JTable table) {
//		this.table = table;
//	}

	// 수정, 삭제용 생성자
	public Administrator(String id, UserList userList) {

		this.userList = userList;
		UserDao userDao = new UserDao();

	}

	public Administrator() {

		setFont(new Font("맑은 고딕", Font.BOLD, 20));
		setTitle("Force.pos");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 15, 1326, 753);
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

		String colName[] = { "유저코드", "유저이름", "분류" };
		Object data[][] = { { "1", "개나리", "주간1" }, { "2", "노오란", "야간1" }, { "3", "꽃그늘", "주간2" },
				{ "4", "최아래", "야간2" } };

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

		
		//유저등록 버튼
		JButton userInsert = new JButton("\uC720\uC800\uB4F1\uB85D");
		userInsert.setForeground(new Color(255, 255, 255));
		userInsert.setBackground(new Color(0, 0, 128));
		userInsert.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userInsert.setBounds(0, 10, 164, 120);
		pSellFunction.add(userInsert);

		//유저수정 버튼
		JButton userUpdate = new JButton("\uC720\uC800\uC218\uC815");
		userUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		userUpdate.setBackground(new Color(100, 149, 237));
		userUpdate.setForeground(new Color(255, 255, 255));
		userUpdate.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userUpdate.setBounds(0, 130, 164, 120);
		pSellFunction.add(userUpdate);

		
		//유저삭제 버튼
		JButton userDelete = new JButton("\uC720\uC800\uC0AD\uC81C");
		userDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		userDelete.setBackground(new Color(0, 0, 128));
		userDelete.setForeground(new Color(255, 255, 255));
		userDelete.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		userDelete.setBounds(0, 260, 164, 120);
		pSellFunction.add(userDelete);

		JButton sBtnPdChange = new JButton("\uCD9C\uACB0");
		sBtnPdChange.setBackground(new Color(100, 149, 237));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 390, 164, 120);
		pSellFunction.add(sBtnPdChange);

		JButton logout = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logout.setBackground(new Color(255, 69, 0));
		logout.setForeground(new Color(255, 255, 255));
		logout.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		logout.setBounds(0, 520, 164, 120);
		pSellFunction.add(logout);

		pMonitor = new JPanel();
		pMonitor.setSize(new Dimension(1144, 533));
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1144, 533);
		contentPane.add(pMonitor);

		pRegister.setBackground(SystemColor.desktop);
		pRegister.setBorder(new LineBorder(new Color(0, 0, 0)));
		pRegister.setLayout(null);

		JPanel prInput = new JPanel();
		prInput.setBorder(new LineBorder(new Color(0, 0, 0)));
		prInput.setBounds(451, 10, 356, 513);
		prInput.setLayout(new GridLayout(11, 1, 0, 0));
		pRegister.add(prInput);

		tm = new DefaultTableModel(data, colName);

		pTable.setBounds(0, 0, 10, 10);
		pMonitor.add(pTable);
		table = new JTable(tm);

		table.setRowHeight(60);
		tableCellCenter(table);
		table.getColumn("유저코드").setPreferredWidth(5);
		pTable.setLayout(null);
		JScrollPane scrollPane = new JScrollPane(table);
		pTable.add(scrollPane);
		scrollPane.setBounds(0, 5, 1144, 528);

		//카드레이아웃담당.
		pMonitor.setLayout(card);
		pMonitor.add("pTable", pTable);
		pMonitor.add("pRegister", pRegister);
		card.show(pMonitor, "pTable");

		JPanel panel = new JPanel();
		prInput.add(panel);

//		pRegister.setBackground(SystemColor.desktop);
//		pRegister.setBorder(new LineBorder(new Color(0, 0, 0)));
//		pMonitor.add(pRegister, "name_39053666565740");
//		pRegister.setLayout(null);
//		JPanel prInput = new JPanel();
//		prInput.setBorder(new LineBorder(new Color(0, 0, 0)));
//		prInput.setBounds(456, 10, 356, 513);
//		pRegister.add(prInput);
//		prInput.setLayout(new GridLayout(9, 1, 0, 0));

		JLabel userIdLabel = new JLabel("\uC720\uC800ID");
		userIdLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(userIdLabel);

		//회원등록 - 유저아이디입력
		userTf = new JTextField();
		prInput.add(userTf);
		userTf.setColumns(10);
		JLabel passWLabel_1 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		passWLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(passWLabel_1);
		
		//회원등록  - 패스워드입력
		passTf = new JTextField();
		prInput.add(passTf);
		passTf.setColumns(10);
		JLabel lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(lblNewLabel_2);

		
		//회원등록 - 이름입력
		nameTf = new JTextField();
		prInput.add(nameTf);
		nameTf.setColumns(10);
		JLabel lblNewLabel_3 = new JLabel("\uAD8C\uD55C");
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(lblNewLabel_3);

		// 권한배열
		authority = new JComboBox(arrJob);
		prInput.add(authority);
//		authority.getName();
//		System.out.println(authority);
		authority.getSelectedItem();

		
		//아래버튼패널
		JPanel pB = new JPanel();
		prInput.add(pB);
		pB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		//확인버튼
		JButton ok = new JButton("\uD655\uC778");
		ok.setMargin(new Insets(2, 20, 2, 20));
		ok.setHorizontalTextPosition(SwingConstants.CENTER);
		ok.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ok.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		ok.setAlignmentX(Component.CENTER_ALIGNMENT);
		pB.add(ok);

		//취소버튼
		JButton cancel = new JButton("\uCDE8\uC18C");
		cancel.setMargin(new Insets(2, 20, 2, 20));
		cancel.setBackground(new Color(255, 99, 71));
		cancel.setHorizontalTextPosition(SwingConstants.CENTER);
		cancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pB.add(cancel);

		//이벤트 리스너 등록
		userInsert.addActionListener(this);
		userDelete.addActionListener(this);
		logout.addActionListener(this);
		ok.addActionListener(this);
		cancel.addActionListener(this);
//		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
//		celAlignCenter.setHorizontalAlignment(table);
	}

	//회원등록창에 입력된 값을 보여줘
	public UserDto getViewData() {

//		BufferedReader in;
//		in = new BufferedReader(new InputStreamReader(System.in));
		UserDto userdto = new UserDto();

		try {

//			System.out.print("유저코드 입력 바람.");
//			int user_code = Integer.parseInt(in.readLine());
//			System.out.println("패스워드 입력바람");
			userdto.setPw(passTf.getText());
//			System.out.println("아이디 입력바람");
			userdto.setId(userTf.getText());
//			System.out.println("권한 입력바람");
			userdto.setAuthority(auth); // string
			userdto.setName(nameTf.getText());
//			System.out.println("이름 입력바람");

		} catch (NumberFormatException e) {
			System.out.println("입력실패");
			e.printStackTrace();
		}

		// 권한설정 시 공백을 주고 선택됬을 때 값을 얻어온다.

//		dto.setUserCode(userCode);

		return userdto;

	}

//	public String isSelect() {
//
//		Object ob = authority.getSelectedItem();
//
//		if (ob.equals("직원")) {
//			auth = "F";
//		} else {
//			auth = "T";
//		}
//
//		return auth;
//	
//		char ch;
//		
//		if (job2.isSelected()) {
//			ch = auth.charAt(0);
//			auth = "T";
//		} else if(job1.isSelected()) {
//			ch = auth.charAt(0);
//			auth = "F";
//		}
//		
//		return auth;

//	}

	// 회원등록유효성검사.
	private boolean insertUser() {

		boolean result = true;

		UserDto userDto = getViewData();
		UserDao userDao = new UserDao();

//		boolean result = userDao.insertMember(userDto);

		if (result) {
			JOptionPane.showMessageDialog(this, "등록이 완료되었습니다. 감사합니다.");
			dispose();
		} else {
			JOptionPane.showMessageDialog(this, "등록이 실패되었습니다.");
			result = false;
		}
		return result;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object ob = e.getActionCommand();

		// 회원등록이라고하죠.
		if (ob.equals("유저등록")) {
			card.show(pMonitor, "pRegister");
//			userdao = new UserDao();
//			System.out.println("유저등록버튼");
//			userdto = new UserDto();
//			userdao.insertMember(userdto);  
//			DefaultTableModel tm = (DefaultTableModel) table.getModel();
//			String add[] = {"1", "김의연", "주간"};
//			tm.addRow(add);
//			UserDao userdao = new UserDao();
//			userdao.register(posdto);	
//			getViewData();

		}

		if (ob.equals("유저수정")) {
			
		}

		// 유저를 지워보도록 하죠
		if (ob.equals("유저삭제")) {
			// 테이블에서 내가 선택한 행번호
			int number = table.getSelectedRow();
//			System.out.println(number);
			DefaultTableModel tm = (DefaultTableModel) table.getModel();
			if (number >= 0 && number < table.getRowCount()) {
				tm.removeRow(number);
			}
		}

		// 확인을 누르면 유저등록창
		if (ob.equals("확인")) {
//			System.out.println("확인등록용");
			insertUser();
			UserDto result = getViewData();
			UserDao userdao = new UserDao();
			userdao.insertMember(result);
		}

		// 취소버튼을 누르는 동시에 다시 테이블화면으로.
		if (ob.equals("취소")) {
//			System.out.println("취소버튼등록");
			card.show(pMonitor, "pTable");
		}

		// 말그대로 로그아웃 메인프레임으로 넘어감.
		if (ob.equals("로그아웃")) {
			this.setVisible(false);
			ForcePos forcePos = new ForcePos();
			forcePos.setVisible(true);
		}

	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		Object ob = e.getItem();

		System.out.println(ob);

	}
}
