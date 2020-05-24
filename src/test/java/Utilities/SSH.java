package Utilities;

import com.jcraft.jsch.*;

public class SSH {
    private int lport;
    private String lhost;
    private String rhost;
    private int rport;
    private String user;
    private String password;
    private Session session;
    private JSch jsch;

    public Session getSession() {
        return session;
    }

    public void setSession(Session session) {
        this.session = session;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public JSch getJsch() {
        return jsch;
    }

    public void setJsch(JSch jsch) {
        this.jsch = jsch;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public int getLport() {
        return lport;
    }

    public void setLport(int lport) {
        this.lport = lport;
    }

    public String getLhost() {
        return lhost;
    }

    public void setLhost(String lhost) {
        this.lhost = lhost;
    }

    public String getRhost() {
        return rhost;
    }

    public void setRhost(String rhost) {
        this.rhost = rhost;
    }

    public int getRport() {
        return rport;
    }

    public void setRport(int rport) {
        this.rport = rport;
    }

    private static volatile SSH instance = null;

    public static SSH getInstance(){
        if (instance == null) {
            synchronized (JsonOP.class) {
                if (instance == null) {
                    instance = new SSH();
                }
            }
        }

        return instance;
    }

    public SSH() {
        setJsch(new JSch());
        //http://www.jcraft.com/jsch/examples/PortForwardingL.java
    }

    public void setValues(int lport, String lhost, String rhost, int rport) {
        this.lport = lport;
        this.lhost = lhost;
        this.rhost = rhost;
        this.rport = rport;

    }

    public void connect(String userName, String password){
        try {
            setSession(getJsch().getSession(userName, lhost, 22));
            getSession().setConfig("StrictHostKeyChecking", "no");
            getSession().setPassword(password);
            getSession().connect();
            getSession().setPortForwardingL(lport, rhost, rport);
        } catch (JSchException e) {
            e.printStackTrace();
        }
    }

    public void disConnect(){
        if(getSession()!=null){
            getSession().disconnect();
        }
    }

}
