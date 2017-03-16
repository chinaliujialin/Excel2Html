package com.chinadaas.utils;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

/**
 * Created by pc on 2017/3/15.
 */
public class PathMatcher {

    public static void main(String [] args)throws Exception{
        System.out.println("请输入要进行的操作编号（1、2、3）：\n1、单个文件转换\n2、文件夹转换\n3、结束");
        String type;
        while(true){
            try{
                Scanner input= new Scanner(System.in);
                type=input.nextLine();
                if(type.equals("1")==true){
                    System.out.println("单文本转换："+type);
                    System.out.println("请输入要转换的文件地址：");
                    String dirPath =input.nextLine();
                    File file = new File(dirPath);
                    if(file.exists()){
                        if(file.isDirectory()){
                            System.out.println("输入文件为目录，请输入正确的Excel文件");
                        } else{
                            if(file.getName().endsWith(".xls")||file.getName().endsWith(".xlsx")){
                                ToHtml toHtml = new ToHtml();
                                toHtml.excelToHtml(dirPath,"F:\\" + file.getName() + ".html");
                                System.out.println("转化后的输出路径为："+"F:\\"+file.getName()+".html");
                            }else{
                                System.out.println("输入的不是正确的Excel格式文件");
                            }
                        }
                    }else{
                        System.out.println("请输入正确的文件名，按任意键继续");
                    }
                }
                else if(type.equals("2")==true){
                    System.out.println("你输入的是目录转换："+type);
                    System.out.println("请输入文件目录地址：");
                    String dirInPath =input.nextLine();
                    System.out.println("请输入文件输出目录地址：（如直接输入名称则在软件根目录下建立文件夹）");
                    String dirOutPath =input.nextLine();
                    Dir_todir dir_todir = new Dir_todir(dirInPath,dirOutPath);
                }
                else if(type.equals("3")==true){
                    System.out.println("结束"+type);
                    break;
                }
                else{
                    System.out.println("你输入的不是有效内容");
                    System.out.println("请输入要进行的操作编号（1、2、3）：\n1、单个文件转换\n2、文件夹转换\n3、结束");
                }
            } catch (IOException e) {   e.printStackTrace();}
        }
        System.out.print("done");
    }

}
