package com.kitri.pos.sale;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JPanel;

public class SaleBtn extends JPanel {

	public JButton sBtnCustomer;
	public 	JButton sBtnDisuse;
	public JButton sBtnPdChange;
	public JButton sBtnPdCancel;
	public JButton sBtnPdHold;
	public JButton sBtnPay;
	public JButton sBtnAcencel;
	public JButton sBtnCancel;

	/**
	 * Create the panel.
	 */
	public SaleBtn() {
		setLayout(null);
		setBackground(Color.WHITE);

		sBtnCustomer = new JButton("\uACE0\uAC1D\uAD00\uB9AC");
		sBtnCustomer.setForeground(new Color(255, 255, 255));
		sBtnCustomer.setBackground(new Color(0, 0, 128));
		sBtnCustomer.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnCustomer.setBounds(0, 0, 164, 85);
		add(sBtnCustomer);

		sBtnDisuse = new JButton("\uD3D0\uAE30\uAD00\uB9AC");
		sBtnDisuse.setBackground(new Color(100, 149, 237));
		sBtnDisuse.setForeground(new Color(255, 255, 255));
		sBtnDisuse.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnDisuse.setBounds(0, 83, 164, 85);
		add(sBtnDisuse);

		sBtnPdChange = new JButton("\uC0C1\uD488\uC218\uC815");
		sBtnPdChange.setBackground(new Color(0, 0, 128));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 165, 164, 85);
		add(sBtnPdChange);

		sBtnPdCancel = new JButton("\uC0C1\uD488\uCDE8\uC18C");
		sBtnPdCancel.setBackground(new Color(100, 149, 237));
		sBtnPdCancel.setForeground(new Color(255, 255, 255));
		sBtnPdCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdCancel.setBounds(0, 249, 164, 85);
		add(sBtnPdCancel);

		sBtnPdHold = new JButton("\uC0C1\uD488\uBCF4\uB958");
		sBtnPdHold.setForeground(Color.WHITE);
		sBtnPdHold.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPdHold.setBackground(new Color(0, 0, 128));
		sBtnPdHold.setBounds(0, 333, 164, 85);
		add(sBtnPdHold);

		sBtnPay = new JButton("\uACB0\uC81C");
		sBtnPay.setForeground(Color.WHITE);
		sBtnPay.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnPay.setBackground(new Color(153, 0, 0));
		sBtnPay.setBounds(0, 416, 164, 85);
		add(sBtnPay);

		sBtnCancel = new JButton("환불");
		sBtnCancel.setBounds(0, 577, 164, 78);
		sBtnCancel.setForeground(Color.WHITE);
		sBtnCancel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnCancel.setBackground(new Color(153, 0, 0));
		add(sBtnCancel);

		sBtnAcencel = new JButton("거래취소");
		sBtnAcencel.setForeground(Color.WHITE);
		sBtnAcencel.setFont(new Font("맑은 고딕", Font.BOLD, 20));
		sBtnAcencel.setBackground(new Color(255, 51, 0));
		sBtnAcencel.setBounds(0, 495, 164, 85);
		add(sBtnAcencel);

	}

}
