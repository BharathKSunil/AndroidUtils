package com.bharathksunil.utils

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.ColorFilter
import android.graphics.Paint
import android.graphics.PixelFormat
import android.graphics.Rect
import android.graphics.RectF
import android.graphics.Typeface
import android.graphics.drawable.ShapeDrawable
import android.graphics.drawable.shapes.OvalShape
import android.graphics.drawable.shapes.RectShape
import android.graphics.drawable.shapes.RoundRectShape

/**
 * TextDrawable This light-weight library provides images with letter/text like the Gmail app
 * This class is imported from com.amulyakhare:com.amulyakhare.textdrawable:1.0.1
 *
 * @author Bharath on 15-08-2017.
 */
class TextDrawable//todo: Add documentation and code cleanup
private constructor(builder: Builder) : ShapeDrawable(builder.shape) {
    private val mTextPaint: Paint
    private val mBorderPaint: Paint
    private val mText: String?
    private val mColor: Int
    private val mShape: RectShape?
    private val mDrawableHeight: Int
    private val mDrawableWidth: Int
    private val mTextFontSize: Int
    private val mDrawableRadius: Float
    private val mDrawableBorderThickness: Int

    init {

        // shape properties
        mShape = builder.shape
        mDrawableHeight = builder.height
        mDrawableWidth = builder.width
        mDrawableRadius = builder.mDrawableRadius

        // text and color
        mText = if (builder.toUpperCase) builder.mDrawableText!!.toUpperCase() else builder.mDrawableText
        mColor = builder.color

        // text paint settings
        mTextFontSize = builder.fontSize
        mTextPaint = Paint()
        mTextPaint.color = builder.mTextColor
        mTextPaint.isAntiAlias = true
        mTextPaint.isFakeBoldText = builder.isBold
        mTextPaint.style = Paint.Style.FILL
        mTextPaint.typeface = builder.font
        mTextPaint.textAlign = Paint.Align.CENTER
        mTextPaint.strokeWidth = builder.borderThickness.toFloat()

        // border paint settings
        mDrawableBorderThickness = builder.borderThickness
        mBorderPaint = Paint()
        mBorderPaint.color = getDarkerShade(mColor)
        mBorderPaint.style = Paint.Style.STROKE
        mBorderPaint.strokeWidth = mDrawableBorderThickness.toFloat()

    }

    private fun getDarkerShade(color: Int): Int {
        return Color.rgb((SHADE_FACTOR * Color.red(color)).toInt(),
                (SHADE_FACTOR * Color.green(color)).toInt(),
                (SHADE_FACTOR * Color.blue(color)).toInt())
    }

    override fun draw(canvas: Canvas) {
        super.draw(canvas)
        val r = bounds


        // draw border
        if (mDrawableBorderThickness > 0) {
            drawBorder(canvas)
        }

        val count = canvas.save()
        canvas.translate(r.left.toFloat(), r.top.toFloat())

        // draw text
        val width = if (this.mDrawableWidth < 0) r.width() else this.mDrawableWidth
        val height = if (this.mDrawableHeight < 0) r.height() else this.mDrawableHeight
        val fontSize = if (this.mTextFontSize < 0) Math.min(width, height) / 2 else this.mTextFontSize
        mTextPaint.textSize = fontSize.toFloat()
        canvas.drawText(mText!!, (width / 2).toFloat(), height / 2 - (mTextPaint.descent() + mTextPaint.ascent()) / 2, mTextPaint)

        canvas.restoreToCount(count)

    }

    private fun drawBorder(canvas: Canvas) {
        val rect = RectF(bounds)
        rect.inset((mDrawableBorderThickness / 2).toFloat(), (mDrawableBorderThickness / 2).toFloat())

        if (mShape is OvalShape) {
            canvas.drawOval(rect, mBorderPaint)
        } else if (mShape is RoundRectShape) {
            canvas.drawRoundRect(rect, mDrawableRadius, mDrawableRadius, mBorderPaint)
        } else {
            canvas.drawRect(rect, mBorderPaint)
        }
    }

    override fun setAlpha(alpha: Int) {
        mTextPaint.alpha = alpha
    }

    override fun setColorFilter(cf: ColorFilter?) {
        mTextPaint.colorFilter = cf
    }

    override fun getOpacity(): Int {
        return PixelFormat.TRANSLUCENT
    }

    override fun getIntrinsicWidth(): Int {
        return mDrawableWidth
    }

    override fun getIntrinsicHeight(): Int {
        return mDrawableHeight
    }

    interface IConfigBuilder {
        fun width(width: Int): IConfigBuilder

        fun height(height: Int): IConfigBuilder

        fun textColor(color: Int): IConfigBuilder

        fun withBorder(thickness: Int): IConfigBuilder

        fun useFont(font: Typeface): IConfigBuilder

        fun fontSize(size: Int): IConfigBuilder

        fun bold(): IConfigBuilder

        fun toUpperCase(): IConfigBuilder

        fun endConfig(): IShapeBuilder
    }

    interface IBuilder {

        fun build(text: String, color: Int): TextDrawable
    }

    interface IShapeBuilder {

        fun beginConfig(): IConfigBuilder

        fun rect(): IBuilder

        fun round(): IBuilder

        fun roundRect(radius: Int): IBuilder

        fun buildRect(text: String, color: Int): TextDrawable

        fun buildRoundRect(text: String, color: Int, radius: Int): TextDrawable

        fun buildRound(text: String, color: Int): TextDrawable
    }

    private class Builder private constructor() : IConfigBuilder, IShapeBuilder, IBuilder {

        private var mTextColor: Int = 0
        internal var mDrawableRadius: Float = 0.toFloat()
        private var mDrawableText: String? = null
        private var color: Int = 0
        private var borderThickness: Int = 0
        private var width: Int = 0
        private var height: Int = 0
        private var font: Typeface? = null
        private var shape: RectShape? = null
        private var fontSize: Int = 0
        private var isBold: Boolean = false
        private var toUpperCase: Boolean = false

        init {
            mDrawableText = ""
            color = Color.GRAY
            mTextColor = Color.WHITE
            borderThickness = 0
            width = -1
            height = -1
            shape = RectShape()
            font = Typeface.create("sans-serif-light", Typeface.NORMAL)
            fontSize = -1
            isBold = false
            toUpperCase = false
        }

        override fun width(width: Int): IConfigBuilder {
            this.width = width
            return this
        }

        override fun height(height: Int): IConfigBuilder {
            this.height = height
            return this
        }

        override fun textColor(color: Int): IConfigBuilder {
            this.mTextColor = color
            return this
        }

        override fun withBorder(thickness: Int): IConfigBuilder {
            this.borderThickness = thickness
            return this
        }

        override fun useFont(font: Typeface): IConfigBuilder {
            this.font = font
            return this
        }

        override fun fontSize(size: Int): IConfigBuilder {
            this.fontSize = size
            return this
        }

        override fun bold(): IConfigBuilder {
            this.isBold = true
            return this
        }

        override fun toUpperCase(): IConfigBuilder {
            this.toUpperCase = true
            return this
        }

        override fun beginConfig(): IConfigBuilder {
            return this
        }

        override fun endConfig(): IShapeBuilder {
            return this
        }

        override fun rect(): IBuilder {
            this.shape = RectShape()
            return this
        }

        override fun round(): IBuilder {
            this.shape = OvalShape()
            return this
        }

        override fun roundRect(radius: Int): IBuilder {
            this.mDrawableRadius = radius.toFloat()
            val radii = floatArrayOf(radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat(), radius.toFloat())
            this.shape = RoundRectShape(radii, null, null)
            return this
        }

        override fun buildRect(text: String, color: Int): TextDrawable {
            rect()
            return build(text, color)
        }

        override fun buildRoundRect(text: String, color: Int, radius: Int): TextDrawable {
            roundRect(radius)
            return build(text, color)
        }

        override fun buildRound(text: String, color: Int): TextDrawable {
            round()
            return build(text, color)
        }

        override fun build(text: String, color: Int): TextDrawable {
            this.color = color
            this.mDrawableText = text
            return TextDrawable(this)
        }
    }

    companion object {

        private val SHADE_FACTOR = 0.9f

        fun builder(): IShapeBuilder {
            return Builder()
        }
    }
}
