package com.util;

import java.awt.AWTException;
import java.awt.Robot;

public class RobotUtil {

	public static Robot robot = null; 
	
	public static Robot getInstance() {
		if(robot==null) {
			try {
				robot = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
		}
		return robot;
	}
}
