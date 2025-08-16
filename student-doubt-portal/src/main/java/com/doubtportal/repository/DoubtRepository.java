package com.doubtportal.repository;

import com.doubtportal.model.Doubt;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DoubtRepository extends JpaRepository<Doubt, Integer> {
    List<Doubt> findByAskedBy(String askedBy);
}
