


import java.io.*;
import java.util.*;

/**
 * (Use Javadoc tags to document your code too.
 *
 * @author 5994961 - Sebastian Podgaetz
 *
 * Title: (program's title) Semester: COP3804 - Fall 2020 Lecturer's Name:
 * This program reads a file and analyzes the aspects into sortable Media objects. A menu will also be provided for
 * the user to interact with the program and the catalog.
 */

public class Driver {

    ArrayList<Media> Catalog = new ArrayList<>();
    File myFile;
    Scanner inputFile = null;
    boolean tryAgain = true;
    String userInput = " ";
    Scanner keyboard = new Scanner(System.in);

    //Main method of the Program
    public static void main(String[] args) throws IOException {

        Scanner keyboard = new Scanner(System.in);

        Driver driver = new Driver();

        driver.checkFile();
        driver.processFile();
        driver.sortByMediaName();
        driver.printListByMedia();
        driver.sortByArtistName();
        driver.printListByArtist();
        driver.printMenu();
        String userInput = null;

        do {
            userInput = keyboard.next();
            switch (userInput) {
                case "1":
                    System.out.println("Enter a movie/album to search!");
                    driver.sortByMediaName();
                    String media = keyboard.next();
                    driver.searchMedia(media);
                    driver.printMenu();
                    //other stuff
                    break;
                case "2":
                    System.out.println("Enter an Artist name to search!");
                    driver.sortByArtistName();
                    String artist = keyboard.next();
                    driver.searchArtist(artist);
                    driver.printMenu();
                    break;
                case "3":
                    System.out.println("Add new things to the catalog!");
                    driver.addMedia();
                    System.out.println("Sort the new catalog by Artist (1), or by Media (2)");
                    String answer = keyboard.next();
                    if(answer.equalsIgnoreCase("1")){
                        driver.sortByArtistName();
                        System.out.println("Sorted by Artist Name!");
                    } else if (answer.equalsIgnoreCase("2")){
                        driver.sortByMediaName();
                        System.out.println("Sorted by Media Name!");
                    }
                    driver.printMenu();
                    break;
                case "4":
                    System.out.println("Good-Bye!");
                    break;
                default:
                    System.out.println("Invalid menu selection, try again!");
            }
        } while (userInput != "4");
    }

    //Method that checks if the file exists in the project folder and prompts the user if the file is not found.
    public void checkFile() {
        System.out.println("Welcome to your catalog, what is the name of the text file?");
        do {
            String userInput = keyboard.next();
            try {
                myFile = new File(userInput);
                inputFile = new Scanner(myFile);
                System.out.println("File Found!");
                tryAgain = false;
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found! Try Again!");
                tryAgain = true;
            }
        } while (tryAgain);
    }

