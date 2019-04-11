package com.kitri.pos;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.CardLayout;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Color;

public class StockPopupChg extends JFrame implements ActionListener{

	JPanel contentPane;
	JTextField ChgCountTf;
	JPanel pChg;
	JLabel selecItem;
	JLabel chgCountL;
	JButton chgOk;
	JButton chgCancel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StockPopupChg frame = new StockPopupChg();
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
	public StockPopupChg() {

		setTitle("\uC7AC\uACE0 \uAD00\uB9AC");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 700, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(false);
		contentPane.setLayout(null);
		
		pChg = new JPanel();
		pChg.setBounds(5, 5, 684, 455);
		contentPane.add(pChg);
		pChg.setLayout(null);
		
		selecItem = new JLabel("\uC120\uD0DD\uD55C \uD589\uC758 \uC0C1\uD488\uC815\uBCF4");
		selecItem.setBackground(new Color(221, 160, 221));
		selecItem.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 16));
		selecItem.setBounds(12, 48, 660, 100);
		selecItem.setOpaque(true);
		pChg.add(selecItem);
		
		chgCountL = new JLabel("\uC218\uC815\uD560 \uC218\uB7C9");
		chgCountL.setBackground(new Color(221, 160, 221));
		chgCountL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		chgCountL.setHorizontalAlignment(SwingConstants.CENTER);
		chgCountL.setBounds(115, 206, 140, 60);
		chgCountL.setOpaque(true);
		pChg.add(chgCountL);
		
		ChgCountTf = new JTextField();
		ChgCountTf.setHorizontalAlignment(SwingConstants.CENTER);
		ChgCountTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		ChgCountTf.setBounds(267, 206, 300, 60);
		pChg.add(ChgCountTf);
		ChgCountTf.setColumns(10);
		
		chgOk = new JButton("\uC644 \uB8CC");
		chgOk.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		chgOk.setBounds(212, 362, 120, 60);
		pChg.add(chgOk);
		
		chgCancel = new JButton("\uCDE8 \uC18C");
		chgCancel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		chgCancel.setBounds(344, 362, 120, 60);
		pChg.add(chgCancel);

		chgOk.addActionListener(this);
		chgCancel.addActionListener(this);
		ChgCountTf.addActionListener(this);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob==chgCancel) {
			setVisible(false);
			ChgCountTf.setText("");
		} else if(ob==chgOk || ob==ChgCountTf) {
			
		}
		
		
	}
}
