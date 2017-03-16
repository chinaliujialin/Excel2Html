package com.chinadaas.utils;

import java.io.*;
import java.util.Scanner;

/**
 * Created by pc on 2017/3/16.
 */
public class FileUtils {


    private  String inDirPath=null;
    private  String outDirPath=null;
    private File outPath;
    private File inPath;

    public FileUtils(String inPutPath, String outPutPath){
        this.inDirPath=inPutPath;
        this.outDirPath=outPutPath;
        this.inPath=new File(inDirPath);
        this.outPath= new File(outDirPath);
        testInputPath();
    }

    public FileUtils(){

    }

    public void singleFileCheck()throws Exception{
        String type;
        Scanner input= new Scanner(System.in);
        type=input.nextLine();
        System.out.println("单文本转换："+type);
        System.out.println("请输入要转换的文件地址：");
        String dirPath =input.nextLine();
        File file = new File(dirPath);
        if(file.exists()){
            if(file.isDirectory()){
                System.out.println("输入文件为目录，请输入正确的Excel文件");
            } else{
                String outputPathName ="";
                if(file.getName().endsWith(".xlsx")){
                    ToHtml toHtml = new ToHtml();
                    outputPathName ="F:\\" + file.getName().substring(0,file.getName().indexOf(".xlsx")) + ".html" ;
                    toHtml.excelToHtml(dirPath,outputPathName);
                    System.out.println("转化后的输出路径为："+outputPathName);
                }else if(file.getName().endsWith(".xls")){
                    ToHtml toHtml = new ToHtml();
                    outputPathName ="F:\\" + file.getName().substring(0,file.getName().indexOf(".xls")) + ".html";
                    toHtml.excelToHtml(dirPath,outputPathName);
                    System.out.println("转化后的输出路径为："+outputPathName);
                }
                else{
                    System.out.println("输入的不是正确的Excel格式文件");
                }
            }
        }else{
            System.out.println("请输入正确的文件名，按任意键继续");
        }
    }

    public void directoryFileCheck(){
        String type;
        Scanner input= new Scanner(System.in);
        type=input.nextLine();
        System.out.println("你输入的是目录转换："+type);
        System.out.println("请输入文件目录地址：");
        String dirInPath =input.nextLine();
        System.out.println("请输入文件输出目录地址：（如直接输入名称则在软件根目录下建立文件夹）");
        String dirOutPath =input.nextLine();
        FileUtils pathChenger = new FileUtils(dirInPath,dirOutPath);
    }

    public void errorMessage(){
        {
            System.out.println("你输入的不是有效内容");
            System.out.println("请输入要进行的操作编号（1、2、3）：\n1、单个文件转换\n2、文件夹转换\n3、结束");
        }
    }

    public void finishCheck(){
        System.out.println("结束");
    }

    private void testInputPath(){
        if (inPath.exists()&&outPath.exists()) {
            if (inPath.isDirectory()&&outPath.isDirectory()) {
                System.out.println("输入路径,输出路径有效");
                try {
                    createdir();
                    getFileList(inDirPath,inDirPath,outDirPath);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            } else {
                System.out.println("输入路径或输出路径不是文件夹");
            }
        } else {
            System.out.println("输入或输出路径不存在");
        }
        System.out.print("请按任意键继续");
    }

    public void createdir() throws IOException {
        String lastName =inPath.getName();
        outPath = new File(outDirPath);
        String outPutPathName =outPath.getPath()+"\\"+lastName;  //输出的路径名
        outPath=new File(outPutPathName);
        outPath.mkdirs();
    }

    /**
     * 为文件生成多级目录
     *@param originPath,源目录文件根地址
     *@param cutOriginPath 源文件地址（用于剪切字符）
     *@param aimPath 目标文件根目录
     */
    public void getFileList(String originPath,String cutOriginPath,String aimPath)throws Exception {
        File originPathFile = new File(originPath);
        File cutOriginPathFile = new File(cutOriginPath);
        File[] files = originPathFile.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) {
                    String filename = files[i].getAbsolutePath().replace(cutOriginPathFile.getAbsolutePath(),"") ;
                    File outPrint = new File(aimPath+"\\"+cutOriginPathFile.getName()+filename);
                    outPrint.mkdirs();
                    getFileList(files[i].getAbsolutePath(),cutOriginPath,aimPath);
                } else if (fileName.endsWith("xlsx")) {
                    String strFileName = files[i].getName();
                    String strFiledir = files[i].getAbsolutePath(); //加工文件名称  1、去除输入路径的文件名：F:\AAAA\AAASS\A.XLDX----->F:\AAAA\AAASS\
                    String str1=strFiledir.replace(strFileName,"");//加工文件名称  2、去除输入路径的根目录：F:\AAAA\AAASS\------>\AAASS\
                    String str2=str1.replace(cutOriginPath,"");//加工文件名称  3、增加输出目录的根目录：\AAASS\------>F:\asd\AAASS\
                    String str3=aimPath+"\\"+cutOriginPathFile.getName()+str2+strFileName.substring(0,strFileName.indexOf(".xlsx")) + ".html";//输出文件  Els_x_toHtml（输入路径，输出路径）
                    File filetest = new File(str3);
                    filetest.mkdirs();
                    ToHtml toHtml= new ToHtml();
                    try{
                        toHtml.excelToHtml(strFiledir,str3+"\\"+strFileName.substring(0, strFileName.indexOf(".xlsx"))+".html");
                    }catch (IOException e){
                        e.printStackTrace();
                    }
                }else if (fileName.endsWith("xls")) {
                    String strFileName = files[i].getName();
                    String strFiledir = files[i].getAbsolutePath();
                    String str1 = strFiledir.replace(strFileName, "");
                    String str2 = str1.replace(cutOriginPath, "");
                    String str3 = aimPath + "\\" + cutOriginPathFile.getName() + str2 + strFileName.substring(0, strFileName.indexOf(".xls")) + ".html";
                    File filetest = new File(str3);
                    filetest.mkdirs();
                    ToHtml toHtml = new ToHtml();
                    try {
                        toHtml.excelToHtml(strFiledir, str3 + "\\" + strFileName.substring(0, strFileName.indexOf(".xls")) + ".html");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                } else {
                    continue;
                }
            }
        }
    }
}
