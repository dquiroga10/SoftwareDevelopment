/**
 * 
 */
package gui;

import generation.Order;


import java.awt.BorderLayout;

import java.awt.event.KeyListener;
import java.io.File;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;


/**
 * This class is a wrapper class to startup the Maze game as a Java application
 * 
 * This code is refactored code from Maze.java by Paul Falstad, www.falstad.com, Copyright (C) 1998, all rights reserved
 * Paul Falstad granted permission to modify and use code for teaching purposes.
 * Refactored by Peter Kemper
 * 
 * TODO: use logger for output instead of Sys.out
 */
public class MazeApplication extends JFrame {

	// not used, just to make the compiler, static code checker happy
	private static final long serialVersionUID = 1L;
	BasicRobot robot;
	RobotDriver drivers;
	
	/**
	 * Constructor
	 */
	public MazeApplication() {
		init(null);
	}

	/**
	 * Constructor that loads a maze from a given file or uses a particular method to generate a maze
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
     * or a filename that stores an already generated maze that is then loaded, or can be null
	 */
	public MazeApplication(String parameter) {
		init(parameter);
	}

	/**
	 * Instantiates a controller with settings according to the given parameter.
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
	 * or a filename that contains a generated maze that is then loaded,
	 * or can be null
	 * @return the newly instantiated and configured controller
	 */
	 Controller createController(String parameter) {
	    // need to instantiate a controller to return as a result in any case
	    Controller result = new Controller() ;
	    String msg = null; // message for feedback
	    // Case 1: no input
	    if (parameter == null) {
	        msg = "MazeApplication: maze will be generated with a randomized algorithm."; 
	    }
	    // Case 2: Prim
	    else if ("Prim".equalsIgnoreCase(parameter))
	    {
	        msg = "MazeApplication: generating random maze with Prim's algorithm.";
	        result.setBuilder(Order.Builder.Prim);
	    }
	    // Case 3 a and b: Eller, Kruskal or some other generation algorithm
	    else if ("Kruskal".equalsIgnoreCase(parameter))
	    {
	    	// TODO: for P2 assignment, please add code to set the builder accordingly
	        throw new RuntimeException("Don't know anybody named Kruskal ...");
	    }
	    else if ("Eller".equalsIgnoreCase(parameter))
	    {
	    	msg = "MazeApplication: generating random maze with Eller's algorithm.";
	        result.setBuilder(Order.Builder.Eller);
	    }
	    // Case 4: a file
	    else {
	        File f = new File(parameter) ;
	        if (f.exists() && f.canRead())
	        {
	            msg = "MazeApplication: loading maze from file: " + parameter;
	            result.setFileName(parameter);
	            return result;
	        }
	        else {
	            // None of the predefined strings and not a filename either: 
	            msg = "MazeApplication: unknown parameter value: " + parameter + " ignored, operating in default mode.";
	        }
	    }
	    // controller instanted and attributes set according to given input parameter
	    // output message and return controller
	    System.out.println(msg);
	    return result;
	}

	/**
	 * Initializes some internals and puts the game on display.
	 * @param parameter can identify a generation method (Prim, Kruskal, Eller)
     * or a filename that contains a generated maze that is then loaded, or can be null
	 */
	private void init(String parameter) {
	    // instantiate a game controller and add it to the JFrame
	    Controller controller = createController(parameter);
	    BasicRobot robot = new BasicRobot();
	    robot.setMaze(controller);
	    ManualDriver driver = new ManualDriver();
	    driver.setRobot(robot);
		//wizard.setRobot(robot);
		controller.setRobotAndDriver(robot, driver);
		controller.setSkillLevel(0);
		add(controller.getPanel(), BorderLayout.CENTER) ;
		//BasicRobot robot = new BasicRobot();
		//robot.setMaze(controller);
		
		// instantiate a key listener that feeds keyboard input into the controller
		// and add it to the JFrame
		KeyListener kl = new SimpleKeyListener(this, controller, controller.getDriver());
		
		addKeyListener(kl);
		JPanel tempPanel = chooseMenu(controller, robot, (SimpleKeyListener) kl);
		add(tempPanel, BorderLayout.NORTH);
		controller.setMyPanel(tempPanel);
		
		//ManualDriver driver = new ManualDriver();
		//Wizard wizard = new Wizard();
		//WallFollower follow = new WallFollower();
		//Explorer explore = new Explorer();
		//robot = new BasicRobot();
		//robot.setMaze(controller);
		//driver.setRobot(robot);
		//wizard.setRobot(robot);
		//controller.setRobotAndDriver(robot, driver);
		//controller.setSkillLevel(0);
		//follow.setRobot(robot);
		//explore.setRobot(robot);
		//assertNotNull(controller.getRobot());
		//assertNotNull(controller.getDriver());
		//KeyListener kl = new SimpleKeyListener(this, controller, controller.getDriver());
		//controller.setRobotAndDriver(robot, driver);
		
		// set the frame to a fixed size for its width and height and put it on display
		setSize(405, 400) ;
		setVisible(true) ;
		// focus should be on the JFrame of the MazeApplication and not on the maze panel
		// such that the SimpleKeyListener kl is used
		setFocusable(true) ;
		// start the game, hand over control to the game controller
		controller.start();
	}
	
