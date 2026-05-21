package com.matilescano.helpdesk.repository;

import com.matilescano.helpdesk.model.BoardColumn;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface BoardColumnRepository extends JpaRepository<BoardColumn, Integer> {
    List<BoardColumn> findByBoardIdBoardOrderByPosicion(Integer idBoard);
}