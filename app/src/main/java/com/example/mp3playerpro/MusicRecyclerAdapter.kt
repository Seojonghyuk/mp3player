package com.example.mp3playerpro

import android.content.Context
import android.content.Intent
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.mp3playerpro.databinding.ItemRecyclerBinding
import java.text.SimpleDateFormat

class MusicRecyclerAdapter(val context: Context, val musicList:MutableList<MusicData>):
    RecyclerView.Adapter<MusicRecyclerAdapter.CustomViewHolder>() {
    val ALBUM_IMAGE_SIZE = 90
    var flag = false


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CustomViewHolder {
        val binding = ItemRecyclerBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return CustomViewHolder(binding)
    }

    override fun getItemCount(): Int = musicList.size

    override fun onBindViewHolder(holder: CustomViewHolder, position: Int) {
        val binding = holder.binding
        //이미지가져오기,artist,title,dutation binding
        val bitmap = musicList.get(position).getAlbumBitmap(context, ALBUM_IMAGE_SIZE)
        if (bitmap != null) {
            binding.imageAlbum.setImageBitmap(bitmap)
        }else{
            binding.imageAlbum.setImageResource(R.drawable.music_vedio_24)
        }
        binding.textArtist.text = musicList.get(position).artist
        binding.textTitle.text = musicList.get(position).title
        binding.textDuration.text = SimpleDateFormat("mm:ss").format(musicList.get(position).duration)
        //아이템항목 클릭시 PlayActivity MusicData 전달
        binding.root.setOnClickListener {
            val intent = Intent(binding.root.context,PlayActivity::class.java)
            intent.putExtra("musicData",musicList.get(position))
            binding.root.context.startActivity(intent)
        }
        binding.button1.setOnClickListener {
            if(flag == false){
                binding.button1.setBackgroundResource(R.drawable.heart_btn)
                flag = true
            }else {
                binding.button1.setBackgroundResource(R.drawable.mtheart_btn)
                flag = false

            }
        }
    }
    inner class CustomViewHolder(val binding: ItemRecyclerBinding):RecyclerView.ViewHolder(binding.root)

}