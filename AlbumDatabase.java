import java.io.*;
import java.util.*;

class trackLength {
    private final int hours, minutes, seconds;

    public trackLength(int hours, int minutes, int seconds) {
        this.hours = hours;
        this.minutes = minutes;
        this.seconds = seconds;
    }

    public String toString() {
        return (hours < 10 ? "0" : "") + hours + ":" +
                (minutes < 10 ? "0" : "") + minutes + ":" +
                (seconds < 10 ? "0" : "") + seconds;
    }

}

class Track {
    private final String title;
    private final trackLength trackLength;

    public Track(String title, trackLength trackLength) {
        this.title = title;
        this.trackLength = trackLength;
    }

    public String toString() {
        return trackLength + " - " + title;
    }
}

class Album {
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

    public String albumtitle() {
        StringBuilder sb = new StringBuilder();
        sb.append(artist).append(" : ").append(title).append(" (").append(year).append(")");
        return sb.toString();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(artist).append(" : ").append(title).append(" (").append(year).append(")\n");
        tracks.forEach(track -> sb.append("  ").append(track).append("\n"));
        return sb.toString();
    }
}

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
                        currentAlbum.addTrack(new Track(title, new trackLength(hours, minutes, seconds)));
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

        // get a specific song and its duration
        String Song = "On the Run";
        for (Album album : albums) {
            for (Track track : album.getTracks()) {
                if (track.toString().contains(Song)) {
                    System.out.println("Found Song: " + Song + " | it was in the album: " + album.albumtitle());
                    System.out.println("Track Length: " + track.toString());
                }
            }
        }
    }
}