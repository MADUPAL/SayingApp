package com.back;

import com.back.say.controller.SayController;
import com.back.say.repository.FileSayRepositoryV1;
import com.back.say.repository.FileSayRepositoryV2;
import com.back.say.repository.InMemorySayRepository;
import com.back.say.repository.SayRepository;
import com.back.say.service.SayService;

import java.util.List;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) {
        SayRepository repository = new FileSayRepositoryV2();   // 10단계
//        SayRepository repository = new FileSayRepositoryV1();   // 9단계
//        SayRepository repository = new InMemorySayRepository(); // 8단계
        SayService service = new SayService(repository);
        new SayController(service, new Scanner(System.in)).run();
    }
}