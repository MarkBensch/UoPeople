package edu.uopeople.cs1103.unit7.grading;


import javax.swing.JFrame;
import java.io.File;
import javax.swing.JFileChooser;
//import javax.swing.JOptionPane;
/**
 *
 * @author Owner
 */
public class MediaDirChooser extends JFrame {
    public MediaDirChooser () {
        super("Directory Chooser");
    }
    //Allow the user specify the Directory name
    public File getFile() {
        //Display dialog, so user can choose a directory to open
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int result = fileChooser.showOpenDialog(this);

        File fileName = fileChooser.getSelectedFile();

        //if user clicked cancel button on dialog then return "No Path" or ""
        if (result == JFileChooser.CANCEL_OPTION) {
            //TODO should i update the class "NO Path Selected" perhaps?
            
        }
        return  fileName;
    }
    public String getStrFile() {
        //Display dialog, so user can choose a directory to open
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);

        int result = fileChooser.showOpenDialog(this);

        File fileName = fileChooser.getSelectedFile();

        //if user clicked cancel buttonon dialog then return "No Path" or ""
        if (result == JFileChooser.CANCEL_OPTION) {
            //TODO should i update the class "NO Path Selected" perhaps?
            
        }
        return  fileName.toString();
    }

}
