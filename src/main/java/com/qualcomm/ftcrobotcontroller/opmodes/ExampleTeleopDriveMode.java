package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by owner on 10/4/2015.
 */
public class ExampleTeleopDriveMode extends OpMode {

    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;

    final double UP_POSITION = 0.0;
    final double DOWN_POSITION = 0.3;


    Servo climber;


    @Override
    public void init() {
        /* get reference to the motors from hardware map */
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotorRear = hardwareMap.dcMotor.get("left_drive_rear");
        rightMotorRear = hardwareMap.dcMotor.get("right_drive_rear");

        /* reverse the right motor */
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRear.setDirection(DcMotor.Direction.REVERSE);

        climber = hardwareMap.servo.get("climber");


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

        if (gamepad1.x)
        {
            climber.setPosition(UP_POSITION);
        }

        if(gamepad1.a)
        {
            climber.setPosition(DOWN_POSITION);
        }



    }
}
