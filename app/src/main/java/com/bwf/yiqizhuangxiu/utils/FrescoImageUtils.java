package com.bwf.yiqizhuangxiu.utils;

import android.graphics.drawable.Animatable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.controller.ControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;

import me.relex.photodraweeview.PhotoDraweeView;

/**
 * Created by Administrator on 2016/11/22.
 */

public class FrescoImageUtils {
    /**
     * 设置SimpleDraweeView的Controller 根据给定的宽度按照图片比例设置宽高
     *
     * @param simpleDraweeView
     * @param imageUri
     * @param imageWidth
     */
    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imageUri, final int imageWidth) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                layoutParams.width = imageWidth;
                layoutParams.height = (int) ((float) (imageWidth * height) / (float) width);
                simpleDraweeView.setLayoutParams(layoutParams);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                LogUtils.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                if (throwable != null) {
                    throwable.printStackTrace();
                }
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setControllerListener(controllerListener)
                .setUri(Uri.parse(imageUri))
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 设置SimpleDraweeView的Controller 根据给定的宽度和高度按照图片比例设置宽高使宽高不会超过限定值导致部分图片无法显示
     *
     * @param simpleDraweeView
     * @param imageUri
     * @param maxWidth
     * @param maxHeight
     */
    public static void setControllerListener(final SimpleDraweeView simpleDraweeView, String imageUri, final int maxWidth, final int maxHeight) {
        final ViewGroup.LayoutParams layoutParams = simpleDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                if (width / (float) height > maxWidth / (float) maxHeight) {
                    layoutParams.width = maxWidth;
                    layoutParams.height = (int) ((float) (maxWidth * height) / (float) width);
                } else {
                    layoutParams.height = maxHeight;
                    layoutParams.width = (int) ((float) (maxHeight * width) / (float) height);
                }
                simpleDraweeView.setLayoutParams(layoutParams);
                simpleDraweeView.setScaleType(ImageView.ScaleType.FIT_XY);
            }

            @Override
            public void onIntermediateImageSet(String id, @Nullable ImageInfo imageInfo) {
                LogUtils.d("TAG", "Intermediate image received");
            }

            @Override
            public void onFailure(String id, Throwable throwable) {
                if (throwable != null) {
                    throwable.printStackTrace();
                }
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setControllerListener(controllerListener)
                .setUri(Uri.parse(imageUri))
                .build();
        simpleDraweeView.setController(controller);
    }

    /**
     * 设置SimpleDraweeView的Controller 可以进行手势操作例如双指缩放的适应宽高的设置
     * 1、需要使用PhotoDraweeView
     * 2、控件大小应当剂量大
     *
     * @param photoDraweeView
     * @param imageUri
     * @param maxWidth
     * @param maxHeight
     */
    public static void setZoomableControllerListener(final PhotoDraweeView photoDraweeView, String imageUri, final int maxWidth, final int maxHeight) {
        final ViewGroup.LayoutParams layoutParams = photoDraweeView.getLayoutParams();
        ControllerListener controllerListener = new BaseControllerListener<ImageInfo>() {
            @Override
            public void onFinalImageSet(String id, @Nullable ImageInfo imageInfo, @Nullable Animatable anim) {
                if (imageInfo == null || photoDraweeView == null) {
                    return;
                }
                int height = imageInfo.getHeight();
                int width = imageInfo.getWidth();
                if (width / (float) height > maxWidth / (float) maxHeight) {
                    layoutParams.width = maxWidth;
                    layoutParams.height = (int) ((float) (maxWidth * height) / (float) width);
                } else {
                    layoutParams.height = maxHeight;
                    layoutParams.width = (int) ((float) (maxHeight * width) / (float) height);
                }
                photoDraweeView.update(layoutParams.width, layoutParams.width);
                layoutParams.width = maxWidth;
                layoutParams.height = maxHeight;
                photoDraweeView.setLayoutParams(layoutParams);
            }
        };
        DraweeController controller = Fresco.newDraweeControllerBuilder()
                .setAutoPlayAnimations(true)
                .setOldController(photoDraweeView.getController())
                .setControllerListener(controllerListener)
                .setUri(Uri.parse(imageUri))
                .build();
        photoDraweeView.setController(controller);
    }
}
