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

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.google.gson.stream.JsonReader;

/**
 *
 * @author jluis
 */
public class Regresiones {

    static CsvProcessor csv;

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args)  throws IOException, ParseException {
        // TODO code application logic here

        try {
            JFileChooser choose = new JFileChooser();

            choose.showOpenDialog(null);

            csv = new CsvProcessor();
            ArrayList<GeneralData> listcsv = csv.process(choose.getSelectedFile(), 0);
            List<Salidas> listSalidasMysql = (new SalidasJpaController()).findSalidasEntities();

            listcsv.addAll(getJsonData());
            
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
    
    public static ArrayList<GeneralData> getJsonData()  throws IOException, ParseException {
    	ArrayList<GeneralData> listcsv = new ArrayList<>();
    	JFileChooser choose_json = new JFileChooser();
    	choose_json.showOpenDialog(null);
    	
    	File file_json = choose_json.getSelectedFile();
    	if(file_json != null) {
    		
    		Gson gson = new Gson();
    		JsonReader reader = new JsonReader(new FileReader(file_json.getAbsolutePath()));
    		
    		Type listType = new TypeToken<List<Sales>>() {}.getType();
    		
    		List<Sales> sales = gson.fromJson(reader, listType);
    		
    		DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
    		for(Sales s : sales) {
    			GeneralData temp = new GeneralData();
    			temp.fecha = format.parse(s.getDate());
                temp.venta = s.getTotal();
                listcsv.add(temp);
    		}
    		
    		reader.close();
    		
    	}else {
    		System.out.println("File was null for json selection");
    	}
    	return listcsv;
    }

}

class Sales{
	private String id;
	private double total;
	private int product;
	private String date;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public int getProduct() {
		return product;
	}
	public void setProduct(int product) {
		this.product = product;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}	
}
