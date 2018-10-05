/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import Repository.general.LinealJpaController;
import Repository.general.SalidasJpaController;
import entity.mysql.Salidas;
import java.util.List;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jluis
 */
public class TestDB {
    
    public TestDB() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
     @Test
     public void test1() {
         try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("ventasPU");
         SalidasJpaController jpa = new SalidasJpaController(emf);
         
         List<Salidas> result = jpa.findSalidasEntities();
         }catch(Exception e)
         {
             System.out.println(e.getStackTrace());
         }
     }
     
          @Test
     public void test2() {
         try{
          EntityManagerFactory emf = Persistence.createEntityManagerFactory("RegresionesPU");
         LinealJpaController jpa = new LinealJpaController(emf);
         
         jpa.findLinealEntities();
         }catch(Exception e)
         {
             System.out.println(e.getStackTrace());
         }
     }
}
