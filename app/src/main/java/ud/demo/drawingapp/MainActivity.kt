package ud.demo.drawingapp

import android.app.Dialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton

class MainActivity : AppCompatActivity() {
    private var drawingView:DrawingView?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawingView=findViewById(R.id.drawing_view)
        drawingView?.setSizeForBrush(20.toFloat())

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
        smallBtn.setOnClickListener{
            drawingView!!.setSizeForBrush(20.toFloat())
            brushDailog.dismiss()
        }

        val largeBtn:ImageButton= brushDailog.findViewById<ImageButton>(R.id.large_brush)
        smallBtn.setOnClickListener{
            drawingView!!.setSizeForBrush(30.toFloat())
            brushDailog.dismiss()
        }


        brushDailog.show()

    }
}