	/**
	 * Main method to launch Maze game as a java application.
	 * The application can be operated in three ways. 
	 * 1) The intended normal operation is to provide no parameters
	 * and the maze will be generated by a randomized DFS algorithm (default). 
	 * 2) If a filename is given that contains a maze stored in xml format. 
	 * The maze will be loaded from that file. 
	 * This option is useful during development to test with a particular maze.
	 * 3) A predefined constant string is given to select a maze
	 * generation algorithm, currently supported is "Prim".
	 * @param args is optional, first string can be a fixed constant like Prim or
	 * the name of a file that stores a maze in XML format
	 */
	public static void main(String[] args) {
	    JFrame app ; 
		switch (args.length) {
		case 1 : app = new MazeApplication(args[0]);
		break ;
		case 0 : 
		default : app = new MazeApplication() ;
		break ;
		}
		app.repaint() ;
	}
	
	
	/**
	 * this is the panel I created in order to allow the user to choose what type of maze he/she wants, skillLevel, and what type of driver he/she wants
	 * @param control, the controller that keeps track of the maze
	 * @param robot, the robot that is connected to the controller
	 * @param kl, the simple key listener that allows for movements 
	 * @return, the panel 
	 */
	public JPanel chooseMenu(Controller control, BasicRobot robot, SimpleKeyListener kl) {
		JPanel pan = new JPanel();
		
		String[] mazeTypes = { "Default", "Eller", "Prim" };
		JComboBox<Object> mazeList = new JComboBox<Object>(mazeTypes);
		mazeList.setSelectedIndex(0);
		//mazeList.setEditable(true);
		ActionListener mazeListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String m = (String) mazeList.getSelectedItem();
				switch(m) {
				case "Default":
					System.out.println("hit default");
					control.setBuilder(Order.Builder.DFS);
					break;
				case "Eller":
					System.out.println("hit Eller");
					control.setBuilder(Order.Builder.Eller);
					break;
				case "Prim":
					System.out.println("hit Prim");
					control.setBuilder(Order.Builder.Prim);
					break;
				}
				
			}
			
		};
		mazeList.addActionListener(mazeListener);
		//mazeList.addActionListener((ActionListener) this);
		
		String[] skill = { "0", " 1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15" };
		JComboBox<Object> skillList = new JComboBox<Object>(skill);
		skillList.setSelectedIndex(0);
		//skillList.setEditable(true);
		ActionListener skillListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String m = (String) skillList.getSelectedItem();
				switch(m) {
				case "0":
					control.setSkillLevel(0);
					break;
				case "1":
					control.setSkillLevel(1);
					break;
				case "2":
					control.setSkillLevel(2);
					break;
				case "3":
					control.setSkillLevel(3);
					break;
				case "4":
					control.setSkillLevel(4);
					break;
				case "5":
					control.setSkillLevel(5);
					break;
				case "6":
					control.setSkillLevel(6);
					break;
				case "7":
					control.setSkillLevel(7);
					break;
				case "8":
					control.setSkillLevel(8);
					break;
				case "9":
					control.setSkillLevel(9);
					break;
				case "10":
					control.setSkillLevel(10);
					break;
				case "11":
					control.setSkillLevel(11);
					break;
				case "12":
					control.setSkillLevel(12);
					break;
				case "13":
					control.setSkillLevel(13);
					break;
				case "14":
					control.setSkillLevel(14);
					break;
				case "15":
					control.setSkillLevel(15);
					break;
				}
				
			}
			
		};
		skillList.addActionListener(skillListener);
		//skillList.addActionListener((ActionListener) this);
		
		String[] Driver = { "Manual", "Wizard", "WallFollower", "Explorer", "Pledge" };
		JComboBox<Object> driveList = new JComboBox<Object>(Driver);
		driveList.setSelectedIndex(0);
		//driveList.setEditable(true);
		ActionListener driveListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String m = (String) driveList.getSelectedItem();

				switch(m) {
				case "Manual":
					
					RobotDriver driver = new ManualDriver();
					robot.setMaze(control);
					driver.setRobot(robot);
					kl.setDriver(driver);
					control.setRobotAndDriver(robot, driver);
					break;
				case "Wizard":
					RobotDriver wizard = new Wizard();
					robot.setMaze(control);
					wizard.setRobot(robot);
					kl.setDriver(wizard);
					control.setRobotAndDriver(robot, wizard);
					break;
				case "WallFollower":
					RobotDriver follower = new WallFollower();
					robot.setMaze(control);
					follower.setRobot(robot);
					kl.setDriver(follower);
					control.setRobotAndDriver(robot, follower);
					break;
				case "Explorer":
					RobotDriver explore = new Explorer();
					robot.setMaze(control);
					explore.setRobot(robot);
					kl.setDriver(explore);
					control.setRobotAndDriver(robot, explore);
					break;
				case "Pledge":
					RobotDriver pledge = new Pledge();
					robot.setMaze(control);
					pledge.setRobot(robot);
					kl.setDriver(pledge);
					control.setRobotAndDriver(robot, pledge);
					break;
				}
			}
			
		};
		driveList.addActionListener(driveListener);
		//driveList.addActionListener((ActionListener) this);
		
		JButton button = new JButton();
		button.setText("Start");
		ActionListener buttonListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				//kl.setDriver(control.getDriver());
				control.switchFromTitleToGenerating(0);
				pan.setVisible(false);
			}
			
		};
		button.addActionListener(buttonListener);
		
		pan.add(mazeList);
		pan.add(skillList);
		pan.add(driveList);
		pan.add(button);
		 
		
		return pan;
		
	}

}
