//store details of an album track
//store the name of the track (str) and the Duration
public class Track {

  private final String title;
  private final Duration Duration;

  public Track(String title, Duration Duration) {
    this.title = title;
    this.Duration = Duration;
  }

  public String toString() {
    return Duration + " - " + title;
  }
}
