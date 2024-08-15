
/**
 * (Use Javadoc tags to document your code too.
 *
 * @author 5994961 - Sebastian Podgaetz
 *
 * Title: (program's title) Semester: COP3804 - Fall 2020 Lecturer's Name:
 * This program reads a file and analyzes the aspects into sortable Media objects. A menu will also be provided for
 * the user to interact with the program and the catalog.
 */

public class Media implements Comparable<Media> {

    private String artistName;
    private String mediaName;

    //Constructor for the Media object with the parameters of a String Artist and String Media.
    public Media(String artist, String media){
        this.artistName = artist;
        this.mediaName = media;
    }

    //Method that returns the name of the Artist.
    public String getArtistName() {
        return artistName;
    }

    //Method that sets the name of the Artist.
    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    //Method that returns the name of the Media
    public String getMediaName() {
        return mediaName;
    }

    //Method that sets the name of the Media.
    public void setMediaName(String mediaName) {
        this.mediaName = mediaName;
    }

    //Method that returns a String with the Media object contents.
    @Override
    public String toString() {
        return "Media{" + "artistName=" + artistName + ", mediaName=" + mediaName + '}';
    }

    //Method that compares the Media object to another Media object.
    @Override
    public int compareTo(Media o) {
        Media otherMedia = (Media) o;
        return mediaName.compareTo(otherMedia.mediaName);
    }
}

