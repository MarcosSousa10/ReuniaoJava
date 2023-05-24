package Conexao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

	public class Banco {
		 public static Connection fazconexao() {
		        //verificaÃ§Ã£o de dados validos
		        try{
		            //faz com que a classe seja carregada pela JVM             
		           Class.forName("com.mysql.cj.jdbc.Driver");
		           //criar conexÃ£o com banco de dados
		           Connection conex= DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","");
//		           retorna conexÃ§Ã£o com banco de dados
		                   return conex; 
		        }catch(ClassNotFoundException erro1){
		            //driver não encontrado
		            JOptionPane.showMessageDialog(null," O driver especificado nÃ£o foi encontrado ");
		            return null;
		        }catch(SQLException erro2){
		            //nnÃ£o foi possivel localizar o banco de dados
		            JOptionPane.showMessageDialog(null ," O arquivo de Banco de dados nÃ£o foi encontrado ");
		             return null;
		        }
		       
		      
		    }
	}
