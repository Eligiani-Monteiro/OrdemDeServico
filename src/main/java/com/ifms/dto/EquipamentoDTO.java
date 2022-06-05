package com.ifms.dto;

import java.io.Serializable;

import com.ifms.entities.Equipamento;

public class EquipamentoDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	private long id;
	private String equipamento;
	private String patrimonio;
	private String setor;

//nesta classe pega os valores e envia po ser um objeto de transferencia de dados  na aplicação
	public EquipamentoDTO(Equipamento t) {

	}

	public EquipamentoDTO(long id, String equipamento, String patrimonio, String setor) {

		this.id = id;
		this.equipamento = equipamento;
		this.patrimonio = patrimonio;
		this.setor = setor;
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

	public String getSetor() {
		return setor;
	}

	public void setSetor(String setor) {
		this.setor = setor;
	}

}
