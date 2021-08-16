package ipca.example.customviews

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Rect
import android.os.Build
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.annotation.RequiresApi

//
// Created by lourencogomes on 20/10/2020.
//
class LightBrightnessView : View {

    var touchY = 0F



    constructor(context: Context?) : super(context) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?) : super(context, attrs) {
        init()
    }
    constructor(context: Context?, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    ){
        init()
    }

    var setOnValueChange : ((value : Float)->Unit)? = null

    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)
    constructor(
        context: Context?,
        attrs: AttributeSet?,
        defStyleAttr: Int,
        defStyleRes: Int
    ) : super(context, attrs, defStyleAttr, defStyleRes){
        init()
    }

    fun init(){

    }

    override fun draw(canvas: Canvas?) {
        super.draw(canvas)

        val paint = Paint()
        paint.color = Color.BLUE
        paint.strokeWidth = 10F
        paint.style = Paint.Style.STROKE

        val rect = Rect(10, 10, width-10, height-10)
        canvas?.drawRect(rect, paint )

        val rect2 = Rect(10, touchY.toInt(), width-10, height-10)

        paint.color = Color.YELLOW
        paint.style = Paint.Style.FILL

        canvas?.drawRect(rect2, paint )


    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val y = event?.y
        when (event?.action){
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_MOVE -> {
                touchY = y?:0F
                invalidate()
                setOnValueChange?.let {

                    var v = (100 - (touchY/height)*100 )
                    if (v > 100) v = 100F
                    if (v < 0 ) v = 0F
                    it.invoke(v)
                }
            }
        }

        return true
    }
}