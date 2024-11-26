//store duration of a Track, Album or AlbumCOllection
//Needs too store Hours, Minuets, Seconds
//Must impliment Comparable interface?

//Duration class used too store & manipulate time values

import java.util.ArrayList;
import java.util.Collections; //From my online reaserch for impliments Comparable this is the module that defines it

public class Duration implements Comparable<Duration> { //standardised for clases too be upper camlecase, methods lower camlecase

  //Duration is made of three int values,
  private final int hours; //defined like this and not on one line for clarity as is standard in industry
  private final int minutes;
  private final int seconds;

  //Duration constuctor
  public Duration(int hours, int minutes, int seconds) {
    this.hours = hours;
    this.minutes = minutes;
    this.seconds = seconds;
  } //end of constructor

  public String stringDuration() { //used too add 0's too the display of Duration, and return a string value
    return (
      (hours < 10 ? "0" : "") +
      hours +
      ":" +
      (minutes < 10 ? "0" : "") +
      minutes +
      ":" +
      (seconds < 10 ? "0" : "") +
      seconds
    );
  }

  //accessor (& arithmatic) method that returns the total duration in seconds, hence why length is used as our semantic diffirence
  public int getDurationLength() {
    return hours * 3600 + minutes * 60 + seconds;
  }

  public int compareTo(Duration that) {
    if (this.getDurationLength() == that.getDurationLength()) {
      return 0;
    } else if (this.getDurationLength() > that.getDurationLength()) {
      return 1;
    } else {
      return -1;
    }
  }

  //test harness
  public static void main(String[] args) {
    Duration durationObjectOne = new Duration(0, 3, 44); //static definition, can change in the code, dont want too clutter w/ scanners
    Duration durationObjectTwo = new Duration(0, 2, 14);

    //creating an array list for this test harness too allow for demonstration of compable
    ArrayList<Duration> testArray = new ArrayList<Duration>();
    testArray.add(durationObjectOne);
    testArray.add(durationObjectTwo);
    //demonstration of Comparable || This array looks like [durationObjectOne, durationObjectTwo]
    Collections.sort(testArray); // The array now looks like [durationObjectTwo, durationObjectOne] || since its been sorted

    System.out.println(
      "This is a test harness function for the duration class\nExample of stringDuration: \n" +
      durationObjectOne.stringDuration() +
      "\nExample of totalSeconds Method: \n" +
      durationObjectOne.getDurationLength() +
      "\nExample of the implimented Compare (compareTo & sort (low to high, in this case flipping the array of Durations)):"
    );
    for (Duration iDuration : testArray) {
      System.out.println(iDuration.stringDuration());
    }
  }
} // end of Duration class
