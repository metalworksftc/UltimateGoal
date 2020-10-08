package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "AutoTest")

public class AutoTest extends LinearOpMode {
    Wheels wheels;
    @Override
    public void runOpMode() throws InterruptedException {

        waitForStart();
        wheels = new Wheels();
        wheels.init(hardwareMap);

        wheels.drive_Cartesian(0, 50, 0);




//        int count = 0;
//        while (count < 10);
//        wheels.drive_Cartesian(0, 0.9,0);
//        count++;
    }

}
