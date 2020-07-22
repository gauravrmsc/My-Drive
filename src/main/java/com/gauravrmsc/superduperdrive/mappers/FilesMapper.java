package com.gauravrmsc.superduperdrive.mappers;

import com.gauravrmsc.superduperdrive.model.File;
import java.util.List;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface FilesMapper {

  @Select("SELECT * FROM FILES")
  List<File> findAll();

  @Select("SELECT * FROM FILES WHERE fileId = #{fileId}")
  File findOne(long fileId);

  @Select("SELECT * FROM FILES WHERE userId = #{userId}")
  List<File> findByUserId(long userId);

  @Insert(
      "INSERT INTO FILES (filename, contenttype, filesize, filedata, userId) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userId})")
  int insertFile(File file, long userId);

  @Delete("DELETE FROM FILES WHERE fileId = #{fileId}")
  int deleteFile(long fileId);

}
