package com.example.messagefeed.repos;

import com.example.messagefeed.domain.Massage;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MassageRepo extends CrudRepository<Massage, Long> {
    List<Massage> findByTag(String tag);  //поиск по тэгу
}
