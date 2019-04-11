package com.kitri.pos.sales;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;

public class Payment_3 extends JFrame {

	JPanel contentPane;
	JPanel payment_3 = new JPanel();
	JTextField tfP3SM;
	JTextField tfP3CardP;
	JButton btnP3Before;
	JButton btnP3Next;
	JButton btnP3Cancel;
	JLabel lbP3FinalPay;
	JLabel lbP3Payment;
	JTextField tfP3CashP;
	JLabel lbP3PointView;
	JLabel lbP3FinalPayView;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_3 frame = new Payment_3();
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
	public Payment_3() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(payment_3, BorderLayout.CENTER);
		payment_3.setLayout(null);

		tfP3SM = new JTextField();
		tfP3SM.setHorizontalAlignment(SwingConstants.CENTER);
		tfP3SM.setText("\uCE74\uB4DC, \uD604\uAE08 \uC785\uB825\uC774 \uC644\uB8CC \uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		tfP3SM.setBounds(150, 20, 300, 30);
		payment_3.add(tfP3SM);
		tfP3SM.setColumns(10);

		JButton btnP4Input = new JButton("\uC785\uB825\uC644\uB8CC");
		btnP4Input.setBounds(369, 89, 140, 90);
		payment_3.add(btnP4Input);

		tfP3CardP = new JTextField();
		tfP3CardP.setBounds(153, 89, 150, 40);
		payment_3.add(tfP3CardP);
		tfP3CardP.setColumns(10);

		btnP3Before = new JButton("\uC774\uC804\uC73C\uB85C");
		btnP3Before.setBounds(12, 341, 150, 50);
		payment_3.add(btnP3Before);

		btnP3Next = new JButton("\uB2E4\uC74C\uC73C\uB85C");
		btnP3Next.setBounds(412, 341, 150, 50);
		payment_3.add(btnP3Next);

		JLabel lbP4Point = new JLabel("\uC801\uB9BD \uB420 \uD3EC\uC778\uD2B8");
		lbP4Point.setHorizontalAlignment(SwingConstants.CENTER);
		lbP4Point.setBounds(212, 227, 150, 40);
		payment_3.add(lbP4Point);

		btnP3Cancel = new JButton("\uAC70\uB798\uCDE8\uC18C");
		btnP3Cancel.setBounds(212, 341, 150, 50);
		payment_3.add(btnP3Cancel);

		lbP3FinalPay = new JLabel("\uC785\uB825 \uB41C \uCD1D \uAE08\uC561");
		lbP3FinalPay.setHorizontalAlignment(SwingConstants.CENTER);
		lbP3FinalPay.setBounds(397, 227, 150, 40);
		payment_3.add(lbP3FinalPay);

		lbP3Payment = new JLabel("\uACB0\uC81C \uD560 \uAE08\uC561");
		lbP3Payment.setHorizontalAlignment(SwingConstants.CENTER);
		lbP3Payment.setBounds(37, 227, 150, 40);
		payment_3.add(lbP3Payment);

		tfP3CashP = new JTextField();
		tfP3CashP.setColumns(10);
		tfP3CashP.setBounds(153, 139, 150, 40);
		payment_3.add(tfP3CashP);

		JLabel label = new JLabel("\uCE74\uB4DC \uBC1B\uC740 \uAE08\uC561");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 89, 129, 40);
		payment_3.add(label);

		JLabel label_1 = new JLabel("\uD604\uAE08 \uBC1B\uC740 \uAE08\uC561");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 139, 129, 40);
		payment_3.add(label_1);

		JLabel lbP4PaymentView = new JLabel("");
		lbP4PaymentView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbP4PaymentView.setHorizontalAlignment(SwingConstants.CENTER);
		lbP4PaymentView.setBounds(37, 277, 150, 40);
		payment_3.add(lbP4PaymentView);

		lbP3PointView = new JLabel("");
		lbP3PointView.setHorizontalAlignment(SwingConstants.CENTER);
		lbP3PointView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbP3PointView.setBounds(212, 277, 150, 40);
		payment_3.add(lbP3PointView);

		lbP3FinalPayView = new JLabel("");
		lbP3FinalPayView.setHorizontalAlignment(SwingConstants.CENTER);
		lbP3FinalPayView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbP3FinalPayView.setBounds(397, 277, 150, 40);
		payment_3.add(lbP3FinalPayView);
	}
}
