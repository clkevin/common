package com.liukevin.util.string;

import com.sun.tools.javac.Main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.lang.reflect.Method;

/**
 * Created by liukai on 2017/9/14.
 */
public class StringCodeExcute {
    public static void main(String[] args) {
        int i = 10;
        String code = "System.out.println(\"Hello World!\"+(13+2*5/3));";
        code += "for(int i=0;i<" + i + ";i++){";
        code += " System.out.println(Math.pow(i,2));";
        code += "}";
        try {
            run(code);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private synchronized static File compile(String code) throws Exception {

        File file = File.createTempFile("JavaRuntime", ".java", new File(System.getProperty("user.dir")));
        file.deleteOnExit();
        //      获得类名
        String classname = getBaseFileName(file);
        //      将代码输出到文件
        PrintWriter out = new PrintWriter(new FileOutputStream(file));
        out.println(getClassCode(code, classname));
        out.close();
        //      编译生成的java文件
        String[] cpargs = new String[] { "-d",
                System.getProperty("user.dir") + "/target/classes",
                file.getName() };
        int status = Main.compile(cpargs);
        if (status != 0) {
            throw new Exception("语法错误！");
        }
        return file;
    }
    private static synchronized void run(String code) throws Exception {
        String classname = getBaseFileName(compile(code));
        new File(System.getProperty("user.dir")
                + "\\WebRoot\\WEB-INF\\classes\\" + classname + ".class")
                .deleteOnExit();
        try {
            Class cls = Class.forName(classname);
            Method main = cls.getMethod("method", null);
            main.invoke(cls, null);
        } catch (Exception se) {
            se.printStackTrace();
        }
    }
    private static String getClassCode(String code, String className) {
        StringBuffer text = new StringBuffer();
        text.append("public class " + className + "{\n");
        text.append(" public static void method(){\n");
        text.append(" " + code + "\n");
        text.append(" }\n");
        text.append("}");
        return text.toString();
    }
    private static String getBaseFileName(File file) {
        String fileName = file.getName();
        int index = fileName.indexOf(".");
        String result = "";
        if (index != -1) {
            result = fileName.substring(0, index);
        } else {
            result = fileName;
        }
        return result;
    }
}
