package com.gauravrmsc.superduperdrive.repository.repositoryservice;

import com.gauravrmsc.superduperdrive.mappers.NotesMapper;
import com.gauravrmsc.superduperdrive.model.Notes;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotesRepositoryService {
  @Autowired
  NotesMapper repository;

  public void addNote(Notes note, long userId) {
    repository.insertNote(note, userId);
  }

  public List<Notes> findByUserId(long userId) {
    return repository.findByUserId(userId);
  }

  public void deleteNote(long noteId, long userId) throws Exception {
    List<Notes> notes = findByUserId(userId);
    for (Notes note : notes) {
      if (note.getNoteId() == noteId) {
        repository.deleteNote(noteId);
        return;
      }
    }
    throw new Exception("Notes Not Found");
  }

  public void updateNote(Notes updatedNote, long userId) throws Exception {
    List<Notes> notes = findByUserId(userId);
    for (Notes note : notes) {
      if (note.getNoteId() == updatedNote.getNoteId()) {
        repository.updateNote(note);
        return;
      }
    }
    throw new Exception("Note Not Found");
  }
}
