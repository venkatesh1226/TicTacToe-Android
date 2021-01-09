package com.example.tictactoe;

import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Friends extends Fragment implements View.OnClickListener {
    Button[][] b=new Button[3][3];
    View g;
    View done;
    View layout;
    TextView status;
    Button btn;
    main m;
    SharedPreferences sharedPref;
    SharedPreferences.Editor editor;

    public Friends() {
        // Required empty public constructor
    }
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_friends, container, false);
        init(view);
        return view;
    }
    private void init(View view) {
        sharedPref = getActivity().getPreferences(getContext().MODE_PRIVATE);
        editor = sharedPref.edit();
        m=new main();
        m.fillBoard();
        m.turn = 0;
        done=view.findViewById(R.id.done);
        layout=view.findViewById(R.id.layout);
        status=done.findViewById(R.id.status);
        btn=done.findViewById(R.id.play);
        btn.setOnClickListener(Friends.this);
        g = view.findViewById(R.id.g);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                Resources res = getContext().getResources();
                int id = res.getIdentifier("b" + i + j, "id", getContext().getPackageName());
                b[i][j]=g.findViewById(id);
                b[i][j].setOnClickListener(Friends.this);
            }
        }
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.b00:
                mover(0,0);
                break;
            case R.id.b01:
                mover(0,1);
                break;
            case R.id.b02:
                mover(0,2);
                break;
            case R.id.b10:
                mover(1,0);
                break;
            case R.id.b11:
                mover(1,1);
                break;
            case R.id.b12:
                mover(1,2);
                break;
            case R.id.b20:
                mover(2,0);
                break;
            case R.id.b21:
                mover(2,1);
                break;
            case R.id.b22:
                mover(2,2);
                break;
            case R.id.play:
                refresh();
                break;
        }
    }
    void refresh(){
        done.setVisibility(View.GONE);
       FragmentTransaction frag= getFragmentManager().beginTransaction();
       frag.replace(R.id.parent,new Friends());
       frag.commit();
    }
    void mover(int row,int col){
        if(m.turn >= 0 && m.turn <= 8){
            if (null != m.fillBoard(row, col)) {
                b[row][col].setText(m.chance());
                winUpdate();
                m.turn++;
            } else
                Toast.makeText(getContext(),
                        "Already Occupied try another one :)",
                        Toast.LENGTH_LONG).show();}
        else{
            editor.putInt(getString(R.string.Total),
                    sharedPref.getInt(getString(R.string.Total), 0)+1);
            editor.apply();
            done.setVisibility(View.VISIBLE);
            status.setText("Game is Draw :)");
            layout.setVisibility(View.GONE);
            g.setVisibility(View.GONE);
            Toast.makeText(getContext(),"Game is Draw",Toast.LENGTH_SHORT).show();
        }
    }
    void winUpdate(){
        if (m.isWin(m.chance())) {
            editor.putInt(getString(R.string.Total),
                    sharedPref.getInt(getString(R.string.Total), 0)+1);
            editor.apply();
            if (m.chance() == "X") {
                editor.putInt(getString(R.string.Win),
                        sharedPref.getInt(getString(R.string.Win), 0)+1);
                editor.apply();
                status.setText("Congo!! You Won " + m.chance());
                Toast.makeText(getContext(), "Hey You Won " + m.chance(), Toast.LENGTH_SHORT).show();
            }
            else{
                editor.putInt(getString(R.string.Lose),
                        sharedPref.getInt(getString(R.string.Lose), 0)+1);
                editor.apply();
                status.setText("Hey You Lost X Try Again :)");
                Toast.makeText(getContext(),"Hey You Lost X",Toast.LENGTH_SHORT).show();}
            layout.setVisibility(View.GONE);
            g.setVisibility(View.GONE);
            done.setVisibility(View.VISIBLE);
        }
    }
}