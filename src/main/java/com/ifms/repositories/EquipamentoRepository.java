package com.ifms.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ifms.entities.Equipamento;

@Repository// indica que é um repositório e é uma porta de acesso a outras camadas
//e retorna o objeto
public interface EquipamentoRepository extends JpaRepository<Equipamento, Long>{

}
