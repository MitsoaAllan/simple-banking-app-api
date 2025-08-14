package com.mitsoa.banking.repository;

import java.util.List;

public interface CrudRepostory<T> {
    List<T> saveAll(List<T> list);

    List<T> findAll(Integer page, Integer size);


}
