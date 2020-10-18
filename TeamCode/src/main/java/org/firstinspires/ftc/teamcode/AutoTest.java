package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoTest")

public class AutoTest extends LinearOpMode {
    Wheels wheels;

    @Override
    public void runOpMode() {
        wheels = new Wheels(hardwareMap, telemetry);
        waitForStart();
        wheels.forward(20,0.25);
        wheels.absoluteTurnPower(90, 0.25);
        wheels.forward(10,0.25);
        wheels.absoluteTurnPower(-90,0.25);
    }

}