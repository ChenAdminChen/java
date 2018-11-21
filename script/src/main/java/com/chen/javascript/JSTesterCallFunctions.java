package com.chen.javascript;


import javax.script.*;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by chen on 2017/7/4.
 *
 */
public class JSTesterCallFunctions {
    public static void main(String[] args) {

        ScriptEngineManager sem = new ScriptEngineManager();

        //ScriptEngine engine = sem.getEngineByName("javascript");     //python or jython,
        ScriptEngine engine = sem.getEngineByName("js");     //python or jython,

        //简单实现 javascript中的方法

        //向javascript中传送 参数
        engine.put("a", 4);
        engine.put("b", 7);

        try {

            //获得java 向javaScript中传递参数  并通过Bindings获得参数
            Bindings bindings = engine.getBindings(ScriptContext.ENGINE_SCOPE);
            Object a = bindings.get("a");
            Object b = bindings.get("b");
            System.out.println("a = " + a);
            System.out.println("b = " + b);

//            Object maxNum = engine.eval("function max_num(a,b){return (a>b)?1:0;}max_num(a,b);");
            Object maxNum = engine.eval("a<5 && b> 6");
            System.out.println("max_num:" + maxNum);

            //调用java 中的类型变量
            String script = "print ('hello script')";
            engine.eval(script);


        } catch (Exception e) {
            e.printStackTrace();
        }
//
//        try {
//
//            // ScriptEngine 中的eval方法中写java script的方法
////            engine.eval("function max_num(a,b){return (a>b)?a:b;}");
//
//            //读取指定的 javascript文件
////            engine.eval(new FileReader("G:\\Xml\\script.js"));
//
//            //读取指定的javascript文件的一种方法
//            InputStreamReader reader = new InputStreamReader(new FileInputStream("G:\\Xml\\script.js"), "UTF-8");
//            engine.eval(reader);
//
//            //可以通过Invocable接口来多次调用脚本库中的函数，Invocable接口是 ScriptEngine可选实现的接口
//            Invocable invoke = (Invocable) engine;
//            Object maxNum = invoke.invokeFunction("max_num", 4, 6);
//            System.out.println(maxNum);
//
//            maxNum = invoke.invokeFunction("max_num", 7, 6);
//            System.out.println(maxNum);
//
//
//            //编译时执行，javaScript是脚本语言，解释执行
//            Compilable compEngine = (Compilable) engine;
//            CompiledScript script = compEngine.compile("function max_num1(a,b){return (a>b)?a:b;}");
//            script.eval();
//            Invocable invoke1 = (Invocable) engine;
//            Object maxNum1 = invoke1.invokeFunction("max_num1", 4, 6);
//            System.out.println(maxNum1+"compEngine:");
//
//
//            testList();
//        } catch (Exception e) {
//            // TODO: handle exception
//        }
    }

    private static void testList() {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factoryList = manager.getEngineFactories();
        System.out.println(factoryList.size());
        for (ScriptEngineFactory factory : factoryList) {
            System.out.println(factory.getEngineName() + "="
                    + factory.getLanguageName());
        }
    }
}
