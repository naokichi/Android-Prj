package com.example.num_hit;

import android.os.Bundle;
import android.app.Activity;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.View;
import java.util.Random;
import android.widget.*;

public class MainActivity extends Activity {

	// 正解しているか
	private Boolean lbCorrected = false;
	// 正解値
	private int liCorrectValue = -1;
	// 判定ボタン
	private Button btn_Judge = null;
	// 小見出しView
	private TextView txv_Title = null;
	// 判定結果表示View
	private TextView txv_Result = null;
	// 判定値入力View
	private EditText edt_Input = null;
	// 判定ボタン押下時処理
	private View.OnClickListener onClick = new View.OnClickListener() {

		@Override
		public void onClick(View view) {
			// 正解してない時
			if(!lbCorrected){
				// 正解チェック
				if(!this.checkAnswer())
					return;
				// 諸文言変更
				txv_Title.setText(getString(R.string.title_correct));
				btn_Judge.setText(getString(R.string.btn_correct));
				// 文字色変更
				txv_Result.setTextColor(Color.RED);
				lbCorrected = true;
			}
			// 正解後なら
			else
				// 初期化
				setDft();
		}
		// 正解チェック
		private Boolean checkAnswer()
		{
			try{
				int iAnsValue = Integer.valueOf(edt_Input.getText().toString());
				String sReslut = "";
				Boolean isCorrect = false;
				// 正解はもっと小さい
				if(iAnsValue > liCorrectValue)
				{
					sReslut = getString(R.string.result_smaller);
				}
				// 正解はもっと大きい
				else if(iAnsValue < liCorrectValue)
				{
					sReslut = getString(R.string.result_bigger);
				}
				else
				{
					sReslut = getString(R.string.result_correct);
					isCorrect = true;
				}
				// 判定結果文言変更
				txv_Result.setText(sReslut);
				return isCorrect;
			}
			catch(Exception ex)
			{
				// 保留
			}
			return false;
		}
	};
	// 入力View入力後処理
	private TextWatcher txtWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence charsequence, int i, int j, int k) {}

		@Override
		public void beforeTextChanged(CharSequence charsequence, int i, int j, int k) {}

		@Override
		public void afterTextChanged(Editable editable) {
			String sFinalValue = "";
			String sEdited = editable.toString();
			// 空
			if(sEdited.equals(""))
				sFinalValue = "0";
			else
			{
				int iEdited = Integer.parseInt(sEdited);
				// 入力値が0～100以外なら
				if(iEdited < 0)
					sFinalValue = "0";
				else if(iEdited > 100)
					sFinalValue = "100";
			}
			if(!sFinalValue.equals(""))
				edt_Input.setText(sFinalValue);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.init();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	// 初期設定
	private void init()
	{
		// View取得
		this.getView();
		// コールバックリスナー登録
		this.setCallBackListener();
		// 初期状態セット
		this.setDft();
	}
	// View取得
	private void getView()
	{
		this.btn_Judge = (Button)this.findViewById(R.id.btn_Judge);
		this.txv_Result = (TextView)this.findViewById(R.id.txv_Result);
		this.edt_Input = (EditText)this.findViewById(R.id.edt_Input);
		this.txv_Title = (TextView)this.findViewById(R.id.txv_Title);
	}
	// コールバックリスナー登録
	private void setCallBackListener()
	{
		// ボタン
		this.btn_Judge.setOnClickListener(this.onClick);
		// 入力View
		this.edt_Input.addTextChangedListener(this.txtWatcher);
	}
	// DFT値設定
	private void setDft()
	{
		// 正解値決定
		this.liCorrectValue = new Random().nextInt(100);
		// フラグ初期化
		this.lbCorrected = false;
		// 文言初期化
		this.txv_Result.setText(this.getString(R.string.result_dft));
		this.txv_Title.setText(this.getString(R.string.title_dft));
		this.edt_Input.setText("50");
		this.btn_Judge.setText(this.getString(R.string.btn_dft));
		// 文字色初期化
		this.txv_Result.setTextColor(Color.BLACK);
	}
}