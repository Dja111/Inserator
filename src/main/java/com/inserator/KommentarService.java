package com.inserator;

import entity.Kommentar;
import impl.KommentarImpl;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

@Path("kommentares")
public class KommentarService {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllKommentar(){
        List<Kommentar> kommentars = new ArrayList<>();

        try (KommentarImpl kommentarIm = new KommentarImpl()){

            kommentars = kommentarIm.getAllKommentar();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(kommentars)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @Path("/addKommentar/{messageId}/{messageText}/{benutzerName}")
    @Produces(MediaType.TEXT_PLAIN)
    public  Response AddKommentar(@PathParam("messageId") int messageId, @PathParam("messageText") String messageText, @PathParam("benutzerName") String benutzerName){
        String res ="";
        Kommentar kommentar = new Kommentar(messageText);
        try (KommentarImpl kommentarIm = new KommentarImpl()){
            res = kommentarIm.addKommentar(kommentar, benutzerName, messageId);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }
    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getKommentarByAnzeigeId(@PathParam("id")int id){
        List<Kommentar> result = new ArrayList<>() ;

        try (KommentarImpl kommentarImp = new KommentarImpl()){
            result= kommentarImp.getByKommentarId(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }


}
