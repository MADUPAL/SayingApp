package com.back;

import com.back.say.MyHashMapSayRepository;
import com.back.say.Say;
import com.back.say.SayRepository;
import com.back.say.SayService;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SayService mySayService = new SayService(new MyHashMapSayRepository());

        System.out.println("== 명언 앱 ==");

        while (true) {
            System.out.print("명령) ");
            String command = sc.nextLine();
            if (command.equals("종료")) {
                break;
            } else if (command.equals("등록")) {
                System.out.print("명언 : ");
                String author = sc.nextLine();

                System.out.print("작가 : ");
                String say = sc.nextLine();

                int savedId = mySayService.save(author, say);
                System.out.println(savedId + "번 명언이 등록되었습니다.");

            } else if (command.equals("목록")) {
                System.out.println("번호 / 작가 / 명언");
                System.out.println("=====================");
                mySayService.printAll();

            } else if(command.contains("삭제")) {

                int id = Integer.parseInt(command.substring(command.indexOf("=")+1));
                Say result = mySayService.findById(id);
                if (result == null) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                    continue;
                }
                mySayService.delete(id);
                System.out.println(id + "번 명언이 삭제되었습니다.");

            } else if (command.contains("수정")) {
                int id = Integer.parseInt(command.substring(command.indexOf("=")+1));
                Say result = mySayService.findById(id);
                if (result == null) {
                    System.out.println(id + "번 명언은 존재하지 않습니다.");
                    continue;
                }
                System.out.println("명언(기존) : " + result.getContent());
                System.out.print("명언 : ");
                String updateContent = sc.nextLine();

                System.out.println("작가(기존) : " + result.getAuthor());
                System.out.print("작가 : ");
                String updateAuthor = sc.nextLine();

                mySayService.update(id, new Say(updateAuthor, updateContent));
                System.out.println(id + "번 명언이 수정되었습니다.");
            }
        }
        sc.close();
    }
}