package com.wtgkpt.utils;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;

public class AwayTools {

	/**
	 * 系统登录用户
	 */
	public static final String SYS_LOGIN_USER = "loginUser";
	public final static String SYSOUTFILE = getRrpLogPath() + "/wtgkpt/syslog/log.txt";
	public static final String[] IMAGE_TYPE = { "jpg", "jpeg", "png", "bmp", "gif", "tif", "pcx", "tga", "exif", "fpx", "svg", "psd", "webp", "WMF", "raw" };
	
	/**
	 * 获取class加载的路径（classes/）
	 * @return
	 */
    public static final String getClassBasePath() {
        return AwayTools.class.getResource("/").getFile();
    }

    /**
     * 获取web根目录WebContent
     * @return
     */
    public static final String getWebRootFilePath() {
        String rootClassPath = getClassBasePath();
        File rootFile = new File(rootClassPath);
        String webInfoDirectoryPath = rootFile.getParent() + File.separator;
        File webInfoDir = new File(webInfoDirectoryPath);
//        String servletContextPath = webInfoDir.getParent() + File.separator;
        return webInfoDir.toString();
    }
    
    /**
     * rrp日志路径 为了测试方便，window系统运行此项目暂时放在/WebContent/rrplog/
     * @return
     */
    public static final String getRrpLogPath() {
        return "/home/away/";
    }
    
    public static final String getUserUploadPath() {
    	return getWebRootFilePath() + "\\jsp\\useruploads\\user\\";
    }
    
