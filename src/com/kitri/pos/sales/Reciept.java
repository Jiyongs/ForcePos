package com.kitri.pos;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Rectangle;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JButton;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.GridLayout;
import java.awt.Color;
import javax.swing.JList;

public class Reciept extends JFrame {

	private JPanel contentPane;
	JButton cancel;
	JButton print;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Reciept frame = new Reciept();
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
	public Reciept() {
		setCursor(Cursor.getPredefinedCursor(Cursor.DEFAULT_CURSOR));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 369, 598);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(0, 0, 128));
		contentPane.setSize(new Dimension(450, 700));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(255, 255, 255));
		panel.setBounds(12, 73, 328, 394);
		contentPane.add(panel);
		panel.setLayout(new GridLayout(0, 1, 5, 5));
		
		JLabel date = new JLabel("\uAC70\uB798\uC77C\uC790");
		panel.add(date);
		
		JLabel product_list = new JLabel("\uC0C1\uD488\uB9AC\uC2A4\uD2B8");
		panel.add(product_list);
		
		JList list = new JList();
		panel.add(list);
		
		JLabel barcode = new JLabel("\uBC14\uCF54\uB4DC");
		panel.add(barcode);
		
		JLabel total_price = new JLabel("\uCD1D \uAE08\uC561");
		panel.add(total_price);
		
		JLabel cash = new JLabel("\uD604\uAE08 \uAE08\uC561");
		panel.add(cash);
		
		JLabel credit_card = new JLabel("\uCE74\uB4DC \uAE08\uC561");
		panel.add(credit_card);
		
		JLabel actual_price = new JLabel("\uACB0\uC81C\uAE08\uC561");
		panel.add(actual_price);
		
		JLabel point = new JLabel("\uD3EC\uC778\uD2B8 \uC801\uB9BD");
		point.setName("point");
		panel.add(point);
		
		JLabel id = new JLabel("\uAC70\uB798\uBC88\uD638");
		id.setName("id");
		panel.add(id);
		
		JLabel force_pos = new JLabel("ForcePos");
		panel.add(force_pos);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBackground(new Color(0, 0, 128));
		panel_1.setBounds(12, 477, 328, 73);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		cancel = new JButton("\uCDE8\uC18C");
		cancel.setFont(new Font("굴림", Font.PLAIN, 15));
		cancel.setBounds(60, 10, 97, 53);
		panel_1.add(cancel);
		
		print = new JButton("\uCD9C\uB825");
		print.setFont(new Font("굴림", Font.PLAIN, 15));
		print.setBounds(167, 10, 97, 53);
		panel_1.add(print);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(255, 255, 255));
		panel_2.setBounds(12, 10, 329, 53);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\uC601 \uC218 \uC99D");
		lblNewLabel.setBackground(new Color(255, 255, 255));
		lblNewLabel.setFont(new Font("굴림", Font.BOLD, 18));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(12, 10, 305, 33);
		panel_2.add(lblNewLabel);
	}
}
