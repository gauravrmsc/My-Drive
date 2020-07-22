package com.gauravrmsc.superduperdrive.mappers;

import com.gauravrmsc.superduperdrive.model.Notes;
import java.util.List;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface NotesMapper {

  @Select("SELECT * FROM NOTES")
  List<Notes> findAll();

  @Select("SELECT * FROM NOTES WHERE noteId = #{noteId}")
  Notes findOne(long noteId);

  @Select("SELECT * FROM NOTES WHERE userId = #{userId}")
  List<Notes> findByUserId(long userId);

  @Insert(
      "INSERT INTO NOTES (notetitle, notedescription, userId) VALUES (#{note.notetitle}, #{note.notedescription}, #{userId})")
  int insertNote(Notes note, long userId);

  @Delete("DELETE FROM NOTES WHERE noteId = #{noteId}")
  int deleteNote(long noteId);

  @Update(
      "UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteId = #{noteId}")
  int updateNote(Notes note);

}
