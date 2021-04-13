package site.ncov.www.ncov.common.model.entity;

import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Getter
public class Picture {

    static Logger logger = LoggerFactory.getLogger(Picture.class);

    private String url;
    private MultipartFile picture;

    private String site="www.2019-ncov.site";
    private String path=ResourceUtils.getURL("classpath:").getPath()+"static/img/";

    public Picture(String url) throws FileNotFoundException {
        this.url = url;
    }

    public Picture(MultipartFile picture) throws FileNotFoundException {
        fileToUrl(picture);
        this.picture = picture;
    }


    private void fileToUrl(MultipartFile picture) throws FileNotFoundException {
        String fileName = picture.getOriginalFilename(); // 文件名

        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath =  path; // 上传后的路径
        fileName = UUID.randomUUID() + suffixName;
        File file = new File(filePath + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            picture.transferTo(file);
        } catch (IOException e) {
            logger.debug(e.toString());
        }

        url = "http://"+site+":8000/"+fileName;

    }



}
