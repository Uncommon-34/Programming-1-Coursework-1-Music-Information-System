import java.io.*;
import java.util.*;

public class AlbumDatabase {

  //1 reading in the AlbumCollection from the file 'albums.txt'
  //Method that creates a list of AlbumCollections for each artist from a file
  public static List<AlbumCollection> makeAlbumCollection(String fileName) {
    List<String> artists = new ArrayList<>(); //Stores a list of unique artist names
    List<Album> albums = new ArrayList<>(); //List of albums
    List<AlbumCollection> albumCollections = new ArrayList<>(); //creates a list of AlbumCollections that're grouped by artist

    //reads the file with a BufferedReader object
    try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
      String line;
      Album currentAlbum = null;

      //loops through each line in he file
      while ((line = reader.readLine()) != null) {
        line = line.trim();

        //checks if the line contains album info in the format  || <Artist> : <AlbumTItle> (<Year>)
        if (line.contains(" : ") && line.contains("(") && line.contains(")")) {
          String[] parts = line.split(" : ");
          String artist = parts[0].trim(); //if the line does we take the artist name
          String[] albumParts = parts[1].split("\\("); //split the title & year
          String albumTitle = albumParts[0].trim(); //get the album title
          int year = Integer.parseInt(albumParts[1].replace(")", "").trim());

          //we make a new album object & add it too the 'albums' list
          currentAlbum = new Album(artist, albumTitle, year);
          albums.add(currentAlbum);

          //if the artist isnt in the artist list we add them
          if (artists.contains(artist)) {} else {
            artists.add(artist);
          }
          //if the line contains track info
        } else if (
          line.contains(":") &&
          line.contains(":") &&
          line.contains(":") &&
          line.contains(" - ") //one long if statment too check for if its track info
        ) {
          String[] trackParts = line.split(" - ", 2); //splits time & track title
          String[] timeParts = trackParts[0].split(":"); //splits time into hours, munuets & seconds; ready for Duration
          int hours = Integer.parseInt(timeParts[0]); //parses the time & title values into variables
          int minutes = Integer.parseInt(timeParts[1]);
          int seconds = Integer.parseInt(timeParts[2]);
          String title = trackParts[1].trim();

          //If we have an album too add too, add the track we just discovered
          if (currentAlbum != null) {
            Duration duration = new Duration(hours, minutes, seconds);
            currentAlbum.addTrack(new Track(title, duration));
          } else {
            System.out.println("error with the album: " + line);
          }
        } else {
          //if unexpected line format throw an error/report it via the terminal
          System.out.println("Error in line format: " + line);
        }
      } //end of while loop
    } catch (IOException e) {
      //handles file reading errors
      System.out.println("Error reading file: " + e.getMessage());
    } //end of catch

    //Create's an Album Collection for each artist
    AlbumCollection currentCollection = null;
    for (String art : artists) {
      currentCollection = new AlbumCollection(art); //actualy does the creation
      for (Album album : albums) {
        //add albums too the correct collection
        if (album.getArtist().equalsIgnoreCase(art)) {
          currentCollection.addAlbum(album);
        }
      }
      albumCollections.add(currentCollection); //add the collection to the list
    }

