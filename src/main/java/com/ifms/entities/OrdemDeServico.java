package com.ifms.entities;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.ifms.entities.enums.Prioridade;
import com.ifms.entities.enums.Status;

@Entity
@Table(name = "tb_ordemDeServico")
public class OrdemDeServico implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String descricaoProblema;
	private String descricaoSolucao;
	private Instant dataCadastro;
	private Status status;
	private Prioridade prioridade;
	
	@ManyToOne
	@JoinColumn(name="id_tecnico_fk")
	private Tecnico tecnico;

	@ManyToMany
	@JoinTable(name = "tb_equipamento_servico", joinColumns = @JoinColumn(name = "id_ordem"), inverseJoinColumns = @JoinColumn(name = "id_equipamento"))
	Set<Equipamento>equipamentos = new HashSet<>();
	
	
	@Column(columnDefinition ="TIMESTAMP WITHOUT TIME ZONE")
	private Instant createdAt;
	
	@Column(columnDefinition ="TIMESTAMP WITHOUT TIME ZONE")
	private Instant updatedAt;

	public OrdemDeServico() {

	}

	public OrdemDeServico(Long id, String descricaoProblema, String descricaoSolucao, Instant dataCadastro,
			Status status, Prioridade prioridade) {
		this.id = id;
		this.descricaoProblema = descricaoProblema;
		this.descricaoSolucao = descricaoSolucao;
		this.dataCadastro = dataCadastro;
		this.status = status;
		this.prioridade = prioridade;
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
	
	

	public Tecnico getTecnico() {
		return tecnico;
	}

	public void setTecnico(Tecnico tecnico) {
		this.tecnico = tecnico;
	}

	public Set<Equipamento> getEquipamentos() {
		return equipamentos;
	}

	public void setEquipamentos(Set<Equipamento> equipamentos) {
		this.equipamentos = equipamentos;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		OrdemDeServico other = (OrdemDeServico) obj;
		return Objects.equals(id, other.id);
	}

}
