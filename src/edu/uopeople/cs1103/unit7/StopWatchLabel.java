package edu.uopeople.cs1103.unit7;

import java.awt.event.*;
import javax.swing.*;


/**
 * A custom component that acts as a simple stop-watch.  When the user clicks
 * on it, this componet starts timing.  When the user clicks again,
 * it displays the time between the two clicks.  Clicking a third time
 * starts another timer, etc.  While it is timing, the label just
 * displays the message "Timing....".
 */
public class StopWatchLabel extends JLabel implements MouseListener {

	private long startTime;   // Start time of timer.
	//   (Time is measured in milliseconds.)

	private boolean running;  // True when the timer is running.

	/**
	 * Constructor sets initial text on the label to
	 * "Click to start timer." and sets up a mouse listener
	 * so the label can respond to clicks.
	 */
	public StopWatchLabel() {
		super( "  Click to start timer.  ", JLabel.CENTER );
		addMouseListener( this );
	}


	/**
	 * Tells whether the timer is currently running.
	 */
	public boolean isRunning() {
		return running;
	}


	/**
	 * React when the user presses the mouse by starting
	 * or stopping the timer and changing the text that
	 * is shown on the label.
	 */
	public void mousePressed( MouseEvent evt ) {
		Timer timer = null; // add timer
		if ( running == false ) {
			// Record the time and start the timer.
			running = true;
			startTime = evt.getWhen();  // Time when mouse was clicked.
			setText( "Timing...." );
			// added this: if timer is null and it is to start
			// make a timer and start it else restart the clock
			// this - is the mouselistener
			if ( timer == null ) {
				timer = new Timer( 200, this::actionPerformed );
				timer.start();
			} else {
				timer.restart();
			}
			////////
		} else {
			// Stop the timer.  Compute the elapsed time since the
			// timer was started and display it.
			timer.stop(); // stop the clock
			running = false;
			long endTime = evt.getWhen();
			double seconds = ( endTime - startTime ) / 1000.0;
			setText( "Time: " + seconds + " sec." );
		}
	}

	public void mouseReleased( MouseEvent evt ) {
	}

	public void mouseClicked( MouseEvent evt ) {
	}

	public void mouseEntered( MouseEvent evt ) {
	}

	public void mouseExited( MouseEvent evt ) {
	}
//create something that does stuff after the mouse is clicked and the timer starts
	public void actionPerformed( ActionEvent evt ) {
		long time = ( System.currentTimeMillis() - startTime ) / 1000;
		setText( "Running:  " + time + " seconds" );
	}

}
