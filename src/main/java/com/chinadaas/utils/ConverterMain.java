package com.chinadaas.utils;

import java.io.IOException;
import java.util.Scanner;

/**
 * Created by pc on 2017/3/15.
 */
public class ConverterMain {

    public static void main(String [] args)throws Exception{
        System.out.println("请输入要进行的操作编号（1、2、3）：\n1、单个文件转换\n2、文件夹转换\n3、结束");
        String type;
        while(true){
            try{
                Scanner input= new Scanner(System.in);
                type=input.nextLine();
                FileUtils fileUtils = new FileUtils();
                if(type.equals("1")==true){
                    fileUtils.singleFileCheck();
                }
                else if(type.equals("2")==true){
                   fileUtils.directoryFileCheck();
                }
                else if(type.equals("3")==true){
                    fileUtils.finishCheck();
                    break;
                }
                else{
                    fileUtils.errorMessage();
                }
            } catch (IOException e) {   e.printStackTrace();}
        }
        System.out.print("done");
    }

}
