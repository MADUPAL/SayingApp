package com.back.say.repository;

import com.back.say.domain.Say;
import com.back.say.dto.SayDto;
import com.back.say.utils.Pageable;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

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
            return -1;
        sayMap.put(id, new Say(id, dto.getAuthor(), dto.getContent()));
        return id;
    }

    @Override
    public int delete(int id) {
        if (!sayMap.containsKey(id))
            return -1;
        sayMap.remove(id);
        return id;
    }

    @Override
    public Optional<Say> findById(int id) {
        Say say = sayMap.get(id);
        if(say == null)
            return Optional.empty();
        return Optional.of(say);
    }

    @Override
    public List<Say> findAll() {
        return sayMap.values().stream().toList();
    }

    @Override
    public void build() {

    }

    @Override
    public List<Say> findAllPaged(Pageable pageable) {
        return List.of();
    }

    @Override
    public List<Say> findByAuthorContains(String keyword, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<Say> findByContentContains(String keyword, Pageable pageable) {
        return List.of();
    }

    @Override
    public List<Say> findByAuthorContainsOrContentContains(String keyword, Pageable pageable) {
        return List.of();
    }
}
