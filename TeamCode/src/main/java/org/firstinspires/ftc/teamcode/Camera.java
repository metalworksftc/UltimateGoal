package org.firstinspires.ftc.teamcode;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

public class Camera {
    TFObjectDetector tfod;
    private static final String VUFORIA_KEY =
            " AV5DiRv/////AAABmQQY+qA6hk+yvs2h+C9m7O0RUTsHHsO90qj9c2c7U2NiWAGSvnLjTlL5NWFlyOcqHjqMAk4UNRsDPhS15hb3argJVORiC7ra0KqgkIGfaOcujDTpGtpWrcvnKS1bvM2UIhpLjy20Do5TPt2HRhfEo3MqDFehYGpA48mYCLEbBL4mhl1RdCZYAWHbsZgYTbyOzieaEvwcjSYHJDV+sQUkqzRrHXQx6OZtDtz9BkZalvvJa+pGGhBhN9PEXwZkU6gfon3LRZS8NJv84Az5/dsHvCNwdNpaFTyW62emm5+Q9Nf9iIgkH9b7Wk4b7RbYL3SdAr9PzWeSVi0DxZ2HIkyroAReM8UAwfq75HQdNXQq6hRB";
    VuforiaLocalizer vuforia;
    private static final String TFOD_MODEL_ASSET = "UltimateGoal.tflite";
    private static final String LABEL_FIRST_ELEMENT = "Quad";
    private static final String LABEL_SECOND_ELEMENT = "Single";
    private Telemetry telemetry;
    private LinearOpMode linearOpMode;
    private HardwareMap hardwareMap;

    public Camera(HardwareMap hardwareMap, Telemetry telemetry, LinearOpMode linearOpMode) {
        init(hardwareMap, telemetry, linearOpMode);
    }

    private void init(HardwareMap hardwareMap, Telemetry telemetry, LinearOpMode linearOpMode) {
        this.telemetry = telemetry;
        this.linearOpMode = linearOpMode;
        this.hardwareMap = hardwareMap;
        initVuforia();
        initTfod();

    }
    public String seeRings() {
        if (tfod != null) {
            tfod.activate();
        }
        List<Recognition> updatedRecognitions = null;
        if (linearOpMode.opModeIsActive()) {
            long targetTime = System.currentTimeMillis() + 3750;
            while (linearOpMode.opModeIsActive() &&
                    System.currentTimeMillis() < targetTime
                    && (updatedRecognitions == null || updatedRecognitions.size() == 0)) {
                // getUpdatedRecognitions() will return null if no new information is available since
                // the last time that call was made.
                updatedRecognitions = tfod.getUpdatedRecognitions();
                if (updatedRecognitions != null) {
                    telemetry.addData("# Object Detected", updatedRecognitions.size());
                    // step through the list of recognitions and display boundary info.
                    int i = 0;
                    for (Recognition recognition : updatedRecognitions) {
                        telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
                        telemetry.addData(String.format("  left,top (%d)", i), "%.03f , %.03f",
                                recognition.getLeft(), recognition.getTop());
                        telemetry.addData(String.format("  right, bottom (%d)", i), "%.03f , %.03f",
                                recognition.getRight(), recognition.getBottom());
                    }
                    telemetry.update();
                   linearOpMode.sleep(2000);
                }
            }
        }
        if (updatedRecognitions.size() == 0){
            return "no stack";
        }
        return updatedRecognitions.get(0).getLabel();
    }


    private void initVuforia() {
        /*
         * Configure Vuforia by creating a Parameter object, and passing it to the Vuforia engine.
         */
        VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();

        parameters.vuforiaLicenseKey = VUFORIA_KEY;
        parameters.cameraDirection = VuforiaLocalizer.CameraDirection.BACK;

        //  Instantiate the Vuforia engine
        vuforia = ClassFactory.getInstance().createVuforia(parameters);

        // Loading trackables is not necessary for the TensorFlow Object Detection engine.
    }

    private void initTfod() {
        int tfodMonitorViewId = hardwareMap.appContext.getResources().getIdentifier(
                "tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());
        TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
        tfodParameters.minResultConfidence = 0.8f;
        tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
        tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABEL_FIRST_ELEMENT, LABEL_SECOND_ELEMENT);
    }
}