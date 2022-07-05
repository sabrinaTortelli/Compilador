package br.univali.compiladores.compilador.model.virtualMachine;

import br.univali.compiladores.compilador.model.Compile.HelpInstructionTable;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        System.out.println("Hello world!");
        ArrayList<HelpInstructionTable> driver = new ArrayList<>();
        int count = 0;

        HelpInstructionTable helper01 = new HelpInstructionTable(1,"ALR",1);
        driver.add(helper01);

        HelpInstructionTable helper02 = new HelpInstructionTable(2,"LDR",25.00);
        driver.add(helper02);

        HelpInstructionTable helper03 = new HelpInstructionTable(3,"STC",1);
        driver.add(helper03);

        HelpInstructionTable helper04 = new HelpInstructionTable(4,"ALI",2);
        driver.add(helper04);

        HelpInstructionTable helper05 = new HelpInstructionTable(5,"ALI",1);
        driver.add(helper05);

        HelpInstructionTable helper06 = new HelpInstructionTable(6,"REA",1);
        driver.add(helper06);

        HelpInstructionTable helper07 = new HelpInstructionTable(7,"STR",2);
        driver.add(helper07);

        HelpInstructionTable helper08 = new HelpInstructionTable(8,"REA",1);
        driver.add(helper08);

        HelpInstructionTable helper09 = new HelpInstructionTable(9,"STR",3);
        driver.add(helper09);

        HelpInstructionTable helper10 = new HelpInstructionTable(10,"LDV",2);
        driver.add(helper10);

        HelpInstructionTable helper11 = new HelpInstructionTable(11,"LDV",3);
        driver.add(helper11);

        HelpInstructionTable helper12 = new HelpInstructionTable(12,"ADD",0);
        driver.add(helper12);

        HelpInstructionTable helper13 = new HelpInstructionTable(13,"STR",4);
        driver.add(helper13);

        HelpInstructionTable helper14 = new HelpInstructionTable(14,"LDS","Soma=");
        driver.add(helper14);

        HelpInstructionTable helper15 = new HelpInstructionTable(15,"WRT",0);
        driver.add(helper15);

        HelpInstructionTable helper16 = new HelpInstructionTable(16,"LDS","Soma");
        driver.add(helper16);

        HelpInstructionTable helper17 = new HelpInstructionTable(17,"WRT",0);
        driver.add(helper17);

        HelpInstructionTable helper18 = new HelpInstructionTable(18,"LDV",4);
        driver.add(helper18);

        HelpInstructionTable helper19 = new HelpInstructionTable(19,"WRT",0);
        driver.add(helper19);

        HelpInstructionTable helper20 = new HelpInstructionTable(20,"STP",0);
        driver.add(helper20);

        VirtualMachine vm = new VirtualMachine(driver);
        vm.executeCode();

    }
    public static void vmTeste(ArrayList<HelpInstructionTable> command){
        ArrayList<HelpInstructionTable> driver = command;
        VirtualMachine vm = new VirtualMachine(driver);
        vm.executeWithMaxPriority();

    }
}