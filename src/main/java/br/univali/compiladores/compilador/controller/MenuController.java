package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.model.Compile.Compile;
import br.univali.compiladores.compilador.view.WindowER;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.text.DefaultEditorKit;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.*;

public class MenuController {

    private final WindowER gui;
    String fileName;
    String fileAddress;
    private boolean respCancel;
    private boolean compiled = false;

    public MenuController(WindowER gui){
        this.gui = gui;
    }

    public void newFile(){
        gui.getTa().setText("");
        gui.getTf().setText("");
        gui.setTitle("Compilador");
        compiled = false;
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
        compiled = false;
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
        compiled = false;
        if(!gui.getTa().getText().equals("")){
            try {
                String text = "";
                text += gui.getTa().getText();
                Compile compile = new Compile(gui);
                compile.runLexicalVerification(text);
            } catch (Exception e){
                System.out.println("Erro na função do compilador!" + e.getMessage());
                compiled = false;
            }
        } else {
            gui.getTf().setText("----------Erro - Arquivo vazio não pode ser compilado!------------");
            gui.getTf().setForeground(Color.red);
            compiled = false;
        }
    }

    public void execute(){
        if(gui.getTa().getText().equals("")){
            gui.getTf().setText("----------Erro - Arquivo vazio não pode ser executado!------------");
            gui.getTf().setForeground(Color.red);
        } else if(!compiled){
            gui.getTf().setText("----------Erro - Arquivo com erro na compilação não pode ser executado!------------");
            gui.getTf().setForeground(Color.red);
        } else {
            new WindowVMController();
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

    public void setCompiled(boolean compiled) {
        this.compiled = compiled;
    }
}
