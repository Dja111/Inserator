package com.inserator;

import entity.KleinAnzeige;
import impl.KleinanzeigeImpl;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlRootElement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@XmlRootElement
@Path("kleinanzeiges")
public class KleinanzeigeService {


    @GET
    @Path("/{title}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getByTitle(@PathParam("title") String title){

        KleinAnzeige kleinanzeige = null;

        try (KleinanzeigeImpl kleinanzeigeImp = new KleinanzeigeImpl()){
            kleinanzeige = kleinanzeigeImp.getByTitle(title);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(kleinanzeige)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }


    @GET
    @Path("/preis/{preis}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getByPreis(@PathParam("preis") double preis){
            KleinAnzeige kleinanzeige = null;
        try (KleinanzeigeImpl kleinanzeigeIm = new KleinanzeigeImpl()){
            kleinanzeige = kleinanzeigeIm.getByPreis(preis);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(kleinanzeige)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    //add new ANzeige
    @GET
    @Path("/add/kleinanzeige/{title}/{preis}/{beschreibung}/{kategorieName}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response addKleinanzeige(@PathParam("title") String title,
                                    @PathParam("preis") double preis, @PathParam("beschreibung") String beschreibung,
                                    @PathParam("kategorieName") String name){


        Date dNow = new Date();
        String loginBenutzerName = "k.ralf";
        String status ="aktiv";


        java.lang.String res ="";

        try (KleinanzeigeImpl kleinanzeigeImp = new KleinanzeigeImpl()){

            res = kleinanzeigeImp.addKleinanzeige(title,beschreibung,preis,loginBenutzerName,status,name);

        }catch (Exception e){
            e.printStackTrace();
        }

        return Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }


    @GET
    @Path("anzeigeById/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getById(@PathParam("id") int id){
        KleinAnzeige kleinAnzeige = null;

        try (KleinanzeigeImpl kleinanzeigeIm = new KleinanzeigeImpl()){
            kleinAnzeige = kleinanzeigeIm.getById(id);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(kleinAnzeige)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @Path("/delete/{id}")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteByid(@PathParam("id")int id){
        String res ="";

        try (KleinanzeigeImpl kleinanzeigeIm = new KleinanzeigeImpl()){
            res = kleinanzeigeIm.deleteKleinAnzeige(id);
        }catch (Exception e){
            e.printStackTrace();
        }

        return Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }


    @GET
    @Path("/update/{id}/{title}/{text}/{preis}/{datum}/{ersteller}/{status}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response updateAnzeige(@PathParam("id") int id, @PathParam("title") String title,@PathParam("text") String text, @PathParam("preis") double preis,
                                   @PathParam("datum") String datum, @PathParam("ersteller") String ersteller, @PathParam("status") String status) throws ParseException {


        SimpleDateFormat sdf1 = new SimpleDateFormat("dd-MM-yyyy");
        java.util.Date date = sdf1.parse(datum);
        java.sql.Date sqlStartDate = new java.sql.Date(date.getTime());
        KleinAnzeige kleinAnzeigeInput = new KleinAnzeige(id,title,text,preis,sqlStartDate,ersteller,status);
        //KleinAnzeige kleinAnzeige = null;
        String res ="";
        try (KleinanzeigeImpl kleinanzeigeIm = new KleinanzeigeImpl()){
            res = kleinanzeigeIm.updateKleinanzeige(kleinAnzeigeInput);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(res)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();

    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getAll(){
        List<KleinAnzeige> Result = new ArrayList<>();

        try (KleinanzeigeImpl kleinanzeigeIm = new KleinanzeigeImpl()){
            Result = kleinanzeigeIm.getAllKleinanzeige();
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(Result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

    @GET
    @Path("/byUserAktiv/{benutzerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getAllAnByUserNamAktiv(@PathParam("benutzerName") String benutzerName){
        List<KleinAnzeige> Result = new ArrayList<>();

        try (KleinanzeigeImpl kleinanzeigeIm = new KleinanzeigeImpl()){
            Result = kleinanzeigeIm.getAllKleinanzeigeByUserNameAktiv(benutzerName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(Result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }


    @GET
    @Path("/byUserVerkauft/{benutzerName}")
    @Produces(MediaType.APPLICATION_JSON)
    public  Response getAllAnByUserNamVerkauft(@PathParam("benutzerName") String benutzerName){
        List<KleinAnzeige> Result = new ArrayList<>();

        try (KleinanzeigeImpl kleinanzeigeIm = new KleinanzeigeImpl()){
            Result = kleinanzeigeIm.getAllKleinanzeigeByUserNameVerkauft(benutzerName);
        }catch (Exception e){
            e.printStackTrace();
        }
        return Response.status(200).entity(Result)
                .header("Access-Control-Allow-Origin", "*")
                .header("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT")
                .allow("OPTIONS").build();
    }

}
