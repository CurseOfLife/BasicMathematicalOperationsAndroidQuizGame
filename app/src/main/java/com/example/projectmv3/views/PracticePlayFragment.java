package com.example.projectmv3.views;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;

import android.os.CountDownTimer;
import android.os.Handler;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projectmv3.Game.Question.Add;
import com.example.projectmv3.Game.Question.Divide;
import com.example.projectmv3.Game.Question.Multiply;
import com.example.projectmv3.Game.Question.Question;
import com.example.projectmv3.Game.Question.QuestionFactory;
import com.example.projectmv3.Game.Question.Subtract;
import com.example.projectmv3.R;
import com.example.projectmv3.Game.Game;
import com.example.projectmv3.tools.VerticalProgressBar;
import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.google.android.material.bottomsheet.BottomSheetDialog;

import java.text.NumberFormat;


public class PracticePlayFragment extends Fragment {

    private TextView menu;

    private FrameLayout bottom_sheet;
    private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetDialog bottomSheetDialog;

    private static final String TAG = "MyActivity";

    private AppCompatButton btn_answer_one, btn_answer_two, btn_answer_three, btn_answer_four;
    private TextView game_question, txt_timer, txt_score, txt_v_title;
    private VerticalProgressBar progress_timer, progress_score;

    private Game game = new Game();

    int remaining_time = 10;

