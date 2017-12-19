/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

/**
 *
 * @author dam213
 */
import Observer.Subject;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.util.Arrays;

public class RecepcionMensajes extends Subject implements Runnable{
    private String listen, comunicationType, mensajeRecibido;
    private InformacionSocket socket;
    
    private String remoteIP;
    private String remoteFQDN;
    private String remoteHostName;
    private String remotePort;
     
    private String localIP;
    private String localFQDN;
    private String localHostName;
    private String localPort;

    public String getMensajeRecibido() {
        return mensajeRecibido;
    }
    
    @Override
    public void run() {
        byte[] mensaje;
        InputStream flujoLectura;
        DataInputStream flujo;
        while(true){
            try {
                
                EscucharPuerto();

                try (ServerSocket socketServidor = new ServerSocket(8000)) {
                    Socket comunicaCliente = socketServidor.accept();
                    
                    remoteIP=Arrays.toString(comunicaCliente.getInetAddress().getAddress()); // ip remoto
                    remoteFQDN=comunicaCliente.getInetAddress().getCanonicalHostName(); // FQDN remote
                    remoteHostName=comunicaCliente.getInetAddress().getHostName(); // nombre host remoto
                    remotePort=""+comunicaCliente.getPort(); // puerto remoto
                    localIP=Arrays.toString(comunicaCliente.getLocalAddress().getAddress()); // ip local
                    localFQDN=""+comunicaCliente.getLocalAddress().getCanonicalHostName(); // FQDN local
                    localHostName=""+comunicaCliente.getLocalAddress().getHostName(); // nombre host local
                    localPort=""+comunicaCliente.getLocalPort(); // puerto local
                    
                    comunicationType = "Comunication established";
                    
                    socket = new InformacionSocket(remoteIP, remoteFQDN, remoteHostName, remotePort, localIP,
                            localFQDN, localHostName, localPort,listen, comunicationType);
                    
                    
                    flujoLectura = comunicaCliente.getInputStream();
                    flujo = new DataInputStream(flujoLectura);
                    int i = flujo.readInt();
                    mensaje = new byte[i];
                    flujo.readFully(mensaje);
                    mensajeRecibido = new String(mensaje);
                    comunicaCliente.close();
                }

                notifyObservers();
            } catch (IOException e) {
                comunicationType = "Comunication error";
                //System.exit(0);
                this.notifyObservers();
            } catch (SecurityException e) {
                comunicationType = "Communication not allowed for security reasons";
                //System.exit(0);
                this.notifyObservers();
            }
        }
    }

    public void cerrarSocket(){
        
    }
    private void EscucharPuerto() throws IOException {
        ServerSocket s = new ServerSocket(0);
        listen = "Listening on port: " + s.getLocalPort();
        s.close();
    }

    public InformacionSocket getSocket() {
        return socket;
    }
}
