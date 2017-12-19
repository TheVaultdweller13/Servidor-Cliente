/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Package;

import java.util.Calendar;
import java.util.GregorianCalendar;

/**
 *
 * @author dam213
 */
public class InformacionSocket {
    
     private String remoteIP;
     private String remoteFQDN;
     private String remoteHostName;
     private String remotePort;
     
     private String localIP;
     private String localFQDN;
     private String localHostName;
     private String localPort;
     
     private String listen, comunicationType;


    
    

    public InformacionSocket(String remoteIP, String remoteFQDN, String remoteHostName, String remotePort, 
            String localIP, String localFQDN, String localHostName, String localPort, String listen, String comunicationType) {
        this.remoteIP = remoteIP;
        this.remoteFQDN = remoteFQDN;
        this.remoteHostName = remoteHostName;
        this.remotePort = remotePort;
        this.localIP = localIP;
        this.localFQDN = localFQDN;
        this.localHostName = localHostName;
        this.localPort = localPort;
        this.listen = listen;
        this.comunicationType = comunicationType;
    }
    public InformacionSocket(String remoteIP, String remoteFQDN, String remoteHostName, 
            String remotePort, String localIP, String localFQDN, 
            String localHostName, String localPort) {
        
        this.remoteIP = remoteIP;
        this.remoteFQDN = remoteFQDN;
        this.remoteHostName = remoteHostName;
        this.remotePort = remotePort;
        this.localIP = localIP;
        this.localFQDN = localFQDN;
        this.localHostName = localHostName;
        this.localPort = localPort;
    }

    public String getListen() {
        return listen;
    }

    public String getComunicationType() {
        return comunicationType;
    }
    
    
    
    public String getRemoteIP() {
        return remoteIP;
    }

    public String getRemoteFQDN() {
        return remoteFQDN;
    }

    public String getRemoteHostName() {
        return remoteHostName;
    }

    public String getRemotePort() {
        return remotePort;
    }

    public String getLocalIP() {
        return localIP;
    }

    public String getLocalFQDN() {
        return localFQDN;
    }

    public String getLocalHostName() {
        return localHostName;
    }
    
    public String getLocalPort() {
        return localPort;
    }
    
}
