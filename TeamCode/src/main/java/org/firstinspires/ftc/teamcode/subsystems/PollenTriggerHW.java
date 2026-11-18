package org.firstinspires.ftc.teamcode.subsystems;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.Servo;

public class PollenTriggerHW
{

   // Name used for the hardware map and general ID.
   private String m_name;

   // Save a copy of the OpMode to gain access to telemetry, hardwareMap and controllers
   private OpMode m_OpMode = null;

   // The servo that drives the Pollen gate.
   private Servo m_servo;

   private double m_curPos;
   /**
    * Constructor
    * @param opMode Robot OpMode object
    * @param name Name of the Mapped hardware device
    */
   public PollenTriggerHW(OpMode opMode, String name)
   {
      //super(opMode);
      this.m_name = name;
      this.m_OpMode = opMode;
      init();
   }

   /**
    * Init the hardware device
    */
   public void init()
   {
      m_OpMode.telemetry.log().add("Adding: ", m_name);
      m_servo = m_OpMode.hardwareMap.get(Servo.class, m_name);
      m_servo.setPosition(0.5);
      m_curPos = m_servo.getPosition();

      m_servo.setDirection(Servo.Direction.REVERSE);
      //m_servo.scaleRange(0.5, 1.0); // only go from midpoint to far right point
   }

   /////////////////////////////////
   ///  Interface Controls
   /////////////////////////////////



   public void open()
   {
      m_servo.setPosition(0.55);
   }
   public void close()
   {
      m_servo.setPosition(0.5);
   }

   public void setPosition(float value) {
      m_servo.setPosition(value);
   }

   public void increase() {
      //m_curPos = m_curPos + 0.01;
      //m_servo.setPosition(m_curPos);

      open();
   }

   public void decrease() {
      //m_curPos = m_curPos - 0.01;
      //m_servo.setPosition(m_curPos);

      close();
   }

   public double getCurrentPos() {
      return m_servo.getPosition();
   }
//   public void stop()
//   {
//      m_intake.setPower(0);
//   }
//   public double getPower()
//   {
//      return m_intake.getPower();
//   }
//

   /////////////////////////////////
   ///  Commands Functions
   /////////////////////////////////


}
