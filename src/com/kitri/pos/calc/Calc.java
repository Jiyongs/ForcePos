//test
package com.kitri.pos.calc;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JTextField;
import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JButton;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import java.awt.GridLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import javax.swing.BoxLayout;

public class Calc extends JFrame {


	private JPanel contentPane;
	private JTextField notice;
	private JTable cashTable;
	private JTextField tfCashState;
	private JTextField tfCashCheck;
	private JTextField tfCalcResult;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Calc frame = new Calc();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Calc() {
		setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		setTitle("Force.pos");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1326, 753);
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
		titleLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 25));
		titleLabel.setBounds(14, 8, 241, 31);
		pStatusBar.add(titleLabel);
		
		notice = new JTextField();
		notice.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
		notice.setText("\uC0C1\uD488\uBA85(..)\uB294 \uC720\uD1B5\uAE30\uD55C\uC774 \uC9C0\uB0AC\uC2B5\uB2C8\uB2E4.");
		notice.setHorizontalAlignment(SwingConstants.CENTER);
		notice.setBounds(258, 8, 726, 31);
		pStatusBar.add(notice);
		notice.setColumns(10);
		
		JLabel dateLabel = new JLabel("2019-04-01 \uC624\uD6C4 5:01");
		dateLabel.setBackground(new Color(0, 0, 128));
		dateLabel.setHorizontalAlignment(SwingConstants.CENTER);
		dateLabel.setForeground(new Color(255, 255, 255));
		dateLabel.setFont(new Font("¸¼Àº °íµñ", Font.PLAIN, 20));
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
		idLabel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		idLabel.setBounds(860, 0, 201, 120);
		pMainBtn.add(idLabel);
		
		JButton mBtnInven = new JButton("\uC7AC\uACE0");
		mBtnInven.setBackground(new Color(28, 94, 94));
		mBtnInven.setForeground(new Color(255, 255, 255));
		mBtnInven.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		mBtnInven.setBounds(0, 0, 157, 120);
		pMainBtn.add(mBtnInven);
		
		JButton mBtnSale = new JButton("\uD310\uB9E4");
		mBtnSale.setBackground(new Color(99, 166, 166));
		mBtnSale.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		mBtnSale.setForeground(new Color(255, 255, 255));
		mBtnSale.setBounds(156, 0, 157, 120);
		pMainBtn.add(mBtnSale);
		
		JButton mBtnCalc = new JButton("\uC815\uC0B0");
		mBtnCalc.setBackground(new Color(28, 94, 94));
		mBtnCalc.setForeground(new Color(255, 255, 255));
		mBtnCalc.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		mBtnCalc.setBounds(313, 0, 157, 120);
		pMainBtn.add(mBtnCalc);
		
		JButton mBtnStat = new JButton("\uD1B5\uACC4");
		mBtnStat.setBackground(new Color(99, 166, 166));
		mBtnStat.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		mBtnStat.setForeground(new Color(255, 255, 255));
		mBtnStat.setBounds(470, 0, 157, 120);
		pMainBtn.add(mBtnStat);
		
		JButton mBtnAccount = new JButton("\uACC4\uC815");
		mBtnAccount.setBackground(new Color(28, 94, 94));
		mBtnAccount.setForeground(new Color(255, 255, 255));
		mBtnAccount.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		mBtnAccount.setBounds(626, 0, 157, 120);
		pMainBtn.add(mBtnAccount);
		
		JPanel pSellFunction = new JPanel();
		pSellFunction.setBackground(new Color(0, 0, 128));
		pSellFunction.setBounds(1144, 50, 164, 655);
		contentPane.add(pSellFunction);
		pSellFunction.setLayout(null);
		
		JButton sBtnCustomer = new JButton("\uC7AC\uACE0\uC870\uD68C");
		sBtnCustomer.setForeground(new Color(255, 255, 255));
		sBtnCustomer.setBackground(new Color(0, 0, 128));
		sBtnCustomer.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnCustomer.setBounds(0, 0, 164, 120);
		pSellFunction.add(sBtnCustomer);
		
		JButton sBtnDisuse = new JButton("\uC785\uCD9C\uACE0");
		sBtnDisuse.setBackground(new Color(100, 149, 237));
		sBtnDisuse.setForeground(new Color(255, 255, 255));
		sBtnDisuse.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnDisuse.setBounds(0, 130, 164, 120);
		pSellFunction.add(sBtnDisuse);
		
		JButton sBtnPdInput = new JButton("\uC7AC\uACE0\uB4F1\uB85D");
		sBtnPdInput.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sBtnPdInput.setBackground(new Color(0, 0, 128));
		sBtnPdInput.setForeground(new Color(255, 255, 255));
		sBtnPdInput.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnPdInput.setBounds(0, 260, 164, 120);
		pSellFunction.add(sBtnPdInput);
		
		JButton sBtnPdChange = new JButton("\uC7AC\uACE0\uC218\uC815");
		sBtnPdChange.setBackground(new Color(100, 149, 237));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 390, 164, 120);
		pSellFunction.add(sBtnPdChange);
		
		JButton sBtnPdCancel = new JButton("\uC7AC\uACE0\uC0AD\uC81C");
		sBtnPdCancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		sBtnPdCancel.setBackground(new Color(0, 0, 128));
		sBtnPdCancel.setForeground(new Color(255, 255, 255));
		sBtnPdCancel.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnPdCancel.setBounds(0, 520, 164, 120);
		pSellFunction.add(sBtnPdCancel);
		
		/////////////////////////////////////////////////////////////////////////////////////////////////////////
		
		JPanel pMonitor = new JPanel();
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1144, 535);
		contentPane.add(pMonitor);
		pMonitor.setLayout(new CardLayout(0, 0));
		
		JPanel pCalc = new JPanel();
		pMonitor.add(pCalc, "name_51446151219700");
		pCalc.setLayout(null);
		
		JPanel pCashstate = new JPanel();
		pCashstate.setBounds(12, 10, 533, 515);
		pCalc.add(pCashstate);
		
		String[] headings = new String[] {"±ÇÁ¾", "¸Å¼ö", "±Ý¾×"};
		Object[][] data = new Object[][] {
			{50000, 0, 0},
			{10000, "0", 0},
			{5000, "0", 0},
			{1000, "0", 0},
			{500, "0", 0},
			{100, "0", 0},
			{50, "0", 0},
			{10, "0", 0}
		};
		
		cashTable = new JTable(data,headings);
		cashTable.setFont(new Font("±¼¸²", Font.PLAIN, 20));
		pCashstate.add(cashTable);
		cashTable.setPreferredSize(new Dimension(533,515));
		cashTable.setFillsViewportHeight(true);
