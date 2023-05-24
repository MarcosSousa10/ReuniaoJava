package Janela;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class SegundaJanela extends JFrame {
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();
	Objetos obj = new Objetos();
	JLabel usu= new JLabel();
	private JRadioButton um;
	private JRadioButton dois;
	private RadioButtonHandler handler;
	private ButtonGroup grupo1;
	JFormattedTextField datas;
	JMenuBar menubar = new JMenuBar();
	JComboBox<String> box;

	public SegundaJanela() {
		
		setIconImage(obj.icone());
		setSize(d.width-300, d.height-200);
		setTitle("Othon de Carvalho");
	//	setExtendedState(JFrame.MAXIMIZED_BOTH);

	//	setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		
		
		setLayout(null);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				adjustComponents();
			}
		});
		radio();
		obj.mascara("##/##/####", 320, 80, 100, 30);
		add(obj.getMarcara());
		obj.mascara("##:##", 500, 80, 100, 30);
		add(obj.getMarcara());
		setVisible(true);
		setLocationRelativeTo(null);
		adjustComponents();
		add(obj.getImagem());
        usu.setFont(new java.awt.Font("Ubuntu", 3, 25));
		usu.setBounds( 320, 150, 100, 30);
		add(usu);
	}

	public void radio() {
		grupo1 = new ButtonGroup();
		um = new JRadioButton("um", false);
		dois = new JRadioButton("dois", false);
		handler = new RadioButtonHandler();
		grupo1.add(um);
		grupo1.add(dois);
		um.addItemListener(handler);
		dois.addItemListener(handler);
		// um.setBounds(320, 40, 50, 30);
		// dois.setBounds(390, 40, 50, 30);
		setLayout(null);
		add(um);
		add(dois);

	}

	private class RadioButtonHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (um.isSelected())
				JOptionPane.showMessageDialog(null, "Parabéns");
			if (dois.isSelected())
				JOptionPane.showMessageDialog(null, "troxa!");
		}

	}

	private void adjustComponents() {
		int width = getWidth();
		int height = getHeight();

		int radioX = (int) (width * 0.4);
		int radioY = (int) (height * 0.05);
		int radioWidth = (int) (width * 0.1);
		int radioHeight = (int) (height * 0.03);
		um.setBounds(radioX, radioY, radioWidth, radioHeight);
		dois.setBounds(radioX + radioWidth + 10, radioY, radioWidth, radioHeight);
		int maskX = (int) (width * 0.4);
		int maskY = (int) (height * 0.1);
		int maskWidth = (int) (width * 0.1);
		int maskHeight = (int) (height * 0.03);
		obj.getMarcara().setBounds(maskX, maskY, maskWidth, maskHeight);
		int X = (int) (width*0.30);
		int Y = (int) (height*0.11);
		int Width = (int) ( width*0.35);
		int Height = (int) (height*0.80);
		obj.imagem("/Imagens/verdadeiro.png",X , Y,Width ,Height ,Width, Height);
		repaint();

	}
	public void recebendo(String recebe) {
		 recebe = recebe.substring(0,1).toUpperCase().concat(recebe.substring(1));
        usu.setText(recebe);
    }

}
