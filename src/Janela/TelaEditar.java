package Janela;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class TelaEditar extends JFrame{
	int usu;
	JTextField usuarios=new JTextField();
	JTextField data= new JTextField();
	JTextField horainicio= new JTextField();
	JTextField horafim=new JTextField();
	JTextField equipamentos=new JTextField();
	JTextField salareuniaos=new JTextField();
	String id_usuario;
	Reuniao enciartexto;
	Toolkit tk = Toolkit.getDefaultToolkit();
	Dimension d = tk.getScreenSize();
	Objetos obj = new Objetos();
public TelaEditar() {
	
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
	usu();
	
}
public void recebendo(int a, String usuario, String Data, String horaInicio, String horaFim, String equipamentoo, String salareuniao, String id_usuario) {
	a = a;
	usuario = usuario.substring(0,1).toUpperCase().concat(usuario).substring(1);
	Data= Data.substring(0,1).toUpperCase().concat(Data).substring(1);
	horaInicio = horaInicio.substring(0,1).toUpperCase().concat(horaInicio).substring(1);
	horaFim= horaFim.substring(0,1).toUpperCase().concat(horaFim).substring(1);
	equipamentoo = equipamentoo.substring(0,1).toUpperCase().concat(equipamentoo).substring(1);
	salareuniao = salareuniao.substring(0,1).toUpperCase().concat(salareuniao).substring(1);
	id_usuario= id_usuario.substring(0,1).toUpperCase().concat(id_usuario).substring(1);
	salareuniaos.setText(salareuniao);
	data.setText(Data);
	horainicio.setText(horaInicio);
	horafim.setText(horaFim);
	equipamentos.setText(equipamentoo);
	usuarios.setText(usuario);	
	usu=a;
}
	
private void usu(){
	usuarios.setFont(new java.awt.Font("Tahoma", 3, 24));
	usuarios.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	add(usuarios);
}
private void adjustComponents() {
	int width = getWidth();
	int height = getHeight();
	int listarX = (int) (width * 0.67);
	int listarY = (int) (height * 0.80);
	int listarWidth = (int) (width * 0.1);
	int listarHeight = (int) (height * 0.05);
	usuarios.setBounds(listarX, listarY, listarWidth, listarHeight);
}
}
