package whb.cn.com.smartpart.ui;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import butterknife.Bind;
import butterknife.OnClick;
import whb.cn.com.mylibrary.bean.CustomDataModel;
import whb.cn.com.mylibrary.common.Config;
import whb.cn.com.mylibrary.utils.Configs;
import whb.cn.com.smartpart.Lisntener.ActivityTaskCallback;
import whb.cn.com.smartpart.R;
import whb.cn.com.smartpart.bean.Account;
import whb.cn.com.smartpart.bean.InnerMeassge;
import whb.cn.com.smartpart.bean.UserBean;
import whb.cn.com.smartpart.server.ManagerEngine;
import whb.cn.com.smartpart.utils.GsonUtil;

public class LoginActivity extends BaseActivity {
    @Bind(R.id.phone)
    EditText mPhone;
    @Bind(R.id.pwd)
    EditText mPwd;
    @Bind(R.id.btn_ensure)
    Button mButton;
    @Bind(R.id.tv_result)
    TextView mTextView;
    private String phoneNum;
    private String passWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initListener() {

    }

    @OnClick(R.id.btn_ensure)
    public void onClick(View view) {
        phoneNum = mPhone.getText().toString().trim();
        passWord = mPwd.getText().toString().trim();
        Account account = new Account();
        account.setAccName("whb");
        account.setAccPwd("123");
        ManagerEngine.getSingleton().getUserManager().getLoginUser(account, new ActivityTaskCallback<InnerMeassge>(this, false) {
            @Override
            public void success(InnerMeassge userModel) {
//                Log.e("wewewe",userModel.toString());
                mTextView.append(userModel.toString());
            }
        });
    }
}
/*        phoneNum = mPhone.getText().toString().trim();
        passWord = mPwd.getText().toString().trim();
        Account  account=new Account();
        account.setAccName("admin");
        account.setAccPwd("admin");
        String jsonAccount=GsonUtil.toJson(account);
        InnerMeassge innerMeassge=new InnerMeassge();
        innerMeassge.setJsonData(jsonAccount);
        innerMeassge.setTag("login");
        innerMeassge.setRequstCode(Configs.CLIENT2MASTERSERVER);
        String jsonData=GsonUtil.toJson(innerMeassge);
        Log.e("魏红彬",jsonData);
        okSocket.addNewRequest(jsonData);*/
//    }



//}
