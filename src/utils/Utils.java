package utils;

import exceptions.FileException;
import models.SodokuModel;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Utils {
    private static String[] path={
            "src\\data\\level.txt",
            "src\\data\\data.txt"
    };


    public static String convertSecondToMinute(int time) {
        int min = time / 60;
        int sec = time % 60;
        return String.format("%02d:%02d", min, sec);
    }

    public static double random(int min,int max){
        return Math.random()*(max-min+1)+min;
    }

    public static List<String> getLevel(){
        List<String> data=new ArrayList<>();
        FileReader fileReader;
        BufferedReader bufferedReader;
        try{
            fileReader=new FileReader(path[0]);
            bufferedReader=new BufferedReader(fileReader);
            String line;
            while ((line=bufferedReader.readLine())!=null){
                data.add(line);
            }
            fileReader.close();
            bufferedReader.close();

        }catch (Exception e){
            throw new FileException("Not found level data!");
        }

        return data;

    }


    public static SodokuModel getData(){
        FileInputStream fileInputStream;
        ObjectInputStream objectInputStream;
        SodokuModel sodokuModel=null;
        try{
            fileInputStream=new FileInputStream(path[1]);
            objectInputStream=new ObjectInputStream(fileInputStream);
            sodokuModel= (SodokuModel) objectInputStream.readObject();
            fileInputStream.close();
            objectInputStream.close();

        }catch (Exception e){
            throw new FileException("Not found data player!");
        }
        return sodokuModel;
    }
}
