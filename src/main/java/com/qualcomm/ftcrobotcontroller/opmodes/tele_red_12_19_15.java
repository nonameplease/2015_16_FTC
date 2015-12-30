package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by owner on 10/4/2015.
 */
public class tele_red_12_19_15 extends LinearOpMode {



    DcMotor leftMotor;
    DcMotor rightMotor;
    DcMotor leftMotorRear;
    DcMotor rightMotorRear;
    DcMotor leftMotorRotate;
    DcMotor rightMotorRotate;
    DcMotor pickUp;


    final double UP_POSITION = 0.0;
    final double DOWN_POSITION = 0.5;

    final double CLOSE_POSITION = 1;
    final double RESQ_POSITION = 0.3;
    final double Buffer1 = 0.9;
    final double Buffer2 = 0.8;
    final double Buffer3 = 0.7;
    final double Buffer4 = 0.6;
    final double Buffer5 = 0.5;

    Servo climber;
    Servo resQ;



    @Override
    public void runOpMode() throws InterruptedException {

    
        /* get reference to the motors from hardware map */
        leftMotor = hardwareMap.dcMotor.get("left_drive");
        rightMotor = hardwareMap.dcMotor.get("right_drive");
        leftMotorRear = hardwareMap.dcMotor.get("left_drive_rear");
        rightMotorRear = hardwareMap.dcMotor.get("right_drive_rear");
        leftMotorRotate = hardwareMap.dcMotor.get("left_drive_rotate");
        rightMotorRotate = hardwareMap.dcMotor.get("right_drive_rotate");
        pickUp = hardwareMap.dcMotor.get("pick_up");

        /* reverse the right motor */
        rightMotor.setDirection(DcMotor.Direction.REVERSE);
        rightMotorRotate.setDirection(DcMotor.Direction.REVERSE);
        leftMotorRear.setDirection(DcMotor.Direction.REVERSE);

        climber = hardwareMap.servo.get("climber");
        resQ = hardwareMap.servo.get("resQ");
        resQ.setPosition(CLOSE_POSITION);

        waitForStart();

        while(opModeIsActive()) {
            
        
        
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
            double valueFeedFromLT = gamepad1.left_trigger;
            double valueFeedFromRT = gamepad1.right_trigger;

            if (gamepad1.left_bumper) {
                leftMotorRotate.setPower(-1);
            } else if (valueFeedFromLT > 0.5) {
                leftMotorRotate.setPower(1);
            } else {
                leftMotorRotate.setPowerFloat();
            }

            if (gamepad1.right_bumper) {
                rightMotorRotate.setPower(-1);
            } else if (valueFeedFromRT > 0.5) {
                rightMotorRotate.setPower(1);
            } else {
                rightMotorRotate.setPowerFloat();
            }


            if (gamepad2.x) {
                climber.setPosition(UP_POSITION);
            }

            if (gamepad2.a) {
                climber.setPosition(DOWN_POSITION);
            }

            if (gamepad2.left_bumper) {
                resQ.setPosition(CLOSE_POSITION);
            }

            if (gamepad2.right_bumper) {
                resQ.setPosition(Buffer1);
                sleep(200);
                resQ.setPosition(Buffer2);
                sleep(200);
                resQ.setPosition(Buffer3);
                sleep(200);
                resQ.setPosition(Buffer4);
                sleep(200);
                resQ.setPosition(Buffer5);
                sleep(200);
                resQ.setPosition(RESQ_POSITION);

            }

            if (gamepad1.left_trigger > 0.2) {
                double ltvalue = gamepad1.left_trigger;
                resQ.setPosition(ltvalue);
            }

            if (gamepad2.y) {
                pickUp.setPower(1);
            } else if (gamepad2.b) {
                pickUp.setPower(-1);
            } else {
                pickUp.setPowerFloat();
            }


            telemetry.addData("left motor power", leftMotor.getPower());
            telemetry.addData("right motor power", rightMotor.getPower());
            telemetry.addData("climber position", climber.getPosition());

            waitOneFullHardwareCycle();

        }

    }
}
