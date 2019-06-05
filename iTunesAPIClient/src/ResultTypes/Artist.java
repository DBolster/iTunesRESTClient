/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ResultTypes;

/**
 *
 * @author douglas.bolster@student.sl.on.ca
 */
public class Artist {

    private String artistType, artistName, artistLinkURL,
            artistId, primaryGenreName;

    public String getArtistType() {
        return artistType;
    }

    public void setArtistType(String artistType) {
        this.artistType = artistType;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getArtistLinkURL() {
        return artistLinkURL;
    }

    public void setArtistLinkURL(String artistLinkURL) {
        this.artistLinkURL = artistLinkURL;
    }

    public String getArtistId() {
        return artistId;
    }

    public void setArtistId(String artistId) {
        this.artistId = artistId;
    }
    
    public String getPrimaryGenreName(){        
        return primaryGenreName;
    }

}
