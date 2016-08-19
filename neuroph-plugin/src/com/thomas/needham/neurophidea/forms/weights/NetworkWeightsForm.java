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
package com.thomas.needham.neurophidea.forms.weights;

import com.intellij.uiDesigner.core.GridLayoutManager;
import com.thomas.needham.neurophidea.forms.test.NetworkResultsBrowseActionListener;
import com.thomas.needham.neurophidea.forms.test.TestNetworkFormImplementationKt;
import org.jetbrains.annotations.NotNull;
import org.neuroph.core.NeuralNetwork;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.WindowConstants;
import java.awt.BorderLayout;
import java.awt.Insets;
import java.awt.Toolkit;

/**
 * Created by thoma on 13/06/2016.
 */
public class NetworkWeightsForm extends JFrame {
    public JPanel root;
    public JPanel inner;
    public NeuralNetwork network;
    public String path;
    public double[][] weights = null;
    public JTextField[][] fields = null;
    public JButton saveButton = null;

    public NetworkWeightsForm(String path, @NotNull NeuralNetwork network) {
        super("Edit Network Weights");
        this.path = path;
        this.network = network;
        this.setContentPane(root);
        this.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
        Toolkit t = Toolkit.getDefaultToolkit();
        int x = (int) ((t.getScreenSize().getWidth() - (double) this.getWidth()) / 2.0D);
        int y = (int) ((t.getScreenSize().getHeight() - (double) this.getHeight()) / 2.0D);
        this.setLocation(x, y);
        this.weights = NetworkWeightsFormImplementationKt.GetNetworkWeights(this);
        this.fields = NetworkWeightsFormImplementationKt.CreateFields(this);
        this.saveButton = NetworkWeightsFormImplementationKt.CreateSaveButton(this);
        this.pack();
        this.setVisible(true);
    }

    {
// GUI initializer generated by IntelliJ IDEA GUI Designer
// >>> IMPORTANT!! <<<
// DO NOT EDIT OR ADD ANY CODE HERE!
        $$$setupUI$$$();
    }

    /**
     * Method generated by IntelliJ IDEA GUI Designer
     * >>> IMPORTANT!! <<<
     * DO NOT edit this method OR call it in your code!
     *
     * @noinspection ALL
     */
    private void $$$setupUI$$$() {
        root = new JPanel();
        root.setLayout(new BorderLayout(0, 0));
        inner = new JPanel();
        inner.setLayout(new GridLayoutManager(1, 1, new Insets(5, 10, 0, 5), -1, -1));
        root.add(inner, BorderLayout.CENTER);
    }

    /**
     * @noinspection ALL
     */
    public JComponent $$$getRootComponent$$$() {
        return root;
    }
}
