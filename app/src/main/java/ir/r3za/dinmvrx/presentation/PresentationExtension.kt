package ir.r3za.dinmvrx.presentation

import android.content.Context
import ir.r3za.dinmvrx.R
import java.math.BigDecimal

fun BigDecimal.formattedPrice(context: Context): String {
    return context.getString(
        R.string.price_format, this.setScale(1, BigDecimal.ROUND_HALF_UP).toString()
    )
}