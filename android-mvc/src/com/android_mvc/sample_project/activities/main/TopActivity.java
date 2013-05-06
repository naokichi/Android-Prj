
package com.android_mvc.sample_project.activities.main;

import android.view.View;
import android.view.View.OnClickListener;

import com.android_mvc.framework.activities.base.BaseNormalActivity;
import com.android_mvc.framework.ui.UIBuilder;
import com.android_mvc.framework.ui.UIUtil;
import com.android_mvc.framework.ui.menu.OptionMenuBuilder;
import com.android_mvc.framework.ui.menu.OptionMenuDescription;
import com.android_mvc.framework.ui.view.MButton;
import com.android_mvc.framework.ui.view.MTextView;
import com.android_mvc.sample_project.R;
import com.android_mvc.sample_project.bat.SamplePeriodicService;
import com.android_mvc.sample_project.controller.MainController;

/**
 * サンプルのトップ画面。
 *
 * @author id:language_and_engineering
 */
public class TopActivity extends BaseNormalActivity
{
    @Override
    public void defineContentView() {
        final TopActivity activity = this;

        // ここに，画面上のUI部品の定義を記述する。
        new UIBuilder(context)
                .add(
                        new MTextView(context)
                                .text("ここは，Top画面です。\n画面のレイアウトには，XMLもHTMLも使っていません。")
                                .widthWrapContent(),
                        new MTextView(context)
                                .text("このアプリの名称：" + $._(R.string.app_name) + "\n")
                                .widthWrapContent(),
                        new MButton(context)
                                .text("トーストを表示")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        UIUtil.longToast(context, "トーストです。");
                                    }
                                }),
                        new MTextView(context)
                                .text("\n友達情報をDBで管理するサンプル：")
                                .widthWrapContent(),
                        new MButton(context)
                                .text("DB登録画面へ")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MainController.submit(activity, "EDIT_DB");
                                    }
                                }),
                        new MButton(context)
                                .text("DB閲覧画面へ")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MainController.submit(activity, "VIEW_DB");
                                    }
                                }),
                        new MTextView(context)
                                .text("\nGPS機能のサンプル：")
                                .widthWrapContent(),
                        new MButton(context)
                                .text("サービスを起動")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        new SamplePeriodicService().startResident(context);
                                        UIUtil.longToast(activity, "サンプルのサービス常駐を開始しました。");
                                    }
                                }),
                        new MButton(context)
                                .text("サービスの常駐を解除")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        SamplePeriodicService.stopResidentIfActive(context);
                                        UIUtil.longToast(activity, "サンプルのサービス常駐を解除しました。");
                                    }
                                }),
                        new MButton(context)
                                .text("GoogleMapのサンプルへ")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MainController.submit(activity, "MAP_SAMPLE");
                                    }
                                }),
                        new MTextView(context)
                                .text("\nレイアウト・UIのサンプル：")
                                .widthWrapContent(),
                        new MButton(context)
                                .text("HTMLによる画面描画のサンプルへ")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MainController.submit(activity, "HTML_SAMPLE");
                                    }
                                }),
                        new MButton(context)
                                .text("jQuery Mobileによる画面描画のサンプルへ")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MainController.submit(activity, "JQUERY_SAMPLE");
                                    }
                                }),
                        new MButton(context)
                                .text("タブと通信のサンプルへ")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MainController.submit(activity, "TAB_SAMPLE");
                                    }
                                }),
                        new MButton(context)
                                .text("アニメーションのサンプル")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MainController.submit(activity, "ANIM_SAMPLE");
                                    }
                                }),
                        new MButton(context)
                                .text("jquery Mobileお試し")
                                .click(new OnClickListener() {
                                    @Override
                                    public void onClick(View v) {
                                        MainController.submit(activity, "JQUERY_WORK");
                                    }
                                })
                )
                .display();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public OptionMenuBuilder defineMenu()
    {
        final TopActivity activity = this;

        // オプションメニューを構築
        return new OptionMenuBuilder(context)
                .add(
                        new OptionMenuDescription()
                        {
                            @Override
                            protected String displayText() {
                                return "DB登録";
                            }

                            @Override
                            protected void onSelected() {
                                // 画面遷移
                                MainController.submit(activity, "EDIT_DB");
                            }
                        }
                )
                .add(
                        new OptionMenuDescription()
                        {
                            @Override
                            protected String displayText() {
                                return "DB閲覧";
                            }

                            @Override
                            protected void onSelected() {
                                // 画面遷移
                                MainController.submit(activity, "VIEW_DB");
                            }
                        }
                );
    }

    @Override
    public void onBackPressed()
    {
        // 戻るキーが押されたら終了
        moveTaskToBack(true);
    }

}
