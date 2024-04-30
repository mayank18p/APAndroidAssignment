package com.example.apandroidassignment.pagination;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.apandroidassignment.MyApplication;
import com.example.apandroidassignment.R;
import com.example.apandroidassignment.caching.DownloadImageTask;
import com.example.apandroidassignment.model.ApiResponse;
import com.example.apandroidassignment.model.Thumbnail;
import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class PaginationAdapter extends RecyclerView.Adapter<PaginationAdapter.ViewHolder> {
    private Context context;
    private List<ApiResponse> resultList;
    private String imgUrl;
    DownloadImageTask imgTask;

    public PaginationAdapter(Context context, List<ApiResponse> resultList) {
        this.context = context;
        this.resultList = resultList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_list, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Thumbnail thumbnail = resultList.get(position).getThumbnail();
        imgUrl = thumbnail.getDomain() + "/" + thumbnail.getBasePath() + "/0/" + thumbnail.getKey();

//      Using LruCache to cache images efficiently. You can read about LruCache from Android Developer site
        Bitmap bm = MyApplication.getInstance().getImageCache().getImageFromWarehouse(imgUrl);
        if(bm != null) {
            viewHolder.imageView.setImageBitmap(bm);
        } else {
            viewHolder.imageView.setImageBitmap(null);
            imgTask = new DownloadImageTask(this, 300, 300);
            imgTask.execute(imgUrl);
        }
    }

    @Override
    public int getItemCount() {
        return resultList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private AppCompatImageView imageView;

        public ViewHolder(View itemView) {
            super(itemView);
            imageView = (AppCompatImageView) itemView.findViewById(R.id.imageIv);
        }
    }
}