    /**
     * 查看图片
     *
     * @param stFileName
     * @param oRequest
     * @param oResponse
     */
    public static void getShowImage(String stRealPath, String stFileName, HttpServletResponse oResponse) {

        stFileName = AwayTools.checkNullString(stFileName).trim();
        if (stFileName.lastIndexOf(".") > 0) {
            String stSuffix = stFileName.substring(stFileName.lastIndexOf(".")).toLowerCase();
            if (stSuffix.endsWith("jpg") || stSuffix.endsWith("jpeg")) {
                oResponse.setHeader("Content-Type", "image/jpeg");
            } else {
                oResponse.setHeader("Content-Type", "image/gif");
            }
        }
        try {
            InputStream in = new BufferedInputStream(new FileInputStream(stRealPath + stFileName));
            ServletOutputStream out = oResponse.getOutputStream();
            int data = 0;
            while ((data = in.read()) != -1) {
                out.write(data);
            }
            in.close();
            out.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 以指定的格式来格式化日期
     * @param date
     * @param format
     * @return
     */
    public static String formatDateByFormat(java.util.Date date, String format) {
        String result = "";
        if (date != null) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.format(date);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        return result;
    }

    public static String checkNullString(String str) {
        if (str == null) {
            return "";
        } else {
            return str.trim();
        }
    }
    
    public static String ToString(Object str) {
    	if (str == null) {
    		return "";
    	} else {
    		try {
    			return str.toString().trim();
			} catch (Exception e) {
				return "";
			}
    	}
    }

    public static boolean isNumber(String stNumber) {
        return isNumberString(stNumber);
    }

    public static boolean isNumberString(String str) {
        if (str == null) {
            return false;
        }
        int i = 0;
        for (; i < str.length(); i++) {
            if (str.charAt(i) < '0' || str.charAt(i) > '9') {
                return false;
            }
        }
        if (i == 0)
            return false;
        return true;
    }

    public static java.util.Date parseFormatDate(String strdate) {
        DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT, Locale.CHINA);
        java.util.Date date = null;
        try {
            date = df.parse(strdate);
        } catch (ParseException e) {
            e.printStackTrace();
            return null;
        }
        return date;
    }

    /**
     * 常用的格式化日期
     * @param date
     * @return
     */
    public static String formatDate(java.util.Date date) {
        return formatDateByFormat(date, "yyyy-MM-dd");
    }

    /**
     * 写入日志文件stLogFile
     * @param logFile
     * @param logContent
     */
    public static void writeLog(String logFile, String logContent) {
        if (StringUtils.isEmpty(logContent)) {
            return;
        }
        if (!logContent.endsWith("\r\n")) {
            logContent += "\r\n";
        }
        File fLog = new File(logFile);
        FileUtil.createFile(fLog);
        FileOutputStream fosLog = null;
        try {
            fosLog = new FileOutputStream(logFile, true);
            fosLog.write(logContent.getBytes());
            fosLog.flush();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fosLog.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 当前日期
     *
     * @return
     */
    public static String getDateString() {
        String stResult = null;
        Calendar oCalendar = Calendar.getInstance();

        String stMonth = String.valueOf(oCalendar.get(Calendar.MONTH) + 1);
        String stDay = String.valueOf(oCalendar.get(Calendar.DAY_OF_MONTH));

        if ((oCalendar.get(Calendar.MONTH) + 1) < 10) {
            stMonth = "0".concat(stMonth);
        }
        if (oCalendar.get(Calendar.DAY_OF_MONTH) < 10) {
            stDay = "0".concat(stDay);
        }
        stResult = String.valueOf(oCalendar.get(Calendar.YEAR)) + "-" + stMonth + "-" + stDay;
        return stResult;
    }

    /**
     * 以指定的格式来转换日期
     *
     * @param stDate
     * @param format
     * @return
     */
    public static Date parseDateByFormat(String stDate, String format) {
        Date result = null;
        if (StringUtils.isNotBlank(stDate)) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat(format);
                result = sdf.parse(stDate);
            } catch (Exception ex) {
            }
        }

        return result;
    }

    /**
     * 对象取整
     * @param object
     * @return
     */
	public static int intValue(Object object) {
		if (object == null) {
			return 0;
		}
		if (object instanceof Integer) {
			return ((Integer) object).intValue();
		}  else if (object instanceof Float) {
			return Math.round(((Float) object).floatValue());
		} else if (object instanceof Double) {
			return Math.round(((Double) object).floatValue());
		} else if (object instanceof BigDecimal) {
			return Math.round(((BigDecimal) object).floatValue());
		} else {
			try {
				return Integer.parseInt(ToString(object));
			} catch (Exception e) {
				return 0;
			}
		}
	}

	/**
	 * 对象取浮点
	 * @param object
	 * @return
	 */
	public static float floatValue(Object object) {
		if (object == null) {
			return 0;
		}
		if (object instanceof Integer) {
			return ((Integer) object).intValue();
		}else if (object instanceof Float) {
			return ((Float) object).floatValue();
		} else if (object instanceof Double) {
			return ((Double) object).floatValue();
		}else if (object instanceof BigDecimal) {
			return ((BigDecimal) object).floatValue();
		}else {
			try {
				return Float.parseFloat(ToString(object));
			} catch (Exception e) {
				return 0;
			}
		}
	}
	/**
	 * 对象取double
	 * @param object
	 * @return
	 */
	public static double doubleValue(Object obj) {
		if(obj == null){
			return 0l;
		}
		String temp = obj.toString().trim();
		if(StringUtils.isEmpty(temp))
			return 0l;
		try{
			double num = Double.parseDouble(temp);
			return num;
		}catch(Exception ex){
			return 0l;
		}
	}
	
	/**
	 * 金额格式转换
	 * @param money
	 * @return
	 */
	public static String formatMoney(Double money){
		DecimalFormat myformat = new DecimalFormat();
		myformat.applyPattern(",###,##0.00");
		if (money==null||money==0.00) {
			return "0.00";
		}else{
			money=money+0.0001;
		}
		return myformat.format(money);
	}
	
	/**
	 * 金额格式转换
	 * @param money
	 * @return
	 */
	public static double formatMoneyDoblue(Double money){
		DecimalFormat myformat = new DecimalFormat("#######0.00");
		if (money == null||money == 0.00) {
			return 0.00;
		} else {
			money = money + 0.0001;
		}
		return Double.valueOf(myformat.format(money)).doubleValue();
	}

    /**
     * 验证是否存在除中文、字母、数字 的 特殊字符
     * @param value
     * @return
     */
    public static boolean isExistsSpecialChar(String value) {
    	String str = "^[\u4E00-\u9FA5a-zA-Z0-9]+$";
    	Pattern p = Pattern.compile(str);
    	Matcher m = p.matcher(value);
    	return !m.matches();
    }
    
    public static void saveFile(String filePath, String content) {
		saveFile(filePath, content, "UTF-8");
    }
    
    public static void saveFile(String filePath, String content,String charsetName) {
    	File fLog = new File(filePath);
    	FileUtil.createFile(fLog);
    	FileOutputStream fosLog = null;
    	try {
    		fosLog = new FileOutputStream(filePath, false);
    		fosLog.write(content.getBytes(charsetName));
    		fosLog.flush();
    	} catch (Exception e) {
    		e.printStackTrace();
    	} finally {
    		try {
    			fosLog.close();
    		} catch (Exception e) {
    			e.printStackTrace();
    		}
    	}
    }
    
    public static String getRemoteIp(HttpServletRequest oRequest) {
        String strIP = null;

        String strTranoff = oRequest.getHeader("x-forwarded-for");
        if (strTranoff != null && !"".equals(strTranoff)) {
            strIP = getLastRemoteIP(strTranoff);
        } else {
            strIP = oRequest.getRemoteAddr();
        }
        return strIP;
    }

    public static String getLastRemoteIP(String strXforwordIP) {
        if (strXforwordIP == null) {
            return null;
        }
        String strTranoff = strXforwordIP;
        String strIP = strTranoff;
        int idex = strTranoff.lastIndexOf(",");
        while (idex > 0) {
            strIP = strTranoff.substring(idex + 1, strTranoff.length()).trim();
            strTranoff = strTranoff.substring(0, idex);
            idex = strTranoff.lastIndexOf(",");
        }
        strIP = strTranoff.trim();
        return strIP;
    }
}
