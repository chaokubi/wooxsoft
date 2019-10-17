package com.mip.software.common.util.crypt;

import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.alibaba.fastjson.JSONObject;
import com.mip.software.common.util.HttpXmlUtil;
import com.mip.software.common.util.JsonUtils;
import com.mip.software.common.util.StringUtil;

public class PayHelpler {

	private static Log logger=LogFactory.getLog(PayHelpler.class);
	
	private static PayHelpler instance=new PayHelpler();
	
	public static PayHelpler getInstance() {
		if (instance == null) {
			synchronized (PayHelpler.class) {
				if(instance == null){
					instance = new PayHelpler();
				}
			}
		}
		return instance;
	}
	
	private PayHelpler(){
		
	}
	
	@SuppressWarnings("rawtypes")
	public String getResult(String url,Map parameters) throws Exception{
		logger.info(">>>>>传输参数："+StringUtil.getString(parameters));
		
		JSONObject obj=new JSONObject();
		
		String secretKey=PayBean.getRandomNumString(16);
		obj.put("encryptValue", AESUtil.encrypt(JSONObject.toJSONString(parameters), secretKey));//AES密钥加密
		obj.put("encryptKey", RSAUtil.encryptByPublicKey(secretKey, RSAUtil.getPublicKey(PayBean.getEModulus(), PayBean.getPublicExponent())));//RSA公钥加密
		
		logger.info(">>>>>>加密参数："+obj.toString());
		
		String json=HttpXmlUtil._doPost(url, obj.toJSONString());
		Map<String,Object> result=JsonUtils.json2Map(json);
		
		logger.info(">>>>>>返回数据："+StringUtil.getString(result));
		
		String ev=StringUtil.getString(result.get("encryptValue"));
		String ek=StringUtil.getString(result.get("encryptKey"));
		
		String key=RSAUtil.decryptByPrivateKey(ek, RSAUtil.getPrivateKey(PayBean.getDModulus(), PayBean.getPrivateExponent()));//RSA私钥解密
		String val=AESUtil.decrypt(ev, key);//AES秘钥解密
		
		logger.info(">>>>>>解密数据："+val);
		
		return val;
	}
	
}
