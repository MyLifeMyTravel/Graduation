package com.lion.graduation2.util;

import android.os.Environment;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Lion on 2015/4/19.
 */
public class FileUtils {

    /**
     * 判断目录是否存在
     *
     * @param dirPath
     * @return
     */
    public static boolean isDirExist(String dirPath) {
        boolean isExist = false;
        File dir = new File(dirPath);
        if (dir.isDirectory() && dir.exists()) {
            isExist = true;
        }
        return isExist;
    }

    /**
     * 创建目录
     *
     * @param dirPath
     */
    public static void makeDir(String dirPath) {
        File dir = new File(dirPath);
        dir.mkdirs();
    }

    /**
     * 检查是否存在SDCard
     *
     * @return
     */
    public static boolean hasSdcard() {
        String state = Environment.getExternalStorageState();
        if (state.equals(Environment.MEDIA_MOUNTED)) {
            return true;
        } else {
            return false;
        }
    }

    private static boolean isAppend = false;// 是否以追加方式将内容写入到文件中去

    /**
     * 根据isAppend,判断是否以可追加方式将数据content写入到filePath指定的文件中
     *
     * @param filePath
     *            文件路径
     * @param content
     *            文件内容
     * @param isAppend
     *            true 表示以可追加方式将数据写入文件，若文件不存在，则创建
     */
    public static void writeFile(String filePath, String content,
                                 boolean isAppend) {
        FileUtils.isAppend = isAppend;
        File file = new File(filePath);
        writeFile(file, content);
    }

    /**
     * 将数据content写入到filePath指定的文件中，若文件不存在，则创建
     *
     * @param filePath
     *            文件路径
     * @param content
     *            文件内容
     */
    public static void writeFile(String filePath, String content) {
        File file = new File(filePath);
        writeFile(file, content);
    }

    /**
     * 数据content写入到文件file中，若文件不存在，则创建
     *
     * @param file
     *            要写入内容的文件
     * @param content
     *            文件内容
     */
    public static void writeFile(File file, String content) {
        FileOutputStream fos = null;

        try {
            fos = new FileOutputStream(file, isAppend);
            fos.write(content.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            isAppend = false;// 将isAppend重新置为false
            try {
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件
     *
     * @param filePath
     *            文件路径
     * @return 字节数组
     * @throws FileNotFoundException
     */
    public static byte[] readFile(String filePath) throws FileNotFoundException {
        File file = new File(filePath);
        return readFile(file);
    }

    /**
     * 读取文件
     *
     * @param file
     *            需要读取的文件
     * @return 字节数组
     * @throws FileNotFoundException
     */
    public static byte[] readFile(File file) throws FileNotFoundException {
        if (!file.exists())
            throw new FileNotFoundException(file + "不存在");

        FileInputStream fis = null;
        byte[] buf = null;

        try {
            fis = new FileInputStream(file);
            buf = new byte[fis.available()];
            fis.read(buf);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    fis = null;
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return buf;
    }

    /**
     * 用给定的路径创建目录
     *
     * @param dirPath
     *            路径
     * @return
     */
    public static void createDir(String dirPath) {
        File file = new File(dirPath);
        createDir(file);
    }

    /**
     * 用给定的File创建目录
     *
     * @param dir
     *            目录文件
     * @return
     */
    public static void createDir(File dir) {
        if (!dir.exists())
            dir.mkdirs();
    }

    /**
     * 复制文件，将文件从src复制到des，若文件存在，则覆盖，否则，创建一个新文件
     *
     * @param src
     *            源文件路径
     * @param des
     *            目标文件路径
     */
    public static void copyFile(String src, String des) {
        File srcFile = new File(src);
        File desFile = new File(des);
        copyFile(srcFile, desFile);
    }

    /**
     * 复制文件，将源文件复制到目标文件
     *
     * @param srcFile
     *            源文件
     * @param desFile
     *            目标文件
     */
    public static void copyFile(File srcFile, File desFile) {
        FileInputStream fis = null;
        FileOutputStream fos = null;

        try {
            fis = new FileInputStream(srcFile);
            fos = new FileOutputStream(desFile);

            byte[] buf = new byte[8 * 1024];
            int c;
            while ((c = fis.read(buf)) != -1) {
                fos.write(buf, 0, c);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fis != null) {
                    fis.close();
                    fis = null;
                }
                if (fos != null) {
                    fos.close();
                    fos = null;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
