package Janela;

import java.awt.Color;
import java.awt.Dimension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import Conexao.Banco;

public class Reuniao extends JFrame{
    private JTable table;
    String[] names = { "Nome", "Sala", "Data", "Hora Entrada", "Hora Saida"};
    String[][] body = {    
    		{null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null},
            {null, null, null, null, null}};
	public Reuniao() {		
        super("JTable Demo");
        table = new JTable(body, names);
        table.setBackground(Color.ORANGE);
        table.setPreferredScrollableViewportSize(new Dimension(500, 300));
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table));
	}
		
		public static void main(String args []) {
			Reuniao table = new Reuniao();
		        table.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		        table.setSize(500, 300);
		        table.setVisible(true);
		
		}
            

                  
        }