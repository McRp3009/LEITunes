package domain.playlists;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;

import java.util.Arrays;
import java.util.Objects;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import domain.core.MusicLibrary;
import domain.core.Song;
import domain.core.SongMetaInfo;
import domain.facade.ISong;

public class EvalManualPlaylistTest {

	private MusicLibrary lib;
	private ManualPlaylist playlist;
	private Song song1, song2, song3;

	@Before
	@BeforeEach
	public void setUp() throws Exception {
		// create a music library and add some songs
		lib = new MusicLibrary();
		// Create a mock MusicLibrary
		SongMetaInfo info = new SongMetaInfo("Test song","Test album", "Pop", Arrays.asList("Test artist"));
		// Create a Song
		song1 = new Song("test_song.mp3",info);
		SongMetaInfo info2 = new SongMetaInfo("Test song 2","Test album 2", "Rock", Arrays.asList("Test artist2"));
		// Create a Song
		song2 = new Song( "test_song.mp3", info2);
		SongMetaInfo info3 = new SongMetaInfo("Test song 3", "Test album3","Hip Hop", Arrays.asList("Test artist3"));
		// Create a Song
		song3 = new Song("test_song.mp3",info3);
		lib.add(song1);
		lib.add(song2);
		lib.add(song3);

		// create a playlist and add the songs
		playlist = new ManualPlaylist("test", lib);
	
	}

	@Test
	public void testGetName() {
		assertEquals("test", playlist.getName());
	}

	@Test
	public void testAdd() {
		assertTrue(playlist.add(song1));
		assertTrue(playlist.add(song2));
		assertFalse(playlist.add(song1));
		assertFalse(playlist.add(song2));

		assertEquals(2, playlist.size());
	}

	@Test
	public void testRemove() {
		playlist.add(song1);
		playlist.add(song2);
		playlist.select(0);
		assertTrue(playlist.remove());
		assertFalse(playlist.someSelected());
		assertEquals(1, playlist.size());
		assertEquals(song2, playlist.iterator().next());
	}

	@Test
	public void testMoveSelected() {
		playlist.add(song1);
		playlist.add(song2);
		playlist.select(0);
		assertTrue(playlist.moveUpSelected(1));
		assertEquals(song2, playlist.getSelected());
	}

	@Test
	public void testIterator() {
		playlist.add(song1);
		playlist.add(song2);

		assertIterableEquals(Arrays.asList(song1, song2), playlist);
	}

	@Test
	public void testSize() {
		assertEquals(0, playlist.size());

		playlist.add(song1);
		assertEquals(1, playlist.size());

		playlist.add(song2);
		assertEquals(2, playlist.size());

		playlist.remove();
		assertEquals(1, playlist.size());

	}

	@Test
	public void testSelect() {
		assertFalse(playlist.someSelected());

		playlist.add(song1);
		playlist.select(0);
		assertTrue(playlist.someSelected());
		assertEquals(song1, playlist.getSelected());

		playlist.add(song2);
		playlist.select(1);
		assertTrue(playlist.someSelected());
		assertEquals(song2, playlist.getSelected());
	}

	@Test
	public void testGetIndexSelected() {
		assertEquals(-1, playlist.getIndexSelected());

		playlist.add(song1);
		playlist.select(0);
		assertEquals(0, playlist.getIndexSelected());

		playlist.add(song2);
		playlist.select(1);
		assertEquals(1, playlist.getIndexSelected());
	}





	@Test
	public void testGetSelected() {
		playlist.add(song1);
		playlist.add(song2);
		playlist.add(song3);
		// initially nothing is selected
		assertFalse(playlist.someSelected());
		// assertNull(playlist.getSelected());
		
		// select the second song
		playlist.select(1);
		assertTrue(playlist.someSelected());
		assertEquals(song2, playlist.getSelected());

		// select the first song
		playlist.select(0);
		assertTrue(playlist.someSelected());
		assertEquals(song1, playlist.getSelected());

		// deselect the selected song
		playlist.select(-1);
		assertFalse(playlist.someSelected());
		// assertNull(playlist.getSelected());
	}

	

	@Test
	public void testPrevious() {
		// starting at the first song should not be able to go previous
		assertFalse(playlist.someSelected());
		playlist.previous();
		assertFalse(playlist.someSelected());

		// selecting a song and going previous should select the previous song
		playlist.select(1);
		playlist.previous();
		// assertEquals(songs.get(0), playlist.getSelected());

		// going previous again should do nothing
		playlist.previous();
		// assertEquals(songs.get(0), playlist.getSelected());
	}



	@Test
	public void testNext() {
		
		playlist.add(song1);
		playlist.add(song2);
		playlist.select(0);

		
		assertEquals(song1, playlist.getSelected());
		playlist.next();
		assertEquals(song2, playlist.getSelected());
	}

	
}
