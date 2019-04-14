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
	JTextField tfP2PhoneNum;
	JTextField tfP2Point;
	JButton btnP2Before;
	JButton btnP2Next;
	JLabel lbP2AfterPoint;
	JButton btnP2Cancel;
	JLabel lbP2point;
	JLabel lbP2UsePoint;
	JButton btnP2Register;
	JButton btnP2Save;
	JButton btnP2Apply;
	

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
		
		JButton btnP2Reference = new JButton("\uC870\uD68C");
		btnP2Reference.setBounds(350, 70, 150, 50);
		payment_2.add(btnP2Reference);
		
		tfP2PhoneNum = new JTextField();
		tfP2PhoneNum.setBounds(100, 70, 150, 40);
		payment_2.add(tfP2PhoneNum);
		tfP2PhoneNum.setColumns(10);
		
		tfP2Point = new JTextField();
		tfP2Point.setColumns(10);
		tfP2Point.setBounds(100, 205, 150, 40);
		payment_2.add(tfP2Point);
		
		btnP2Before = new JButton("\uC774\uC804\uC73C\uB85C");
		btnP2Before.setBounds(12, 341, 150, 50);
		payment_2.add(btnP2Before);
		
		btnP2Next = new JButton("\uB2E4\uC74C\uC73C\uB85C");
		btnP2Next.setBounds(412, 341, 150, 50);
		payment_2.add(btnP2Next);
		
		JLabel lbP2CheckUser = new JLabel("\uB4F1\uB85D\uC720\uBB34(\uB4F1\uB85D,\uBBF8\uB4F1\uB85D)");
		lbP2CheckUser.setHorizontalAlignment(SwingConstants.CENTER);
		lbP2CheckUser.setBounds(100, 115, 150, 40);
		payment_2.add(lbP2CheckUser);
		
		lbP2AfterPoint = new JLabel("\uB0A8\uC740 \uAE08\uC561(Total?)");
		lbP2AfterPoint.setHorizontalAlignment(SwingConstants.CENTER);
		lbP2AfterPoint.setBounds(100, 285, 150, 40);
		payment_2.add(lbP2AfterPoint);
		
		btnP2Cancel = new JButton("\uAC70\uB798\uCDE8\uC18C");
		btnP2Cancel.setBounds(212, 341, 150, 50);
		payment_2.add(btnP2Cancel);
		
		lbP2point = new JLabel("\uD3EC\uC778\uD2B8\uD604\uD669(Point)");
		lbP2point.setHorizontalAlignment(SwingConstants.CENTER);
		lbP2point.setBounds(100, 155, 150, 40);
		payment_2.add(lbP2point);
		
		lbP2UsePoint = new JLabel("\uC801\uC6A9\uD560 \uD3EC\uC778\uD2B8(point)");
		lbP2UsePoint.setHorizontalAlignment(SwingConstants.CENTER);
		lbP2UsePoint.setBounds(100, 245, 150, 40);
		payment_2.add(lbP2UsePoint);
		
		btnP2Register = new JButton("\uB4F1\uB85D");
		btnP2Register.setBounds(350, 130, 150, 50);
		payment_2.add(btnP2Register);
		
		btnP2Save = new JButton("\uC801\uB9BD");
		btnP2Save.setBounds(350, 190, 150, 50);
		payment_2.add(btnP2Save);
		
		btnP2Apply = new JButton("\uC801\uC6A9");
		btnP2Apply.setBounds(350, 254, 150, 50);
		payment_2.add(btnP2Apply);
		
		
	}

	
}
