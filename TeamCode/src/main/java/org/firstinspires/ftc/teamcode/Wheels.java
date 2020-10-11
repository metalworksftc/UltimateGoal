package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;

import org.firstinspires.ftc.robotcore.external.Telemetry;

public class Wheels {
    DcMotor leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;
    Telemetry telemetry;

    public void init(HardwareMap hardwareMap, Telemetry telemetry) {
        leftFrontMotor = hardwareMap.dcMotor.get("lfm");
        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontMotor = hardwareMap.dcMotor.get("rfm");
        leftRearMotor = hardwareMap.dcMotor.get("lrm");
        leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRearMotor = hardwareMap.dcMotor.get("rrm");
        this.telemetry = telemetry;
    }

    private void normalize(double[] wheelSpeeds) {
        double maxMagnitude = Math.abs(wheelSpeeds[0]);

        for (int i = 1; i < wheelSpeeds.length; i++) {
            double magnitude = Math.abs(wheelSpeeds[i]);

            if (magnitude > maxMagnitude) {
                maxMagnitude = magnitude;
            }
        }

        if (maxMagnitude > 1.0) {
            for (int i = 0; i < wheelSpeeds.length; i++) {
                wheelSpeeds[i] /= maxMagnitude;
            }
        }
    }

    public void driveCartesian(double x, double y, double rotation) {
        double wheelSpeeds[] = new double[4];

        wheelSpeeds[0] = x + y + rotation;
        wheelSpeeds[1] = -x + y - rotation;
        wheelSpeeds[2] = -x + y + rotation;
        wheelSpeeds[3] = x + y - rotation;

        normalize(wheelSpeeds);

        rightFrontMotor.setPower(wheelSpeeds[0]);
        leftFrontMotor.setPower(wheelSpeeds[1]);
        rightRearMotor.setPower(wheelSpeeds[2]);
        leftRearMotor.setPower(wheelSpeeds[3]);

    }   //mecanumDrive_Cartesian

    protected static final double DRIVE_CALIBRATION = 75;
    protected static final double CALIBRATION_COUNTS = 3000;
    double COUNTS_PER_INCH = CALIBRATION_COUNTS / DRIVE_CALIBRATION;

    public void forward(double power, double distance) {

        int target = leftFrontMotor.getCurrentPosition() - (int) (COUNTS_PER_INCH * distance);
        driveCartesian(0, -power, 0);

        while (leftFrontMotor.getCurrentPosition() > target) {
            telemetry.addLine("Driving: " + leftFrontMotor.getCurrentPosition() + " of " + target);
            telemetry.update();
        }
        driveCartesian(0, 0, 0);
    }

    public void sleep(double time) {
        try {
            Thread.sleep((long) time * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}