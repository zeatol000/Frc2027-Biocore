package org.team4153.core.hardware;

/** This piece of hardware is an output.
 *
 * Requires the implementing class to be Hardware
 */
public interface Output<Self extends Output<Self> & Hardware> {}
