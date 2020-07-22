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

  @Select("SELECT * FROM FILES WHERE fileid = #{fileid}")
  File findOne(int fileid);

  @Select("SELECT * FROM FILES WHERE userid = #{userid}")
  List<File> findByUserId(int userid);

  @Insert(
      "INSERT INTO FILES (filename, contenttype, filesize, filedata, userid) VALUES (#{file.filename}, #{file.contenttype}, #{file.filesize}, #{file.filedata}, #{userid})")
  int insertFile(File file, int userid);

  @Delete("DELETE FROM FILES WHERE fileid = #{fileid}")
  int deleteFile(int fileid);

}
