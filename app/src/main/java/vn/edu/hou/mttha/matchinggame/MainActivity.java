package vn.edu.hou.mttha.matchinggame;

import android.os.Bundle;
import android.os.Handler;
import android.widget.GridLayout;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridLayout gridLayout;
    private TextView tvMoves;
    private List<Integer> shuffledFlags;
    private ImageButton firstSelected = null;
    private int firstImageId = -1;
    private int moves = 0;
    private int matchedPairs = 0;
    private final int GRID_SIZE = 12;
    private ImageButton[] buttons = new ImageButton[GRID_SIZE];
    private int[] flagImages={
            R.drawable.br, R.drawable.br,
            R.drawable.ma, R.drawable.ma,
            R.drawable.sa, R.drawable.sa,
            R.drawable.uk,R.drawable.uk,
            R.drawable.us,R.drawable.us,
            R.drawable.ger,R.drawable.ger };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        gridLayout = findViewById(R.id.gridLayout);
        tvMoves = findViewById(R.id.tvMoves);
        setupGame();
    }

    private void setupGame() {
        shuffledFlags = new ArrayList<>();
        for (int img : flagImages) {
            shuffledFlags.add(img);
        }
        Collections.shuffle(shuffledFlags);

        for (int i = 0; i < GRID_SIZE; i++) {
            final int index = i;
            buttons[i] = new ImageButton(this);
            buttons[i].setLayoutParams(new GridLayout.LayoutParams());
            buttons[i].setScaleType(ImageButton.ScaleType.CENTER_CROP);
            buttons[i].setAdjustViewBounds(true);
            buttons[i].setImageResource(R.drawable.flag_black);  // Hình mặc định

            buttons[i].setOnClickListener(view -> {
                if (buttons[index].getDrawable().getConstantState() == getResources().getDrawable(R.drawable.flag_black).getConstantState()) {
                    handleSelection(buttons[index], shuffledFlags.get(index));
                }
            });

            GridLayout.LayoutParams params = new GridLayout.LayoutParams();
            params.width = 250;
            params.height = 500;
            params.setMargins(10, 10, 10, 10);
            buttons[i].setLayoutParams(params);

            gridLayout.addView(buttons[i]);
        }
    }

    private void handleSelection(ImageButton button, int imageId) {
        button.setImageResource(imageId);

        if (firstSelected == null) {
            firstSelected = button;
            firstImageId = imageId;
        } else {
            moves++;
            tvMoves.setText("Số lần di chuyển: " + moves);

            if (firstImageId == imageId) {
                matchedPairs++;
                firstSelected = null;

                if (matchedPairs == GRID_SIZE / 2) {
                    tvMoves.setText("Chúc mừng! Bạn đã hoàn thành trò chơi.");
                }
            } else {
                Handler handler = new Handler();
                handler.postDelayed(() -> {
                    firstSelected.setImageResource(R.drawable.flag_black);
                    button.setImageResource(R.drawable.flag_black);
                    firstSelected = null;
                }, 1000);
            }
        }
    }}