package com.gauravrmsc.superduperdrive.service;

import com.gauravrmsc.superduperdrive.mappers.UserMapper;
import com.gauravrmsc.superduperdrive.model.Notes;
import com.gauravrmsc.superduperdrive.model.Result;
import com.gauravrmsc.superduperdrive.repository.entity.UserEntity;
import com.gauravrmsc.superduperdrive.repository.repositoryservice.NotesRepositoryService;
import com.gauravrmsc.superduperdrive.repository.repositoryservice.UserRepositoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class NotesService {



  @Autowired
  UserRepositoryService userRepositoryService;

  @Autowired
  NotesRepositoryService notesRepositoryService;
  @Autowired
  UserMapper userMapper;



  public Result addORUpdateNote(Notes note, String username) throws Exception {

    Result result = new Result();
    UserEntity user = userRepositoryService.getUser(username);

    if (note.getNoteId() != -1) {
      try {

        notesRepositoryService.updateNote(note, user.getUserId());
        result.setSuccessMessage("Details Updated Successfully");
        return result;
      } catch (Exception e) {
        result.setErrorMessage("Specified Details does not exist");
        return result;
      }

    }
    notesRepositoryService.addNote(note, user.getUserId());
    result.setSuccessMessage("Note Added Successfully");
    return result;
  }

  public void addNotesToModel(Model model) throws Exception {
    UserEntity user = (UserEntity) model.getAttribute("user");
    long userId = user.getUserId();
    List<Notes> notes = notesRepositoryService.findByUserId(userId);
    model.addAttribute("notes", notes);
  }

  public Result deleteNote(long noteId, String username) {
    Result result = new Result();
    UserEntity user = userRepositoryService.getUser(username);
    long userId = user.getUserId();
    try {
      notesRepositoryService.deleteNote(noteId, userId);
      result.setSuccessMessage("Note deleted Successfully");
    } catch (Exception e) {
      result.setErrorMessage("Note does not exist");
    }
    return result;
  }

}

