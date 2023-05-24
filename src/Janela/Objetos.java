package Janela;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.net.URL;
import java.text.ParseException;

import javax.swing.ImageIcon;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

public class Objetos {
JLabel imagem = new JLabel();
JFormattedTextField datas;
public void imagem(String caminho,Integer a,Integer b,Integer c ,Integer d,Integer e ,Integer f) {
	
    
    ImageIcon imgIcon = new ImageIcon(getClass().getResource(caminho));
    // Obtener la imagen y ajustarla al tamaño deseado
    Image img = imgIcon.getImage().getScaledInstance(e, f, Image.SCALE_SMOOTH);
    ImageIcon imgScaled = new ImageIcon(img);
    
    imagem.setIcon(imgScaled);
    imagem.setBounds(a,b,c,d);

}
    public JLabel getImagem() {
        return imagem;
    }
    public void mascara(String mascara,Integer a,Integer b,Integer c ,Integer d) {
  	  try {
  		
            MaskFormatter mascaraData = new MaskFormatter(mascara);
            mascaraData.setPlaceholderCharacter('_');

            datas = new JFormattedTextField(mascaraData);
            datas.setBounds(a,b,c,d);
        } catch (ParseException e) {
            e.printStackTrace();
        }}
    public JFormattedTextField getMarcara() {
    	return datas;
    }
    public Image icone() {
    	URL url = this.getClass().getResource("/Imagens/othon.png");
    	Image iconeTitulo = Toolkit.getDefaultToolkit().getImage(url);
    	return 	iconeTitulo;
    } 



}
