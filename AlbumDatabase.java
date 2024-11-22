//need too seperate the logic into methods then call the methods (abstract & encapsulated)
//read in albums.txt
//display in alphabetical order of artist (if artist has multiple display in ascending order of release)
//display all albums of a given artist & give total playtime
//display album with shortest title
//displa details of longest track within a given album collection

import java.io.*;
import java.util.*;

public class AlbumDatabase {

  public static void main(String[] args) {
    String fileName = "albums.txt";
    List<Album> albums = new ArrayList<>();

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
        } else if (line.contains(":") && line.contains(":") && line.contains(":") && line.contains(" - ")) {
          String[] trackParts = line.split(" - ", 2);
          String[] timeParts = trackParts[0].split(":");
          int hours = Integer.parseInt(timeParts[0]);
          int minutes = Integer.parseInt(timeParts[1]);
          int seconds = Integer.parseInt(timeParts[2]);

          String title = trackParts[1].trim();
          if (currentAlbum != null) {
            Duration duration = new Duration(hours, minutes, seconds);
            currentAlbum.addTrack(
                new Track(title, duration));
          } else {
            System.out.println("error with the album: " + line);
          }
        } else {
          System.out.println("Error in line format: " + line);
        }
      }
    } catch (IOException e) {
      System.out.println("Error reading file: " + e.getMessage());
    }

    // exarmple of usage of pased data
    // ps: you may need to make a methord to get the parts you need

    // // get all albums from artist
    // String artist = "Pink Floyd";
    // for (Album album : albums) {
    // if (album.getArtist().equals(artist)) {
    // System.out.println("Found album by" + artist + ": " + album);
    // }
    // }

    // // get a specific song and its duration and the album its in
    // String Song = "On the Run";
    // for (Album album : albums) {
    // for (Track track : album.getTracks()) {
    // if (track.toString().contains(Song)) {
    // System.out.println(
    // "Found Song: " +
    // Song +
    // " | it was in the album: " +
    // album.albumtitle());
    // System.out.println("Track Length: " + track.toString());
    // }
    // }
    // }

    // 1.

    System.out.println("\n ");
    // 2.

    System.out.println("\n ");
    // 3. Display the total play time of all Kraftwerk albums in the collection.

    String targetArtist = "Kraftwerk";
    int totalSeconds = 0;

    for (Album album : albums) {
      if (album.getArtist().equalsIgnoreCase(targetArtist)) {
        totalSeconds += album.getTotalPlayTimeInSeconds();
      }
    }

    int hours = totalSeconds / 3600;
    int minutes = (totalSeconds % 3600) / 60;
    int seconds = totalSeconds % 60;

    String formattedTime = (hours < 10 ? "0" : "") + hours + ":" +
        (minutes < 10 ? "0" : "") + minutes + ":" +
        (seconds < 10 ? "0" : "") + seconds;
    System.out.println("Total play time of all " + targetArtist + " albums: " + formattedTime);

    System.out.println("\n ");
    // 4. Display the album with the shortest title

    Album shortestTitleAlbum = null;
    for (Album album : albums) {
      if (shortestTitleAlbum == null || album.getAlbumTitle().length() < shortestTitleAlbum.getAlbumTitle().length()) {
        shortestTitleAlbum = album;
      }
    }

    if (shortestTitleAlbum != null) {
      System.out.println("Album with the shortest title: " + shortestTitleAlbum.getAlbumTitle());
    }

    System.out.println("\n ");
    // 5. Display the details of the longest track in the album collection.

    Track longestTrack = null;
    Album albumWithLongestTrack = null;

    for (Album album : albums) {
      for (Track track : album.getTracks()) {
        if (longestTrack == null || track.getDuration().toSeconds() > longestTrack.getDuration().toSeconds()) {
          longestTrack = track;
          albumWithLongestTrack = album;
        }
      }
    }

    if (longestTrack != null && albumWithLongestTrack != null) {
      System.out.println("Longest Track Details:");
      System.out.println("Title: " + longestTrack.getTitle());
      System.out.println("Duration: " + longestTrack.getDuration());
      System.out.println("Album: " + albumWithLongestTrack.getAlbumTitle());
      System.out.println("Artist: " + albumWithLongestTrack.getArtist());
    } else {
      System.out.println("No tracks found in the collection.");
    }

    System.out.println("\n ");
  }
}
