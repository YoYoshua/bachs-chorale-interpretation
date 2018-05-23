//package main.java;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.List;
//import java.util.Map;
//
//public class FileSaver {
//    public void saveDataToFiles() {
//        //Saving to file phase
//        inputSet = transposer.transpose(inputSet);
//        final String interpretedData = "resources/interpreted.txt";
//        BufferedWriter writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter(interpretedData));
//            for (List<Float> set: interpretedList) {
//                for(int i = 0; i < set.size(); i++) {
//                    writer.write(set.get(i).toString());
//                    if(i != set.size() - 1) {
//                        writer.write(",");
//                    }
//                }
//                writer.write("\n");
//            }
//        } catch(IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if(writer != null)
//                    writer.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        inputSet = transposer.transpose(inputSet);
//        final String plik = "resources/output.txt";
//        writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter(plik));
//            for (List<Float> set: inputSet) {
//                for(int i = 0; i < set.size(); i++) {
//                    writer.write(set.get(i).toString());
//                    if(i != set.size() - 1) {
//                        writer.write(",");
//                    }
//                }
//                writer.write("\n");
//            }
//        } catch(IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if(writer != null)
//                    writer.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//
//        final String classes = "resources/classes.txt";
//        writer = null;
//        try {
//            writer = new BufferedWriter(new FileWriter(classes));
//            for(Map.Entry<String, Float> entry : interpreter.classes.entrySet()) {
//                writer.write(entry.getKey() + " - " + entry.getValue());
//                writer.write("\n");
//            }
//        } catch(IOException ex) {
//            ex.printStackTrace();
//        } finally {
//            try {
//                if(writer != null)
//                    writer.close();
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//        }
//    }
//}
