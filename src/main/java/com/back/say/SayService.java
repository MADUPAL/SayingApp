package com.back.say;

import java.util.List;

public class SayService {
    private final SayRepository sayRepository;

    public SayService(SayRepository sayRepository) {
        this.sayRepository = sayRepository;
    }

    public int save(String author, String content) {
        Say say = new Say(content, author);

        return sayRepository.save(say);
    }

    public Say findById(int id) {
        return sayRepository.findById(id);
    }

    public void printAll() {
        List<Say> allSays = sayRepository.findAll();
        for (Say say : allSays) {
            System.out.println(say.getId() + " / " + say.getAuthor() + " / " + say.getContent());
        }
    }

    public void delete(int id) {
        sayRepository.delete(id);
    }
    public void update(int id, Say say) {
        sayRepository.update(id, say);
    }
}
