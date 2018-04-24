package com.chen.javascript;

import net.sourceforge.jeval.EvaluationException;
import net.sourceforge.jeval.Evaluator;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by chen on 2017/7/25.
 */
public class TestJeval {
    @Test
    public void testJeval(){
        Evaluator evaluator=new Evaluator();

        String sttt="8<=x<=105";

        String str1="x == 105";

        String str2="!(105 > x > 8)";

        String str="105 >= x && x >= 8";


       try {
            evaluator.putVariable("x","7");
            boolean value=  evaluator.getBooleanResult(formatExpression(str));



            System.out.println(value);

        } catch (EvaluationException e) {
            e.printStackTrace();
        }

     //  formatExpression(str2);
    }

    public static String formatExpression(String exp){
        //英文字母變量用#{ }包裝：如變量x，#{x}
        String re = "([a-zA-Z]+)";
        Pattern pattern = Pattern.compile(re);
        Matcher matcher = pattern.matcher(exp);
        StringBuffer sb = new StringBuffer();
        while(matcher.find()){
            matcher.appendReplacement(sb, "#{" + matcher.group(1) + "}");
        }
        matcher.appendTail(sb);

        String ss = "([><]+)";
        Pattern pattern1 = Pattern.compile(ss);
        Matcher matcher1 = pattern1.matcher(exp);
        StringBuffer sb1 = new StringBuffer();
        int count=0;

        while(matcher1.find()){
            count++;
        }

        if(count>1){

        }


        return sb.toString();
    }
}
