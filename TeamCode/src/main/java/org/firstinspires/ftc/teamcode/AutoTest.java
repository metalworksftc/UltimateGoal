package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

@Autonomous(name = "Auto Test ")

public class AutoTest extends LinearOpMode {
    DcMotor motor;
    Servo servo;
    Wheels wheels;

    @Override
    public void runOpMode() {

        waitForStart();

        wheels.forwardCounts(1000, 0.75);
    }
}
