package org.team4153.core.collection;

/** An Option forces null handling. An Option<T> is either a Some<T> or None */
public class Options {
	public static sealed class Option<T>
		permits None_c, Some
	{
		public static <T> Option<T> of(T value) {
			if (value == null) return None;
			else return new Some<T>(value);
		}
	}


	/* PLEASE SWITCH TO A VALUE CLASS IN JDK 28 */
	public static final class Some<T> extends Option<T> {
		public final T value;

		public Some(T value) {
			this.value = value;
		}
	}



	// None
	static { None = new None_c(); }

	public static final None_c None;

	public static final class None_c extends Option {
		None_c() {}

		@Override
		public boolean equals(Object that) {
			return this == that;
		}
	}
}
