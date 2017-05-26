package org.fleen.cloudedPlain.core;

import java.util.ArrayList;
import java.util.List;

/*
 * abstract clouded plain
 */
public class CloudedPlain{
  
  List<Cloud> clouds=new ArrayList<Cloud>();
  
  Plain plain=new Plain();
  
  void addCloud(Cloud cloud){
    cloud.setPlain(plain);
    clouds.add(cloud);}
  
  

}
