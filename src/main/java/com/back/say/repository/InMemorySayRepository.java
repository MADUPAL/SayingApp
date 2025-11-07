package com.back.say.repository;

import com.back.say.domain.Say;
import com.back.say.dto.SayDto;
import com.back.say.exception.SayNotFoundException;

import java.util.HashMap;
import java.util.List;

public class InMemorySayRepository implements SayRepository {

    private int id;
    private final HashMap<Integer, Say> sayMap;

    public InMemorySayRepository() {
        id = 0;
        sayMap = new HashMap<>();
    }

    @Override
    public int create(SayDto dto) {
        id++;
        sayMap.put(id, new Say(id, dto.getAuthor(), dto.getContent()));

        return id;
    }

    @Override
    public int update(int id, SayDto dto) {
        if (!sayMap.containsKey(id))
            throw new SayNotFoundException(id);
        Say updated = sayMap.put(id, new Say(id, dto.getAuthor(), dto.getContent()));
        return updated.getId();
    }

    @Override
    public int delete(int id) {
        if (!sayMap.containsKey(id))
            throw new SayNotFoundException(id);
        Say removed = sayMap.remove(id);
        return removed.getId();
    }

    @Override
    public Say findById(int id) {
        Say say = sayMap.get(id);
        if (say == null) {
            throw new SayNotFoundException(id);
        }
        return say;
    }

    @Override
    public List<Say> findAll() {
        return sayMap.values().stream().toList();

    }
}
