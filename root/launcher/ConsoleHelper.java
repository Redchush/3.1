package root.launcher;

import java.io.BufferedReader;
import java.io.InputStreamReader;

class ConsoleHelper {

    private static final String ERROR_MSG =  "Произошла ошибка при попытке ввода текста. Попробуйте еще раз.";
    private static final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

    public static void writeMessage(String message){
        if (message != null) {
            System.out.println(message);
        }
    }

    public static String readString(){
        String line = "";
        while (true){
            try{
                line = reader.readLine();
                if (line == null) throw new Exception();
                break;
            }catch (Exception e){
                writeMessage(ERROR_MSG);
            }
        }
        return line;
    }

    public static int readInt(){
        int result = 0;
        while (true){
            try{
                String line = readString();
                if ( (line == null) || (line.length() == 0) )throw new NumberFormatException();
                result = Integer.parseInt(line);
                break;
            }catch (Exception e){
                writeMessage(ERROR_MSG);

            }
        }

        return result;
    }
}
