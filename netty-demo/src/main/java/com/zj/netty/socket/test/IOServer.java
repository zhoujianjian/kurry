package com.zj.netty.socket.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName: IOServer
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/6/5$ 11:39$
 * @Version: 1.0
 */
public class IOServer {

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket  = new ServerSocket(8000);
        Socket server = serverSocket.accept();
        while (true){
            System.out.println("远程主机地址：" + server.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(server.getInputStream());
            System.out.println(in.readUTF());
            DataOutputStream out = new DataOutputStream(server.getOutputStream());
            out.writeUTF("谢谢连接我：" + server.getLocalSocketAddress() + "\nGoodbye!");
        }

        //server.close();

    }
}
