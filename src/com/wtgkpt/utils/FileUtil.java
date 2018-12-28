package com.wtgkpt.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;

import org.apache.commons.lang3.StringUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUtil {

	/**
	 * 创建文件
	 *
	 * @param destFile
	 * @return
	 */
	public static boolean createFile(File destFile) {
		if (destFile.exists()) {
			return false;
		}
		if (destFile.getPath().endsWith(File.separator)) {
			return false;
		}
		// 判断目标文件所在的目录是否存在
		if (destFile.getParentFile() != null && !destFile.getParentFile().exists()) {
			// 如果目标文件所在的目录不存在，则创建父目录
			if (!destFile.getParentFile().mkdirs()) {
				return false;
			}
		}
		// 创建目标文件
		try {
			if (destFile.createNewFile()) {
				return true;
			} else {
				return false;
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}
	}

	/**
	 * 获取文件扩展名
	 * @param filename
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		return filename;
	}

	/**
	 * 获取不带扩展名的文件名
	 * @param filename
	 * @return
	 */
	public static String getFileNameNoEx(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length()))) {
				return filename.substring(0, dot);
			}
		}
		return filename;
	}
	
	/**
     * 用上传文件流判断是否为合法后缀文件名
     * @param file
     * @param endNameList 不加.符号
     * @return
     */
    public static boolean checkValidFileEndName(MultipartFile file, String[] endNameList){        
    	String tempEndName = null;      
	    //判断 是否为允许的后缀文件名                
	    ImageInputStream iis = null;  
	  	boolean isok= false;
	    try {  
	         iis = ImageIO.createImageInputStream(file.getInputStream());
	         Iterator<ImageReader> iter = ImageIO.getImageReaders(iis);   
	         if(iter.hasNext()){
	         tempEndName = iter.next().getFormatName();
	         for (String endNameStr : endNameList) {
	          	if(StringUtils.isNotEmpty(endNameStr) )
	          		//外面会带.符号进来，改外面，在这处理
	          		if(endNameStr.equalsIgnoreCase(tempEndName)){
	          			isok=true;
	          			break;
	          		}
				}
	         }  
	     } catch (IOException e) {
	    	 return false;
	     }finally{
	         if(iis!=null){  
	             try {  
	                  iis.close();  
	             } catch (IOException e) {  
	                  e.printStackTrace();  
	             }  
	         }  
	     }
	  	if(!isok){
	  		return false;
	  	}
	    //end 判断 是否为允许的后缀文件名 
    	return true;
    }
    
    /**
     * 读取到字节数组2
     *
     * @param filePath
     * @return
     * @throws IOException
     */
    public static byte[] toByteArray2(String filePath) throws IOException {

        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }

        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while ((channel.read(byteBuffer)) > 0) {
                // do nothing
                // System.out.println("reading");
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}