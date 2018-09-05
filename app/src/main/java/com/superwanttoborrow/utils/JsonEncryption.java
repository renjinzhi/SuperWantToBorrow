package com.superwanttoborrow.utils;

import android.content.Context;

import java.io.InputStream;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author renji
 * @date 2018/1/19
 */

public class JsonEncryption {

    private static InputStream open;

    public static Map<String, String> addkey(Context context, String json)throws Exception {

        System.out.println("数据=============>" + json);

        //商户获取公钥
        PublicKey pubKeyFromCrt = RSA.getPublicKey("MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQCdUqZXKe8IdpcUVvManlyJ4OnHv1lHE3mqKcUn6qhWiFQFaspVEchnLG7nSdglGGxjUeutLv7rHfVfQufAxeC5i+TQZWMvNEp8wpnkwYF9LDN9My4UhBAiQyZxrZeme+oiLwnOuJIHwZbhP/LSOcTKv9oqI2JJFVX2oXUWGYj6zwIDAQAB");
        //随机生成16数字
        String key = RandomUtil.getRandom(16);
        //进行加密
        String encryptkey = RSA.encrypt(key, pubKeyFromCrt);
        String encryData = AES.encryptToBase64(json, key);

        Map<String, String> map = new HashMap<String, String>();
        map.put("data", encryData);
        map.put("encryptkey", encryptkey);

        return map;
    }

    public static InputStream readAssert(Context context, String fileName) {
        try {
            InputStream inputStream = context.getResources().getAssets().open(fileName);
            return inputStream;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


}
