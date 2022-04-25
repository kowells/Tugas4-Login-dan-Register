/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
/**
 *
 * @author ridwa
 */
public class View extends JFrame implements ActionListener {
    
    JLabel llUser, llPw , ldUser, ldPw;
    JButton tombolLogin, tombolDaftar;
    final JTextField flUser, flPw, fdUser, fdPw;
    Connector connector = new Connector();
    
    public View(){
        setTitle("Tugas JDBC");
        setSize(500,300);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        llUser = new JLabel ("Username");
        llPw = new JLabel ("Password");
        ldUser = new JLabel ("Username");
        ldPw = new JLabel ("Password");
        
        tombolLogin = new JButton("Login");
        tombolDaftar = new JButton("Daftar");
        
        flUser = new JTextField();
        flPw = new JTextField();
        fdUser = new JTextField();
        fdPw = new JTextField();
        
        setLayout(null);
        add(llUser);
        add(llPw);
        add(ldUser);
        add(ldPw);
        add(tombolLogin);
        add(tombolDaftar);
        add(flUser);
        add(flPw);
        add(fdUser);
        add(fdPw);
        
        
        llUser.setBounds(30, 50, 60, 20);
        llPw.setBounds(30, 100, 60, 20);
        ldUser.setBounds(200, 50, 60, 20);
        ldPw.setBounds(200, 100, 60, 20);
        
        
        
        flUser.setBounds(30, 70, 100, 20);
        flPw.setBounds(30, 120, 100, 20);
        fdUser.setBounds(200, 70, 100, 20);
        fdPw.setBounds(200, 120, 100, 20);
        
        
        
        tombolLogin.setBounds(50, 150, 70, 20);
        tombolDaftar.setBounds(220, 150, 70, 20);

        tombolLogin.addActionListener(this);
        tombolDaftar.addActionListener(this);
        
        
        
        
    }

    @Override
    public void actionPerformed(ActionEvent e) {
     if(e.getSource() == tombolLogin){ 
            try{
                if(getUsername() == null || getUsername().isEmpty()){
                    throw new IllegalArgumentException("Username is empty");
                }
                if(getPassword() == null || getPassword().isEmpty()){
                     throw new IllegalArgumentException("Password is empty");
                }
                
                String query = "SELECT password FROM users WHERE username = '"+ getUsername() +"'"; 
                connector.statement = connector.koneksi.createStatement();
                ResultSet result = connector.statement.executeQuery(query);
                if(result.next()){
                    String pass = result.getString("password");
                    if(!pass.equals(getPassword())){ 
                        JOptionPane.showMessageDialog(new JFrame(), "Password Salah");
                    }else{
                        JOptionPane.showMessageDialog(new JFrame(), "Berhasil Login");
                    }
                }else{ 
                    JOptionPane.showMessageDialog(new JFrame(), "Username tidak ditemukan");
                }
                connector.statement.close(); 
            }catch(Exception error){
                JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
            }
            
        }
        if(e.getSource() == tombolDaftar){
            try{
                if(getUsernameR() == null || getUsernameR().isEmpty()){
                    throw new IllegalArgumentException("Username is empty");
                }
                if(getPasswordR() == null || getPasswordR().isEmpty()){
                     throw new IllegalArgumentException("Password is empty");
                }
                String query = "SELECT username FROM users WHERE username = '"+getUsernameR()+"'"; 
                connector.statement = connector.koneksi.createStatement();
                ResultSet result = connector.statement.executeQuery(query);

                if(result.next() == false){ 
                    String queryInsert = "INSERT INTO users(username, password) VALUES ('"+getUsernameR()+"','"+getPasswordR()+"')";
                    connector.statement = connector.koneksi.createStatement();
                    connector.statement.executeUpdate(queryInsert);
                    JOptionPane.showMessageDialog(new JFrame(), "Berhasil Mendaftarkan User");
                }else{
                    JOptionPane.showMessageDialog(new JFrame(), "Username sudah digunakan");
                }
                connector.statement.close();
            }catch(Exception error){
                JOptionPane.showMessageDialog(new JFrame(), error.getMessage());
            }
        } 
        
    }
    public String getUsername(){
        return flUser.getText();
    }
    public String getPassword(){
        return flPw.getText();
    }
    public String getUsernameR(){
        return fdUser.getText();
    }
    public String getPasswordR(){
        return fdPw.getText();
    }
    
}


