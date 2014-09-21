import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;


public class ApiGui extends JPanel implements ActionListener{

 JButton addB;
 
 public static void main(String[] args) throws FileNotFoundException{
  SwingUtilities.invokeLater(new Runnable(){
   public void run(){
    UIManager.put("swing.boldMetal", Boolean.FALSE);
    try {
     createAndShow();
    } catch (FileNotFoundException e) {
     throw new Error("File not found");
    }
   }
  }); 
 }
 
 public ApiGui() throws FileNotFoundException{
  super(new BorderLayout());
  ApiKeyManager keys = new ApiKeyManager();
     ApiKey[] listk = keys.listKeys();
     Object rowData[][] = new Object[listk.length][listk.length];
     
     for(int i = 0; i<listk.length; i++){
      rowData[i][0] = listk[i].name;
      rowData[i][1] = listk[i].method;
      rowData[i][2] = listk[i].key;
     }

     
     Object columnNames[] = { "Name", "Method", "Api key" };
     
     JTable table = new JTable(rowData, columnNames);
     JScrollPane scrollPane = new JScrollPane(table);
     addB = new JButton("add key");
     addB.addActionListener(this);
     
     JPanel buttonPanel = new JPanel();
        buttonPanel.add(addB);
        
        add(buttonPanel, BorderLayout.PAGE_END);
        add(scrollPane, BorderLayout.CENTER);
     
 }
 
 private static void createAndShow() throws FileNotFoundException{
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
   System.out.println("addB pressed");
  }
 }
 
 
}