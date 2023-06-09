package Janela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.print.PrinterException;
import java.awt.print.PrinterJob;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.print.attribute.standard.OrientationRequested;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SpinnerDateModel;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

import com.toedter.calendar.JDateChooser;
import com.toedter.calendar.JTextFieldDateEditor;

import Conexao.Banco;

public class Reuniao extends JFrame {
	public  int a;
	public String usuario;
	public String Data;
	public String equipamento;
	public String horaFim;
	public String horaInicio;
	public String salareuniao;
	public String id_usuario;
	TelaEditar enciartexto;
	JDateChooser dateChooser = new JDateChooser();
	SpinnerDateModel spinnerModel = new SpinnerDateModel();
	String formattedDate;
	JButton imprimir=new JButton();
	JLabel UsuarioL = new JLabel();
	JButton Pesquisa = new JButton();
	private JTable table;
	JFormattedTextField data;
	JButton editar = new JButton();
	JButton Deletar = new JButton();
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension d = tk.getScreenSize();
	private Objetos obj = new Objetos();
	private String[] names = { "Id", "Nome De Usuario", "Data", "Hora Inicio", "Hora termino", "Equipamento", "Sala" };
	private String[][] body = { { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, }, };

	public Reuniao() {

		setIconImage(obj.icone());
		setSize(d.width - 600, d.height - 500);
		setTitle("Othon de Carvalho");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				adjustComponents();
			};
		});
		ButtonAtualizar();
		ButtonDelete();
		btnPesquisar();
		btnData();
		btnImpressora();
		DefaultTableModel model = new DefaultTableModel(body, names);

		table = new JTable(model);
		table.setBackground(Color.WHITE);
		table.setPreferredScrollableViewportSize(new Dimension(d.width - 600, d.height - 500));
		table.setFillsViewportHeight(true);

		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, d.width, d.height);
		add(scrollPane);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		listar();

		UsuarioL.setFont(new java.awt.Font("Tahoma", 1, 14));
		add(UsuarioL);
		table.setColumnSelectionAllowed(true);
		table.addMouseListener(new java.awt.event.MouseAdapter() {
			public void mouseClicked(java.awt.event.MouseEvent evt) {
				tableMouseClicked(evt);
			}
		});
		// Crie um objeto TableCellRenderer personalizado para centralizar a coluna
		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		// Aplique o renderizador personalizado à coluna desejada
		scrollPane.setViewportView(table);
		table.getColumnModel().getSelectionModel()
				.setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
		if (table.getColumnModel().getColumnCount() > 0) {
			table.getColumnModel().getColumn(0).setMinWidth(0);
			table.getColumnModel().getColumn(0).setPreferredWidth(0);
			table.getColumnModel().getColumn(0).setMaxWidth(0);
			table.getColumnModel().getColumn(1).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(1).setMinWidth(250);
			table.getColumnModel().getColumn(1).setPreferredWidth(250);
			table.getColumnModel().getColumn(1).setMaxWidth(250);
			table.getColumnModel().getColumn(2).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(2).setMinWidth(75);
			table.getColumnModel().getColumn(2).setPreferredWidth(75);
			table.getColumnModel().getColumn(2).setMaxWidth(75);
			table.getColumnModel().getColumn(3).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(3).setMinWidth(75);
			table.getColumnModel().getColumn(3).setPreferredWidth(75);
			table.getColumnModel().getColumn(3).setMaxWidth(75);
			table.getColumnModel().getColumn(4).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(4).setMinWidth(75);
			table.getColumnModel().getColumn(4).setPreferredWidth(75);
			table.getColumnModel().getColumn(4).setMaxWidth(75);
			table.getColumnModel().getColumn(5).setMinWidth(200);
			table.getColumnModel().getColumn(5).setPreferredWidth(200);
			table.getColumnModel().getColumn(5).setMaxWidth(200);
			table.getColumnModel().getColumn(5).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(6).setCellRenderer(centerRenderer);
			table.getColumnModel().getColumn(6).setMinWidth(200);
			table.getColumnModel().getColumn(6).setPreferredWidth(200);
			table.getColumnModel().getColumn(6).setMaxWidth(200);

		}

	}

	private void tableMouseClicked(java.awt.event.MouseEvent evt) {
		int setor = table.getSelectedRow();
		
		try {
			a = Integer.parseInt(table.getModel().getValueAt(setor, 0).toString());
			Data = table.getModel().getValueAt(setor, 1).toString();
			usuario = table.getModel().getValueAt(setor,2).toString();
			equipamento = table.getModel().getValueAt(setor,3).toString();
			horaFim = table.getModel().getValueAt(setor, 4).toString();
			horaInicio = table.getModel().getValueAt(setor, 5).toString();
			salareuniao = table.getModel().getValueAt(setor, 6).toString();
			id_usuario = table.getModel().getValueAt(setor, 7).toString();
		
		} catch (Exception e) {
			// TODO: handle exception
		}

	}

	private void DeletarActionPerformed(java.awt.event.ActionEvent evt) {

		try {
			Connection con = Banco.fazconexao();
			String sql = "select *from usuarios where nome_usuario=? and administrador=?";
			PreparedStatement stmt = con.prepareStatement(sql);
			stmt.setString(1, UsuarioL.getText());
			stmt.setString(2, "S");
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				if (a == 0) {
					JOptionPane.showMessageDialog(null, "Favor Selecionar Uma Linha Na Tabela");
				} else {
					String msg = "Deseja realmente excluir !?";
					int resp = JOptionPane.showConfirmDialog(null, msg, "Confirmação", JOptionPane.YES_NO_OPTION);

					// excluir registro selecionando pelo ID
					if (resp == JOptionPane.YES_OPTION) {
						try {
							Connection con1 = Banco.fazconexao();
							String sql1 = "delete from alocacao where id=? ";
							PreparedStatement stmt1 = con.prepareStatement(sql1);
							stmt1.setInt(1, a);
							stmt1.execute();
							stmt1.close();
							con.close();
							JOptionPane.showMessageDialog(null, "Excluido!");
							a = 0;
							listar();
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

			} else {
				JOptionPane.showMessageDialog(null, "O Usuario Não Tem Permição de Acesso! ");
			}

		} catch (SQLException erro) {
			JOptionPane.showMessageDialog(null, "Erro");
		}
	}

	public void ButtonDelete() {
		Deletar.setFont(new Font("Ubuntu", 3, 25));
		Deletar.setForeground(new Color(1, 1, 1));
		Deletar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		Deletar.setText("Excluir");
		add(Deletar);
		Deletar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				DeletarActionPerformed(evt);
			}
		});
	}

	public void ButtonAtualizar() {
		editar.setText("Atualizar");
		editar.setFont(new Font("Ubuntu", 3, 25));
		editar.setForeground(new Color(1, 1, 1));
		editar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

		add(editar);

		editar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				listar();
			}
		});
	}

	public void listar() {
		try {
			Connection con = Banco.fazconexao();

			String sql = "select alocacao.id as ID,usuarios.nome_usuario as batman,date_format(alocacao.DATA,'%d/%m/%Y') as Data,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM,alocacao.EQUIPAMENTO as EQUIPAMENTO,alocacao.SALAREUNIAO as SALAREUNIAO from usuarios inner join alocacao on usuarios.id_usuario = alocacao.id_usuario order by alocacao.DATA;";
			PreparedStatement stmt = con.prepareStatement(sql);
			ResultSet rs = stmt.executeQuery();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.setNumRows(0);
			while (rs.next()) {
				modelo.addRow(new Object[] {

						rs.getString("ID"), rs.getString("batman"), rs.getString("Data"), rs.getString("HORAINICIO"),
						rs.getString("HORAFIM"), rs.getString("EQUIPAMENTO"), rs.getString("SALAREUNIAO") });
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void btnPesquisar() {

		Pesquisa.setFont(new Font("Ubuntu", 3, 25));
		Pesquisa.setForeground(new Color(1, 1, 1));
		Pesquisa.setText("Pesquisa");
		Pesquisa.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
		add(Pesquisa);
		Pesquisa.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				evtBtnPesquisar(evt);
			}
		});
		Pesquisa.addKeyListener(new java.awt.event.KeyAdapter() {
			public void keyPressed(java.awt.event.KeyEvent evt) {
				clickBtnPesquisar(evt);
			}
		});
	}

	private void clickBtnPesquisar(java.awt.event.KeyEvent evt) {
		if (evt.getKeyCode() == evt.VK_ENTER) {
			try {
				Connection con = Banco.fazconexao();
				String sql = "select alocacao.id as Id,usuarios.nome_usuario as usuario,alocacao.DATA as data,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM, alocacao.EQUIPAMENTO as EQUIPAMENTO,alocacao.SALAREUNIAO as SALA from alocacao inner join usuarios on usuarios.id_usuario = alocacao.id_usuario where DATA = ?";
				PreparedStatement stmt = con.prepareStatement(sql);

				stmt.setObject(1, formattedDate);

				ResultSet rs = stmt.executeQuery();
				DefaultTableModel modelo = (DefaultTableModel) table.getModel();
				modelo.setNumRows(0);
				while (rs.next()) {
					modelo.addRow(new Object[] { rs.getString("Id"), rs.getString("usuario"), rs.getString("data"),
							rs.getString("HORAINICIO"), rs.getString("HORAFIM"), rs.getString("EQUIPAMENTO"),
							rs.getString("SALA") });
				}
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	private void evtBtnPesquisar(java.awt.event.ActionEvent evt) {
		try {
			Connection con = Banco.fazconexao();
			String sql = "select alocacao.id as Id,usuarios.nome_usuario as usuario,alocacao.DATA as data,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM, alocacao.EQUIPAMENTO as EQUIPAMENTO,alocacao.SALAREUNIAO as SALA from alocacao inner join usuarios on usuarios.id_usuario = alocacao.id_usuario where DATA = ?";
			PreparedStatement stmt = con.prepareStatement(sql);

			stmt.setObject(1, formattedDate);

			ResultSet rs = stmt.executeQuery();
			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
			modelo.setNumRows(0);
			while (rs.next()) {
				modelo.addRow(new Object[] { rs.getString("Id"), rs.getString("usuario"), rs.getString("data"),
						rs.getString("HORAINICIO"), rs.getString("HORAFIM"), rs.getString("EQUIPAMENTO"),
						rs.getString("SALA") });
			}
			rs.close();
			con.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	private void btnData() {
		((JTextFieldDateEditor) dateChooser.getDateEditor()).setEditable(false);
		dateChooser.setFont(new Font("Ubuntu", 3, 17));
		dateChooser.setForeground(new Color(1, 1, 1));
		dateChooser.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
		add(dateChooser); 
	}
	private void btnImpressora() {
		imprimir.setFont(new Font("Ubuntu", 3, 25));
		imprimir.setForeground(new Color(1, 1, 1));
		imprimir.setText("Imprimir");
		imprimir.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(imprimir);
		imprimir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				impressoras();
			}
		});
	}

private void impressoras() {
	MessageFormat reader =new MessageFormat("Impressora");
	//add(OrientationRequested.LANDSCAPE);
    try{
        table.print(JTable.PrintMode.FIT_WIDTH, reader, null);
    }catch(Exception e){
        JOptionPane.showMessageDialog(rootPane, e);
    }

}
public void btnEditar() {
	 try{
         Connection con = Banco.fazconexao();
        String sql="select *from usuarios where nome=? and administrador=?";
            PreparedStatement stmt=con.prepareStatement(sql); 
            stmt.setString(1,UsuarioL.getText());
            stmt.setString(2,"S");
             ResultSet rs = stmt.executeQuery();
        if(rs.next()){
                  if(a == 0){
        JOptionPane.showMessageDialog(null,"Favor Selecionar Uma Linha");}else{
        if(enciartexto == null){
            
    enciartexto=new TelaEditar();           
    enciartexto.setVisible(true);
               this.dispose(); 
    enciartexto.recebendo(a,usuario,Data,horaInicio,horaFim,equipamento,salareuniao,id_usuario);
     
    listar();
  
    
}else{
    enciartexto.setVisible(true);
 this.dispose(); 
    enciartexto.setState(TelaEditar.NORMAL);
enciartexto.recebendo(a,usuario,Data,horaInicio,horaFim,equipamento,salareuniao,id_usuario);


listar();

        }
        
        }
            
        }else{
            JOptionPane.showMessageDialog(null,"O Usuario Não Tem Permição de Acesso a Edição  ");
        }
            
      }catch (SQLException erro ){
          JOptionPane.showMessageDialog(null,"Erro");
      }      
        
}

	private void adjustComponents() {
		int width = getWidth();
		int height = getHeight();
		int listarX = (int) (width * 0.67);
		int listarY = (int) (height * 0.80);
		int listarWidth = (int) (width * 0.1);
		int listarHeight = (int) (height * 0.05);
		editar.setBounds(listarX, listarY, listarWidth, listarHeight);
		int deleteX = (int) (width * 0.80);
		int deleteY = (int) (height * 0.80);
		int deleteWidth = (int) (width * 0.1);
		int deleteHeight = (int) (height * 0.05);
		Deletar.setBounds(deleteX, deleteY, deleteWidth, deleteHeight);
		int labelX = (int) (width * 0.62);
		int labelY = (int) (height * 0.80);
		int labelWidth = (int) (width * 0.1);
		int labelHeight = (int) (height * 0.05);
		UsuarioL.setBounds(labelX, labelY, labelWidth, labelHeight);
		int PesquisaX = (int) (width * 0.54);
		int PesquisaY = (int) (height * 0.80);
		int PesquisaWidth = (int) (width * 0.1);
		int PesquisaHeight = (int) (height * 0.05);
		Pesquisa.setBounds(PesquisaX, PesquisaY, PesquisaWidth, PesquisaHeight);
		int datasX = (int) (width * 0.41);
		int datasY = (int) (height * 0.80);
		int datasWidth = (int) (width * 0.1);
		int datasHeight = (int) (height * 0.05);
		dateChooser.setBounds(datasX, datasY, datasWidth, datasHeight);
		int imprimirX = (int) (width * 0.28);
		int imprimirY = (int) (height * 0.80);
		int imprimirWidth = (int) (width * 0.1);
		int imprimirHeight = (int) (height * 0.05);
		imprimir.setBounds(imprimirX, imprimirY, imprimirWidth, imprimirHeight);
	}

	public void recebendo(String recebe) {
		recebe = recebe.substring(0, 1).toUpperCase().concat(recebe.substring(1));
		UsuarioL.setText(recebe);
	}
}
