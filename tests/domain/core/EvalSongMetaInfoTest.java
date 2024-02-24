package domain.core;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

class EvalSongMetaInfoTest {

//	@Test
//	void testConstructorWithNullFields() {
//		SongMetaInfo smi = new SongMetaInfo(null, null, null, null);
//		assertEquals("unkown", smi.songTitle());
//		assertEquals("unkown", smi.genre());
//		assertEquals("unkown", smi.album());
//		assertEquals(Arrays.asList("unkown"), smi.authors());
//	}
	
	@Test
	void testMatches() {
		SongMetaInfo smi = new SongMetaInfo("Title","Album", "Genre", Arrays.asList("Author 1", "Author 2"));
		assertTrue(smi.matches("Title"));
		assertTrue(smi.matches("Genre"));
		assertTrue(smi.matches("Album"));
		assertTrue(smi.matches("Author 1"));
		assertTrue(smi.matches("Author 2"));
		assertFalse(smi.matches("Unknown"));
	}
	
	@Test
	void testToString() {
		SongMetaInfo smi = new SongMetaInfo("Song Title","Album", "Genre", Arrays.asList("Author 1", "Author 2"));
		assertTrue( smi.toString().contains("Song Title"));
	}

}

