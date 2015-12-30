package com.qualcomm.ftcrobotcontroller.opmodes;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorController;
import com.qualcomm.robotcore.hardware.Gamepad;
import com.qualcomm.robotcore.hardware.Servo;

/**
 * Created by owner on 10/4/2015.
 */
public class ExampleRobotDrive {

    private DcMotor leftMotor;
    private DcMotor rightMotor;
    private Servo one;
    private Servo two;

    public ExampleRobotDrive(DcMotor left, DcMotor right)
    {
        leftMotor = left;
        rightMotor = right;

        rightMotor.setDirection(DcMotor.Direction.REVERSE);
    }


    public void arcadeDrive(double forwardSpeed, double turnRate)
    {
        leftMotor.setPower(forwardSpeed + turnRate);
        rightMotor.setPower(forwardSpeed - turnRate);
    }

    public void  arcadeDrive(Gamepad gamepad)
    {
        arcadeDrive(-gamepad.left_stick_y, gamepad.left_stick_x);
    }

    public void tankDrive(double leftValue, double rightValue)
    {
        leftMotor.setPower(leftValue);
        rightMotor.setPower(rightValue);
    }

    public void tankDrive(double leftValue, double rightValue, boolean squareInputs)
    {
        if(squareInputs)
        {
            if(leftValue >= 0.0)
            {
                leftValue = (leftValue*leftValue);
            }
            else
            {
                leftValue = -(leftValue*leftValue);
            }

            if(rightValue >= 0.0)
            {
                rightValue = (rightValue*rightValue);
            }
            else
            {
                rightValue = -(rightValue*rightValue);
            }
        }
        tankDrive(leftValue, rightValue);
    }

    public void encorderdrive(int DISTANCE, double power)
    {
        final int ENCODER_CPR = 1440;
        final double GEAR_RATIO = 2;
        final int WHEEL_DIAMETER = 4;
       /* final static int DISTANCE = 24;

        */

        final double CIRCUMFERENCE = Math.PI * WHEEL_DIAMETER;
        final double ROTATIONS = DISTANCE / CIRCUMFERENCE;
        final double COUNTS = ENCODER_CPR * ROTATIONS * GEAR_RATIO;

        leftMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);
        rightMotor.setChannelMode(DcMotorController.RunMode.RESET_ENCODERS);

        leftMotor.setTargetPosition((int) COUNTS);
        rightMotor.setTargetPosition((int) COUNTS);

        leftMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);
        rightMotor.setChannelMode(DcMotorController.RunMode.RUN_TO_POSITION);

        leftMotor.setPower(power);
        rightMotor.setPower(power);
    }

    public void servoControl(int one_open,int one_close,int two_open,int two_close, boolean button_1,boolean button_2)
    {
        if(button_1)
        {
            one.setPosition(one_open);
            two.setPosition(two_open);
        }

        if(button_2)
        {
            one.setPosition(one_close);
            two.setPosition(two_close);
        }
    }

}
