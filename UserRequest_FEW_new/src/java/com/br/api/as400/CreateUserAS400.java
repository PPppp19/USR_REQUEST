/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.br.api.as400;

/**
 *
 * @author Phongsathon
 */
import com.ibm.as400.access.AS400;
import com.ibm.as400.access.AS400Message;
import com.ibm.as400.access.AS400SecurityException;
import com.ibm.as400.access.CommandCall;
import com.ibm.as400.access.ErrorCompletingRequestException;
import com.ibm.as400.access.ObjectDoesNotExistException;
import com.ibm.as400.access.RequestNotSupportedException;
import com.ibm.as400.access.UserList;
import java.awt.HeadlessException;
import java.beans.PropertyVetoException;
import java.io.IOException;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import javax.swing.JOptionPane;


public class CreateUserAS400  {
    
 
    public static void CREATE_USER(String username, String password) throws Exception {

        String HOST = "192.200.9.190";
        String Uname = username;
        String Pword = password;

        String Command = "CRTUSRPRF USRPRF(" + username + ") PASSWORD(" + password + ") GRPPRF(BRDBUSR)";
//        String Command = "CHGUSRPRF USRPRF(SITTIS_SEE) TEXT('BRUSRPRF ICT System MGR')";
        AS400 as400 = new AS400(HOST, "MVXSECOFR", "lawson@90");
        System.out.println("Connected !!");

        CommandCall cmd = new CommandCall(as400);

        try {

            if (cmd.run(Command)) {
                System.out.println(Command + " Run success");
            } else {
                System.out.println(Command + " Run faild");
            }

            AS400Message[] msg400 = cmd.getMessageList();
            if (msg400.length > 0) {
                System.out.println(" , messages from the command: ");
                System.out.println(" ");
                for (int i = 0; i < 1; i++) {
                    System.out.println(msg400[i].getID());

                    System.out.println(" : ");
                    System.out.println(msg400[i].getText());

                }
            }
                   
        } catch (Exception e) {
            System.out.println(e.toString());

        }  
    }
    
    
//     public static void SetExpUser() throws IOException, PropertyVetoException {
//
//        try {
//
//            String HOST = "192.200.9.190";
//            String MVXSECOFR = "MVXSECOFR";
//            String PASSWORD = "lawson@90";
//            String username2 = txt_username.getText().trim().toUpperCase();
//            String Command = "CHGUSRPRF USRPRF(" + username2 + ")  PASSWORD(" + username2 + ")";
//
//            Set<String> as400Users = new HashSet();
//            AS400 as400 = new AS400(HOST, MVXSECOFR, PASSWORD);
//            UserList users = new UserList(as400);
//            Enumeration io = users.getUsers();
//            CommandCall cmd = new CommandCall(as400);
//
//            while (io.hasMoreElements()) {
//                com.ibm.as400.access.User u = (com.ibm.as400.access.User) io.nextElement();
//                if (u.getName().toString().trim().equals(username2)) {
//                    if (cmd.run(Command)) {
//
//                        u.setPasswordSetExpire(true);
//                        System.out.println(Command + " Run success");
//                        JOptionPane.showMessageDialog(null, "Complete");
//                        break;
//                    } else {
//                        JOptionPane.showMessageDialog(null, "Error command");
//                        break;
//                    }
//                }
//            }
//            as400.disconnectService(AS400.COMMAND);
//            as400.disconnectService(AS400.SIGNON);
//        } catch (AS400SecurityException | ErrorCompletingRequestException | ObjectDoesNotExistException | RequestNotSupportedException | HeadlessException | PropertyVetoException | IOException | InterruptedException ex) {
//            System.out.println(ex.toString());
//        }
//    }

}
