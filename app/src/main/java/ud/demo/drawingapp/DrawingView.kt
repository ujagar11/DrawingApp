package ud.demo.drawingapp

import android.content.Context
import android.graphics.*

import android.util.AttributeSet
import android.util.TypedValue
import android.view.MotionEvent
import android.view.View

class DrawingView(context:Context,attrs: AttributeSet): View(context,attrs) {
    //it represents the current path being drawn
    private var mDrawPath:CustomPath?=null
    private  var mCanvasBitmap:Bitmap?=null
    private var mDrawPaint: Paint?=null
    //used to handle canvas when drawing the bitmap
    private var mCanvasPaint:Paint?=null
    private var mBrushSize: Float=0.toFloat()
    private var color =Color.BLACK
    private var canvas:Canvas?=null

    private val mPaths =ArrayList<CustomPath>()
    init {
        setUpDrawing()
    }
    private fun setUpDrawing(){
        mDrawPaint= Paint()
        mDrawPath= CustomPath(color,mBrushSize)
        // !!used to see that variable does not contain a null variable
        mDrawPaint!!.color=color
        mDrawPaint!!.style=Paint.Style.STROKE
        mDrawPaint!!.strokeJoin=Paint.Join.ROUND
        mDrawPaint!!.strokeCap=Paint.Cap.ROUND
        //DITHER_FLAG is used to increase the rendering quality of paint or drawing
        mCanvasPaint=Paint(Paint.DITHER_FLAG)
      //  mBrushSize=0.toFloat() no need set we are set size from main by brushSize func()


    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        // we have created a Canvas object which is a 2d surface for drawing and content on that surface will be hold by bitmap
        mCanvasBitmap = Bitmap.createBitmap(w,h,Bitmap.Config.ARGB_8888)
        canvas= Canvas(mCanvasBitmap!!)
    }
    //Canvas? to Canvas has been done
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(mCanvasBitmap!!,0f,0f,mCanvasPaint)
        //showing saved paths
        for (path in mPaths){
            mDrawPaint!!.strokeWidth=path!!.brushThickness
            mDrawPaint!!.color=path!!.color
            canvas.drawPath(path!!,mDrawPaint!!)
        }
       if(!mDrawPath!!.isEmpty) {
           mDrawPaint!!.strokeWidth=mDrawPath!!.brushThickness
           mDrawPaint!!.color=mDrawPath!!.color
           canvas.drawPath(mDrawPath!!, mDrawPaint!!)
       }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val touchX=event?.x
        val touchY= event?.y

        when(event?.action){
            MotionEvent.ACTION_DOWN->{
                mDrawPath!!.color=color
                mDrawPath!!.brushThickness=mBrushSize

                mDrawPath!!.reset()
                mDrawPath!!.moveTo(touchX!!,touchY!!)
            }

            MotionEvent.ACTION_MOVE->{
                mDrawPath!!.lineTo(touchX!!,touchY!!)

            }
            MotionEvent.ACTION_UP->{
                // adding each path when its done
                mPaths.add(mDrawPath!!)
                mDrawPath=CustomPath(color,mBrushSize)


            }
            else->return false

        }
        //is called to force a redraw of the view, which triggers the onDraw() method to update the canvas with the new drawing.
        invalidate()

        return true

    }
    fun setColor(newColor:String){
        color =Color.parseColor(newColor)
        mDrawPaint!!.color = color
    }
    
    fun setSizeForBrush(newSize:Float){
        mBrushSize=TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
            newSize,resources.displayMetrics)
        mDrawPaint!!.strokeWidth=mBrushSize
    }
    internal inner class CustomPath(var color:Int,
                                    var brushThickness:Float): Path() {

    }


}