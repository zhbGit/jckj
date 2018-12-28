package com.wtgkpt.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UploadUtil {

	private UploadUtil() {
		
    }

	private static SimpleDateFormat fullSdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");

	/**
	 * 返回yyyy File.separator MM File.separator dd格式的字符串
	 * @return
	 */
	public static String getFolder() {
		return AwayTools.formatDateByFormat(new Date(), "yyyyMMdd");
	}

	/**
     * 文件上传
     * 
     * @param srcName
     *            原文件名
     * @param dirName
     *            目录名
     * @param input
     *            要保存的输入流
     * @return 返回要保存到数据库中的路径
     */
    public static String writeFile(String srcName, String dirName, InputStream input) throws IOException {
        AwayTools.writeLog(AwayTools.SYSOUTFILE, srcName);
        String uploadDir = AwayTools.getUserUploadPath();//设置你上传路径
        // 重命名文件
        if (null != srcName) {
            srcName = srcName.substring(srcName.indexOf("."));
        } else {
            srcName = ".jpg";
        }
        String filename = "";
        // 得到要上传的文件路径
        filename = uploadDir + File.separator + dirName + File.separator + fullSdf.format(new Date()) + srcName;

        // 得到将要保存到数据中的路径
        String savePath = filename.replace(uploadDir, "");
        savePath = savePath.replace("\\", "/");

        File file = new File(filename);
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        FileOutputStream fos = new FileOutputStream(file);
        // 一次30kb
        byte[] readBuff = new byte[1024 * 30];
        int count = -1;
        while ((count = input.read(readBuff, 0, readBuff.length)) != -1) {
            fos.write(readBuff, 0, count);
        }
        fos.flush();
        fos.close();
        input.close();
        return savePath;
    }
}
