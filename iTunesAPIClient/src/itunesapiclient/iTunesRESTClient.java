/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itunesapiclient;

import ResultTypes.OuterResult;
import ResultTypes.WrapperType;
import ResultTypes.Song;
import ResultTypes.Artist;
import ResultTypes.Album;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import java.util.ArrayList;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;

/**
 * Jersey REST client generated for REST resource:ITunesRESTAPIResource
 * 
 * @author douglas.bolster@student.sl.on.ca and the genie inside Netbeans
 */
public class iTunesRESTClient {

    private WebTarget webTarget;
    private Client client;
    private static final String BASE_URI = "http://localhost:8080/iTunesRESTapi/itunes";
    Artist artist;
    Song song;
    Album album;
    ArrayList<Album> albums;
    ArrayList<Song> songs;
    
    public iTunesRESTClient() {
        client = javax.ws.rs.client.ClientBuilder.newClient();
        webTarget = client.target(BASE_URI).path("artist");
    }
    
    /**
     * Returns the Artist ID based on a Search request to the /name endpoint
     * @param name
     * @return
     * @throws ClientErrorException 
     */
    public String getArtistIdFromName(String name) throws ClientErrorException {
        WebTarget resource = webTarget;
        String resultString = "";

        resource = resource.path(java.text.MessageFormat.format("{0}/name", new Object[]{name}));
        resultString = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        OuterResult outerResult = gson.fromJson(resultString, OuterResult.class);
        //If we got no results, or more than one result (i.e maybe user typed a track # instead)
        //then return null;
        if (outerResult.getResultCount() == 0) {
            return null;
        }
        //System.out.println(outerResult.getResultCount());
        JsonObject[] results = outerResult.getResults();
        WrapperType wrapperType = gson.fromJson(results[0], WrapperType.class);
        System.out.println(wrapperType.getArtistId());
        return wrapperType.getArtistId();
    }

    /**
     * Returns an Artist object from a lookup request endpoint
     * @param id
     * @return
     * @throws ClientErrorException 
     */
    public Artist getArtist(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        String resultString = "";
       
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException ne) {
            return null;
        }

        resource = resource.path(java.text.MessageFormat.format("{0}", new Object[]{id}));
        resultString = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        OuterResult outerResult = gson.fromJson(resultString, OuterResult.class);
        //If we got no results, or more than one result (i.e maybe user typed a track # instead)
        //then return null;
        if (outerResult.getResultCount() == 0 || outerResult.getResultCount() > 1) {
            return null;
        }
        //System.out.println(outerResult.getResultCount());
        JsonObject[] results = outerResult.getResults();

        for (JsonObject j : results) {
            WrapperType wrapperType = gson.fromJson(j, WrapperType.class);
            switch (wrapperType.getWrapperType()) {
                case "artist":
                    artist = gson.fromJson(j, Artist.class);
                    break;
            }
        }
        return artist;
    }
    
    /**
     * Returns an arrayList of Album types from a /albums endpoint
     * @param id
     * @return
     * @throws ClientErrorException 
     */
    public ArrayList<Album> getAlbum(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        String resultString = "";
        albums = new ArrayList<Album>();
        
        try {
            Integer.parseInt(id);
        } catch (NumberFormatException ne) {
            return null;
        }


        resource = resource.path(java.text.MessageFormat.format("{0}/albums", new Object[]{id}));
        resultString = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        OuterResult outerResult = gson.fromJson(resultString, OuterResult.class);
        if (outerResult.getResultCount() == 0) {
            return null;
        }
        System.out.println(outerResult.getResultCount());
        JsonObject[] results = outerResult.getResults();

        for (JsonObject j : results) {
            WrapperType wrapperType = gson.fromJson(j, WrapperType.class);

            switch (wrapperType.getWrapperType()) {
                case "artist":
                    artist = gson.fromJson(j, Artist.class);
                    break;
                case "collection":
                    album = gson.fromJson(j, Album.class);
                    albums.add(album);
                    break;
            }
        }
        return albums;
    }
    
    /**
     * Returns an arraylist of Song types from a /songs endpoint
     * @param id
     * @return
     * @throws ClientErrorException 
     */
    public ArrayList<Song> getSongs(String id) throws ClientErrorException {
        WebTarget resource = webTarget;
        String resultString = "";

        try {
            Integer.parseInt(id);
        } catch (NumberFormatException ne) {
            return null;
        }

        songs = new ArrayList<Song>();
        resource = resource.path(java.text.MessageFormat.format("{0}/songs", new Object[]{id}));
        resultString = resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        OuterResult outerResult = gson.fromJson(resultString, OuterResult.class);
        if (outerResult.getResultCount() == 0) {
            return null;
        }
        System.out.println(outerResult.getResultCount());
        JsonObject[] results = outerResult.getResults();

        for (JsonObject j : results) {
            WrapperType wrapperType = gson.fromJson(j, WrapperType.class);

            switch (wrapperType.getWrapperType()) {
                case "artist":
                    artist = gson.fromJson(j, Artist.class);
                    break;
                case "track":
                    song = gson.fromJson(j, Song.class);
                    songs.add(song);
                    break;

            }

        }
        return songs;
    }
    
    public String getJson() throws ClientErrorException {
        //WebTarget resource = webTarget;
        //return resource.request(javax.ws.rs.core.MediaType.APPLICATION_JSON).get(String.class);
        return "Function not implemented";
    }

    public void close() {
        client.close();
    }
}
