//store duration of a Track, Album or AlbumCOllection
//Needs too store Hours, Minuets, Seconds
//Must impliment Comparable interface?

public class Duration {

  private final int hours, minutes, seconds;

  public Duration(int hours, int minutes, int seconds) {
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  }

  public int toSeconds() {
    return hours * 3600 + minutes * 60 + seconds;
  }

  public String toString() {
    return ((hours < 10 ? "0" : "") +
        hours +
        ":" +
        (minutes < 10 ? "0" : "") +
        minutes +
        ":" +
        (seconds < 10 ? "0" : "") +
        seconds);
  }
}
