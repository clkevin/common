package com.liukevin.util.string;

import java.security.MessageDigest;

/**
 * 对字符串进行md5加密处理
 */
public class MD5Util {


	/**
	 *
	 * @param origin  进行加密的字符串
	 * @param charsetname  加密的编码
	 * @return  加密后的32位md5串
	 */
	public static String MD5Encode(String origin, String charsetname) {
		String resultString = null;
		try {
			resultString = new String(origin);
			MessageDigest md = MessageDigest.getInstance("MD5");
			if (charsetname == null || "".equals(charsetname))
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes()));
			else
				resultString = byteArrayToHexString(md.digest(resultString
						.getBytes(charsetname)));
		} catch (Exception exception) {
		}
		return resultString;
	}

	private static final String hexDigits[] = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };


	private static String byteArrayToHexString(byte b[]) {
		StringBuffer resultSb = new StringBuffer();
		for (int i = 0; i < b.length; i++)
			resultSb.append(byteToHexString(b[i]));

		return resultSb.toString();
	}

	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0)
			n += 256;
		int d1 = n / 16;
		int d2 = n % 16;
		return hexDigits[d1] + hexDigits[d2];
	}

	public static void main(String []args){
		String origin="amount=100&check_name=OPTION_CHECK&desc=节日快乐!&mch_appid=wx2421b1c4370ec43b&mch_id=10000100&nonce_str=0b5e29aa1acf8bdc5d8935d7036fa4f5&openid=ohO4Gt7wVPxIT1A9GjFaMYMiZY1s&partner_trade_no=100000982014120919616&re_user_name=张三&spbill_create_ip=10.90.0.136&key=192006250b4c09247ec02edce69f6a2d";
		String str=MD5Util.MD5Encode(origin, "utf-8");
		System.out.println(str);
	}
}
