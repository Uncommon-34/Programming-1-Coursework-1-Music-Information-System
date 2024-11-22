import java.io.*;
import java.util.*;

public class AlbumDatabase {

public static List<AlbumCollection> makeAlbumCollection(String fileName) {
  List<String> artists = new ArrayList<>();
  List<Album> albums = new ArrayList<>();
  List<AlbumCollection> albumCollections = new ArrayList<>();
  
  try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
    String line;
    Album currentAlbum = null;

    while ((line = reader.readLine()) != null) {
      line = line.trim();

      if (line.contains(" : ") && line.contains("(") && line.contains(")")) {
        String[] parts = line.split(" : ");
        String artist = parts[0].trim();
        String[] albumParts = parts[1].split("\\(");
        String albumTitle = albumParts[0].trim();
        int year = Integer.parseInt(albumParts[1].replace(")", "").trim());

        currentAlbum = new Album(artist, albumTitle, year);
        albums.add(currentAlbum);
       
          if (artists.contains(artist)) {

        } else {
          artists.add(artist);
        }
      } else if (
        line.contains(":") &&
        line.contains(":") &&
        line.contains(":") &&
        line.contains(" - ")
      ) {
        String[] trackParts = line.split(" - ", 2);
        String[] timeParts = trackParts[0].split(":");
        int hours = Integer.parseInt(timeParts[0]);
        int minutes = Integer.parseInt(timeParts[1]);
        int seconds = Integer.parseInt(timeParts[2]);

        String title = trackParts[1].trim();
        if (currentAlbum != null) {
          Duration duration = new Duration(hours, minutes, seconds);
          currentAlbum.addTrack(new Track(title, duration));
        } else {
          System.out.println("error with the album: " + line);
        }
      } else {
        System.out.println("Error in line format: " + line);
      }
    } //while linereader not null
  } catch (IOException e) {
    System.out.println("Error reading file: " + e.getMessage());
  } //Catch

  AlbumCollection currentCollection = null;

  for (String art : artists) {
      currentCollection = new AlbumCollection(art);
      for (Album album : albums) {
        if (album.getArtist().equalsIgnoreCase(art)) {
          currentCollection.addAlbum(album);
        }
      }
      albumCollections.add(currentCollection);
    }

  return albumCollections;

}


 // 2. Display the entire album collection, arranged in alphabetical order of the album artist.
 // If more than one album exists for a given artist, they should be displayed in ascending
 // order of the year of release (oldest Album first).
 public static void two() {


  
  System.out.println("\n");
 }

 // 3. Display the total play time of all Kraftwerk albums in the collection.
public static void three(List<AlbumCollection> ourCollection, String artistname) {
 int totalSeconds = 0;

 for (AlbumCollection coll : ourCollection) {
  if (coll.getArtist().equalsIgnoreCase(artistname)) {
    List<Album> Albums = coll.getAlbums();
    for (Album album : Albums) {
      totalSeconds += album.getTotalPlayTimeInSeconds();
    }
  }
}
 int hours = totalSeconds / 3600;
 int minutes = (totalSeconds % 3600) / 60;
 int seconds = totalSeconds % 60;

 String formattedTime = (hours < 10 ? "0" : "") + hours + ":" +
     (minutes < 10 ? "0" : "") + minutes + ":" +
     (seconds < 10 ? "0" : "") + seconds;

 System.out.println("Total play time of all " + artistname + " albums: " + formattedTime);

 System.out.println("\n ");
}

 // 4. Display the album with the shortest title
public static void four(List<AlbumCollection> ourCollection) {
 Album shortestTitleAlbum = null;

 for (AlbumCollection coll : ourCollection) {
  List<Album> Albums = coll.getAlbums();
    for (Album album : Albums) {
      if (shortestTitleAlbum == null || album.getAlbumTitle().length() < shortestTitleAlbum.getAlbumTitle().length()) {
        shortestTitleAlbum = album;
      }
    }
  }

 if (shortestTitleAlbum != null) {
   System.out.println("Album with the shortest title: " + shortestTitleAlbum.getAlbumTitle());
 }

 System.out.println("\n ");
}

 // 5. Display the details of the longest track in the album collection.
public static void five(List<AlbumCollection> ourCollection) {
  ArrayList<Duration> comp = new ArrayList<Duration>();
  Track longestTrack = null;
  Album albumWithLongestTrack = null;

for (AlbumCollection coll : ourCollection) {
  List<Album> Albums = coll.getAlbums();

 for (Album album : Albums) {
   for (Track track : album.getTracks()) {
    if (longestTrack == null || track.getDuration().getDurationLength() > longestTrack.getDuration().getDurationLength()) {
      longestTrack = track;
      albumWithLongestTrack = album;
    }
    Duration trackduration = track.getDuration();
    comp.add(trackduration);
   }
 }
}

Collections.sort(comp);

Duration longestdDuration = comp.getLast();

if (longestTrack != null && albumWithLongestTrack != null) {
  System.out.println("Longest Track Details:");
  System.out.println("Title: " + longestTrack.getTitle());
  System.out.println("Duration: " + longestdDuration.stringDuration());
  System.out.println("Album: " + albumWithLongestTrack.getAlbumTitle());
  System.out.println("Artist: " + albumWithLongestTrack.getArtist());
} else {
  System.out.println("No tracks found in the collection.");
}

System.out.println("\n ");
}

    public static void main(String[] args) {
    List<AlbumCollection> ourCollection =   makeAlbumCollection("albums.txt");
    
    three(ourCollection, "Kraftwerk");

    four(ourCollection);

    five(ourCollection);

      //List all albums and there tracks orginised by artist exarmple: 
      // for (AlbumCollection coll : ourCollection) {
      //   System.out.println("All albums for " + coll.getArtist() + " :\n");
      //   List<Album> Albums = coll.getAlbums();
      //     for (Album album : Albums) {
      //       System.out.println(album.toString());
      //     }
      // }

  } //Main
} //AlbumDatabase