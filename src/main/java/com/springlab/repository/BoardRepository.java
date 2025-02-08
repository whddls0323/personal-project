package com.springlab.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.springlab.domain.Board;

@Repository
public interface BoardRepository extends CrudRepository<Board, Long> {

}
