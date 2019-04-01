package com.inserator;

import entity.Kategorie;
import impl.kategorieImpl;
import utils.StoreException;
import utils.kategorieDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement
@Path("kategories")
public class KategorieService {

    @GET
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByName(@PathParam("name") String name){
        Kategorie kategorie = null;
        try (kategorieImpl kategorieIm = new kategorieImpl()){
            kategorie = kategorieIm.getByName(name);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(kategorie)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(){
        List<Kategorie> getAllKategorie = new ArrayList<>();

        try (kategorieImpl kategorieIm = new kategorieImpl()){
            getAllKategorie = kategorieIm.getAllNames();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(getAllKategorie)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @Path("/add/{kategorieName}")
    @Produces(MediaType.TEXT_PLAIN)
    public  Response addKategorie(@PathParam("kategorieName") String kategorieName){
        String res ="";
        Kategorie kategorie = new Kategorie(kategorieName);
        try (kategorieImpl kategorieIm = new kategorieImpl()){
            res = kategorieIm.addKategorie(kategorie);
        }catch (Exception e){
            throw  new StoreException(e);
        }
        return Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

}
