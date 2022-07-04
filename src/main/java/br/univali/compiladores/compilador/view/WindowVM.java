//package br.univali.compiladores.compilador.view;
//
//import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;
//
//import javax.swing.*;
//import javax.swing.text.StyledDocument;
//import java.awt.*;
//import java.util.ArrayList;
//
//public class WindowVM extends JFrame {
//
//    private JTextArea textArea;
//    private JTextField textField;
//    private JPanel jpanel;
//    private StyledDocument doc;
//
//    public WindowVM() {
//        super("Virtual Machine");
//        initComponents();
//    }
//
//    private void initComponents() {
//        setWindow();
//        createEditionArea();
//    }
//
//    private void setWindow() {
//        Dimension size = getPreferredSize();
//        size.width = 1000;
//        size.height = 700;
//        setPreferredSize(size);
//        setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
//        setSize(size);
//        jpanel = new JPanel();
//        jpanel.setLayout(new BoxLayout(jpanel, BoxLayout.PAGE_AXIS));
//        jpanel.setBorder(BorderFactory.createEmptyBorder(5,2,10,2));
//        setContentPane(jpanel);
//    }
//
//    private void createEditionArea(){
//        textArea = new JTextArea();
//        textArea.setForeground(Color.yellow);
//        textArea.setBackground(Color.BLACK);
//        textArea.setEditable(false);
//        textArea.setFont(textArea.getFont().deriveFont(16.0f));
//        jpanel.add(textArea);
//    }
//
//    public void setEditable(boolean status){
//        textArea.setEditable(status);
//    }
//
//    public void printText(String s) {
//        try {
//            String textComplete="";
//            if(textArea.getText().equals("")){
//                textComplete = s;
//            } else {
//                textComplete = textArea.getText() + "\n" + s;
//            }
//            textArea.setText(textComplete);
//        }
//        catch(Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
