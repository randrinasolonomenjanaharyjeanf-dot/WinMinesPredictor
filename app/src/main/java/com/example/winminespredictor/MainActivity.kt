
package com.example.winminespredictor

import android.app.Activity
import android.os.Bundle
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.widget.*

class MainActivity : Activity() {

    private val dark = Color.rgb(13, 18, 32)
    private val panel = Color.rgb(25, 33, 52)
    private val accent = Color.rgb(35, 190, 145)
    private val white = Color.WHITE

    private lateinit var idTour: EditText
    private lateinit var clientSeed: EditText
    private lateinit var serverSeed: EditText
    private lateinit var nonce: EditText
    private lateinit var status: TextView
    private lateinit var analyzeButton: Button
    private val cells = ArrayList<TextView>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val scroll = ScrollView(this)
        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(24, 30, 24, 30)
            setBackgroundColor(dark)
        }

        scroll.addView(root)

        val title = TextView(this).apply {
            text = "WIN MINES PREDICTOR"
            textSize = 25f
            setTextColor(accent)
            typeface = Typeface.DEFAULT_BOLD
            gravity = Gravity.CENTER
        }
        root.addView(title)

        addText(root, "1WIN CRYPTO MINES ANALYZER", 14f, accent)

        addText(root, "ROUND INFORMATION", 17f, white)

        idTour = addInput(root, "ID du Tour")
        clientSeed = addInput(root, "Client Seed")
        serverSeed = addInput(root, "Server Seed")
        nonce = addInput(root, "Nonce")

        analyzeButton = Button(this).apply {
            text = "ANALYZE / PREDICT"
            setTextColor(white)
            setBackgroundColor(accent)
            textSize = 16f
        }
        root.addView(analyzeButton)

        addText(root, "ANALYSIS GRID — 20 CELLS", 17f, white)

        val grid = GridLayout(this).apply {
            columnCount = 5
            setPadding(0, 12, 0, 12)
        }

        for (i in 1..20) {
            val cell = TextView(this).apply {
                text = "?"
                textSize = 22f
                gravity = Gravity.CENTER
                setTextColor(white)
                setBackgroundColor(panel)

                val size = (resources.displayMetrics.widthPixels / 5) - 28
                layoutParams = GridLayout.LayoutParams().apply {
                    width = size
                    height = size
                    setMargins(3, 3, 3, 3)
                }
            }
            cells.add(cell)
            grid.addView(cell)
        }

        root.addView(grid)

        addText(root, "PROVABLY FAIR VERIFICATION", 17f, white)

        status = TextView(this).apply {
            text = "Waiting for round information..."
            textSize = 14f
            setTextColor(white)
            setPadding(12, 18, 12, 18)
            setBackgroundColor(panel)
        }
        root.addView(status)

        val verify = Button(this).apply {
            text = "VERIFY ROUND"
            setOnClickListener {
                status.text =
                    "Verification is not yet connected. A valid 1Win algorithm and round data are required."
            }
        }
        root.addView(verify)

        analyzeButton.setOnClickListener {
            if (idTour.text.isBlank() ||
                clientSeed.text.isBlank() ||
                nonce.text.isBlank()
            ) {
                status.text = "Please enter ID du Tour, Client Seed and Nonce."
            } else {
                status.text =
                    "Information received. Real prediction is not available until the official algorithm is verified."
            }
        }

        setContentView(scroll)
    }

    private fun addText(
        root: LinearLayout,
        value: String,
        size: Float,
        color: Int
    ) {
        val text = TextView(this).apply {
            this.text = value
            textSize = size
            setTextColor(color)
            typeface = Typeface.DEFAULT_BOLD
            setPadding(0, 22, 0, 12)
        }
        root.addView(text)
    }

    private fun addInput(
        root: LinearLayout,
        hintText: String
    ): EditText {
        val input = EditText(this).apply {
            hint = hintText
            setHintTextColor(Color.LTGRAY)
            setTextColor(white)
            textSize = 15f
            setSingleLine(true)
            setPadding(14, 10, 14, 10)
            setBackgroundColor(panel)
        }
        val params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(0, 7, 0, 7)
        root.addView(input, params)
        return input
    }
}
