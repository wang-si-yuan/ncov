package site.ncov.www.ncov.common.model.entity;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import lombok.Getter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import site.ncov.www.ncov.common.utils.CovertDateFormatUtils;
import site.ncov.www.ncov.common.utils.DataFactory;
import site.ncov.www.ncov.common.utils.ImageUtils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;
import java.util.UUID;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Getter
public class Picture {

    static Logger logger = LoggerFactory.getLogger(Picture.class);

    private String url;
    private String localUrl;
    private MultipartFile picture;

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
        localUrl = filePath + fileName;
        File file = new File(filePath + fileName);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        try {
            picture.transferTo(file);
        } catch (IOException e) {
            logger.debug(e.toString());
        }

    //    url = "https://"+ DataFactory.site +":8000/"+fileName;
        url = "https://"+ DataFactory.site +"/img/"+fileName;

    }

    public void clearPic() {
        File file = new File(localUrl);
        if (file.getParentFile().exists()) {
            file.getParentFile().delete();
        }
    }

    public User queryUserByOCR() {
        return getUser(url);
    }

    private User getUser(String url) {
        try{

            Credential cred = new Credential("AKIDEUXLjuYlJccLYXoxFMgQt4HniKfx6nY1",
                    "MM6YjeKlvQijhJB8qH0vLQE26dz61f13");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);

            IDCardOCRRequest req = new IDCardOCRRequest();

            String s = ImageUtils.image2Base64(url);
            //req.setImageUrl(picture);
            req.setImageBase64(s);

            IDCardOCRResponse resp = client.IDCardOCR(req);

            String json = IDCardOCRResponse.toJsonString(resp);

            Map<String,String> map = (Map) JSONObject.parse(json);
            User user = User.builder().userName(map.get("Name")).userNation(map.get("Nation"))
                    .userAddress(map.get("Address")).userIdcard(map.get("IdNum")).userCardPhoto(this).userBirth(
                            CovertDateFormatUtils.dateToLocalDate(
                                    CovertDateFormatUtils.parseDate(map.get("Birth"), "yyyy/M/d")
                            )
                    ).build();
            if(map.get("Sex").equals("男")){
                user.setUserGender(Gender.MAN);
            }else {
                user.setUserGender(Gender.WOMAN);
            }
            return user;

        } catch (TencentCloudSDKException e) {
            logger.info(e.toString());
        }
        return null;
    }


}
