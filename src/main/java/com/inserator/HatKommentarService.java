package com.inserator;


import entity.hatKommentar;
import impl.HatKommentarImpl;
import utils.HatKommentarDao;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.IOException;

@XmlRootElement
@Path("hatkommentars")
public class HatKommentarService {

    @GET
    @Path("/add/{kleinAnzeigeId}/{benutzerName}/{kommentarId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response addHatKommentar(@PathParam("kleinAnzeigeId")int kleinAnzeigeId,@PathParam("benutzerName")String benutzerName,@PathParam("kommentarId")int
                                    kommentarId){
        hatKommentar hatKommentar = new hatKommentar(kleinAnzeigeId,benutzerName,kommentarId);
        String res = "";

        try (HatKommentarImpl hatKommentarIm = new HatKommentarImpl()){
            res = hatKommentarIm.addNewHatKommentar(hatKommentar);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("option").build();
    }
}
