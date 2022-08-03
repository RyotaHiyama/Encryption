package com.nokopi.encryption

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.util.Base64
import android.util.Log
import android.widget.TextView
import com.google.zxing.BarcodeFormat
import com.google.zxing.EncodeHintType
import com.journeyapps.barcodescanner.BarcodeEncoder

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var flag = false
        var decodeResult : ByteArray
        var encodeResult : String
        val size = 400
        var bitmap : Bitmap
        var bitmap2 : Bitmap

        mode.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                flag = true
                Text.text = "デコードしたい文字"
                button.text = "デコード"
            } else {
                flag = false
                textView.text = "モード切り替え"
                Text.text = "エンコードしたい文字"
                button.text = "エンコード"
            }
        }

        button.setOnClickListener {
            if (flag) {
                decodeResult = Base64.decode(edit.text.toString(), Base64.DEFAULT) //Base64 デコード
                result.setText(String(decodeResult, charset("UTF-8")), TextView.BufferType.NORMAL) //デコード結果を表示
                bitmap = BarcodeEncoder().encodeBitmap(String(decodeResult, charset("UTF-8")), BarcodeFormat.QR_CODE, size, size, mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")) //QRコードを生成
                imageView2.setImageBitmap(bitmap) //生成したQRコードを表示
                bitmap2 = BarcodeEncoder().encodeBitmap(edit.text.toString(),BarcodeFormat.QR_CODE,size,size, mapOf(EncodeHintType.CHARACTER_SET to "UTF-8"))
                imageView.setImageBitmap(bitmap2)
            } else {
                encodeResult = Base64.encodeToString(edit.text.toString().toByteArray(), Base64.DEFAULT) //Base64 エンコード
                result.setText(encodeResult, TextView.BufferType.NORMAL) //エンコード結果を表示
                bitmap = BarcodeEncoder().encodeBitmap(encodeResult, BarcodeFormat.QR_CODE, size, size, mapOf(EncodeHintType.CHARACTER_SET to "UTF-8")) //QRコードを生成
                imageView2.setImageBitmap(bitmap) //生成したQRコードを表示
                bitmap2 = BarcodeEncoder().encodeBitmap(edit.text.toString(),BarcodeFormat.QR_CODE,size,size, mapOf(EncodeHintType.CHARACTER_SET to "UTF-8"))
                imageView.setImageBitmap(bitmap2)
            }
        }
    }

}