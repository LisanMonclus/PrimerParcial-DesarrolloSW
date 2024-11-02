package com.example.Primer.Parcial.repository;

import com.example.Primer.Parcial.DnaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DnaRepository extends JpaRepository<DnaEntity, Long> {
    Optional<DnaEntity> findByDnaSequence(String dnaSequence);
    boolean existsByDnaSequence(String dnaSequence); // Cambiado de 'existsByDna' a 'existsByDnaSequence'
    long countByIsMutantTrue();
    long countByIsMutantFalse();
}