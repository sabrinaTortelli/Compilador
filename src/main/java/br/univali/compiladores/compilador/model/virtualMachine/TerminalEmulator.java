package br.univali.compiladores.compilador.model.virtualMachine;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class TerminalEmulator extends JFrame {

    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea jtxtConsole;

    private final TerminalObserver conListerner;

    public TerminalEmulator() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Terminal Emulator");

        jtxtConsole.addFocusListener(new FocusListener() {
            @Override
            public void focusLost(FocusEvent e) {
            }

            @Override
            public void focusGained(FocusEvent e) {
                jtxtConsole.getCaret().setVisible(true);
            }
        });

        jtxtConsole.getCaret().setVisible(true);
        conListerner = new TerminalObserver(jtxtConsole);
        jtxtConsole.addKeyListener(conListerner);
        this.setVisible(true);
    }
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jtxtConsole = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jtxtConsole.setEditable(false);
        jtxtConsole.setBackground(java.awt.Color.black);
        jtxtConsole.setColumns(150);
        jtxtConsole.setFont(new java.awt.Font("Arial Black", 0, 14)); // NOI18N
        jtxtConsole.setForeground(Color.green);
        jtxtConsole.setRows(50);
        jtxtConsole.setCaretColor(Color.lightGray);
        jtxtConsole.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jtxtConsole);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());

        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 537, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 408, Short.MAX_VALUE))
                                .addContainerGap())
        );

        pack();
    }

    public void print(String str) {
        jtxtConsole.append(str);
        jtxtConsole.setCaretPosition(jtxtConsole.getText().length());
    }

    public void println(String str) {
        print(str + "\n");
    }

    public String read() {
        conListerner.setIsReading(true);

        while (conListerner.isReading()) {
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                ex.printStackTrace();
            }
        }

        String userInput = conListerner.getUserInputBuffer();
        conListerner.emptyUserBuffer();
        return userInput;
    }

}
