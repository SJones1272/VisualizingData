import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class DataFileChooser extends JPanel implements ActionListener {
 
 public static void main(String[] args){
  SwingUtilities.invokeLater(new Runnable(){
   public void run(){
    UIManager.put("swing.boldMetal", Boolean.FALSE);
    createAndShowGUI();
   }
  }); 
 }

 JFileChooser fc;
 JButton openButton;
 JTextArea log;
 
 public DataFileChooser(){
  super(new BorderLayout());
  
        //create file chooser
        fc = new JFileChooser();
        
        //sets up a small dialog area
        log = new JTextArea(5, 20);
        log.setMargin(new Insets(5, 5, 5, 5));
        log.setEditable(false);
        JScrollPane logScrollPane = new JScrollPane(log);
        
        
        //allows files and directories to be selected
        fc.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        
        //creates open button
        openButton = new JButton("Open a File..");
        openButton.addActionListener(this);
        
        //Layout of the button
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(openButton);
        
        add(buttonPanel, BorderLayout.PAGE_START);
        add(logScrollPane, BorderLayout.CENTER);
 }
 
 
 //Determines what to do when button is clicked
 @Override
 public void actionPerformed(ActionEvent e) {
  if(e.getSource() == openButton){
   int returnVal = fc.showOpenDialog(DataFileChooser.this);
  if(returnVal == JFileChooser.APPROVE_OPTION){
   File file = fc.getSelectedFile();
   //displays path of file chosen
   //System.out.println("Path: " + file.getAbsolutePath() + "\n");
   log.append("The path for " + file.getName() + " has been \ncopied to the clipboard \n\n");
   copy2Clip(file.getAbsolutePath());
  }
  else{
   System.out.println("Operation cancelled. \n");
   }
  }
 }
 
 //sets up the window. 
 private static void createAndShowGUI(){
  JFrame frame = new JFrame("Data File Chooser");
  frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  frame.add(new DataFileChooser());
  frame.pack();
  frame.setSize(300, 200);
  frame.setVisible(true);
 }
 
 private static void copy2Clip(String x){
  StringSelection sSelect = new StringSelection(x);
  Clipboard clip = Toolkit.getDefaultToolkit().getSystemClipboard();
  clip.setContents(sSelect, null);
 }
 
}