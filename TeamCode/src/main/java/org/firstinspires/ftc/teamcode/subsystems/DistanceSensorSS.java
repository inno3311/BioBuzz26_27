package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorSS
{

    private final DistanceSensor m_distanceSensor;

    private OpMode m_opMode;

    double m_currentDistance;
    public DistanceSensorSS(OpMode opMode) {
        m_distanceSensor = opMode.hardwareMap.get(DistanceSensor.class, "distancesensor");
        m_opMode = opMode;
    }

    public double getDistance() {
        return m_distanceSensor.getDistance(DistanceUnit.INCH);
    }

    public double getSavedValue() { return m_currentDistance;}

    public Command isBlockageCommand()
    {
        return Command.build()
            .setExecute(() ->
            {
                m_currentDistance = getDistance();
                m_opMode.telemetry.log().add(
                String.format("Distance: %.2f inches", m_currentDistance));
            })
            .setDone(() ->
            {
                m_currentDistance = getDistance();
                m_opMode.telemetry.log().add(
                    String.format("Done Distance: %.2f inches", m_currentDistance));
                return true;
            });

    }
}
