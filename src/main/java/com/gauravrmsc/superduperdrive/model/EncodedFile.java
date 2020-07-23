package com.gauravrmsc.superduperdrive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class EncodedFile {
  private long fileId;
  private String filename;
  private String dataURL;
}
