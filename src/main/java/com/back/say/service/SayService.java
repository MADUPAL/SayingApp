package com.back.say.service;

import com.back.say.domain.Say;
import com.back.say.dto.ResponseSayDto;
import com.back.say.dto.SayDto;
import com.back.say.exception.SayNotFoundException;
import com.back.say.repository.SayRepository;
import com.back.say.utils.Pageable;

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

    public List<ResponseSayDto> findAllPaged(Pageable pageable) {
//        int offset = (page-1) * size;
        List<Say> allPaged = sayRepository.findAllPaged(pageable);// offset부터 size개만큼
        return toDtoList(allPaged);
    }

/*    public List<ResponseSayDto> findByAuthorContains(String keyword, Pageable pageable) {
        List<Say> byAuthorContains = sayRepository.findByAuthorContains(keyword, pageable);
        return toDtoList(byAuthorContains);

    }
    public List<ResponseSayDto> findByContentContains(String keyword, Pageable pageable) {
        List<Say> byContentContains = sayRepository.findByContentContains(keyword, pageable);
        return toDtoList(byContentContains);
    }
    public List<ResponseSayDto> findByAuthorContainsOrContentContains(String keyword, Pageable pageable) {
        List<Say> byAuthorOrContentContains = sayRepository.findByAuthorContainsOrContentContains(keyword, pageable);
        return toDtoList(byAuthorOrContentContains);
    }*/

    public List<ResponseSayDto> getList(String keywordType, String keyword, Pageable pageable) {
        boolean noKeyword = keyword.isBlank();
        // 키워드 없음: all
        if ("all".equals(keywordType) && noKeyword) {
            if (pageable.getPageNo() == 1) {
                // 기본: 최신 5개
                return findRecentTop5();
            } else {
                // 2페이지 이상: 전체 페이징
                return findAllPaged(pageable);
            }
        }

        // 키워드 있음: 타입별로 분기
        return switch (keywordType) {
            case "author" -> toDtoList(
                    sayRepository.findByAuthorContains(keyword, pageable)
            );
            case "content" -> toDtoList(
                    sayRepository.findByContentContains(keyword, pageable)
            );
            case "all" -> toDtoList(
                    sayRepository.findByAuthorContainsOrContentContains(keyword, pageable)
            );
            default -> List.of(); // 잘못된 keywordType
        };
    }
    private List<ResponseSayDto> findRecentTop5() {
        return findAll();
    }

    private List<ResponseSayDto> toDtoList(List<Say> sayList) {
        return sayList.stream()
                .map(say -> new ResponseSayDto(say.getId(), say.getAuthor(), say.getContent()))
                .toList();
    }
}
