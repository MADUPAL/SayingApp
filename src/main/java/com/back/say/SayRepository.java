package com.back.say;

import java.util.List;

public interface SayRepository {
    int save(Say say);
    void update(int id, Say say);
    void delete(int id);
    Say findById(int id);
    List<Say> findAll();
}
