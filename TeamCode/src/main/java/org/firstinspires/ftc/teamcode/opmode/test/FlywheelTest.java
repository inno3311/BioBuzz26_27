package org.firstinspires.ftc.teamcode.opmode.test;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.subsystems.Flywheel;

import com.bylazar.telemetry.PanelsTelemetry;
import com.bylazar.telemetry.TelemetryManager;

@TeleOp(name = "Flywheel Test")
public class FlywheelTest extends LinearOpMode {

    TelemetryManager panels;



    @Override
    public void runOpMode() {
        Flywheel flywheel = new Flywheel(
            hardwareMap, "shooter"
        );
        flywheel.setReversed(true);

        panels = PanelsTelemetry.INSTANCE.getTelemetry();

        waitForStart();

        while (opModeIsActive()) {
            if (gamepad1.a) {
                flywheel.applyPIDF();
                flywheel.setVelocity(Flywheel.FlyWheelConfigurables.setVel);
                //flywheel.setRPM(4500);
            } else if (gamepad1.b) {
                flywheel.stop();
            }

            telemetry.addData(
                "Target RPM", flywheel.getTargetRPM()
            );
            telemetry.addData(
                "Actual RPM", flywheel.getRPM()
            );
            telemetry.addData(
                "Velocity Error", flywheel.getError()
            );

            telemetry.addData(
                "Target Velocity", flywheel.getTargetVelocity()
            );
            //telemetry.update();


            panels.addData("curr vel", flywheel.getCurrentVelocity());
            panels.addData("set vel", flywheel.getTargetVelocity());
            panels.addData("0", 0);


            panels.update(telemetry);
        }

        flywheel.stop();
    }
}