package com.kitri.pos.account;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import java.util.Vector;
import java.awt.*;
import java.awt.event.*;
import java.sql.SQLException;
import javax.swing.border.LineBorder;

import com.kitri.pos.ForcePos;
import com.kitri.pos.db.*;
import com.kitri.pos.*;

public class Administrator extends JPanel{

	private static final long serialVersionUID = 1L;
	public ForcePos forcePos; //로그인 화면 
	JTextField userTf; // 아이디 입력
	JTextField passTf; // 패스워드 입력
	JTextField nameTf; // 이름 입력
	JTextField notice; // 상태창
	public PosDto userDto; //UserDto 클래스 
	public UserDao userDao; //UserDao 클래스
	public JComboBox authority; //등록 권한
	public JComboBox authorityUp;// 수정 권한
	Vector<PosDto> data;
	Vector<String> userColumn;
	public String auth; // 콤보박스 선택에 따라 권한 설정
	public JPanel pAcMonitor;
	public JTable table; //테이블
	public DefaultTableModel tm;
	public JTextField upuserTF; //유저수정 아이디필드
	JTextField upassTf; //유저수정 비밀번호필드
	JTextField unameTf; //유저수정 이름필드
	public boolean result; // 인서트 결과값 저장
	
	JScrollPane scrollPane;
	JLabel userIdLabel;
	JPanel panel;
	JLabel passWLabel_1;
	JLabel lblNewLabel_2;
	JLabel lblNewLabel_3;
	JPanel pB;
	public JButton ok;
	public JButton cancel;
	JPanel prInsert;
	JPanel panel_2;
	JLabel upuserL;
	JLabel uppassL;
	JLabel upnameL;
	JLabel upauthL;
	JPanel panel_3;
	public JButton button;
	public JButton button_1;
	JPanel prInput;

