package com.kitri.pos;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import java.awt.Font;

public class UserList implements MouseListener {

	Vector<UserDto> row;
	Vector<String> cols;
	
	//====================//
	
	DefaultTableModel model;
	JTable jTable;
	JScrollPane pane;


	// 기본생성자
	public UserList() {

		UserDao dao = new UserDao();
		row = dao.getMemberList();
		
		cols = new Vector<String>(getColum());


		jTable = new JTable(model);
		jTable.setFont(new Font("맑은 고딕", Font.PLAIN, 20));
		pane = new JScrollPane(jTable);
		jTable.addMouseListener(this);

	}

	// Jtable 컬럼
	Vector<String> getColum() {
		
		cols = new Vector<String>();

		cols.add("유저코드");
		cols.add("패스워드");
		cols.add("아이디");
		cols.add("권한");
		cols.add("이름");

		return cols;
	}

	// Jtable 내용 갱신
	public void jtableRefresh() {

//		UserDao userdao = new UserDao();
//		DefaultTableModel model = new DefaultTableModel(userdao.getMemberList(), getColum());
//		jTable.setModel(model);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		//테이블의 행을 선택할 때의 인덱스값을 얻어온다.
//		int r = jTable.getSelectedRow();
//		String str = (String) jTable.getValueAt(r, 0);
//		Administrator ad = new Administrator();
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
