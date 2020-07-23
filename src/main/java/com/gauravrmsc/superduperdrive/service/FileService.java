package com.gauravrmsc.superduperdrive.service;

import com.gauravrmsc.superduperdrive.mappers.UserMapper;
import com.gauravrmsc.superduperdrive.model.EncodedFile;
import com.gauravrmsc.superduperdrive.model.File;
import com.gauravrmsc.superduperdrive.model.Result;
import com.gauravrmsc.superduperdrive.repository.entity.UserEntity;
import com.gauravrmsc.superduperdrive.repository.repositoryservice.FilesRepositoryService;
import com.gauravrmsc.superduperdrive.repository.repositoryservice.UserRepositoryService;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

@Service
public class FileService {



  @Autowired
  UserRepositoryService userRepositoryService;

  @Autowired
  FilesRepositoryService filesRepositoryService;
  @Autowired
  UserMapper userMapper;



  public Result addFile(File file, String username) {

    Result result = new Result();
    UserEntity user = userRepositoryService.getUser(username);

    try {
      filesRepositoryService.addFile(file, user.getUserId());
    } catch (Exception e) {
      if (e.getMessage().equals("File Already Exists")) {
        result.setErrorMessage("File Already Exist");
      } else {
        result.setErrorMessage("File too big");
      }

      return result;
    }

    result.setSuccessMessage("File Added Successfully");
    return result;
  }


  public Result deleteFile(long fileId, String username) {
    Result result = new Result();
    UserEntity user = userRepositoryService.getUser(username);
    long userId = user.getUserId();
    try {
      filesRepositoryService.deleteFile(fileId, userId);
      result.setSuccessMessage("File deleted Successfully");
    } catch (Exception e) {
      result.setErrorMessage("File does not exist");
    }
    return result;
  }

  public void addFilesToModel(Model model) throws Exception {
    UserEntity user = (UserEntity) model.getAttribute("user");
    long userId = user.getUserId();
    List<File> files = filesRepositoryService.findByUserId(userId);
    List<EncodedFile> encodedFiles = new ArrayList<>();
    for (File file : files) {
      EncodedFile encodedFile = getEncodedFile(file);
      encodedFiles.add(encodedFile);
    }
    model.addAttribute("files", encodedFiles);
  }

  public EncodedFile getEncodedFile(File file) {
    String base64 = Base64.getEncoder().encodeToString(file.getFiledata());
    String dataURL = "data:" + file.getContenttype() + ";base64," + base64;
    EncodedFile encodedFile = new EncodedFile(file.getFileId(), file.getFilename(),dataURL);
    return encodedFile;
  }
}
