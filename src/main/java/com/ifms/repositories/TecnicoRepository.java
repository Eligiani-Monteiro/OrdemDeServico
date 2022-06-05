package com.ifms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifms.entities.Tecnico;

@Repository
public interface TecnicoRepository extends JpaRepository<Tecnico, Long> {
	
}
