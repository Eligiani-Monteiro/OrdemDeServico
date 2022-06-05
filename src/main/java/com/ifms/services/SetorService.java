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

import com.ifms.dto.SetorDTO;
import com.ifms.dto.TecnicoDTO;
import com.ifms.entities.Setor;
import com.ifms.entities.Tecnico;
import com.ifms.repositories.SetorRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class SetorService {

	@Autowired
	private SetorRepository repository;

	@Transactional(readOnly = true)
	public List<SetorDTO> findAll() {
		List<Setor> list = repository.findAll();
//		List<SetorDTO> listDto = new ArrayList<>();

//		for(Setor t : list) {
//			listDto.add(new SetorDTO(t));
//		}

//		return listDto;
		return list.stream().map(t -> new SetorDTO(t)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public SetorDTO findById(Long id) {
		Optional<Setor> obj = repository.findById(id);
		Setor entity = obj.orElseThrow(() -> new ResourceNotFoundException("A entidade consultada não foi localizada"));
		return new SetorDTO(entity);
	}

	@Transactional
	public SetorDTO insert(SetorDTO dto) {
		Setor entity = new Setor();
		entity.setSigla(dto.getSigla());
		entity.setNome(dto.getNome());
		entity.setEmail(dto.getEmail());
		entity.setTelefone(dto.getTelefone());
		entity.setCoordenador(dto.getCoordenador());
		entity = repository.save(entity);
		return new SetorDTO(entity);
	}

	@Transactional
	public SetorDTO update(Long id, SetorDTO dto) {
		try {
			Setor entity = repository.getById(id);
			entity.setSigla(dto.getSigla());
			entity.setNome(dto.getNome());
			entity.setEmail(dto.getEmail());
			entity.setTelefone(dto.getTelefone());
			entity.setCoordenador(dto.getCoordenador());
			entity = repository.save(entity);
			return new SetorDTO(entity);
		} catch (EntityNotFoundException e) {
			throw new ResourceNotFoundException("O recurso com o ID = " + id + " não foi localizado");
		}
	}

	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("O recurso com o ID = " + id + " não foi localizado");
		} catch (DataIntegrityViolationException e) {
			throw new DataBaseException("Não é possível excluir o registro, pois o mesmo está em uso");
		}
	}

}
