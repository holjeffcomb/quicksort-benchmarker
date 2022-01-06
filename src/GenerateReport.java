/*
Author: Jeffrey Holcomb
Date: 9/13/2020
Course: CMSC 451
Title: Generate Quick Sort Benchmark Report
 */

/*
This class reads data from either the RecursiveOutput.txt or IterativeOutput.txt files and
performs the necessary calculations for generating the report data. The methods coefficientOfVariation,
standardDeviation, and mean, were copied from https://www.geeksforgeeks.org/program-coefficient-variation/
 */

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.text.DecimalFormat;

public class GenerateReport extends JFrame {

    private GenerateReport(String[] sizeData, float[] countAvgData, float[] countCoefData, float[] timeAvgData, float[] timeCoefData, String filePath){
        super("Quick Sort Report");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(480, 300));

        JPanel mainPanel = new JPanel();
        JTable mainTable = new JTable(11, 5);

        // Populate column headers
        mainTable.setValueAt("Size", 0, 0);
        mainTable.setValueAt("Avg Count", 0, 1);
        mainTable.setValueAt("Coef Count", 0, 2);
        mainTable.setValueAt("Avg Time", 0, 3);
        mainTable.setValueAt("Coef Time", 0, 4);

        // Populate table
        for(int i = 1; i < sizeData.length+1; i++){
            mainTable.setValueAt(sizeData[i-1], i, 0);
            mainTable.setValueAt(countAvgData[i-1], i, 1);
            mainTable.setValueAt(getPercent(countCoefData[i-1]), i, 2);
            mainTable.setValueAt(timeAvgData[i-1], i, 3);
            mainTable.setValueAt(getPercent(timeCoefData[i-1]), i, 4);
        }

        mainPanel.add(mainTable);
        mainPanel.add(new JLabel(filePath));

        this.add(mainPanel);
        this.pack();
        setVisible(true);
    }

    public static void main(String[] args) {

        // Data arrays to be sent to report
        String[] sizeData = new String[10];
        float[] countAvgArr = new float[10];
        float[] countCoefArr = new float[10];
        float[] timeAvgArr = new float[10];
        float[] timeCoefArr = new float[10];

        int index = 0; // index for while loop

        File selectedFile;

        try {

            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));

            int result = fileChooser.showOpenDialog(new JOptionPane());
            if (result == JFileChooser.APPROVE_OPTION) {
                selectedFile = fileChooser.getSelectedFile();

                BufferedReader br = new BufferedReader(new FileReader(selectedFile));
                String contentLine = br.readLine();

                while (contentLine != null) { // iterate through each line of data file

                    String[] tokens = contentLine.split(" ");

                    int[] countArr = new int[tokens.length-1];
                    int[] timeArr = new int[tokens.length-1];

                    sizeData[index] = tokens[0];

                    for (int i = 1; i < tokens.length; i++){ // iterate through each number pair
                        // Convert String values to int pairs and store in arrays
                        int[] vals = findVals(tokens[i]);
                        countArr[i-1] = vals[0];
                        timeArr[i-1] = vals[1];
                    }

                    // Perform calculations on data and store in report arrays
                    countAvgArr[index] = findAverage(findSum(countArr), countArr.length);
                    countCoefArr[index] = coefficientOfVariation(countArr, countArr.length);
                    timeAvgArr[index] = findAverage(findSum(timeArr), timeArr.length);
                    timeCoefArr[index] = coefficientOfVariation(timeArr, timeArr.length);

                    contentLine = br.readLine();
                    index++;
                }

                new GenerateReport(sizeData, countAvgArr, countCoefArr, timeAvgArr, timeCoefArr, selectedFile.getAbsolutePath()); // Send data to report
            }
        }
         catch(IOException io)
            {
                System.out.println("IO Exception: " + io);
            }
    }

    // Takes String value in the form ###,### and returns int value pair
    private static int[] findVals(String token) {
        String[] temp = token.split(",");
        int[] vals = new int[2];
        vals[0] = Integer.parseInt(temp[0]);
        vals[1] = Integer.parseInt(temp[1]);
        return vals;
    }

    // Helper method for findAverage
    private static int findSum(int[] arr) {
        int sum = 0;
        for (int ele : arr) sum += ele;
        return sum;
    }

    // Find average of array
    private static float findAverage(int sum, int count) {
        return (float)sum/count;
    }

    // Helper method to coefficientOfVariation
    private static float mean(int[] arr, int n) {
        float sum = 0;
        for (int i = 0; i < n; i++)
            sum = sum + arr[i];
        return sum / n;
    }

    // Helper method to coefficientOfVariation
    private static float standardDeviation(int[] arr, int n) {
        float sum = 0;
        for (int i = 0; i < n; i++)
            sum = sum + (arr[i] - mean(arr, n)) * (arr[i] - mean(arr, n));
        return (float)Math.sqrt(sum / (n - 1));
    }

    // Find coefficient of variation of array
    private static float coefficientOfVariation(int[] arr, int n) {
        return standardDeviation(arr, n) / mean(arr, n);
    }

    // Convert float to percentage format
    private static String getPercent(float val) {
        DecimalFormat df = new DecimalFormat("###.##");
        return df.format(val*100) + "%";
    }

}
