


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

public class DVDMedia extends Media {

    private int yearReleased;
    private ArrayList<String> coStars = new ArrayList<>();

    //Method that is a constructor with parameters Artist, Media, Year, and an array of Co-Stars.
    public DVDMedia(String artist, String media, int year, ArrayList stars) {
        super(artist, media);
        this.yearReleased = year;
        this.coStars = stars;
    }

    //Method that returns the year the film was released.
    public int getYearReleased() {
        return yearReleased;
    }

    //Method that sets the year of the film being released.
    public void setYearReleased(int yearReleased) {
        this.yearReleased = yearReleased;
    }

    //Method that returns the ArrayList of co-stars.
    public ArrayList<String> getCoStars() {
        return coStars;
    }

    //Method that sets the ArrayList of co-stars.
    public void setCoStars(ArrayList<String> coStars) {
        this.coStars = coStars;
    }

    //Method that returns the contents of the DVD media object.
    @Override
    public String toString() {
        return super.toString() + "DVDMedia{" + "yearReleased=" + yearReleased + ", coStars=" + coStars + '}';
    }
}
