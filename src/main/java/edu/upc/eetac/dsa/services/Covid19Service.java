package edu.upc.eetac.dsa.services;

import edu.upc.eetac.dsa.BroteOT;
import edu.upc.eetac.dsa.CasoOT;
import edu.upc.eetac.dsa.Covid19Manager;
import edu.upc.eetac.dsa.Covid19ManagerImpl;
import edu.upc.eetac.dsa.exceptions.BroteNotFoundException;
import edu.upc.eetac.dsa.models.Brote;
import edu.upc.eetac.dsa.models.Caso;
import io.swagger.annotations.*;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.ws.rs.*;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

@Api(value = "/Covid19", description = "Endpoint to Service")
@Path("/Covid19")
public class Covid19Service {
    static final Logger logger = Logger.getLogger(Covid19Service.class);
    private Covid19Manager covid19Manager;

    public Covid19Service() throws BroteNotFoundException {
        PropertyConfigurator.configure("src/main/resources/log4j.properties");
        this.covid19Manager = Covid19ManagerImpl.getInstance();
        this.covid19Manager.newBrote("COVID-19");


        this.covid19Manager.addCaso("COVID-19", new Caso("Corona","Virus", new Date(1999,12,02), new Date(2020, 04,17), "bajo", "masculino", "corona@gmail.com", 666666666,"Wuhan", "A"));
        this.covid19Manager.addCaso("COVID-19", new Caso("Corona2","Virus", new Date(1999,12,02), new Date(2020, 04,17), "bajo", "masculino", "corona@gmail.com", 666666666,"Wuhan", "A"));
        this.covid19Manager.addCaso("COVID-19", new Caso("Vacuna","Virus", new Date(1999,12,02), new Date(2020, 04,17), "bajo", "masculino", "corona@gmail.com", 012,"Wuhan", "C"));
    }

    @POST
    @ApiOperation(value = "Nuevo Brote", notes = "Devuelve el Brote añadido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = BroteOT.class),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/newBrote")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newBrote(@ApiParam("ID") String ID) {
        if(ID.isEmpty())
            return Response.status(500).entity(null).build();
        else{
            Brote brote = covid19Manager.newBrote(ID);
            return Response.status(201).entity(new BroteOT(brote.getID(), brote.getCasos())).build();
        }
    }

    @GET
    @ApiOperation(value = "Listado de Brotes", notes = "Devuelve una lista de Brotes")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = Brote.class, responseContainer="List")
    })
    @Path("/brotesList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response brotesList() {
        List<Brote> brotes = new LinkedList<>(this.covid19Manager.getBrotes().values());

        GenericEntity<List<Brote>> entity = new GenericEntity<List<Brote>>(brotes){};
        return Response.status(201).entity(entity).build();
    }



    @POST
    @ApiOperation(value = "Nuevo Caso", notes = "Devuelve el Caso añadido")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = CasoOT.class),
            @ApiResponse(code = 500, message = "Validation Error"),
            @ApiResponse(code = 404, message = "BroteNotFound Error")
    })
    @Path("/newCaso")
    @Produces(MediaType.APPLICATION_JSON)
    public Response newCaso(@ApiParam("ID") String ID, @ApiParam("caso") Caso caso) throws BroteNotFoundException {
        if(ID.isEmpty())
            return Response.status(500).entity(null).build();
        else{
            Caso c = covid19Manager.addCaso(ID,caso);
            return Response.status(201).entity(new CasoOT(c)).build();

        }
    }

    @GET
    @ApiOperation(value = "Listado de Casos de un brote", notes = "Devuelve una lista de casos")
    @ApiResponses(value = {
            @ApiResponse(code = 201, message = "Successful", response = CasoOT.class, responseContainer="List"),
            @ApiResponse(code = 500, message = "Validation Error")
    })
    @Path("/casosList")
    @Produces(MediaType.APPLICATION_JSON)
    public Response casosList(@ApiParam("ID") String ID) throws BroteNotFoundException {
        if(ID.isEmpty())
            return Response.status(500).entity(null).build();
        else{
            List<Caso> casos = new LinkedList<Caso>(this.covid19Manager.sortByClassification(ID));
            GenericEntity<List<Caso>> entity = new GenericEntity<List<Caso>>(casos){};
            return Response.status(201).entity(entity).build();
        }
    }

}
