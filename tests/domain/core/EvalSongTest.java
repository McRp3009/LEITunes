package domain.core;

import static org.junit.Assert.*;

import java.lang.reflect.Constructor;
import java.util.Arrays;

import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import domain.facade.ISong;

public class EvalSongTest {
    
	ISong song;

	@Before @BeforeEach
	public void setUp() {
		 // Create a mock MusicLibrary
    	SongMetaInfo info = new SongMetaInfo("Test song","Test album", "Pop", Arrays.asList("Test artist"));

        // Create a Song
        song = new Song("test_song.mp3", info);
	}
 

    @Test
    public void testGetSongTitle() {
    	SongMetaInfo info = new SongMetaInfo("Test song", "Test album","Pop", Arrays.asList("Test artist"));
        assertEquals("Test song", song.getSongTitle());
    }

    @Test
    public void testGetGenre() {
        assertEquals("Pop", song.getGenre());
    }

    @Test
    public void testGetArtists() {
        assertEquals(Arrays.asList("Test artist"), song.getArtists());
    }

    @Test
    public void testGetAlbum() {
        assertEquals("Test album", song.getAlbum());
    }

    @Test
    public void testGetFilename() {
        assertEquals("test_song.mp3", song.getFilename());
    }

    @Test
    public void testIncTimesPlayed() {
        song.incTimesPlayed();
        assertEquals(1, song.getTimesPlayed());
    }

    @Test
    public void testGetTimesPlayed() {
        song.incTimesPlayed();
        assertEquals(1, song.getTimesPlayed());
    }

   

    @Test
    public void testMatches() {
        assertTrue(song.matches(".*song.*"));
        assertTrue(song.matches(".*Test.*"));
        assertFalse(song.matches(".*Jazz.*"));
    }
    
    @Test
   	public void testIncRating() {
   		// Check that rating is 0 initially
   		assertEquals(0, song.getRating().compareTo(createRate(0)));
   		
   		// Increment the rating and check that it has increased by 1
   		song.incRating();
   		assertEquals(0, song.getRating().compareTo(createRate(1)));
   		
   		// Increment the rating to the maximum and check that it doesn't increase anymore
   		for (int i = 1; i <= 5; i++) {
   			song.incRating();
   		}
   		assertEquals(0, song.getRating().compareTo(createRate(5)));
   	}

   	@Test
   	public void testDecRating() {
   		// Check that rating is 0 initially
   		assertEquals(0, song.getRating().compareTo(createRate(0)));
   		
   		// Decrement the rating and check that it has decreased by 1
   		song.decRating();
   		assertEquals(0, song.getRating().compareTo(createRate(0)));
   		
   		// Decrement the rating to the minimum and check that it doesn't decrease anymore
   		for (int i = 1; i <= 5; i++) {
   			song.decRating();
   		}
   		assertEquals(0, song.getRating().compareTo(createRate(0)));
   	}

   	@Test
   	public void testGetRating() {
   		// Check that rating is 0 initially
   		assertEquals(0, song.getRating().compareTo(createRate(0)));
   		
   		// Increment the rating twice and check that it is equal to 2
   		song.incRating();
   		song.incRating();
   		assertEquals(0, song.getRating().compareTo(createRate(2)));
   	}
    
    private Rate createRate(int n) {
        try {
            Constructor<Rate> constructor = Rate.class.getDeclaredConstructor(int.class);
            constructor.setAccessible(true);
            return constructor.newInstance(n);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    
}
