package com.liukevin.util.array;

import java.util.*;

/**
 * Created by liukai on 2017/9/10.
 */
public class Array2CollectionUtil {

    public static Collection array2Collection(Object o,Class<? extends Collection> clazz) throws Exception{

        if(o instanceof Collection){
            return (Collection)o;
        }

        if(!o.getClass().isArray()){
            throw new Exception("o is not array");
        }
        //对象数组
        if(o instanceof Object[]){
            //此处需要先将数组对象转为对象数组，否则会把整个数组按照一个对象放到数组中转为一个数组对象
            Object []oarray = (Object[])o;
            return Arrays.asList(oarray);
        }
        Collection result = null;
        if(clazz != null){
            result = clazz.newInstance();
        }else{
            result = new HashSet();
        }
        //HashSet result = new HashSet();
        //基本数据类型byte char int float double boolean short long 数组
        if(o instanceof byte[]){
            byte[] array = (byte[])o;
            for(byte arr : array){
                result.add(arr);
            }
        }
        if(o instanceof char[]){
            char[] array = (char[])o;
            for(char arr : array){
                result.add(arr);
            }
        }
        if(o instanceof int[]){
            int[] array = (int[])o;
            for(int arr : array){
                result.add(arr);
            }
        }
        if(o instanceof float[]){
            float[] array = (float[])o;
            for(float arr : array){
                result.add(arr);
            }

        }
        if(o instanceof double[]){
            double[] array = (double[])o;
            for(double arr : array){
                result.add(arr);
            }
        }
        if(o instanceof boolean[]){
            boolean[] array = (boolean[])o;
            for(boolean arr : array){
                result.add(arr);
            }
        }
        if(o instanceof short[]){
            short[] array = (short[])o;
            for(short arr : array){
                result.add(arr);
            }
        }
        if(o instanceof long[]){
            long[] array = (long[])o;
            for(long arr : array){
                result.add(arr);
            }
        }


        return result;



    }
    public static void main(String []args){
        try{
            //对象
            args = new String []{"a","b","c"};
            Collection oc = array2Collection(args,null);
            for(Object o : oc){
                System.out.println(o);
            }
            int is [] = new int[]{1,34,23};
            Collection ic = array2Collection(is,HashSet.class);
            for(Object o : ic){
                System.out.println(o);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
    }

}

