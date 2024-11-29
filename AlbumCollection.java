//store details of a colluctoin of almbums
//member variable of Albums
//methods for awnsering general queries

import java.util.ArrayList;
import java.util.List;

public class AlbumCollection {

  private final String artist;
  private final List<Album> albums = new ArrayList<>();

  public AlbumCollection(String artist) {
    this.artist = artist;
  }

  public void addAlbum(Album album) {
    albums.add(album);
  }

  public String getArtist() {
    return artist;
  }

  public List<Album> getAlbums() {
    return albums;
  }

  //test harness
  public static void main(String[] args) {
    Album albumObjectOne = new Album(
      "Kraftwerk",
      "COMPUTER WORLD",
      1981
    );
    Album albumObjectTwo = new Album(
      "Kraftwerk",
      "DIE MENSCH-MASCHINE",
      1978
    );

    //no need to add tracks as no track methords to test

    AlbumCollection albumCollectionObject = new AlbumCollection("Kraftwerk"); // artist name is the title of the collection
    albumCollectionObject.addAlbum(albumObjectOne);
    albumCollectionObject.addAlbum(albumObjectTwo);

    System.out.println(
      "This is a test harness function for the AlbumCollection class\nExample of getArtist:\n" +
      albumCollectionObject.getArtist() +
      "\n\nExample of getAlbums:\n" +
      albumCollectionObject.getAlbums() +
      "\n\nputting them albums to strings:"
    );

    List<Album> Albums = albumCollectionObject.getAlbums();
        for (Album album : Albums) {
          System.out.println(album.stringAlbumInfo());
        }
  }
}

