package com.epam.jmp.dmytro_hontar.application;

import com.epam.jmp.dmytro_hontar.application.datagenerator.DataGenerator;


public class BankApplication {


    public static void main(String[] args) {
        DataGenerator dg = new DataGenerator();
        dg.generateData();
    }
}
