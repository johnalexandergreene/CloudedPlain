package org.fleen.cloudedPlain.core.exportAudio;

import java.io.File;
import java.util.List;

public interface AudioExporter{
  
  /*
   * assemble the audioframes arrays into a single big sound array
   * convert the int[] to a WAV or whatever and write it
   */
  void exportAudio(List<int[]> audioframes,File workingdirectory);

}
