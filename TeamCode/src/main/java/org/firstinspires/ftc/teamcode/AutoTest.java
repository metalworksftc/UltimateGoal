package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoTest")

public class AutoTest extends LinearOpMode {
    Wheels wheels;

    @Override
    public void runOpMode() {
        wheels = new Wheels();
        wheels.init(hardwareMap, telemetry);
        waitForStart();
        wheels.absoluteTurnPower(90, 0.5); ;
        sleep(5000);

    }

}
