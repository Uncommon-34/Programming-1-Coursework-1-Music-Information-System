//store details of an album track
//store the name of the track (str) and the Duration

public class Track {

  //String title & Duration duration are stored
  private final String title;
  private final Duration duration;

  //Track constructor
  public Track(String title, Duration duration) {
    this.title = title;
    this.duration = duration;
  }

  //Acessor methods for Duration & title
  public Duration getDuration() {
    return duration;
  }

  public String getTitle() {
    return title;
  }

  //used too format Track's variables, and return a string value
  public String stringTrack() {
    return duration.stringDuration() + " - " + title;
  }

  //test harness
  public static void main(String[] args) {
    Track trackObject = new Track("HOLLOW HUNGER", new Duration(0, 3, 44)); //static definition, can change in the code, dont want too clutter w/ scanners
    System.out.println(
      "This is a test harness function for the Track class\n Example of stringTrack: " +
      trackObject.stringTrack() +
      "\nHere is the Duration & Title (getDuration & get Title):\n" +
      trackObject.getDuration().stringDuration() +
      " - " +
      trackObject.getTitle()
    );
  }
}
