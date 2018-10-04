package com.numeros.csv;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import javax.swing.JOptionPane;
import regresiones.GeneralData;

public class CsvProcessor {

    public ArrayList<GeneralData> process(File file, int type) {
        BufferedReader br = null;
        String line = "";
      
        ArrayList<GeneralData> listResult = new ArrayList();
        try {

            br = new BufferedReader(new FileReader(file));
            boolean flag=true;
            while ((line = br.readLine()) != null) {

                   if(flag){
                   flag = false;
                   }else{
                    String cvsSplitBy = ";";
                           String[] row = line.split(cvsSplitBy);
                        GeneralData numeroTemp = new GeneralData();
                        numeroTemp.venta= Double.parseDouble(row[0]);
                        numeroTemp.fecha=new Date( row[1]);
                        listResult.add(numeroTemp);
                   }

            }

        } catch (FileNotFoundException e) {
//            JOptionPane.showMessageDialog(null, "Error al abrir el archivo", "Message", JOptionPane.ERROR);
            e.printStackTrace();
        } catch (IOException e) {
//            JOptionPane.showMessageDialog(null, "Error al leer el archivo", "Message", JOptionPane.ERROR);
            e.printStackTrace();
        } catch (Exception e) {
//            JOptionPane.showMessageDialog(null, "Error fatal", "Message", JOptionPane.ERROR);
            e.printStackTrace();
        } finally { 
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return listResult;

    }
    
    
    
    
        
  
}
