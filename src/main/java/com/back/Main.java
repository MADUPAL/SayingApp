package com.back;

import com.back.say.controller.SayController;
import com.back.say.repository.FileSayRepositoryV1;
import com.back.say.repository.SayRepository;
import com.back.say.service.SayService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        SayRepository repository = new FileSayRepositoryV1();
//        SayRepository repository = new InMemorySayRepository(); // 8단계
        SayService service = new SayService(repository);
        new SayController(service, new Scanner(System.in)).run();
    }
}