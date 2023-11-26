package com.epam.jmp.dhontar.task7.blurring;

import javax.imageio.ImageIO;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.File;
import java.util.concurrent.ForkJoinPool;

public class Blinder {

    private static final int M_BLUR_WIDTH = 15;
    protected static final int S_THRESHOLD = 100_000;
    public static final String PATH = "Advanced Multithreading/src/main/resources/";

    public static void main(String[] args) throws Exception {
        String srcName = PATH + "tree.jpg";
        File srcFile = new File(srcName);
        BufferedImage image = ImageIO.read(srcFile);

        System.out.println("Source image: " + srcName);

        RenderedImage blurredImage = blur(image);

        String dstName = PATH + "blurred-tree.jpg";
        File dstFile = new File(dstName);

        ImageIO.write(blurredImage, "JPG", dstFile);
        System.out.println("Output image: " + dstName);
    }

    public static BufferedImage blur(BufferedImage srcImage) {
        int w = srcImage.getWidth();
        int h = srcImage.getHeight();

        int[] src = srcImage.getRGB(0, 0, w, h, null, 0, w);
        int[] dst = new int[src.length];

        System.out.println("Array size is " + src.length);
        System.out.println("Threshold is " + S_THRESHOLD);

        int processors = Runtime.getRuntime().availableProcessors();
        System.out.println(processors + " processor"
                + (processors != 1 ? "s are " : " is ")
                + "available");

        ForkBlur fb = new ForkBlur(src, 0, src.length, dst, M_BLUR_WIDTH, S_THRESHOLD);

        ForkJoinPool pool = new ForkJoinPool();

        long startTime = System.currentTimeMillis();
        pool.invoke(fb);
        long endTime = System.currentTimeMillis();

        System.out.println("Image blur took " + (endTime - startTime) +
                " milliseconds.");

        BufferedImage dstImage =
                new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
        dstImage.setRGB(0, 0, w, h, dst, 0, w);

        return dstImage;
    }
}
