package com.back.say.repository;

import com.back.say.domain.Say;
import com.back.say.dto.SayDto;
import com.back.say.utils.Pageable;

import java.util.List;
import java.util.Optional;

public interface SayRepository {
    int create(SayDto dto);
    int update(int id, SayDto dto);
    int delete(int id);
    Optional<Say> findById(int id);
    List<Say> findAll();
    void build();
    List<Say> findAllPaged(Pageable pageable);
    List<Say> findByAuthorContains(String keyword, Pageable pageable);
    List<Say> findByContentContains(String keyword, Pageable pageable);
    List<Say> findByAuthorContainsOrContentContains(String keyword, Pageable pageable);
}
