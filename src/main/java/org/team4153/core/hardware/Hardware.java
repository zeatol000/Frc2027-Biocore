package org.team4153.core.hardware;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;

import org.team4153.core.config.Conf;
import org.team4153.core.config.Prelog;
import org.team4153.core.hardware.Motor;

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
	int ID();

	/** Literally just a debug utility to figure out what is going wrong.
	 * Only used in reporting.
	 */
	String NAME();



	/** All hardware by ids */
	Hardware[] hardware = new Hardware[Conf.hardwareArraySize];


	static void unsafeMakeHardware(Config cfg) {
		int id = cfg.getInt("id");

    if (id < 0 || id >= hardware.length) {
      String msg = "Hardware id goes out of bounds: " +
        id +
        ", where the upper bound is " +
        hardware.length +
        "\nCannot continue execution!!";

      Prelog.error(msg);
      throw new ConfigException.Generic(msg);
    }

		if (hardware[id] != null) {
			String msg = "Conflicting hardware IDs: "+id+"\nCannot continue execution!!";
			Prelog.error(msg);
			throw new ConfigException.Generic(msg);
		}

		String type = cfg.getString("type").toLowerCase();

		Hardware el = switch (type) {
			case "motor" -> Motor.unsafeMakeMotor(cfg);
			case "controller" -> Controller.unsafeMakeController(cfg);
			default -> {
				String msg = "Unknown hardware type: "+type;
				Prelog.error(msg);
				throw new ConfigException.BadValue("type", msg);
			}
		};

		hardware[id] = el;
	}
}
