package org.fleen.cloudedPlain.core;

import java.util.List;

/*
 * a generator generates clouds
 * it may itself employ generators
 */
public interface Generator{
  
  void setPlain(Plain plain);
  
  List<Cloud> generateClouds();

}
