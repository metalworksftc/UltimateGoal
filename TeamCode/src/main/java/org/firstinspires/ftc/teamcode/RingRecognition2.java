package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

@Autonomous(name = "RingRecognition2")

public class RingRecognition2 extends LinearOpMode {
    Wheels wheels;
    Camera camera;
    Arm arm;

    @Override
    public void runOpMode() {
        wheels = new Wheels(hardwareMap, telemetry);
        camera = new Camera(hardwareMap, telemetry, this);
        arm = new Arm(hardwareMap, telemetry);
        arm.close();
        waitForStart();

        wheels.forward(8, 0.25);


        String rings = camera.seeRings();
        telemetry.addLine(rings);
        telemetry.update();
        sleep(1000);


        if (rings.equals("Quad")) {
            wheels.standardDrive(30,95,45,0);

        } else if (rings.equals("Single")) {
            wheels.standardDrive(30,75,15,30);

        } else if (rings.equals("no stack")) {
            wheels.standardDrive(30,47,5,0);
        }

    }
}
