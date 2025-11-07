package com.back.say.repository;

import com.back.say.domain.Say;
import com.back.say.dto.SayDto;

import java.util.List;

public interface SayRepository {
    int create(SayDto dto);
    int update(int id, SayDto dto);
    int delete(int id);
    Say findById(int id);
    List<Say> findAll();
}
