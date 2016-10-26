package tck.cn.mvp;


import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import tck.cn.mvp.api.ApiService;
import tck.cn.mvp.contract.MainContract;
import tck.cn.mvp.model.PhoneBean;
import tck.cn.mvp.presenter.MainPresenterImpl;


public class MainActivity extends AppCompatActivity implements MainContract.MainView, View.OnClickListener {


    private EditText phone;
    private Button find;
    private TextView province;
    private TextView city;
    private TextView areacode;
    private TextView company;
    private TextView card;
    private ProgressDialog mPd;
    private MainPresenterImpl mMainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        mMainPresenter = new MainPresenterImpl(this);


    }

    private void initView() {

        phone = (EditText) findViewById(R.id.phone);
        find = (Button) findViewById(R.id.find);
        province = (TextView) findViewById(R.id.province);
        city = (TextView) findViewById(R.id.city);
        areacode = (TextView) findViewById(R.id.areacode);
        company = (TextView) findViewById(R.id.company);
        card = (TextView) findViewById(R.id.card);

        mPd = new ProgressDialog(this);
        mPd.setMessage("正在加载中。。。");

        find.setOnClickListener(this);

    }

    @Override
    public void hideLoading() {
        mPd.dismiss();
    }

    @Override
    public void isSuccess(PhoneBean phoneBean) {
        if (phoneBean == null) {
            Toast.makeText(this, "获取数据失败，请检查网络。。。", Toast.LENGTH_SHORT).show();
        } else {
            province.setText("省：" + phoneBean.getResult().getProvince());
            city.setText("市：" + phoneBean.getResult().getCity());
            areacode.setText("区号：" + phoneBean.getResult().getAreacode());
            company.setText("品牌：" + phoneBean.getResult().getCompany());
            card.setText("卡类型：" + phoneBean.getResult().getCard());
        }
    }

    @Override
    public void onClick(View view) {
        mPd.show();
        mMainPresenter.getWheather(phone.getText().toString().trim(), ApiService.KEY);
    }
}
