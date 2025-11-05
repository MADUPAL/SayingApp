package com.back.say;

import java.util.List;

public class SayService {
    private final SayRepository sayRepository;

    public SayService(SayRepository sayRepository) {
        this.sayRepository = sayRepository;
    }

    public int save(String author, String content) {
        Say say = new Say(author, content);

        return sayRepository.save(say);
    }

    public Say findById(int id) {
        return sayRepository.findById(id);
    }

    public List<Say> findAll() {
        return sayRepository.findAll();
    }

    public void delete(int id) {
        sayRepository.delete(id);
    }
    public void update(int id, Say say) {
        sayRepository.update(id, say);
    }
}
