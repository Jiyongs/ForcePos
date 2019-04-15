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
import javax.swing.JMenuItem;
import java.awt.Font;

public class Payment_1 extends JFrame {

	JPanel contentPane;
	final JPanel payment_1 = new JPanel();
	JTextField tfP1SM;
	JTextField tfP1BeforePrice;
	JTextField tfP1DiscountPercent;
	JTextField tfP1Afterprice;
	JButton btnP1Before;
	JButton btnP1Next;
	JLabel lbP1Afterprice;
	JLabel lbP1Discountpercent;
	Payment_2 payment_2 = new Payment_2();
	Payment_3 payment_3 = new Payment_3();
	JComboBox cbP1Cooperation;
	JButton btnP1Apply;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_1 frame = new Payment_1();
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
	public Payment_1() {
		setTitle("\uD560\uC778");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(payment_1, BorderLayout.CENTER);
		payment_1.setLayout(null);

		tfP1SM = new JTextField();
		tfP1SM.setText("\uD560\uC778\uC801\uC6A9\uB418\uC5C8\uC2B5\uB2C8\uB2E4.");
		tfP1SM.setBounds(150, 20, 300, 30);
		payment_1.add(tfP1SM);
		tfP1SM.setColumns(10);

		cbP1Cooperation = new JComboBox();
		cbP1Cooperation.setBounds(100, 100, 200, 50);
		payment_1.add(cbP1Cooperation);
		cbP1Cooperation.addItem("없음");
		cbP1Cooperation.addItem("SK");
		cbP1Cooperation.addItem("KT");
		cbP1Cooperation.addItem("LG");
		

		btnP1Apply = new JButton("\uC801\uC6A9");
		btnP1Apply.setBounds(350, 100, 150, 50);
		payment_1.add(btnP1Apply);

		tfP1BeforePrice = new JTextField();
		tfP1BeforePrice.setFont(new Font("굴림", Font.BOLD, 18));
		tfP1BeforePrice.setEditable(false);
		tfP1BeforePrice.setBounds(100, 220, 150, 50);
		payment_1.add(tfP1BeforePrice);
		tfP1BeforePrice.setColumns(10);

		tfP1DiscountPercent = new JTextField();
		tfP1DiscountPercent.setEditable(false);
		tfP1DiscountPercent.setFont(new Font("굴림", Font.BOLD, 18));
		tfP1DiscountPercent.setHorizontalAlignment(SwingConstants.CENTER);
		tfP1DiscountPercent.setBounds(265, 220, 70, 50);
		payment_1.add(tfP1DiscountPercent);
		tfP1DiscountPercent.setColumns(10);

		tfP1Afterprice = new JTextField();
		tfP1Afterprice.setEditable(false);
		tfP1Afterprice.setColumns(10);
		tfP1Afterprice.setBounds(350, 220, 150, 50);
		payment_1.add(tfP1Afterprice);

		btnP1Before = new JButton("\uAC70\uB798\uCDE8\uC18C");
		btnP1Before.setBounds(100, 310, 150, 50);
		payment_1.add(btnP1Before);

		btnP1Next = new JButton("\uB2E4\uC74C\uC73C\uB85C");
		btnP1Next.setBounds(350, 310, 150, 50);
		payment_1.add(btnP1Next);

		JLabel lbP1Beforeprice = new JLabel("\uD560\uC778\uC801\uC6A9 \uC804 \uAC00\uACA9");
		lbP1Beforeprice.setHorizontalAlignment(SwingConstants.CENTER);
		lbP1Beforeprice.setBounds(100, 160, 150, 50);
		payment_1.add(lbP1Beforeprice);

		lbP1Afterprice = new JLabel("\uD560\uC778\uC801\uC6A9 \uD6C4 \uAC00\uACA9");
		lbP1Afterprice.setHorizontalAlignment(SwingConstants.CENTER);
		lbP1Afterprice.setBounds(350, 160, 150, 50);
		payment_1.add(lbP1Afterprice);

		lbP1Discountpercent = new JLabel("\uD560\uC778\uC728");
		lbP1Discountpercent.setHorizontalAlignment(SwingConstants.CENTER);
		lbP1Discountpercent.setBounds(265, 160, 70, 50);
		payment_1.add(lbP1Discountpercent);

	}
}
