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

    private WindowER gui;
    String fileName;
    String fileAddress;
    private LexicalAnalysis lexicalAnalysis;

    public MenuController(WindowER gui){
        this.gui = gui;
    }

    public void newFile(){
        //verificar se houve edição do arquivo anterior
        gui.getTa().setText("");
        gui.getTf().setText("");
        gui.setTitle("Compilador - Novo arquivo");
        fileName = null;
        fileAddress = null;
    }

    public void openFile(){
        //verificar se houve edição do arquivo anterior
        JFileChooser fc = new JFileChooser();
        FileNameExtensionFilter filterFile = new FileNameExtensionFilter( "Apenas documentos de texto (*.txt)", "txt" );
        fc.setAcceptAllFileFilterUsed(false);
        fc.addChoosableFileFilter(filterFile);

        int respFile = fc.showOpenDialog(gui);
        if(respFile == JFileChooser.APPROVE_OPTION){
            File fileSelected = fc.getSelectedFile();
            fileName = fileSelected.getName();
            gui.setTitle("Compilador - " + fileName);
            fileAddress = fileSelected.getAbsolutePath();
            try {
                BufferedReader br = new BufferedReader(new FileReader(fileAddress));
                gui.getTa().setText("");
                String line = null;
                while((line = br.readLine())!=null){
                    gui.getTa().append(line + "\n");
                }
                br.close();
            } catch (Exception e){
                System.out.println("Arquivo nao pode ser aberto!");
            }
        } else {
            JOptionPane.showMessageDialog(gui, "Nenhum arquivo foi selecionado");
            System.out.println("Nenhum arquivo selecionado!");
        }
    }

    public void save(){

        if(fileName==null){
            saveAs();
        } else {
            try{
                FileWriter fw = new FileWriter(fileAddress + fileName);
                fw.write(gui.getTa().getText());
                gui.setTitle("Compilador - " + fileName);
                fw.close();
            }catch(Exception e){
                System.out.println("Not saved!");
            }
        }
    }

    public void saveAs(){
        FileDialog fd = new FileDialog(gui, "Salvar", FileDialog.SAVE);
        fd.setVisible(true);

        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            gui.setTitle("Compilador - " + fileName);
        }

        try {
            FileWriter fw = new FileWriter(fileAddress + fileName);
            fw.write(gui.getTa().getText());
            fw.close();
        } catch (Exception e){
            System.out.println("Not saved!");
        }
    }

    public void exit(){
        //perguntar se deseja salvar o arquivo antes de fechar
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
        try {
            String text = "\n";
            text += gui.getTa().getText();
            System.out.println(text);
            StringReader textReader = new StringReader(text);
            BufferedReader textToParser = new BufferedReader(new InputStreamReader(new ReaderInputStream(textReader, StandardCharsets.UTF_8)));
            System.out.println(textToParser.readLine());
            lexicalAnalysis = new LexicalAnalysis(gui);
            lexicalAnalysis.runLexicalVerification(textToParser);
        } catch (Exception e){
            System.out.println("Erro na função do compilador!");
        }
    }

    private void verifyEdition(){


        String[] options = {"Sim", "Não", "Cancelar"};
        int optionSelected = JOptionPane.showOptionDialog(gui, "Deseja salvar o arquivo modificado?",  "Salvar arquivo?",
                JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, options, "Sim");
        if(optionSelected == 0){

        } else if(optionSelected == 1){

        } else {

        }


    }

}
