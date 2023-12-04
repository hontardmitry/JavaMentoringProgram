package com.epam.jmp.dhontar.task7.blurring;

import java.util.concurrent.RecursiveAction;

public class ForkBlur extends RecursiveAction {

    // Processing window size; should be odd.
    private final int mBlurWidth;
    private final int sThreshold;
    private final int[] mSource;
    private final int mStart;
    private final int mLength;
    private final int[] mDestination;


    public ForkBlur(int[] src, int start, int length, int[] dst,
                    int mBlurWidth, int sThreshold) {
        mSource = src;
        mStart = start;
        mLength = length;
        mDestination = dst;
        this.mBlurWidth = mBlurWidth;
        this.sThreshold = sThreshold;
    }

    protected void computeDirectly() {
        int sidePixels = (mBlurWidth - 1) / 2;
        for (int index = mStart; index < mStart + mLength; index++) {
            // Calculate average.
            float rt = 0, gt = 0, bt = 0;
            for (int mi = -sidePixels; mi <= sidePixels; mi++) {
                int mindex = Math.min(Math.max(mi + index, 0),
                        mSource.length - 1);
                int pixel = mSource[mindex];
                rt += (float) ((pixel & 0x00ff0000) >> 16) / mBlurWidth;
                gt += (float) ((pixel & 0x0000ff00) >> 8) / mBlurWidth;
                bt += (float) ((pixel & 0x000000ff) >> 0) / mBlurWidth;
            }

            // Reassemble destination pixel.
            int dPixel = (0xff000000) |
                    (((int) rt) << 16) |
                    (((int) gt) << 8) |
                    (((int) bt) << 0);
            mDestination[index] = dPixel;
        }
    }

    @Override
    protected void compute() {
        if (mLength < sThreshold) {
            computeDirectly();
            return;
        }
        int split = mLength / 2;
        invokeAll(new ForkBlur(mSource, mStart, split, mDestination, mBlurWidth,
                        sThreshold),
                new ForkBlur(mSource, mStart + split, mLength - split,
                        mDestination, mBlurWidth, sThreshold));
    }
}