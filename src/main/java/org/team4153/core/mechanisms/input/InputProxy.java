package org.team4153.core.mechanisms.input;

/** This class provides a mutable wrapper over input sources for easily
 * switching the actual input source. For example, this can be used to keep a
 * distinction from controller inputs and auto recording playback.
 *
 * Basically this is just a wrapper over the volatile field 'src'.
 * However, if you are experiencing race conditions and want to make sure that
 * the field gets properly mutated, use the get and set methods, which are
 * marked synchronized.
 */
public class InputProxy<T extends InputSource> {
	public volatile T src;

	public InputProxy(T active) {
		this.src = active;
	}


	public synchronized T get() {
		return this.src;
	}

	public synchronized void set(T active) {
		this.src = active;
	}
}
