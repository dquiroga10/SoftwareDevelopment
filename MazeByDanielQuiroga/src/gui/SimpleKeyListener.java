package gui;

import java.awt.Container;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;



import generation.Cells;
import gui.Constants.UserInput;


/**
 * Class implements a translation for the user input handled by the MazeController class. 
 * The MazeApplication attaches the listener to the GUI, such that user keyboard input
 * flows from GUI to the listener.keyPressed to the MazeController.keyDown method.
 *
 * This code is refactored code from Maze.java by Paul Falstad, 
 * www.falstad.com, Copyright (C) 1998, all rights reserved
 * Paul Falstad granted permission to modify and use code for teaching purposes.
 * Refactored by Peter Kemper
 */
public class SimpleKeyListener implements KeyListener {

	private Container parent ;
	private Controller controller ;
	private RobotDriver driver;
	Cells cell;

	
	SimpleKeyListener(Container parent, Controller controller, RobotDriver driver){
		this.parent = parent;
		this.controller = controller;
		this.driver = driver;
	}
	/**
	 * Translate keyboard input to the corresponding operation for 
	 * the Controller.keyDown method.
	 */
	@Override
	public void keyPressed(KeyEvent arg0) {
		int key = arg0.getKeyChar();
		int code = arg0.getKeyCode();
		
		//Possible operations for UserInput based on enum
		// {ReturnToTitle, Start, 
		// Up, Down, Left, Right, Jump, 
		// ToggleLocalMap, ToggleFullMap, ToggleSolution, 
		// ZoomIn, ZoomOut };
		UserInput uikey = null;
		int value = 0;
			
		// translate keyboard input into operation for MazeController
		switch (key) {
		case ('w' & 0x1f): // Ctrl-w makes a step forward even through a wall
			uikey = UserInput.Jump;
			break;
		case '\t': case 'm': // show local information: current position and visible walls
			// precondition for showMaze and showSolution to be effective
			// acts as a toggle switch
			uikey = UserInput.ToggleLocalMap;
			break;
		case 'z': // show a map of the whole maze
			// acts as a toggle switch
			uikey = UserInput.ToggleFullMap;
			break;
		case 's': // show the solution on the map as a yellow line towards the exit
			// acts as a toggle switch
			uikey = UserInput.ToggleSolution;
			break;
		case '+': case '=': // zoom into map
			uikey = UserInput.ZoomIn;
			break ;
		case '-': // zoom out of map
			uikey = UserInput.ZoomOut;
			break ;
		case KeyEvent.VK_ESCAPE: // is 27
			uikey = UserInput.ReturnToTitle;
			break;
		case 'h': // turn left
			uikey = UserInput.Left;
			break;
		case 'j': // move backward
			uikey = UserInput.Down;
			break;
		case 'k': // move forward
			uikey = UserInput.Up;
			break;
		case 'l': // turn right
			uikey = UserInput.Right;
			break;
		case KeyEvent.CHAR_UNDEFINED: // fall back if key is undefined but code is
			// char input for 0-9, a-f skill-level
			if ((KeyEvent.VK_0 <= code && code <= KeyEvent.VK_9) || (KeyEvent.VK_A <= code && code <= KeyEvent.VK_Z)){
				if (code >= '0' && code <= '9') {
					value = code - '0';
					uikey = UserInput.Start;
				}
				if (code >= 'a' && code <= 'f') {
					value = code - 'a' + 10;
					uikey = UserInput.Start;
				}
			} else {
				if (KeyEvent.VK_ESCAPE == code)
					uikey = UserInput.ReturnToTitle;
				if (KeyEvent.VK_UP == code)
					uikey = UserInput.Up;
				if (KeyEvent.VK_DOWN == code)
					uikey = UserInput.Down;
				if (KeyEvent.VK_LEFT == code)
					uikey = UserInput.Left;
				if (KeyEvent.VK_RIGHT == code)
					uikey = UserInput.Right;
			}
			break;
		default:
			// check ranges of values as possible selections for skill level
			if (key >= '0' && key <= '9') {
				value = key - '0';
				uikey = UserInput.Start;
			} else
			if (key >= 'a' && key <= 'f') {
				value = key - 'a' + 10;
				uikey = UserInput.Start;
			} else
				System.out.println("SimpleKeyListener:Error: cannot match input key:" + key);
			break;
		}
		// don't let bad input proceed
		if (null == uikey) {
			System.out.println("SimpleKeyListener: ignoring unmatched keyboard input: key=" + key + " code=" + code);
			return;
		}
		
		assert (0 <= value && value <= 15);		
		// feed user input into controller
		// value is only used in combination with uikey == Start
		
		// if statement for keys that are needed for manual driver and then else for those going to control
		// System.out.println("val: "+ value + " uikey: "+ uikey);
		if (uikey == UserInput.Up || uikey == UserInput.Left || uikey == UserInput.Right || uikey == UserInput.Down) {
			try {
				driverSetMethods();
				((ManualDriver)driver).keyDown(uikey);
			} catch(Exception e) {
				try {
					algorithmSetMethods();
					driver.drive2Exit();
				} catch (Exception e1) {
					//controller.switchFromPlayingToWinning(driver.getPathLength(), driver.getEnergyConsumption());
					e1.printStackTrace();
				}
			}
		}
		else {
			controller.keyDown(uikey, value);
		}
		parent.repaint() ;
	}
	
	
	/**
	 * This method to make sure the Automatic Driver gets all the information it needs from the maze in order to function properly
	 * very similar to that of the manual driver
	 */
	private void algorithmSetMethods() {
		
		cell = controller.getMazeConfiguration().getMazecells();
		driver.setDimensions(cell.width, cell.height);
		driver.setDistance(controller.getMazeConfiguration().getMazedists());
		
		
		
	}
	
	/**
	 * This method to make sure the Manual Driver gets all the information it needs from the maze in order to function properly
	 */
	private void driverSetMethods() {
		// this sets the dimensions of the Manual Robot equal to that of the maze 
		if (((ManualDriver)driver).getWidth() == 0 && ((ManualDriver)driver).getHeigth() == 0) {
			if (cell == null) {
				cell = controller.getMazeConfiguration().getMazecells();
			}
			else if(cell != null) {
				cell = controller.getMazeConfiguration().getMazecells();
			}
			driver.setDimensions(cell.width, cell.height);
		}
		else {
			cell = controller.getMazeConfiguration().getMazecells();
			driver.setDimensions(cell.width, cell.height);
		}
		// this sets the distance of the Manual Robot equal to that of the maze and each current position
		if (((ManualDriver)driver).getDist() == null) {
			driver.setDistance(controller.getMazeConfiguration().getMazedists());
		}else if (((ManualDriver)driver).getDist() != null) {
			driver.setDistance(controller.getMazeConfiguration().getMazedists());
		}
	}
	
	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// nothing to do
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// NOTE FOR THIS TYPE OF EVENT IS getKeyCode always 0, so Escape etc is not recognized	
		// this is why we work with keyPressed
	}
	
	public void setDriver(RobotDriver driver) {
		this.driver = driver;
	}

}

