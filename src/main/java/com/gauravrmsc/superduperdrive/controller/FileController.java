package com.gauravrmsc.superduperdrive.controller;

import com.gauravrmsc.superduperdrive.model.File;
import com.gauravrmsc.superduperdrive.model.Result;
import com.gauravrmsc.superduperdrive.service.FileService;
import java.security.Principal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/files")
public class FileController {

  @Autowired
  FileService fileService;

  @PostMapping("/add")
  public String addFile(@RequestParam("file") MultipartFile inputFile, Model model, Principal principal)
      throws Exception {
    if (inputFile.isEmpty()) {
      Result errorResult = new Result();
      errorResult.setErrorMessage("Not a valid File");
      model.addAttribute("result", errorResult);
      return "result";
    }
    File file = new File();
    file.setContenttype(inputFile.getContentType());
    file.setFilename(inputFile.getOriginalFilename());
    file.setFilesize(inputFile.getSize());
    file.setFiledata(inputFile.getBytes());
    Result result = fileService.addFile(file, principal.getName());
    model.addAttribute("result", result);
    return "result";
  }

  @PostMapping("/delete")
  public String deleteFile(long fileId, Model model, Principal principal) {
    Result result = fileService.deleteFile(fileId, principal.getName());
    model.addAttribute("result", result);
    return "result";
  }

}
