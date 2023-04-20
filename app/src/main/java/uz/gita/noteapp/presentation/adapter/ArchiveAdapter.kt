package uz.gita.noteapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.noteapp.R
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.databinding.ItemNoteBinding
import uz.gita.noteapp.utils.showStrikeThrough

class ArchiveAdapter(private val context: Context) :
    ListAdapter<NoteData, ArchiveAdapter.ItemHolder>(NoteCallback) {

    private var deleteLongClickListener: ((NoteData) -> Unit)? = null

    fun setOnDeleteLongClickListener(l: (NoteData) -> Unit) {
        deleteLongClickListener = l
    }

    inner class ItemHolder(private val binding: ItemNoteBinding) :
        ViewHolder(binding.root) {

        init {
            binding.root.setOnLongClickListener {
                deleteLongClickListener?.invoke(getItem(adapterPosition))
                true
            }
        }

        fun bind() {
            val item = getItem(adapterPosition)
            binding.apply {
                itemNote.setBackgroundColor(item.color)
                txtTitle.text = item.title
                txtTitle.showStrikeThrough(true)

                txtContent.text = item.content.parseAsHtml()

                txtData.text = item.createdAt
                txtData.startAnimation(AnimationUtils.loadAnimation(context, R.anim.textview_anim))
                imgPin.visibility = View.INVISIBLE
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemHolder {
        return ItemHolder(
            ItemNoteBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: ItemHolder, position: Int) {
        holder.bind()
    }

    object NoteCallback : DiffUtil.ItemCallback<NoteData>() {
        override fun areItemsTheSame(oldItem: NoteData, newItem: NoteData): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: NoteData, newItem: NoteData): Boolean =
            oldItem == newItem
    }
}