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
package com.thomas.needham.neurophidea.project

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.projectRoots.SdkAdditionalData
import com.intellij.util.xmlb.XmlSerializerUtil
import com.sun.org.apache.xml.internal.serialize.XMLSerializer

/**
 * Created by Thomas Needham on 06/06/2016.
 */
class NeurophSDKData : SdkAdditionalData, PersistentStateComponent<NeurophSDKData>{
    var homePath = ""
    var version = ""
    companion object Data{
        val INSTANCE = NeurophSDKData()
    }
    constructor(){

    }
    constructor(homePath: String, version: String){
        this.homePath = homePath
        this.version = version
    }
    @SuppressWarnings("CloneDoesntCallSuperClone")
    override fun clone() : Any? {
        throw CloneNotSupportedException()
    }

    override fun getState() : NeurophSDKData? {
        return this
    }

    override fun loadState(p0 : NeurophSDKData?) {
        XmlSerializerUtil.copyBean<NeurophSDKData?>(p0!!, this);
    }
}