package domain.core;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertIterableEquals;
import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import domain.facade.ISong;

class EvalMusicLibraryTest {
	
	MusicLibrary musicLibrary;
	ISong song1;
	ISong song2;

	@BeforeEach
	public void setUp() throws Exception {
		musicLibrary = new MusicLibrary();
		SongMetaInfo info = new SongMetaInfo("Test song", "Test album","Pop", Arrays.asList("Test artist"));
		// Create a Song
		song1 = new Song("test_song.mp3", info);
		SongMetaInfo info2 = new SongMetaInfo("Test song 2", "Rock","Test album 2", Arrays.asList("Test artist2"));
		// Create a Song
		song2 = new Song("test_song.mp3",info2);
		
	}

	@Test
	public void testSize() {
		assertEquals(0,musicLibrary.size());
		musicLibrary.add((Song)song2);
		assertEquals(1,musicLibrary.size());
	}
	
	@Test
	public void testSelect() {
		musicLibrary.add((Song)song1);
		musicLibrary.add((Song)song2);
		musicLibrary.select(1);
		assertEquals(1,musicLibrary.getIndexSelected());
		assertEquals(song2,musicLibrary.getSelected());
		assertTrue(musicLibrary.someSelected());

	}
	
	@Test
	public void testGetSelected() {
		musicLibrary.add((Song)song1);
		musicLibrary.add((Song)song2);
		musicLibrary.select(1);
		assertEquals(1,musicLibrary.getIndexSelected());
		assertEquals(song2,musicLibrary.getSelected());
		assertTrue(musicLibrary.someSelected());
	}
	
	@Test
	public void testSomeSelected() {
		musicLibrary.add((Song)song1);
		musicLibrary.add((Song)song2);
		musicLibrary.select(1);
		assertEquals(1,musicLibrary.getIndexSelected());
		assertEquals(song2,musicLibrary.getSelected());
		assertTrue(musicLibrary.someSelected());
	}
	
	@Test
	public void testAdd() {
		musicLibrary.add((Song)song1);
		assertIterableEquals(Arrays.asList(song1), musicLibrary);
	}

	@Test
	public void testGetSongs() {
		assertIterableEquals(Arrays.asList(), musicLibrary);

		
		musicLibrary.add((Song)song1);
		musicLibrary.add((Song)song2);
		assertIterableEquals(Arrays.asList(song1,song2), musicLibrary);
	}

	@Test
	public void testGetMatches() {
		musicLibrary.add((Song)song2);
		assertEquals(Arrays.asList(song2), musicLibrary.getMatches("Rock"));
		assertEquals(Arrays.asList(), musicLibrary.getMatches("fado"));
	}

	



}
