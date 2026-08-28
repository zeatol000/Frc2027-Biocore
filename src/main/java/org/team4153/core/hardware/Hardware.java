package org.team4153.core.hardware;

/** The [[java.lang.Object]] of hardware
 */
public interface Hardware {
 
	/** Not all hardware objects use the CAN system, but all hardware objects
	 * need a fast way of figuring out what is what. This is one of the many
	 * uses of an ID that makes it so much better than comparing strings.
	 *
	 * Additionally, these create distinction from CAN ids, which can change
	 * often and will need to be manually changed in the code every mutation.
	 */
	public abstract int ID();

	/** Literally just a debug utility to figure out what is going wrong.
	 * Only used in reporting.
	 */
	public abstract String NAME();



	/** All hardware. Please don't touch this */
	public static final Hardware[] hardware = new Hardware[0x100];
}
