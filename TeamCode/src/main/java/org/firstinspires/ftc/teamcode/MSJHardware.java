package org.firstinspires.ftc.teamcode;


import com.qualcomm.robotcore.hardware.DcMotor;
import com.qualcomm.robotcore.hardware.DcMotorSimple;
import com.qualcomm.robotcore.hardware.HardwareMap;
import com.qualcomm.robotcore.hardware.Servo;
import com.qualcomm.robotcore.util.ElapsedTime;

public class MSJHardware
{
    //Create Drivetrain Motors
    public DcMotor frontRightMotor = null;
    public DcMotor frontLeftMotor = null;
    public DcMotor backRightMotor = null;
    public DcMotor backLeftMotor = null;

    //Create Shooter Motor
    public DcMotor shooterMotor = null;

    //Create Lift
    public DcMotor liftMotor = null;

    //Create Loader Servo
    public Servo loaderServo = null;

    //Additional Variables
    HardwareMap hwMap = null;
    public ElapsedTime runtime = new ElapsedTime();
    public MSJHardware(){

    }
   // public MSJHardware(HardwareMap ahwMap) {

    //    initialize(ahwMap);
   // }

    public void init(HardwareMap ahwMap){
        hwMap = ahwMap;

        //Connect Drive Train Motors
        frontRightMotor = hwMap.get(DcMotor.class, "frontRightMotor");
        frontLeftMotor = hwMap.get(DcMotor.class,"frontLeftMotor");
        backRightMotor = hwMap.get(DcMotor.class,"backRightMotor");
        backLeftMotor = hwMap.get(DcMotor.class,"backLeftMotor");

        //Connect Shooter Motor
        shooterMotor = hwMap.get(DcMotor.class,"shooterMotor");

        //Connect Lift Motor
        liftMotor = hwMap.get(DcMotor.class,"liftMotor");
        //Connect Loader Servo
        loaderServo = hwMap.get(Servo.class,"loaderServo");

        //Set Up Motor Direction
        frontRightMotor.setDirection(DcMotor.Direction.FORWARD);
        frontLeftMotor.setDirection(DcMotor.Direction.REVERSE);
        backRightMotor.setDirection(DcMotor.Direction.FORWARD);
        backLeftMotor.setDirection(DcMotor.Direction.REVERSE);

        shooterMotor.setDirection(DcMotor.Direction.REVERSE);

        liftMotor.setDirection(DcMotor.Direction.REVERSE);


        //Set Motor Mode  (For now we will run it without an encoder, but when we do stop_and_reset_encoder for each motor and then run_using_encoder for each motor)
        // We should be using encoders b/c running based off time is not reliable b/c it depends on charge of battery and the power the motors are receiving
        frontRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        frontLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backRightMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);
        backLeftMotor.setMode(DcMotor.RunMode.RUN_WITHOUT_ENCODER);

        liftMotor.setMode(DcMotor.RunMode.RUN_USING_ENCODER);
      //  liftMotor.setMode(DcMotor.RunMode.RUN_TO_POSITION);

        //Set ZERO POWER BEHAVIOR for Drive Train as BRAKE so that the motors stop turning
        frontRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        frontLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backRightMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);
        backLeftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        liftMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.BRAKE);

        //Set ZERO POWER BEHAVIOR for Shooters as FREESPIN so that the motor continue to spin freely
        shooterMotor.setZeroPowerBehavior(DcMotor.ZeroPowerBehavior.FLOAT);

        //Set Motors to use no power
        frontRightMotor.setPower(0);
        frontLeftMotor.setPower(0);
        backRightMotor.setPower(0);
        backLeftMotor.setPower(0);
        shooterMotor.setPower(0);
        liftMotor.setPower(0);

    }
}
