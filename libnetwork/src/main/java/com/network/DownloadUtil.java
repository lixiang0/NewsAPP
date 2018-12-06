package com.network;

import android.os.Environment;
import android.support.annotation.NonNull;
import android.util.Log;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public abstract class DownloadUtil {
    private static final String TAG = "news DownloadUtil";

    private final OkHttpClient okHttpClient;

    public DownloadUtil() {
        okHttpClient = new OkHttpClient();
    }

    /**
     * @param url     下载连接
     * @param saveDir 储存下载文件的SDCard目录
     */
    public void download(final String url, final String saveDir) {
        Log.e(TAG, "download: start");
        Request request = new Request.Builder().url(url).build();
        okHttpClient.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                Log.e(TAG, "onFailure: ");
                // 下载失败
                onDownloadFailed(null, e);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                Log.e(TAG, "onResponse: ");
                InputStream is = null;
                byte[] buf = new byte[1024];
                int len = 0;
                FileOutputStream fos = null;
                // 储存下载文件的目录
                String savePath = isExistDir(saveDir);
                File file = new File(savePath, getNameFromUrl(response, url));
                try {
                    is = response.body().byteStream();
                    long total = response.body().contentLength();
                    fos = new FileOutputStream(file);
                    long sum = 0;
                    while ((len = is.read(buf)) != -1) {
                        fos.write(buf, 0, len);
                        sum += len;
                        int progress = (int) (sum * 1.0f / total * 100);
                        // 下载中
                        onDownloading(progress);
                    }
                    fos.flush();
                    Log.e(TAG, "onResponse: try 9");
                    // 下载完成
                    onDownloadSuccess(file);
                } catch (Exception e) {
                    Log.e(TAG, "onResponse: e " + e.getMessage());
                    onDownloadFailed(file, e);
                } finally {
                    Log.e(TAG, "onResponse:  finally");
                    try {
                        if (is != null)
                            is.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    try {
                        if (fos != null)
                            fos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }

    /**
     * @param saveDir
     * @return
     * @throws IOException 判断下载目录是否存在
     */
    private String isExistDir(String saveDir) throws IOException {
        // 下载位置
        File downloadFile = new File(Environment.getExternalStorageDirectory(), saveDir);
        if (!downloadFile.mkdirs()) {
            downloadFile.createNewFile();
        }
        String savePath = downloadFile.getAbsolutePath();
        return savePath;
    }

    /**
     * @param url
     * @return 从下载连接中解析出文件名
     */
    @NonNull
    private String getNameFromUrl(Response response, String url) {
        String fileName = url.substring(url.lastIndexOf("/") + 1);
        if (!fileName.contains(".")) {
            String fileType = getHeaderFileType(response);
            fileName += fileType;
        }
        return fileName;
    }

    /**
     * 解析文件头
     * Content-Disposition:attachment;filename=FileName.txt
     * Content-Disposition: attachment; filename*="UTF-8''%E6%9B%BF%E6%8D%A2%E5%AE%9E%E9%AA%8C%E6%8A%A5%E5%91%8A.pdf"
     */
    private static String getHeaderFileType(Response response) {
        String dispositionHeader = response.header("Content-Type");
        if ("application/vnd.android.package-archive".equals(dispositionHeader)) {
            return ".apk";
        } else {
            return "";
        }
    }

    /**
     * 下载成功
     */
    public abstract void onDownloadSuccess(File file);

    /**
     * @param progress 下载进度
     */
    public abstract void onDownloading(int progress);

    /**
     * 下载失败
     *
     * @param e
     */
    public abstract void onDownloadFailed(File file, Exception e);


}
