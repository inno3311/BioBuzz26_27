
package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorEx;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.PIDFCoefficients;
import com.qualcomm.robotcore.util.Range;

import com.bylazar.configurables.annotations.Configurable;
import com.bylazar.configurables.annotations.Sorter;

public class Flywheel {

    private final DcMotorEx motor;

    // PIDF tuning values - starting points, not final tuned values
//    private double p = 10.0;
//    private double i = 0.0;
//    private double d = 0.0;
//    private double f = 12.0;

    @Configurable
    public static class FlyWheelConfigurables {
        @Sorter(sort = 0)
        public static double p = 0.0;

        @Sorter(sort = 1)
        public static double i = 0.0;

        @Sorter(sort = 2)
        public static double d = 0.0;

        @Sorter(sort = 3)
        public static double f = 12.0;

        public static double setVel = 1400;
    }

    private double targetVelocity = 0.0;

    public Flywheel(HardwareMap hardwareMap, String motorName) {
        motor = hardwareMap.get(DcMotorEx.class, motorName);

        motor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);
        motor.setMode(DcMotor.RunMode.STOP_AND_RESET_ENCODER);
        motor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);

        applyPIDF();
    }

    // Set target speed in encoder ticks per second
    public void setVelocity(double ticksPerSecond) {
        targetVelocity = Math.max(0, ticksPerSecond);
        motor.setVelocity(targetVelocity);
    }

    // Set speed in RPM, assuming the encoder reports motor-shaft RPM
    public void setRPM(double rpm) {
        setVelocity(rpm * motor.getMotorType().getTicksPerRev() / 60.0);
    }

    public void stop() {
        targetVelocity = 0;
        motor.setVelocity(0);
    }

    // Update PIDF coefficients
    public void setPIDF(double p, double i, double d, double f) {
        FlyWheelConfigurables.p = p;
        FlyWheelConfigurables.i = i;
        FlyWheelConfigurables.d = d;
        FlyWheelConfigurables.f = f;
        applyPIDF();
    }

    public void applyPIDF() {
        motor.setPIDFCoefficients(
            DcMotor.RunMode.RUN_USING_ENCODER,
            new PIDFCoefficients(FlyWheelConfigurables.p, FlyWheelConfigurables.i, FlyWheelConfigurables.d, FlyWheelConfigurables.f)
        );
    }

    public double getTargetVelocity() {
        return targetVelocity;
    }

    public double getCurrentVelocity() {
        return motor.getVelocity();
    }

    public double getError() {
        return targetVelocity - motor.getVelocity();
    }

    public double getRPM() {
        return motor.getVelocity() * 60.0
            / motor.getMotorType().getTicksPerRev();
    }

    public double getTargetRPM() {
        return targetVelocity * 60.0
            / motor.getMotorType().getTicksPerRev();
    }

//    public void setPIDP(double p) {
//        setPIDF(p, i, d, f);
//    }
//
//    public void setPIDI(double i) {
//        setPIDF(p, i, d, f);
//    }
//
//    public void setPIDD(double d) {
//        setPIDF(p, i, d, f);
//    }
//
//    public void setPIDFValue(double f) {
//        setPIDF(p, i, d, f);
//    }

    public void setPower(double power) {
        motor.setPower(Range.clip(power, 0.0, 1.0));
    }

    public void setReversed(boolean reversed) {
        motor.setDirection(
            reversed ? DcMotor.Direction.REVERSE
                : DcMotor.Direction.FORWARD
        );
    }
}
