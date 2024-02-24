package util.adts;

import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import domain.core.Rate;
import domain.core.Song;
import domain.core.SongMetaInfo;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 * 
 * Tests for Song.java
 *
 */
public class SongTests {

	private Song song;
	private SongMetaInfo info;
	private Random rd;
	private static final String FILENAME = "filename";
	private static final String TITLE = "titulo";
	private static final String ALBUM = "album";
	private static final String GENRE = "genre";
	private static final List<String> ARTISTS = List.of("artist1", "artist2", "artist3");
	private static final int DEFAULT_TIMESPLAYED = 0;
	private static final Rate DEFAULT_RATE = Rate.UNRATED;

	/**
	 * Method that sets up the Song object to test before each test
	 */
	@BeforeEach
	void setUp() {
		this.info = new SongMetaInfo(TITLE, ALBUM, GENRE, ARTISTS);
		this.song = new Song(FILENAME, info);
		this.rd = new Random();
	}

	/**
	 * Tests the song constructor
	 */
	@Test
	public void constructorTest() {
		assertNotNull(song);
		assertEquals(song.getInfo(), info);
	}

	/**
	 * Tests if the method incTimesPlayed is working properly
	 */
	@Test
	public void incTimesPlayedTest() {
		int before = this.song.getTimesPlayed();
		int amount = this.rd.nextInt(50);
		for (int i = 0; i < amount; i++) {
			this.song.incTimesPlayed();
		}

		int after = this.song.getTimesPlayed();
		assertEquals((before + amount), after);
	}

	/**
	 * Tests if the method getTimesPlayed is working properly
	 */
	@Test
	public void getTimesPlayedTest() {
		int timesPlayed = this.song.getTimesPlayed();
		assertEquals(timesPlayed, DEFAULT_TIMESPLAYED);
	}

	/**
	 * Tests if the method getRating is working properly
	 */
	@Test
	public void getRatingTest() {
		Rate r = this.song.getRating();
		assertEquals(r, DEFAULT_RATE);
	}

	/**
	 * Tests if the method incRating is working properly
	 */
	@Test
	public void incRatingTest() {
		Rate before = song.getRating();
		song.incRating();
		Rate after = this.song.getRating();
		if (before == Rate.AMAZING) {
			assertEquals(before, after);
		} else {
			assertTrue(before.compareTo(after) < 0);
		}

	}

	/**
	 * Tests if the method decRating is working properly
	 */
	@Test
	public void decRatingTest() {
		Rate before = song.getRating();
		song.decRating();
		Rate after = song.getRating();
		if (before == Rate.UNRATED) {
			assertEquals(before, after);
		} else {
			assertTrue(after.compareTo(before) < 0);
		}

	}

	/**
	 * Tests if the method getSongTitle is working properly
	 */
	@Test
	public void getSongTitleTest() {
		String name = this.song.getSongTitle();
		assertEquals(name, TITLE);
	}

	/**
	 * Test for method the getGenre()
	 */
	@Test
	public void getGenreTest() {
		String genre = this.song.getGenre();
		assertEquals(genre, GENRE);
	}

	/**
	 * Test for method the getArtists()
	 */
	@Test
	public void getArtistsTest() {
		List<String> artists = this.song.getArtists();
		assertEquals(artists, ARTISTS);
	}

	/**
	 * Test for method the getAlbum()
	 */
	@Test
	public void getAlbumTest() {
		String album = this.song.getAlbum();
		assertEquals(album, ALBUM);
	}

	/**
	 * Test for method the getFilename()
	 */
	@Test
	public void getFilenameTest() {
		String filename = this.song.getFilename();
		assertEquals(filename, FILENAME);
	}

	/**
	 * Test for method the matches() using the title
	 */
	@Test
	public void matchesTitleTest() {
		assertTrue(this.song.matches(TITLE));
	}

	/**
	 * Test for method the matches() using the genre
	 */
	@Test
	public void matchesGenreTest() {
		assertTrue(this.song.matches(GENRE));
	}

	/**
	 * Test for method the matches() using each artists
	 */
	@Test
	public void matchesArtistsTest() {
		for (String s : ARTISTS) {
			assertTrue(this.song.matches(s));
		}
	}

	/**
	 * Test for method the matches() using the album
	 */
	@Test
	public void matchesAlbumTest() {
		assertTrue(this.song.matches(ALBUM));
	}

	/**
	 * Test for method the matches() using the filename
	 */
	@Test
	public void matchesTest() {
		assertFalse(this.song.matches(FILENAME));
	}

	/**
	 * Test for toString()
	 */
	@Test
	public void toStringTest() {

		String s = this.song.getInfo().toString() + " --- " + this.song.getRating().toString() + " --- "
				+ this.song.getTimesPlayed();
		assertEquals(this.song.toString(), s);

	}

	/**
	 * Test for equals
	 */
	@Test
	public void equalsTest() {

		Song s = new Song(FILENAME, info);

		assertTrue(song.equals(song));
		assertFalse(song.equals(null));
		assertFalse(song.equals(1));
		assertTrue(song.equals(s));
		assertTrue(s.equals(song));

		Song diffFilename = new Song("ola", info);
		assertFalse(song.equals(diffFilename));

		Song diffTimesPlayed = new Song(FILENAME, info);
		diffTimesPlayed.incTimesPlayed();
		assertFalse(song.equals(diffTimesPlayed));
		Song diffRating = new Song(FILENAME, info);
		diffRating.incRating();
		assertFalse(song.equals(diffRating));
		SongMetaInfo info2 = new SongMetaInfo("ola", ALBUM, GENRE, ARTISTS);
		Song diffInfo = new Song(FILENAME, info2);
		assertFalse(song.equals(diffInfo));

	}

	/**
	 * Test for getInfo()
	 */
	@Test
	public void getInfoTest() {
		assertEquals(this.song.getInfo(), this.info);
	}
}
