
package fr.bird.bloom.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;

class StreamGobbler extends Thread{
	
    InputStream is;
    String type;
    OutputStream os;
    
    StreamGobbler(InputStream is, String type){
        this(is, type, null);
    }
    
    StreamGobbler(InputStream is, String type, OutputStream redirect){
        this.is = is;
        this.type = type;
        this.os = redirect;
    }
    
    public void run(){
        try{
            PrintWriter pw = null;
            if (os != null){
                pw = new PrintWriter(os);
            }
            InputStreamReader isr = new InputStreamReader(is);
            BufferedReader br = new BufferedReader(isr);
            String line=null;                        
            while ( (line = br.readLine()) != null){
                if (pw != null){
                	//System.out.println(line);
                	pw.println(line);
                }
                
                System.out.println(type + ">" + line);    
            }
            if (pw != null){
            	pw.flush();
            }
        } catch (IOException ioe){
        	ioe.printStackTrace();  
        }
    }
}
