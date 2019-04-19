package com.kitri.pos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EtchedBorder;
import com.kitri.pos.db.*;
import com.kitri.pos.stock.StockDao;
import com.kitri.pos.account.*;

public class ForcePos extends JFrame implements ActionListener {

	
	public static PosDto usercodeDto = new PosDto();
	public static PosDto expName = new PosDto();
	public static PosDto exp = new PosDto();
	public static PosDto selldto = new PosDto();
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	final JTextField userTf;
	final JTextField passTf;
	

	private JButton exitB;
	private JButton loginB;
	public MainFrame mainFrame = new MainFrame(); // 메인 프레임
	public StockDao stockDao = new StockDao();
	public UserDao userDao;
	public Administrator administrator;

	// 생성자
	public ForcePos() {
		super("ForcePos");

		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 15, 1326, 753);
		contentPane = new JPanel();
		contentPane.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 배경화면 패널
		JPanel background = new JPanel() {
			private static final long serialVersionUID = 1L;

			@Override
			public void paintComponent(Graphics g) {
				Dimension d = getSize(); // 패널의 크기를 얻어옴
				ImageIcon image = new ImageIcon(
						"E:\\javadata\\workspace\\javase\\POS\\src\\image\\loginBackground.png"); // 이미지얻어옴.
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null); // Jpanel의 크기에 맞게 이미지를 그린다.
				setOpaque(false); // 배경을 투명하게 설정해줌
				super.paintComponent(g);
			}
		};
		background.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		background.setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		background.setOpaque(false);

		background.setBounds(0, 0, 1320, 725);
		contentPane.add(background);
		background.setLayout(null);

		JPanel title = new JPanel() {
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon image = new ImageIcon("E:\\javadata\\workspace\\javase\\POS\\src\\image\\LoginTitle.png");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		title.setOpaque(false);
		title.setBounds(130, 144, 529, 199);
		background.add(title);
		title.setLayout(new BorderLayout(0, 0));

		// 유저 패널
		JPanel userP = new JPanel();
		userP.setOpaque(false);
		userP.setBounds(211, 351, 369, 87);
		background.add(userP);
		userP.setLayout(new BorderLayout(0, 0));

		// 유저 텍스트필드
		userTf = new JTextField(6) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon image = new ImageIcon("E:\\javadata\\workspace\\javase\\POS\\src\\image\\idbg.png");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			};
		};

		userTf.setToolTipText("ID");
		userTf.setBorder(null);
		userTf.setBackground(new Color(242, 242, 242));// ****************************************************************
		userP.add(userTf, BorderLayout.CENTER);
		userTf.setHorizontalAlignment(SwingConstants.CENTER);
		userTf.setFont(new Font("맑은 고딕", Font.ITALIC, 40));

		// 아이디를 입력받는
		userTf.setText("");
		userTf.setColumns(10);

		// 이벤트 리스너 등록
		userTf.addActionListener(this);

		// 패스워드 패널
		JPanel passP = new JPanel();
		passP.setBounds(211, 457, 369, 87);
		background.add(passP);
		passP.setLayout(new BorderLayout(0, 0));

		// 비밀번호텍스트필드
		passTf = new JPasswordField(6) {
			private static final long serialVersionUID = 1L;

			@Override
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon image = new ImageIcon("E:\\javadata\\workspace\\javase\\POS\\src\\image\\pwbg.png");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			};
		};
		passTf.setToolTipText("PASSWORD");
		passTf.setBorder(null);
		passTf.setBackground(new Color(242, 242, 242)); // ****************************************************************
		passP.add(passTf, BorderLayout.CENTER);
		passTf.setHorizontalAlignment(SwingConstants.CENTER);
		passTf.setFont(new Font("맑은 고딕", Font.ITALIC, 40));

		// 비밀번호를 입력받는
		passTf.setText("");
		passTf.setColumns(10);
		passTf.addActionListener(this);
		passTf.addActionListener(this);

		// 로그인 버튼
		// 둥글게 만드는 버튼 클래스 객체 생성후 대입
		loginB = new JButton(new ImageIcon(ForcePos.class.getResource("/image/loginButton.png")));
		loginB.setLocation(693, 243);
		background.add(loginB);

		loginB.setBorderPainted(false);
		loginB.setContentAreaFilled(false);
		loginB.setFocusPainted(false);
		loginB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginB.setSize(new Dimension(200, 200));
		loginB.setIconTextGap(1);

		// 종료 버튼
		// 둥글게 만드는 버튼 클래스 객체 생성후 대입
		exitB = new JButton(new ImageIcon(ForcePos.class.getResource("/image/exit.png")));
		exitB.setPreferredSize(new Dimension(100, 100));
		exitB.setBounds(1170, 572, 160, 160);
		background.add(exitB);
		exitB.setBorderPainted(false);
		exitB.setContentAreaFilled(false);
		exitB.setFocusPainted(false);

		exitB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitB.setIconTextGap(1);
		exitB.setMinimumSize(new Dimension(73, 23));
		exitB.setMaximumSize(new Dimension(73, 23));
		exitB.addActionListener(this);
		loginB.addActionListener(this);
		setResizable(false);

	}

	

	

// 아이디 비밀번호 유효성 검사
	public boolean isLoginCheck() {
		boolean loginCheck = false;

		if (userTf.getText().trim().isEmpty() || passTf.getText().trim().isEmpty()) {
			JOptionPane.showMessageDialog(this, "ID와 비밀번호를 입력 바랍니다.", "입력오류", JOptionPane.ERROR_MESSAGE);
		} else {
			loginCheck = true;
		}
		return loginCheck;
	}

	// 메인프레임을 보여줘
	public void showFrame() {

		mainFrame.setVisible(true);

		String auth = userDao.userDto.getAuthority().trim();
//		System.out.println(auth);

		if (auth.equals("F")) {
			mainFrame.mBtnAccount.setEnabled(false);
			mainFrame.mBtnStat.setEnabled(false);
			
		} else if (auth.equals("T")) {
			mainFrame.mBtnAccount.setEnabled(true);
			mainFrame.mBtnStat.setEnabled(true);
		}

		this.setVisible(false);

	}

	// 실행
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ForcePos forcePos = new ForcePos();
					forcePos.setVisible(true);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		Object ob = e.getSource();

	

		if (ob == loginB || ob == passTf) {
			String id = userTf.getText().trim();
			String pw = passTf.getText().trim();
			if (isLoginCheck()) {
				passTf.requestFocus();
			}
			// DB에 있는 아이디와 비밀번호가 일치할 경우
			userDao = new UserDao();

			if (userDao.pass(id, pw) == true) {
				JOptionPane.showMessageDialog(this, "'" + id + "'" + " 환영합니다.");
				showFrame();
				mainFrame.mBtnSale.setBackground(new Color(255, 69, 0));
				mainFrame.monitor.show(mainFrame.pMonitor, "ViewSalesInput");
				mainFrame.btn.show(mainFrame.pFBtn, "salebtn");
				mainFrame.setExp();
				
			} else {
				JOptionPane.showMessageDialog(this, "올바른 ID, PASSWORD 입력바랍니다.", "입력오류", JOptionPane.WARNING_MESSAGE);
			}
		}

		// 종료 버튼
		if (ob == exitB) {
			System.exit(0);
		}
	}
}