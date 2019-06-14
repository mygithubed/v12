package com.it.v12centerweb.controller;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.it.v12.common.pojo.RsetBean;
import com.it.v12centerweb.pojo.Wangeditor3UploadRestBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * Author:曾志鹏
 * Date:2019/6/14
 * Time:13:31
 */
@Controller
@RequestMapping("file")
public class FileUploadController {

    /**
     * 文件上传的
     */
    @Autowired
    private FastFileStorageClient client;

    @Value("${image.service}")
    private String imageService;

    /**
     * 图片上传的功能实现
     * @param file
     * @return
     */
    @RequestMapping("upload")
    @ResponseBody
    public RsetBean updateImage(MultipartFile file){
        System.out.println(file);
        String filename = file.getOriginalFilename();
        String endName = filename.substring(filename.lastIndexOf(".")+1);
        try {
            //文件上传的全路径
            StorePath storePath =
                        client.uploadFile(file.getInputStream(),file.getSize(),endName,null);
            String path = new  StringBuilder(imageService).append(storePath.getFullPath()).toString();
            return new RsetBean("200",path);
        }catch (IOException e){
            e.printStackTrace();
        }
        return new RsetBean("404","上传失败！");
    }

    /**
     * 多文件的上传，在富文本框中的图片
     * @return
     */
    @RequestMapping("uploads")
    @ResponseBody
    public Wangeditor3UploadRestBean fileUploads(MultipartFile[] files){
        //上传文件的个数
        String[]  count =  new String[files.length];
        //上传
        for (int i = 0; i < files.length; i++) {
            String filename = files[i].getOriginalFilename();
            String endName = filename.substring(filename.lastIndexOf(".")+1);
            try {
                //文件上传的全路径
                StorePath storePath =
                        client.uploadFile(files[i].getInputStream(),files[i].getSize(),endName,null);
                String path = new  StringBuilder(imageService).append(storePath.getFullPath()).toString();
                //保存路劲
                count[i] = path;

            }catch (IOException e){
                e.printStackTrace();
                return  new Wangeditor3UploadRestBean("1",null);
            }
        }
        return  new Wangeditor3UploadRestBean("0",count);
    }
}
