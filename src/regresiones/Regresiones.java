/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresiones;

import com.numeros.csv.CsvProcessor;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

/**
 *
 * @author jluis
 */
public class Regresiones {

   static CsvProcessor csv;
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        
        try{
            JFileChooser choose = new JFileChooser();
			
			choose.showOpenDialog(null);
			
			  csv = new CsvProcessor();
                          csv.process(choose.getSelectedFile(), 0);
                        }catch(Exception e)
                        {
                        JOptionPane.showMessageDialog(null,"Error al cargar el archivo","Message",JOptionPane.INFORMATION_MESSAGE);
                        }
    }
    
}
