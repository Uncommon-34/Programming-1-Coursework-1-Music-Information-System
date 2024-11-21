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
        } else if (line.matches("\\d+:\\d{2}:\\d{2} - .*")) {
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

    // get all albums from artist
    String artist = "Pink Floyd";
    for (Album album : albums) {
      if (album.getArtist().equals(artist)) {
        System.out.println("Found album by" + artist + ": " + album);
      }
    }

    // get a specific song and its duration and the album its in
    String Song = "On the Run";
    for (Album album : albums) {
      for (Track track : album.getTracks()) {
        if (track.toString().contains(Song)) {
          System.out.println(
              "Found Song: " +
                  Song +
                  " | it was in the album: " +
                  album.albumtitle());
          System.out.println("Track Length: " + track.toString());
        }
      }
    }
  }
}
