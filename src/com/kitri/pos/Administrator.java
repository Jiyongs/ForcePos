package com.kitri.pos;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.table.*;
import javax.swing.border.EmptyBorder;

import java.awt.event.ActionListener;
import java.awt.*;
import java.awt.event.ActionEvent;

public class Administrator extends JFrame implements ActionListener {

	private JPanel contentPane;
	private JTextField notice;
	ForcePos frame = new ForcePos();
	private DefaultTableModel tm;
	private JTable table;
	Administrator administrator;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Administrator administrator = new Administrator();
					administrator.setVisible(true);
					ForcePos forcepos = new ForcePos();
					forcepos.setVisible(true);
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

//	public void showFrameTest() {
//		frame1.setVisible(true);
//		frame.dispose();
//	}
	public static void tableCellCenter(JTable table) {
		DefaultTableCellRenderer dtcr = new DefaultTableCellRenderer(); // µðÆúÆ®Å×ÀÌºí¼¿·»´õ·¯¸¦ »ý¼º
		dtcr.setHorizontalAlignment(SwingConstants.CENTER); // ·»´õ·¯ÀÇ °¡·ÎÁ¤·ÄÀ» CENTER·Î

		TableColumnModel tcm = table.getColumnModel(); // Á¤·ÄÇÒ Å×ÀÌºíÀÇ ÄÃ·³¸ðµ¨À» °¡Á®¿È

		// ÀüÃ¼ ¿­¿¡ ÁöÁ¤
		for (int i = 0; i < tcm.getColumnCount(); i++) {
			tcm.getColumn(i).setCellRenderer(dtcr);
		}
	}
	
	public boolean showFrame() {
		boolean viewFrame = true;
		frame.setVisible(showFrame());
		return viewFrame;		
//		this.frame = frame;
//		administrator.setVisible(false);
	}
	
// Àü¿ªº¯¼öÀÇ Å×ÀÌºí
//	public Administrator(JTable table) {
//		this.table = table;
//	}

	public Administrator() {
		setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		setTitle("Force.pos");
		setAlwaysOnTop(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(10, 15, 1326, 753);
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

		String colName[] = { "À¯ÀúÄÚµå", "À¯ÀúÀÌ¸§", "ºÐ·ù" };
		Object data[][] = { { "1", "°³³ª¸®", "ÁÖ°£1" }, { "2", "³ë¿À¶õ", "¾ß°£1" }, { "3", "²É±×´Ã", "ÁÖ°£2" },
				{ "4", "ÃÖ¾Æ·¡", "¾ß°£2" } };

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

		JButton userInsert = new JButton("\uC720\uC800\uB4F1\uB85D");
		userInsert.setForeground(new Color(255, 255, 255));
		userInsert.setBackground(new Color(0, 0, 128));
		userInsert.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		userInsert.setBounds(0, 0, 164, 120);
		pSellFunction.add(userInsert);

		JButton userUpdate = new JButton("\uC720\uC800\uC218\uC815");
		userUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		userUpdate.setBackground(new Color(100, 149, 237));
		userUpdate.setForeground(new Color(255, 255, 255));
		userUpdate.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		userUpdate.setBounds(0, 130, 164, 120);
		pSellFunction.add(userUpdate);

		JButton userDelete = new JButton("\uC720\uC800\uC0AD\uC81C");
		userDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		userDelete.setBackground(new Color(0, 0, 128));
		userDelete.setForeground(new Color(255, 255, 255));
		userDelete.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		userDelete.setBounds(0, 260, 164, 120);
		pSellFunction.add(userDelete);

		JButton sBtnPdChange = new JButton("\uCD9C\uACB0");
		sBtnPdChange.setBackground(new Color(100, 149, 237));
		sBtnPdChange.setForeground(new Color(255, 255, 255));
		sBtnPdChange.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		sBtnPdChange.setBounds(0, 390, 164, 120);
		pSellFunction.add(sBtnPdChange);

		JButton logout = new JButton("\uB85C\uADF8\uC544\uC6C3");
		logout.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		logout.setBackground(new Color(255, 69, 0));
		logout.setForeground(new Color(255, 255, 255));
		logout.setFont(new Font("¸¼Àº °íµñ", Font.BOLD, 20));
		logout.setBounds(0, 520, 164, 120);
		pSellFunction.add(logout);

		JPanel pMonitor = new JPanel();
		pMonitor.setSize(new Dimension(1144, 533));
		pMonitor.setBackground(new Color(255, 255, 255));
		pMonitor.setBounds(0, 50, 1144, 533);
		contentPane.add(pMonitor);
		pMonitor.setLayout(new CardLayout(0, 0));

		tm = new DefaultTableModel(data, colName);
		table = new JTable(tm);
		
		table.setRowHeight(60);
		tableCellCenter(table);
		table.getColumn("À¯ÀúÄÚµå").setPreferredWidth(5);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(0, 0, 1144, 533);
		pMonitor.add(scrollPane, "name_6176959994573");

		userDelete.addActionListener(this);
		logout.addActionListener(this);
		userInsert.addActionListener(this);
//		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
//		celAlignCenter.setHorizontalAlignment(table);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		Object ob = e.getActionCommand();
		
		if(ob.equals("À¯Àúµî·Ï")) {
			DefaultTableModel tm = (DefaultTableModel) table.getModel();
			String add[] = {"1", "±èÀÇ¿¬", "ÁÖ°£"};
			tm.addRow(add);
		}
		
		if (ob.equals("À¯Àú»èÁ¦")) {
			// Å×ÀÌºí¿¡¼­ ³»°¡ ¼±ÅÃÇÑ Çà¹øÈ£
			int number = table.getSelectedRow();
//			System.out.println(number);
			DefaultTableModel tm = (DefaultTableModel) table.getModel();
				if(number >= 0 && number < table.getRowCount()) {
					tm.removeRow(number);
				}
			if (ob.equals("·Î±×¾Æ¿ô")) {
				showFrame();
				
			}

		}

	}
}
