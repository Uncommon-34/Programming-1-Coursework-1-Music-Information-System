//store details of a colluctoin of almbums
//member variable of Albums
//methods for awnsering general queries

import java.util.ArrayList;
import java.util.List;

public class AlbumCollection {

  //An AlbumCollection object is composed of a list of albums and the artist (this is doubled up with Album and thus we could remove one too optimise)
  private final String artist;
  private final List<Album> albums = new ArrayList<>();

  //Constructor
  public AlbumCollection(String artist) {
    this.artist = artist;
  }

  //Method too add an Album too the AlbumCollection's 'albums' List
  public void addAlbum(Album album) {
    albums.add(album);
  }

  //Acessor methods
  public String getArtist() {
    return artist;
  }

  public List<Album> getAlbums() {
    return albums;
  }

  //test harness
  public static void main(String[] args) {
    Album albumObjectOne = new Album("Kraftwerk", "COMPUTER WORLD", 1981);
    Album albumObjectTwo = new Album("Kraftwerk", "DIE MENSCH-MASCHINE", 1978);

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
