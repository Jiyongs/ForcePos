package com.kitri.pos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.EtchedBorder;

public class ForcePos extends JFrame implements ActionListener {

	private JPanel contentPane;
	final JTextField userTf;
	final JTextField passTf;
	private RoundedButton rb_1;
	private JButton exitB;
	private JButton loginB;
	private MainFrame main; // 메인 프레임
	private boolean loginCheck; // 아이디와 패스워드가 일치하는지 확인해주는 필드
//	int count = 0;

	/*
	 * private String img;
	 * 
	 * public void inputIcon() { mageIcon 변경할아이콘 = new ImageIcon("이미지.png"); Image
	 * 변경할이미지 = 변경할아이콘.getImage(); //ImageIcon을 Image로 변환. Image 변경된이미지 =
	 * 변경할이미지.getScaledInstance(가로, 세로, java.awt.Image.SCALE_SMOOTH); ImageIcon
	 * 변경된아이콘 = new ImageIcon(변경된이미지); //Image로 ImageIcon 생성 img =
	 * System.getProperty("user.dir"); System.out.println(img); ImageIcon icon = new
	 * ImageIcon("userIcon.png"); Image image = icon.getImage(); Image image2 =
	 * image.getScaledInstance(250, 250, Image.SCALE_AREA_AVERAGING); ImageIcon
	 * icon2 = new ImageIcon(image2); System.out.println(icon2);
	 * lblNewLabel.setIcon(new ImageIcon()); 디렉토리 경로에 파일이 있는지 확인하는 구문 File f = new
	 * File("D:\\Workspace\\Project\\userIcon.png"); System.out.println(f.exists());
	 * Toolkit tk = Toolkit.getDefaultToolkit(); image =
	 * tk.getImage("D:\\Workspace\\Project\\userIcon.png");
	 * 
	 * jpanel = new JPanel() {
	 * 
	 * @Override public void paint(Graphics g) { g.drawImage(image, 0, 0, null);
	 * super.paintComponents(g); } };
	 * 
	 * add("Center", jpanel); add("North", new JLabel("패널에 이미지 붙이기"));
	 * setBounds(200, 200, 300, 300); contentPane.add(jpanel); setVisible(true);
	 * 
	 * }
	 */

