package com.chinadaas.utils;

import java.io.File;
import java.io.IOException;

/**
 * Created by pc on 2017/3/16.
 */
public class PathChenger {


    private  String inDirPath=null;
    private  String outDirPath=null;
    private File outPath;
    private File inPath;

    public PathChenger (String inPutPath,String outPutPath){
        this.inDirPath=inPutPath;
        this.outDirPath=outPutPath;
        this.inPath=new File(inDirPath);
        this.outPath= new File(outDirPath);
        testInputPath();
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
                    try{ toHtml.excelToHtml(strFiledir,str3+"\\"+strFileName+".html");}catch (IOException e){e.printStackTrace();}
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
                        toHtml.excelToHtml(strFiledir, str3 + "\\" + strFileName + ".html");
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
