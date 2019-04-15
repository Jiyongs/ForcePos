package com.kitri.pos.calc;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;
import java.awt.Rectangle;
import javax.swing.ScrollPaneConstants;

public class PCalc extends JPanel {

//	Vector<String> type = new Vector<String>();
	public DefaultTableModel model;
//	Vector<String> cnt;
//	Vector<String> total;

	String[][] data;
	public JTable cashTable;
	public JTextField tfCashState;
	public JTextField tfCashCheck;
	public JTextField tfCalcResult;

//	

	JPanel pcalcmain;
	JPanel pNum;
	JButton btnCalc_0;
	JButton btnCalc_00;
	JButton btnCalc_1;
	JButton btnCalc_2;
	JButton btnCalc_3;
	JButton btnCalc_4;
	JButton btnCalc_5;
	JButton btnCalc_6;
	JButton btnCalc_7;
	JButton btnCalc_8;
	JButton btnCalc_9;
	JButton btnCalc_Input;
	JButton btnCalc_del;
	JButton btnCalc_C;
	JButton btnCalc_Apply;
	JButton btnCalc_Cancel;
	JLabel lbCashState;
	JLabel lbCashCheck;
	JLabel lbCalcResult;
	private JScrollPane spTable;

	/**
	 * Create the panel.
	 */
	public PCalc() {

		setBackground(new Color(255, 255, 255));
		setBounds(0, 50, 1144, 535);

		String[] header = new String[] { "권종", "수량", "금액" };

		data = new String[][] { { "50000", "0", "0" }, { "10000", "0", "0" }, { "5000", "0", "0" }, { "1000", "0", "0" },
				{ "500", "0", "0" }, { "100", "0", "0" }, { "50", "0", "0" }, { "10", "0", "0" } };

		setLayout(null);
		JPanel pCashstate = new JPanel();
		pCashstate.setBounds(12, 10, 533, 515);
		add(pCashstate);

		model = new DefaultTableModel(data, header) {
			@Override
			public boolean isCellEditable(int row,int column) {
				if (column == 1) {
					return true;
				} else {
					return false;
				}
			}
		};

		spTable = new JScrollPane();
		spTable.setWheelScrollingEnabled(false);
		spTable.setEnabled(false);
		spTable.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		spTable.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		pCashstate.add(spTable);
		cashTable = new JTable(model);
		cashTable.setRowHeight(61);
		cashTable.setPreferredScrollableViewportSize(new Dimension(530, 485));
		cashTable.setAutoscrolls(false);

//		cashTable = new JTable();

		cashTable.setFont(new Font("����", Font.PLAIN, 20));
		cashTable.setPreferredSize(new Dimension(530, 485));
		cashTable.setFillsViewportHeight(true);
		cashTable.setBounds(0, 0, 533, 515);
		// cashTable.setDragEnabled(true);
		// cashTable.setColumnSelectionAllowed(false);
		spTable.setBounds(0, 0, 533, 515);
		spTable.setViewportView(cashTable);
//				pCashstate.add(cashTable);

		pcalcmain = new JPanel();
		pcalcmain.setBounds(557, 10, 575, 515);
		add(pcalcmain);
		pcalcmain.setLayout(null);

		pNum = new JPanel();
		pNum.setBounds(0, 0, 394, 325);
		pcalcmain.add(pNum);
		pNum.setLayout(new GridLayout(4, 1, 0, 10));

		JPanel panel_7 = new JPanel();
		pNum.add(panel_7);
		panel_7.setLayout(new GridLayout(0, 3, 10, 10));

		btnCalc_7 = new JButton("7");
		panel_7.add(btnCalc_7);

		btnCalc_8 = new JButton("8");
		panel_7.add(btnCalc_8);

		btnCalc_9 = new JButton("9");
		panel_7.add(btnCalc_9);

		JPanel panel_4 = new JPanel();
		pNum.add(panel_4);
		panel_4.setLayout(new GridLayout(0, 3, 10, 10));

		btnCalc_4 = new JButton("4");
		panel_4.add(btnCalc_4);

		btnCalc_5 = new JButton("5");
		panel_4.add(btnCalc_5);

		btnCalc_6 = new JButton("6");
		panel_4.add(btnCalc_6);

		JPanel panel_1 = new JPanel();
		pNum.add(panel_1);
		panel_1.setLayout(new GridLayout(0, 3, 10, 10));

		btnCalc_1 = new JButton("1");
		panel_1.add(btnCalc_1);

		btnCalc_2 = new JButton("2");
		panel_1.add(btnCalc_2);

		btnCalc_3 = new JButton("3");
		panel_1.add(btnCalc_3);

		JPanel panel_0 = new JPanel();
		pNum.add(panel_0);
		panel_0.setLayout(null);

		btnCalc_0 = new JButton("0");
		btnCalc_0.setBounds(0, 0, 257, 73);
		panel_0.add(btnCalc_0);

		btnCalc_00 = new JButton("00");
		btnCalc_00.setBounds(269, 0, 125, 73);
		panel_0.add(btnCalc_00);

		JPanel pcalc_btns = new JPanel();
		pcalc_btns.setBounds(406, 0, 169, 515);
		pcalcmain.add(pcalc_btns);
		pcalc_btns.setLayout(null);

		btnCalc_del = new JButton("\u2190");
		btnCalc_del.setBounds(0, 0, 169, 80);
		pcalc_btns.add(btnCalc_del);

		btnCalc_C = new JButton("C");
		btnCalc_C.setBounds(0, 90, 169, 80);
		pcalc_btns.add(btnCalc_C);

		btnCalc_Input = new JButton("Enter");
		btnCalc_Input.setBounds(0, 180, 169, 145);
		pcalc_btns.add(btnCalc_Input);

		btnCalc_Apply = new JButton("\uC815\uC0B0");
		btnCalc_Apply.setBounds(0, 335, 169, 80);
		pcalc_btns.add(btnCalc_Apply);

		btnCalc_Cancel = new JButton("\uB2EB\uAE30");
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

		lbCashCheck = new JLabel("\uC815\uC0B0 \uD604\uAE08");
		lbCashCheck.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbCashCheck);

		lbCalcResult = new JLabel("\uCC28\uC561");
		lbCalcResult.setHorizontalAlignment(SwingConstants.CENTER);
		panel.add(lbCalcResult);

		JPanel panel_2 = new JPanel();
		pcalcState.add(panel_2, BorderLayout.CENTER);
		panel_2.setLayout(new GridLayout(0, 1, 0, 10));

		tfCashState = new JTextField();
		tfCashState.setEditable(false);
		tfCashState.setForeground(Color.BLACK);
		tfCashState.setFont(new Font("굴림", Font.BOLD, 20));
		panel_2.add(tfCashState);
		tfCashState.setColumns(10);

		tfCashCheck = new JTextField();
		tfCashCheck.setEditable(false);
		tfCashCheck.setForeground(Color.BLACK);
		tfCashCheck.setFont(new Font("굴림", Font.BOLD, 20));
		panel_2.add(tfCashCheck);
		tfCashCheck.setColumns(10);

		tfCalcResult = new JTextField();
		tfCalcResult.setEditable(false);
		tfCalcResult.setForeground(Color.RED);
		tfCalcResult.setFont(new Font("굴림", Font.BOLD, 20));
		panel_2.add(tfCalcResult);
		tfCalcResult.setColumns(10);
	}

}
