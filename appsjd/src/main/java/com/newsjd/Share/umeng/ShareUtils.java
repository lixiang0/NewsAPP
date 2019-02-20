package com.newsjd.Share.umeng;

import android.app.Activity;
import android.graphics.Bitmap;


import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMAuthListener;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.io.File;

public class ShareUtils {
    /**
     * 获取 分享动作
     *
     * @param activity 活动页面
     * @return 分享实例
     */
    public static ShareAction getShareAction(Activity activity) {
        return new ShareAction(activity);
    }

    /**
     * 获取web参数
     *
     * @param url         路径
     * @param title       标题
     * @param thumb       缩略图
     * @param description 简述
     * @return 网页实例
     */
    public static UMWeb getUMWeb(String url, String title, UMImage thumb, String description) {
        UMWeb web = new UMWeb(url);
        web.setTitle(title);//标题
        web.setThumb(thumb);  //缩略图
        web.setDescription(description);//描述
        return web;
    }

    /**
     * 获取图片
     *
     * @param activity 活动页面
     * @param netUrl   网络图片地址
     * @return 图片实例
     */
    public static UMImage getUMImage(Activity activity, String netUrl) {
        UMImage image = new UMImage(activity, netUrl);//网络图片
//用户设置的图片大小最好不要超过250k，缩略图不要超过18k，如果超过太多（最好不要分享1M以上的图片，压缩效率会很低），图片会被压缩。用户可以设置压缩的方式：
        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
//        image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享压缩格式设置
        image.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        return image;
    }

    /**
     * 获取图片
     */
    public static UMImage getUMImage(Activity activity, File file) {
        UMImage image = new UMImage(activity, file);//本地文件
        //用户设置的图片大小最好不要超过250k，缩略图不要超过18k，如果超过太多（最好不要分享1M以上的图片，压缩效率会很低），图片会被压缩。用户可以设置压缩的方式：
        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
//        image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享压缩格式设置
        image.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        return image;
    }

    /**
     * 获取图片
     */
    public static UMImage getUMImage(Activity activity, int rdrawable) {
        UMImage image = new UMImage(activity, rdrawable);//资源文件
        //用户设置的图片大小最好不要超过250k，缩略图不要超过18k，如果超过太多（最好不要分享1M以上的图片，压缩效率会很低），图片会被压缩。用户可以设置压缩的方式：
        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
//        image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享压缩格式设置
        image.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        return image;
    }

    /**
     * 获取图片
     */
    public static UMImage getUMImage(Activity activity, Bitmap bitmap) {
        UMImage image = new UMImage(activity, bitmap);//bitmap文件
        //用户设置的图片大小最好不要超过250k，缩略图不要超过18k，如果超过太多（最好不要分享1M以上的图片，压缩效率会很低），图片会被压缩。用户可以设置压缩的方式：
        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
//        image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享压缩格式设置
        image.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        return image;
    }

    /**
     * 获取图片
     */
    public static UMImage getUMImage(Activity activity, byte[] bytes) {
        UMImage image = new UMImage(activity, bytes);//字节流
        //用户设置的图片大小最好不要超过250k，缩略图不要超过18k，如果超过太多（最好不要分享1M以上的图片，压缩效率会很低），图片会被压缩。用户可以设置压缩的方式：
        image.compressStyle = UMImage.CompressStyle.SCALE;//大小压缩，默认为大小压缩，适合普通很大的图
//        image.compressStyle = UMImage.CompressStyle.QUALITY;//质量压缩，适合长图的分享压缩格式设置
        image.compressFormat = Bitmap.CompressFormat.PNG;//用户分享透明背景的图片可以设置这种方式，但是qq好友，微信朋友圈，不支持透明背景图片，会变成黑色
        return image;
    }

    /**
     * 设置文本
     */
    public static ShareAction setText(ShareAction shareAction, String text) {
        return shareAction.withText(text);
    }

    /**
     * 设置图片
     */
    public static ShareAction setImage(ShareAction shareAction, UMImage image) {
        return shareAction.withMedia(image);
    }

    /**
     * 设置网页
     */
    public static ShareAction setWeb(ShareAction shareAction, UMWeb web) {
        return shareAction.withMedia(web);
    }

    /**
     * 设置平台
     */
    public static ShareAction setPlatform(ShareAction shareAction) {
        return shareAction.setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN);
    }

    /**
     * 设置回调
     */
    public static ShareAction setCallback(ShareAction shareAction, UMShareListener umShareListener) {
        return shareAction.setCallback(umShareListener);
    }

    /**
     * 参考模板：分享（带面板）
     */
    public static void shareWithUI(Activity activity, String text, UMShareListener umShareListener) {
        new ShareAction(activity)
                .withText(text)
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.QQ, SHARE_MEDIA.WEIXIN)
                .setCallback(umShareListener).open();
    }

    /**
     * 参考模板：分享（不带面板）
     */
    public static void shareWithOutUI(Activity activity, UMShareListener umShareListener, String text) {
        new ShareAction(activity)
                .setPlatform(SHARE_MEDIA.QQ)//传入平台
                .withText(text)//分享内容
                .setCallback(umShareListener)//回调监听器
                .share();
    }


    /**
     * 分享视频,只能使用网络视频：
     */
    public static void shareVideo(Activity activity) {
     /*   UMVideo video = new UMVideo(videourl);
        video.setTitle("This is music title");//视频的标题
        video.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");//视频的缩略图
        video.setDescription("my description");//视频的描述
        new ShareAction(activity).withText("hello").withMedia(video).share();*/
    }

    /**
     * 分享音乐
     */
    public static void shareMusic(Activity activity) {
        /*UMusic music = new UMusic(musicurl);//音乐的播放链接
        music.setTitle("This is music title");//音乐的标题
        music.setThumb("http://www.umeng.com/images/pic/social/chart_1.png");//音乐的缩略图
        music.setDescription("my description");//音乐的描述
        music.setmTargetUrl(Defaultcontent.url);//音乐的跳转链接

        new ShareAction(activity).withMedia(music).share();*/
    }

    /**
     * 分享Gif
     * 只有微信好友分享支持Emoji表情，其他平台暂不支持
     */
    public static void shareGif(Activity activity) {
     /*   UMEmoji emoji = new UMEmoji(this, "http://img5.imgtn.bdimg.com/it/u=2749190246,3857616763&fm=21&gp=0.jpg");
        emoji.setThumb(new UMImage(this, R.drawable.thumb));
        new ShareAction(ShareActivity.this)
                .withMedia(emoji).share();*/
    }

    /**
     * 分享微信小程序
     */
    public static void shareWeChatModule(Activity activity) {
    /*    UMMin umMin = new UMMin(Defaultcontent.url);
        //兼容低版本的网页链接
        umMin.setThumb(imagelocal);
        // 小程序消息封面图片
        umMin.setTitle(Defaultcontent.title);
        // 小程序消息title
        umMin.setDescription(Defaultcontent.text);
        // 小程序消息描述
        umMin.setPath("pages/page10007/xxxxxx");
        //小程序页面路径
        umMin.setUserName("gh_xxxxxxxxxxxx");
        // 小程序原始id,在微信平台查询
        new ShareAction(ShareDetailActivity.this)
                .withMedia(umMin)
                .setPlatform(share_media)
                .setCallback(shareListener).share();*/
    }

    /**
     * 获取用户信息
     */
    public static void getUserInfo(Activity activity, UMAuthListener umAuthListener) {
//        mShareAPI.getPlatformInfo(activity, SHARE_MEDIA.SINA, umAuthListener);
    }
}
