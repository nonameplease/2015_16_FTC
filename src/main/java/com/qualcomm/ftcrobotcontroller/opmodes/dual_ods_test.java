package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;

/**
 * Created by Scott on 12/30/2015.
 */
public class dual_ods_test extends OpMode {

    DeviceInterfaceModule dim;
    OpticalDistanceSensor ods_l;
    OpticalDistanceSensor ods_r;

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;

    @Override
    public void init() {
        dim = hardwareMap.deviceInterfaceModule.get("device");
        ods_l = hardwareMap.opticalDistanceSensor.get("odsl");
        ods_r = hardwareMap.opticalDistanceSensor.get("odsr");

        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotorRear = hardwareMap.dcMotor.get("left_drive_rear");
        rightMotorRear = hardwareMap.dcMotor.get("right_drive_rear");

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRear.setDirection(DcMotor.Direction.REVERSE);

    }

    @Override
    public void loop() {
        double distance_l = ods_l.getLightDetected();
        double distance_r = ods_r.getLightDetected();



        if (distance_l < 0.01)
        {
            leftMotor.setPower(0.5);
            //rightMotor.setPower(0.5);
            leftMotorRear.setPower(0.5);
           // rightMotorRear.setPower(0.5);
        }
        else
        {
            leftMotor.setPowerFloat();
           // rightMotor.setPowerFloat();
            leftMotorRear.setPowerFloat();
           // rightMotorRear.setPowerFloat();
        }

        if (distance_r < 0.01)
        {
            rightMotor.setPower(0.5);
            rightMotorRear.setPower(0.5);
        }
        else
        {
            rightMotorRear.setPowerFloat();
            rightMotor.setPowerFloat();
        }



        telemetry.addData("Distance Detected Left", distance_l);
        telemetry.addData("Distance Detected Right", distance_r);

    }
}
