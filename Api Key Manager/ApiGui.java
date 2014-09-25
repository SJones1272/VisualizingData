import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.List;
import java.util.prefs.BackingStoreException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class ApiGui extends JPanel implements ActionListener{

 /*
  * Need to add some sort of remove button beside each row. 
  */
 
 
 JButton addB;
 ApiKeyManager apiM;
 JTextField namef = new JTextField("Name..", 10);
    JTextField methodf = new JTextField("Method..",10);
    JTextField keyf = new JTextField("key value..", 10);
 
 public static void main(String[] args){
  SwingUtilities.invokeLater(new Runnable(){
   public void run(){
    UIManager.put("swing.boldMetal", Boolean.FALSE);
    try {
     createAndShow();
    } catch (IOException e) {
     throw new Error("File not found");
    } catch (BackingStoreException e) {
     throw new Error("Backing Store Error");
    }
   }
  }); 
 }
 
 public ApiGui() throws BackingStoreException{
  super(new BorderLayout());
  apiM = new ApiKeyManager();
  List<ApiKey> keys = apiM.keys;
    
  //gets the table data
  Object rowData[][] = new Object[keys.size()][3];
     for(int i = 0; i<keys.size(); i++){
      rowData[i][0] = keys.get(i).name;
      rowData[i][1] = keys.get(i).method;
      rowData[i][2] = keys.get(i).key;
     }

     Object columnNames[] = { "Name", "Method", "Api key" };
     JTable table = new JTable(rowData, columnNames);
     JScrollPane scrollPane = new JScrollPane(table);
     addB = new JButton("add key");
     addB.addActionListener(this);
     
     JPanel buttonPanel = new JPanel();
     buttonPanel.add(namef);
     buttonPanel.add(methodf);
     buttonPanel.add(keyf);
        buttonPanel.add(addB);
        
        add(buttonPanel, BorderLayout.PAGE_END);
        add(scrollPane, BorderLayout.CENTER);
     
 }
 
 private static void createAndShow() throws IOException, BackingStoreException{
  JFrame frame = new JFrame();
     frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
     frame.add(new ApiGui());
     //frame.setSize(500, 200);
     frame.setVisible(true);
     frame.pack();
 }

 @Override
 public void actionPerformed(ActionEvent e) {
  if(e.getSource() == addB){
   String t1 = namef.getText();
   String t2 = methodf.getText();
   String t3= keyf.getText();
   if(!(t1.equals("Name..") || t2.equals("Method..") || t3.equals("key value.."))){
   apiM.addKey(new ApiKey(t1, t2, t3));
   }
  }
 }
 
 
}