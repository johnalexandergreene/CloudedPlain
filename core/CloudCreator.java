package org.fleen.cloudedPlain.core;

import java.util.List;

public interface CloudCreator{
  
  void setPlain(Plain plain);
  
  List<Cloud> create();

}
