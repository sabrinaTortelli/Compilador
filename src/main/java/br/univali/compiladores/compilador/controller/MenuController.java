package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.model.LexicalAnalysis.LexicalAnalysis;
import br.univali.compiladores.compilador.view.WindowER;
import org.apache.commons.io.input.ReaderInputStream;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;
import java.nio.charset.StandardCharsets;

public class MenuController {

    private final WindowER gui;
    String fileName;
    String fileAddress;
    private boolean respCancel;

    public MenuController(WindowER gui){
        this.gui = gui;
    }

    public void newFile(){
        gui.getTa().setText("");
        gui.getTf().setText("");
        gui.setTitle("Compilador");
        fileName = null;
    }

    public void openFile(){
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filterFile = new FileNameExtensionFilter( "Apenas documentos de texto (*.txt)", "txt" );
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(filterFile);
        fc.setDialogTitle("Abrir arquivo de texto");
        if(fileAddress != null){
            fc.setCurrentDirectory(new File(fileAddress));
        }
        int respFile = fc.showOpenDialog(gui);
        if(respFile == JFileChooser.APPROVE_OPTION) {
            File fileSelected = fc.getSelectedFile();
            fileName = fileSelected.getName();
            gui.setTitle("Compilador - " + fileName);
            fileAddress = fileSelected.getAbsolutePath();
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileAddress));
                gui.getTa().setText("");
                String line;
                while ((line = br.readLine()) != null) {
                    gui.getTa().append(line + "\n");
                }
                gui.getTf().setText("");
                br.close();
            } catch (Exception e) {
                System.out.println("Arquivo nao pode ser aberto!");
            }
        } else if(respFile == JFileChooser.CANCEL_OPTION){
            JOptionPane.showMessageDialog(gui, "Nenhum arquivo foi selecionado!");
        } else {
            JOptionPane.showMessageDialog(gui, "Erro ao selecionar o arquivo!");
        }
    }

    public void save(){
        if(fileName==null){
            saveAs();
        } else {
            try{
                FileWriter fw = new FileWriter(fileAddress);
                fw.write(gui.getTa().getText());
                gui.setTitle("Compilador - " + fileName);
                fw.close();
            }catch(Exception e){
                System.out.println("Arquivo não pode ser salvo!");
            }
        }
    }

    public void saveAs() {
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filterFile = new FileNameExtensionFilter( "Apenas documentos de texto (*.txt)", "txt" );
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(filterFile);
        fc.setDialogTitle("Salvar");
        if(fileAddress != null){
            fc.setCurrentDirectory(new File(fileAddress));
            fc.setSelectedFile(new File(fileName));
        }
        int respFile = fc.showSaveDialog(gui);
        respCancel = false;
        if(respFile == JFileChooser.APPROVE_OPTION) {
            File fileSelected = fc.getSelectedFile();
            if(fileSelected.exists()){
                int selectionOption = JOptionPane.showConfirmDialog(gui, "O arquivo já existe, deseja sobrescrevê-lo?", null, JOptionPane.OK_CANCEL_OPTION);
                if(selectionOption == JOptionPane.OK_OPTION){
                    fileName = fileSelected.getName();
                    gui.setTitle("Compilador - " + fileName);
                    fileAddress = fileSelected.getAbsolutePath();
                    try {
                        FileWriter fw = new FileWriter(fileAddress);
                        fw.write(gui.getTa().getText());
                        gui.setTitle("Compilador - " + fileName);
                        fw.close();
                    } catch (Exception e) {
                        System.out.println("Arquivo nao pode ser salvo!");
                    }
                }
            } else {
                fileAddress = fileSelected.getAbsolutePath();
                fileName = fileSelected.getName();
                if(!fileName.endsWith(".txt")){
                    fileName += ".txt";
                    fileAddress += ".txt";
                }
                try {
                    FileWriter fw = new FileWriter(fileAddress);
                    fw.write(gui.getTa().getText());
                    gui.setTitle("Compilador - " + fileName);
                    fw.close();
                } catch (Exception e) {
                    System.out.println("Arquivo nao pode ser salvo!");
                }
            }
        } else if(respFile == JFileChooser.CANCEL_OPTION){
            respCancel = true;
            JOptionPane.showMessageDialog(gui, "Nenhum arquivo foi selecionado!");
        } else {
            JOptionPane.showMessageDialog(gui, "Erro ao selecionar o arquivo!");
            respCancel = true;
        }
    }

    public void exit(){
        System.exit(0);
    }

    public void cut(ActionEvent e){
        Action cutAction = new DefaultEditorKit.CutAction();
        cutAction.actionPerformed(e);
    }

    public void copy(ActionEvent e){
        Action copyAction = new DefaultEditorKit.CopyAction();
        copyAction.actionPerformed(e);
    }

    public void paste(ActionEvent e){
        Action pasteAction = new DefaultEditorKit.PasteAction();
        pasteAction.actionPerformed(e);
    }

    public void compile(){
        if(!gui.getTa().getText().equals("")){
            try {
                String text = "\n";
                text += gui.getTa().getText();
                System.out.println(text);
                StringReader textReader = new StringReader(text);
                BufferedReader textToParser = new BufferedReader(new InputStreamReader(new ReaderInputStream(textReader, StandardCharsets.UTF_8)));
                System.out.println(textToParser.readLine());
                LexicalAnalysis lexicalAnalysis = new LexicalAnalysis(gui);
                lexicalAnalysis.runLexicalVerification(textToParser);
            } catch (Exception e){
                System.out.println("Erro na função do compilador!");
            }
        } else {
            gui.getTf().setText("----------Erro - Arquivo vazio não pode ser compilado!------------");
            gui.getTf().setForeground(Color.red);
        }

    }

    public void verifyEdition(String action){
        boolean changed = gui.isChangedDocument();
        if(changed){
            String[] options = {"Sim", "Não", "Cancelar"};
            int optionSelected = JOptionPane.showOptionDialog(gui, "Deseja salvar o arquivo modificado?",  "Arquivo modificado",
                    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Sim");
            if(optionSelected == JOptionPane.YES_OPTION){
                save();
                if(!respCancel){
                    chooseAction(action);
                }
            } else if(optionSelected == JOptionPane.NO_OPTION){
                chooseAction(action);
            }
        }else {
            chooseAction(action);
        }
    }

    public void chooseAction(String action){
        switch (action){
            case "New":
                newFile();
                gui.setChangedDocument(false);
                break;
            case "Open":
                openFile();
                gui.setChangedDocument(false);
                break;
            case "Exit":
                exit();
                break;
            default:
                System.out.println(action);
                break;
        }
    }
}
