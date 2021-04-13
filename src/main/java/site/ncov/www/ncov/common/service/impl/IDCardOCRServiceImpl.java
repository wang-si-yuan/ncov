package site.ncov.www.ncov.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import site.ncov.www.ncov.common.model.entity.Picture;
import site.ncov.www.ncov.common.model.entity.User;
import site.ncov.www.ncov.common.service.IDCardOCRService;
import site.ncov.www.ncov.common.utils.ImageUtils;

import java.util.Map;

/**
 * @author 王思源
 * @version 0.0.0
 */

@Service
public class IDCardOCRServiceImpl implements IDCardOCRService {
    static Logger logger = LoggerFactory.getLogger(IDCardOCRServiceImpl.class);
    @Override
    public User getUserByOCR(Picture picture) {
        try{

            Credential cred = new Credential("AKIDEUXLjuYlJccLYXoxFMgQt4HniKfx6nY1", "MM6YjeKlvQijhJB8qH0vLQE26dz61f13");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);

            IDCardOCRRequest req = new IDCardOCRRequest();
            req.setImageUrl(picture.getUrl());

            IDCardOCRResponse resp = client.IDCardOCR(req);

            String json = IDCardOCRResponse.toJsonString(resp);

            Map<String,String> map = (Map) JSONObject.parse(json);
            User user = new User();
            user.setUserName(map.get("Name"));
            user.setUserNation(map.get("Nation"));
            if(map.get("Sex").equals("男")){
                user.setUserGender(0);
            }else {
                user.setUserGender(1);
            }
            user.setUserAddress(map.get("Address"));
            user.setUserIdcard(map.get("IdNum"));
            return user;

        } catch (TencentCloudSDKException e) {
            logger.debug(e.toString());
        }
        return null;
    }
}
