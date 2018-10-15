package org.fleen.cloudedPlain.core.exportAudio;

import java.io.File;

import org.fleen.cloudedPlain.core.CloudedPlain;

public interface AudioExporter{
  
  void setCloudedPlain(CloudedPlain cp);
  
  CloudedPlain getCloudedPlain();
  
  /*
   * assemble the audioframes arrays into a single big sound array
   * convert the int[] to a WAV or whatever and write it
   */
  void exportAudio(int[] audio,File workingdirectory);

}
