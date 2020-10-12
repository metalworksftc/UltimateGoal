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
        wheels.left(20, 0.5); ;
        wheels.right(20,0.5);
    }

}
