package com.app.marveltestapp.ui.fragment

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.transition.TransitionInflater
import android.util.Log
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.core.view.ViewCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.app.marveltestapp.R
import com.app.marveltestapp.databinding.FragmentCharacterDetailsBinding
import com.app.marveltestapp.di.components.CharacterDetailsUIComponent
import com.app.marveltestapp.ui.base.BaseFragment
import com.app.marveltestapp.utils.resolveVisibility
import com.app.marveltestapp.viewModel.CharacterDetailsViewModel
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import javax.inject.Inject

class CharacterDetailsFragment :
    BaseFragment(R.layout.fragment_character_details),
    Observer<CharacterDetailsViewModel.GetCompanyDetailsResult> {

    companion object {
        const val KEY_CHARACTER = "characterId"
        fun createInstance(characterId: String): Fragment {
            val fragment = CharacterDetailsFragment()
            val bundle = Bundle()
            bundle.putString(KEY_CHARACTER, characterId)
            fragment.arguments = bundle
            return fragment
        }
    }

    private lateinit var binding: FragmentCharacterDetailsBinding

    private lateinit var characterId: String

    @Inject
    lateinit var companiesViewModelFactory: ViewModelProvider.Factory

    private lateinit var characterDetailsViewModel: CharacterDetailsViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = TransitionInflater.from(requireContext())
            .inflateTransition(R.transition.shared_image)

        characterId = requireArguments().get(KEY_CHARACTER) as String
        characterDetailsViewModel = ViewModelProvider(this, companiesViewModelFactory)
            .get(CharacterDetailsViewModel::class.java)
        characterDetailsViewModel.initialize(characterId)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        binding = FragmentCharacterDetailsBinding.bind(view)
        characterDetailsViewModel.companyDetailsLiveData.observe(viewLifecycleOwner, this)

        ViewCompat.setTransitionName(
            binding.characterDetailsImage,
            "image$characterId"
        )
        ViewCompat.setTransitionName(
            binding.characterNameTextView,
            "name$characterId"
        )

    }

    override fun initComponent() {
        componentStorage.getOrCreateComponent<CharacterDetailsUIComponent>().inject(this)
    }

    override fun releaseComponent() {
        componentStorage.releaseComponent<CharacterDetailsUIComponent>()
    }

    override fun onChanged(result: CharacterDetailsViewModel.GetCompanyDetailsResult) {
        when (result) {
            CharacterDetailsViewModel.GetCompanyDetailsResult.Progress -> {
//                showToast("Загрузка...")
            }
            is CharacterDetailsViewModel.GetCompanyDetailsResult.Success -> {
                if (result.characterData.isNotEmpty()) {
                    showCharacterDetails(result)
                }
            }
            is CharacterDetailsViewModel.GetCompanyDetailsResult.Error -> {
                showAlertDialog(result)
            }
        }
    }

    private fun showCharacterDetails(result: CharacterDetailsViewModel.GetCompanyDetailsResult.Success) {
        Glide.with(binding.root.context)
            .load(
                "https://${result.characterData[0].thumbnail.path.substring(6)}." +
                        "${result.characterData[0].thumbnail.extension}"
            )
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    Log.d("kostya", e?.localizedMessage)
                    binding.characterDetailsImage
                        .setImageResource(R.drawable.ic_baseline_error_outline_24)
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    startPostponedEnterTransition()
                    binding.characterDetailsImage.setImageDrawable(resource)
                    return true
                }

            })
            .apply(RequestOptions().fitCenter())
            .into(binding.characterDetailsImage)

        binding.descriptionHeader.resolveVisibility(result.characterData[0].description.isNotEmpty())
        binding.scrollView.resolveVisibility(result.characterData[0].description.isNotEmpty())
        binding.characterNameTextView.text = result.characterData[0].name
        binding.descriptionTextView.text = result.characterData[0].description

    }

    private fun showAlertDialog(result: CharacterDetailsViewModel.GetCompanyDetailsResult.Error) {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Ошибка!")
        alertDialogBuilder.setMessage("Неверный ответ от сервера \n${result.throwable.message}")
        alertDialogBuilder.setNeutralButton("OK") { _, _ ->
            getFlowNavigator()?.onBackPressed()
        }
        alertDialogBuilder.setCancelable(false)
        val dialog: AlertDialog = alertDialogBuilder.create()
        dialog.show()
    }

}