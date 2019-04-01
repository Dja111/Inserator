package com.inserator;


import entity.Benutzer;
import impl.BenutzerImpl;
import utils.DBUtil;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Path("benutzers")
public class BenutzerService {

    @GET
    @Path("/check")
    @Produces(MediaType.TEXT_PLAIN)
    public String connectionResult() {
        String db = "INSDB";
        String res = null;
        res = DBUtil.checkDatabaseExists(db) == true ? "db is connected" : "db is NOT connected";
        return res + "\n";
    }


    //getAllBenutzer

    @GET
    @Path("/benutzers")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBenutzer()throws IOException {
        List<Benutzer>  benutzerList = new ArrayList<>();
        try (BenutzerImpl benutzerImpl = new BenutzerImpl()){
            benutzerList = benutzerImpl.getAllBenutzer();
            System.out.println(benutzerList);
        }
        return  Response.status(200).entity(benutzerList)
                .header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("option").build();
    }


    //getByUserName

    @GET
    @Path("/{benutzerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBenutzerByBenutzerName(@PathParam("benutzerName") String benutzerName){

        Benutzer benutzer = null;

        try (BenutzerImpl benutzerImpl = new BenutzerImpl()){
            benutzer = benutzerImpl.getBenutzerByBenutzerName(benutzerName);
        }catch (Exception e){
            e.printStackTrace();
        }

        return  Response.status(200).entity(benutzer)
                .header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("option").build();
    }

}
