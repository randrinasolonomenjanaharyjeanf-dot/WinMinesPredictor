package com.example.winminespredictor

import android.app.Activity
import android.os.Bundle
import android.widget.*

class MainActivity : Activity() {
    private val total = 20
    private var mines = 3
    private val safe = BooleanArray(total)
    private val cells = ArrayList<Button>()
    private lateinit var info: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val root = LinearLayout(this).apply {
            orientation = LinearLayout.VERTICAL
            setPadding(32, 96, 32, 32)
        }
        info = TextView(this).apply { textSize = 18f }
        root.addView(info)

        val size = (resources.displayMetrics.widthPixels / 5) - 8
        val grid = GridLayout(this).apply { columnCount = 5 }
        for (i in 0 until total) {
            val btn = Button(this).apply {
                layoutParams = GridLayout.LayoutParams().apply {
                    width = size; height = size; setMargins(4, 4, 4, 4)
                }
                setOnClickListener { safe[i] = !safe[i]; refresh() }
            }
            cells.add(btn)
            grid.addView(btn)
        }
        root.addView(grid)

        val row = LinearLayout(this).apply { orientation = LinearLayout.HORIZONTAL }
        row.addView(Button(this).apply {
            text = "Mines -"
            setOnClickListener { if (mines > 1) mines--; refresh() }
        })
        row.addView(Button(this).apply {
            text = "Mines +"
            setOnClickListener { if (mines < total - 1) mines++; refresh() }
        })
        row.addView(Button(this).apply {
            text = "Reset"
            setOnClickListener { safe.fill(false); refresh() }
        })
        root.addView(row)

        setContentView(root)
        refresh()
    }

    private fun refresh() {
        val safeCount = safe.count { it }
        val remaining = total - safeCount
        val prob = if (remaining <= 0) 0.0 else minOf(100.0, mines * 100.0 / remaining)
        cells.forEachIndexed { i, b -> b.text = if (safe[i]) "✓" else "?" }
        info.text = "Mines: $mines | Case azo antoka: $safeCount\n" +
            "Probabilité mine (case sisa): ${"%.1f".format(prob)}%"
    }
}
