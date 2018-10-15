package org.fleen.cloudedPlain.core.exportAudio;

import java.io.ByteArrayInputStream;
import java.io.File;

import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;

import org.fleen.cloudedPlain.core.CloudedPlain;

public class AudioExporter0 implements AudioExporter{
  
  /*
   * ################################
   * CLOUDED PLAIN
   * ################################
   */
  
  CloudedPlain cloudedplain;
  
  public void setCloudedPlain(CloudedPlain cp){
    cloudedplain=cp;}
  
  public CloudedPlain getCloudedPlain(){
    return cloudedplain;}
  
  /*
   * ################################
   * EXPORT
   * ################################
   */
  
  public void exportAudio(int[] audio,File workingdirectory){
    //convert int array to byte array
    //2 bytes per sound tick. so max sound integer value is 65536
    final byte[] soundbytes=new byte[audio.length*2];
    for(int i=0;i<audio.length;i++){
      soundbytes[i*2]=(byte)audio[i];
      soundbytes[i*2+1]=(byte)(audio[i]>>>8);}
    //output the file
    File out=new File(workingdirectory.getAbsolutePath()+"/audio.wav");
    boolean bigEndian=false;
    boolean signed=true;
    int bits=16;
    int channels=1;
    AudioFormat format=
      new AudioFormat((float)getCloudedPlain().getAudioSampleRatePerSecond(),
        bits,channels,signed,bigEndian);
    ByteArrayInputStream bais=new ByteArrayInputStream(soundbytes);
    AudioInputStream audioInputStream;
    audioInputStream=new AudioInputStream(bais,format,audio.length);
    try{
      AudioSystem.write(audioInputStream,AudioFileFormat.Type.WAVE,out);
      audioInputStream.close();
    }catch(Exception x){
      x.printStackTrace();}}

}
