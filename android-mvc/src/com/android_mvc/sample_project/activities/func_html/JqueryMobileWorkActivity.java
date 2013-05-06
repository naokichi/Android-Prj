/**
 *
 */
package com.android_mvc.sample_project.activities.func_html;

import com.android_mvc.framework.activities.base.BaseNormalActivity;
import com.android_mvc.framework.ui.UIBuilder;

/**
 * @author OkaNao
 *
 */
public class JqueryMobileWorkActivity extends BaseNormalActivity {

    /* (非 Javadoc)
     * @see com.android_mvc.framework.activities.IBaseActivity#defineContentView()
     */
    @Override
    public void defineContentView() {

        new UIBuilder(context)
            .renderLocalHTML("html5_base.html")
        .display();

    }

}
