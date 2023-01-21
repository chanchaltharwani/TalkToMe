package com.app.talktome.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.talktome.R
import com.app.talktome.databinding.ReceiverItemLayoutBinding
import com.app.talktome.databinding.SentItemLayoutBinding
import com.app.talktome.model.MessageModel
import com.google.firebase.auth.FirebaseAuth


class MessageAdapter(var context: Context, var list: ArrayList<MessageModel>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var ITEM_SENT = 1
    var ITEM_RECEIVE = 2

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == ITEM_SENT)
            SendViewHolder(LayoutInflater.from(context).inflate(R.layout.sent_item_layout,parent,false))
        else
            ReceiverViewHolder(LayoutInflater.from(context).inflate(R.layout.receiver_item_layout,parent,false))

    }

    override fun getItemViewType(position: Int): Int {
        return if (FirebaseAuth.getInstance().uid == list[position].senderId) ITEM_SENT else ITEM_RECEIVE
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val message  = list[position]

        if (holder.itemViewType == ITEM_SENT){
            val viewHolder = holder as SendViewHolder
            viewHolder.binding.usermsg.text = message.message
        }else{
            val viewHolder = holder as ReceiverViewHolder
            viewHolder.binding.usermsgR.text = message.message
        }


    }

    override fun getItemCount(): Int {
        return list.size
    }


    inner class SendViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = SentItemLayoutBinding.bind(view)
    }

    inner class ReceiverViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        var binding = ReceiverItemLayoutBinding.bind(view)
    }
}