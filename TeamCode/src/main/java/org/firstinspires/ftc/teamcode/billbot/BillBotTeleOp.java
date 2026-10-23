package org.firstinspires.ftc.teamcode.billbot;

import com.pedropathing.follower.Follower;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.pedro.Constants;
import org.firstinspires.ftc.teamcode.subsystems.DistanceSensorSS;
import org.firstinspires.ftc.teamcode.subsystems.FlywheelHW;
import org.firstinspires.ftc.teamcode.subsystems.IntakeHW;

//import com.bylazar.telemetry.PanelsTelemetry;
//import com.bylazar.panels.telemetry.TelemetryManager;

//import com.bylazar.telemetry;
//import com.bylazar.telemetry.PanelsTelemetry;

//import com.bylazar.telemetry.PanelsTelemetry;

@TeleOp(name = "Bill_Bot")
public class BillBotTeleOp extends OpMode {



    private DriveBaseHW m_driveBase;

    FlywheelHW m_flywheel;

    IntakeHW m_intake;

    @Override
    public void init() {

        m_driveBase = new DriveBaseHW();
        m_driveBase.init(this.hardwareMap);
        m_flywheel = new FlywheelHW(this, "flywheel");
        m_intake = new IntakeHW(this, "intake");
    }

    @Override
    public void init_loop() {


        telemetry.update();
    }

    @Override
    public void loop() {


        m_driveBase.drive(-gamepad1.left_stick_y, gamepad1.right_stick_x, -gamepad1.left_stick_x);

        // Send real-time data back to Panels dashboard
//        telemetry.addData("Path Busy", follower.isBusy());
//        telemetry.addData("X", follower.pose().x());
//        telemetry.addData("Y", follower.pose().y());
//        telemetry.addData("Heading", Math.toDegrees(follower.pose().heading()));
        telemetry.update();

        if (gamepad2.rightBumperWasPressed())
        {
            m_flywheel.increase_power();
        }
        else if (gamepad2.leftBumperWasPressed())
        {
            m_flywheel.decrease_power();
        }

        telemetry.addData("flywheelPower", m_flywheel.getPower());

        if (gamepad1.right_trigger > 0.1)
        {
            m_intake.intake();
        }
        else if (gamepad1.right_trigger <= 0.1)
        {
            m_intake.stop();
        }


        if (gamepad1.left_trigger > 0.1)
        {
            m_intake.outtake();
        }
        else if (gamepad1.left_trigger <= 0.1)
        {
            m_intake.stop();
        }

        telemetry.addData("intakePower", m_intake.getPower());
    }
}
