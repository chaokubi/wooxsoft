package com.mip.software.common.util.crypt;

public class PayConstants {
	/**
	 * 交易成功
	 */
	public static final String RES_CODE_0000="0000";
	
	/**
	 * 未设置支付密码
	 */
	public static final String RES_CODE_2001="2001";
	
	/**
	 * 支付密码次数超限，请重置支付密码【短信验证方式】
	 */
	public static final String RES_CODE_2002="2002";
	
	/**
	 * 支付密码不正确
	 */
	public static final String RES_CODE_2003="2003";
	
	/**
	 * 订单支付金额与订单金额不一致
	 */
	public static final String RES_CODE_3001="3001";
	
	/**
	 * 商户号未配置
	 */
	public static final String RES_CODE_3002="3002";
	
	/**
	 * 订单已取消，请重新下单
	 */
	public static final String RES_CODE_3003="3003";
	
	/**
	 * 订单号重复
	 */
	public static final String RES_CODE_3004="3004";
	
	/**
	 * 订单号不存在
	 */
	public static final String RES_CODE_3009="3009";
	
	/**
	 * 银行扣款返回错误，请联系管理员
	 */
	public static final String RES_CODE_4001="4001";
	
	/**
	 * 电子钱包金额不足
	 */
	public static final String RES_CODE_4002="4002";
	
	/**
	 * 与银行预留手机不一致
	 */
	public static final String RES_CODE_4010="4010";
	
	/**
	 * 统一支付商户号
	 */
	public static final String TMALL_MERCHANT_ID="220000001";
	
	/**
	 * 费用类型10 趣医网支付  11 垃圾回收支付 13 特价商品
	 */
	public static final String FEE_TYPE="22";
	
	/**
	 * 所属银行，目前固定01，指安徽农金卡
	 */
	public static final String BANK_NO="01";
	
	/**
	 * 支付渠道，目前固定01，指安徽农金渠道
	 */
	public static final String CHANNEL_NO="01";
}
