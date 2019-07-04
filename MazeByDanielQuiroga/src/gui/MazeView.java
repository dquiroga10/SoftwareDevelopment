package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import gui.Constants.StateGUI;

/**
 * Implements the screens that are displayed whenever the game is not in 
 * the playing state. The screens shown are the title screen, 
 * the generating screen with the progress bar during maze generation,
 * and the final screen when the game finishes.
 * 
 * @author Peter Kemper
 *
 */
public class MazeView {

	private StateGenerating controllerState; // used for generating screen
	int batLev = 0;
	int odometer = 0;
    
    public MazeView(StateGenerating c) {
        super() ;
        controllerState = c ;
    }
    
    
   /* public void redrawNoBattery(MazePanel panel, int pathLength, float energy) {
    	Graphics g = panel.getBufferGraphics() ;
        if (null == g) {
            System.out.println("MazeView.redrawNoBattery: can't get graphics object to draw on, skipping redraw operation") ;
        }
        else {
            redrawNoBattery(g, pathLength, energy);
        }
    }
    
    void redrawNoBattery(Graphics gc, int pathLength, float energy) {
		// produce blue background
		gc.setColor(Color.blue);
		gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title 
		gc.setFont(largeBannerFont);
		FontMetrics fm = gc.getFontMetrics();
		gc.setColor(Color.yellow);
		centerString(gc, fm, "ROBOT ERROR", 100);
		// write some extra blurb
		gc.setColor(Color.orange);
		gc.setFont(smallBannerFont);
		fm = gc.getFontMetrics();
		centerString(gc, fm, "Uh Oh!", 160);
		// write the instructions
		gc.setColor(Color.white);
		centerString(gc, fm, "Hit any key to restart", 300);
		
		gc.setColor(Color.magenta);
		gc.setFont(smallBannerFont);
		fm = gc.getFontMetrics();
		centerString(gc, fm, "TOTAL DISTANCE: "+ pathLength + " Cells!", 200); 
		
		gc.setColor(Color.magenta);
		gc.setFont(smallBannerFont);
		fm = gc.getFontMetrics();
		centerString(gc, fm, "ENERGY CONSUMPTION: " + energy + " Watts!", 250);
	}*/
    
    /**
     * Draws the title screen, screen content is hard coded
     * @param panel holds the graphics for the off-screen image
     * @param filename is a string put on display for the file
     * that contains the maze, can be null
     */
    public void redrawTitle(MazePanel panel, String filename) {
    	Graphics g = panel.createBufferGraphics() ;
        if (null == g) {
            System.out.println("MazeView.redrawTitle: can't get graphics object to draw on, skipping redraw operation") ;
        }
        else {
            redrawTitle(g,filename);
        }
    }
    /**
     * Helper method for redraw to draw the title screen, screen is hard coded
     * @param  gc graphics is the off-screen image, can not be null
     * @param filename is a string put on display for the file
     * that contains the maze, can be null
     */
    private void redrawTitle(Graphics gc, String filename) {
        // produce white background
        gc.setColor(Color.white);
        gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
        // write the title 
        gc.setFont(largeBannerFont);
        FontMetrics fm = gc.getFontMetrics();
        gc.setColor(Color.red);
        centerString(gc, fm, "MAZE", 100);
        // write the reference to Paul Falstad
        gc.setColor(Color.blue);
        gc.setFont(smallBannerFont);
        fm = gc.getFontMetrics();
        centerString(gc, fm, "by Paul Falstad", 160);
        centerString(gc, fm, "www.falstad.com", 190);
        // write the instructions
        gc.setColor(Color.black);
        if (filename == null) {
        	// default instructions
        	
        	centerString(gc, fm, "(Choose a skill level, driver and a", 220);
        	centerString(gc, fm, "type of maze builder) THEN HIT START!", 250);
        	centerString(gc, fm, "*Click arrow key to start playing once maze has loaded*", 300);
        }
        else {
        	// message if maze is loaded from file
        	centerString(gc, fm, "Loading maze from file:", 250);
        	centerString(gc, fm, filename, 300);
        }
        centerString(gc, fm, "Version 4.0", 350);
    }

	
	public void redraw(Graphics gc, StateGUI state, int px, int py, int view_dx,
			int view_dy, int walk_step, int view_offset, RangeSet rset, int ang, int pathLength, float batteryLevelUsed) {
		//dbg("redraw") ;
		switch (state) {
		case STATE_TITLE:
			redrawTitle(gc,null);
			break;
		case STATE_GENERATING:
			redrawGenerating(gc);
			break;
		case STATE_PLAY:
			// skip this one
			break;
		case STATE_FINISH:
			redrawFinish(gc, pathLength, batteryLevelUsed); // ADDED TWO EXTRA PARAMETERS IN ORDER TO PRINT OUT PATH LENGTH AND BATTERY LEVEL
			break;
		}
	}
	
