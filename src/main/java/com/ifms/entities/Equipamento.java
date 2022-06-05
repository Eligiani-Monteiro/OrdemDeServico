package com.ifms.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "tb_equipamento") // essa anotação indica que o nome da tabela no banco de dados
public class Equipamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // Essa anotação gera o id automaticamente
	private long id;
	private String equipamento;
	private String patrimonio;
	@ManyToOne
	@JoinColumn(name="id_setor_fk")
	private Setor setor;
	
	
//	@Column(columnDefinition ="TIMESTAMP WITHOUT TIME ZONE")
//	private Instant createdAt;
//	
//	@Column(columnDefinition ="TIMESTAMP WITHOUT TIME ZONE")
//	private Instant updatedAt;

	// construtor vazio
	public Equipamento() {

	}

	public Equipamento(long id, String equipamento, String patrimonio) {
		this.id = id;
		this.equipamento = equipamento;
		this.patrimonio = patrimonio;
		
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getEquipamento() {
		return equipamento;
	}

	public void setEquipamento(String equipamento) {
		this.equipamento = equipamento;
	}

	public String getPatrimonio() {
		return patrimonio;
	}

	public void setPatrimonio(String patrimonio) {
		this.patrimonio = patrimonio;
	}

	public Setor getSetor() {
		return setor;
	}

	public void setSetor(Setor setor) {
		this.setor = setor;
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
		Equipamento other = (Equipamento) obj;
		return id == other.id;
	}

}
