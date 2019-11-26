package modelUDP;

import java.io.File;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.SourceDataLine;

public class ThreadAudioUDPServer extends Thread {

	    private SourceDataLine sLine;
	    private AudioFormat audioFormat;
	    private AudioInputStream audioInputStream=null;
	    private String host="localhost";
	    private int port=5000;
	    private DatagramSocket server;
	    private DatagramPacket packet;
	    private long startTime;
	    private long endTime=System.nanoTime();;
	    private long elapsed=System.nanoTime();;
	    private double sleepTime;
	    private long sleepTimeMillis;
	    private int sleepTimeNanos, epsilon;

	    public ThreadAudioUDPServer(String host, int port, int index) {      
	        this.host=host;
	        this.port=port;
	        init(index);
	    }

	    public void init(int index) {
	        File file = new File("./data/tema.wav");
	        File fileC = new File("./data/comercial.wav");
	        try {
	        	if(index==1) {  
	        		audioInputStream=AudioSystem.getAudioInputStream(file);
	        	}
	        	else {
	        		audioInputStream=AudioSystem.getAudioInputStream(fileC);	
	        	}

	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        
	        audioFormat = audioInputStream.getFormat();
	        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
	        System.out.println(info);

	        try {
	            server = new DatagramSocket();
	            System.out.println("Server started");

	        } catch (SocketException e) {
	            e.printStackTrace();
	        }       
	    }

	    public void run() {
	        try {
	        	
	            byte bytes[] =  new byte[4096];
	            int bytesRead=0;
	            sleepTime=(1024/audioFormat.getSampleRate());
	            epsilon=400000;
	            sleepTimeMillis=(long)(sleepTime*1000);
	            sleepTimeNanos=(int)((sleepTime*1000-sleepTimeMillis)*1000000);
	            System.out.println("Sleep time :"+sleepTimeMillis+" ms, "+sleepTimeNanos+" ns");

	            while ((bytesRead=audioInputStream.read(bytes, 0, bytes.length))!= -1) {
	            	
	                try {                   
	                    packet = new DatagramPacket(bytes, bytes.length, InetAddress.getByName(host), port);
	                    packet.setData(bytes);
	                    server.send(packet);                    
	                    packet.setLength(bytes.length);  
	                    Thread.sleep(sleepTimeMillis,sleepTimeNanos);                   
	                } catch (IOException e) {
	                    e.printStackTrace();
	                }
	            }                      
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        sLine.close();

	    }

	}