package domain.core;

import java.util.List;
import java.util.regex.Pattern;

import util.adts.RegExpMatchable;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 *         Class that represents the info of a song
 *
 */
public record SongMetaInfo(String titulo, String album, String genero, List<String> artistas)
		implements RegExpMatchable {

	/**
	 * Method that checks if a given string matches any of
	 * the class parammeters
	 *
	 * @requires regexp != null
	 * @return if regexp matches any of the parameters
	 * @ensures \result == true || \result == false
	 */
	@Override
	public boolean matches(String regexp) {
		Pattern pat = Pattern.compile(regexp);
		boolean matches;
		matches = (pat.matcher(titulo).matches())
				|| (pat.matcher(genero).matches())
				|| (pat.matcher(album).matches());

		for (String artista : artistas) {
			matches = matches || (pat.matcher(artista).matches());
		}

		return matches;

	}

	/**
	 * Creates a String representation of SongMetaInfo
	 * @return String representing SongMetaInfo
	 * @ensures \result != null
	 */
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();

		sb.append("[");
		sb.append(this.titulo + ", ");
		sb.append(this.album + ", ");
		sb.append(this.genero + ", ");
		sb.append(this.artistas + "]");

		return sb.toString();
	}

}
