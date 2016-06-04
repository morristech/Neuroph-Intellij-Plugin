package com.thomas.needham.neurophidea.consumers

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.Consumer
import com.thomas.needham.neurophidea.Constants.TEST_FORM_TESTING_SET_LOCATION_KEY
import com.thomas.needham.neurophidea.Constants.VERSION_KEY
import com.thomas.needham.neurophidea.actions.ShowCreateNetworkFormAction
import com.thomas.needham.neurophidea.forms.create.CreateTrainingSetBrowseButtonActionListener
import com.thomas.needham.neurophidea.forms.test.TestingSetBrowseButtonActionListener

/**
 * Created by thoma on 04/06/2016.
 */
class TestNetworkSetFileConsumer : Consumer<VirtualFile?> {

    constructor(){

    }
    companion object Data{
        @JvmStatic var properties = PropertiesComponent.getInstance()
        @JvmStatic var version = properties.getValue(VERSION_KEY)
        @JvmStatic var path : String? = ""
    }

    override fun consume(p0 : VirtualFile?) {
        for(ext : String in TestingSetBrowseButtonActionListener.allowedFileTypes){
            if(p0?.extension?.equals(ext)!!){
                path = p0?.path
                properties.setValue(TEST_FORM_TESTING_SET_LOCATION_KEY, path)
                return
            }
        }
        Messages.showErrorDialog(ShowCreateNetworkFormAction.project,"Invalid testing data selected","Error")
    }
}