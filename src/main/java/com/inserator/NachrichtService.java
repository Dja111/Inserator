package com.inserator;

import entity.Nachricht;
import impl.NachrichtImpl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("nachrichten")
public class NachrichtService {

    @GET
    @Path("/add / {message}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addMessage(@PathParam("message") String message){

        String res ="";
        Nachricht nachricht = new Nachricht(message);
        try(NachrichtImpl nachrichtImp = new NachrichtImpl()){
            res = nachrichtImp.addNachricht(nachricht);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();

    }

    // add new nachnricht
}
