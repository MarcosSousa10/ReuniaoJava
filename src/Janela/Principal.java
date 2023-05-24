package Janela;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Conexao.Banco;

public class Principal extends JFrame {
	Objetos obj = new Objetos();
	JButton entrar = new JButton("Entrar");
	JTextField nome = new JTextField();
	JPasswordField senha = new JPasswordField();
	JLabel nomel = new JLabel("Nome");
	JLabel senhal = new JLabel("Senha");
	JLabel label = new JLabel();
	JLabel usu = new JLabel();
	SegundaJanela enciartexto;
    //Equipamento enciartext;
 //   listareuniao enciartexto;

	public Principal() {
		Color minhaCor = new Color(211, 211, 211);
		getContentPane().setBackground(minhaCor);
		setIconImage(obj.icone());
		setTitle("Login");
		Toolkit tk = Toolkit.getDefaultToolkit();
		Dimension d = tk.getScreenSize();
		setLayout(null);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				ajustaComponentes();
			}
		});
		componentes();
		add(obj.getImagem());
		setResizable(false);
		int janelaX = d.width - 800;
		int janelaY = d.height - 700;
		if (janelaX < 1100 || janelaY < 380) {
			janelaX = 1100;
			janelaY = 380;
		}

		setSize(janelaX, janelaY);
		
		setVisible(true);
		setLocationRelativeTo(null);
		ajustaComponentes();
	}

	public void componentes() {
		entrar.setBounds(300, 280, 200, 50);
		add(entrar);
		entrar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Connection con = Banco.fazconexao();
					String sql = "select * from usuarios where nome_usuario=? and senha_usuario=?";
					PreparedStatement stmt = con.prepareStatement(sql);
					stmt.setString(1, nome.getText());
					stmt.setString(2, new String(senha.getPassword()));
					ResultSet rs = stmt.executeQuery();
					if (rs.next()) {
						SegundaJanela segunda = new SegundaJanela();
						funcao();
						setVisible(false);
					} else {
						JOptionPane.showMessageDialog(null, "Usuário inexistente");
					}
					stmt.close();
					con.close();

				} catch (SQLException erro) {
					erro.printStackTrace();
				}
			}
		});
		Icon icone = new ImageIcon("/Imagens/othon.png");
		entrar.setIcon(icone);
		entrar.setFont(new java.awt.Font("Tahoma", 3, 24));
		entrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		usu.setFont(new java.awt.Font("Tahoma", 3, 24));
		usu.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		nomel.setFont(new java.awt.Font("Tahoma", Font.BOLD + Font.ITALIC, 25));
		nomel.setForeground(new java.awt.Color(13, 21, 11));
		nomel.setText("Usuario ");
		senhal.setFont(new java.awt.Font("Tahoma", 3, 25));
		senhal.setForeground(new java.awt.Color(1, 1, 1));
		senhal.setText("Senha");
		nome.setFont(new java.awt.Font("Ubuntu", 0, 14));
		nome.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		senha.setFont(new java.awt.Font("Ubuntu", 0, 14));
		senha.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		entrar.setFont(new java.awt.Font("Tahoma", 3, 24));
		entrar.setText("Entrar");
		entrar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		label.setFont(new java.awt.Font("Ubuntu", 3, 48)); // NOI18N
		label.setText("Registro de Reuniões");
		add(nome);
		add(senha);
		add(nomel);
		add(senhal);
		add(label);
		add(usu);
		senha.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					entrar.doClick();
				}
			}
		});
		nome.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_ENTER) {
					senha.requestFocus();
				}
			}
		});
	}

	public static void main(String[] arg) {
		Principal janela = new Principal();

	}
	public void funcao() throws SQLException{
		if(enciartexto == null){
		    enciartexto=new SegundaJanela();
		    enciartexto.setVisible(true);
		    enciartexto.recebendo(nome.getText());
		    
		}else{
		    enciartexto.setVisible(true);
		    enciartexto.setState(SegundaJanela.NORMAL);
		    enciartexto.recebendo(nome.getText());
		}
		}   


	private void ajustaComponentes() {
		int width = getWidth();
		int height = getHeight();
		int nomeX = (int) (width * 0.5);
		int nomeY = (int) (height * 0.05);
		int nomeWidth = (int) (width * 0.37);
		int nomeHeight = (int) (height * 0.085);
		nome.setBounds(nomeX, nomeY + 60, nomeWidth, nomeHeight);
		nomel.setBounds(nomeX - 130, nomeY + 60, nomeWidth - 100, nomeHeight);
		senha.setBounds(nomeX, nomeY + 130, nomeWidth, nomeHeight);
		senhal.setBounds(nomeX - 110, nomeY + 130, nomeWidth - 100, nomeHeight);
		entrar.setBounds(nomeX + 100, nomeY + 210, nomeWidth - 200, nomeHeight + 20);
		label.setBounds(nomeX - 100, nomeY - 5, nomeWidth + 100, nomeHeight + 5);
		usu.setBounds(nomeX, nomeY, nomeWidth, nomeHeight);
		int imagemX = (int) (width *0.000000000001);
		int imagemY = (int) (height * 0.002);
		int imagemWidth = (int) (width * 0.48);
		int imagemHeight = (int) (height * 0.9);
		obj.imagem("/Imagens/logi.png", imagemX, imagemY, imagemWidth, imagemHeight, imagemWidth, imagemHeight);

	}
}
