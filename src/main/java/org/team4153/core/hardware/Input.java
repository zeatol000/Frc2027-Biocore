package org.team4153.core.hardware;

/** This piece of hardware is an input instead of an output
 *
 * Requires the implementing class to be Hardware
 */
public interface Input<Self extends Input<Self> & Hardware> {}
