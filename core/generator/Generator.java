package org.fleen.cloudedPlain.core.generator;

import java.util.List;

import org.fleen.cloudedPlain.core.Plain;
import org.fleen.cloudedPlain.core.cloud.Cloud;

/*
 * a generator generates clouds
 * it may employ more generators
 */
public interface Generator{
  
  void setPlain(Plain plain);
  
  List<Cloud> generate();

}
