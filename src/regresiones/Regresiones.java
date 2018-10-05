/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package regresiones;

import BLL.general.MultipleLinearRegression;
import BLL.general.Regresion;
import Repository.general.LinealJpaController;
import Repository.general.SalidasJpaController;
import com.numeros.csv.CsvProcessor;
import entity.mysql.Lineal;
import entity.mysql.Salidas;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
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

        try {
            JFileChooser choose = new JFileChooser();

            choose.showOpenDialog(null);

            csv = new CsvProcessor();
            ArrayList<GeneralData> listcsv = csv.process(choose.getSelectedFile(), 0);
            List<Salidas> listSalidasMysql = (new SalidasJpaController()).findSalidasEntities();

            for (Salidas data : listSalidasMysql) {
                GeneralData temp = new GeneralData();
                temp.fecha = data.getFecha();
                temp.venta = data.getVenta();
                listcsv.add(temp);
            }
            listcsv.stream().map(x -> x.venta).collect(Collectors.toList());
            Double []x = new Double[listcsv.size()];
             Double []y = new Double[listcsv.size()];
                        x =    listcsv.stream().map(s -> s.venta).collect(Collectors.toList()).toArray(new Double[listcsv.size()]);
              Integer i =1;
              for(GeneralData data : listcsv){
                    y[i-1]=(double)i;
                    i++;
              }          
           Regresion regresionLineal = new Regresion(x, y);
           regresionLineal.lineal();
           regresionLineal.correlacion();
           
           //RESULTADO RE REGRESION LINEAL
           Lineal lineal = new Lineal();
           lineal.setOrdenada( regresionLineal.a);
           lineal.setPendiente(regresionLineal.b);
           (new LinealJpaController()).create(lineal);
           
           //para calcular x es   -> x = a+(b * y)
           
           //REGRESION MULTIVARIABLE
          
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e.getStackTrace(), "Message", JOptionPane.INFORMATION_MESSAGE);
        }
    }

}
