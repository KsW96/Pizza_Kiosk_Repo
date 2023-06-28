package frame;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;

import Function.MainOrder;
import Function.Sql_Methods;
import img.imageIcon;
import utilty.invisibility;

public class MenuFrame extends JFrame {
	private imageIcon icon = new imageIcon();
	private JLayeredPane jlp;
	private invisibility util = new invisibility();
	private JPanel panel;
	private JButton pizzaBtn;
	private JButton sideBtn;
	private JButton drinkBtn;
	private JButton makePizzaBtn;
	private int mainOrderCount = 0; // order 누르면 ++ 되게
	private int detailOrderCount = 0; // 피자 - 담기누를때 ++ 사이드,음료 - 담을때마다 ++

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuFrame frame = new MenuFrame();
					frame.setVisible(true);
					frame.requestFocus();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuFrame() {
		mainOrderCount++;
		MainOrder mo = new MainOrder(mainOrderCount); // total_price는 마지막에 주문완료할때 넣어주자
		// detailorder랑 menuitem은 피자만들어서 담기할때랑 사이드,음료 눌러서 넣을때 생성해서
		// menuitem이 포함된 detailorder - 피자, 나만의피자
		// menuitem이 없는 detailorder - 사이드, 음료

		exiteKey();

		FrameSetting();

		pizzaTapBtn(mo);

		buttonSetting();// 버튼 생성 메소드

		pizzaBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				pizzaBtn.setIcon(icon.getwhitePizzaBtn());

				sideBtn.setIcon(icon.getdarkSideBtn());
				drinkBtn.setIcon(icon.getdarkDrinkBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());

				JLabel menulist = new JLabel();

			}
		});

		sideBtn.addActionListener(new ActionListener() {
			// 사이드버튼 액션리스너
			@Override
			public void actionPerformed(ActionEvent e) {

				sideBtn.setIcon(icon.getwhiteSideBtn());
				// 버튼을 눌렀을 때 해당버튼 외 버튼들을 회색으로 바꿈
				pizzaBtn.setIcon(icon.getdarkPizzaBtn());
				drinkBtn.setIcon(icon.getdarkDrinkBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());

				System.out.println("사이드 탭 액션 진입");

			}
		});

		drinkBtn.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				drinkBtn.setIcon(icon.getwhiteDrinkBtn());

				pizzaBtn.setIcon(icon.getdarkPizzaBtn());
				sideBtn.setIcon(icon.getdarkSideBtn());
				makePizzaBtn.setIcon(icon.getdarkMakePizzaBtn());

			}
		});

		makePizzaBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				pizzaBtn.setIcon(icon.getdarkPizzaBtn());
				sideBtn.setIcon(icon.getdarkSideBtn());
				drinkBtn.setIcon(icon.getdarkDrinkBtn());

				makePizzaBtn.setIcon(icon.getwhiteMakePizzaBtn());
			}
		});

		util.invisible(pizzaBtn);
		util.invisible(sideBtn);
		util.invisible(drinkBtn);
		util.invisible(makePizzaBtn);

		JLayeredPane menuPnl = new JLayeredPane();
		menuPnl.setBounds(0, 665, 800, -468);
		jlp.add(menuPnl, new Integer(2));

	}

	private void FrameSetting() {
		JLabel lbl = new JLabel(icon.getMenuFrame());
		lbl.setBounds(0, 0, 800, 900);

		jlp = new JLayeredPane();
		jlp.setPreferredSize(new Dimension(icon.getMainFrame().getIconWidth(), icon.getMainFrame().getIconHeight()));
		jlp.setLayout(null);

		jlp.add(lbl);

		setContentPane(jlp);

		setUndecorated(true);
		setVisible(true);
		setSize(800, 900);
		setLocationRelativeTo(null);

		exiteKey();

	}

	private void buttonSetting() {

		pizzaBtn = new JButton(icon.getwhitePizzaBtn());
		pizzaBtn.setBounds(0, 150, 200, 50);
		jlp.add(pizzaBtn, new Integer(3));
		// util.invisible(pizzaBtn);

		sideBtn = new JButton();
		sideBtn.setBounds(200, 150, 200, 50);
		jlp.add(sideBtn, new Integer(3));

		drinkBtn = new JButton();
		drinkBtn.setBounds(400, 150, 200, 50);
		jlp.add(drinkBtn, new Integer(3));

		makePizzaBtn = new JButton();
		makePizzaBtn.setBounds(600, 150, 200, 50);
		jlp.add(makePizzaBtn, new Integer(3));

	}

	// 팝업화면으로 이동시 mainorder정보를 가지고가서 detailorder 입력해주기
	private void pizzaTapBtn(MainOrder mo) {
		Sql_Methods sqm = new Sql_Methods();

		// 피자 버튼 눌렀을시 동작하는 메소드
		// 사이드 음료는 detailorder 바로 만들어서 mainorder에 넣어주기
		ActionListener al = new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				JButton a = (JButton) e.getSource();
				String target = a.getText();
				Pizza_PopUp_Frame ppf = new Pizza_PopUp_Frame(target, mo);
				ppf.setVisible(true);
			}
		};

		JButton btn1 = new JButton("불고기피자");
		btn1.setBounds(50, 300, 200, 50);
		btn1.addActionListener(al);
		jlp.add(btn1, new Integer(3));

		JButton btn2 = new JButton("페퍼로니피자");
		btn2.setBounds(300, 300, 200, 50);
		btn2.addActionListener(al);
		jlp.add(btn2, new Integer(3));

		JButton btn3 = new JButton("쉬림프피자");
		btn3.setBounds(550, 300, 200, 50);
		btn3.addActionListener(al);
		jlp.add(btn3, new Integer(3));

		JButton btn4 = new JButton("치즈피자");
		btn4.setBounds(50, 400, 200, 50);
		btn4.addActionListener(al);
		jlp.add(btn4, new Integer(3));

		JButton btn5 = new JButton("콤비네이션피자");
		btn5.setBounds(300, 400, 200, 50);
		btn5.addActionListener(al);
		jlp.add(btn5, new Integer(3));

		JButton btn6 = new JButton("포테이토피자");
		btn6.setBounds(550, 400, 200, 50);
		btn6.addActionListener(al);
		jlp.add(btn6, new Integer(3));

	}

	private void exiteKey() {
		addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				int exite = e.getKeyCode();
				System.out.println("종료");
				if (exite == KeyEvent.VK_ESCAPE) {
					System.exit(0);

				}
			}
		});
	}
}