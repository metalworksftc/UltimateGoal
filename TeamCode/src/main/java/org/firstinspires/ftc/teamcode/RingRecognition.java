package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.ArrayList;
import java.util.List;

@Autonomous(name = "RingRecognition")

public class RingRecognition extends LinearOpMode {
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
            standardDrive(30,95,45);

        } else if (rings.equals("Single")) {
            standardDrive(0,75,15);

        } else if (rings.equals("no stack")) {
            standardDrive(30,45,1);
        }
    }

    public void standardDrive(int left, int forward, int back) {
        wheels.left(left,wheels.driveSpeed);
        wheels.absoluteTurnPower(0, wheels.driveSpeed);
        wheels.forward(forward, wheels.driveSpeed);
        sleep(2000);
        arm.drop(3000);
        wheels.backwards(back, wheels.driveSpeed);
        sleep(500);
        wheels.right(10,wheels.driveSpeed);
    }
}
