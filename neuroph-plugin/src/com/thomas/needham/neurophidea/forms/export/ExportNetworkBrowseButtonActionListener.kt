/*
The MIT License (MIT)

Copyright (c) 2016 Tom Needham

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
 */
package com.thomas.needham.neurophidea.forms.export

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.Consumer
import com.thomas.needham.neurophidea.Constants.NETWORK_TO_EXPORT_LOCATION_KEY
import com.thomas.needham.neurophidea.actions.ShowExportNetworkFormAction
import com.thomas.needham.neurophidea.consumers.ExportNetworkConsumer
import com.thomas.needham.neurophidea.forms.create.NetworkOutputBrowseButtonActionListener
import java.awt.event.ActionEvent
import java.awt.event.ActionListener

/**
 * Created by Thomas Needham on 29/05/2016.
 */
class ExportNetworkBrowseButtonActionListener : ActionListener {
    var formInstance : ExportNetworkForm? = null
    companion object Data{
        val defaultPath = ""
        val allowedFileTypes = arrayOf("conf")
        val fileDescriptor = FileChooserDescriptor(true, false, false, false, false, false)
        val consumer : ExportNetworkConsumer? = ExportNetworkConsumer()
        val properties = PropertiesComponent.getInstance()
        var chosenPath = ""
    }
    override fun actionPerformed(e : ActionEvent?) {
        properties?.setValue(NETWORK_TO_EXPORT_LOCATION_KEY,defaultPath)
        FileChooser.chooseFile(fileDescriptor,ShowExportNetworkFormAction.project,null,consumer as Consumer<VirtualFile?>)
        chosenPath = properties.getValue(NETWORK_TO_EXPORT_LOCATION_KEY, defaultPath)
        formInstance?.txtExportNetwork?.text = chosenPath
    }
}