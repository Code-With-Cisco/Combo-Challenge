


import java.util.Comparator;

/**
 * (Use Javadoc tags to document your code too.
 *
 * @author 5994961 - Sebastian Podgaetz
 *
 * Title: (program's title) Semester: COP3804 - Fall 2020 Lecturer's Name:
 * This program reads a file and analyzes the aspects into sortable Media objects. A menu will also be provided for
 * the user to interact with the program and the catalog.
 */

public class ComparatorByArtistName implements Comparator<Media> {

    //Method that compares 2 media objects.
    @Override
    public int compare(Media media1, Media media2) {


            return media1.getArtistName().compareTo(media2.getArtistName());

    }
}

