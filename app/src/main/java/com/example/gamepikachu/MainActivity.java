package com.example.gamepikachu;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    ImageView curView = null;
    private int countPair = 0;
    final int[] drawable = new int[] {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3,
            R.drawable.image4,
            R.drawable.image5,
            R.drawable.image6,
            R.drawable.image7,
            R.drawable.image10,
    };
    int[] pos = {0,1,2,3,4,5,6,7,0,1,2,3,4,5,6,7};
    int currentPos = -1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GridView GridView = (GridView)findViewById(R.id.GridView);
        ImageAdapter imageAdapter = new ImageAdapter(this);
        GridView.setAdapter(imageAdapter);

        GridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                ImageView imageView = (ImageView) view;
                imageView.setImageResource(drawable[pos[position]]); // Hiển thị hình ảnh

                if (curView == null) {
                    curView = imageView;
                    currentPos = position;
                } else {
                    if (currentPos == position) {
                        return; // Tránh trường hợp nhấp vào cùng một hình ảnh lần hai
                    }
                    // So sánh hai hình ảnh
                    if (pos[currentPos] == pos[position]) {
                        // Nếu giống nhau, ẩn hai hình ảnh
                        imageView.setVisibility(View.INVISIBLE);
                        curView.setVisibility(View.INVISIBLE);
                        countPair++;
                        if (countPair == drawable.length / 2) {
                            // Nếu tất cả các cặp đã được tìm thấy, hiển thị thông báo chiến thắng
                            Toast.makeText(getApplicationContext(), "You win", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        // Nếu không giống nhau, đặt lại hình ảnh
                        // Hoặc bạn có thể hiển thị lại hình ảnh mặc định thay vì giữ nguyên
                        // imageView.setImageResource(R.drawable.question);
                    }
                    curView = null;
                    currentPos = -1;
                }
            }
        });
    }
}