//		cashTable.setBounds(0,0,533,515);
		
		JPanel pcalcmain = new JPanel();
		pcalcmain.setBounds(557, 10, 575, 515);
		pCalc.add(pcalcmain);
		pcalcmain.setLayout(null);
		
		JPanel pNum = new JPanel();
		pNum.setBounds(0, 0, 394, 325);
		pcalcmain.add(pNum);
		pNum.setLayout(new GridLayout(4, 1, 0, 10));
		
		JPanel panel_7 = new JPanel();
		pNum.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 3, 10, 10));
		
		JButton btnCalc_7 = new JButton("7");
		panel_7.add(btnCalc_7);
		
		JButton btnCalc_8 = new JButton("8");
		panel_7.add(btnCalc_8);
		
		JButton btnCalc_9 = new JButton("9");
		panel_7.add(btnCalc_9);
		
		JPanel panel_4 = new JPanel();
		pNum.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 3, 10, 10));
		
		JButton btnCalc_4 = new JButton("4");
		panel_4.add(btnCalc_4);
		
		JButton btnCalc_5 = new JButton("5");
		panel_4.add(btnCalc_5);
		
		JButton btnCalc_6 = new JButton("6");
		panel_4.add(btnCalc_6);
		
		JPanel panel_1 = new JPanel();
		pNum.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 3, 10, 10));
		
		JButton btnCalc_1 = new JButton("1");
		panel_1.add(btnCalc_1);
		
		JButton btnCalc_2 = new JButton("2");
		panel_1.add(btnCalc_2);
		
		JButton btnCalc_3 = new JButton("3");
		panel_1.add(btnCalc_3);
		
		JPanel panel_0 = new JPanel();
		pNum.add(panel_0);
		panel_0.setLayout(null);
		
		JButton btnCalc_0 = new JButton("0");
		btnCalc_0.setBounds(0, 0, 257, 73);
		panel_0.add(btnCalc_0);
		
		JButton btnCalc_00 = new JButton("00");
		btnCalc_00.setBounds(269, 0, 125, 73);
		panel_0.add(btnCalc_00);
		
		JPanel pcalc_btns = new JPanel();
		pcalc_btns.setBounds(406, 0, 169, 515);
		pcalcmain.add(pcalc_btns);
		pcalc_btns.setLayout(null);
		
		JButton btnCalc_del = new JButton("\u2190");
		btnCalc_del.setBounds(0, 0, 169, 80);
		pcalc_btns.add(btnCalc_del);
		
		JButton btnCalc_C = new JButton("C");
		btnCalc_C.setBounds(0, 90, 169, 80);
		pcalc_btns.add(btnCalc_C);
		
		JButton btnCalc_Input = new JButton("Enter");
		btnCalc_Input.setBounds(0, 180, 169, 145);
		pcalc_btns.add(btnCalc_Input);
		
		JButton btnCalc_Apply = new JButton("\uC815\uC0B0");
		btnCalc_Apply.setBounds(0, 335, 169, 80);
		pcalc_btns.add(btnCalc_Apply);
		
		JButton btnCalc_Cancel = new JButton("\uB2EB\uAE30");
		btnCalc_Cancel.setBounds(0, 425, 169, 80);
		pcalc_btns.add(btnCalc_Cancel);
		
		JPanel pcalcState = new JPanel();
		pcalcState.setBounds(23, 335, 371, 170);
		pcalcmain.add(pcalcState);
		pcalcState.setLayout(new BorderLayout(20, 0));
		
		JPanel panel = new JPanel();
		pcalcState.add(panel, BorderLayout.WEST);
		panel.setLayout(new GridLayout(0, 1, 0, 10));
		
		JLabel lbCashState = new JLabel("\uD604\uC7AC \uBCF4\uC720 \uD604\uAE08");
		lbCashState.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbCashState);
		
		JLabel lbCashCheck = new JLabel("\uC815\uC0B0 \uD604\uAE08");
		lbCashCheck.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbCashCheck);
		
		JLabel lbCalcResult = new JLabel("\uCC28\uC561");
		lbCalcResult.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbCalcResult);
		
		JPanel panel_2 = new JPanel();
		pcalcState.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 10));
		
		tfCashState = new JTextField("300000");
		tfCashState.setEnabled(false);
		panel_2.add(tfCashState);
		tfCashState.setColumns(10);
		
		tfCashCheck = new JTextField("250000");
		tfCashCheck.setEnabled(false);
		panel_2.add(tfCashCheck);
		tfCashCheck.setColumns(10);
		
		tfCalcResult = new JTextField(String.valueOf((Integer.parseInt(tfCashState.getText()) - Integer.parseInt(tfCashCheck.getText()))));	//	'-'ºÎÈ£°¡ ¶ßÁú ¾ÊÀ½
		tfCalcResult.setEnabled(false);
		panel_2.add(tfCalcResult);
		tfCalcResult.setColumns(10);
	}
}
