package ud.demo.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.LinearLayout
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.core.view.get

class MainActivity : AppCompatActivity() {
    private var drawingView:DrawingView?=null
    private var mImageButtonCurrrentPaint:ImageButton?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView=findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())

        val linearLayoutPaintColors = findViewById<LinearLayout>(R.id.ll_paint_colors)
        mImageButtonCurrrentPaint = linearLayoutPaintColors[1] as ImageButton




        val brush :ImageButton = findViewById(R.id.brush)
        brush.setOnClickListener{
            showBrushSizeChooserDialog()
        }


    }
    private fun showBrushSizeChooserDialog(){
        val brushDailog = Dialog(this)
        brushDailog.setContentView(R.layout.dialog_brush_size)
        brushDailog.setTitle("Brush size: ")
        val smallBtn:ImageButton= brushDailog.findViewById<ImageButton>(R.id.small_brush)
        smallBtn.setOnClickListener{
            drawingView!!.setSizeForBrush(10.toFloat())
            brushDailog.dismiss()
        }

        val mediumBtn:ImageButton= brushDailog.findViewById<ImageButton>(R.id.medium_brush)
        mediumBtn.setOnClickListener{
            drawingView!!.setSizeForBrush(20.toFloat())
            brushDailog.dismiss()
        }

        val largeBtn:ImageButton= brushDailog.findViewById<ImageButton>(R.id.large_brush)
        largeBtn.setOnClickListener{
            drawingView!!.setSizeForBrush(30.toFloat())
            brushDailog.dismiss()
        }


        brushDailog.show()

    }

    fun paintClicked(view: View){
         if(view!== mImageButtonCurrrentPaint){
             val imageButton= view as ImageButton
             val colorTag = imageButton.tag.toString()
             drawingView?.setColor(colorTag)

             imageButton.setImageDrawable(
                 ContextCompat.getDrawable(this,R.drawable.pallet_pressed)
             )
             mImageButtonCurrrentPaint!!.setImageDrawable(
                 ContextCompat.getDrawable(this,R.drawable.pallet_normal)
             )

             mImageButtonCurrrentPaint=view
         }else{
             Toast.makeText(this,"Already selected",Toast.LENGTH_LONG).show()
         }
    }
}