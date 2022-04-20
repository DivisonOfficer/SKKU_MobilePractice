package edu.skku.cs.designpattern.activity.mvvm;

import androidx.databinding.BaseObservable;
import androidx.databinding.Bindable;

import edu.skku.cs.designpattern.BR;

public class MVVMViewModel extends BaseObservable {

    @Bindable
    public String valueString = "string";

    private MVVMModel model = new MVVMModel(0);

    public void changeValue(String newValue) {
        valueString = newValue;
        notifyPropertyChanged(BR.valueString);
    }

    public void addValue(){
        model.add();
        changeValue("value : " + model.value );
    }


}
