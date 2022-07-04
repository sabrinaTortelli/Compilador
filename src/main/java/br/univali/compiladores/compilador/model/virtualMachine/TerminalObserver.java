package br.univali.compiladores.compilador.model.virtualMachine;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;


public class TerminalObserver implements KeyListener {

    private final JTextArea jtxtConsole;
    private boolean isReading;
    private String userInputBuffer;

    public TerminalObserver(JTextArea jtxtConsole) {
        this.jtxtConsole = jtxtConsole;
        userInputBuffer = "";
        isReading = false; //user só escreve quando estiver true
    }

    public String getUserInputBuffer() {
        return userInputBuffer;
    }

    public void emptyUserBuffer() {
        userInputBuffer = "";
    }
    public boolean isReading() {
        return isReading;
    }

    public void setIsReading(boolean reading) {
        isReading = reading;
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //youtube indian guys
        if (!isReading) {
            return;
        }
        char key = e.getKeyChar();
        //pegando o input do usuário em uma string
        if (key != '\b' && key != '\n') {
            jtxtConsole.append("" + key);
            userInputBuffer += key;
            return;
        }
        //backspace
        if (key == '\b'){
            try {
                String str = jtxtConsole.getText();
                jtxtConsole.setText(str.substring(0, str.length() - 1));
                userInputBuffer = userInputBuffer.substring(0, userInputBuffer.length() - 1);
                return;
            }catch (Exception exception){
                System.out.println("Buffer vazio");
                exception.printStackTrace();
            }
        }
        //enter
        if (key == '\n') {
            jtxtConsole.append("\n");
            synchronized (this) {
                isReading = false;
            }
            return;
        }
        else {
            jtxtConsole.append("" + key);
            userInputBuffer += key;
            return;
        }
    }


    @Override
    public void keyPressed(KeyEvent e) {
        //veio da interface, deixar vazio
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //veio da interface, deixar vazio
    }

}

