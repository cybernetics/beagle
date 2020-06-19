/*
 * Copyright 2020 ZUP IT SERVICOS EM TECNOLOGIA E INOVACAO SA
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package br.com.zup.beagle.widget.ui

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.view.View
import br.com.zup.beagle.engine.renderer.ActivityRootView
import br.com.zup.beagle.engine.renderer.ui.UndefinedViewRenderer
import br.com.zup.beagle.widget.form.InputWidget
import br.com.zup.beagle.widget.pager.PageIndicatorOutput
import br.com.zup.beagle.widget.pager.PageIndicatorComponent

internal class UndefinedWidget : InputWidget(), PageIndicatorComponent {

    override fun onErrorMessage(message: String) {}

    override fun initPageView(pageIndicatorOutput: PageIndicatorOutput) {}

    override fun onItemUpdated(newIndex: Int) {}

    override fun setCount(pages: Int) {}

    override fun getValue(): Any = ""

    override fun buildView(context: Context): View = UndefinedViewRenderer(this).build(
        ActivityRootView(context as AppCompatActivity)
    )
}