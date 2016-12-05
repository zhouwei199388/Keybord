package zw.com.keybord;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.InputType;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 15090 on 2016/11/27.
 */

public class MainActivity  extends Activity implements View.OnClickListener,View.OnTouchListener{

    private EditText tv_carplate, tv_cartype, tv_carbrand;

    private KeyboardUtil mKeyboardUtil;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }
    private void initView() {
        tv_carplate = (EditText) findViewById(R.id.tv_carplate);
        tv_cartype = (EditText) findViewById(R.id.tv_cartype);
        tv_carbrand = (EditText) findViewById(R.id.tv_carbrand);
        tv_carplate.setOnTouchListener(this);
        tv_carbrand.setOnTouchListener(this);
        tv_cartype.setOnTouchListener(this);
        findViewById(R.id.tjcl_btn).setOnClickListener(this);
    }

    @Override
    public void onClick(View key) {
        switch (key.getId()) {

            case R.id.tjcl_btn:
                EditText editText_1 = (EditText) findViewById(R.id.tv_carbrand);

                String str_pp = editText_1.getText().toString();

                if (null != str_pp && str_pp.trim().length() > 0) {
                    EditText editText_2 = (EditText) findViewById(R.id.tv_cartype);

                    String str_xh = editText_2.getText().toString();
                    if (null != str_xh && str_xh.trim().length() > 0) {
                        EditText editText_3 = (EditText) findViewById(R.id.tv_carplate);

                        String str_cp = editText_3.getText().toString();
                        //车牌验证正则
                        Pattern pattern = Pattern
                                .compile("^[\u4e00-\u9fa5]{1}[A-Z]{1}[A-Z_0-9]{5}$");
                        Matcher matcher = pattern.matcher(str_cp);
                        if (matcher.matches()) {
//                            addCar(str_pp, str_xh, str_cp);
                            Toast.makeText(this,"输入成功",Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(this,"车牌号格式不对！",Toast.LENGTH_SHORT).show();
                        }
//					if (null != str_cp && str_cp.trim().length() > 0) {
//						Toast.makeText(AddCarActivity.this, "添加车辆成功！",
//								Toast.LENGTH_SHORT).show();
//						addCar(str_pp, str_xh, str_cp);
//					} else {
//						Toast.makeText(AddCarActivity.this, "车牌不能为空！",
//								Toast.LENGTH_SHORT).show();
//					}
                    } else {
                        Toast.makeText(this,"型号不能为空！",Toast.LENGTH_SHORT).show();

                    }
                } else {
                    Toast.makeText(this,"品牌不能为空！",Toast.LENGTH_SHORT).show();

                }

                break;

            default:
                break;
        }
    }
    @Override
    public boolean onTouch(View v, MotionEvent arg1) {
        switch (v.getId()) {
            case R.id.tv_carplate:
                int inputback = tv_carplate.getInputType();
                tv_carplate.setInputType(InputType.TYPE_NULL);
                if (mKeyboardUtil == null) {
                    mKeyboardUtil = new KeyboardUtil(this,
                            getApplicationContext(), tv_carplate);
                    mKeyboardUtil.showKeyboard();
                } else {
                    mKeyboardUtil.showKeyboard();
                }

                manageSoftInput(getApplicationContext(), tv_carbrand, true);
               manageSoftInput(getApplicationContext(), tv_cartype, true);
                tv_carplate.setInputType(inputback);
                break;
            case R.id.tv_carbrand:
                if (mKeyboardUtil != null) {
                    mKeyboardUtil.hideKeyboard();
                }
            case R.id.tv_cartype:
                if (mKeyboardUtil != null) {
                    mKeyboardUtil.hideKeyboard();
                }
                break;
            default:
                break;
        }
        return false;
    }

    /**
     * 键盘操作
     *
     * @param isColse
     *            是否要关闭 true 关闭，false 打开
     */
    public static void manageSoftInput(Context ctx, EditText editText,
                                       boolean isColse) {
        InputMethodManager imm = (InputMethodManager) ctx
                .getSystemService(Context.INPUT_METHOD_SERVICE);
        if (isColse) {
            imm.hideSoftInputFromWindow(editText.getWindowToken(), 0);
        } else {
            imm.showSoftInput(editText, 0);
        }
    }
}
