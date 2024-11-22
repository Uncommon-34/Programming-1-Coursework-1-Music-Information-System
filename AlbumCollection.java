import java.util.*;

public class AlbumCollection {
  //store details of a colluctoin of almbums
  //member variable of Albums
  //methods for awnsering general queries


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

  public String getAlbumCollectionNameString() {
    return artist;
  }

}



