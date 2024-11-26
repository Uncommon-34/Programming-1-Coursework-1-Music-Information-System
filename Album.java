//store details of an album
//name of artist, title of album (str)
//release year of album (int)
//collection of track objects

import java.util.*; //needed for lists

public class Album {

  private final String artist;
  private final String title;
  private final int year;
  //stores a list of tracks
  private final List<Track> tracks = new ArrayList<>();

  //constructor
  public Album(String artist, String title, int year) {
    this.artist = artist;
    this.title = title;
    this.year = year;
  }

  //method that adds/appends a track too the list
  public void addTrack(Track track) {
    tracks.add(track);
  }

  //returns the private artist String, otherwise is impossible too access (acessor method)
  public String getArtist() {
    return artist;
  }

  //returns the list of tracks
  public List<Track> getTracks() {
    return tracks;
  }

  //acessor method for the total duration in seconds, hence why we use the word length, easier then getTotalAlbumDurationInSeconds
  public int getAlbumLength() {
    int totalSeconds = 0;
    for (Track track : tracks) {
      totalSeconds += track.getDuration().getDurationLength();
    }
    return totalSeconds;
  }

  public String getAlbumTitle() {
    return title;
  }

  public int getAlbumYear() {
    return year;
  }

  //returns the album's info as a String without the tracks
  public String stringAlbumInfo() {
    StringBuilder builderObject = new StringBuilder();
    builderObject
      .append(artist)
      .append(" : ")
      .append(title)
      .append(" (")
      .append(year)
      .append(")");
    return builderObject.toString();
  }

  //returns the album's info as a String with the tracks as the Track datatype, doesnt include the artist as its called for an album collection of a single artist
  public String stringAlbumTracks() {
    StringBuilder builderObject = new StringBuilder();
    builderObject.append(title).append(" (").append(year).append(")\n");
    tracks.forEach(track ->
      builderObject.append("  ").append(track.stringTrack()).append("\n")
    );
    return builderObject.toString();
  }

  //test harness
  public static void main(String[] args) {
    Album albumObject = new Album(
      "MYTH & ROID",
      "THE BEST OF MYTH & ROID",
      2020
    );

    //defining Tracks too append manualy
    Track trackObjectOne = new Track("HOLLOW HUNGER", new Duration(0, 3, 44)); //static definition, can change in the code, dont want too clutter w/ scanners
    Track trackObjectTwo = new Track("HYDRA", new Duration(0, 4, 52));
    Track trackObjectThree = new Track("VORACITY", new Duration(0, 3, 52));

    //appending Tracks too the album manualy, this is normaly handled by _____________________
    albumObject.addTrack(trackObjectOne);
    albumObject.addTrack(trackObjectTwo);
    albumObject.addTrack(trackObjectThree);

    System.out.println(
      "This is a test harness function for the Album class\n Example of stringAlbumTracks:\n" +
      albumObject.stringAlbumTracks() +
      "\nExample of stringAlbumInfo:\n" +
      albumObject.stringAlbumInfo() +
      "\n Example of getAlbumLength\n" +
      albumObject.getAlbumLength()
    );
  }
}
