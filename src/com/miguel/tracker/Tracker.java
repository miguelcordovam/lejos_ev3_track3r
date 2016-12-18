package com.miguel.tracker;

import lejos.hardware.motor.Motor;
import lejos.robotics.RegulatedMotor;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;

public class Tracker {

	public static void main(String[] args) {

		RegulatedMotor motor = Motor.A;
		motor.rotate(1800, true);
		
		MoveController pilot = new DifferentialPilot(2.25f, 5.5f, Motor.C,
				Motor.B);
		pilot.setTravelSpeed(20d);
		pilot.travel(-20);
		
		
	}

}
