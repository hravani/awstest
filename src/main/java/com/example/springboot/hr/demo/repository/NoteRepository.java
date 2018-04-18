package com.example.springboot.hr.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.hr.demo.entity.Note;

@Repository
public interface NoteRepository extends JpaRepository<Note, Long>{

}
