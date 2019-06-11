package com.xuhuawei.sid;

import java.io.File;

/**
 * 删除多余重复的图片
 */
public class DeleDumpFile {
    private static final String rootFile = "E:\\ImageBackup";

    public static void main(String[] args) {
        File file = new File(rootFile);
        if (file.exists()) {
            File subFiles[]=file.listFiles();
            for (File fileItem: subFiles) {
                if (fileItem.isDirectory()){
                    String[] itemFile = fileItem.list(new DelDumpImageFileFilter());
                    for (String strFile: itemFile) {
                        File delFile=new File(fileItem.getAbsolutePath(),strFile);
                        delFile.delete();
                    }
                }
            }


        }
    }
}
