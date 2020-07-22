package com.gauravrmsc.superduperdrive.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class File {
  private long fileId;
  private String filename;
  private String contenttype;
  private String filesize;
  private byte[] filedata;
}
