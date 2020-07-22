package com.gauravrmsc.superduperdrive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ResponseFile {
  private int fileid;
  private String filename;
  private String dataURL;
}
