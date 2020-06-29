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

package br.com.zup.beagle.android.components.form

import android.view.View
import br.com.zup.beagle.android.action.Action
import br.com.zup.beagle.android.components.Button
import br.com.zup.beagle.android.context.ContextData
import br.com.zup.beagle.android.data.PreFetchHelper
import br.com.zup.beagle.android.utils.handleEvent
import br.com.zup.beagle.android.view.ViewFactory
import br.com.zup.beagle.android.view.custom.BeagleFlexView
import br.com.zup.beagle.android.widget.RootView
import br.com.zup.beagle.android.widget.WidgetView
import br.com.zup.beagle.core.ServerDrivenComponent
import br.com.zup.beagle.core.Style

/**
 * component will define a submit handler for a SimpleForm.
 *
 * @param context define the contextData that be set to form
 *
 * @param child
 *                  define the submit handler.
 *                  It is generally set as a button to be clicked after a form is filled up.
 * @param enabled
 *                  define as "true" by default and it will enable the button to be clicked on.
 *                  If it is defined as "false" the button will start as "disabled"
 *
 */
data class SimpleForm (
    val context: ContextData,
    val onSubmit:List<Action>,
    val children: List<ServerDrivenComponent>
): WidgetView() {

    @Transient
    private val viewFactory: ViewFactory = ViewFactory()

    @Transient
    private val preFetchHelper: PreFetchHelper = PreFetchHelper()

    override fun buildView(rootView: RootView): View {
        preFetchHelper.handlePreFetch(rootView, onSubmit)
        return viewFactory.makeBeagleFlexView(rootView.getContext(), style  ?: Style())
            .apply {
                tag = this@SimpleForm
                addChildrenForm(this,rootView)
                handleEvent(rootView, onSubmit, "onPress")
            }
    }

    private fun addChildrenForm(beagleFlexView: BeagleFlexView, rootView: RootView){
        children.forEach{child ->
            beagleFlexView.addServerDrivenComponent(child, rootView)
        }
    }

    fun submit(rootView: RootView) {
        onSubmit.forEach { action ->
            action.execute(rootView)
        }
    }
}
