package com.ifms.dto;

import java.io.Serializable;
import java.time.Instant;

import com.ifms.entities.OrdemDeServico;
import com.ifms.entities.enums.Prioridade;
import com.ifms.entities.enums.Status;

public class OrdemDeServicoDTO implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String descricaoProblema;
	private String descricaoSolucao;
	private Instant dataCadastro;
	private Status status;
	private Prioridade prioridade;
	
	public OrdemDeServicoDTO() {
	}

	public OrdemDeServicoDTO(Long id, String descricaoProblema, String descricaoSolucao, Instant dataCadastro,
			Status status, Prioridade prioridade) {
		this.id = id;
		this.descricaoProblema = descricaoProblema;
		this.descricaoSolucao = descricaoSolucao;
		this.dataCadastro = dataCadastro;
		this.status = status;
		this.prioridade = prioridade;
	}
	
	public OrdemDeServicoDTO(OrdemDeServico entity) {
		this.id = entity.getId();
		this.descricaoProblema = entity.getDescricaoProblema();
		this.descricaoSolucao= entity.getDescricaoSolucao();
		this.dataCadastro = entity.getDataCadastro();
		this.status = entity.getStatus();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricaoProblema() {
		return descricaoProblema;
	}

	public void setDescricaoProblema(String descricaoProblema) {
		this.descricaoProblema = descricaoProblema;
	}

	public String getDescricaoSolucao() {
		return descricaoSolucao;
	}

	public void setDescricaoSolucao(String descricaoSolucao) {
		this.descricaoSolucao = descricaoSolucao;
	}

	public Instant getDataCadastro() {
		return dataCadastro;
	}

	public void setDataCadastro(Instant dataCadastro) {
		this.dataCadastro = dataCadastro;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Prioridade getPrioridade() {
		return prioridade;
	}

	public void setPrioridade(Prioridade prioridade) {
		this.prioridade = prioridade;
	}
}
