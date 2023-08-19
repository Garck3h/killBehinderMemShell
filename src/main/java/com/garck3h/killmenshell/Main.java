package com.garck3h.killmenshell;

import java.io.IOException;
import com.sun.tools.attach.AgentInitializationException;
import com.sun.tools.attach.AgentLoadException;
import com.sun.tools.attach.AttachNotSupportedException;
import com.sun.tools.attach.VirtualMachine;


public class Main {
    public static void main(String[] args) throws IOException, AttachNotSupportedException, AgentLoadException, AgentInitializationException {
        //判断用户有没有输入进程pid和目标类
        if (args.length < 2) {
            System.out.println("请提供进程ID和目标类名作为参数！");
            System.out.println("例如：java -Xbootclasspath/a:/usr/lib/jvm/java-1.8.0-openjdk-1.8.0.372.b07-1.el7_9.x86_64/lib/tools.jar -cp killBehinderMemShell-1.0-SNAPSHOT-jar-with-dependencies.jar  110256 javax.servlet.http.HttpServlet\n");
            System.out.println("tools.jsr为自己服务器上的tools.jar的路径\n");
            System.out.println("110256为tomcat运行的pid;（可以使用jps查看：即Bootstrap的pid）\n");
            System.out.println("javax.servlet.http.HttpServlet为重置的目标类\n");
            return;
        }
        System.out.println("killBehinderMemShell_V1.0 By Garck3h");
        String pid = args[0]; // 进程ID
        String targetClass = args[1]; // 目标类名
        System.out.println("开始清除内存马，目标类名：" + targetClass);

        VirtualMachine virtualMachine = VirtualMachine.attach(pid);
        virtualMachine.loadAgent(Main.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "=" + targetClass);
        virtualMachine.detach();
        System.out.println("冰蝎内存马清除成功!!");
    }
}