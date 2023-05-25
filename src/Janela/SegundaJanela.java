package Janela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.ParseException;

import javax.swing.BorderFactory;
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
import javax.swing.border.BevelBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class SegundaJanela extends JFrame {
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();
	Objetos obj = new Objetos();
	JLabel usu = new JLabel();
	JLabel nome = new JLabel();
	JLabel data = new JLabel();
	JLabel hora = new JLabel();
	JLabel hora2 = new JLabel();

	JLabel sala = new JLabel();
	private JRadioButton um;
	private JRadioButton dois;
	private RadioButtonHandler handler;
	private ButtonGroup grupo1;
	JFormattedTextField datas;
	JFormattedTextField horas;
	JFormattedTextField horae;
	JMenuBar menubar = new JMenuBar();
	JComboBox<String> box;
	JTextField sNome = new JTextField();

	public SegundaJanela() {

		setIconImage(obj.icone());
		setSize(d.width - 400, d.height - 300);
		setTitle("Othon de Carvalho");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				adjustComponents();
			};
		});		
		
		nome.setText("Nome:");
		nome.setFont(new Font("Ubuntu", 3, 25));
		nome.setForeground(new Color(1, 1, 1));
		add(nome);
		sNome.setFont(new Font("Ubuntu", 2, 15));
		sNome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		add(sNome);
		sala.setText("Escolha uma opção:");
		sala.setFont(new Font("Ubuntu", 3, 25));
		sala.setForeground(new Color(1, 1, 1));
		add(sala);
		data.setText("Data:");
		data.setFont(new Font("Ubuntu", 3, 25));
		data.setForeground(new Color(1, 1, 1));
		add(data);
		hora.setText("Hora de entrada:");
		hora.setFont(new Font("Ubuntu", 3, 25));
		hora.setForeground(new Color(1, 1, 1));
		add(hora);
		hora2.setText("Hora de saida:");
		hora2.setFont(new Font("Ubuntu", 3, 25));
		hora2.setForeground(new Color(1, 1, 1));
		add(hora2);
		
		
		radio();
		usu.setFont(new java.awt.Font("Ubuntu", 3, 25));

		setLocationRelativeTo(null);	
		try {
			MaskFormatter mascaraData = new MaskFormatter("##/##/####");
			mascaraData.setPlaceholderCharacter('_');
			datas = new JFormattedTextField(mascaraData);
			

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			MaskFormatter mascaraHora = new MaskFormatter("##:##");
			mascaraHora.setPlaceholderCharacter('_');
			horas = new JFormattedTextField(mascaraHora);
			

		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			MaskFormatter mascaraHorae = new MaskFormatter("##:##");
			mascaraHorae.setPlaceholderCharacter('_');
			horae = new JFormattedTextField(mascaraHorae);	
		} catch (ParseException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		adjustComponents();
		datas.setFont(new Font("Ubuntu", 2, 20));
		datas.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(datas);
		horas.setFont(new Font("Ubuntu", 2, 20));
		horas.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(horas);
		horae.setFont(new Font("Ubuntu", 2, 20));
		horae.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(horae);
		add(obj.getImagem());
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
		um.setBounds(radioX - 250, radioY + 59, radioWidth - 50, radioHeight);
		dois.setBounds(radioX - 150, radioY + 59, radioWidth - 50, radioHeight);
		int X = (int) (width * 0.30);
		int Y = (int) (height * 0.11);
		int Width = (int) (width * 0.35);
		int Height = (int) (height * 0.80);
		datas.setBounds(radioX - 430, radioY + 120, radioWidth-42, radioHeight+9);
		horas.setBounds(radioX - 300, radioY + 240, radioWidth-92, radioHeight+9);
		horae.setBounds(radioX - 300, radioY + 180, radioWidth-92, radioHeight+9);
		obj.imagem("/Imagens/verdadeiro.png", X, Y, Width, Height, Width, Height);
		nome.setBounds(radioX - 500, radioY, radioWidth, radioHeight);
		usu.setBounds(radioX + 700, radioY, radioWidth, radioHeight);

		sala.setBounds(radioX - 500, radioY + 60, radioWidth + 130, radioHeight+9);
		data.setBounds(radioX - 500, radioY + 120, radioWidth, radioHeight);
		hora.setBounds(radioX - 500, radioY + 180, radioWidth + 100, radioHeight);
		hora2.setBounds(radioX - 500, radioY + 240, radioWidth + 100, radioHeight);
		sNome.setBounds(radioX - 400, radioY, radioWidth + 300, radioHeight + 9);

		repaint();

	}

	public void recebendo(String recebe) {
		recebe = recebe.substring(0, 1).toUpperCase().concat(recebe.substring(1));
		usu.setText(recebe);
	}

}
