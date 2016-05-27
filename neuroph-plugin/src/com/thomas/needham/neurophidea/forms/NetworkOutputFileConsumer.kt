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
package com.thomas.needham.neurophidea.forms

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.Consumer
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.newvfs.impl.VirtualDirectoryImpl
import com.intellij.util.Icons
import com.thomas.needham.neurophidea.Constants.VERSION_KEY
import com.thomas.needham.neurophidea.Constants.NETWORK_OUTPUT_LOCATION_KEY
import com.thomas.needham.neurophidea.CreateNetworkAction


/**
 * Created by thoma on 27/05/2016.
 */
class NetworkOutputFileConsumer : Consumer<VirtualDirectoryImpl?> {
    constructor(){

    }
    companion object Data{
        @JvmStatic var properties = PropertiesComponent.getInstance()
        @JvmStatic var version = properties.getValue(VERSION_KEY)
        @JvmStatic var path : String? = ""
    }
    override fun consume(p0 : VirtualDirectoryImpl?) {
        path = p0?.path
        properties.setValue(NETWORK_OUTPUT_LOCATION_KEY,path)
    }
//    override fun consume(p0 : VirtualFile?) {
//        path = p0?.path
//        properties.setValue(NETWORK_OUTPUT_LOCATION_KEY,path)
//        return
//    }

        //val dialog = Messages.showOkCancelDialog(CreateNetworkAction.project,"Invalid output file or directory", "Error", Icons.ERROR_INTRODUCTION_ICON)
}