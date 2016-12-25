package com.miguel.tracker;

import lejos.hardware.motor.EV3LargeRegulatedMotor;
import lejos.hardware.motor.EV3MediumRegulatedMotor;
import lejos.hardware.port.MotorPort;

public class Shooting {

    public static void main(String[] args) {

        EV3LargeRegulatedMotor RIGHT_MOTOR = new EV3LargeRegulatedMotor(
                MotorPort.A);

        RIGHT_MOTOR.setSpeed(360);

        for (int i = 0; i < 3; i++) {
            RIGHT_MOTOR.rotate(i % 2 == 0 ? 360 : -360);
            shootBall();
        }

        RIGHT_MOTOR.close();
    }

    private static void shootBall() {
        EV3MediumRegulatedMotor SHOOTER = new EV3MediumRegulatedMotor(
                MotorPort.C);
        SHOOTER.rotate(1200);
        SHOOTER.close();
    }
}
