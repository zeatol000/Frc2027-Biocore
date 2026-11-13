package org.team4153.core.hardware;

import com.typesafe.config.Config;
import com.typesafe.config.ConfigException;

import org.team4153.core.config.Prelog;
import org.team4153.core.hardware.impl.input.XboxInputSource;
import org.team4153.core.util.SDLController;

/** Controller inputs such as an XBox or PS3 controller. */
public interface Controller
	extends
		Hardware,
		Input<Controller>,
		SDLController
{
	
	/** The port of which this controller resides */
	int PORT();

	/*static Controller getBy(byte canId) {
		if (CAN.elements[canId] instanceof Controller c)
			return c;

		else
			return null;
	}*/


	/** Internal method for assembling motors by config */
	static Controller unsafeMakeController(Config self) {
		String cls = self.getString("class").toLowerCase();

		return switch (cls) {
			case "xbox" -> new XboxInputSource(self);
			default -> {
				String msg = "Unknown controller type: "+cls+"\nCannot continue execution";
				Prelog.error(msg);
				throw new ConfigException.Generic(msg);
			}
		};

		//return el;
	}
}
