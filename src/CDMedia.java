
import java.util.ArrayList;

/**
 * (Use Javadoc tags to document your code too.
 *
 * @author 5994961 - Sebastian Podgaetz
 *
 * Title: (program's title) Semester: COP3804 - Fall 2020 Lecturer's Name:
 * This program reads a file and analyzes the aspects into sortable Media objects. A menu will also be provided for
 * the user to interact with the program and the catalog.
 */

public class CDMedia extends Media {

    private ArrayList<String> albumSongs = new ArrayList<>();

    //This creates a CD object with the parameters String Artist, String Media, and an ArrayList of Songs.
    public CDMedia(String artist, String media, ArrayList songs) {
        super(artist, media);
        this.albumSongs = songs;
    }

    //Method that returns the ArrayList with the songs of an album.
    public ArrayList<String> getAlbumSongs() {
        return albumSongs;
    }

    //Method that sets the ArrayList to a new ArrayList of songs.
    public void setAlbumSongs(ArrayList<String> albumSongs) {
        this.albumSongs = albumSongs;
    }

    //Method that returns a string with the contents of the CD Object.
    @Override
    public String toString() {
        return super.toString() + "CDMedia{" + "albumSongs=" + albumSongs + '}';
    }


}
