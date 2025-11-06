package com.back;

import com.back.say.*;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SayRepository repository = new MyHashMapSayRepository();
        SayService service = new SayService(repository);
        new SayController(service, new Scanner(System.in)).run();
    }
}