    //Method that analyzes the file and creates the media objects.
    public void processFile() {
        //Media Type
        String mediaType = "";
        String artistName = "";
        String mediaName = "";
        //CD INFO
        String songList = null;
        String[] songSplit;                             //Array Created by String split() method.
        ArrayList<String> arrSongs = new ArrayList<>(); //Moved array to an ArrayList to be added to the CD object.
        //DVD INFO
        int mediaYear = 0;
        String coStarsList = null;
        String[] coStarSplit;                           //Array Created by String split() method.
        ArrayList<String> coStars = new ArrayList<>();  //Moved array to an ArrayList to be added to the CD object.

        while (inputFile.hasNext()) {


            mediaType = inputFile.next();

            if (mediaType.equalsIgnoreCase("C")) {

                artistName = inputFile.next();
                mediaName = inputFile.next();
                songList = inputFile.nextLine();
                songSplit = songList.split(" ", 25);
                arrSongs.addAll(Arrays.asList(songSplit));
                CDMedia CD = new CDMedia(artistName, mediaName, arrSongs);
                Catalog.add(CD);



            } else if (mediaType.equalsIgnoreCase("D")) {
                artistName = inputFile.next();
                mediaName = inputFile.next();
                try {
                    mediaYear = inputFile.nextInt();
                    if(!(mediaYear > 1900)){
                        mediaYear = 0;
                        System.out.println("Movie Year out of Bounds, needs to be fixed.");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Error: Not a number! Needs to be fixed");
                }
                coStarsList = inputFile.nextLine();

                coStarSplit = coStarsList.split(" ", 25);
                if(!coStarsList.equalsIgnoreCase("")) {
                    Collections.addAll(coStars, coStarSplit);
                }
                DVDMedia DVD = new DVDMedia(artistName, mediaName, mediaYear, coStars);
                Catalog.add(DVD);
            }

            arrSongs.clear();
            coStars.clear();

        }
    }

    //Method that sorts the catalog by Media name.
    public void sortByMediaName() {
        System.out.println("Sorting by media name");
        Collections.sort(Catalog);
    }

    //Method that prints the list organized by Media name.
    public void printListByMedia(){
        for (int i = 0; i < Catalog.size(); i++) {
            System.out.println("Media: " + Catalog.get(i).getMediaName() + " Artist: " + Catalog.get(i).getArtistName());
        }
        System.out.println("************");
    }

    //Method that sorts the catalog by Artist name.
    public void sortByArtistName() {
        System.out.println("Sorting by artist name");
        Collections.sort(Catalog, new ComparatorByArtistName());
    }

    //Method that prints the list organized by Artist name.
    public void printListByArtist(){
        for (int i = 0; i < Catalog.size(); i++) {
            System.out.println("Artist: " + Catalog.get(i).getArtistName() + " Media: " + Catalog.get(i).getMediaName());
        }
        System.out.println("************");
    }

    //Method that prints the main menu.
    public void printMenu() {
        System.out.println("--Main Menu--");
        System.out.println("1. Search by Media Title (Movie name or Album name)");
        System.out.println("2. Search by Artist (singer or actor)");
        System.out.println("3. Add media to catalog");
        System.out.println("4. Quit");
    }

    //Method that searches the whole catalog for an Artist that the user provides.
    public void searchArtist(String artist) {
        Media searchArtist = new Media(artist, " ");
        int searchIndex = Collections.binarySearch(Catalog, searchArtist, new ComparatorByArtistName());
        System.out.println(searchIndex);
        if (searchIndex < 0) {
            System.out.println("No results found searching for " + artist);
            System.out.println(searchIndex + " , " + Catalog.size());
        } else {
            while (searchIndex >= 0 && Catalog.get(searchIndex).getArtistName().compareToIgnoreCase(artist) == 0) {
                System.out.println("Artist: " + Catalog.get(searchIndex).getArtistName() + " Media: " + Catalog.get(searchIndex).getMediaName());
                searchIndex++;
            }
        }
        System.out.println("done :)");
    }

    //Method that searches the whole catalog for an Media that the user provides.
    public void searchMedia(String media){
        Media searchMedia = new Media (" ", media);
        int searchIndex = Collections.binarySearch(Catalog, searchMedia);
        System.out.println(searchIndex);
        if (searchIndex < 0) {
            System.out.println("No results found searching for " + media);
            System.out.println(searchIndex + " , " + Catalog.size());
        } else {
                while (searchIndex >= 0 && Catalog.get(searchIndex).getMediaName().compareToIgnoreCase(media) == 0) {
                    System.out.println("Media: " + Catalog.get(searchIndex).getMediaName() + " Artist: " + Catalog.get(searchIndex).getArtistName().replace("_", " "));
                    searchIndex++;
                }
        }
        System.out.println("done :)");
    }

    //Method that prompts the user to add Media to the catalog.
    public void addMedia() throws IOException {
        Scanner keyboard = new Scanner(System.in);
        System.out.println("Which file would you be adding to?");
        FileWriter fileWriter;
        PrintWriter printWriter = null;

        do {
            String addingToThisFile = keyboard.nextLine();

            try {
                myFile = new File(addingToThisFile);
                inputFile = new Scanner(myFile);
                System.out.println("File Found!");
                fileWriter = new FileWriter(addingToThisFile, true);
                printWriter = new PrintWriter(fileWriter);
                tryAgain = false;
            } catch (FileNotFoundException e) {
                System.out.println("File Not Found! Try Again!");
                tryAgain = true;
            }

        } while (tryAgain);

        ArrayList<String> songsToAdd = new ArrayList<>();
        ArrayList<String> coStarsToAdd = new ArrayList<>();
        boolean keepGoing = true;

        do {
            System.out.println("Will you be adding a CD (1) or a DVD (2) to the catalog?");
            String newMediaType = keyboard.nextLine();

            switch (newMediaType){
                case "1":
                    printWriter.print("\nC ");
                    System.out.println("Enter the name of the Artist.");
                    String newArtist = keyboard.nextLine();
                    printWriter.print(newArtist.replace(" ", "_") + " ");
                    System.out.println("Enter the name of the Album.");
                    String newAlbum = keyboard.nextLine();
                    printWriter.print(newAlbum.replace(" ", "_") + " ");
                    System.out.println("Enter the tracks in the Album, type 'q' to exit");
                    String newSong = " ";
                    while(!(newSong.equalsIgnoreCase("q"))){
                        newSong = keyboard.nextLine();
                        if(!(newSong.equalsIgnoreCase("q"))){
                            printWriter.print(newSong.replace(" ", "_") + " ");
                            songsToAdd.add(newSong.replace(" ", "_"));
                        }
                    }
                    CDMedia newCD = new CDMedia(newArtist, newAlbum, songsToAdd);
                    Catalog.add(newCD);
                    System.out.println(newArtist + " - " + newAlbum + " added to the catalog!");
                    break;
                case "2":
                    String yearForFilm = "";
                    String newCoStars = " ";
                    System.out.println("Setting up now :)");
                    printWriter.print("\nD ");
                    System.out.println("Enter the name of the Actor");
                    String newActor = keyboard.nextLine();
                    printWriter.print(newActor.replace(" " , "_") + " ");
                    System.out.println("What is the name of the Film?");
                    String newFilm = keyboard.nextLine();
                    printWriter.print(newActor.replace(" ", "_") + " ");

                        System.out.println("What year did this film release?");

                        do {
                            String newFilmYear = keyboard.nextLine();
                            try {

                                if (Integer.parseInt(newFilmYear) >= 1900) {
                                    System.out.println("Year Accepted.");
                                    printWriter.print(Integer.parseInt(newFilmYear) + " ");
                                    yearForFilm = newFilmYear;
                                    tryAgain = false;
                                } else if (Integer.parseInt(newFilmYear) < 1900) {
                                    System.out.println("Error, films on and before 1900 are unacceptable. Enter a correct year.");
                                    tryAgain = true;
                                }
                            } catch (NumberFormatException e) {
                                System.out.println("What you entered was not a valid year, enter one.");
                                tryAgain = true;
                            }
                        } while(tryAgain);



                    System.out.println("Are there any co-stars? If so enter them below, if not or when done, type 'done'");
                    while(!(newCoStars.equalsIgnoreCase("done"))) {
                        newCoStars = keyboard.nextLine();
                        if(!(newCoStars.equalsIgnoreCase("done"))){
                            printWriter.print(newCoStars.replace(" ", "_") + " ");
                            coStarsToAdd.add(newCoStars.replace(" ", "_") + " ");
                        }
                    }
                    DVDMedia newDVD = new DVDMedia(newActor, newFilm, Integer.parseInt(yearForFilm), coStarsToAdd);
                    Catalog.add(newDVD);
                    System.out.println(newActor + " - " + newFilm + " added to the catalog!");
                    break;
                default:
                    System.out.println("Incorrect Selection, Enter 1 or 2.");
                    break;
            }
            System.out.println("Would you like to add other media? (Y/N)");
            String userAnswer = keyboard.nextLine();
            if(userAnswer.equalsIgnoreCase("y")){
                keepGoing = true;
            } else if (userAnswer.equalsIgnoreCase("n")){
                keepGoing = false;
            } else {
                keepGoing = true;
            }
        } while (keepGoing);

        printWriter.close();
        System.out.println("Done :), Going back to the main menu.");

    }


}


