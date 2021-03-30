package com.app.marveltestapp.ui.item

import android.graphics.drawable.Drawable
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.ViewCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.app.marveltestapp.R
import com.app.marveltestapp.databinding.ItemCharacterBinding
import com.app.marveltestapp.model.entity.CharactersDataResponse
import com.app.marveltestapp.ui.TransitionInfo
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import com.mikepenz.fastadapter.binding.AbstractBindingItem

class CharacterItem(var characterResponse: CharactersDataResponse.Data.Result) :
    AbstractBindingItem<ItemCharacterBinding>() {

    override val type: Int
        get() = R.id.itemCompany

    var transitionInfoList: List<TransitionInfo>? = null

    override fun bindView(binding: ItemCharacterBinding, payloads: List<Any>) {
        binding.companyNameItemTextView.text = characterResponse.name

        transitionInfoList = mutableListOf(
            TransitionInfo(binding.imageCompany, "image${characterResponse.id}"),
            TransitionInfo(binding.companyNameItemTextView, "name${characterResponse.id}"),
        )
        ViewCompat.setTransitionName(
            binding.imageCompany,
            "image${characterResponse.id}"
        )
        ViewCompat.setTransitionName(
            binding.companyNameItemTextView,
            "name${characterResponse.id}"
        )

        val circularProgressDrawable = CircularProgressDrawable(binding.root.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        Glide.with(binding.root.context)
            .load(
                "https://${characterResponse.thumbnail.path.substring(6)}." +
                        "${characterResponse.thumbnail.extension}"
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    Log.d("kostya", e?.localizedMessage)
                    binding.imageCompany.setImageResource(R.drawable.ic_baseline_error_outline_24)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.imageCompany.setImageDrawable(resource)
                    return true
                }

            })
//            .apply(RequestOptions().centerCrop())
            .placeholder(circularProgressDrawable)
            .into(binding.imageCompany)
    }

    override fun createBinding(inflater: LayoutInflater, parent: ViewGroup?): ItemCharacterBinding {
        return ItemCharacterBinding.inflate(inflater, parent, false)
    }
}
