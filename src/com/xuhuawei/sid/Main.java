package com.xuhuawei.sid;

import java.io.*;

public class Main {
    private static final String rootFile="F:\\小米6相册";
    private static final int MAX_PIC_COUNT=500;
    private static final int MAX_ARRAY_SIZE=10240;
    public static void main(String[] args) {
        File file=new File(rootFile);
        if (file.exists()){
            String[] itemFile=file.list(new ImageFileFilter());
            int totalSize=itemFile.length;
            for (int i=0;i<totalSize;i++){
                String itemName=itemFile[i];
                if (i%100==0||totalSize-1==i){
                    System.out.println("progress:"+i+"/"+totalSize+", itemName: "+itemName);
                }
                String date;
                String year;
                String month;
                String day;
                if (itemName.startsWith("IMG_")){
                    date=itemName.substring(4,12);
                    year=date.substring(0,4);
                    month=date.substring(4,6);
                    day=date.substring(6,8);
                    jump2File(year,itemName);
                }else if (itemName.startsWith("IMG")){
                    date=itemName.substring(3,11);
                    year=date.substring(0,4);
                    month=date.substring(4,6);
                    day=date.substring(6,8);
                    jump2File(year,itemName);
                }else if (itemName.startsWith("PANO_")){
                    date=itemName.substring(5,13);
                    year=date.substring(0,4);
                    month=date.substring(4,6);
                    day=date.substring(6,8);
                    jump2File(year,itemName);
                }else{
                    if (itemName.startsWith("201")){
                        date=itemName.substring(0,8);
                        year=date.substring(0,4);
                        month=date.substring(4,6);
                        day=date.substring(6,8);
                        jump2File(year,itemName);
                    }else{
                        jump2File("other",itemName);
                    }
                }
            }
        }
    }

    private static void jump2File(String  year,String fileName){
        int time=0;
        while (true){
            String subFileDir=year+"_"+time;
            File newFileDir=new File(rootFile,subFileDir);
            if (!newFileDir.exists()) {
                newFileDir.mkdirs();
            }
            if (newFileDir.list().length>=MAX_PIC_COUNT){
                time++;
                continue;
            }else{
                File srcFile=new File(rootFile,fileName);
                File desFile=new File(newFileDir,fileName);
                copyFile(srcFile,desFile);
                break;
            }
        }
    }
    private static void copyFile(File srcFile,File desFile){
        FileInputStream inputStream=null;
        FileOutputStream outputStream=null;
        try {
            inputStream=new FileInputStream(srcFile);
            outputStream=new FileOutputStream(desFile);
            byte data[]=new byte[MAX_ARRAY_SIZE];
            int length=0;
            while ((length=inputStream.read(data))>0){
                outputStream.write(data,0,length);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (inputStream!=null){
                try {
                    inputStream.close();
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }
    }
}
