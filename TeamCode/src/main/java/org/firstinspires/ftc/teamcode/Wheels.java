package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.internal.opmode.TelemetryImpl;

@TeleOp(name = "Wheels")

public class Wheels extends OpMode {
    DcMotor leftFrontMotor, leftRearMotor, rightFrontMotor, rightRearMotor;

    public void init(HardwareMap hardwareMap) {
        leftFrontMotor = hardwareMap.dcMotor.get("lfm");
        leftFrontMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightFrontMotor = hardwareMap.dcMotor.get("rfm");
        leftRearMotor = hardwareMap.dcMotor.get("lrm");
        leftRearMotor.setDirection(DcMotorSimple.Direction.REVERSE);
        rightRearMotor = hardwareMap.dcMotor.get("rrm");
    }
    public Telemetry telemetry = new TelemetryImpl(this);
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

    protected static final double DRIVE_CALIBRATION = 54;
    protected static final double CALIBRATION_COUNTS = 4848;

    public void drive_Cartesian(double x, double y, double rotation)
    {
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

    }   //Drive_Cartesian
    protected void reverse(double inches, double power) {

        final double COUNTS_PER_INCH = -CALIBRATION_COUNTS / DRIVE_CALIBRATION;

        power = -power;

        int target = leftFrontMotor.getCurrentPosition() + (int) (COUNTS_PER_INCH * inches);

        rightFrontMotor.setPower(-power);
        leftFrontMotor.setPower(power);
        rightRearMotor.setPower(power);
        leftRearMotor.setPower(-power);

        while (leftFrontMotor.getCurrentPosition() > target) {
            telemetry.addLine("Driving: " + leftFrontMotor.getCurrentPosition() + " of " + target + " counts");
            telemetry.update();
        }

        leftFrontMotor.setPower(0);
        rightFrontMotor.setPower(0);
       // waitSec(0.5);
    }


    @Override
    public void init() {

    }

    @Override
    public void loop() {

    }
}