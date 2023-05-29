package Janela;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableModel;

import Conexao.Banco;

public class Reuniao extends JFrame {
	private JTable table;
	JFormattedTextField data;
	JButton editar = new JButton();
	private Toolkit tk = Toolkit.getDefaultToolkit();
	private Dimension d = tk.getScreenSize();
	private Objetos obj = new Objetos();
	private String[] names = { "ID", "batman", "DATAINICIO", "HORAINICIO","HORAFIM","EQUIPAMENTO","SALAREUNIAO"};
	private String[][] body = { { null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null,},
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, },
			{ null, null, null, null, null, null, null, }, { null, null, null, null, null, null, null, } };

	public Reuniao() {
		setIconImage(obj.icone());
		setTitle("Othon de Carvalho");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addComponentListener(new ComponentAdapter() {

			@Override
			public void componentResized(ComponentEvent e) {
				adjustComponents();
			};
		});
		DefaultTableModel model = new DefaultTableModel(body, names);
		table = new JTable(model);
		table.setBackground(Color.ORANGE);
		table.setPreferredScrollableViewportSize(new Dimension(1200, 1000));
		table.setFillsViewportHeight(true);
		JScrollPane scrollPane = new JScrollPane(table);
		scrollPane.setBounds(10, 10, d.width, d.height);
		add(scrollPane);

		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		ButtonEditar();
	}

	public void ButtonEditar() {
		editar.setText("Editar");
		editar.setFont(new Font("Ubuntu", 2,20));
		editar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
		add(editar);
		editar.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				listar();
		
//		try {
//			Connection con = Banco.fazconexao();
//
//			String sql = "select alocacao.ID as ID,usuarios.nome as batman,alocacao.PRIORIDADE as PRIORIDADE,alocacao.DATAINICIO as DATAINICIO,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM, alocacao.EQUIPAMENTO as EQUIPAMENTO,alocacao.SALAREUNIAO as SALAREUNIAO,alocacao.ASSUNTO as ASSUNTO from alocacao inner join usuarios on usuarios.idusuario = alocacao.idusuario where DATAINICIO>= ? AND DATAINICIO<= ?";
//			PreparedStatement stmt = con.prepareStatement(sql);
//			stmt.setObject(1, data);
//
//			ResultSet rs = stmt.executeQuery();
//			DefaultTableModel modelo = (DefaultTableModel) table.getModel();
//			modelo.setNumRows(0);
//			while (rs.next()) {
//				modelo.addRow(new Object[] { rs.getString("ID"), rs.getString("batman"),
//						rs.getString("DATAINICIO"), rs.getString("HORAINICIO"), rs.getString("HORAFIM"),
//						rs.getString("EQUIPAMENTO"), rs.getString("SALAREUNIAO") });
//			}
//			rs.close();
//			con.close();
//		} catch (SQLException eeer) {
//			eeer.printStackTrace();
//		}
			}
		});
	}
    public void listar(){
        try{
    Connection con =Banco.fazconexao();
 
    String sql="select alocacao.id as ID,usuarios.nome_usuario as batman,date_format(alocacao.DATA,'%d/%m/%Y') as Data,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM,alocacao.EQUIPAMENTO as EQUIPAMENTO,alocacao.SALAREUNIAO as SALAREUNIAO from usuarios inner join alocacao on usuarios.id_usuario = alocacao.id_usuario order by alocacao.DATA;";
         PreparedStatement stmt=con.prepareStatement(sql);
    ResultSet rs = stmt.executeQuery();
        DefaultTableModel modelo = (DefaultTableModel) table.getModel();
        modelo.setNumRows(0);
        while (rs.next()){
            modelo.addRow(new Object[]{
   
            //      rs.getString("ID"),rs.getString("batman"),rs.getString("PRIORIDADE"),rs.getString("DATAINICIO"),rs.getString("HORAINICIO"),rs.getString("HORAFIM"),rs.getString("EQUIPAMENTO"),rs.getString("SALAREUNIAO"),rs.getString("ASSUNTO")});
                  rs.getString("ID"),rs.getString("batman"),rs.getString("Data"),rs.getString("HORAINICIO"),rs.getString("HORAFIM"),rs.getString("EQUIPAMENTO"),rs.getString("SALAREUNIAO")});
        }
        rs.close();
        con.close();
            }catch(SQLException e){
        e.printStackTrace();
    }
}
    private void adjustComponents() {
		int width = getWidth();
		int height = getHeight();
		int listarX = (int) (width * 0.72);
		int listarY = (int) (height * 0.80);
		int listarWidth = (int) (width * 0.1);
		int listarHeight = (int) (height * 0.05);
		editar.setBounds(listarX, listarY, listarWidth, listarHeight);
    }
	private void Carregar() {

		int setor = table.getSelectedRow();
		Nome.setText(table.getModel().getValueAt(setor, 0).toString());
		nomes.setText(table.getModel().getValueAt(setor, 1).toString());
		prioridade.setText(table.getModel().getValueAt(setor, 2).toString());
		Datainicio.setText(table.getModel().getValueAt(setor, 3).toString());
		Horainicio.setText(table.getModel().getValueAt(setor, 4).toString());
		horafim.setText(table.getModel().getValueAt(setor, 5).toString());
		Equipamento.setText(table.getModel().getValueAt(setor, 6).toString());
		salareuniao1.setText(table.getModel().getValueAt(setor, 7).toString());

	}
}
