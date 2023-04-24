package uz.gita.noteapp_bek.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.parseAsHtml
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.noteapp_bek.data.model.NoteData
import uz.gita.noteapp_bek.databinding.ItemNoteBinding

class HomeAdapter : ListAdapter<NoteData, HomeAdapter.ItemHolder>(NoteCallback) {

    private var deleteLongClickListener: ((NoteData) -> Unit)? = null
    private var onItemClickListener: ((NoteData) -> Unit)? = null

    fun setOnDeleteLongClickListener(l: (NoteData) -> Unit) {
        deleteLongClickListener = l
    }

    fun setOnItemClickListener(l: (NoteData) -> Unit) {
        onItemClickListener = l
    }

    inner class ItemHolder(private val binding: ItemNoteBinding) :
        ViewHolder(binding.root) {

        init {

            binding.root.setOnLongClickListener {
                deleteLongClickListener?.invoke(getItem(adapterPosition))
                true
            }

            binding.root.setOnClickListener {
                onItemClickListener?.invoke(getItem(adapterPosition))
            }
        }

        fun bind() {
            val item = getItem(adapterPosition)
            binding.apply {
                itemNote.setBackgroundColor(item.color)
                txtTitle.text = item.title
                txtData.text = item.createdAt

                txtContent.text = item.content.parseAsHtml().trim()

                if (item.pinned == 0) imgPin.visibility = View.INVISIBLE
                else imgPin.visibility = View.VISIBLE
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