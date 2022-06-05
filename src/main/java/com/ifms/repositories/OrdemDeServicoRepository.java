package com.ifms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifms.entities.OrdemDeServico;

@Repository
public interface OrdemDeServicoRepository extends JpaRepository<OrdemDeServico,Long> {

}
