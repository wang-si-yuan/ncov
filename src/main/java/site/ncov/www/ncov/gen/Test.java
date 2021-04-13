package site.ncov.www.ncov.gen;

import com.alibaba.fastjson.JSONObject;
import com.tencentcloudapi.common.Credential;
import com.tencentcloudapi.common.exception.TencentCloudSDKException;
import com.tencentcloudapi.common.profile.ClientProfile;
import com.tencentcloudapi.common.profile.HttpProfile;
import com.tencentcloudapi.ocr.v20181119.OcrClient;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRRequest;
import com.tencentcloudapi.ocr.v20181119.models.IDCardOCRResponse;
import site.ncov.www.ncov.common.model.entity.Picture;
import site.ncov.www.ncov.common.model.entity.User;
import site.ncov.www.ncov.common.utils.ImageUtils;

import java.util.Map;

/**
 * @author 王思源
 * @version 0.0.0
 */

public class Test {
    public static void main(String[] args) {
        User user = getUserByOCR("http://www.2019-ncov.site:8000/4b51c292-83b1-4fb0-9a8d-21c9b63d215e.jpg");
        System.out.println(user);

    }
    public static User getUserByOCR(String picture) {
        try{

            Credential cred = new Credential("AKIDEUXLjuYlJccLYXoxFMgQt4HniKfx6nY1", "MM6YjeKlvQijhJB8qH0vLQE26dz61f13");

            HttpProfile httpProfile = new HttpProfile();
            httpProfile.setEndpoint("ocr.tencentcloudapi.com");

            ClientProfile clientProfile = new ClientProfile();
            clientProfile.setHttpProfile(httpProfile);

            OcrClient client = new OcrClient(cred, "ap-beijing", clientProfile);

            IDCardOCRRequest req = new IDCardOCRRequest();
            String s = ImageUtils.image2Base64(picture);
            //req.setImageUrl(picture);
            req.setImageBase64(s);

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
            e.printStackTrace();
        }
        return null;
    }
}
