package com.back.say.service;

import com.back.say.domain.Say;
import com.back.say.dto.SayDto;
import com.back.say.repository.SayRepository;

import java.util.List;

public class SayService {
    private final SayRepository sayRepository;

    public SayService(SayRepository sayRepository) {
        this.sayRepository = sayRepository;
    }

    public int save(SayDto dto) {
        if (dto.getAuthor() == null || dto.getAuthor().isBlank())
            throw new IllegalArgumentException("작가는 비어있을 수 없습니다.");
        if (dto.getContent() == null || dto.getContent().isBlank())
            throw new IllegalArgumentException("명언은 비어있을 수 없습니다.");

        return sayRepository.create(dto);
    }

    public Say findById(int id) {
        return sayRepository.findById(id);
    }

    public List<Say> findAll() {
        return sayRepository.findAll();
    }

    public int delete(int id) {
        return sayRepository.delete(id);
    }
    public int update(int id, SayDto dto) {
        return sayRepository.update(id, dto);
    }
}
