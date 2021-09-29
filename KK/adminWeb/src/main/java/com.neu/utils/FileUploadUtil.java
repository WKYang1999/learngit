package com.neu.utils;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;

public class FileUploadUtil {

    public static boolean saveFile ( MultipartFile file){
        if (!file.isEmpty()) {
            try {
                String filePath = (new File("")).getAbsolutePath() + "\\src\\main\\webapp\\static\\style\\" + "pic\\" + file.getOriginalFilename();
                File saveDir = new File(filePath);
                if (!saveDir.getParentFile().exists()) {
                    saveDir.getParentFile().mkdirs();
                }
                //文件转存
                file.transferTo(saveDir);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
