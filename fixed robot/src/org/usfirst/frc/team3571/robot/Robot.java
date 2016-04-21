
package org.usfirst.frc.team3571.robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.RobotBase;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.Solenoid;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;

import java.util.TimerTask;
import java.awt.Toolkit;

import org.usfirst.frc.team3571.robot.commands.ExampleCommand;
import org.usfirst.frc.team3571.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;


public class Robot extends IterativeRobot {

	public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	
	public static PowerDistributionPanel pdb = new PowerDistributionPanel();
	public static Solenoid c = new Solenoid(0);
	public static Solenoid b = new Solenoid(1);
	
	
	//Motors 0 and 1 are for the right side and 2 and 3 are for the left
	public static RobotDrive drive = new RobotDrive (2, 1, 3 , 0 );
	
	public static Joystick driver = new Joystick(0);
	public static Joystick operator = new Joystick(1);
	
	
	
	//The following lines show how to import motors. FYI motors are all connected to talons
	//Talon *name* = new Talon (*channel*);
	
	//Imports the arm's motor
	public static Talon arm = new Talon (4);
	
	//Imports the ball intake's motor
	public static Talon ballintake = new Talon (5);
	
	//Imports the lifting mechanisms motor
	public static Talon lift = new Talon (6);

    Command autonomousCommand;
    SendableChooser chooser;

    
    public void robotInit() {
		oi = new OI();
        chooser = new SendableChooser();
        chooser.addDefault("Default Auto", new ExampleCommand());
//        chooser.addObject("My Auto", new MyAutoCommand());
        SmartDashboard.putData("Auto mode", chooser);
    }
	
	
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	
    public void autonomousInit() {
        autonomousCommand = (Command) chooser.getSelected();
       
        /*long t = System.currentTimeMillis();
        long end = t + 250;
        while(System.currentTimeMillis() < end){
        	drive.arcadeDrive(-0.3, 0);
        }
        drive.arcadeDrive(0, 0);
        
        end = t + 1000;
        while(System.currentTimeMillis() < end){
        	arm.set(-0.4);
        }
        arm.set(0);
        
        end = t + 1000;
        while(System.currentTimeMillis() < end){
        	drive.arcadeDrive(-0.8, 0);
        }
        drive.arcadeDrive(0, 0);*/
        
        long t = System.currentTimeMillis();
        long end = t + 2000;
        while(System.currentTimeMillis() < end){
        	drive.arcadeDrive(-0.3, 0);
        }
        drive.arcadeDrive(0, 0);
        
    	// schedule the autonomous command (example)
        if (autonomousCommand != null) autonomousCommand.start();
    }
    

    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
        b.set(true);
        c.set(false);
        
        
      
    }

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
       

               
        if(driver.getRawButton(3)){
        	b.set(false);
        	c.set(true);
        }
        if(driver.getRawButton(2)){
        	b.set(true);
        	c.set(false);
        }
            
                //starts driving using drivey and drivex values
        drive.arcadeDrive(driver.getY(), driver.getX());
        //drive.arcadeDrive(moveValue, rotateValue);h
        
        
        //Axis 5 is the Y value of the right thumbstick
        //Sets motor speed
        arm.set(driver.getRawAxis(5) * 0.75);
        
        //Sets intake speed
        if(driver.getRawAxis(3) > driver.getRawAxis(2))
        {
        	ballintake.set(driver.getRawAxis(3));
        }
        else if(driver.getRawAxis(3) < driver.getRawAxis(2))
        {
        	ballintake.set(-driver.getRawAxis(2));
        }
        else
        {
        	ballintake.set(0);
        }
        
        //Moves the lift mechanism
        lift.set(-operator.getY());
        
        //standard delay
        Timer.delay(0.01);
        
      
        }


	/**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
        teleopPeriodic();
    }
}
