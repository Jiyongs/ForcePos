package com.kitri.pos;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Vector;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class UserList implements MouseListener {

	Vector v;
	Vector cols;
	//====================//
	DefaultTableModel model;
	JTable jTable;
	JScrollPane pane;
	UserDao dao;

	// 기본생성자
	public UserList() {

		UserDao dao = new UserDao();
		v = dao.getMemberList();
		cols = getColum();

		model = new DefaultTableModel(v, cols);

		jTable = new JTable(model);
		pane = new JScrollPane(jTable);

		jTable.addMouseListener(this);

	}

	// Jtable 컬럼
	private Vector getColum() {
		Vector col = new Vector();

		col.add("유저코드");
		col.add("패스워드");
		col.add("아이디");
		col.add("권한");
		col.add("이름");

		return col;
	}

	// Jtable 내용 갱신
	public void jtableRefresh() {

		dao = new UserDao();
		DefaultTableModel model = new DefaultTableModel(dao.getMemberList(), getColum());
		jTable.setModel(model);

	}

	@Override
	public void mouseClicked(MouseEvent e) {
	
		int r = jTable.getSelectedRow();
		String id = (String) jTable.getValueAt(r, 0);
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
