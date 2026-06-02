package com.example.beautysalon.repository;

import com.example.beautysalon.entity.BeautyService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BeautyServiceRepository extends JpaRepository<BeautyService, Long> {
}