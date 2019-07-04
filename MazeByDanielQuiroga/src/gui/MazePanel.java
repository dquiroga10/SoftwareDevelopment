package gui;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Panel;
import java.awt.RenderingHints;
import java.awt.Color;

/**
 * Add functionality for double buffering to an AWT Panel class.
 * Used for drawing a maze.
 * 
 * @author Peter Kemper
 *
 */
public class MazePanel extends Panel  {
	/* Panel operates a double buffer see
	 * http://www.codeproject.com/Articles/2136/Double-buffer-in-standard-Java-AWT
	 * for details
	 */
	// bufferImage can only be initialized if the container is displayable,
	// uses a delayed initialization and relies on client class to call initBufferImage()
	// before first use
	private Image bufferImage;  
	private Graphics2D graphics; // obtained from bufferImage, 
	// graphics is stored to allow clients to draw on the same graphics object repeatedly
	// has benefits if color settings should be remembered for subsequent drawing operations
	
	Graphics g;
	
	/**
	 * Constructor. Object is not focusable.
	 */
	public MazePanel() {
		setFocusable(false);
		bufferImage = null; // bufferImage initialized separately and later
		graphics = null;	// same for graphics
	}
	
	@Override
	public void update(Graphics g) {
		paint(g);
	}
	/**
	 * Method to draw the buffer image on a graphics object that is
	 * obtained from the superclass. 
	 * Warning: do not override getGraphics() or drawing might fail. 
	 */
	public void update() {
		paint(getGraphics());
	}
	
	/**
	 * Draws the buffer image to the given graphics object.
	 * This method is called when this panel should redraw itself.
	 * The given graphics object is the one that actually shows 
	 * on the screen.
	 */
	@Override
	public void paint(Graphics g) {
		if (null == g) {
			System.out.println("MazePanel.paint: no graphics object, skipping drawImage operation");
		}
		else {
			g.drawImage(bufferImage,0,0,null);	
		}
	}

	/**
	 * Obtains a graphics object that can be used for drawing.
	 * This MazePanel object internally stores the graphics object 
	 * and will return the same graphics object over multiple method calls. 
	 * The graphics object acts like a notepad where all clients draw 
	 * on to store their contribution to the overall image that is to be
	 * delivered later.
	 * To make the drawing visible on screen, one needs to trigger 
	 * a call of the paint method, which happens 
	 * when calling the update method. 
	 * @return graphics object to draw on, null if impossible to obtain image
	 */
	public Graphics createBufferGraphics() {
		// if necessary instantiate and store a graphics object for later use
		if (null == graphics) { 
			if (null == bufferImage) {
				bufferImage = createImage(Constants.VIEW_WIDTH, Constants.VIEW_HEIGHT);
				if (null == bufferImage)
				{
					System.out.println("Error: creation of buffered image failed, presumedly container not displayable");
					return null; // still no buffer image, give up
				}		
			}
			graphics = (Graphics2D) bufferImage.getGraphics();
			if (null == graphics) {
				System.out.println("Error: creation of graphics for buffered image failed, presumedly container not displayable");
			}
			else {
				// System.out.println("MazePanel: Using Rendering Hint");
				// For drawing in FirstPersonDrawer, setting rendering hint
				// became necessary when lines of polygons 
				// that were not horizontal or vertical looked ragged
				graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
						RenderingHints.VALUE_INTERPOLATION_BILINEAR);
			}
		}
		return graphics;
	}
	
	/**
	 * uses java graphics to fill the rectangle with the specified dimensions
	 * and position in the maze 
	 * @param x position in the maze
	 * @param y position in the maze 
	 * @param width how wide the rectangle is 
	 * @param height how high the rectangles is 
	 */
	public void fillRect(int x, int y, int width, int height) {
		graphics.fillRect(x, y, width, height);
	}
	
	/**
	 * sets the color of the graphics for when drawing in order to draw 
	 * with various colors
	 * @param red how much red component in the color
	 * @param green how much green component in the color
	 * @param blue how much blue component in the color
	 */
	public void setColor(int red, int green, int blue) {
		graphics.setColor(new Color(red , green, blue));
	}
	
	/**
	 * uses java graphics to fill the polygon whose dimensions and points are
	 * passed through as parameters 
	 * @param xPoints array of the x points 
	 * @param yPoints array of the y points 
	 * @param the total number of points 
	 */
	public void fillPolygon(int[] xPoints, int[]yPoints, int nPoints) {
		graphics.fillPolygon(xPoints, yPoints, nPoints);
	}
	
	/**
	 * used to draw a line from the distance between the two points passed in
	 * @param x1
	 * @param y1
	 * @param x2
	 * @param y2
	 */
	public void drawLine(int x1, int y1, int x2, int y2) {
		graphics.drawLine(x1, y1, x2, y2);
	}
	
	/**
	 * fills and oval bounded by the parameters passed in 
	 * @param x position in the maze
	 * @param y position in the maze 
	 * @param width how wide the oval is 
	 * @param height how high the oval is 
	 */
	public void fillOval(int x, int y, int width, int height) {
		graphics.fillOval(x, y, width, height);
	}
	
	
	/**
	 * converts the three components of the color into a single integer
	 * @param r the red component 
	 * @param g the green component
	 * @param b the blue component
	 * @return the integer representation of the color in sRGB
	 */
	public int getRGB(int r, int g, int b) {
		Color col = new Color(r, g, b);
		return col.getRGB();
	}
	
	/**
	 * converts the sRGB representation of the color wanted into a an array 
	 * with 3 elements each for the reference of the componenets of the Color 
	 * class
	 * @param col, the sRGB representation of the color
	 * @return the array with the components of the color
	 */
	public int[] getColorComponents(int col) {
		int[] com = new int[3];
		Color color = new Color(col);
		com[0] = color.getRed();
		com[1] = color.getGreen();
		com[2] = color.getBlue();
		return com;
	}
}
