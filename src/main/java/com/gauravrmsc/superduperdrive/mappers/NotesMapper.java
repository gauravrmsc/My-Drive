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

  @Select("SELECT * FROM NOTES WHERE noteid = #{noteid}")
  Notes findOne(int noteid);

  @Select("SELECT * FROM NOTES WHERE userid = #{userid}")
  List<Notes> findByUserId(int userid);

  @Insert(
      "INSERT INTO NOTES (notetitle, notedescription, userid) VALUES (#{note.notetitle}, #{note.notedescription}, #{userid})")
  int insertNote(Notes note, int userid);

  @Delete("DELETE FROM NOTES WHERE noteid = #{noteid}")
  int deleteNote(int noteid);

  @Update(
      "UPDATE NOTES SET notetitle = #{notetitle}, notedescription = #{notedescription} WHERE noteid = #{noteid}")
  int updateNote(Notes note);

}
