/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ITunesRESTapi;

import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.PathParam;



/**
 * REST Web Service
 * Contains the endpoints for the REST api which contain the individual
 * iTunes lookup and search request parameters
 * @author Douglas Bolster
 */
@Path("artist")
public class ITunesRESTAPIResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of ITunesRESTAPIResource
     */
    public ITunesRESTAPIResource() {
    }

    /**
     * Retrieves representation of an instance of
     * ITunesRESTapi.ITunesRESTAPIResource
     *
     * @return an instance of java.lang.String
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getJson() {
        //TODO return proper representation object
        throw new UnsupportedOperationException();
    }
    
    @GET
    @Path("{name}/name")    
    @Produces(MediaType.APPLICATION_JSON)
    public String getArtistIdFromName (@PathParam("name") String artistName) {        
        //Valid artist IDs = 909253 Jack Johnson
        return getResults("https://itunes.apple.com/search?term=" + artistName);
      
    }

    @GET
    @Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public String getArtist(@PathParam("id") Integer artistId) {
        
        //Valid artist IDs = 909253 Jack Johnson
       return getResults("https://itunes.apple.com/lookup?id=" + artistId);
    }
    
    @GET
    @Path("{id}/albums")    
    @Produces(MediaType.APPLICATION_JSON)
    public String getAlbum(@PathParam("id") Integer artistId) {
        
        //Valid artist IDs = 909253 Jack Johnson
        return getResults("https://itunes.apple.com/lookup?id=" + artistId + "&entity=album");
      
    }
    
    @GET
    @Path("{id}/songs")
    @Produces(MediaType.APPLICATION_JSON)
    public String getSongs(@PathParam("id") Integer artistId) {
        
        //Valid artist IDs = 909253 Jack Johnson
        return getResults("https://itunes.apple.com/lookup?id=" + artistId + "&entity=song");
      
    }

    /**
     * PUT method for updating or creating an instance of ITunesRESTAPIResource
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }

    public String getResults(String target) {
        String result = "";
        try {
            URL url = new URL(target);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.setRequestProperty("Accept", "application/vnd.github.v3+json");
            connection.connect();

            int code = connection.getResponseCode();
            if (code == HttpURLConnection.HTTP_OK) {
                Logger.getLogger(ITunesRESTAPIResource.class.getName()).log(Level.INFO, "Connection RC: " + code);

                Scanner ns = new Scanner(connection.getInputStream());

                while (ns.hasNext()) {
                    result += ns.next();
                }
                
                ns.close();
            }else{
                System.out.println("Cannot create HTTP Connection");
            }
           connection.disconnect();
            
        } catch (MalformedURLException ex) {
            Logger.getLogger(ITunesRESTAPIResource.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        } catch (IOException ex) {
            Logger.getLogger(ITunesRESTAPIResource.class.getName()).log(Level.SEVERE, ex.getMessage(), ex);
        }
        return result;
    }
}
