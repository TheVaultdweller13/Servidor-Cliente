/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import Observer.Subject;
import java.net.Socket;
import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Arrays;

/**
 *
 * @author dam213
 */
public class EnvioMensajes extends Subject implements Runnable{
    
    private String frase;
    private String error;
    
    private String remoteIP;
    private String remoteFQDN;
    private String remoteHostName;
    private String remotePort;
     
    private String localIP;
    private String localFQDN;
    private String localHostName;
    private String localPort;
    
    private String listen;
    
    private int num1, num2, num3, num4;
    private int cambiarPuerto;
    
    private InformacionSocket socket;

    public String getError() {
        return error;
    }

//    public String getFrase() {
//        return frase;
//    }

    public String getListen() {
        return listen;
    }
    public void setFrase(String frase) {
        this.frase = frase;
    }
    public void setPuerto(int cambiarPuerto){
        this.cambiarPuerto = cambiarPuerto;
    }

    public void setNum1(int num1) {
        this.num1 = num1;
    }

    public void setNum2(int num2) {
        this.num2 = num2;
    }

    public void setNum3(int num3) {
        this.num3 = num3;
    }

    public void setNum4(int num4) {
        this.num4 = num4;
    }

    
    public void run(){
        OutputStream flujoSalida;
        DataOutputStream flujo;
        byte[] ipAddr = new byte[]{(byte) num1, (byte) num2, (byte) num3, (byte) num4};
        
        try {
            InetAddress addr = InetAddress.getByAddress(ipAddr);
            
            Socket socketCliente = new Socket(addr, cambiarPuerto);
            remoteIP=Arrays.toString(socketCliente.getInetAddress().getAddress()); // ip remoto
            remoteFQDN=socketCliente.getInetAddress().getCanonicalHostName(); // FQDN remote
            remoteHostName=socketCliente.getInetAddress().getHostName(); // nombre host remoto
            remotePort=""+socketCliente.getPort(); // puerto remoto
            localIP=Arrays.toString(socketCliente.getLocalAddress().getAddress()); // ip local
            localFQDN=""+socketCliente.getLocalAddress().getCanonicalHostName(); // FQDN local
            localHostName=""+socketCliente.getLocalAddress().getHostName(); // nombre host local
            localPort=""+socketCliente.getLocalPort(); // puerto local
            
            socket = new InformacionSocket(remoteIP, remoteFQDN, remoteHostName, remotePort,
            localIP, localFQDN, localHostName, localPort);
            
            ServerSocket s = new ServerSocket(0);
            listen = ""+s.getLocalPort();
            //s.close();
            
            
            flujoSalida = socketCliente.getOutputStream();
            flujo = new DataOutputStream(flujoSalida);
            //frase = "Hello World - 14FHH";
            flujo.writeInt(frase.length());
            flujo.writeBytes(frase);
            
            socketCliente.close();
            this.notifyObservers();
            
        } catch (UnknownHostException e) {
            error = "Host reference not solved";
                this.notifyObservers();
        } catch (IOException e) {
            error = "Error in the Comunication";
                this.notifyObservers();
        } catch (SecurityException e) {
            error = "Communication not allowed";
                this.notifyObservers();
        }
    }

    public InformacionSocket getSocket() {
        
        return socket;
    }
    

    
}
