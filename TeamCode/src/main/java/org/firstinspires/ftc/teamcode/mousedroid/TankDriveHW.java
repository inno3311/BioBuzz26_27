package org.firstinspires.ftc.teamcode.mousedroid;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.DcMotorSimple;

public class TankDriveHW
{
    DcMotorEx leftBack;
    DcMotorEx rightBack;



    TankDriveHW(OpMode opMode) {

        opMode.hardwareMap.get(DcMotor.class, "leftBack");
        opMode.hardwareMap.get(DcMotor.class, "rightBack");


        leftBack.setDirection(DcMotorSimple.Direction.FORWARD);
        rightBack.setDirection(DcMotorSimple.Direction.FORWARD);


    }

    public void tankDrive(double leftPower, double rightPower) {

        leftPower = clipPower(leftPower);
        rightPower = clipPower(rightPower);

        leftBack.setPower(leftPower);

        rightBack.setPower(rightPower);
    }

    private double clipPower(double power) {

        return Math.max(-1.0, Math.min(1.0, power));
    }


}
