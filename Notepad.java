import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;
import java.io.*;

public class Notepad implements ActionListener {
    //creating frame and textarea
    JFrame frame;
    JTextArea area;
    JMenuBar jmenuBar;

    //creating constructor
    Notepad() {
        frame = new JFrame("Notepad");
        area = new JTextArea();
        jmenuBar = new JMenuBar();
        //area=new JTextArea(500,500);

        JMenu file = new JMenu("file");
        JMenu edit = new JMenu("edit");

        JMenuItem openfile = new JMenuItem("Open File");
        JMenuItem savefile = new JMenuItem("Save File");
        JMenuItem printfile = new JMenuItem("Print File");
        JMenuItem newfile = new JMenuItem("New File");

        openfile.addActionListener(this);
        savefile.addActionListener(this);
        printfile.addActionListener(this);
        newfile.addActionListener(this);
        
        file.add(openfile);
        file.add(savefile);
        file.add(printfile);
        file.add(newfile);

        JMenuItem cut = new JMenuItem("Cut");
        JMenuItem copy = new JMenuItem("Copy");
        JMenuItem paste = new JMenuItem("Paste");
        JMenuItem close = new JMenuItem("close");

        cut.addActionListener(this);
        copy.addActionListener(this);
        paste.addActionListener(this);
        close.addActionListener(this);

        edit.add(cut);
        edit.add(copy);
        edit.add(paste);
        edit.add(close);

        jmenuBar.add(file);
        jmenuBar.add(edit);

        frame.setJMenuBar(jmenuBar);
        frame.add(area);
        frame.setVisible(true);
        frame.setSize(800, 800);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public static void main(String arg[]) {
        Notepad object = new Notepad();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String call = e.getActionCommand();
        if (call == "New File") {
            area.setText("");
        } else if (call == "Cut") {
            area.cut();
        } else if (call == "Paste") {
            area.paste();
        } else if (call == "Close") {
            frame.setVisible(false);
        }
        else if(call=="Save File"){
            JFileChooser fileChooser=new JFileChooser("C:");
            int ans=fileChooser.showOpenDialog(null);
            if(ans==fileChooser.APPROVE_OPTION){
                File file=new File(fileChooser.getSelectedFile().getAbsolutePath());
                BufferedWriter writer=null;
                try{
                    writer=new BufferedWriter(new FileWriter(file,false));}
                catch(IOException ex){
                    throw new RuntimeException(ex);
                }
                try{
                    writer.write(area.getText());
                }
                catch(IOException ex){
                    throw new RuntimeException(ex);
                }
                try{
                    writer.flush();
                }
                catch(IOException ex){
                    throw new RuntimeException(ex);
                }
                try {
                    writer.close();
                }
                catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call=="Open File"){
            JFileChooser fileChooser2=new JFileChooser("C:");
            int ans=fileChooser2.showOpenDialog(null);
            if(ans==JFileChooser.APPROVE_OPTION){
                File file=new File(fileChooser2.getSelectedFile().getAbsolutePath());
                try {
                    String s1 = "", s2 = "";
                    BufferedReader bufferedReader = new BufferedReader(new FileReader(file));
                    s2=bufferedReader.readLine();
                    while((s1=bufferedReader.readLine())!=null){
                        s2+=s1+"\n";
                    }
                }
                catch(IOException ex){
                    throw new RuntimeException(ex);
                }
            }
        }
        else if(call=="Print File"){
            try {
                area.print();
            } catch (PrinterException ex) {
                throw new RuntimeException(ex);
            }

        }
    }
}
