package org.team4153.core.hardware.impl.input;

import com.typesafe.config.Config;

import org.wpilib.driverstation.XboxController;

import org.team4153.core.hardware.Controller;
import org.team4153.core.mechanisms.input.InputSource;

public class XboxInputSource extends InputSource implements Controller {

	public final int id;
	public final int port;
	public final String name;
	public final XboxController controller;


	public XboxInputSource(Config config) {
		this.id = config.getInt("id");
		this.port = config.getInt("port");
		this.name = config.getString("name");

		this.controller = new XboxController(port);
	}

	public XboxInputSource(int id, String name, int port) {
		this.id = id;
		this.name = name;
		this.port = port;

		this.controller = new XboxController(port);
	}

	protected XboxInputSource() {
		id = -1;
		port = -1;
		name = "";
		controller = null;
	}


	public boolean a() {
		return controller.getAButton();
	}
	public boolean south() {
		return controller.getAButton();
	}

	public boolean b() {
		return controller.getBButton();
	}
	public boolean east() {
		return controller.getBButton();
	}

	public boolean x() {
		return controller.getXboxButton();
	}
	public boolean west() {
		return controller.getXButton();
	}

	public boolean y() {
		return controller.getYButton();
	}
	public boolean north() {
		return controller.getYButton();
	}


	public boolean dPadDown() {
		return controller.getDpadDownButton();
	}

	public boolean dPadLeft() {
		return controller.getDpadLeftButton();
	}

	public boolean dPadRight() {
		return controller.getDpadRightButton();
	}

	public boolean dPadUp() {
		return controller.getDpadUpButton();
	}


	public boolean leftBumper() {
		return controller.getLeftBumperButton();
	}

	public boolean rightBumper() {
		return controller.getRightBumperButton();
	}


	public boolean leftStick() {
		return controller.getLeftStickButton();
	}

	public boolean rightStick() {
		return controller.getRightStickButton();
	}


	public boolean menu() {
		return controller.getMenuButton();
	}
	public boolean back() {
		return controller.getMenuButton();
	}

	public boolean view() {
		return controller.getViewButton();
	}
	public boolean start() {
		return controller.getViewButton();
	}

	public boolean xbox() {
		return controller.getXboxButton();
	}
	public boolean guide() {
		return controller.getXboxButton();
	}


	public double leftX() {
		return controller.getLeftX();
	}

	public double leftY() {
		return controller.getLeftY();
	}

	public double rightX() {
		return controller.getRightX();
	}

	public double rightY() {
		return controller.getRightY();
	}


	public double leftTrigger() {
		return controller.getLeftTrigger();
	}

	public double rightTrigger() {
		return controller.getRightTrigger();
	}




	public int ID() {
		return id;
	}

	public String NAME() {
		return name;
	}

	public int PORT() {
		return port;
	}
}
