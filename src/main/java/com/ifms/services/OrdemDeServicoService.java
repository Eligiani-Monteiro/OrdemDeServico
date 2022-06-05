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

import com.ifms.dto.OrdemDeServicoDTO;
import com.ifms.entities.OrdemDeServico;
import com.ifms.repositories.OrdemDeServicoRepository;
import com.ifms.services.exceptions.DataBaseException;
import com.ifms.services.exceptions.ResourceNotFoundException;

@Service
public class OrdemDeServicoService {

	@Autowired
	private OrdemDeServicoRepository repository;

	@Transactional(readOnly = true)
	public List<OrdemDeServicoDTO> findAll() {
		List<OrdemDeServico> list = repository.findAll();
//		List<OrdemDeServicoDTO> listDto = new ArrayList<>();

//		for(OrdemDeServico t : list) {
//			listDto.add(new OrdemDeServicoDTO(t));
//		}

//		return listDto;
		return list.stream().map(t -> new OrdemDeServicoDTO(t)).collect(Collectors.toList());
	}

	@Transactional(readOnly = true)
	public OrdemDeServicoDTO findById(Long id) {
		Optional<OrdemDeServico> obj = repository.findById(id);
		OrdemDeServico entity = obj
				.orElseThrow(() -> new ResourceNotFoundException("A entidade consultada não foi localizada"));
		return new OrdemDeServicoDTO(entity);
	}

	@Transactional
	public OrdemDeServicoDTO insert(OrdemDeServicoDTO dto) {
		OrdemDeServico entity = new OrdemDeServico();
		entity.setDescricaoProblema(dto.getDescricaoProblema());
		entity.setDescricaoSolucao(dto.getDescricaoSolucao());
		entity.setDataCadastro(dto.getDataCadastro());
		entity.setStatus(dto.getStatus());
		entity.setPrioridade(dto.getPrioridade());
		entity = repository.save(entity);
		return new OrdemDeServicoDTO(entity);
	}

	@Transactional
	public OrdemDeServicoDTO update(Long id, OrdemDeServicoDTO dto) {
		try {
			OrdemDeServico entity = repository.getById(id);
			entity.setDescricaoProblema(dto.getDescricaoProblema());
			entity.setDescricaoSolucao(dto.getDescricaoSolucao());
			entity.setDataCadastro(dto.getDataCadastro());
			entity.setStatus(dto.getStatus());
			entity.setPrioridade(dto.getPrioridade());
			entity = repository.save(entity);
			return new OrdemDeServicoDTO(entity);
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