    return albumCollections; //returns the list of album collections
  } //end of makeAlbumCollection

  //

  //

  //

  // 2. Display the entire album collection, arranged in alphabetical order of the
  // album artist.
  // If more than one album exists for a given artist, they should be displayed in
  // ascending order of the year of release (oldest Album first).

  //I will use an insersion sort checking against the two parameters, this gives it a time complexity of O(n^2)
  public static void two(List<AlbumCollection> ourCollection) {
    //init an empty list for our sourted album objects
    List<Album> sortedAlbums = new ArrayList<>();

    //make an array of all the albums with an insersion sort as described above
    for (AlbumCollection albumCollection : ourCollection) {
      for (Album currentAlbum : albumCollection.getAlbums()) {
        int insertionIndex = findInsertionIndex(sortedAlbums, currentAlbum);
        sortedAlbums.add(insertionIndex, currentAlbum);
      }
    } //we now have a sorted list of all albums
      
      //just have too print the sorted albums
      printAlbums(sortedAlbums);
  
    //end of the logic for Q2, just helper methods left

    //abstracted/encapsulated methods aka helper methods 
    private static int findInsertionIndex(List<Album> sortedAlbums, Album currentAlbum) {//compares two given albums by artist, then year if needed
      for (int index = 0; index < sortedAlbums.size(); index++) {//iterate over sortedAlbums
       if (compareAlbums(currentAlbum, sortedAlbums.get(index)) =!0) {//comparing the current album and the album at index i for the sortedAlbums
          return index;
      }//end of if
    }//end of for loop
      return sortedAlbums.size();//inset at the end since it ant be inserted inbetween the list
    } //end of findInsersionIndex

    private static int compareAlbums(Album albumA, Album albumB) { //compares two given albums by artist, then year if needed
      int artistCompare = albumA.getArtist().compareToIgnoreCase(albumB.getArtist()); //returns 0 when strings are equal
      if (artistCompare != 0) {
        return artistCompare;
      } else {
        return Integer.compare(albumA.getAlbumYear(), albumB.getAlbumYear());
      }//end of if else
    }//end of compareAlbums

    private static void printAlbums(List<Album> sortedAlbums) { //helper method that prints the sortedAlbums
      for (Album nextAlbum : sortedAlbums) {
        System.out.println(nextAlbum.stringAlbumInfo());
      }//end of for loop
    }//end of sortedAlbums

  
  }//end of Q2
  //

  //

  //

  // 3. Display the total play time of all Kraftwerk albums in the collection.
  public static void three(
    List<AlbumCollection> ourCollection,
    String artistname
  ) {
    int totalSeconds = 0;

    for (AlbumCollection coll : ourCollection) {
      if (coll.getArtist().equalsIgnoreCase(artistname)) {
        List<Album> Albums = coll.getAlbums();
        for (Album album : Albums) {
          totalSeconds += album.getAlbumLength();
        }
      }
    }
    int hours = totalSeconds / 3600;
    int minutes = (totalSeconds % 3600) / 60;
    int seconds = totalSeconds % 60;

    String formattedTime =
      (hours < 10 ? "0" : "") +
      hours +
      ":" +
      (minutes < 10 ? "0" : "") +
      minutes +
      ":" +
      (seconds < 10 ? "0" : "") +
      seconds;

    System.out.println(
      "Total play time of all " + artistname + " albums: " + formattedTime
    );

    System.out.println("\n ");
  }

  //

  //

  //

  // 4. Display the album with the shortest title
  public static void four(List<AlbumCollection> ourCollection) {
    Album shortestTitleAlbum = null;

    for (AlbumCollection coll : ourCollection) {
      List<Album> Albums = coll.getAlbums();
      for (Album album : Albums) {
        if (
          shortestTitleAlbum == null ||
          album.getAlbumTitle().length() <
          shortestTitleAlbum.getAlbumTitle().length()
        ) {
          shortestTitleAlbum = album;
        }
      }
    }

    if (shortestTitleAlbum != null) {
      System.out.println(
        "Album with the shortest title: " + shortestTitleAlbum.getAlbumTitle()
      );
    }

    System.out.println("\n ");
  }

  //

  //

  //

  // 5. Display the details of the longest track in the album collection.
  public static void five(List<AlbumCollection> ourCollection) {
    ArrayList<Duration> comp = new ArrayList<Duration>();
    Track longestTrack = null;
    Album albumWithLongestTrack = null;

    for (AlbumCollection coll : ourCollection) {
      List<Album> Albums = coll.getAlbums();

      for (Album album : Albums) {
        for (Track track : album.getTracks()) {
          if (
            longestTrack == null ||
            track.getDuration().getDurationLength() >
            longestTrack.getDuration().getDurationLength()
          ) {
            longestTrack = track;
            albumWithLongestTrack = album;
          }
          Duration trackduration = track.getDuration();
          comp.add(trackduration);
        }
      }
    }

    Collections.sort(comp);

    Duration longestDuration = comp.get(comp.size() - 1); //same as getLast() but more robust

    if (longestTrack != null && albumWithLongestTrack != null) {
      System.out.println("Longest Track Details:");
      System.out.println("Title: " + longestTrack.getTitle());
      System.out.println("Duration: " + longestDuration.stringDuration());
      System.out.println("Album: " + albumWithLongestTrack.getAlbumTitle());
      System.out.println("Artist: " + albumWithLongestTrack.getArtist());
    } else {
      System.out.println("No tracks found in the collection.");
    }

    System.out.println("\n ");
  }

  //

  //

  //

  public static void main(String[] args) {
    List<AlbumCollection> ourCollection = makeAlbumCollection("albums.txt");
    two(ourCollection);
    three(ourCollection, "Kraftwerk");
    four(ourCollection);
    five(ourCollection);
    // List all albums and there tracks orginised by artist exarmple:
    // System.out.println("Listing all albums and Tracks: \n\n");
    // for (AlbumCollection coll : ourCollection) {
    // System.out.println("All albums for " + coll.getArtist() + " :");
    // List<Album> Albums = coll.getAlbums();
    // for (Album album : Albums) {
    // System.out.println(" " + album.toString());
    // }
    // }

  } // Main
} // AlbumDatabase
