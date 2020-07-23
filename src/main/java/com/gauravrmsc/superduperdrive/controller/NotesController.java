package com.gauravrmsc.superduperdrive.controller;

import com.gauravrmsc.superduperdrive.model.Notes;
import com.gauravrmsc.superduperdrive.model.Notes;
import com.gauravrmsc.superduperdrive.model.Result;
import com.gauravrmsc.superduperdrive.service.NotesService;
import com.gauravrmsc.superduperdrive.service.NotesService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/notes")
public class NotesController {

  @Autowired
  NotesService notesService;

  @PostMapping("/add")
  public String addNote(Notes note, Model model, Principal principal)
      throws Exception {
    Result result = notesService.addORUpdateNote(note, principal.getName());
    model.addAttribute("result", result);
    return "result";
  }

  @PostMapping("/delete")
  public String deleteNote(long noteId, Model model, Principal principal) {
    Result result = notesService.deleteNote(noteId, principal.getName());
    model.addAttribute("result", result);
    return "result";
  }

}
