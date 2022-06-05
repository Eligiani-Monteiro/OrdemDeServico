package com.ifms.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ifms.dto.EquipamentoDTO;
import  com.ifms.entities.Equipamento;
import  com.ifms.repositories.EquipamentoRepository;
import com.ifms.services.exceptions.DataBaseException;
import  com.ifms.services.exceptions.ResourceNotFoundException;

@Service // fla que a classe é um serviço define as regras de negocio
public class EquipamentoService {


	@Autowired
	private EquipamentoRepository repository;// está chamando o repository

	@Transactional(readOnly = true) // indica que o método permite somente leitura
	public List<EquipamentoDTO> findAll() {
		List<Equipamento> list = repository.findAll();
//		List<TecnicoDTO> listDto = new ArrayList<>();

//		for(Tecnico t : list) {S
//			listDto.add(new TecnicoDTO(t));
//		}

//		return listDto;
		return list.stream().map(t -> new EquipamentoDTO(t)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true) //
	public EquipamentoDTO findById(Long id) {
		Optional<Equipamento> obj = repository.findById(id);
		Equipamento entity = obj
				.orElseThrow(() -> new ResourceNotFoundException("A entidade consultada não foi localizada"));
		return new EquipamentoDTO(entity);

	}

	@Transactional // demarca as transações na camada de serviço e faz transações no banco de dados
	public EquipamentoDTO insert(EquipamentoDTO dto) {// cria e salva novos objetos no banco de dados
		Equipamento entity = new Equipamento();
		entity.setEquipamento(dto.getEquipamento());
		entity.setPatrimonio(dto.getPatrimonio());
		
		entity = repository.save(entity);
		return new EquipamentoDTO(entity);

	}

	public EquipamentoDTO update(Long id, EquipamentoDTO dto) {// Vai tentar atualizar se não encontrar o id manda um
																// exeção
		try {
			Equipamento entity = repository.getById(id);
			entity.setEquipamento(dto.getEquipamento());
			entity.setPatrimonio(dto.getPatrimonio());
		
			entity = repository.save(entity);
			return new EquipamentoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O recurso com o ID = " + id + " não foi localizado");
		}

	}

	public void delete(Long id) {// tentar atualizar se não encontrar o id manda uma exeção

		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("O recurso com o ID = " + id + " não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não é possível excluir o registro, pois o mesmo está em uso");
		}

	}

}
