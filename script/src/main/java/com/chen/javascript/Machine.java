package com.chen.javascript;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

/**
 * Created by chen on 2017/7/5.
 */
public class Machine {
    public static void main(String[] args) {
        //指定使用javaScript的方法
        ScriptEngineManager scriptEngineManager=new ScriptEngineManager();
        ScriptEngine engine=scriptEngineManager.getEngineByExtension("js");




    }
}
