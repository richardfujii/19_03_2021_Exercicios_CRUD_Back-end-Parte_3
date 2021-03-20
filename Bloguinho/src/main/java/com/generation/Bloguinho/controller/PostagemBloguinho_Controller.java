package com.generation.Bloguinho.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.generation.Bloguinho.model.PostagemBloguinho;
import com.generation.Bloguinho.repository.PostagemBloguinho_Repository;

@RestController
@RequestMapping("/postBloguinho")
@CrossOrigin("*")
public class PostagemBloguinho_Controller {
	
	@Autowired
	private PostagemBloguinho_Repository repository;
	
	@GetMapping
	public ResponseEntity<List<PostagemBloguinho>> FindAllPostagemBloguinho(){
		return ResponseEntity.ok(repository.findAll());
	}
	
	@GetMapping("/{idd}")
	public ResponseEntity<PostagemBloguinho> findByIDPostagemBloguinho(@PathVariable long idd){
		return repository.findById(idd).map(answer -> ResponseEntity.ok(answer)).orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/title/{title}")
	public ResponseEntity<List<PostagemBloguinho>> getByTitulo(@PathVariable String title){
		return ResponseEntity.ok(repository.findByTitle(title));
	}
	
	@PostMapping
	public ResponseEntity<PostagemBloguinho> post (@RequestBody PostagemBloguinho postagem){
		return ResponseEntity.status(HttpStatus.CREATED).body(repository.save(postagem));
	}
	
	@PutMapping
	public ResponseEntity<PostagemBloguinho> put (@RequestBody PostagemBloguinho postagem){
		return ResponseEntity.status(HttpStatus.OK).body(repository.save(postagem));
	}
	
	@DeleteMapping("/{idd}")
	public void delete(@PathVariable long idd) {
		repository.deleteById(idd);
	}
	

}