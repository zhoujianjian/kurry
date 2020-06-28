package com.zj.netty.socket.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @ClassName: Client
 * @Description: 描述
 * @Author: zhoujian
 * @Date: 2020/6/5$ 14:52$
 * @Version: 1.0
 */
public class Client {

    public static void main(String[] args) throws Exception{
        Socket client = new Socket("localhost",8000);
        System.out.println("远程主机地址：" + client.getRemoteSocketAddress());
        OutputStream outToServer = client.getOutputStream();
        DataOutputStream out = new DataOutputStream(outToServer);

        out.writeUTF("Hello from " + client.getLocalSocketAddress());
        InputStream inFromServer = client.getInputStream();
        DataInputStream in = new DataInputStream(inFromServer);
        System.out.println("服务器响应： " + in.readUTF());
        client.close();
    }
}
