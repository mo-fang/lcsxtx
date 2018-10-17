package com.mo.fang.springcloudsystem.system.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.util.ResourceUtils;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class ApplicationStartupListener  implements ApplicationListener<ContextRefreshedEvent> {

        @Override
        public void onApplicationEvent(ContextRefreshedEvent arg0) {
            Process p = null;
            boolean flag = true;
            try {
                p = Runtime.getRuntime().exec("cmd  /c   tasklist ");
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                InputStream os = p.getInputStream();
                byte b[] = new byte[256];
                while (os.read(b) > 0)
                    baos.write(b);
                String s = baos.toString();
                if (s.indexOf("redis-server.exe") >= 0) {
                    flag = false;
                } else {
                    System.out.println("redis not RUNNING");
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
            try {
                File file = ResourceUtils.getFile("classpath:bat/startRedis.bat");
                String bat = file.getPath();
                String runCMD = "cmd /c start "+ bat;
                if(flag){
                    Process ps = Runtime.getRuntime().exec(runCMD);
                    System.out.print("redis  RUN SUCCESS~~");
                }
            } catch(IOException ioe) {
                System.out.print("redis RUN FAIL~~~");
                ioe.printStackTrace();
            }

        }

    }
