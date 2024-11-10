package com.alisievich.common.service;

import lombok.AllArgsConstructor;
import org.apache.commons.collections4.IterableUtils;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

@AllArgsConstructor
public class CrudService<T, ID, ResponseT> {
    CrudRepository<T, ID> repository;
    GenericMapper<T, ResponseT> responseMapper;

    public List<ResponseT> getAll() {
        return IterableUtils.toList(repository.findAll())
                .stream()
                .map(responseMapper::map)
                .toList();
    }
}
