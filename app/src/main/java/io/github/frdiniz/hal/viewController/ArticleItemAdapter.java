package io.github.frdiniz.hal.viewController;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import io.github.frdiniz.hal.R;
import io.github.frdiniz.hal.model.Article;
import io.github.frdiniz.hal.model.DataBitmapUtil;


public class ArticleItemAdapter extends BaseAdapter {
    private Context context;
    private List<Article> list;

    public ArticleItemAdapter(Context context, List<Article> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return (long) list.get(i).getId();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        LinearLayout layout;
        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = new LinearLayout(context);
            inflater.inflate(R.layout.article_list_item, layout);

        } else {
            layout = (LinearLayout)view;
        }

        ImageView image = (ImageView)layout.findViewById(R.id.item_image);
        Bitmap temp = DataBitmapUtil.toImage(list.get(i).getImage());
        image.setImageBitmap(temp);

        TextView title = (TextView) layout.findViewById(R.id.item_title);
        title.setText(list.get(i).getTitle());

        TextView site = (TextView) layout.findViewById(R.id.item_site);
        site.setText(list.get(i).getSite());

        TextView description = (TextView) layout.findViewById(R.id.item_desc);
        String shorten = list.get(i).getDescription();
        shorten = shorten.substring(0, Math.min(shorten.length(), 90));
        shorten = shorten + " ...";
        description.setText(shorten);

        return layout;
    }
}
