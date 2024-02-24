package domain.core;

/**
 * @author André Reis fc58192
 * @author Martim Pereira fc58223
 *
 */
public enum Rate implements Comparable<Rate> {

	UNRATED("Unrated", 0),
	TERRIBLE("Terrible", 1),
	BAD("Bad", 2),
	OK("Ok", 3),
	GOOD("Good", 4),
	AMAZING("Amazing", 5);

	private final String label;
	private final int rating;

	/**
	 * Rate Constructor
	 *
	 * @param label
	 * @param rating
	 */
	private Rate(String label, int rating) {
		this.label = label;
		this.rating = rating;
	}

	/**
	 * Increases the rate by one. If the rate is already the highest remains
	 * unchanged
	 *
	 * @return the new rating
	 * @ensures \result.rating >= this.rating
	 */
	public Rate incRate() {
		switch (this.rating) {
			case 0:
				return TERRIBLE;
			case 1:
				return BAD;
			case 2:
				return OK;
			case 3:
				return GOOD;
			case 4:
				return AMAZING;
			default:
				return this;
		}
	}

	/**
	 * Decreases the rate by one. If the rate is already the lowest remains
	 * unchanged
	 *
	 * @return the new rating
	 * @ensures \result.rating <= this.rating
	 */
	public Rate decRate() {
		switch (this.rating) {
			case 0:
				return this;
			case 1:
				return this;
			case 2:
				return TERRIBLE;
			case 3:
				return BAD;
			case 4:
				return OK;
			default:
				return GOOD;
		}
	}

	/**
	 * Creates a String representation of Rate
	 *
	 * @return String representation of Rate
	 * @ensures \result != null
	 */
	@Override
	public String toString() {
		return this.label;
	}
}
