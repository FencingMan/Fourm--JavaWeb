package com.alipay.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101300679479";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCzI6ngw3N5NyLzG3Vq1SAHvX4i4jzqzNGgyd8PGtGo486ZJAsdYBzWpn7h+Q1vV0AqyyncuP4qrmOxaAXMUXAnACozTx9Hyv/5w/fR5LC1pg/DilHTCOOyC2Q5kTY5ZLNJ2IPRgyvbrPkvrDAhczRsJYz/94SdhFqG+qsuy/4SKafBr6XsIrpmFGRlp6hnpTQ4R67JXq+XSjeljMJYYuvHMGx29fKMolYvYvqXhPTbykOg11Bb75DJp2Mq2h14fgp9F/zGjr6VmbBEgMtn0cWoAo/5f4ySdSBwjp0avThYijRkKCB/NKbpOMkza8MlU1fmO8tGkZnqkCDRNFryJtKjAgMBAAECggEAbWTI95XDrXErKZRKFsxBfBiOr5TCqIwdM3gzfBDfmahL4J7vHqgz1CzmbO5e8vAwIjHgxYY+yCeof4zLhaLxvw9NtfmKW5guywKOEqH5+2OGWt3b6+iQy4hDUxhHKbNB/UV38fiNTuAJZ8BZwAOPLr+bpDAtztclRSpMYQ53f/TkT9LszSF5q2/LcI+Ps0ePmQYR/zN13ZUirk+pWMP7vz1Le8TWkvv1Q9zjlKqSU5ReHClPDRKeqj2uVcQmNrwY6q+jJlyiXQrmvLgRUyWDlzVElNULvUmYU5svrvcQJNFSaqz+vM2c/76w0gXCeIoj2GCzJDNwnHZHgVpSH2oIkQKBgQDfhOHSZWOMBI42vtljDygD+wOSKUMDXgeDGZp0o6R7qxhUUA+li7eiw11XUdzvXVpwnx2kTR5OsUZRq2HuxOE7klXGlraGC6fcQ9Bw9vpknJ9Ho+feLtAtATbM8yxCrWitjOapIYSl1gREm3cZo/h+lNiPj9Xb1NH3eg2b4g8KCQKBgQDNK9Ahp5KVxt6bNgOFcOdAfu+k30WiYQQEOGq93/N7ZFmuTmp/fKz1eY5rtRJy5q2uh5q0YKHwAGpGnN8pftg6mXdxP0Fos4PE2FcRlOgYXhwvazWKT18JwGQ/Dv3x5tCnCP/xXS+znaSsTKNQGPbUG5T9SiPoGUSgIOCAdEhSSwKBgQDFerPBnY578LJtL+UEniluiuG2yvnICdV9QKu0DbiPArU2GJ4aVvzhstXPBia63LHA+vPHa9MxF3LXSSH3QL72UO2zzQhqGfYup0OL6smDBtW7B9cd+CI5AO+X1+js6CSp4OkR2OlHU3Tl4K0zXCl6PKuw+xWpabgwfAd2CB3dcQKBgHmIm3hkEBPCwrhELZ/M+l8dEg2i6FfAiiWJ2cjxsQQFtI3pC3LLQpUCVVf94ZjIhlddIkn499P4GvwLDrJA3gsQP02I/9DqjYJ7+E+E2lsc3CtGoWd1jsFzAq7LzaeOZ0iarymlXo1SM2qQ1yiqMQQB9qZ3VX+2AA2ui+3rc6+zAoGAVlv4RupUwo4i1Z950d58PHbf3Bc0SqAHhf3+LNlgKPNdNbewfo/f11OAfLuRL2v+WXGfW3I/fw01KIagar6rQMwiVNqFw48gO4bwhS3KDO+N02IK2uv+XQNUcS3YgpZLT29p//bUC/QHaEfc3/FMRs8qRmTWDr9V4Ak2+Y6/Ecw=";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxro43vKEJ3g/ZJ1iWBRqw/4A0bdAdHLyr4IgLiPOT1I4Vz4l0B+ExHpL4gc9exxvf5XBQY0d8Fb9A2a9EtcgehqGOXYv08kV6USQRLdyce/bMXx4YYcc/4YVBTd+eygk7Sm8WZuAdULuf93TV+0nCC8Q+hBwh9J6rrHDPjTrEa0XB8L12EZ16jT0sgXDI5AJzTMIzRySobEwTitFjr6zzMLhi0QBdeLQ+kks/DMVxx+KtRN1JoIzeLAckGpTMImbTchHE+zZ1bapf3jfs6/QQJMpADhg07bcdUdMMn63gRjp/umbGWpOKlyAO0qscKn7UovRyRBhW0nANPgOx4ZLTQIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://localhost:8080/cnmd/alipay.trade.page.pay-JAVA-UTF-8/notify_url.jsp";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://localhost:8080/cnmd/alipay.trade.page.pay-JAVA-UTF-8/return_url.jsp";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

