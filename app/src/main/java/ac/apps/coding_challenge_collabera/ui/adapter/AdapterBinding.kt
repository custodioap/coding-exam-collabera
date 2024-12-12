package ac.apps.coding_challenge_collabera.ui.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("urlImage")
fun loadImage(view: ImageView, url: String){
    url?.let {
        Glide.with(view.context)
            .load(it)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(RequestOptions().centerCrop())
            .into(view)
    }
}