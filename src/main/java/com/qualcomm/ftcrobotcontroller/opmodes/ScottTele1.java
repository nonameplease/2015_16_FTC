package com.qualcomm.ftcrobotcontroller.opmodes;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DeviceInterfaceModule;
import com.qualcomm.robotcore.hardware.OpticalDistanceSensor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by owner on 10/4/2015.
 */
public class ScottTele1 extends OpMode {



    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;
    DcMotor leftMotorRotate;
    DcMotor rightMotorRotate;

    DeviceInterfaceModule interfaceModule;
    OpticalDistanceSensor leftDistance;
    OpticalDistanceSensor rightDistance;

    final double UP_POSITION = 0.0;
    final double DOWN_POSITION = 0.3;

    Servo climber;


    SensorDrive OpticalSensorValue;


    @Override
    public void init() {
        /* get reference to the motors from hardware map */
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotorRear = hardwareMap.dcMotor.get("left_drive_rear");
        rightMotorRear = hardwareMap.dcMotor.get("right_drive_rear");
        leftMotorRotate = hardwareMap.dcMotor.get("left_drive_rotate");
        rightMotorRotate = hardwareMap.dcMotor.get("right_drive_rotate");

        /* reverse the right motor */
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRear.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRotate.setDirection(DcMotor.Direction.REVERSE);

        climber = hardwareMap.servo.get("climber");

        interfaceModule = hardwareMap.deviceInterfaceModule.get("interface_module");
        leftDistance = hardwareMap.opticalDistanceSensor.get("left_d");
        rightDistance = hardwareMap.opticalDistanceSensor.get("right_d");

        OpticalSensorValue = new SensorDrive
                (hardwareMap.deviceInterfaceModule.get("device"),
                        hardwareMap.opticalDistanceSensor.get("odslf"),
                        hardwareMap.opticalDistanceSensor.get("odsrf"));

    }

    @Override
    public void loop() {
        /*
        get value from gamepad
        caution that gamepad's value is reversed so all value should muptiply by -1
        */
        float leftY = -gamepad1.left_stick_y;
        float rightY = -gamepad1.right_stick_y;


        /* set the power of motors with the gamepad values */
        leftMotor.setPower(leftY);
        leftMotorRear.setPower(-leftY);
        rightMotor.setPower(rightY);
        rightMotorRear.setPower(-rightY);

        /*
        rotate drive
         */
        if(gamepad1.y)
        {
            leftMotorRotate.setPower(1);
            rightMotorRotate.setPower(1);
        }
        else if(gamepad1.b)
        {
            leftMotorRotate.setPower(-1);
            rightMotorRotate.setPower(-1);
        }
        else
        {
            leftMotorRotate.setPower(0);
            rightMotorRotate.setPower(0);
        }

        if (gamepad1.x)
        {
            climber.setPosition(UP_POSITION);
        }

        if(gamepad1.a)
        {
            climber.setPosition(DOWN_POSITION);
        }

        telemetry.addData("left motor power", leftMotor.getPower());
        telemetry.addData("right motor power", rightMotor.getPower());
        telemetry.addData("climber position", climber.getPosition());
        telemetry.addData("ODSLF",OpticalSensorValue.reflectanceLeft);
        telemetry.addData("ODSRF", OpticalSensorValue.reflectanceRight);

    }
}
