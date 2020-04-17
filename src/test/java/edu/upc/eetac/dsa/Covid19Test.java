package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.exceptions.BroteNotFoundException;
import edu.upc.eetac.dsa.models.Brote;
import edu.upc.eetac.dsa.models.Caso;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class Covid19Test {
    //Log4j
    private static Logger log = Logger.getLogger(Covid19Test.class);
    //Covid19 Manager
    public Covid19Manager covid19Manager = null;
    String broteID;
    @Before
    public void setUp(){
        //Configuramos Log4j
        PropertyConfigurator.configure("src/main/resources/log4j.properties");

        covid19Manager = Covid19ManagerImpl.getInstance();
        broteID = "COVID-19";
    }
    @After
    public void tearDown(){covid19Manager.liberarRecursos();}

    @Test
    public void nuevoBrote() throws BroteNotFoundException {
        Brote brote = covid19Manager.newBrote(broteID);
        Assert.assertEquals("COVID-19", covid19Manager.getBrote("COVID-19").getID());
        log.info("Brote: " + brote.getID());
    }
    @Test
    public void nuevoCaso() throws BroteNotFoundException{
        Caso caso = new Caso("Corona","Virus", new Date(1999,12,02), new Date(2020, 04,17), "bajo", "masculino", "corona@gmail.com", 666666666,"Wuhan", "C" );
        //Recordamos que la clasificación la asignamos internamente como: A->Confirmado, B->sospechoso, C-> No caso
        Brote brote = covid19Manager.newBrote(broteID);
        covid19Manager.addCaso(covid19Manager.getBrote("COVID-19").getID(), caso);

        Assert.assertEquals("Corona", covid19Manager.getBrote(broteID).getCaso(0).getNombre());
    }
}
