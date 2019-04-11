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

public class StockPopupIn extends JFrame implements ActionListener {

	JPanel contentPane;
	JTextField PcodeTf;
	JTextField IndateTf;
	JTextField PcntTf;
	JPanel pIn;
	JLabel pcodeL;
	JLabel indateL;
	JLabel pcntL;
	JLabel pcodeResult;
	JLabel indateResult;
	JLabel pcntResult;
	JButton pcodeInput;
	JButton indateInput;
	JButton pcntInput;
	JButton inOk;
	JButton inCancel;

	/**
	 * Launch the application.
	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					StockPopupIn frame = new StockPopupIn();
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
	public StockPopupIn() {

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
		
		pIn = new JPanel();
		pIn.setBounds(5, 5, 684, 455);
		contentPane.add(pIn);
		pIn.setLayout(null);
		
		pcodeL = new JLabel("\uC0C1\uD488 \uCF54\uB4DC");
		pcodeL.setBackground(new Color(255, 165, 0));
		pcodeL.setHorizontalAlignment(SwingConstants.CENTER);
		pcodeL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeL.setBounds(14, 47, 120, 60);
		pcodeL.setOpaque(true);
		pIn.add(pcodeL);
		
		indateL = new JLabel("\uC785\uACE0\uC77C\uC790");
		indateL.setBackground(new Color(255, 165, 0));
		indateL.setHorizontalAlignment(SwingConstants.CENTER);
		indateL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		indateL.setBounds(14, 155, 120, 60);
		indateL.setOpaque(true);
		pIn.add(indateL);
		
		pcntL = new JLabel("\uC218\uB7C9");
		pcntL.setBackground(new Color(255, 165, 0));
		pcntL.setHorizontalAlignment(SwingConstants.CENTER);
		pcntL.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntL.setBounds(14, 268, 120, 60);
		pcntL.setOpaque(true);
		pIn.add(pcntL);
		
		PcodeTf = new JTextField();
		PcodeTf.setHorizontalAlignment(SwingConstants.CENTER);
		PcodeTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		PcodeTf.setBounds(146, 47, 230, 60);
		pIn.add(PcodeTf);
		PcodeTf.setColumns(10);
		
		IndateTf = new JTextField();
		IndateTf.setHorizontalAlignment(SwingConstants.CENTER);
		IndateTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 15));
		IndateTf.setColumns(10);
		IndateTf.setBounds(146, 155, 230, 60);
		pIn.add(IndateTf);
		
		PcntTf = new JTextField();
		PcntTf.setHorizontalAlignment(SwingConstants.CENTER);
		PcntTf.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		PcntTf.setColumns(10);
		PcntTf.setBounds(146, 268, 230, 60);
		pIn.add(PcntTf);
		
		pcodeResult = new JLabel("");
		pcodeResult.setBackground(Color.LIGHT_GRAY);
		pcodeResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeResult.setHorizontalAlignment(SwingConstants.CENTER);
		pcodeResult.setBounds(497, 47, 177, 60);
		pcodeResult.setOpaque(true);
		pIn.add(pcodeResult);
		
		indateResult = new JLabel("");
		indateResult.setBackground(Color.LIGHT_GRAY);
		indateResult.setHorizontalAlignment(SwingConstants.CENTER);
		indateResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		indateResult.setBounds(497, 155, 177, 60);
		indateResult.setOpaque(true);
		pIn.add(indateResult);
		
		pcntResult = new JLabel("");
		pcntResult.setBackground(Color.LIGHT_GRAY);
		pcntResult.setHorizontalAlignment(SwingConstants.CENTER);
		pcntResult.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntResult.setBounds(497, 268, 177, 60);
		pcntResult.setOpaque(true);
		pIn.add(pcntResult);
		
		pcodeInput = new JButton("\uC785\uB825");
		pcodeInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcodeInput.setBounds(388, 47, 97, 60);
		pIn.add(pcodeInput);
		
		indateInput = new JButton("\uC785\uB825");
		indateInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		indateInput.setBounds(388, 155, 97, 60);
		pIn.add(indateInput);
		
		pcntInput = new JButton("\uC785\uB825");
		pcntInput.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		pcntInput.setBounds(388, 268, 97, 60);
		pIn.add(pcntInput);
		
		inOk = new JButton("\uB4F1 \uB85D");
		inOk.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inOk.setBounds(210, 373, 120, 60);
		pIn.add(inOk);
		
		inCancel = new JButton("\uCDE8 \uC18C");
		inCancel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		inCancel.setBounds(342, 373, 120, 60);
		pIn.add(inCancel);
		
		inOk.addActionListener(this);
		inCancel.addActionListener(this);
		PcodeTf.addActionListener(this);
		IndateTf.addActionListener(this);
		PcntTf.addActionListener(this);
		pcodeInput.addActionListener(this);
		indateInput.addActionListener(this);
		pcntInput.addActionListener(this);
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getSource();
		if(ob==inCancel) {
			setVisible(false);
			IndateTf.setText("");
			PcodeTf.setText("");
			PcntTf.setText("");
			
		} else if(ob==PcodeTf || ob==pcodeInput) {
			pcodeResult.setText(PcodeTf.getText());
		} else if(ob==IndateTf || ob==indateInput) {
			indateResult.setText(IndateTf.getText());
		} else if(ob==PcntTf || ob==pcntInput) {
			pcntResult.setText(PcntTf.getText());
		} else if(ob==inOk) {
			
		}
		
	}
}
