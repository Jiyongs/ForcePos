package com.kitri.pos.sales;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class Payment_2 extends JFrame {

	private JPanel contentPane;
	private final JPanel payment_2 = new JPanel();
//	Payment_1 payment_1 = new Payment_1();
//	Payment_3 payment_3 = new Payment_3();
	JTextField tfP2SM;
	JTextField tfP2phoneNum;
	JTextField tfP2UsePoint;
	JButton btnP2Before;
	JButton btnP2Next;
	JLabel lbP2Aftertotal;
	JButton btnP2Cancel;
	JLabel lbP2point;
	JLabel lbP2UsePoint;
	JButton btnP2Apply;
	JButton btnP2Reference;
	JTextField tfP2Aftertotal;
	JTextField tfP2point;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_2 frame = new Payment_2();
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
	public Payment_2() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(payment_2, BorderLayout.CENTER);
		payment_2.setLayout(null);
		
		
		tfP2SM = new JTextField();
		tfP2SM.setHorizontalAlignment(SwingConstants.CENTER);
		tfP2SM.setText("010 - **** - **** \uB2D8\uC758 \uBA64\uBC84\uC27D\uC774 \uD655\uC778\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
		tfP2SM.setBounds(150, 20, 300, 30);
		payment_2.add(tfP2SM);
		tfP2SM.setColumns(10);
		
		btnP2Reference = new JButton("\uC870\uD68C");
		btnP2Reference.setBounds(398, 70, 140, 50);
		payment_2.add(btnP2Reference);
		
		tfP2phoneNum = new JTextField();
		tfP2phoneNum.setHorizontalAlignment(SwingConstants.CENTER);
		tfP2phoneNum.setBounds(212, 76, 150, 40);
		payment_2.add(tfP2phoneNum);
		tfP2phoneNum.setColumns(10);
		
		tfP2UsePoint = new JTextField();
		tfP2UsePoint.setHorizontalAlignment(SwingConstants.CENTER);
		tfP2UsePoint.setColumns(10);
		tfP2UsePoint.setBounds(212, 205, 150, 40);
		payment_2.add(tfP2UsePoint);
		
		btnP2Before = new JButton("\uC774\uC804\uC73C\uB85C");
		btnP2Before.setBounds(12, 341, 150, 50);
		payment_2.add(btnP2Before);
		
		btnP2Next = new JButton("\uB2E4\uC74C\uC73C\uB85C");
		btnP2Next.setBounds(412, 341, 150, 50);
		payment_2.add(btnP2Next);
		
		JLabel lbP2phoneNum = new JLabel("전화번호");
		lbP2phoneNum.setHorizontalAlignment(SwingConstants.CENTER);
		lbP2phoneNum.setBounds(30, 76, 150, 40);
		payment_2.add(lbP2phoneNum);
		
		lbP2Aftertotal = new JLabel("남은 금액");
		lbP2Aftertotal.setHorizontalAlignment(SwingConstants.CENTER);
		lbP2Aftertotal.setBounds(30, 277, 150, 40);
		payment_2.add(lbP2Aftertotal);
		
		btnP2Cancel = new JButton("\uAC70\uB798\uCDE8\uC18C");
		btnP2Cancel.setBounds(212, 341, 150, 50);
		payment_2.add(btnP2Cancel);
		
		lbP2point = new JLabel("포인트현황");
		lbP2point.setHorizontalAlignment(SwingConstants.CENTER);
		lbP2point.setBounds(30, 136, 150, 40);
		payment_2.add(lbP2point);
		
		lbP2UsePoint = new JLabel("사용할 포인트");
		lbP2UsePoint.setHorizontalAlignment(SwingConstants.CENTER);
		lbP2UsePoint.setBounds(30, 205, 150, 40);
		payment_2.add(lbP2UsePoint);
		
		btnP2Apply = new JButton("\uC801\uC6A9");
		btnP2Apply.setBounds(398, 199, 140, 50);
		payment_2.add(btnP2Apply);
		
		tfP2Aftertotal = new JTextField();
		tfP2Aftertotal.setEditable(false);
		tfP2Aftertotal.setHorizontalAlignment(SwingConstants.CENTER);
		tfP2Aftertotal.setColumns(10);
		tfP2Aftertotal.setBounds(212, 276, 150, 40);
		payment_2.add(tfP2Aftertotal);
		
		tfP2point = new JTextField();
		tfP2point.setEditable(false);
		tfP2point.setHorizontalAlignment(SwingConstants.CENTER);
		tfP2point.setColumns(10);
		tfP2point.setBounds(212, 136, 150, 40);
		payment_2.add(tfP2point);
		
		
	}
}
