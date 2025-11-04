package com.back.say;

import java.util.HashMap;
import java.util.List;

public class MyHashMapSayRepository implements SayRepository {

    private int id;
    private HashMap<Integer, Say> sayMap;

    public MyHashMapSayRepository() {
        id = 0;
        sayMap = new HashMap<>();
    }

    @Override
    public int save(Say say) {
        id++;
        say.setId(id);
        sayMap.put(id, say);

        return id;
    }

    @Override
    public void update(int id, Say say) {
        say.setId(id);
        sayMap.put(id, say);
    }

    @Override
    public void delete(int id) {
        sayMap.remove(id);
    }

    @Override
    public Say findById(int id) {
        if (!sayMap.containsKey(id))
            return null;

        return sayMap.get(id);
    }

    @Override
    public List<Say> findAll() {
        return sayMap.values().stream().toList();

    }
}
