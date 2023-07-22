package com.mantra.counter;

import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.ProgressBar;

/* loaded from: classes.dex */
public class ProgressbarAnimation extends Animation {
    private float from;
    private ProgressBar progressBar;
    private float to;

    public ProgressbarAnimation(ProgressBar progressBar, float f, float f2) {
        this.progressBar = progressBar;
        this.from = f;
        this.to = f2;
    }

    @Override // android.view.animation.Animation
    protected void applyTransformation(float f, Transformation transformation) {
        super.applyTransformation(f, transformation);
        float f2 = this.from;
        this.progressBar.setProgress((int) (f2 + ((this.to - f2) * f)));
    }
}
