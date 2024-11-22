//store details of an album
//name of artist, title of album (str)
//release year of album (int)
//collection of track objects

import java.util.*;

public class Album {

  private final String artist, title;
  private final int year;
  private final List<Track> tracks = new ArrayList<>();

  public Album(String artist, String title, int year) {
    this.artist = artist;
    this.title = title;
    this.year = year;
  }

  public void addTrack(Track track) {
    tracks.add(track);
  }

  public String getArtist() {
    return artist;
  }

  public List<Track> getTracks() {
    return tracks;
  }

  public int getTotalPlayTimeInSeconds() {
    int totalSeconds = 0;

    for (Track track : tracks) {
      totalSeconds += track.getDuration().toSeconds();
    }

    return totalSeconds;
  }

  public String getAlbumTitle() {
    return title;
  }

  public String albumtitle() {
    StringBuilder sb = new StringBuilder();
    sb
        .append(artist)
        .append(" : ")
        .append(title)
        .append(" (")
        .append(year)
        .append(")");
    return sb.toString();
  }

  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb
        .append(artist)
        .append(" : ")
        .append(title)
        .append(" (")
        .append(year)
        .append(")\n");
    tracks.forEach(track -> sb.append("  ").append(track).append("\n"));
    return sb.toString();
  }
}
