package com.inserator;

import entity.Kaufen;
import impl.KaufenImpl;
import utils.StoreException;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
@Path("kaufens")
public class KaufenService {

    @GET
    @Path("/add/{kleinAnzeigeId}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addNewKaufen(@PathParam("kleinAnzeigeId") int kleinAnzeigeId ){
        String loginBenutzer = "k.ralf";
        Kaufen kaufen = new Kaufen(kleinAnzeigeId,loginBenutzer);
        String res ="";
        try(KaufenImpl kaufen1 = new KaufenImpl()) {
            res = kaufen1.addNewKauf(kaufen);
        }catch (Exception e){
            throw  new StoreException(e);
        }
        return Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }
}
