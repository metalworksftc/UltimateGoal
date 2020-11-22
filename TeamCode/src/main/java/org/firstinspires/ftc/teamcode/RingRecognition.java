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
            wheels.left(30, 0.5);
            wheels.absoluteTurnPower(0, 0.5);
            wheels.forward(95, 0.5);
            sleep(2000);
            arm.drop();
            wheels.backwards(45,0.5);
        } else if (rings.equals("Single")) {
            wheels.left(0, 0.5);
            wheels.forward(75, 0.5);
            sleep(2000);
            arm.drop();
            wheels.backwards(15,0.5);
        } else if (rings.equals("no stack")) {
            wheels.left(30, 0.5);
            wheels.absoluteTurnPower(0, 0.5);
            wheels.forward(47, 0.5);
            sleep(2000);
            arm.drop();
            wheels.backwards(5, 0.5);
        }
    }
}
