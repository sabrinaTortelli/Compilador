package br.univali.compiladores.compilador.controller;

import br.univali.compiladores.compilador.model.LexicalAnalysis.LexicalAnalysis;
import br.univali.compiladores.compilador.view.WindowER;
import org.apache.commons.io.input.ReaderInputStream;


import javax.swing.*;
import javax.swing.filechooser.FileFilter;
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
    //FileFilter filter = new FileNameExtensionFilter( "Documentos de texto (*.txt)", "txt" );

    public MenuController(WindowER gui){
        this.gui = gui;
    }

    public void newFile(){
        gui.getTa().setText("");
        gui.getTf().setText("");
        gui.setTitle("Compilador - Novo arquivo");
        fileName = null;
        fileAddress = null;
    }

    public void openFile(){
        FileDialog fd = new FileDialog(gui, "Abrir arquivo", FileDialog.LOAD);
        fd.setVisible(true);

        if(fd.getFile()!=null){
            fileName = fd.getFile();
            fileAddress = fd.getDirectory();
            fd.getFilenameFilter();
            gui.setTitle("Compilador - " + fileName);
        }

        try {
            BufferedReader br = new BufferedReader(new FileReader(fileAddress + fileName));
            gui.getTa().setText("");
            String line = null;
            while((line = br.readLine())!=null){
                gui.getTa().append(line + "\n");
            }
            br.close();
        } catch (Exception e){
            System.out.println("File not opened!");
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
            System.out.println("Erro no compilador!");
        }
    }

}
