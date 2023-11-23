package uz.akra.memorygame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import uz.akra.memorygame.databinding.ActivityMainBinding

val listImageOC = arrayOf(
    false, false, false,
    false, false, false,
    false, false, false,
    false, false, false,
    false, false, false,
    false, false, false
)
val imageIndex = arrayOfNulls<Int>(2)
val picId = arrayOfNulls<Int>(2)
var openedPic = 0
var animWorking = false

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var stopwatchTextView: TextView
    private var running = false
    private var seconds = 0

    private val handler = Handler()

    private val runnable = object : Runnable {
        override fun run() {
            if (running) {
                seconds++
                val hours = seconds / 3600
                val minutes = seconds % 3600 / 60
                val secs = seconds % 60

                val time = String.format("%02d:%02d:%02d", hours, minutes, secs)
                stopwatchTextView.text = time

                // Sekundomer davom etishi
                handler.postDelayed(this, 1000)
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        stopwatchTextView = binding.txtTime

        binding.apply {
            imageR11.setOnClickListener {
                imageClick(imageR11, R.drawable.archery, 0)
            }
            imageR12.setOnClickListener {
                imageClick(imageR12, R.drawable.badminton, 1)
            }
            imageR13.setOnClickListener {
                imageClick(imageR13, R.drawable.archery, 2)
            }
            imageR14.setOnClickListener {
                imageClick(imageR14, R.drawable.shooting, 3)
            }
            imageR15.setOnClickListener {
                imageClick(imageR15, R.drawable.pingpong, 4)
            }
            imageR16.setOnClickListener {
                imageClick(imageR16, R.drawable.football, 5)
            }
            imageR21.setOnClickListener {
                imageClick(imageR21, R.drawable.triatlon, 6)
            }
            imageR22.setOnClickListener {
                imageClick(imageR22, R.drawable.wrestling, 7)
            }
            imageR23.setOnClickListener {
                imageClick(imageR23, R.drawable.boxing, 8)
            }
            imageR24.setOnClickListener {
                imageClick(imageR24, R.drawable.fencing, 9)
            }
            imageR25.setOnClickListener {
                imageClick(imageR25, R.drawable.football, 10)
            }
            imageR26.setOnClickListener {
                imageClick(imageR26, R.drawable.fencing, 11)
            }
            imageR31.setOnClickListener {
                imageClick(imageR31, R.drawable.shooting, 12)
            }
            imageR32.setOnClickListener {
                imageClick(imageR32, R.drawable.boxing, 13)
            }
            imageR33.setOnClickListener {
                imageClick(imageR33, R.drawable.pingpong, 14)
            }
            imageR34.setOnClickListener {
                imageClick(imageR34, R.drawable.wrestling, 15)
            }
            imageR35.setOnClickListener {
                imageClick(imageR35, R.drawable.badminton, 16)
            }
            imageR36.setOnClickListener {
                imageClick(imageR36, R.drawable.triatlon, 17)
            }
        }

    }


    fun imageClick(imageView: ImageView, pic: Int, index: Int) {
        if (!animWorking) {
            if (!listImageOC[index]) {
                animOpen(imageView, pic, index)
            } else {
                animClose(imageView, pic, index)
            }
        }
        startStopwatch()
    }

    fun animOpen(imageView: ImageView, pic: Int, index: Int) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale_anim_end)
                imageView.startAnimation(animation2)
                imageView.setImageResource(pic)
                animation2.setAnimationListener(object : Animation.AnimationListener {
                    override fun onAnimationStart(animation: Animation?) {
                        animWorking = true
                    }

                    override fun onAnimationEnd(animation: Animation?) {
                        listImageOC[index] = true
                        imageIndex[openedPic] = index
                        picId[openedPic] = pic
                        openedPic++

                        if (openedPic == 2) {
                            if (picId[0] == picId[1]) {
                                imageViewFind(imageIndex[0]).visibility = View.INVISIBLE
                                openedPic--
                                imageViewFind(imageIndex[1]).visibility = View.INVISIBLE
                                openedPic--
                                Toast.makeText(this@MainActivity, "To'g'ri topildi", Toast.LENGTH_SHORT).show()
                            } else {
                                animClose(imageViewFind(imageIndex[0]), -1, imageIndex[0])
                                animClose(imageViewFind(imageIndex[1]), -1, imageIndex[1])
                            }
                        }
                        animWorking = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })

    }


    fun animClose(imageView: ImageView, pic: Int, index: Int?) {
        val animation = AnimationUtils.loadAnimation(this, R.anim.scale_anim)
        imageView.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {
                animWorking = true
            }

            override fun onAnimationEnd(animation: Animation?) {
                val animation2 = AnimationUtils.loadAnimation(this@MainActivity, R.anim.scale_anim_end)
                imageView.startAnimation(animation2)
                imageView.setImageResource(R.drawable.logo)
                animation2.setAnimationListener(object : Animation.AnimationListener{
                    override fun onAnimationStart(animation: Animation?) {

                    }

                    override fun onAnimationEnd(animation: Animation?) {
                            animWorking = false
                    }

                    override fun onAnimationRepeat(animation: Animation?) {

                    }
                })
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        listImageOC[index!!] = false
        openedPic--
    }


    fun imageViewFind(index: Int?): ImageView {
        var imageView: ImageView? = null
        when (index) {
            0 -> imageView = binding.imageR11
            1 -> imageView = binding.imageR12
            2 -> imageView = binding.imageR13
            3 -> imageView = binding.imageR14
            4 -> imageView = binding.imageR15
            5 -> imageView = binding.imageR16
            6 -> imageView = binding.imageR21
            7 -> imageView = binding.imageR22
            8 -> imageView = binding.imageR23
            9 -> imageView = binding.imageR24
            10 -> imageView = binding.imageR25
            11 -> imageView = binding.imageR26
            12 -> imageView = binding.imageR31
            13 -> imageView = binding.imageR32
            14 -> imageView = binding.imageR33
            15 -> imageView = binding.imageR34
            16 -> imageView = binding.imageR35
            17 -> imageView = binding.imageR36

        }
        return imageView!!
    }

    fun startStopwatch() {
        running = true
        handler.post(runnable)
    }

    fun stopStopwatch() {
        running = false
        handler.post(runnable)
    }

}