	// 생성자
	public ForcePos() {
		super("ForcePos");

		getContentPane().setLayout(null);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 15, 1326, 753);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// 배경화면 패널
		JPanel background = new JPanel() {

			@Override
			public void paintComponent(Graphics g) {
				Dimension d = getSize(); // 패널의 크기를 얻어옴
				ImageIcon image = new ImageIcon("E:\\javadata\\Workspace\\javase\\pos\\src\\image\\Background.png"); // 이미지얻어옴.
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null); // Jpanel의 크기에 맞게 이미지를 그린다.
				setOpaque(false); // 배경을 투명하게 설정해줌
				super.paintComponent(g);

			}
		};
		background.setOpaque(false);

		background.setBounds(12, 10, 1326, 753);
		contentPane.add(background);
		background.setLayout(null);

		JPanel title = new JPanel();
		title.setOpaque(false);
		title.setBounds(12, 10, 1278, 83);
		background.add(title);
		title.setLayout(new BorderLayout(0, 0));

		JLabel Forcepos = new JLabel("Forcepos");
		Forcepos.setHorizontalTextPosition(SwingConstants.CENTER);
		Forcepos.setForeground(new Color(255, 0, 0));
		Forcepos.setHorizontalAlignment(SwingConstants.CENTER);
		Forcepos.setFont(new Font("돋움체", Font.BOLD, 70));
		title.add(Forcepos, BorderLayout.CENTER);

		// 로그인 화면 패널 설정
		JPanel user = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon image = new ImageIcon("E:\\javadata\\Workspace\\javase\\pos\\src\\image\\Usericon.png");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			}
		};
		user.setBounds(328, 176, 153, 165);
		background.add(user);

		// 유저 패널
		JPanel userP = new JPanel();
		userP.setOpaque(false);
		userP.setBounds(497, 176, 475, 165);
		background.add(userP);
		userP.setLayout(new BorderLayout(0, 0));

		// 유저 텍스트필드
		userTf = new JTextField(6);
		userTf.setHorizontalAlignment(SwingConstants.CENTER);
		userTf.setFont(new Font("맑은 고딕", Font.ITALIC, 40));

		// 아이디를 입력받는
		userTf.setText("");
		userP.add(userTf, BorderLayout.CENTER);
		userTf.setColumns(10);

		// 패스워드 패널 설정
		JPanel pass = new JPanel() {
			@Override
			protected void paintComponent(Graphics g) {
				Dimension d = getSize();
				ImageIcon image = new ImageIcon("E:\\javadata\\Workspace\\javase\\pos\\src\\image\\KEY.png");
				g.drawImage(image.getImage(), 0, 0, d.width, d.height, null);
				setOpaque(false);
				super.paintComponent(g);
			};
		};
		pass.setOpaque(false);

		pass.setBounds(328, 368, 153, 156);
		background.add(pass);

		// 패스워드 패널
		JPanel passP = new JPanel();
		passP.setBounds(497, 368, 475, 156);
		background.add(passP);
		passP.setLayout(new BorderLayout(0, 0));

		// 비밀번호텍스트필드
		passTf = new JPasswordField(6);
		passTf.setHorizontalAlignment(SwingConstants.CENTER);
		passTf.setFont(new Font("맑은 고딕", Font.ITALIC, 40));

		// 비밀번호를 입력받는
		passTf.setText("");
		passP.add(passTf, BorderLayout.CENTER);
		passTf.setColumns(10);

		// 버튼 두개
		JPanel SouthButt = new JPanel();
		SouthButt.setOpaque(false);
		SouthButt.setBounds(407, 584, 515, 111);
		background.add(SouthButt);
		SouthButt.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 0));

		// 로그인 버튼
		// 둥글게 만드는 버튼 클래스 객체 생성후 대입
		loginB = new JButton("\uB85C\uADF8\uC778 ");
		RoundedButton rb = new RoundedButton("로그인");
		rb.setBorder(new EtchedBorder(EtchedBorder.RAISED, null, null));
		loginB = rb;

		loginB.setForeground(new Color(240, 248, 255));
		loginB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		loginB.setSize(new Dimension(10, 10));
		loginB.setIconTextGap(1);
		loginB.setBackground(new Color(28, 94, 94));
		loginB.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		loginB.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		SouthButt.add(loginB);

		// 종료 버튼
		// 둥글게 만드는 버튼 클래스 객체 생성후 대입
		exitB = new JButton("\uC885\uB8CC");
		rb_1 = new RoundedButton("종   료");
		rb_1.setBorder(new EtchedBorder(EtchedBorder.RAISED, new Color(255, 127, 80), new Color(255, 127, 80)));
		exitB = rb_1;

		exitB.setBackground(new Color(255, 99, 71));
		exitB.setForeground(new Color(240, 248, 255));
		exitB.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		exitB.setIconTextGap(1);
		exitB.setMinimumSize(new Dimension(73, 23));
		exitB.setMaximumSize(new Dimension(73, 23));
		exitB.setFont(new Font("맑은 고딕", Font.PLAIN, 50));
		SouthButt.add(exitB);
//		inputIcon();
		setResizable(false);

		// 리스너 등록
		userTf.addActionListener(this);
		loginB.addActionListener(this);
		exitB.addActionListener(this);
		passTf.addActionListener(this);

	}

	// 로그인 성공 / 실패 판단 메소드
	public boolean isLogin() {
		return loginCheck;
	}

// 아이디 비밀번호 유효성 검사
// userTf.setText("");
//관리자,직원의 번호를 미리 설정한 후 입력받게 함.

	public void isLoginCheck() {

		if (userTf.getText().equals("개나리") && new String(passTf.getText()).equals("1234")) {
			String str = userTf.getText();
			JOptionPane.showMessageDialog(null, str + "님이 로그인 되었습니다.");
			loginCheck = true;
			if (isLogin()) {
				showFrameTest();
			}

		} else {
			JOptionPane.showMessageDialog(null, "로그인이 되지 않았습니다.");
		}

	}

	public void showFrameTest() {
		main = new MainFrame();
		main.setVisible(true);
//		dispose();
	}

	// 로그인 창에서 메인프레임으로 연결 메소드
	public void setMain(MainFrame main) {
		this.main = main;
		main.frame.setMain(main);
	}

	// 실행
	public static void main(String[] args) {
		ForcePos frame = new ForcePos();
//		main.setVisible(true);

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainFrame main = new MainFrame(); // 메인프레임 객체생성
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});

	}

	public boolean isId() {
		userTf.getText().equals("개나리");
		return true;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		boolean id = userTf.getText().equals("개나리");

		// TODO 텍스트필드에서 마우스클릭 시 필드 초기화 진행해야함
//		if () {
//			isLoginCheck();
//			passTf.requestFocus();	
//		}
		if (id) {
			passTf.requestFocus();
		}

		if (ob == loginB) {
			isLoginCheck();
		}

		if (ob == passTf) {
			isLoginCheck();
		}

		if (ob == exitB) {
			System.exit(0);
		}

	}
}
