package com.gauravrmsc.superduperdrive.repository.repositoryservice;

import com.gauravrmsc.superduperdrive.mappers.FilesMapper;
import com.gauravrmsc.superduperdrive.mappers.FilesMapper;
import com.gauravrmsc.superduperdrive.model.File;
import com.gauravrmsc.superduperdrive.model.File;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilesRepositoryService {
  @Autowired
  FilesMapper repository;

  public void addFile(File file, long userId) throws Exception{
    List<File> existingFiles = findByUserId(userId);
    for (File existingFile : existingFiles) {
      if (existingFile.getFilename().equals(file.getFilename())) {
        throw new Exception("File Already Exists");
      }
    }
    repository.insertFile(file, userId);
  }

  public List<File> findByUserId(long userId) {
    return repository.findByUserId(userId);
  }

  public void deleteFile(long fileId, long userId) throws Exception {
    List<File> File = findByUserId(userId);
    for (File file : File) {
      if (file.getFileId() == fileId) {
        repository.deleteFile(fileId);
        return;
      }
    }
    throw new Exception("File Not Found");
  }

//  public void updateFile(File updatedFile, long userId) throws Exception {
//    List<File> File = findByUserId(userId);
//    for (File file : File) {
//      if (file.getFileId() == updatedFile.getFileId()) {
//        repository.updateFile(file);
//        return;
//      }
//    }
//    throw new Exception("file Not Found");
//  }
}
