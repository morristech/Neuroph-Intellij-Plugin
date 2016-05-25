/* The MIT License (MIT)

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
package com.thomas.needham.neurophidea

import com.intellij.ide.util.PropertiesComponent
import com.intellij.openapi.fileChooser.FileChooser
import com.intellij.openapi.fileChooser.FileChooserDescriptor
import com.intellij.openapi.options.Configurable
import com.intellij.openapi.vcs.changes.ui.SelectFilePathsDialog
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.Consumer
import java.awt.Dimension
import java.awt.event.ItemEvent
import java.util.*
import javax.swing.JComboBox
import javax.swing.JComponent
import javax.swing.JLabel
import javax.swing.JPanel
import com.thomas.needham.neurophidea.Constants.VERSION_KEY
import com.thomas.needham.neurophidea.Constants.LOCATION_KEY
import java.awt.event.ActionListener
import javax.swing.JButton
import javax.swing.JTextField

/**
 * Created by thoma on 24/05/2016.
 */
class VersionSetting : Configurable {
    var properties : PropertiesComponent = PropertiesComponent.getInstance()
    val versions : ArrayList<String?> = ArrayList<String?>()

    companion object Interface {
        var modified : Boolean = false
        val panel = JPanel(null)
        val versionLabel = JLabel("Neuroph Version")
        val versionSelector = JComboBox<String>()
        val insets = panel.insets
        val versionLabelSize = versionLabel.preferredSize
        val versionSelectorSize = versionSelector.preferredSize
        val locationLabel = JLabel("Neuroph.jar Location")
        val locationTextBox = JTextField()
        val locationBrowseButton = JButton("...")
    }

    override fun isModified() : Boolean {
        return modified
    }

    override fun getHelpTopic() : String? {
        return ""
    }

    override fun getDisplayName() : String? {
        return "Select Neuroph Version"
    }

    override fun reset() {

    }

    override fun createComponent() : JComponent? {
        versionLabel.preferredSize = Dimension(100,50)
        versionLabel.setBounds(insets.left + 50,insets.top + 50,100,25)
        panel.add(versionLabel)
        versionSelector.preferredSize = Dimension(200,50)
        versionSelector.setBounds(insets.left + 200,insets.top + 50,200,25)
        PopulateVersions()
        versionSelector.addItemListener { e ->
            if(e.stateChange == ItemEvent.SELECTED){
                modified = true
            }

        }
        panel.add(versionSelector)
        locationLabel.preferredSize = Dimension(150,50)
        locationLabel.setBounds(insets.left + 50,insets.top + 100, 150,25)
        panel.add(locationLabel)
        locationTextBox.preferredSize = Dimension(200,50)
        locationTextBox.setBounds(insets.left + 200,insets.top + 100, 200,25)
        PopulateLocation()
        panel.add(locationTextBox)
        locationTextBox.document.addDocumentListener(JARLocationDocumentListener())
        locationBrowseButton.preferredSize = Dimension(25,25)
        locationBrowseButton.setBounds(insets.left + 400,insets.top + 100, 25,25)
        locationBrowseButton.addActionListener(ActionListener { e ->
            val desc = FileChooserDescriptor(true,false,true,true,false,false)
            val consumer : Consumer<VirtualFile?> = NeurophJarConsumer() as Consumer<VirtualFile?>
            FileChooser.chooseFile(desc,InitAction.project,null,consumer)
        })
        panel.add(locationBrowseButton)
        panel.isVisible = true
        return panel
    }

    private fun PopulateLocation() {
        if(properties.isValueSet(LOCATION_KEY)){
            locationTextBox.text = properties.getValue(LOCATION_KEY,"")
        }
    }

    private fun PopulateVersions() {
        for(i in IntRange(0,NeurophVersions.values().size - 1)){
            versions.add(NeurophVersions.values()[i].version)
            versionSelector.addItem(versions[i])
        }
        if(properties.isValueSet(VERSION_KEY)) {
            versionSelector.selectedItem = versions.find { x -> x == properties.getValue(VERSION_KEY,"") }
        }
    }

    override fun apply() {
        if(modified) {
            properties.setValue(VERSION_KEY, versionSelector.getItemAt(versionSelector.selectedIndex))
            properties.setValue(LOCATION_KEY,locationTextBox.text)
        }
    }

    override fun disposeUIResources() {
        panel.remove(versionSelector)
        panel.remove(versionLabel)
        panel.isVisible = false
    }
}