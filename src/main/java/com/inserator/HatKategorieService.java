package com.inserator;

import entity.hatKategorie;
import impl.HatKategorieImpl;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
@Path("hatKategories")
public class HatKategorieService {

    @GET
    @Path("/add/{kleinAnzeigeId}/{kategorieName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addHatKategorie(@PathParam("kleinAnzeigeId") int kleinAnzeigeId, @PathParam("kategorieName") String kategorieName){
        hatKategorie hatKategorie = new hatKategorie(kleinAnzeigeId,kategorieName);
        String res ="";
        try(HatKategorieImpl hatKategorie1 = new HatKategorieImpl()) {

            res = hatKategorie1.addHatKategorie(hatKategorie);
        }catch (Exception e){
            throw  new SecurityException(e);
        }
        return  Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin","*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("option").build();
    }
}
