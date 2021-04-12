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

    @Value("${imgPath}")
    private String path;

    public Picture(String url){
        this.url = url;
    }

    public Picture(MultipartFile picture) throws FileNotFoundException {
        fileToUrl(picture);
    }

    private void fileToUrl(MultipartFile picture) throws FileNotFoundException {
        String fileName = picture.getOriginalFilename(); // 文件名
        assert fileName != null;
        String suffixName = fileName.substring(fileName.lastIndexOf("."));  // 后缀名
        String filePath = ResourceUtils.getURL(path).getPath(); // 上传后的路径
        fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            picture.transferTo(dest);
        } catch (IOException e) {
            logger.debug(e.toString());
        }

        url = "http://www.2019-ncov.site/img/"+fileName;

    }



}
