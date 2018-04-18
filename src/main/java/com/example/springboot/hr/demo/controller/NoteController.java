package com.example.springboot.hr.demo.controller;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.List;

import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

import com.example.springboot.hr.demo.entity.Note;
import com.example.springboot.hr.demo.exception.ResourceNotFoundException;
import com.example.springboot.hr.demo.repository.NoteRepository;

@RestController
@RequestMapping("/api")
public class NoteController {
	
	private static final Logger log = LoggerFactory.getLogger(NoteController.class);	
	
	@Autowired
	private NoteRepository noteRepository;

	// Get All Notes
	@GetMapping("/version")
	public String getVersion(Principal principal) {
		log.debug("getVersion() - " + principal.getName());
		return "{\"version\": \"1.0.0\", \"datetime\": \"" + LocalDateTime.now() + "\"}";
	}
	
	// Get All Notes
	@GetMapping("/notes")
	public List<Note> getAllNotes(Principal principal) {
		log.debug("getAllNotes() - " + principal.getName());
	    return noteRepository.findAll();
	}
	
	
	// Create a new Note
	@PostMapping("/notes")
	public Note createNote(Principal principal, @Valid @RequestBody Note note) {
		log.debug("createNote() - " + principal.getName());
	    return noteRepository.save(note);
	}
	
	// Get a Single Note
	@GetMapping("/notes/{id}")
	public Note getNoteById(Principal principal, @PathVariable(value = "id") Long noteId) {
		log.debug("getAllNotes() - noteId: " + noteId + " [" + principal.getName() + "]");
	    return noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));
	}
	
	
	// Update a Note
	@PutMapping("/notes/{id}")
	public Note updateNote(Principal principal, @PathVariable(value = "id") Long noteId,
	                                        @Valid @RequestBody Note noteDetails) {
		log.debug("updateNote() - noteId: " + noteId + " [" + principal.getName() + "]");
	    Note note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	    note.setTitle(noteDetails.getTitle());
	    note.setContent(noteDetails.getContent());

	    Note updatedNote = noteRepository.save(note);
	    return updatedNote;
	}
	
	// Delete a Note
	@DeleteMapping("/notes/{id}")
	public ResponseEntity<?> deleteNote(Principal principal, @PathVariable(value = "id") Long noteId) {
		log.debug("deleteNote() - noteId: " + noteId + " [" + principal.getName() + "]");
	    Note note = noteRepository.findById(noteId)
	            .orElseThrow(() -> new ResourceNotFoundException("Note", "id", noteId));

	    noteRepository.delete(note);

	    return ResponseEntity.ok().build();
	}
	
}
