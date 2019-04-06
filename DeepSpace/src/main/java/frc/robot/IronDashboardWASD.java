/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Handles reading sockets from the IronDashboard (on PC side).  
 * </br> </br> 
 * The purpose of this class is to recieve oncoming messages over port
 * 2019 for keyboard input received from the IronDashboard application. 
 *  </br> </br> 
 * P.S Mark Dannemiller made that app so that we can control the robot with WASD.
 * See https://www.youtube.com/watch?v=6G_W54zuadg
 */
public class IronDashboardWASD {

    private static ServerSocket SVRSOCK;
    public static int portNum = 5801; //5800-5810 are the only open ports given to us on the radio
    public static Socket SOCK;

    public static void run() throws Exception {
        SVRSOCK = new ServerSocket(portNum);
        System.out.println(SVRSOCK.getLocalSocketAddress());
    }

    public static String getMessage()
    {
        String message = "";
        try {
            message = read();
        } catch (Exception e) {
            message = "No message!";
        }
        return message;
    }

    /**
     * Reads the oncoming messages from our ServerSocket
     * @return
     * String message from the client side
     * @throws Exception
     */
    private static String read() throws Exception
    {
        if(SOCK == null)
            SOCK = SVRSOCK.accept();
        InputStreamReader IR = new InputStreamReader(SOCK.getInputStream());
        BufferedReader BR = new BufferedReader(IR);

        String MESSAGE = BR.readLine();
        //SOCK.close();
        return MESSAGE;
    }
}
