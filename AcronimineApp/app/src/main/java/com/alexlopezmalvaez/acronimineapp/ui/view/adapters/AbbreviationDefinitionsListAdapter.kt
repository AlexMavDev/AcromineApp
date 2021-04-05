package com.alexlopezmalvaez.acronimineapp.ui.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.alexlopezmalvaez.acronimineapp.R
import com.alexlopezmalvaez.acronimineapp.data.model.AbbreDefsResponse

class AbbreviationDefinitionsListAdapter(
        private val abbreDefsResponse: AbbreDefsResponse
) : RecyclerView.Adapter<AbbreviationDefinitionItemViewHolder> () {

    override fun getItemCount() = abbreDefsResponse.get(0).lfs.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
            AbbreviationDefinitionItemViewHolder(
                    DataBindingUtil.inflate(
                            LayoutInflater.from(parent.context),
                            R.layout.card_long_form_header,
                            parent,
                            false
                    )
            )

    override fun onBindViewHolder(holder: AbbreviationDefinitionItemViewHolder, position: Int) {
        holder.abbreviationDefinitionItemCardBinding.lf = abbreDefsResponse.get(0).lfs.get(position)
    }
}