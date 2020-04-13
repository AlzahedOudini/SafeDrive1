package Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.example.safedrive1.R;
import model.ScreenItem;

import java.util.List;

public class IntroViewPagerAdapter extends PagerAdapter {

    Context mContext;
    List<ScreenItem> mScreenItemList;

    public IntroViewPagerAdapter(Context context, List<ScreenItem> screenItemList) {
        mContext = context;
        mScreenItemList = screenItemList;
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View sliderLayout  = inflater.inflate(R.layout.slider_layout, null);

        ImageView imgSlide = sliderLayout.findViewById(R.id.intro_image);
        TextView desc = sliderLayout.findViewById(R.id.slide_description);

        imgSlide.setImageResource(mScreenItemList.get(position).getImg());
        desc.setText(mScreenItemList.get(position).getDescription());

        container.addView(sliderLayout);
        return  sliderLayout;
    }

    @Override
    public int getCount() {
        return mScreenItemList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {


        container.removeView((View)object);
    }
}
