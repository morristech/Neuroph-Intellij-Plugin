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
package com.thomas.needham.neurophidea.examples.kotlin

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileWriter
import java.io.IOException
import java.io.InputStreamReader
import java.util.logging.Level
import java.util.logging.Logger
import org.neuroph.core.NeuralNetwork
import org.neuroph.core.learning.SupervisedTrainingElement
import org.neuroph.core.learning.TrainingSet
import org.neuroph.util.TransferFunctionType

import org.neuroph.nnet.NeuroFuzzyPerceptron
import org.neuroph.nnet.learning.BackPropagation
import org.neuroph.core.transfer.Sigmoid
import java.util.*


object TestFuzzyPerceptron {
    @JvmStatic var inputSize = 8
    @JvmStatic var outputSize = 1
    @JvmStatic var network : NeuralNetwork? = null
    @JvmStatic var trainingSet : TrainingSet<SupervisedTrainingElement>? = null
    @JvmStatic var testingSet : TrainingSet<SupervisedTrainingElement>? = null
    @JvmStatic var layers = arrayOf(8, 8, 1)

    @JvmStatic fun loadNetwork() {
        network = NeuralNetwork.load("D:/GitHub/Neuroph-Intellij-Plugin/TestFuzzyPerceptron.nnet")
    }

    @JvmStatic fun trainNetwork() {
        val list = ArrayList<Int>()
        for (layer in layers) {
            list.add(layer)
        }

        network = NeuroFuzzyPerceptron(inputSize, Vector<Int>(inputSize), outputSize)
        trainingSet = TrainingSet<SupervisedTrainingElement>(inputSize, outputSize)
        trainingSet = TrainingSet.createFromFile("D:/GitHub/NeuralNetworkTest/Classroom Occupation Data.csv", inputSize, outputSize, ",") as TrainingSet<SupervisedTrainingElement>?
        val learningRule = BackPropagation()
        (network as NeuralNetwork).learningRule = learningRule
        (network as NeuralNetwork).learn(trainingSet)
        (network as NeuralNetwork).save("D:/GitHub/Neuroph-Intellij-Plugin/TestFuzzyPerceptron.nnet")
    }

    @JvmStatic fun testNetwork() {
        var input = ""
        val fromKeyboard = BufferedReader(InputStreamReader(System.`in`))
        val testValues = ArrayList<Double>()
        var testValuesDouble : DoubleArray
        do {
            try {
                println("Enter test values or \"\": ")
                input = fromKeyboard.readLine()
                if (input == "") {
                    break
                }
                input = input.replace(" ", "")
                val stringVals = input.split(",".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray()
                testValues.clear()
                for (`val` in stringVals) {
                    testValues.add(java.lang.Double.parseDouble(`val`))
                }
            } catch (ioe : IOException) {
                ioe.printStackTrace(System.err)
            } catch (nfe : NumberFormatException) {
                nfe.printStackTrace(System.err)
            }

            testValuesDouble = DoubleArray(testValues.size)
            for (t in testValuesDouble.indices) {
                testValuesDouble[t] = testValues[t].toDouble()
            }
            network?.setInput(*testValuesDouble)
            network?.calculate()
        } while (input != "")
    }

    @JvmStatic fun testNetworkAuto(setPath : String) {
        var total : Double = 0.0
        val list = ArrayList<Int>()
        val outputLine = ArrayList<String>()
        for (layer in layers) {
            list.add(layer)
        }

        testingSet = TrainingSet.createFromFile(setPath, inputSize, outputSize, ",") as TrainingSet<SupervisedTrainingElement>?
        val count : Int = testingSet?.elements()?.size!!
        var averageDeviance = 0.0
        var resultString = ""
        try {
            val file = File("Results " + setPath)
            val fw = FileWriter(file)
            val bw = BufferedWriter(fw)

            for (i in 0..testingSet?.elements()?.size!! - 1) {
                val expected : Double
                val calculated : Double
                network?.setInput(*testingSet?.elementAt(i)!!.input)
                network?.calculate()
                calculated = network?.output!![0]
                expected = testingSet?.elementAt(i)?.idealArray!![0]
                println("Calculated Output: " + calculated)
                println("Expected Output: " + expected)
                println("Deviance: " + (calculated - expected))
                averageDeviance += Math.abs(Math.abs(calculated) - Math.abs(expected))
                total += network?.output!![0]
                resultString = ""
                for (cols in 0..testingSet?.elementAt(i)?.inputArray?.size!! - 1) {
                    resultString += testingSet?.elementAt(i)?.inputArray!![cols].toString() + ", "
                }
                for (t in 0..network?.output!!.size - 1) {
                    resultString += network?.output!![t].toString() + ", "
                }
                resultString = resultString.substring(0, resultString.length - 2)
                resultString += ""
                bw.write(resultString)
                bw.flush()

                println()
                println("Average: " + (total / count).toString())
                println("Average Deviance % : " + (averageDeviance / count * 100).toString())
                bw.flush()
                bw.close()
            }
        } catch (ex : IOException) {
            ex.printStackTrace()
        }

    }
}

