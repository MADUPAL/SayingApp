package com.back;

import com.back.say.controller.SayController;
import com.back.say.repository.InMemorySayRepository;
import com.back.say.repository.SayRepository;
import com.back.say.service.SayService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SayRepository repository = new InMemorySayRepository();
        SayService service = new SayService(repository);
        new SayController(service, new Scanner(System.in)).run();
    }
}