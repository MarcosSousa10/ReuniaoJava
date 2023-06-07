package Janela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.MenuBar;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import com.toedter.calendar.*;

import Conexao.Banco;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerDateModel;
import javax.swing.Spring;
import javax.swing.border.BevelBorder;
import javax.swing.text.MaskFormatter;

public class SegundaJanela extends JFrame {
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();
	Objetos obj = new Objetos();
	JLabel usu = new JLabel();
	JLabel nome = new JLabel("nome");
	JLabel data = new JLabel();
	JLabel hora = new JLabel();
	JLabel hora2 = new JLabel();
	JDateChooser dateChooser = new JDateChooser();
	SpinnerDateModel spinnerModel = new SpinnerDateModel();
	JButton salvar = new JButton();
	JButton listar = new JButton();
	JSpinner timeSpinner = new JSpinner(spinnerModel);
	JLabel sala = new JLabel();
	JRadioButton um;
	JRadioButton dois;
	RadioButtonHandler handler;
	ButtonGroup grupo1;
	JFormattedTextField horas;
	JFormattedTextField horae;
	JMenuBar menuBar = new JMenuBar();
	JComboBox<String> box;
	JTextField sNome = new JTextField();
	String escolhaRadio;
	String formattedDate;
	String idusuario;
	Reuniao enciartexto;
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
		setJMenuBar(menuBar);
		JMenu fileMenu = new JMenu("Opções");
		JMenu editMenu = new JMenu("Informações");
		menuBar.add(fileMenu);
		menuBar.add(editMenu);
		JMenuItem newAction = new JMenuItem("New");
		JMenuItem openAction = new JMenuItem("Open");
		JMenuItem exitAction = new JMenuItem("Exit");
		JMenuItem cutAction = new JMenuItem("Cut");
		JMenuItem copyAction = new JMenuItem("Copy");
		JMenuItem pasteAction = new JMenuItem("Paste");
		// Cria e aiciona um CheckButton como um item de menu
		JCheckBoxMenuItem checkAction = new JCheckBoxMenuItem("Check Action");
		// Cria e aiciona um RadioButton como um item de menu
		JRadioButtonMenuItem radioAction1 = new JRadioButtonMenuItem("Radio Button1");
		JRadioButtonMenuItem radioAction2 = new JRadioButtonMenuItem("Radio Button2");
		// Cria um ButtonGroup e adiciona os dois radio Button
		ButtonGroup bg = new ButtonGroup();
		bg.add(radioAction1);
		bg.add(radioAction2);
		fileMenu.add(newAction);
		fileMenu.add(openAction);
		fileMenu.add(checkAction);
		fileMenu.addSeparator();
		fileMenu.add(exitAction);
		editMenu.add(cutAction);
		editMenu.add(copyAction);
		editMenu.add(pasteAction);
		nome.setText("Nome:");
		nome.setFont(new Font("Ubuntu", 3, 25));
		nome.setForeground(new Color(1, 1, 1));
		add(nome);
		sNome.setFont(new Font("Ubuntu", 2, 20));
		sNome.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
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
		ButtonEntrar();
		ButtonListar();

		radio();
		usu.setFont(new Font("Ubuntu", 3, 25));

		setLocationRelativeTo(null);

		((JTextFieldDateEditor) dateChooser.getDateEditor()).setEditable(false);
		dateChooser.setDateFormatString("dd/MM/yyyy");
		dateChooser.setBackground(new Color(211, 211, 211));
		dateChooser.getDateEditor().addPropertyChangeListener(new PropertyChangeListener() {
			@Override
			public void propertyChange(PropertyChangeEvent evt) {
				if ("date".equals(evt.getPropertyName())) {
					Date selectedDate = (Date) evt.getNewValue();
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
					formattedDate = dateFormat.format(selectedDate);
				}
			}
		});

