package whb.cn.com.smartpart.ui;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.OnClick;
import whb.cn.com.smartpart.R;

public class ChatActivity extends BaseActivity  {
    @Bind(R.id.text)
    TextView mTextView;
    @Bind(R.id.edit)
    EditText mEditText;
    @Bind(R.id.btn)
    Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_chat;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.btn)
    public void click(View view) {
//        okSocket.addNewRequest(mEditText.getText().toString().trim());
    }

}