	// 회원등록 패널
	JPanel pRegister = new JPanel();
	// 회원테이블 패널
	JPanel pTable = new JPanel();
	// 회원수정 패널
	JPanel ppRegister;
	// 화면을 변경해줄 카드레이아웃 
	public CardLayout card = new CardLayout();
	

	
	//기본 생성자
	public Administrator() {

		setLayout(null);

		pAcMonitor = new JPanel();
		pAcMonitor.setBackground(new Color(255, 255, 255));
		pAcMonitor.setBounds(0, 50, 1144, 533);
		
		pRegister.setBackground(SystemColor.desktop);
		pRegister.setBorder(new LineBorder(new Color(0, 0, 0)));
		pRegister.setLayout(null);

		ppRegister = new JPanel();
		ppRegister.setBackground(Color.ORANGE);
		ppRegister.setLayout(null);

		prInput = new JPanel();
		prInput.setBorder(new LineBorder(new Color(0, 0, 0)));
		prInput.setBounds(451, 10, 356, 513);
		prInput.setLayout(new GridLayout(11, 1, 0, 0));
		pRegister.add(prInput);

		// Jtable
		userDao = new UserDao();
		data = userDao.getMemberList();

		// ColumnName
		userColumn = new Vector<String>();
		
		userColumn.addElement("유저코드");
		userColumn.addElement("패스워드");
		userColumn.addElement("아이디");
		userColumn.addElement("권한");
		userColumn.addElement("이름");

		tm = new DefaultTableModel(userColumn, 0);
		table = new JTable(tm);
		

		scrollPane = new JScrollPane(table);
		pTable.add(scrollPane);
		table.setRowHeight(60);
		MainFrame.tableCellCenter(table);
		pTable.setLayout(null);
		scrollPane.setBounds(0, 5, 1144, 528);

		int size = data.size();

		for (int i = 0; i < size; i++) {
			// 행 data를 담을 벡터 
			Vector<String> row = new Vector<String>();

			// 행 data 넣는 중.
			row.addElement(data.get(i).getUserCode() + "");
			row.addElement(data.get(i).getPw());
			row.addElement(data.get(i).getId());
			row.addElement(data.get(i).getAuthority());
			row.addElement(data.get(i).getName());
			
			tm.addRow(row);
		}
		
		// 카드레이아웃담당.
		pAcMonitor.setLayout(card);
		pAcMonitor.add("pTable", pTable);
		pAcMonitor.add("pRegister", pRegister);
		pAcMonitor.add("ppRegister", ppRegister);
		card.show(pAcMonitor, "pTable");

		panel = new JPanel();
		prInput.add(panel);

		userIdLabel = new JLabel("\uC720\uC800ID");
		userIdLabel.setHorizontalAlignment(SwingConstants.CENTER);
		userIdLabel.setHorizontalTextPosition(SwingConstants.LEADING);
		userIdLabel.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(userIdLabel);

		// 회원등록 - 유저아이디입력
		userTf = new JTextField();
		userTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(userTf);
		userTf.setColumns(10);
		passWLabel_1 = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		passWLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		passWLabel_1.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(passWLabel_1);

		// 회원등록 - 패스워드입력
		passTf = new JTextField();
		passTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(passTf);
		passTf.setColumns(10);
		lblNewLabel_2 = new JLabel("\uC774\uB984");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(lblNewLabel_2);

		// 회원등록 - 이름입력
		nameTf = new JTextField();
		nameTf.setHorizontalAlignment(SwingConstants.CENTER);
		prInput.add(nameTf);
		nameTf.setColumns(10);
		lblNewLabel_3 = new JLabel("\uAD8C\uD55C");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInput.add(lblNewLabel_3);

		//유저등록 권한배열
		authority = new JComboBox();
		authority.addItem("관리자");
		authority.addItem("직원");
		prInput.add(authority);

		pB = new JPanel();
		prInput.add(pB);
		pB.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		ok = new JButton("\uD655\uC778");
		ok.setMargin(new Insets(2, 20, 2, 20));
		ok.setHorizontalTextPosition(SwingConstants.CENTER);
		ok.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		ok.setAlignmentX(Component.CENTER_ALIGNMENT);
		pB.add(ok);

		cancel = new JButton("\uCDE8\uC18C");
		cancel.setMargin(new Insets(2, 20, 2, 20));
		cancel.setBackground(new Color(255, 99, 71));
		cancel.setHorizontalTextPosition(SwingConstants.CENTER);
		cancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		pB.add(cancel);

		prInsert = new JPanel();
		prInsert.setBounds(451, 10, 356, 513);
		prInsert.setBorder(new LineBorder(new Color(0, 0, 0)));
		ppRegister.add(prInsert);
		prInsert.setLayout(new GridLayout(11, 1, 0, 0));

		panel_2 = new JPanel();
		prInsert.add(panel_2);

		upuserL = new JLabel("\uC720\uC800ID");
		upuserL.setHorizontalAlignment(SwingConstants.CENTER);
		upuserL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInsert.add(upuserL);

		// 회원 수정 아이디 필드
		upuserTF = new JTextField();
		upuserTF.setHorizontalAlignment(SwingConstants.CENTER);
		upuserTF.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		upuserTF.setEnabled(false);
		upuserTF.setDragEnabled(true);
		upuserTF.setColumns(10);
		prInsert.add(upuserTF);

		uppassL = new JLabel("\uD328\uC2A4\uC6CC\uB4DC");
		uppassL.setHorizontalAlignment(SwingConstants.CENTER);
		uppassL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInsert.add(uppassL);

		// 회원 수정 패스워드 필드 
		upassTf = new JTextField();
		upassTf.setHorizontalAlignment(SwingConstants.CENTER);
		upassTf.setColumns(10);
		prInsert.add(upassTf);

		upnameL = new JLabel("\uC774\uB984");
		upnameL.setHorizontalAlignment(SwingConstants.CENTER);
		upnameL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInsert.add(upnameL);

		// 회원 수정 이름 필드 
		unameTf = new JTextField();
		unameTf.setHorizontalAlignment(SwingConstants.CENTER);
		unameTf.setColumns(10);
		prInsert.add(unameTf);

		upauthL = new JLabel("\uAD8C\uD55C");
		upauthL.setHorizontalAlignment(SwingConstants.CENTER);
		upauthL.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		prInsert.add(upauthL);

		//유저수정 권한배열
		authorityUp = new JComboBox();
		authorityUp.addItem("관리자");
		authorityUp.addItem("직원");
		prInsert.add(authorityUp);

		panel_3 = new JPanel();
		prInsert.add(panel_3);
		panel_3.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

		button = new JButton("\uC218\uC815");
		button.setMargin(new Insets(2, 20, 2, 20));
		button.setHorizontalTextPosition(SwingConstants.CENTER);
		button.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		button.setAlignmentX(0.5f);
		panel_3.add(button);

		button_1 = new JButton("\uCDE8\uC18C");
		button_1.setMargin(new Insets(2, 20, 2, 20));
		button_1.setHorizontalTextPosition(SwingConstants.CENTER);
		button_1.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		button_1.setBackground(new Color(255, 99, 71));
		panel_3.add(button_1);
		
		
		add(pAcMonitor);

		
	}

