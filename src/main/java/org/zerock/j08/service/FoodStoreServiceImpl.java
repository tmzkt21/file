package org.zerock.j08.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.zerock.j08.dto.FoodStoreDTO;
import org.zerock.j08.dto.ListFoodStoreDTO;
import org.zerock.j08.entity.FoodStore;
import org.zerock.j08.repository.FoodStoreRepository;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class FoodStoreServiceImpl implements FoodStoreService{

    private final FoodStoreRepository storeRepository;

    @Override
    public Long register(FoodStoreDTO storeDTO) {
        FoodStore result = storeRepository.save(dtoToEntity(storeDTO));
        return null;
    }

    @Override
    public List<ListFoodStoreDTO> getList() {
        Pageable pageable = PageRequest.of(0,10, Sort.by("fno").descending());
        Page<Object[]> result = storeRepository.getList(pageable);

        result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());
        return result.getContent().stream().map(arr -> arrToDTO(arr)).collect(Collectors.toList());
    }

}
