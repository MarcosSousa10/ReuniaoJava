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
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import Conexao.Banco;

public class Reuniao extends JFrame {
    private int a;
    JLabel UsuarioL=new JLabel();
    JButton Pesquisa= new JButton();
	private JTable table;
	JFormattedTextField data;
	JButton editar = new JButton();
	JButton Deletar=new JButton();
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
        table.getColumnModel().getSelectionModel().setSelectionMode(javax.swing.ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
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
	    // Converta o valor do ID para o tipo numérico correto
	    a = Integer.parseInt(table.getModel().getValueAt(setor, 0).toString());
	 
	}
	  private void DeletarActionPerformed(java.awt.event.ActionEvent evt) {                                         

	         try{
	         Connection con = Banco.fazconexao();
	        String sql="select *from usuarios where nome_usuario=? and administrador=?";
	            PreparedStatement stmt=con.prepareStatement(sql); 
	            stmt.setString(1,UsuarioL.getText());
	            stmt.setString(2,"S");
	             ResultSet rs = stmt.executeQuery();
	        if(rs.next()){
	        	JOptionPane.showMessageDialog(null, a);                  
	        if (a == 0){
	    JOptionPane.showMessageDialog(null, "Favor Selecionar Uma Linha Na Tabela");
	}else{       
	            String msg = "Deseja realmente excluir !?"; 
	            int resp = JOptionPane.showConfirmDialog(null, msg, "Confirmação", JOptionPane.YES_NO_OPTION);

	                  // excluir registro selecionando pelo ID
	                  if (resp == JOptionPane.YES_OPTION) {
	        try{
	        Connection con1 = Banco.fazconexao();
	        String sql1="delete from alocacao where id=? ";
	        PreparedStatement stmt1=con.prepareStatement(sql1);
	        stmt1.setInt(1, a);
	        stmt1.execute();
	        stmt1.close();
	        con.close();
	        JOptionPane.showMessageDialog(null, "Excluido!");
	          a = 0;
	            listar();
	        }catch(SQLException e){
	            e.printStackTrace();
	        }}}
	                      
	        }else{
	            JOptionPane.showMessageDialog(null,"O Usuario Não Tem Permição de Acesso! ");
	        }
	            
	      }catch (SQLException erro ){
	          JOptionPane.showMessageDialog(null,"Erro");
	      }
	         }    
 
	public void ButtonDelete() {
		Deletar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
	Deletar.setText("Excluir");
	Deletar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
	add(Deletar);
	Deletar.addActionListener(new java.awt.event.ActionListener() {
        public void actionPerformed(java.awt.event.ActionEvent evt) {
        	DeletarActionPerformed(evt);
        }	
	});
	}

	public void ButtonAtualizar() {
		editar.setText("Atualizar");
		editar.setFont(new Font("Ubuntu", 2, 20));
		editar.setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED));
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

						// rs.getString("ID"),rs.getString("batman"),rs.getString("PRIORIDADE"),rs.getString("DATAINICIO"),rs.getString("HORAINICIO"),rs.getString("HORAFIM"),rs.getString("EQUIPAMENTO"),rs.getString("SALAREUNIAO"),rs.getString("ASSUNTO")});
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
		Pesquisa.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
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
		if(evt.getKeyCode()==evt.VK_ENTER){
		      try{
		        Connection con =Banco.fazconexao();
		       
		        
		 //        String sql="select usuarios.nome as batman,alocacao.PRIORIDADE as PRIORIDADE,alocacao.DATAINICIO as DATAINICIO,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM,alocacao.ASSUNTO as ASSUNTO from usuarios inner join alocacao on usuarios.idusuario = alocacao.idusuario order by alocacao.datainicio;";


		    //  String sql="select usuarios.nome as batman,alocacao.PRIORIDADE as PRIORIDADE,alocacao.DATAINICIO as DATAINICIO,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM,alocacao.ASSUNTO as ASSUNTO from usuarios inner join alocacao on alocacao.datainicio>=? and alocacao.datainicio<=? order by alocacao.datainicio;";
		       String sql="select alocacao.ID as ID,usuarios.nome as batman,alocacao.PRIORIDADE as PRIORIDADE,alocacao.DATAINICIO as DATAINICIO,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM,alocacao.SALAREUNIAO as SALAREUNIAO, alocacao.ASSUNTO as ASSUNTO from alocacao inner join usuarios on usuarios.idusuario = alocacao.idusuario where DATAINICIO>= ? AND DATAINICIO<= ?";
		        PreparedStatement stmt=con.prepareStatement(sql);
		                     // stmt.setTime(1,da);
		                      String dia =data.getText().substring(0,2);
		            String mes =data.getText().substring(3,5);
		            String ano =data.getText().substring(6);
		            String my=ano+"-"+mes+"-"+dia;            
		          //  stmt.setObject(3,da.getText());
		          String di =data.getText().substring(0,2);
		            String me =data.getText().substring(3,5);
		            String an =data.getText().substring(6);
		            String myy=an+"-"+me+"-"+di;       
		            stmt.setObject(1,my);
		            stmt.setObject(2,myy);
		                     //  stmt.setString(1,da.getText());
		                    //   stmt.setString(2,ta.getText());
		        ResultSet rs = stmt.executeQuery();
		            DefaultTableModel modelo = (DefaultTableModel) table.getModel();
		            modelo.setNumRows(0);
		            while (rs.next()){
		                modelo.addRow(new Object[]{
		                     rs.getString("ID"),rs.getString("batman"),rs.getString("PRIORIDADE"),rs.getString("DATAINICIO"),rs.getString("HORAINICIO"),rs.getString("HORAFIM"),rs.getString("SALAREUNIAO"),rs.getString("ASSUNTO")});
		            }
		            rs.close();
		            con.close();
		                }catch(SQLException e){
		            e.printStackTrace();
		        }
		}
	}
	private void evtBtnPesquisar(java.awt.event.ActionEvent evt) {
        try{
       Connection con =Banco.fazconexao();
      String sql="select alocacao.id as Id,usuarios.nome_usuario as usuario,alocacao.DATA as data,alocacao.HORAINICIO as HORAINICIO,alocacao.HORAFIM as HORAFIM, alocacao.EQUIPAMENTO as EQUIPAMENTO,alocacao.SALAREUNIAO as SALA from alocacao inner join usuarios on usuarios.id_usuario = alocacao.id_usuario where DATA = ?";
       PreparedStatement stmt=con.prepareStatement(sql);
           String dia =data.getText().substring(0,2);
           String mes =data.getText().substring(3,5);
           String ano =data.getText().substring(6);
           String my=ano+"-"+mes+"-"+dia;                
           stmt.setObject(1,my);

       ResultSet rs = stmt.executeQuery();
           DefaultTableModel modelo = (DefaultTableModel) table.getModel();
           modelo.setNumRows(0);
           while (rs.next()){
               modelo.addRow(new Object[]{
                     rs.getString("Id"),rs.getString("usuario"),rs.getString("data"),rs.getString("data"),rs.getString("HORAINICIO"),rs.getString("HORAFIM"),rs.getString("EQUIPAMENTO"),rs.getString("SALA")});
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
		
	}

	public void recebendo(String recebe) {
		recebe = recebe.substring(0, 1).toUpperCase().concat(recebe.substring(1));
		UsuarioL.setText(recebe);
	}
}
