package io.easyspring.framework.common.utils;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;

/**
 * 文件工具类
 *
 * @author summer
 * @date 2019-02-12 18:09
 * @version V1.0.0-RELEASE
 */
public class FileUtils {

    /**
     * 根据当前时间, 获取由年月日拼接而成的相对路径
     *
     * @author summer
     * @date 2019-02-12 18:14
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    public static String getDatePath() {
        // 获取系统时间的相关数据,用于动态生成文件夹的名称
        Calendar now = Calendar.getInstance();
        int year = now.get(Calendar.YEAR);
        int month = now.get(Calendar.MONTH) + 1;
        int day = now.get(Calendar.DAY_OF_MONTH);

        return "/" + year + "/" + month + "/" + day;
    }

    /**
     * 根据文件名称, 获取文件后缀
     *
     * @author summer
     * @date 2019-02-12 18:15
     * @param fileName 文件名称
     * @return java.lang.String
     * @version V1.0.0-RELEASE
     */
    public static String getFileSuffix(String fileName) {
        // 参数合法性校验
        if (StringUtils.isEmpty(fileName)) {
            return null;
        }

        // 获取文件名称的最后一个点的位置
        int lastPointIndex = fileName.lastIndexOf(".");

        // 获取并返回文件的后缀名
        return fileName.substring(lastPointIndex);
    }

    /**
     * 根据传入的文件地址, 如果文件不存在则创建文件对象
     *
     * @author summer
     * @date 2019-02-12 18:17
     * @param targetAbsoluteFileDir 文件的父级地址
     * @param fileName 文件名称
     * @return java.io.File
     * @version V1.0.0-RELEASE
     */
    public static File checkAndCreateFile(String targetAbsoluteFileDir, String fileName) throws IOException {
        //
        if (StringUtils.isEmpty(targetAbsoluteFileDir) || StringUtils.isEmpty(fileName)) {
            return null;
        }

        File targetDirFile = new File(targetAbsoluteFileDir);
        // 如果目标文件不存在, 则创建新的文件
        if (!targetDirFile.exists()) {
            targetDirFile.mkdirs();
        }

        File targetPathFile = new File(targetDirFile, fileName);
        if (!targetPathFile.exists()) {
            targetPathFile.createNewFile();
        }
        return targetPathFile;
    }

    /**
     * 根据传入的文件地址, 如果文件不存在则创建文件对象
     *
     * @author summer
     * @date 2019-02-12 18:20
     * @param targetPath 文件地址
     * @return java.io.File
     * @version V1.0.0-RELEASE
     */
    public static File checkAndCreateFile(String targetPath) throws IOException {
        // 参数合法性校验
        if (StringUtils.isEmpty(targetPath)) {
            return null;
        }

        // 创建文件对象, 用于生成相关的信息
        File file = new File(targetPath);

        return checkAndCreateFile(file.getParent(), file.getName());
    }

    /**
     * 删除指定的文件
     *
     * @author summer
     * @date 2019-02-12 18:19
     * @param filePath 文件的绝对路径
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    public static boolean deleteFile(String filePath) {
        // 参数合法性校验
        if (StringUtils.isEmpty(filePath)) {
            return false;
        }

        // 创建文件对象
        File file = new File(filePath);
        // 检查文件是否存在
        if (file.exists() && file.isFile()) {
            // 如果存在, 则执行删除操作
            return file.delete();
        }

        // 其他情况直接返回错误
        return false;
    }

    /**
     * 删除文件
     *
     * @author summer
     * @date 2019-02-12 18:19
     * @param file 需要删除的文件
     * @return boolean
     * @version V1.0.0-RELEASE
     */
    public static boolean deleteFile(File file) {
        if (file != null && file.exists() && file.isFile()) {
            return file.delete();
        }

        return false;
    }
}
