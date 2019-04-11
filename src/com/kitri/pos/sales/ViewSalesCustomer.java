package com.kitri.pos;
								//판매 메인화면은 판매 등록화면으로 시작?
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;
import java.util.Vector;

//import javax.swing.JSpinner;	//텍스트필드에 화살표 위아래
//import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
//import javax.swing.JRadioButton;
import javax.swing.JScrollPane; //컴포넌트에 스크롤
import javax.swing.JTextField;
import java.awt.Dimension;
import javax.swing.SwingConstants;	

public class ViewSalesCustomer extends JPanel {
	
	private JTable tableResult;
	
	JTextField cellphone;
	JTextField name;
	JTable mainTable;
	JButton enroll;
	JButton search;
	JButton delete;
	DefaultTableModel tmodel;
	/**
	 * Create the panel.
	 */
	public ViewSalesCustomer() {
		setSize(new Dimension(1144, 535));
		setLayout(null);
		
		JPanel salesMainTablePanel = new JPanel();//테이블 패널
		salesMainTablePanel.setSize(new Dimension(1144, 535));
		salesMainTablePanel.setBounds(0, 0, 1144, 452);
		add(salesMainTablePanel);
		salesMainTablePanel.setLayout(null);
		
		JScrollPane mainScrollPane = new JScrollPane();//테이블 스크롤
		mainScrollPane.setSize(new Dimension(1144, 535));
		mainScrollPane.setBounds(14, 12, 1116, 428);
		salesMainTablePanel.add(mainScrollPane);
		
		mainTable = new JTable();
		mainScrollPane.setViewportView(mainTable);
		
		JPanel salesMainSearchPanel = new JPanel();//아래 패널
		salesMainSearchPanel.setBounds(0, 452, 1144, 83);
		add(salesMainSearchPanel);
		salesMainSearchPanel.setLayout(null);
		
		name = new JTextField();
		name.setColumns(10);
		name.setBounds(145, 12, 218, 46);
		salesMainSearchPanel.add(name);//이름
		
		cellphone = new JTextField();
		cellphone.setColumns(10);
		cellphone.setBounds(466, 12, 218, 46);
		salesMainSearchPanel.add(cellphone);
		
		JLabel label = new JLabel("\uC774\uB984 :");//이름
		label.setBounds(54, 26, 77, 18);
		salesMainSearchPanel.add(label);
		
		JLabel lblNewLabel_1 = new JLabel("\uD578\uB4DC\uD3F0 :");//휴대폰
		lblNewLabel_1.setBounds(390, 26, 62, 18);
		salesMainSearchPanel.add(lblNewLabel_1);
		
		enroll = new JButton("\uB4F1\uB85D");
		enroll.setBounds(719, 11, 97, 46);
		salesMainSearchPanel.add(enroll);
		
		search = new JButton("\uC870\uD68C");
		search.setBounds(991, 11, 97, 46);
		salesMainSearchPanel.add(search);
		
		delete = new JButton("\uC0AD\uC81C");
		delete.setBounds(857, 11, 97, 46);
		salesMainSearchPanel.add(delete);
		
		//[테이블]
		//임시 테이블 모델 생성
		Vector<String> header = new Vector<String>();
		header.add("멤버십 ID");
		header.add("이름");
		header.add("연락처");
		header.add("포인트");
		
		
//		String header[] = {"멤버쉽 ID", "이름", "연락처", "포인트"};
//		String contents[][] = {
//				{"1", "이종영", "010-1234-1234", "30000"},
//				{"2", "신지영", "010-2345-2345", "3000"},
//				{"3", "박광규", "010-1111-2222", "30000"},
//				{"4", "노정탁", "010-1324-1324", "30000"},
//				{"5", "김의연", "010-1324-3333", "30000"},
//				{"6", "김형섭", "010-2432-2432", "1000000000"}
//		
//		};
		
		tmodel = new DefaultTableModel(header,0);
		
		tableResult = new JTable(tmodel);
		tableResult.setRowHeight(30);
		tableResult.setSize(new Dimension(1144, 535));
		mainScrollPane.setViewportView(tableResult);

	}

	public String getText() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
