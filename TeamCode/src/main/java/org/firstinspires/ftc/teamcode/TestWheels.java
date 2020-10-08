
package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

@TeleOp(name = "TestWheels")

public class TestWheels extends OpMode {
    DcMotor motor;
    Servo servo;
    Wheels wheels;
    @Override
    public void init() {
        wheels = new Wheels();
        wheels.init(hardwareMap);
        motor = hardwareMap.dcMotor.get("m");
        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        servo = hardwareMap.servo.get("s");
    }

    @Override
    public void loop() {
        mecanumDrive_Cartesian(gamepad1.left_stick_x, gamepad1.left_stick_y,gamepad1.right_stick_x);
        motor.setPower(gamepad1.right_stick_y *0.5);
        servo.setPosition(gamepad1.left_trigger);
    }
    public void mecanumDrive_Cartesian(double x, double y, double rotation)
    {
        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0] = x + y + rotation;
        wheelSpeeds[1] = -x + y - rotation;
        wheelSpeeds[2] = -x + y + rotation;
        wheelSpeeds[3] = x + y - rotation;

        normalize(wheelSpeeds);

        wheels.rightFrontMotor.setPower(wheelSpeeds[0]);
        wheels.leftFrontMotor.setPower(wheelSpeeds[1]);
        wheels.rightRearMotor.setPower(wheelSpeeds[2]);
        wheels.leftRearMotor.setPower(wheelSpeeds[3]);
        motor.setPower(gamepad1.right_stick_y);
        servo.setPosition(1-gamepad1.left_trigger);
    }   //mecanumDrive_Cartesian

    private void normalize(double[] wheelSpeeds)
    {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++)
        {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude)
            {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0)
        {
            for (int i = 0; i < wheelSpeeds.length; i++)
            {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }   //normalize
}
