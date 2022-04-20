package edu.skku.cs.designpattern.activity.mvvm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import edu.skku.cs.designpattern.R;
import edu.skku.cs.designpattern.databinding.ActivityMvvmactivityBinding;

import android.os.Bundle;
import android.widget.Toast;

public class MVVMActivity extends AppCompatActivity {

    ActivityMvvmactivityBinding bind;
    MVVMViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setViewModel();
        setBind();
    }

    private void setViewModel(){
        viewModel = new MVVMViewModel();
    }

    private void setBind()
    {
        bind = DataBindingUtil.inflate(getLayoutInflater(),R.layout.activity_mvvmactivity,null,false);

        setContentView(bind.getRoot());

        bind.setActivity(this);
        bind.setViewModel(viewModel);
    }


    public void onButtonClick(){
        viewModel.addValue();
    }

}