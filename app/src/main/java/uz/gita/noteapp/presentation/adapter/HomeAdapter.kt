package uz.gita.noteapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import uz.gita.noteapp.data.model.NoteData
import uz.gita.noteapp.databinding.ItemNoteBinding

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
                //cardview.strokeColor = ContextCompat.getColor(binding.root.context, R.color.gray)
                txtTitle.text = item.title
                txtContent.text = item.content
                txtData.text = item.createdAt
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