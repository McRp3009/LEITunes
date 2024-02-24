package servicos;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.mpatric.mp3agic.ID3v1;
import com.mpatric.mp3agic.ID3v2;
import com.mpatric.mp3agic.InvalidDataException;
import com.mpatric.mp3agic.Mp3File;
import com.mpatric.mp3agic.UnsupportedTagException;

import domain.core.Song;
import domain.core.SongMetaInfo;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Objects of this type represent creators for Songs
 */
public class SongCreator {

	/**
	 * Create a Song object using the wanted song's mp3 file if possible
	 * @param filename the name of the mp3 file
	 * @return a Song object based on the mp3 file given, if possible
	 */
    public Song create(String filename) {

		Song song = null;
        try {
			Mp3File mp3file = new Mp3File(filename);
			if(mp3file.hasId3v1Tag()) {
				ID3v1 id = mp3file.getId3v1Tag();
				String a = id.getArtist();
				List<String> artists = a == null ? Arrays.asList("unknown") : getArtists(a);
				String title = id.getTitle() == null ? "unknown" :  id.getTitle();
				String album = id.getAlbum() == null ? "unknown" : id.getAlbum();
				String genre = id.getGenreDescription() == null ? "unknown" : id.getGenreDescription();
				SongMetaInfo info = new SongMetaInfo(title, album, genre, artists);
				song = new Song(filename, info);
			} else if(mp3file.hasId3v2Tag()) {
				ID3v2 id = mp3file.getId3v2Tag();
				String a = id.getArtist();
				List<String> artists = a == null ? Arrays.asList("unknown") : getArtists(a);
				String title = id.getTitle() == null ? "unknown" :  id.getTitle();
				String album = id.getAlbum() == null ? "unknown" : id.getAlbum();
				String genre = id.getGenreDescription() == null ? "unknown" : id.getGenreDescription();
				SongMetaInfo info = new SongMetaInfo(title, album, genre, artists);
				song = new Song(filename, info);

			} else {
                System.out.println("Impossibel to load song from " + filename);
            }

		} catch (UnsupportedTagException | InvalidDataException | IOException e) {
			e.printStackTrace();
		}

		return song;
        
    }

	/**
	 * Creates a List of artists based on a string with all the artists
	 * @param artists the string of artists
	 * 
	 * @requires the artists in the original string to be divided by ";"
	 * @return the list of individual artists
	 */
    private List<String> getArtists(String artists) {
		String[] art = artists.split(";");
		return Arrays.asList(art);
	}

}
