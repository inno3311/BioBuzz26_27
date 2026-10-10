package org.firstinspires.ftc.teamcode.subsystems;

import com.pedropathing.ivy.Command;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DistanceSensor;

import org.firstinspires.ftc.robotcore.external.navigation.DistanceUnit;

public class DistanceSensorSS
{

    private final DistanceSensor m_distanceSensor;

    double m_currentDistance;
    public DistanceSensorSS(OpMode opMode) {
        m_distanceSensor = opMode.hardwareMap.get(DistanceSensor.class, "distancesensor");
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
            });
//            .setDone(() ->
//            {                 //Change distance requirement
//                if (m_currentDistance < 20)
//                { //false means there is blockage within 12 inches from robot position.
//                    return false;
//                }
//                else if (m_currentDistance >= 20)
//                { //true means there is NO blockage within 12 inches from robot position
//                    return true;
//                }
//                return true;
//            });
    }
//            .setEnd((endCondition) -> {
//
//            });

}
