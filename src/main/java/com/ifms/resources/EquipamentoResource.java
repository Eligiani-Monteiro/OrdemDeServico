package com.ifms.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.ifms.dto.EquipamentoDTO;
import com.ifms.services.EquipamentoService;

@RestController
@RequestMapping(value = "/equipamentos")//indica que a classe é um controller  em que os metodos @RequestMapping assumem a semântica @ResposeBody
//atendendo a API rest(get, put,post, delete)
public class EquipamentoResource {
	
	@Autowired//Essa anotação fornece o controle sobre onde e como a ligação entre os Bens devem ser realizados
	private EquipamentoService service;
	//nesta parte esta chamando o servico
	
	@GetMapping//devolve a lista de equipamentos  consultar o professor
	public ResponseEntity<List<EquipamentoDTO>> findAll(){
		List<EquipamentoDTO> list = service.findAll();
		return ResponseEntity.ok().body(list);
	}

	@GetMapping(value = "/{id}")//Pega  por parametro um id e devolve no corpo da requisição esse objeto
	public ResponseEntity<EquipamentoDTO> findById(@PathVariable Long id){
		EquipamentoDTO dto = service.findById(id);
		return ResponseEntity.ok().body(dto);
	}
	@PostMapping//inserindo um objeto no banco de dados
	public ResponseEntity<EquipamentoDTO> insert(@RequestBody EquipamentoDTO dto){
		dto = service.insert(dto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
											 .path("/{id}")
											 .buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	@PutMapping(value = "/{id}")//Pega por parametro um id inseri novas informações no corpo da requisição e atualiza o banco  de dados
	public ResponseEntity<EquipamentoDTO> update(@PathVariable Long id, @RequestBody EquipamentoDTO dto){
		dto = service.update(id, dto);
		return ResponseEntity.ok().body(dto);
	}
	@DeleteMapping(value = "/{id}")//recebe o id por parametro  e se ele existir o objetoé deletado
	public ResponseEntity<Void> delete(@PathVariable Long id){
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}