	@SuppressWarnings("unused")
	private void dbg(String str) {
		System.out.println("MazeView:" + str);
	}
    /**
     * Draws the finish screen, screen content is hard coded
     * @param panel holds the graphics for the off-screen image
     */
	void redrawFinish(MazePanel panel, int pathLength, float batteryLevelUsed) {
		Graphics g = panel.createBufferGraphics() ;
        if (null == g) {
            System.out.println("MazeView.redrawFinish: can't get graphics object to draw on, skipping redraw operation") ;
        }
        else {
            redrawFinish(g, pathLength, batteryLevelUsed);
        }
	}
	/**
	 * Helper method for redraw to draw final screen, screen is hard coded
	 * @param gc graphics is the off-screen image
	 */
	void redrawFinish(Graphics gc, int pathLength, float batteryLevelUsed) {
		// produce blue background
		gc.setColor(Color.blue);
		gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title
		if (batteryLevelUsed < 3001) {
			gc.setFont(largeBannerFont);
			FontMetrics fm = gc.getFontMetrics();
			gc.setColor(Color.yellow);
			centerString(gc, fm, "You won!", 100);
			// write some extra blurb
			gc.setColor(Color.orange);
			gc.setFont(smallBannerFont);
			fm = gc.getFontMetrics();
			centerString(gc, fm, "Congratulations!", 160);
			// write the instructions
			gc.setColor(Color.white);
			centerString(gc, fm, "*Hit 0-9 or a-f to restart!*", 300);
			
			gc.setColor(Color.magenta);
			gc.setFont(smallBannerFont);
			fm = gc.getFontMetrics();
			centerString(gc, fm, "TOTAL DISTANCE: "+ pathLength + " Cells!", 200); 
			
			gc.setColor(Color.magenta);
			gc.setFont(smallBannerFont);
			fm = gc.getFontMetrics();
			centerString(gc, fm, "ENERGY CONSUMPTION: " + batteryLevelUsed + " Watts!", 250);
		}
		else {
			gc.setFont(largeBannerFont);
			FontMetrics fm = gc.getFontMetrics();
			gc.setColor(Color.yellow);
			centerString(gc, fm, "ROBOT ERROR!", 100);
			

			gc.setColor(Color.yellow);
			centerString(gc, fm, "OH NOO!", 150);
			
			gc.setColor(Color.orange);
			gc.setFont(smallBannerFont);
			fm = gc.getFontMetrics();
			centerString(gc, fm, "RAN OUT OF BATTERY", 220);
			// write the instructions
			gc.setColor(Color.white);
			centerString(gc, fm, "*Hit 0-9 or a-f to restart!*", 300);
			
			gc.setColor(Color.magenta);
			gc.setFont(smallBannerFont);
			fm = gc.getFontMetrics();
			centerString(gc, fm, "TOTAL DISTANCE: "+ pathLength + " Cells!", 200); 
		}
	}
	
    /**
     * Draws the generating screen, screen content is hard coded
     * @param panel holds the graphics for the off-screen image
     */
    public void redrawGenerating(MazePanel panel) {
    	Graphics g = panel.createBufferGraphics() ;
        if (null == g) {
            System.out.println("MazeView.redrawGenerating: can't get graphics object to draw on, skipping redraw operation") ;
        }
        else {
            redrawGenerating(g);
        }
    }
	/**
	 * Helper method for redraw to draw screen during phase of maze generation, screen is hard coded
	 * only attribute percentdone is dynamic
	 * @param gc graphics is the off screen image
	 */
	void redrawGenerating(Graphics gc) {
		// produce yellow background
		gc.setColor(Color.yellow);
		gc.fillRect(0, 0, Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
		// write the title 
		gc.setFont(largeBannerFont);
		FontMetrics fm = gc.getFontMetrics();
		gc.setColor(Color.red);
		centerString(gc, fm, "Building maze", 150);
		gc.setFont(smallBannerFont);
		fm = gc.getFontMetrics();
		// show progress
		gc.setColor(Color.black);
		if (null != controllerState) 
		    centerString(gc, fm, controllerState.getPercentDone()+"% completed", 200);
		else
			centerString(gc, fm, "Error: no controller, no progress", 200);
		// write the instructions
		centerString(gc, fm, "Hit escape to stop", 300);
	}
	
	private void centerString(Graphics g, FontMetrics fm, String str, int ypos) {
		g.drawString(str, (Constants.VIEW_WIDTH-fm.stringWidth(str))/2, ypos);
	}

	final Font largeBannerFont = new Font("TimesRoman", Font.BOLD, 48);
	final Font smallBannerFont = new Font("TimesRoman", Font.BOLD, 16);

}
