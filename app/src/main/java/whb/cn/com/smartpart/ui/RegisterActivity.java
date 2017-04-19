package whb.cn.com.smartpart.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import butterknife.Bind;
import butterknife.OnClick;
import whb.cn.com.smartpart.R;

public class RegisterActivity extends BaseActivity {


    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.btn_ensure)
    Button mButton;

    private String phoneNum;
    private String passWord;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    @Override
    public int getLayoutId() {
        return R.layout.activity_register;
    }

    @Override
    public void initData() {

    }


    @Override
    public void initListener() {
    }

    @OnClick(R.id.btn_ensure)
     public void onClick(View view) {
        phoneNum=mPhone.getText().toString().trim();
        passWord=mPwd.getText().toString().trim();

    }


}
