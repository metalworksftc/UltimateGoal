package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoTest")

public class AutoTest extends LinearOpMode {
    Wheels wheels;
    @Override
    public void runOpMode() throws InterruptedException {
        wheels = new Wheels();
        wheels.init(hardwareMap);
        waitForStart();
        wheels.forward(1, 4 );
    }

}
