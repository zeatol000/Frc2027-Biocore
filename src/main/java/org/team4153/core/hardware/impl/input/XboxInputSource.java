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

	public boolean b() {
		return controller.getBButton();
	}

	public boolean x() {
		return controller.getXboxButton();
	}

	public boolean y() {
		return controller.getYButton();
	}

	public boolean dpadDown() {
		return controller.getDpadDownButton();
	}

	public boolean dpadLeft() {
		return controller.getDpadLeftButton();
	}

	public boolean dpadRight() {
		return controller.getDpadRightButton();
	}

	public boolean dpadUp() {
		return controller.getDpadUpButton();
	}

	public boolean lBumper() {
		return controller.getLeftBumperButton();
	}

	public boolean lStick() {
		return controller.getLeftStickButton();
	}

	public boolean rBumper() {
		return controller.getRightBumperButton();
	}

	public boolean rStick() {
		return controller.getRightStickButton();
	}

	public boolean menu() {
		return controller.getMenuButton();
	}

	public boolean view() {
		return controller.getViewButton();
	}

	public boolean xbox() {
		return controller.getXboxButton();
	}

	public double lTrigger() {
		return controller.getLeftTrigger();
	}

	public double lX() {
		return controller.getLeftX();
	}

	public double lY() {
		return controller.getLeftY();
	}

	public double rTrigger() {
		return controller.getRightTrigger();
	}

	public double rX() {
		return controller.getRightX();
	}

	public double rY() {
		return controller.getRightY();
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
