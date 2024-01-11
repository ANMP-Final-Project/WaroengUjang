package my.anmp.waroengujang.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("setImageUrl")
fun setImage(imageView: ImageView, url:String) {
    Glide.with(imageView.context).load(url).centerCrop().into(imageView)
}