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

public class Payment_4 extends JFrame {

	private JPanel contentPane;
	private final JPanel payment_4 = new JPanel();
	private JTextField tfP4SM;
	private JTextField tfP4CardP;
	private JButton btnP4Before;
	private JButton btnP4Next;
	private JButton btnP4Cancel;
	private JLabel lbP4FinalPay;
	private JLabel lbP4Payment;
	private JTextField tfP4CashP;
	private JLabel lbP4PointView;
	private JLabel lbP4FinalPayView;
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Payment_4 frame = new Payment_4();
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
	public Payment_4() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		contentPane.add(payment_4, BorderLayout.CENTER);
		payment_4.setLayout(null);
		
		
		tfP4SM = new JTextField();
		tfP4SM.setHorizontalAlignment(SwingConstants.CENTER);
		tfP4SM.setText("\uCE74\uB4DC, \uD604\uAE08 \uC785\uB825\uC774 \uC644\uB8CC \uB418\uC5C8\uC2B5\uB2C8\uB2E4");
		tfP4SM.setBounds(150, 20, 300, 30);
		payment_4.add(tfP4SM);
		tfP4SM.setColumns(10);
		
		JButton btnP4Input = new JButton("\uC785\uB825\uC644\uB8CC");
		btnP4Input.setBounds(369, 89, 140, 90);
		payment_4.add(btnP4Input);
		
		tfP4CardP = new JTextField();
		tfP4CardP.setBounds(153, 89, 150, 40);
		payment_4.add(tfP4CardP);
		tfP4CardP.setColumns(10);
		
		btnP4Before = new JButton("\uC774\uC804\uC73C\uB85C");
		btnP4Before.setBounds(12, 341, 150, 50);
		payment_4.add(btnP4Before);
		
		btnP4Next = new JButton("\uB2E4\uC74C\uC73C\uB85C");
		btnP4Next.setBounds(412, 341, 150, 50);
		payment_4.add(btnP4Next);
		
		JLabel lbP4Point = new JLabel("\uC801\uB9BD \uB420 \uD3EC\uC778\uD2B8");
		lbP4Point.setHorizontalAlignment(SwingConstants.CENTER);
		lbP4Point.setBounds(212, 227, 150, 40);
		payment_4.add(lbP4Point);
		
		btnP4Cancel = new JButton("\uAC70\uB798\uCDE8\uC18C");
		btnP4Cancel.setBounds(212, 341, 150, 50);
		payment_4.add(btnP4Cancel);
		
		lbP4FinalPay = new JLabel("\uC785\uB825 \uB41C \uCD1D \uAE08\uC561");
		lbP4FinalPay.setHorizontalAlignment(SwingConstants.CENTER);
		lbP4FinalPay.setBounds(397, 227, 150, 40);
		payment_4.add(lbP4FinalPay);
		
		lbP4Payment = new JLabel("\uACB0\uC81C \uD560 \uAE08\uC561");
		lbP4Payment.setHorizontalAlignment(SwingConstants.CENTER);
		lbP4Payment.setBounds(37, 227, 150, 40);
		payment_4.add(lbP4Payment);
		
		tfP4CashP = new JTextField();
		tfP4CashP.setColumns(10);
		tfP4CashP.setBounds(153, 139, 150, 40);
		payment_4.add(tfP4CashP);
		
		JLabel label = new JLabel("\uCE74\uB4DC \uBC1B\uC740 \uAE08\uC561");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(12, 89, 129, 40);
		payment_4.add(label);
		
		JLabel label_1 = new JLabel("\uD604\uAE08 \uBC1B\uC740 \uAE08\uC561");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setBounds(12, 139, 129, 40);
		payment_4.add(label_1);
		
		JLabel lbP4PaymentView = new JLabel("");
		lbP4PaymentView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbP4PaymentView.setHorizontalAlignment(SwingConstants.CENTER);
		lbP4PaymentView.setBounds(37, 277, 150, 40);
		payment_4.add(lbP4PaymentView);
		
		lbP4PointView = new JLabel("");
		lbP4PointView.setHorizontalAlignment(SwingConstants.CENTER);
		lbP4PointView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbP4PointView.setBounds(212, 277, 150, 40);
		payment_4.add(lbP4PointView);
		
		lbP4FinalPayView = new JLabel("");
		lbP4FinalPayView.setHorizontalAlignment(SwingConstants.CENTER);
		lbP4FinalPayView.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		lbP4FinalPayView.setBounds(397, 277, 150, 40);
		payment_4.add(lbP4FinalPayView);
	}
}