		spinnerModel.setCalendarField(Calendar.MINUTE); // Definir para alterar apenas a hora e minutos
		JSpinner.DateEditor timeEditor = new JSpinner.DateEditor(timeSpinner, "HH:mm");
		timeSpinner.setEditor(timeEditor);
		timeSpinner.addChangeListener(e -> {
			Date selectedTime = (Date) timeSpinner.getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
			String formattedTime = sdf.format(selectedTime);
			horas.setText(formattedTime);
		});

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
			e1.printStackTrace();
		}

		adjustComponents();
		horas.setFont(new Font("Ubuntu", 2, 20));
		horas.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(horas);
		horae.setFont(new Font("Ubuntu", 2, 20));
		horae.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(dateChooser);
		add(timeSpinner);

		add(horae);
		add(obj.getImagem());
		add(usu);
		listarCargo();
	}

	public String listarCargo() {
		try {
			Connection con = Banco.fazconexao();
			String sql = "select id_usuario from usuarios where nome_usuario=?";
			PreparedStatement stmt;
			stmt = con.prepareStatement(sql);
			stmt.setString(1, usu.getText());
			ResultSet rs = stmt.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				// sNome.setText(String.valueOf(id));
				idusuario = String.valueOf(id);
				JOptionPane.showMessageDialog(null, idusuario);

			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return idusuario;

	}

	public void ButtonEntrar() {
		salvar.setText("Salvar");
		salvar.setFont(new Font("Ubuntu", 3, 25));
		salvar.setForeground(new Color(1, 1, 1));
		add(salvar);
		salvar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Banco.fazconexao();
					String sql = "select id_usuario from usuarios where nome_usuario=?";
					PreparedStatement stmt;
					stmt = con.prepareStatement(sql);
					stmt.setString(1, usu.getText());
					ResultSet rs = stmt.executeQuery();
					int id = 0; // Variável para armazenar o ID
	               
	                
	                String idusuario = String.valueOf(id);
					if (rs.next()) {  
						id = rs.getInt("id_usuario");
						// sNome.setText(String.valueOf(id));
						idusuario = String.valueOf(id);
						String sql1 = "insert into alocacao (USUARIO,DATA,HORAINICIO,HORAFIM,EQUIPAMENTO,SALAREUNIAO,id_usuario)values(?,?,?,?,?,?,?)";
						PreparedStatement stmt1 = con.prepareStatement(sql1);
						stmt1.setString(1, sNome.getText());
						stmt1.setObject(2, formattedDate);
						stmt1.setString(3, horae.getText());
						stmt1.setString(4, horas.getText());
						stmt1.setString(5, escolhaRadio);
						stmt1.setString(6, escolhaRadio);
						stmt1.setString(7, idusuario);
						stmt1.execute();
						stmt1.close();
						stmt.close();
						con.close();
						JOptionPane.showMessageDialog(null, "Operação Realizada com Sucesso");
						sNome.setText("");
						um = new JRadioButton("Um", false);
						dois = new JRadioButton("Dois", false);
						horae.setText("");
						horas.setText("");

					} 
					stmt.close(); // Fechar o PreparedStatement após obter o ID
	                rs.close(); // Fechar o ResultSet
				} catch (SQLException es) {
					// TODO Auto-generated catch block
					es.printStackTrace();
				}
			}
		});
	}

	public void ButtonListar() {
		listar.setText("Listar");
		listar.setFont(new Font("Ubuntu", 3, 25));
		listar.setForeground(new Color(1, 1, 1));
		add(listar);
		listar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				try {
					funcao();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	public void radio() {
		grupo1 = new ButtonGroup();
		um = new JRadioButton("Um", false);
		dois = new JRadioButton("Dois", false);
		um.setFont(new Font("Ubuntu", 2, 20));
		dois.setFont(new Font("Ubuntu", 2, 20));
		handler = new RadioButtonHandler();

		grupo1.add(um);
		grupo1.add(dois);
		um.addItemListener(handler);
		dois.addItemListener(handler);

		setLayout(null);
		add(um);
		add(dois);

	}

	public class RadioButtonHandler implements ItemListener {

		@Override
		public void itemStateChanged(ItemEvent event) {
			if (um.isSelected())
				escolhaRadio = "esquerda";
			else if (dois.isSelected())
				escolhaRadio = "direita";
		}

	}

	private void adjustComponents() {
		int width = getWidth();
		int height = getHeight();
		int listarX = (int) (width * 0.72);
		int listarY = (int) (height * 0.80);
		int listarWidth = (int) (width * 0.1);
		int listarHeight = (int) (height * 0.05);
		listar.setBounds(listarX, listarY, listarWidth, listarHeight);
		int salvarX = (int) (width * 0.85);
		int salvarY = (int) (height * 0.80);
		int salvarWidth = (int) (width * 0.1);
		int salvarHeight = (int) (height * 0.05);
		salvar.setBounds(salvarX, salvarY, salvarWidth, salvarHeight);
		int salaX = (int) (width * 0.04);
		int salaY = (int) (height * 0.16);
		int salaWidth = (int) (width * 0.3);
		int salaHeight = (int) (height * 0.05);
		sala.setBounds(salaX, salaY, salaWidth, salaHeight);
		int radioX = (int) (salaX + 250);
		int radioY = (int) (height * 0.16);
		int radioWidth = (int) (width * 0.057);
		int radioHeight = (int) (height * 0.05);
		um.setBounds(radioX, radioY, radioWidth, radioHeight);
		int radio2X = (int) (radioX + 120);
		int radio2Y = (int) (height * 0.16);
		int radio2Width = (int) (width * 0.057);
		int radio2Height = (int) (height * 0.05);
		dois.setBounds(radio2X, radio2Y, radio2Width, radio2Height);
		int dataX = (int) (width * 0.04);
		int dataY = (int) (height * 0.27);
		int dataWidth = (int) (width * 0.08);
		int dataHeight = (int) (height * 0.05);
		data.setBounds(dataX, dataY, dataWidth, dataHeight);
		int datasX = (int) (dataX + 70);
		int datasY = (int) (height * 0.27);
		int datasWidth = (int) (width * 0.09);
		int datasHeight = (int) (height * 0.05);
		dateChooser.setBounds(datasX, datasY, datasWidth, datasHeight);
		int horaX = (int) (width * 0.04);
		int horaY = (int) (height * 0.38);
		int horaWidth = (int) (width * 0.3);
		int horaHeight = (int) (height * 0.05);
		hora.setBounds(horaX, horaY, horaWidth, horaHeight);
		int hora2X = (int) (width * 0.04);
		int hora2Y = (int) (height * 0.49);
		int hora2Width = (int) (width * 0.3);
		int hora2Height = (int) (height * 0.05);
		hora2.setBounds(hora2X, hora2Y, hora2Width, hora2Height);
		int horasX = (int) (hora2X + 190);
		int horasY = (int) (height * 0.49);
		int horasWidth = (int) (width * 0.05);
		int horasHeight = (int) (height * 0.05);
		timeSpinner.setBounds(horasX, horasY, horasWidth, horasHeight);
		int horaeX = (int) (horaX + 210);
		int horaeY = (int) (height * 0.38);
		int horaeWidth = (int) (width * 0.05);
		int horaeHeight = (int) (height * 0.05);
		horae.setBounds(horaeX, horaeY, horaeWidth, horaeHeight);
		int X = (int) (width * 0.35);
		int Y = (int) (height * 0.11);
		int Width = (int) (width * 0.35);
		int Height = (int) (height * 0.80);
		obj.imagem("/Imagens/verdadeiro.png", X, Y, Width, Height, Width, Height);
		int nomeX = (int) (width * 0.04);
		int nomeY = (int) (height * 0.05);
		int nomeWidth = (int) (width * 0.1);
		int nomeHeight = (int) (height * 0.05);
		nome.setBounds(nomeX, nomeY, nomeWidth, nomeHeight);
		int sNomeX = (int) (nomeX + 80);
		int sNomeY = (int) (height * 0.05);
		int sNomeWidth = (int) (width * 0.3);
		int sNomeHeight = (int) (height * 0.05);
		sNome.setBounds(sNomeX, sNomeY, sNomeWidth, sNomeHeight);
		int usuX = (int) (width * 0.9);
		int usuY = (int) (height * 0.05);
		int usuWidth = (int) (width * 0.3);
		int usuHeight = (int) (height * 0.05);
		usu.setBounds(usuX, usuY, usuWidth, usuHeight);
		repaint();

	}

	public void recebendo(String recebe) {
		recebe = recebe.substring(0, 1).toUpperCase().concat(recebe.substring(1));
		usu.setText(recebe);
	}
	public void funcao() throws SQLException{
		if(enciartexto == null){
		    enciartexto= new Reuniao();
		    enciartexto.setVisible(true);
		    enciartexto.recebendo(usu.getText());
		    
		}else{
		    enciartexto.setVisible(true);
		    enciartexto.setState(SegundaJanela.NORMAL);
		    enciartexto.recebendo(usu.getText());
		}
		}   

}
