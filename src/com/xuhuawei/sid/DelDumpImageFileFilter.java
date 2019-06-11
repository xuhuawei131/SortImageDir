package com.xuhuawei.sid;

import java.io.File;
import java.io.FilenameFilter;

public class DelDumpImageFileFilter implements FilenameFilter {
    @Override
    public boolean accept(File dir, String name) {
        if (name.startsWith(".")){
         return false;
        }else if (name.endsWith("(1).jpg")){
            return true;
        }else if (name.endsWith("(2).jpg")){
            return true;
        }else {
            return false;
        }
    }
}
