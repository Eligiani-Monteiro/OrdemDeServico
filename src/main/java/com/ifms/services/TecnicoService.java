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

import com.ifms.dto.TecnicoDTO;
import com.ifms.entities.Tecnico;
import com.ifms.repositories.TecnicoRepository;
import  com.ifms.services.exceptions.DataBaseException;
import  com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class TecnicoService {
	
	@Autowired
	private TecnicoRepository repository;

	@Transactional(readOnly = true)
	public List<TecnicoDTO> findAll(){
		List<Tecnico> list = repository.findAll();
//		List<TecnicoDTO> listDto = new ArrayList<>();
		
//		for(Tecnico t : list) {
//			listDto.add(new TecnicoDTO(t));
//		}
		
//		return listDto;
		return list.stream().map(t -> new TecnicoDTO(t)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public TecnicoDTO findById(Long id) {
		Optional<Tecnico> obj = repository.findById(id);
		Tecnico entity = obj.orElseThrow(() -> new ResourceNotFoundException(
				                "A entidade consultada não foi localizada"));
		return new TecnicoDTO(entity);
	}

	@Transactional
	public TecnicoDTO insert(TecnicoDTO dto) {
		Tecnico entity = new Tecnico();
		entity.setNome(dto.getNome());
		entity.setTelefone(dto.getTelefone());
		entity.setEmail(dto.getEmail());
		entity.setSenha(dto.getSenha());
		entity = repository.save(entity);
		return new TecnicoDTO(entity);
	}

	@Transactional
	public TecnicoDTO update(Long id, TecnicoDTO dto) {
		try {
			Tecnico entity = repository.getById(id);
			entity.setNome(dto.getNome());
			entity.setTelefone(dto.getTelefone());
			entity.setEmail(dto.getEmail());
			entity.setSenha(dto.getSenha());
			entity = repository.save(entity);
			return new TecnicoDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O recurso com o ID = "+id+" não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("O recurso com o ID = "+id+" não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não é possível excluir o registro, pois o mesmo está em uso");
		}
	}
}




 