package com.back.say.service;

import com.back.say.domain.Say;
import com.back.say.dto.ResponseSayDto;
import com.back.say.dto.SayDto;
import com.back.say.exception.SayNotFoundException;
import com.back.say.repository.SayRepository;

import java.util.List;
import java.util.Optional;

public class SayService {
    private final SayRepository sayRepository;

    public SayService(SayRepository sayRepository) {
        this.sayRepository = sayRepository;
    }

    public int create(SayDto dto) {
        if (dto.getAuthor() == null || dto.getAuthor().isBlank())
            throw new IllegalArgumentException("작가는 비어있을 수 없습니다.");
        if (dto.getContent() == null || dto.getContent().isBlank())
            throw new IllegalArgumentException("명언은 비어있을 수 없습니다.");

        return sayRepository.create(dto);
    }

    public ResponseSayDto findById(int id) {
        Optional<Say> result = sayRepository.findById(id);
        if (result.isEmpty())
            throw new SayNotFoundException(id);
        Say say = result.get();
        return new ResponseSayDto(say.getId(), say.getAuthor(), say.getContent());
    }

    public List<ResponseSayDto> findAll() {
        return sayRepository.findAll().stream()
                .map(say->new ResponseSayDto(say.getId(), say.getAuthor(), say.getContent()))
                .toList();
    }

    public int delete(int id) {
        int deletedId = sayRepository.delete(id);
        if (deletedId == -1)
            throw new SayNotFoundException(id);
        return deletedId;
    }

    public int update(int id, SayDto dto) {
        int updatedId = sayRepository.update(id, dto);
        if (updatedId == -1)
            throw new SayNotFoundException(id);
        return updatedId;
    }

    public void build() {
        sayRepository.build();
    }

    public List<Say> findAllPaged(int page, int size) {
        int offset = (page-1) * size;
        return sayRepository.findAllPaged(offset, size); //offset부터 size개만큼
    }

    public List<Say> findByAuthorContains(String keyword) {
        return sayRepository.findByAuthorContains(keyword);
    }
    public List<Say> findByContentContains(String keyword) {
        return sayRepository.findByContentContains(keyword);
    }
}