    CountDownTimer timer = new CountDownTimer(11000, 1000) {
        @Override
        public void onTick(long millisUntilFinished) {
            remaining_time--;
            txt_timer.setText(Integer.toString(remaining_time));
            progress_timer.setProgress(10 - remaining_time);
        }

        @Override
        public void onFinish() {
            btn_answer_one.setEnabled(false);
            btn_answer_two.setEnabled(false);
            btn_answer_three.setEnabled(false);
            btn_answer_four.setEnabled(false);

            txt_v_title.setText("Time's up!" + game.getCorrect_number_count() + "/" + (game.getTotal_questions() - 1)); // THIS COPY TO THE DIALOG JUST LEAVE TIMES UP

            menu.setVisibility(View.VISIBLE);

            final Handler handler = new Handler();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    bottomSheetDialog.show();
                }
            }, 4000);
        }
    };


    public PracticePlayFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_practice_play, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        menu = view.findViewById(R.id.menu);
        bottom_sheet = view.findViewById(R.id.bottom_sheet);
        bottomSheetBehavior = BottomSheetBehavior.from(bottom_sheet);
        btn_answer_one = view.findViewById(R.id.answer_one);
        btn_answer_two = view.findViewById(R.id.answer_two);
        btn_answer_three = view.findViewById(R.id.answer_three);
        btn_answer_four = view.findViewById(R.id.answer_four);
        game_question = view.findViewById(R.id.game_question);
        progress_timer = view.findViewById(R.id.timer);
        txt_timer = view.findViewById(R.id.txt_timer);
        progress_score = view.findViewById(R.id.score);
        txt_score = view.findViewById(R.id.txt_score);
        txt_v_title = view.findViewById(R.id.txt_v_title);

        btn_answer_one.setEnabled(false);
        btn_answer_two.setEnabled(false);
        btn_answer_three.setEnabled(false);
        btn_answer_four.setEnabled(false);

        showBottomSheet();

        // show bottom sheet menu
        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showBottomSheet();
            }
        });

        txt_v_title.setText("quick play");
        game_question.setText("");
        txt_timer.setText("0");
        txt_score.setText("0");


        btn_answer_one.setOnClickListener(onAnswerButtonClickedListener);
        btn_answer_two.setOnClickListener(onAnswerButtonClickedListener);
        btn_answer_three.setOnClickListener(onAnswerButtonClickedListener);
        btn_answer_four.setOnClickListener(onAnswerButtonClickedListener);
    }

    //user clicks on a button they think the answer is
    //check if its right number
    //manage score if wrong or right
    //manage progress
    //provide new question
    View.OnClickListener onAnswerButtonClickedListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            AppCompatButton button = (AppCompatButton) v;

            float selected_answer = Float.parseFloat(button.getText().toString());

            game.evaluateAnswer(selected_answer);
            txt_score.setText(Integer.toString(game.getScore()));
            progress_score.setProgress(game.getScore());
            ;
            startNewGameTurn();
        }
    };

    //setting up the bottom view to serv as a menu for the game
    private void showBottomSheet() {

        if (bottomSheetBehavior.getState() == BottomSheetBehavior.STATE_EXPANDED) {
            bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
        }

        final View view = getLayoutInflater().inflate(R.layout.dialog_practice_menu, null);

        LinearLayout btn_play = view.findViewById(R.id.play);
        SwitchCompat sw_add = view.findViewById(R.id.sw_add);
        SwitchCompat sw_minus = view.findViewById(R.id.sw_minus);
        SwitchCompat sw_multiply = view.findViewById(R.id.multiply);
        SwitchCompat sw_divide = view.findViewById(R.id.divide);
        SeekBar seekBar = view.findViewById(R.id.seekbar);
        TextView txt_upperLimit = view.findViewById(R.id.upper_limit);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int progressChangedValue = 0;

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                progressChangedValue = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                txt_upperLimit.setText(String.valueOf(progressChangedValue));
            }
        });

        btn_play.setOnClickListener(v -> {

            Boolean isAddChecked = sw_add.isChecked();
            Boolean isMinusChecked = sw_minus.isChecked();
            Boolean isMultiplyChecked = sw_multiply.isChecked();
            Boolean isDivideChecked = sw_divide.isChecked();
            QuestionFactory.list.clear();

            if (isAddChecked.equals(false) && isMinusChecked.equals(false) && isMultiplyChecked.equals(false) && isDivideChecked.equals(false)) {
                Toast.makeText(requireActivity(), "You forgot to pick an operation", Toast.LENGTH_SHORT).show();

            } else if (isAddChecked.equals(true) || isMinusChecked.equals(true) || isMultiplyChecked.equals(true) || isDivideChecked.equals(true)) { //if any switch is checked then create the game
                bottomSheetDialog.dismiss();

                remaining_time = 10;

                int upperLimit = seekBar.getProgress();

                if (isAddChecked.equals(true)) {
                    QuestionFactory.list.add(new Add(upperLimit));
                    Log.i(TAG, "Add");
                }

                if (isMinusChecked.equals(true)) {
                    QuestionFactory.list.add(new Subtract(upperLimit));
                }

                if (isMultiplyChecked.equals(true)) {
                    QuestionFactory.list.add(new Multiply(upperLimit));
                }

                if (isDivideChecked.equals(true)) {
                    QuestionFactory.list.add(new Divide(upperLimit));
                }

                game = new Game(); // set upper limit and chosen operations
                startNewGameTurn();
                timer.start();
            }
        });


        bottomSheetDialog = new BottomSheetDialog(requireActivity(), R.style.SheetDialog);
        bottomSheetDialog.setContentView(view);
        bottomSheetDialog.show();


        //  bottomSheetDialog.setOnDismissListener(dialog -> bottomSheetDialog = null);
    }

    //providing a new question
    private void startNewGameTurn() {
        // create a new question
        // set text on answer buttons
        // enable buttons
        // start timer
        // score
        game.createNewQuestions();
        float[] answer = game.getCurrent_question().getAnswersArray();

        Question obj = game.getCurrent_question();

        if (!(obj instanceof Divide)) {
            btn_answer_one.setText(Long.toString(Math.round(answer[0])));
            btn_answer_two.setText(Long.toString(Math.round(answer[1])));
            btn_answer_three.setText(Long.toString(Math.round(answer[2])));
            btn_answer_four.setText(Long.toString(Math.round(answer[3])));
        } else {
            NumberFormat nf = NumberFormat.getInstance();
            nf.setMaximumFractionDigits(3); // prints 1.01 when  4.0099;

            btn_answer_one.setText(nf.format(answer[0]));
            btn_answer_two.setText(nf.format(answer[1]));
            btn_answer_three.setText(nf.format(answer[2]));
            btn_answer_four.setText(nf.format(answer[3]));
        }

        btn_answer_one.setEnabled(true);
        btn_answer_two.setEnabled(true);
        btn_answer_three.setEnabled(true);
        btn_answer_four.setEnabled(true);

        menu.setVisibility(View.INVISIBLE);

        game_question.setText(game.getCurrent_question().getQuestion());
        txt_v_title.setText(game.getCorrect_number_count() + "/" + (game.getTotal_questions() - 1));
    }


}