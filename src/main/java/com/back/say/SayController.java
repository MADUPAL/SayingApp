package com.back.say;

import java.util.List;
import java.util.Scanner;

public class SayController {
    private final SayService sayService;
    private final Scanner sc;

    public SayController(SayService sayService, Scanner sc) {
        this.sayService = sayService;
        this.sc = sc;
    }

    public void run() {
        System.out.println("== 명언 앱 ==");
        while (true) {
            System.out.print("명령) ");
            String cmd = sc.nextLine().trim();

            if (cmd.equals("종료"))
                break;

            handle(cmd);
        }
    }

    private void handle(String cmd) {
        if (cmd.equals("등록"))
            handleCreate();
        else if (cmd.equals("목록"))
            handleList();
        else if (cmd.startsWith("삭제?id="))
            handleDelete(parseId(cmd));
        else if (cmd.startsWith("수정?id="))
            handleUpdate(parseId(cmd));
        else
            System.out.println("잘못된 명령입니다.");
    }

    private void handleCreate() {
        System.out.print("명언 : ");
        String content = sc.nextLine().trim();

        System.out.print("작가 : ");
        String author = sc.nextLine().trim();

        int savedId = sayService.save(author, content);
        System.out.println(savedId + "번 명언이 등록되었습니다.");
    }

    private void handleList() {
        System.out.println("번호 / 작가 / 명언");
        System.out.println("=====================");
        List<Say> allSays = sayService.findAll();
        for (Say say : allSays) {
            System.out.println(say);
        }
    }

    private void handleDelete(int id) {
        Say result = sayService.findById(id);
        if (result == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        sayService.delete(id);
        System.out.println(id + "번 명언이 삭제되었습니다.");
    }

    private void handleUpdate(int id) {
        Say result = sayService.findById(id);
        if (result == null) {
            System.out.println(id + "번 명언은 존재하지 않습니다.");
            return;
        }
        System.out.println("명언(기존) : " + result.getContent());
        System.out.print("명언 : ");
        String updateContent = sc.nextLine();

        System.out.println("작가(기존) : " + result.getAuthor());
        System.out.print("작가 : ");
        String updateAuthor = sc.nextLine();

        sayService.update(id, new Say(updateAuthor, updateContent));
        System.out.println(id + "번 명언이 수정되었습니다.");
    }

    private static int parseId(String command) {
        return Integer.parseInt(command.substring(command.indexOf("=")+1));
    }
}