	// 회원등록창에 입력된 값을 보여줘
	public PosDto getViewData() {
		userDto = new PosDto();

		userDto.setPw(passTf.getText());
		userDto.setId(userTf.getText());
		userDto.setName(nameTf.getText());
		userDto.setAuthority(auth);

		return userDto;
	}

	// 회원 수정창에 입력된 값을 보여줘
	public PosDto getViewUpdata() {
		userDto = new PosDto();

		userDto.setId(upuserTF.getText());
		userDto.setPw(upassTf.getText());
		userDto.setName(unameTf.getText());
		userDto.setAuthority(auth);

		return userDto;
	}
	//유저등록 유효성 검사 
	public boolean isUserId() {

		String user = userTf.getText().trim();
		String pass = passTf.getText().trim();
		String name = nameTf.getText().trim();

		if (user.length() > 10) {
			JOptionPane.showMessageDialog(this, "아이디는 10자 미만으로 생성가능합니다.", "ID생성 오류", JOptionPane.WARNING_MESSAGE);
			result = false;
		} else if (user.isEmpty() || pass.isEmpty() || name.isEmpty()) {
			JOptionPane.showMessageDialog(this, "공백은 안되요!!!");
				result = false;
		} else {
				result = true;
		}
		return result;
	}

	// 유저등록 최종 팝업 창 출력 
	public void insertUser() {
		getViewData();

		if (isUserId()) {
			JOptionPane.showMessageDialog(this, "등록이 완료되었습니다. 감사합니다.");
			card.show(pAcMonitor, "pTable");
		} else {
			JOptionPane.showMessageDialog(this, "등록이 실패되었습니다.");
			return;
		}
	}

	//유저 수정 유효성 검사 
	public void updateUser() {

		userDao = new UserDao();
		PosDto re = getViewUpdata();

		try {
			boolean result;
			
			result = userDao.updateMember(re);

			if (result) {
				JOptionPane.showMessageDialog(this, "수정이 완료되었습니다.");
				card.show(pAcMonitor, "pTable");
			} else {
				JOptionPane.showMessageDialog(this, "수정이 실패되었습니다.");
			 	return;
			}	
			// select 호출
			userDao.userSelectAll(tm);	
		} catch (SQLException e) {
			System.out.println("업데이트 실패");
			e.printStackTrace();
		}
	}
	
	//유저삭제 
	public void deleteUser() {
		try {
			// 행의 번호를 뽑아옴.
			int numberRow = table.getSelectedRow();
			// 테이블 클릭하면 행값과 고정된 (열값 : '아이디'를 고정으로) 호출 
			String id = (String) tm.getValueAt(numberRow, 2);
			System.out.println(id);
			userDao = new UserDao();
			boolean result;
			//아이디를 가져와서 delete 쿼리문 실행 
			//실행이 되었다면 result에는 true
			result = userDao.deleteMember(id);
			if (result) {
				JOptionPane.showMessageDialog(this, "삭제 완료");
			} else {
				JOptionPane.showMessageDialog(this, "삭제 실패");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	// 유저등록 텍스트필드 초기화
	public void tfClear() {
		userTf.setText("");
		passTf.setText("");
		nameTf.setText("");
	}

	// 유저수정 텍스트필드 초기화
	public void tfUClear() {
		upassTf.setText("");
		upuserTF.setText("");
		unameTf.setText("");
	}

	
}