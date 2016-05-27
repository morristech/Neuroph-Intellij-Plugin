package com.thomas.needham.neurophidea.consumers

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.Consumer
import com.intellij.util.Icons
import com.thomas.needham.neurophidea.Constants
import com.thomas.needham.neurophidea.actions.CreateNetworkAction
import com.thomas.needham.neurophidea.settings.VersionSetting
import com.thomas.needham.neurophidea.Constants.TRAINING_SET_LOCATION_KEY
import com.thomas.needham.neurophidea.forms.TrainingSetBrowseButtonActionListener

/**
 * Created by thoma on 26/05/2016.
 */
class TrainingSetFileConsumer : Consumer<VirtualFile?> {

    constructor(){

    }
    companion object Data{
        @JvmStatic var properties = PropertiesComponent.getInstance()
        @JvmStatic var version = properties.getValue(Constants.VERSION_KEY)
        @JvmStatic var path : String? = ""
    }

    override fun consume(p0 : VirtualFile?) {
        for(ext : String in TrainingSetBrowseButtonActionListener.allowedFileTypes){
            if(p0?.extension?.equals(ext)!!){
                path = p0?.path
                properties.setValue(TRAINING_SET_LOCATION_KEY, path)
                return
            }
        }
        Messages.showOkCancelDialog(CreateNetworkAction.project,"Invalid training data selected","Error", Icons.ERROR_INTRODUCTION_ICON)
    }
}