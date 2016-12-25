package com.miguel.tracker;

import lejos.hardware.lcd.LCD;
import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;
import lejos.hardware.port.SensorPort;
import lejos.hardware.sensor.EV3TouchSensor;
import lejos.hardware.sensor.SensorMode;
import lejos.robotics.navigation.DifferentialPilot;
import lejos.robotics.navigation.MoveController;

public class Grabber {

	private static final int TOUCHED = 1;

	public static void main(String[] args) {
		EV3LargeRegulatedMotor LEFT_MOTOR = new EV3LargeRegulatedMotor(
				MotorPort.B);
		EV3LargeRegulatedMotor RIGHT_MOTOR = new EV3LargeRegulatedMotor(
				MotorPort.A);
		EV3MediumRegulatedMotor GRABBER_MOTOR = new EV3MediumRegulatedMotor(
				MotorPort.C);
		EV3TouchSensor TOUCH_SENSOR = new EV3TouchSensor(SensorPort.S4);

		MoveController pilot = new DifferentialPilot(2.25f, 5.5f, LEFT_MOTOR,
				RIGHT_MOTOR);

		pilot.setTravelSpeed(20d);
		pilot.forward();

		SensorMode touch = TOUCH_SENSOR.getTouchMode();
		float[] sample = new float[touch.sampleSize()];

		boolean stop = false;

		while (pilot.isMoving() && !stop) {
			touch.fetchSample(sample, 0);
			LCD.drawString(sample[0] + "", 0, 0);

			if (sample[0] == TOUCHED) {
				stop = true;
				LCD.drawString(sample[0] + "", 0, 0);
				pilot.stop();

				GRABBER_MOTOR.rotate(1800);
				pilot.travel(-20);

				LEFT_MOTOR.rotate(-360);
				pilot.travel(20);
				GRABBER_MOTOR.rotate(-1800);

				pilot.travel(-20);
				RIGHT_MOTOR.rotate(-360);

				pilot.forward();

			}
		}

		LEFT_MOTOR.close();
		RIGHT_MOTOR.close();
		GRABBER_MOTOR.close();
		TOUCH_SENSOR.close();

	}

}
