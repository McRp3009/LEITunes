package domain.playlists;

import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import domain.core.MusicLibrary;
import domain.core.Rate;
import domain.core.Song;
import domain.core.SongAddedLibraryEvent;
import domain.core.SongMetaInfo;
import domain.core.SongRatedLibraryEvent;
import domain.core.SongRemovedLibraryEvent;
import domain.facade.ISong;

public class EvalMostRecentlyAddedSongsPlaylistTest {

	  private MusicLibrary library;
	    private MostRecentlyAddedSongsPlaylist playlist;
	    private ISong song;

	    @BeforeEach
		@Before
		public void setUp() {
			library = new MusicLibrary();
			SongMetaInfo info = new SongMetaInfo("Title","Album", "Genre", Arrays.asList("Author 1", "Author 2"));
			// Create a Song
			song = new Song("test_song.mp3",info);
			song.incTimesPlayed();
			playlist = new MostRecentlyAddedSongsPlaylist( library);
		}

		@Test
		public void testProcessEventSongRated() {
			SongRatedLibraryEvent event = null;
			 try {
		            Constructor<SongRatedLibraryEvent> constructor = SongRatedLibraryEvent.class.getDeclaredConstructor(ISong.class,MusicLibrary.class, Rate.class, Rate.class);
		            constructor.setAccessible(true);
		            event = constructor.newInstance(song,library, Rate.UNRATED, Rate.UNRATED);
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }

			playlist.processEvent(event);
			assertIterableEquals(Arrays.asList(), playlist);
		}
		
		
		@Test
		public void testProcessEventSongRemoved() {
			SongRemovedLibraryEvent event = null;
			 try {
		            Constructor<SongRemovedLibraryEvent> constructor = SongRemovedLibraryEvent.class.getDeclaredConstructor(ISong.class,MusicLibrary.class);
		            constructor.setAccessible(true);
		            event = constructor.newInstance(song,library);
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }
			playlist.processEvent(event);
			assertIterableEquals(Arrays.asList(), playlist);
		}

		@Test
		public void testProcessEventSongAdded() {
			SongAddedLibraryEvent event = null;
			 try {
		            Constructor<SongAddedLibraryEvent> constructor = SongAddedLibraryEvent.class.getDeclaredConstructor(ISong.class,MusicLibrary.class);
		            constructor.setAccessible(true);
		            event = constructor.newInstance(song,library);
		        } catch (Exception e) {
		            throw new RuntimeException(e);
		        }

			playlist.processEvent(event);
			assertIterableEquals(Arrays.asList(song), playlist);
		}

		@Test
		public void testProcessEventSongOther() {
			playlist.processEvent(null);
			assertIterableEquals(Arrays.asList(), playlist);
		}

}
